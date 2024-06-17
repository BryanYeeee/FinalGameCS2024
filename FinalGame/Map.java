import java.util.HashMap;
/**
 * The background map of the game.
 * 
 * @author Bryan Y 
 * @version June 2024
 */
public class Map  
{
    public static final HashMap<String, String> tileHash = new HashMap<>(){{
        put("", "floorimageurl.png"); //DOESNT EXIT RN
    }};
    
    private Tile[][] tileMap;
    private MyWorld w;
    /**
     * Constructor of Map, generate the map using "-" for walls, "" for open space.
     */
    public Map()
    {
        generateMap();
    }

    private void generateMap() {
        String[][] stringMap = {{"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
                                {"-","","","","","","","","","","","","","","","","","","","-"},
                                {"-","","","","","","","","","","","","","","","","","","","-"},
                                {"-","","","","","","","","","","","","","","","","","","","-"},
                                {"-","","","","","","","","","","","","","","","","","","","-"},
                                {"-","","","","","","","","","","","","","","","","","","","-"},
                                {"-","","","","","","","","","","","","","","","","","","","-"},
                                {"-","","","","","","","","","","","","","","","","","","","-"},
                                {"-","","","","","","","","","","","","","","","","","","","-"},
                                {"-","","","","","","","","","","","","","","","","","","","-"},
                                {"-","","","","","","","","","","","","","","","","","","","-"},
                                {"-","","","","","","","","","","","","","","","","","","","-"},
                                {"-","","","","","","","","","","","","","","","","","","","-"},
                                {"-","","","","","","","","","","","","","","","","","","","-"},
                                {"-","","","","","","","","","","","","","","","","","","","-"},
                                {"-","","","","","","","","","","","","","","","","","","","-"},
                                {"-","","","","","","","","","","","","","","","","","","","-"},
                                {"-","","","","","","","","","","","","","","","","","","","-"},
                                {"-","","","","","","","","","","","","","","","","","","","-"},
                                {"-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-"}};
        // String[][] stringMap = {{"",""}};
        tileMap = new Tile[stringMap.length][stringMap[0].length];
        int x =0;
        for(int i = 0; i < stringMap.length; i++) {
            for(int j = 0; j < stringMap[0].length; j++) {
                switch(stringMap[i][j]){
                    case "":
                        tileMap[i][j] = new Tile(tileHash.get(stringMap[i][j]),x);
                        break;
                    case "-":
                        tileMap[i][j] = new Wall();
                        break;
                }
                x++;
            }
        }
    }
    /**
     * Get the tiles used for the map.
     * 
     * @return Tile[][] The map tiles.
     */
    public Tile[][] getTileMap() {
        return tileMap;
    }
}
