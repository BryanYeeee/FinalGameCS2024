import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GrassTile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GrassTile extends Tiles
{
    private GreenfootImage image;
    
    public GrassTile() {
        image = new GreenfootImage("GrassTile.png");
        image.scale(125, 125);
        setImage(image);
    }
    
    /**
     * Act - do whatever the GrassTile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        
    }
}
