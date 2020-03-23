package Explorer.src.main.java.nl.maastrichtuniversity.dke.explorer;


import java.awt.geom.Point2D;
import javafx.stage.Stage;
import javax.swing.text.Position;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math.*;
import java.util.ArrayList;

public class Agent {
    private int ID;
    private static int lastID = 0;
    private int currentXLocation;
    private int currentYLocation;
    private boolean goalReached = false;
    private static final int AGENT_SIZE = 5;
    private ArrayList<Move> possibleMoves;

    public Agent(){
        this.ID = lastID + 1;
        lastID++;

    }

    public Agent(int currentXLocation, int currentYLocation){
        this.currentXLocation = currentXLocation;
        this.currentYLocation = currentYLocation;
        this.ID = lastID + 1;
        lastID ++;
    }

    public void setCurrentLocation(int currentXLocation, int currentYLocation){
        this.currentXLocation = currentXLocation;
        this.currentYLocation = currentYLocation;
    }

    //moves agent to the specified location
    public void executeMove(Move m){
        this.currentXLocation = m.getX();
        this.currentYLocation = m.getY();
    }

    //call this method when the exploration agent got to its target. Should stop moving after goalReached is true
    public void targetReached(){
        this.goalReached = true;
    }

    public int getCurrentXLocation(){
        return this.currentXLocation;
    }

    public int getCurrentYLocation(){
        return this.currentYLocation;
    }

    public int getID(){
        return this.ID;
    }

    public boolean getCurrentStatus(){
        return goalReached;
    }

    public ArrayList<Move> getPossibleMoves() {
        return possibleMoves;
    }

    public static final double getAgentSize(){
        return AGENT_SIZE;
    }

    public void setGoalReached(boolean goalReached) {
        this.goalReached = goalReached;
    }

    public static int getLastID() {
        return lastID;
    }

}
