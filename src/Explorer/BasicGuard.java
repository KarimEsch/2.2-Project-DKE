package Explorer;

import Explorer.Action.ActionsManager;

import java.awt.geom.Rectangle2D;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BasicGuard extends Agent{
    public Move BasicGuard(ArrayList<Move> moves){
        Random generator = new Random();
        int index = generator.nextInt(moves.size());
        Move move = moves.get(index);
        return move;
    }

    public static void main(String[] args){
        BasicGuard b = new BasicGuard();
        String mapD = System.getProperty("user.dir")+System.getProperty("file.separator")+"src"+System.getProperty("file.separator")+"GameControllerSample"+System.getProperty("file.separator")+"testmap.txt";
        Scenario scenario = new Scenario(mapD);
        //System.out.println(scenario.spawnAreaIntruders.getLeftBoundary());
        b.setCurrentLocation(scenario.spawnAreaGuards.getLeftBoundary() + (scenario.spawnAreaGuards.getRightBoundary()-scenario.spawnAreaGuards.getLeftBoundary())/2,
                scenario.spawnAreaGuards.getTopBoundary() + (scenario.spawnAreaGuards.getBottomBoundary()-scenario.spawnAreaGuards.getTopBoundary())/2);


        Runnable run = new Runnable() {
            public void run() {
                ArrayList<Move> moves = ActionsManager.radiusMoves(b.getCurrentXLocation(),b.getCurrentYLocation());
                Move m =  b.BasicGuard(moves);
                double[] teleportCheck =  checkTeleport(b);
                if(teleportCheck[0] != -1){
                    moveExplorer(teleportCheck[1],teleportCheck[2],b);
                    System.out.println("teleported");
                }else{
                    moveExplorer(m.x,m.y,b);
                }
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(run, 0, 5, TimeUnit.MILLISECONDS);

    }

    public static void moveExplorer(double x, double y,BasicGuard b){
        String mapD = System.getProperty("user.dir")+System.getProperty("file.separator")+"src"+System.getProperty("file.separator")+"GameControllerSample"+System.getProperty("file.separator")+"testmap.txt";
        String gameFileD = System.getProperty("user.dir")+System.getProperty("file.separator")+"src"+System.getProperty("file.separator")+"GameControllerSample"+System.getProperty("file.separator")+"gamefile.txt";
        Scenario scenario = new Scenario(mapD);
        double[][] guardPositions = new double[scenario.getNumGuards()][4];
        int[] guardStates = new int[scenario.getNumGuards()];
        //System.out.println(scenario.getGameFile().toString());
        try{
            //System.out.println("working");
            FileWriter write = new FileWriter(gameFileD,false);
            PrintWriter prtln = new PrintWriter(write);
            prtln.println("signal = 1"); // semaphore code   0-> game controller, 1-> guards, 2-> intruder
            prtln.println("scenario = " + scenario.getMapDoc());
            for(int i=0;i<scenario.getNumGuards();i++){
                prtln.println("guard test = "+String.valueOf(i)+" "+String.valueOf(guardStates[i])+" "+String.valueOf(guardPositions[i][0])+" "+String.valueOf(guardPositions[i][1])+" "+String.valueOf(guardPositions[i][2])+" "+String.valueOf(guardPositions[i][3]));
            }
            prtln.println("explorer = " + x + " " + y);
            prtln.close();
        }
        catch(Exception e){
            System.out.println("failed");
            // we ar in trouble
        }
        b.setCurrentLocation(x,y);
        System.out.println("agent moved " + x + " " + y);
    }

    public static double[] checkTeleport(BasicGuard b){
        System.out.println("checking portals");
        String mapD = System.getProperty("user.dir")+System.getProperty("file.separator")+"src"+System.getProperty("file.separator")+"GameControllerSample"+System.getProperty("file.separator")+"testmap.txt";
        Scenario scenario = new Scenario(mapD);

        double x = b.getCurrentLocation().getX();
        double y = b.getCurrentLocation().getY();

        ArrayList<TelePortal> portals = scenario.getTeleportals();

        //System.out.println(scenario.getWalls().size());
        for(TelePortal p: portals){
            Rectangle2D portalRect = new Rectangle2D.Double(p.getLeftBoundary(),p.getBottomBoundary(), p.getRightBoundary() -p.getLeftBoundary() , p.getTopBoundary()-p.getBottomBoundary());
            if(portalRect.contains(x,y)){
                System.out.println("in portal");
                return new double[]{1,Double.valueOf(p.getNewLocation()[0]),Double.valueOf(p.getNewLocation()[1])};
            }
        }
        return new double[]{-1,-1,-1};
    }
}
