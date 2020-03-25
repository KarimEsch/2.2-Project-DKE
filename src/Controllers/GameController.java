package Controllers;

import Explorer.Agent;
import Explorer.Area;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class GameController {
//    private BoardController boardController;
//    private Game gameModel;
//    private static int successes = 0;
//
//    public GameController(Stage primaryStage) {
//        gameModel = new Game();
//        boardController = new BoardController();
//    }
//
//    public void Spawn(){
//
//    }




    /*public void updateGameState(ArrayList<Agent> agents,ArrayList<Area> walls){
        for(Agent agent : agents){
            boolean moved = false;
            Point2D temp = agent.getCurrentLocation();
            ArrayList<Move> allMoves = agent.getPossibleMoves();
            while (!moved) {
                Move bestMove = QLearning.Qlearning(allMoves);//don't know why this error exists. Maybe because they're in the same package but named differently?
                agent.executeMove(bestMove);
                for (Area wall : walls) {
                    if (checkCollision(agent, wall)) {
                        moved = false;
                        allMoves.remove(bestMove);
                        agent.setCurrentLocation(temp);
                        break;
                    }else{
                        moved = true;
                    }
                }
            }
        }
    }

     */




//    public boolean isLegalMove(Action move){
//
//    }

//note to self: passing arraylists to methods means passing reference to arraylist, i.e. any changes made to 'method' arraylist affects original arraylist as well!!!!!

//
//    public static void spawn(Explorer.src.main.java.nl.maastrichtuniversity.dke.explorer.Area){
//
//    }

    /**agents position = centre of circle, wall = rectangles. checks if any point on the edge of rectangles lie within the circle or an agent
     *
     * @param agent
     * @param areas arrayList of all the areas one wishes to check for collision with an agent
     * @return
     */

    public static boolean checkCollisions(double x, double y, ArrayList<Area> areas) {
        double circCentreX = x;
        double circCentreY = y;
        boolean collision = false;
        for (Area area : areas) {
            Rectangle2D bounds = area.getBounds2D();
            double circDistX = Math.abs(circCentreX - bounds.getCenterX());
            double circDistY = Math.abs(circCentreY - bounds.getCenterY());

            if (circDistX > (bounds.getWidth()/2 + Agent.AGENT_SIZE)) { collision = false; }
            if (circDistY > (bounds.getHeight()/2 + Agent.AGENT_SIZE)) { collision = false; }

            if (circDistX <= (bounds.getWidth()/2 )) {
                collision = true;
                break;
            }
            if (circDistY <= (bounds.getHeight()/2 )) {
                collision = true;
                break;
            }

            double cornerDistance_sq = ((circDistX - bounds.getWidth()/2)*(circDistX - bounds.getWidth()/2)) + ((circDistY - bounds.getHeight()/2)*(circDistY - bounds.getHeight()/2));

            collision = (cornerDistance_sq <= (Agent.AGENT_SIZE*Agent.AGENT_SIZE));

        }return collision;
    }


    public static boolean checkCollision(double x, double y, Area area) {
        double circCentreX = x;
        double circCentreY = y;
        Rectangle2D bounds = area.getBounds2D();
        double circDistX = Math.abs(circCentreX - bounds.getCenterX());
        double circDistY = Math.abs(circCentreY - bounds.getCenterY());

        if (circDistX > (bounds.getWidth()/2 + Agent.AGENT_SIZE)) { return false; }
        if (circDistY > (bounds.getHeight()/2 + Agent.AGENT_SIZE)) { return false; }

        if (circDistX <= (bounds.getWidth()/2 )) { return true; }
        if (circDistY <= (bounds.getHeight()/2 )) { return true; }

        double cornerDistance_sq = ((circDistX - bounds.getWidth()/2)*(circDistX - bounds.getWidth()/2)) + ((circDistY - bounds.getHeight()/2)*(circDistY - bounds.getHeight()/2));
        return (cornerDistance_sq <= (Agent.AGENT_SIZE*Agent.AGENT_SIZE));
    }

    /*  TODO check collision between all teleports or make sure 2 teleports are never near enough that both may be used at the same time
        TODO think about teleport implementation:
        TODO 1) can only teleport when in range of teleport, only knowledge of possibility WHEN in range, not where it teleports to
        TODO 2) same as 1 but with knowledge of final destination
    */
//    public boolean canTeleport(Position initPosition, Position finalPosition, Agent a){ return checkCollision(a, teleportals); }
}
