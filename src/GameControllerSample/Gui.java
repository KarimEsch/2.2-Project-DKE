package GameControllerSample;

import Explorer.Agent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.transform.Scale;

public class Gui extends Application{
    protected static Scenario scenario;

    public static void main(String[] args, Scenario s) {
        scenario = s;
        Application.launch(args);
    }

    private static Stage primaryStage;

    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        //explorer.setCenterX();
                        explorer.setCenterX(explorerCoords()[0]);
                        explorer.setCenterY(explorerCoords()[1]);
                    }
                };
                while (true) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                    }
                    // UI update is run on the Application thread
                    //System.out.println("runlater");
                    Platform.runLater(updater);
                }
            }

        });

        thread.setDaemon(true);
        thread.start();
        drawMap();
        //updateGUI();
    }

    private static Circle explorer;

    public static void drawMap(){
        System.out.println("drawing map");
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

        //adding the explorer agent
        explorer = new Circle(Agent.AGENT_SIZE,Color.RED);
        double[] exChoords = explorerCoords();
        explorer.setCenterX(exChoords[0]);
        explorer.setCenterY(exChoords[1]);
        explorer.setFill(Color.RED);

        root.getChildren().addAll(allShaded,allTelePortals,SpawnAreaG,SpawnAreaI,TargetArea,allWalls,explorer);

        //Creating the scale transformation
        Scale scale = new Scale();

        //Setting the dimensions for the transformation
        scale.setX(3);
        scale.setY(3);

        root.getTransforms().addAll(scale);
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static double[] explorerCoords(){
        double[] explorerPositions = new double[2];
        Charset ENCODING = StandardCharsets.UTF_8;
        Path filePath = Paths.get(scenario.getGameFile());
        try (Scanner scanner =  new Scanner(filePath, ENCODING.name())){
            while (scanner.hasNextLine()){
                //System.out.println(scanner.next());
                String id = scanner.next();
                if(id.equals("explorer")){
                    scanner.next();

                    explorerPositions = new double[4];
                    explorerPositions[0] = Double.parseDouble(scanner.next());
                    explorerPositions[1] = Double.parseDouble(scanner.next());
                    explorerPositions[2] = Double.parseDouble(scanner.next());
                    explorerPositions[3] = Double.parseDouble(scanner.next());
                }

            }
        }
        catch(Exception e) {
            //System.out.println("fail");
        }
        return explorerPositions;
    }


}
