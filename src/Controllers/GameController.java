package Controllers;

import Explorer.src.main.java.nl.maastrichtuniversity.dke.explorer.*;
import java.awt.geom.Point2D;
import javafx.stage.Stage;
import javax.swing.text.Position;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math.*;

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


    public void updateGameState(){

    }



//    public boolean isLegalMove(Action move){
//
//    }

//note to self: passing arraylists to methods means passing reference to arraylist, i.e. any changes made to 'method' arraylist affects original arraylist as well!!!!!


    public static void spawn(Explorer.src.main.java.nl.maastrichtuniversity.dke.explorer.Area){

    }
    // agents position = centre of circle, wall = rectangles. checks if any point on the edge of rectangles lie within the circle or an agent
    //
    public static boolean checkCollisions(Agent agent, ArrayList<Area> areas) {
        double circCentreX = agent.getCurrentXLocation();
        double circCentreY = agent.getCurrentYLocation();
        boolean collision = false;
        for (Area area : areas) {
            Rectangle2D bounds = area.getBounds2D();
            double circDistX = Math.abs(circCentreX - bounds.getCenterX());
            double circDistY = Math.abs(circCentreY - bounds.getCenterY());

            if (circDistX > (bounds.getWidth()/2 + agent.getAgentSize())) { collision = false; }
            if (circDistY > (bounds.getHeight()/2 + agent.getAgentSize())) { collision = false; }

            if (circDistX <= (bounds.getWidth()/2 )) {
                collision = true;
                break;
            }
            if (circDistY <= (bounds.getHeight()/2 )) {
                collision = true;
                break;
            }

            double cornerDistance_sq = ((circDistX - bounds.getWidth()/2)*(circDistX - bounds.getWidth()/2)) + ((circDistY - bounds.getHeight()/2)*(circDistY - bounds.getHeight()/2));

            collision = (cornerDistance_sq <= (agent.getAgentSize()*agent.getAgentSize()));

        }return collision;
    }


    public static boolean checkCollision(Agent agent, Area area) {
        double circCentreX = agent.getCurrentXLocation();
        double circCentreY = agent.getCurrentYLocation();
        Rectangle2D bounds = area.getBounds2D();
        double circDistX = Math.abs(circCentreX - bounds.getCenterX());
        double circDistY = Math.abs(circCentreY - bounds.getCenterY());

        if (circDistX > (bounds.getWidth()/2 + agent.getAgentSize())) { return false; }
        if (circDistY > (bounds.getHeight()/2 + agent.getAgentSize())) { return false; }

        if (circDistX <= (bounds.getWidth()/2 )) { return true; }
        if (circDistY <= (bounds.getHeight()/2 )) { return true; }

        double cornerDistance_sq = ((circDistX - bounds.getWidth()/2)*(circDistX - bounds.getWidth()/2)) + ((circDistY - bounds.getHeight()/2)*(circDistY - bounds.getHeight()/2));
        return (cornerDistance_sq <= (agent.getAgentSize()*agent.getAgentSize()));
    }

    /*  TODO check collision between all teleports or make sure 2 teleports are never near enough that both may be used at the same time
        TODO think about teleport implementation:
        TODO 1) can only teleport when in range of teleport, only knowledge of possibility WHEN in range, not where it teleports to
        TODO 2) same as 1 but with knowledge of final destination
    */
//    public boolean canTeleport(Position initPosition, Position finalPosition, Agent a){ return checkCollision(a, teleportals); }
}

