package GameControllerSample.src.main.java.nl.maastrichtuniversity.dke.gamecontrollersample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Gui extends Application{
    protected static Scenario scenario;

    public static void main(String[] args, Scenario s) {
        scenario = s;
        Application.launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Team 4 -  Project 2.2");
        Group root = new Group();
        Scene scene = new Scene(root, scenario.mapWidth, scenario.mapHeight);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
