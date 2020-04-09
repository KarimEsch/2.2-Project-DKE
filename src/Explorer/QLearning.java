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

import static java.lang.Math.abs;

public class QLearning extends Agent {
    final static double threshold = 0.3;
    double targetDirection = 145;
    final static int targetX = 4;
    final static int targetY = 1;


    public Move Qlearning(ArrayList<Move> moves){
        //If the random number is smaller than the threshold:
        //Pick a random move out of the set of all moves
        Random generator = new Random();
        double epsilon = Math.random();
        if (epsilon < threshold) {
            int randomIndex = generator.nextInt(moves.size());
            Move randomMove = moves.get(randomIndex);
            System.out.println(randomIndex);
            return randomMove;
        }
        //Else, evaluate all moves and pick the move with the highest evaluation (lowest error in this case)
        else{
            int bestIndex = 0;
            double lowestError = Double.POSITIVE_INFINITY;
            //Loop through all moves
            for (int i=0; i<moves.size();i++){
                //Calculate evaluation
                double currentAbsoluteError = evaluateMove(moves.get(i),targetDirection);
                //If evaluation is better than current best, set new current best
                if(currentAbsoluteError<lowestError){
                    bestIndex = i;
                    lowestError = currentAbsoluteError;
                }
            }
            return moves.get(bestIndex);
        }
    }


    public double evaluateMove(Move m, double targetDirection) {
        double absoluteError;
        double movingDirection;
        double xVector = getCurrentXLocation() - m.getX();
        double yVector = getCurrentYLocation() - m.getY();
        if (yVector == 0) {
            if (xVector > 0) {
                movingDirection = 0;
            } else {
                movingDirection = 180;
            }
        } else if (xVector == 0) {
            if (yVector > 0) {
                movingDirection = 90;
            } else {
                movingDirection = 270;
            }
        } else {
            movingDirection = Math.toDegrees(Math.atan(yVector / xVector));
            System.out.println("Degree of movement: " + movingDirection);
        }
        absoluteError = abs(targetDirection - movingDirection);

        return absoluteError;

    }

    public void updateDirection(){
        double xVector = targetX - getCurrentXLocation();
        double yVector = targetY - getCurrentYLocation();
        
        if (yVector == 0) {
            if (xVector > 0) {
                this.targetDirection = 0;
            } else {
                this.targetDirection = 180;
            }
        } else if (xVector == 0) {
            if (yVector > 0) {
                this.targetDirection = 90;
            } else {
                this.targetDirection = 270;
            }
        } else {

            this.targetDirection = Math.toDegrees(Math.atan(yVector / xVector));
        }
    }

    static double x = 1;
    static double y = 1;

    public static void main(String[] args){
        QLearning q = new QLearning();
        String mapD = System.getProperty("user.dir")+System.getProperty("file.separator")+"src"+System.getProperty("file.separator")+"GameControllerSample"+System.getProperty("file.separator")+"testmap.txt";
        Scenario scenario = new Scenario(mapD);
        //System.out.println(scenario.spawnAreaIntruders.getLeftBoundary());
        q.setCurrentLocation(scenario.spawnAreaIntruders.getLeftBoundary() + (scenario.spawnAreaIntruders.getRightBoundary()-scenario.spawnAreaIntruders.getLeftBoundary())/2,
                scenario.spawnAreaIntruders.getTopBoundary() + (scenario.spawnAreaIntruders.getBottomBoundary()-scenario.spawnAreaIntruders.getTopBoundary())/2);


        Runnable run = new Runnable() {
            public void run() {
                ArrayList<Move> moves = ActionsManager.radiusMoves(q.getCurrentXLocation(),q.getCurrentYLocation());
                Move m =  q.Qlearning(moves);
                double[] teleportCheck =  checkTeleport(q);
                if(teleportCheck[0] != -1){
                    moveExplorer(teleportCheck[1],teleportCheck[2],q);
                    System.out.println("teleported");
                }else{
                    moveExplorer(m.x,m.y,q);
                }
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(run, 0, 5, TimeUnit.MILLISECONDS);

    }

    public static void moveExplorer(double x, double y,QLearning q){
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
        q.setCurrentLocation(x,y);
        System.out.println("agent moved " + x + " " + y);
    }

    public static double[] checkTeleport(QLearning q){
        System.out.println("checking portals");
        String mapD = System.getProperty("user.dir")+System.getProperty("file.separator")+"src"+System.getProperty("file.separator")+"GameControllerSample"+System.getProperty("file.separator")+"testmap.txt";
        Scenario scenario = new Scenario(mapD);

        double x = q.getCurrentLocation().getX();
        double y = q.getCurrentLocation().getY();

        ArrayList<TelePortal> portals = scenario.getTeleportals();

        //System.out.println(scenario.getWalls().size());
        for(TelePortal p: portals){
            Rectangle2D portalRect = new Rectangle2D.Double(p.getLeftBoundary(),p.getBottomBoundary(), p.getRightBoundary() -p.getLeftBoundary() , p.getTopBoundary()-p.getBottomBoundary());
            if(portalRect.contains(x,y)){
                System.out.println("in portal");
                return new double[]{1,Double.valueOf(p.getNewLocation()[0]),Double.valueOf(p.getNewLocation()[1])};
                //moveExplorer(Double.valueOf(p.getNewLocation()[0]),Double.valueOf(p.getNewLocation()[1]),q);
            }
        }
        return new double[]{-1,-1,-1};
    }
}
