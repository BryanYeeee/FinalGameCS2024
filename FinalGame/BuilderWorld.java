import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BuilderWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BuilderWorld extends World
{
    private GreenfootImage image;
    
    /**
     * Constructor for objects of class BuilderWorld.
     * 
     */
    public BuilderWorld()
    {    
        super(1200, 850, 1);
        
        image = new GreenfootImage("Grassland.png");
        image.scale(1200, 850);
        setBackground(image);
    }
}
