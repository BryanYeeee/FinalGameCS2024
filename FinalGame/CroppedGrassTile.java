import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CroppedGrassTile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CroppedGrassTile extends Tiles
{
    private GreenfootImage image;
    
    public CroppedGrassTile() {
        image = new GreenfootImage("CroppedGrassTile.png");
        image.scale(125, 125);
        setImage(image);
    }
    
    /**
     * Act - do whatever the CroppedGrassTile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
