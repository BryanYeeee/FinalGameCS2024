import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends AllWorld
{
    private Character c;
    private Gun g;
    private int speed;
    private int randX;
    private int randY;
    private int hordeCount;
    private int hordeLimit; // the limit of how many horde enemys can exist at once
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(AllWorld.WORLD_WIDTH, AllWorld.WORLD_HEIGHT, 1);
        c = new Character();
        g = new Gun();
        addObject(c, AllWorld.WORLD_WIDTH/2, AllWorld.WORLD_HEIGHT/2);
        addObject(g, AllWorld.WORLD_WIDTH/2+30, AllWorld.WORLD_HEIGHT/2);
        hordeLimit = 20;
        speed = 2;
    }
    
    public void act(){
        ArrayList<BasicHorde> horde = (ArrayList<BasicHorde>)getObjects(BasicHorde.class);
        if(horde.size() < hordeLimit){
            addHorde();
        }
        determineLevel();
    }
    
    public Character getCharacter(){
        return c;
    }
    
    private void determineLevel(){
        // if you want faster testing of the upgrade world, change first req. of the if statment to something lower
        if(c.getXP() == 1 && c.getLevel() == 0){
            Greenfoot.setWorld(new UpgradeWorld(this, c.getLevel(), c));
        }
        if(c.getXP() == 25 && c.getLevel() == 1){
            Greenfoot.setWorld(new UpgradeWorld(this, c.getLevel(), c));
        }
    }
    
    private void addHorde(){
        if(Greenfoot.getRandomNumber(2) == 0){
            if(Greenfoot.getRandomNumber(2) == 0){
                randX = 0;
            } else {
                randX = AllWorld.WORLD_WIDTH;
            }
            randY = Greenfoot.getRandomNumber(AllWorld.WORLD_HEIGHT-50) + 25; 
            addObject(new BasicHorde(), randX, randY);
        } else {
            randX = Greenfoot.getRandomNumber(AllWorld.WORLD_WIDTH-50) + 25;
            if(Greenfoot.getRandomNumber(2) == 0){
                randY = 0;
            } else {
                randY = AllWorld.WORLD_HEIGHT;
            }
            addObject(new BasicHorde(), randX, randY);
        }
    }
}
