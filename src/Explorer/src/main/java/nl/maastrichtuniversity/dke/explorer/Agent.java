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
    private static ArrayList<Agent> allAgents;
    private int ID;
    private static int lastID = 0;
    private int currentXLocation;
    private int currentYLocation;
    private Point2D currentLocation;
    private boolean goalReached = false;
    public static final int AGENT_SIZE = 5;
    private ArrayList<Move> possibleMoves;

    public Agent(){
        this.ID = lastID + 1;
        lastID++;

    }

    public Agent(int currentXLocation, int currentYLocation){
        this.currentXLocation = currentXLocation;
        this.currentYLocation = currentYLocation;
        this.currentLocation.setLocation(currentXLocation,currentYLocation);
        this.ID = lastID + 1;
        lastID ++;
    }

    public void setCurrentLocation(int currentXLocation, int currentYLocation){
        this.currentXLocation = currentXLocation;
        this.currentYLocation = currentYLocation;
        this.currentLocation.setLocation(currentXLocation,currentYLocation);
    }

    public void setCurrentLocation(Point2D location){
        this.currentXLocation = (int) location.getX();
        this.currentYLocation = (int) location.getY();
        this.currentLocation.setLocation(location);
    }
    //moves agent to the specified location
    public void executeMove(Move m){
        this.currentXLocation = m.getX();
        this.currentYLocation = m.getY();
        this.currentLocation.setLocation(m.getX(), m.getY());
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

    public Point2D getCurrentLocation() {
        return currentLocation;
    }

    public int getID(){
        return this.ID;
    }

    public boolean getCurrentStatus(){
        return goalReached;
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

    public ArrayList<Move> getPossibleMoves() {
        return possibleMoves;
    }
}
