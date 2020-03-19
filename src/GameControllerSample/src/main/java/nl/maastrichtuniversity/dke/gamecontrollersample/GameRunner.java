/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameControllerSample.src.main.java.nl.maastrichtuniversity.dke.gamecontrollersample;
import java.io.FileWriter;
import java.io.PrintWriter;

public class GameRunner {

    protected String mapDoc;
    protected static Scenario scenario;
    public static String Map = "testmap.txt";
    
    GamePlayer p;   
    
    public static void main(String[] args){
        // the mapscenario should be passed as a parameter
        String mapD = System.getProperty("user.dir")+System.getProperty("file.separator")+"src"+System.getProperty("file.separator")+"GameControllerSample"+System.getProperty("file.separator")+Map;
        GameRunner game = new GameRunner(mapD);
        game.p.setup();
        Gui.main(args,scenario);
        //game.writeGameFile();
        game.p.start();
    }

    public GameRunner(String scn){
        mapDoc=scn;
        scenario = new Scenario(mapDoc);
        p = new GamePlayer(scenario);
    }
}
