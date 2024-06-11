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
    private int actCount = 0;
    
    private Map map;
    private Viewport vp;

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Call the superclass constructor with the constants
        super(AllWorld.WORLD_WIDTH, AllWorld.WORLD_HEIGHT, 1,false);
        SimulationFont.initalizeFont("BigSpace.ttf");
        setPaintOrder(SuperTextBox.class,TempBox.class,EntitySprite.class,Character.class,Enemy.class,Gun.class,Bullet.class,Collectible.class,Label.class,Tile.class);
        c = new Character();
        g = new Gun();
        addObject(c, AllWorld.WORLD_WIDTH/2, AllWorld.WORLD_HEIGHT/2);
        addObject(g, AllWorld.WORLD_WIDTH/2+30, AllWorld.WORLD_HEIGHT/2);
        hordeLimit = 20;
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
        ArrayList<BasicHorde> horde = (ArrayList<BasicHorde>)getObjects(BasicHorde.class);
        if(horde.size() < hordeLimit){
            addHorde();
        }
        if(c.getHP() <= 0){
            removeObject(c);
            addObject(new TempBox(AllWorld.WORLD_WIDTH, AllWorld.WORLD_HEIGHT, bgColor), AllWorld.WORLD_WIDTH/2, AllWorld.WORLD_HEIGHT/2);
            addObject(new SuperTextBox("GAME OVER", bgColor, Color.BLACK, SimulationFont.loadCustomFont("BigSpace.ttf", 150), true, 750, 0, Color.BLACK), AllWorld.WORLD_WIDTH/2, AllWorld.WORLD_HEIGHT/2);
        }
        determineLevel();
    }
    
    public Character getCharacter(){
        return c;
    }
    
    private void determineLevel(){
        // if you want faster testing of the upgrade world, change first req. of the if statment to something lower
        if(c.getXP() == 10 && c.getLevel() == 0){
            Greenfoot.setWorld(new UpgradeWorld(this, c.getLevel(), c));
        }
        if(c.getXP() == 25 && c.getLevel() == 1){
            Greenfoot.setWorld(new UpgradeWorld(this, c.getLevel(), c));
        }
        if(c.getXP() == 40 && c.getLevel() == 2){
            Greenfoot.setWorld(new UpgradeWorld(this, c.getLevel(), c));
        }
        if(c.getXP() == 70 && c.getLevel() == 3){
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
