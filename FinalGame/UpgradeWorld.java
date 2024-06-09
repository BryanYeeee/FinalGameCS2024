import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PauseWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UpgradeWorld extends AllWorld
{

    /**
     * Constructor for objects of class PauseWorld.
     * 
     */
    MyWorld mainWorld;
     
    public UpgradeWorld(MyWorld world)
    {
        super(AllWorld.WORLD_WIDTH, AllWorld.WORLD_HEIGHT, 1);
        mainWorld = world;
        mainWorld.getCharacter().increaseLevel();
    }
     
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) Greenfoot.setWorld(mainWorld);
    }
}
