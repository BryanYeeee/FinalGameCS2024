import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RockTile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RockTile extends Tiles
{
    private GreenfootImage image;
    
    public RockTile() {
        image = new GreenfootImage("RockTile.png");
        image.scale(125, 125);
        setImage(image);
    }
    
    /**
     * Act - do whatever the RockTile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
