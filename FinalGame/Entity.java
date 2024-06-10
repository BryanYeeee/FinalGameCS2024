import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Entity here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Entity extends Actor
{
    public void fade(){
        getImage().setTransparency(130);
    }
    public void act()
    {
        // Add your action code here.
    }
}
