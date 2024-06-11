import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Entity here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Entity extends SuperSmoothMover
{
    public void setTransparency(int amount){
        getImage().setTransparency(amount);
    }
    public void act()
    {
        // Add your action code here.
    }
}
