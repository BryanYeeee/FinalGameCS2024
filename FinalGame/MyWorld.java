import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World {
    public static final int WORLD_WIDTH = 1048;
    public static final int WORLD_HEIGHT = 800;
    private Character c;
    private Gun g;
    private int speed;
    private int actCount = 0;

    /**
     * Constructor for objects of class MyWorld.
     */
    public MyWorld() {
        // Call the superclass constructor with the constants
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);
        setPaintOrder(Character.class,Collectible.class);
        c = new Character();
        g = new Gun();
        addObject(c, 300, 200);
        addObject(g, 280, 180);
        speed = 2;
    }

    
    public void act(){
        actCount++;
        /**
         * Randomly spawn xp for now
         */
        if(actCount%5==0){
            addObject(new XP(),Greenfoot.getRandomNumber(WORLD_WIDTH),Greenfoot.getRandomNumber(WORLD_HEIGHT));
        }
    }
    
    public Character getCharacter(){
        return c;
    }
}
