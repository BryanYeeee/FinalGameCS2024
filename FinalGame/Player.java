import java.util.List;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Entity
{
    private final static int MOVEMENT_RANGE = 96; // Distance that mouse needs to be from the player in order to move
    private int pickupRange = 100;
    private int EXPBuff = 0;
    private int myXP = 0;
    private int level = 0;
    private int speed;

    private int actCount = 0;

    private String action = "run";

    //Animation Variables
    private int animationDelay = 30;
    private int animationLength = 8;
    private int imageIndex;
    protected String entityName = "player";
    protected char dirChar = 'L';
    
    public Player(){
        
        super(200,2,45);
        setEntityName("player");
        setAction("run");
        
        speed = 2;
    }

    public void act()
    {
        super.act();
        MouseInfo m = Greenfoot.getMouseInfo();
        if(m != null){
            if(distanceFrom(m.getX(), m.getY())>MOVEMENT_RANGE){
                turnTowards(m.getX(), m.getY());
                if(getX() != m.getX() || getY() != m.getY()){
                    double angle = Math.toRadians(getPreciseRotation());
                    ((MyWorld)getWorld()).updateVP((int)Math.round(Math.cos(angle))*speed, (int)Math.round(Math.sin(angle))*speed);
                }
            }
        }
        
        pickupItems();
    }

    
    public int getPlayerX(){
        return getX();
    }

    public int getPlayerY(){
        return getY();
    }

    private void pickupItems() {
        // Get all Collectible objects within the pickupRange
        List<Collectible> collectibles = getObjectsInRange(pickupRange, Collectible.class);
        // Iterate through the list and remove each item from the world
        for (Collectible c : collectibles) {
            c.pickup(this);
            // You can add additional logic here, like updating a score or inventory
        }
    }

    public void increaseExp(int amount){
        myXP += amount;
    }

    public int getXP(){
        return myXP;
    }

    public void increaseLevel(){
        level++;
    }

    public int getLevel(){
        return level;
    }
    
    public void increasePickUpRange(int amount){
        pickupRange += amount;
    }
    
    public void increaseEXPBuff(int amount){
        EXPBuff += amount;
    }
    
    public int getEXPBuff(){
        return EXPBuff;
    }

}
