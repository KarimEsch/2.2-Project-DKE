package Controllers;

import Controllers.BoardController;
import javafx.stage.Stage;

import javax.swing.text.Position;

public class GameController {
    private BoardController boardController;
    private Game gameModel;
    private int originPosition;

    public GameController(Stage primaryStage) {
        gameModel = new Game();
        boardController = new BoardController();
        tick();
    }
    public void Spaw(){

    }
    public void Teleport(Position initPosition, Position finalPosition){

    }
    public String Texture (String text)
}
