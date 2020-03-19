package GameControllerSample.src.main.java.nl.maastrichtuniversity.dke.gamecontrollersample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Gui extends Application{

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Team 4 -  Project 2.2");
        Group root = new Group();
        Scene scene = new Scene(root, Scenario, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
