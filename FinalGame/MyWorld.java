import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    private Character c;
    private Gun g;
    private int speed;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        c = new Character();
        g = new Gun();
        addObject(c, 300, 200);
        addObject(g, 280, 180);
        speed = 2;
    }
    
    public void act(){
    }
    
    public Character getCharacter(){
        return c;
    }
}
