import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World {
    public static final int WORLD_WIDTH = 1024;//8
    public static final int WORLD_HEIGHT = 768;//6
    private Character c;
    private Gun g;
    private int speed;
    private int actCount = 0;
    
    private Map map;
    private Viewport vp;

    /**
     * Constructor for objects of class MyWorld.
     */
    public MyWorld() {
        // Call the superclass constructor with the constants
        super(WORLD_WIDTH, WORLD_HEIGHT, 1, false);
        setPaintOrder(Character.class,Gun.class,Bullet.class,EntitySprite.class,Collectible.class,Label.class,Tile.class);
        c = new Character();
        g = new Gun();
        addObject(c, WORLD_WIDTH/2, WORLD_HEIGHT/2);
        addObject(g, c.getX()-100, c.getY()-100);
        speed = 2;
        
        map = new Map();
        vp = new Viewport(this);
        // addObject(new Tile("",false,10),-55,675);
        // addObject(new Tile("",false,10),0,500);
    }
    
    public void updateVP(int xMove, int yMove) {
        vp.move(xMove, yMove);
    }

    
    public void act(){
        actCount++;
        /**
         * Randomly spawn xp for now
         */
        if(actCount%5==0 && actCount<600){
            addObject(new XP(),Greenfoot.getRandomNumber(WORLD_WIDTH),Greenfoot.getRandomNumber(WORLD_HEIGHT));
        }
    }
    
    public Character getCharacter(){
        return c;
    }
    
    public Tile[][] getMap() {
        return map.getTileMap();
    }
    
    public void debug() {
        System.out.println("lx: " + vp.lx());
        System.out.println("ly: " + vp.ly());
        System.out.println("rx: " + vp.rx());
        System.out.println("ry: " + vp.ry());
    }
}
