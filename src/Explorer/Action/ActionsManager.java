package Explorer.Action;

import Explorer.*;
import Explorer.Geometry.Angle;
import Explorer.Geometry.Distance;
import Explorer.Geometry.Point;

import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class ActionsManager {

    public static ArrayList<Move> getAllMoves(double my_x, double my_y){
        ArrayList movesList = new ArrayList<>();
        ArrayList AMovesList = new ArrayList();

        //////////////////////Move actions /////////////////////////////

        ///--Move along x--
        for(int x = 0; x<100; x++){
            AMove m = new AMove(new Distance(new Point(my_x,my_y),new Point(my_x+x,my_y)));
            AMovesList.add(m);
            Move f_m = new Move(my_x+x,my_y);
            movesList.add(f_m);
        }
        ///--Move along x--
        for(int y = 0; y<100; y++){
            AMove m = new AMove(new Distance(new Point(my_x,my_y),new Point(my_x,my_y+y)));
            AMovesList.add(m);
            Move f_m = new Move(my_x,my_y+y);
            movesList.add(f_m);
        }

        /////////////////////Rotate and move action //////////////////////////

        //0,0174533 radians is 1 degree
        double r = 0.0174533;
        double init = 0;

        /*
        calculate movement with angle
        xx = x + (d * cos(alpha))
        yy = y + (d * sin(alpha))
         */

        for(int x=0; x<=360; x++) {
            init = init + r;
            Angle a = new Angle(init);
            for (int n = 0; n < 100; n++) {
                AMove m = new AMove(new Distance(new Point(my_x, my_y), new Point(my_x + (n * Math.cos(init)), my_y)));
                AMovesList.add(m);
                Move f_m = new Move(my_x + (n * Math.cos(init)), my_y);
                movesList.add(f_m);
            }
        }


        return movesList;
    }

    public static ArrayList<Move> radiusMoves(double my_x, double my_y){
        double radius = 15;
        ArrayList movesList = new ArrayList<>();
        ArrayList AMovesList = new ArrayList();
        for(double x = my_x-radius; x<my_x+radius; x++){
            for(double y = my_y-radius; y<my_y+radius; y++){
                if(x>0 && y>0 && x<=120 && y<=80){
                    AMove m = new AMove(new Distance(new Point(my_x,my_y),new Point(x,y)));
                    if(!checkObstaclesHit(m)){
                        AMovesList.add(m);
                        Move f_m = new Move(x,y);
                        movesList.add(f_m);
                    }
                }
            }
        }
        return movesList;
    }

    private static boolean checkObstaclesHit(AMove move){
        //System.out.println("checking collision");
        boolean hit = false;
        String mapD = System.getProperty("user.dir")+System.getProperty("file.separator")+"src"+System.getProperty("file.separator")+"GameControllerSample"+System.getProperty("file.separator")+"testmap.txt";
        Scenario scenario = new Scenario(mapD);
        Point pointA = move.getDistance().getPointA();
        Point pointB = move.getDistance().getPointB();

        double m = (pointA.getY()-pointB.getY())/(pointA.getX()-pointB.getX());
        double c = (pointA.getY()-pointA.getX())*m;

        ArrayList<Area> walls = scenario.getWalls ();
        //System.out.println(walls.get(0));
        Line2D line = new Line2D.Double(pointA.getX(), pointA.getY(), pointB.getX(), pointB.getY());
        //System.out.println(line.getBounds());
        for(Area w: walls){
            Rectangle2D wallRect = new Rectangle2D.Double(w.getLeftBoundary(),w.getBottomBoundary(), w.getRightBoundary() -w.getLeftBoundary() +0.5, w.getTopBoundary()-w.getBottomBoundary() +0.5);
            if(line.contains(wallRect.getBounds()) || wallRect.intersectsLine(line) || wallRect.contains(line.getBounds())){
                //System.out.println("hit");
                return true;
            }
        }
        /*
        //System.out.println(hit);
        scenario.getWalls().get(0);
        for(int x=0; x<20; x++){
            for(int y=0; y<20;y++){
                System.out.println(x + " " + y +" " +scenario.inWall(x,y));
            }
        }

         */
        return hit;

    }

    public static boolean checkTeleports(QLearning a){
        System.out.println("checking portals");
        double x = a.getCurrentLocation().getX();
        double y = a.getCurrentLocation().getY();

        String mapD = System.getProperty("user.dir")+System.getProperty("file.separator")+"src"+System.getProperty("file.separator")+"GameControllerSample"+System.getProperty("file.separator")+"testmap.txt";
        Scenario scenario = new Scenario(mapD);

        ArrayList<TelePortal> portals = scenario.getTeleportals();

        System.out.println(scenario.getTeleportals().size());
        for(TelePortal p: portals){
            if(p.contains(x,y)){
                System.out.println("in portal");
                a.setCurrentLocation(p.getNewLocation()[0],p.getNewLocation()[1]);
                QLearning.moveExplorer(p.getNewLocation()[0],p.getNewLocation()[1],a);
                return true;
            }
        }

        return false;
    }


}
