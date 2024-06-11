import java.util.List;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Character here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Character extends Entity
{
    private final static int MOVEMENT_RANGE = 96; // Distance that mouse needs to be from the character in order to move
    private int pickupRange = 100;
    private int myXP = 0;
    private int level = 0;
    
    public Character(){
        super(100,2,"person.png");
    }

    public void act()
    {
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
    
    
}
