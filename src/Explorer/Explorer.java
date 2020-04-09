package Explorer;

/**
 *
 * @author joel
 */
public class Explorer {
    
    protected String mapDoc;
    protected Scenario scenario;
    public static String Map = "testmap.txt";
    
    ExGamePlayer p;   
    
    public static void main(String[] args){
        // the mapscenario should be passed as a parameter
        //String mapD="\\Users\\Matt\\Documents\\p2.2map.txt";
        String mapD = System.getProperty("user.dir")+System.getProperty("file.separator")+"src"+System.getProperty("file.separator")+"GameControllerSample"+System.getProperty("file.separator")+Map;
        Explorer game = new Explorer(mapD);
        //game.writeGameFile();
        game.p.start();
    }

    public Explorer(String scn){
        mapDoc=scn;
        scenario = new Scenario(mapDoc);
        p = new ExGamePlayer(scenario);
    }
}
