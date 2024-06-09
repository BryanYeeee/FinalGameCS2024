import java.util.HashMap;
/**
 * Write a description of class Map here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Map  
{
    public static final HashMap<String, String> tileHash = new HashMap<>(){{
        put("", "floorimageurl.png"); //DOESNT EXIT RN
    }};
    
    private Tile[][] tileMap;
    private MyWorld w;
    
    public Map()
    {
        generateMap();
    }

    private void generateMap() {
        String[][] stringMap = {{"","","","","","","","","",""},
                                {"","","","","","","","","",""},
                                {"","","","","","","","","",""},
                                {"","","","","","","","","",""},
                                {"","","","","","","","","",""},
                                {"","","","","","","","","",""},
                                {"","","","","","","","","",""},
                                {"","","","","","","","","",""},
                                {"","","","","","","","","",""},
                                {"","","","","","","","","",""}};
        tileMap = new Tile[stringMap.length][stringMap[0].length];
        for(int i = 0; i < stringMap.length; i++) {
            for(int j = 0; j < stringMap[0].length; j++) {
                tileMap[i][j] = new Tile(tileHash.get(stringMap[i][j]), false);
            }
        }
    }
    
    public Tile[][] getTileMap() {
        return tileMap;
    }
}
