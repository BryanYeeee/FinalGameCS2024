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
    
    public ImgScroll scroller;
    //private Scroller scroller;
    
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
        scroller = new ImgScroll(this, new GreenfootImage("TestMap.jpg"), 1700, 1500);
        speed = 2;
    }

    
    public void act(){
        actCount++;
        /**
         * Randomly spawn xp for now
         */
        if(actCount%5==0){
            addObject(new XP(Greenfoot.getRandomNumber(1700), Greenfoot.getRandomNumber(1500)), Greenfoot.getRandomNumber(1700),Greenfoot.getRandomNumber(1500));
        }
        
        scroller.scroll(getWidth()/2 - c.getX(), getHeight()/2 - c.getY());
    }
    
    public Character getCharacter(){
        return c;
    }
    
    public ImgScroll getScroller() {
        return scroller;
    }
}
