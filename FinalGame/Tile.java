import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tile extends SuperSmoothMover
{
    public static final int TILE_LENGTH = 128;
    
    boolean passable;
    
    public Tile(String imgURL, boolean passable) {
        this.passable = passable;
        
        getImage().scale(TILE_LENGTH,TILE_LENGTH);
        // GreenfootImage image = new GreenfootImage(imgURL);
        // image.scale(TILE_WIDTH, TILE_HEIGHT);
        // setImage(image);
    }
}
