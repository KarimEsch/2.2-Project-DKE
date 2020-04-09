package GameControllerSample;

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
        Gui gui = new Gui();
        gui.main(args,scenario);
        game.p.start();
    }

    public GameRunner(String scn){
        mapDoc=scn;
        scenario = new Scenario(mapDoc);
        p = new GamePlayer(scenario);
    }
}
