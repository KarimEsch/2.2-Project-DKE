package Explorer;
import java.util.Random;
import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.StrictMath.atan;

public class QLearning extends Agent{
    final static double threshold = 0.99;
    final static int targetDirection = 145;


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


    public double evaluateMove(Move m, int targetDirection) {
        double absoluteError;
        double movingDirection;
        double xVector = m.getX() - this.getCurrentXLocation();
        double yVector = m.getY() - super.getCurrentXLocation();
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
            System.out.println(yVector);
            System.out.println(xVector);
            movingDirection = Math.toDegrees(Math.atan(yVector / xVector));
            System.out.println(atan(yVector / xVector));
            System.out.println(Math.toDegrees(Math.atan(yVector / xVector)));

            System.out.println("Degree of movement: " + movingDirection);
        }
        absoluteError = abs(targetDirection - movingDirection);

        return absoluteError;

    }

    public void updateDirection(){
        //to be created
    }

    public static void main(String[] args){
        QLearning q = new QLearning();
        q.setCurrentLocation(0,0);
        Move m = new Move();
        m.x = 1;
        m.y = 1;
        q.executeMove(m);
        //System.out.println(QLearning.evaluateMove(m,QLearning.targetDirection);
        Move n = new Move();
        n.x = 2;
        n.y = 2;

        ArrayList<Move> am = new ArrayList<Move>();
        am.add(n);
        am.add(m);

        System.out.println(q.Qlearning(am));
    }

}
