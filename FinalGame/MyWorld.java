import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
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
    private int randX;
    private int randY;
    private int hordeCount;
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
        addObject(g, 330, 200);
        for(int i = 0; i < 10; i++){
            addHorde();
        }
        speed = 2;
    }
    
    public void act(){
        ArrayList<BasicHorde> horde = (ArrayList<BasicHorde>)getObjects(BasicHorde.class);
        if(horde.size() < 10){
            addHorde();
        }
    }
    
    public Character getCharacter(){
        return c;
    }
    
    private void addHorde(){
        if(Greenfoot.getRandomNumber(2) == 0){
            randX = 0;
            randY = Greenfoot.getRandomNumber(350) + 25; // based on world height, don't spawn in corners
            addObject(new BasicHorde(), randX, randY);
        } else {
            randX = Greenfoot.getRandomNumber(750) + 25;
            randY = 0;
            addObject(new BasicHorde(), randX, randY);
        }
    }
}
