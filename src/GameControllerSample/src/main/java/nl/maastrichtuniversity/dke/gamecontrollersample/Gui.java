package GameControllerSample.src.main.java.nl.maastrichtuniversity.dke.gamecontrollersample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.scene.paint.Color;

public class Gui extends Application{
    protected static Scenario scenario;

    public static void main(String[] args, Scenario s) {
        scenario = s;
        Application.launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Team 4 -  Project 2.2");
        Group root = new Group();

        //Adding the spawn area guard to the GUI
        Rectangle SpawnAreaG = new Rectangle(  );
        SpawnAreaG.setX(scenario.spawnAreaGuards.leftBoundary);
        SpawnAreaG.setY(scenario.spawnAreaGuards.bottomBoundary);
        SpawnAreaG.setWidth(scenario.spawnAreaGuards.rightBoundary - scenario.spawnAreaGuards.leftBoundary);
        SpawnAreaG.setHeight ( scenario.spawnAreaGuards.topBoundary - scenario.spawnAreaGuards.bottomBoundary );
        SpawnAreaG.setFill (Color.LIGHTBLUE );
        SpawnAreaG.setStroke ( Color.BLACK );

        Rectangle SpawnAreaI = new Rectangle(  );
        SpawnAreaI.setX(scenario.spawnAreaIntruders.leftBoundary);
        SpawnAreaI.setY(scenario.spawnAreaIntruders.bottomBoundary);
        SpawnAreaI.setWidth(scenario.spawnAreaIntruders.rightBoundary - scenario.spawnAreaIntruders.leftBoundary);
        SpawnAreaI.setHeight ( scenario.spawnAreaIntruders.topBoundary - scenario.spawnAreaIntruders.bottomBoundary );
        SpawnAreaI.setFill ( Color.LIGHTGREEN);
        SpawnAreaI.setStroke ( Color.TRANSPARENT );

        Rectangle TargetArea = new Rectangle(  );
        TargetArea.setX(scenario.targetArea.leftBoundary);
        TargetArea.setY(scenario.targetArea.bottomBoundary);
        TargetArea.setWidth(scenario.targetArea.rightBoundary - scenario.targetArea.leftBoundary);
        TargetArea.setHeight ( scenario.targetArea.topBoundary - scenario.targetArea.bottomBoundary );
        TargetArea.setFill ( Color.GREEN);
        TargetArea.setStroke ( Color.TRANSPARENT );

        //Adding the walls to the GUI
        ArrayList<Area> walls = scenario.getWalls ();
        Group allWalls = new Group();
        for (int i=0; i<walls.size () ; i++) {
            System.out.println ( walls.get ( i ).bottomBoundary );
            Rectangle rectangle = new Rectangle ();
            rectangle.setX(walls.get ( i ).leftBoundary);
            rectangle.setY(walls.get ( i ).bottomBoundary);
            rectangle.setWidth(walls.get ( i ).rightBoundary - walls.get ( i ).leftBoundary);
            rectangle.setHeight(walls.get ( i ).topBoundary - walls.get ( i ).bottomBoundary);
            rectangle.setArcHeight ( 5 );
            rectangle.setFill ( Color.BLUE );
            rectangle.setStroke ( Color.BLACK );
            allWalls.getChildren ().add ( rectangle);
        }

        //Adding the ShadedAreas to the GUI
        ArrayList<Area> shades = scenario.getShaded();
        Group allShaded = new Group();
        for (int i=0; i<shades.size () ; i++) {
            System.out.println ( shades.get ( i ).bottomBoundary );
            Rectangle rectangle = new Rectangle ();
            rectangle.setX(shades.get ( i ).leftBoundary);
            rectangle.setY(shades.get ( i ).bottomBoundary);
            rectangle.setWidth(shades.get ( i ).rightBoundary - shades.get ( i ).leftBoundary);
            rectangle.setHeight(shades.get ( i ).topBoundary - shades.get ( i ).bottomBoundary);
            rectangle.setArcHeight ( 5 );
            rectangle.setFill ( Color.LIGHTGREY );
            rectangle.setStroke ( Color.TRANSPARENT );
            allShaded.getChildren ().add(rectangle);
        }

        //Adding the TeleportAreas to the GUI
        ArrayList<TelePortal> teleport = scenario.getTeleportals();
        Group allTelePortals = new Group();
        for (int i=0; i<teleport.size () ; i++) {
            System.out.println ( teleport.get ( i ).bottomBoundary );
            Rectangle rectangle = new Rectangle ();
            rectangle.setX(teleport.get ( i ).leftBoundary);
            rectangle.setY(teleport.get ( i ).bottomBoundary);
            rectangle.setWidth(teleport.get ( i ).rightBoundary - teleport.get ( i ).leftBoundary);
            rectangle.setHeight(teleport.get ( i ).topBoundary - teleport.get ( i ).bottomBoundary);
            rectangle.setArcHeight ( 5 );
            rectangle.setFill ( Color.LIGHTPINK );
            rectangle.setStroke ( Color.TRANSPARENT );
            allTelePortals.getChildren().add(rectangle);
        }



        root.getChildren().addAll(allShaded,allTelePortals,SpawnAreaG,SpawnAreaI,TargetArea,allWalls);

        Scene scene = new Scene(root, scenario.mapWidth, scenario.mapHeight);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
