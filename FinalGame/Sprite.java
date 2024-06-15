import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashMap;
import java.util.Map;

/**
 * The sprite class is in charge of initializing the player sprite.
 * 
 * @author Jeff G
 * @version April 2024
 */
public class Sprite  
{

    private static String entityName = "player";
    private static String action = "walk";
    private static char dirChar = 'L';
    private static int imageIndex = 0;
    private static Map<String, GreenfootImage> frames = new HashMap<>();

    /**
     * Constructor of Sprite.
     */
    public Sprite()
    {
    }

    /**
     * Determine
     */
    public static void init(){
        entityName = "player";
        for(int dirIndex = 0; dirIndex < 2; dirIndex++){ 
            if(dirIndex == 0){
                dirChar = 'L';
            }
            else if(dirIndex == 1){
                dirChar = 'R';
            }
            for(int imageIndex = 0; imageIndex < 8; imageIndex++){
                action="run";
                String key = entityName + "_" + action + "_" + dirChar + "_" + imageIndex;
                //System.out.println(key);
                frames.put(key, new GreenfootImage("images/" + entityName +  "/" + action + "/" + dirChar + imageIndex + ".png"));
            }
        }
        entityName = "basicEnemy";
        for(int dirIndex = 0; dirIndex < 2; dirIndex++){ 
            if(dirIndex == 0){
                dirChar = 'L';
            }
            else if(dirIndex == 1){
                dirChar = 'R';
            }
            for(int imageIndex = 0; imageIndex < 4; imageIndex++){
                action="run";
                String key = entityName + "_" + action + "_" + dirChar + "_" + imageIndex;
                //System.out.println(key);
                frames.put(key, new GreenfootImage("images/" + entityName +  "/" + action + "/" + dirChar + imageIndex + ".png"));
            }
            for(int imageIndex = 0; imageIndex < 1; imageIndex++){
                action="hit";
                String key = entityName + "_" + action + "_" + dirChar + "_" + imageIndex;
                //System.out.println(key);
                frames.put(key, new GreenfootImage("images/" + entityName +  "/" + action + "/" + dirChar + imageIndex + ".png"));
            }
            for(int imageIndex = 0; imageIndex < 8; imageIndex++){
                action="death";
                String key = entityName + "_" + action + "_" + dirChar + "_" + imageIndex;
                //System.out.println(key);
                frames.put(key, new GreenfootImage("images/" + entityName +  "/" + action + "/" + dirChar + imageIndex + ".png"));
            }
        }
    }

    /**
     * 
     */
    public static GreenfootImage getFrame(String key){
        if (frames.containsKey(key)) {
            return frames.get(key);
        } else {
            System.out.println("Key not found in the map: "+ key);
            return frames.get("inmate_black_idle_D_0");
        }
    }

}
