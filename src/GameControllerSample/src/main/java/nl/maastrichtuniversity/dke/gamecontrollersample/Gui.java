package GameControllerSample.src.main.java.nl.maastrichtuniversity.dke.gamecontrollersample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.ArrayList;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.BLUE;

public class Gui extends Application{
    protected static Scenario scenario;

    public static void main(String[] args, Scenario s) {
        scenario = s;
        Application.launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        //Adding the walls to the GUI
        ArrayList<Area> walls = scenario.getWalls ();
        //Adding the spawn area guard added to the GUI
        Rectangle SpawnAreaG = new Rectangle(  );

        SpawnAreaG.setX(scenario.spawnAreaGuards.leftBoundary);
        SpawnAreaG.setY(scenario.spawnAreaGuards.bottomBoundary);
        SpawnAreaG.setWidth(scenario.spawnAreaGuards.rightBoundary - scenario.spawnAreaGuards.leftBoundary);
        SpawnAreaG.setHeight ( scenario.spawnAreaGuards.topBoundary - scenario.spawnAreaGuards.bottomBoundary );

        Rectangle SpawnAreaI = new Rectangle(  );

        SpawnAreaI.setX(scenario.spawnAreaIntruders.leftBoundary);
        SpawnAreaI.setY(scenario.spawnAreaIntruders.bottomBoundary);
        SpawnAreaI.setWidth(scenario.spawnAreaIntruders.rightBoundary - scenario.spawnAreaIntruders.leftBoundary);
        SpawnAreaI.setHeight ( scenario.spawnAreaGuards.topBoundary - scenario.spawnAreaIntruders.bottomBoundary );

        primaryStage.setTitle("Team 4 -  Project 2.2");
        Group root = new Group();
        Group allWalls = new Group();
        for (int i=0; i<walls.size () ; i++) {
            System.out.println ( walls.get ( i ).bottomBoundary );
            Rectangle rectangle = new Rectangle ();
            rectangle.setX(walls.get ( i ).leftBoundary);
            rectangle.setY(walls.get ( i ).bottomBoundary);
            rectangle.setWidth(walls.get ( i ).rightBoundary - walls.get ( i ).leftBoundary);
            rectangle.setHeight(walls.get ( i ).topBoundary - walls.get ( i ).bottomBoundary);
            rectangle.setArcHeight ( 5 );
            rectangle.setFill ( BLUE );
            rectangle.setStroke ( BLACK );
            allWalls.getChildren ().add ( rectangle);
        }
        root.getChildren ().add(allWalls);
        root.getChildren ().add(SpawnAreaG );
        root.getChildren ().add(SpawnAreaI );
        Scene scene = new Scene(root, scenario.mapWidth, scenario.mapHeight);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
