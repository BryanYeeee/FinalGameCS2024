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
    private int pickupRange = 100;
    int act = 0;
    
    public Character(){
        super();
    }

    public void act()
    {
        act++;
        MouseInfo m = Greenfoot.getMouseInfo();
        if(m != null){
            if(distanceFrom(m.getX(), m.getY())>speed){
                turnTowards(m.getX(), m.getY());
                if(getX() != m.getX() || getY() != m.getY()){
                    // move(speed);
                    double angle = Math.toRadians(getPreciseRotation());
                    // if(act%60==0)System.out.println(getPreciseRotation() + " " + Math.cos(angle)*speed + " " + Math.sin(angle)*speed);
                    ((MyWorld)getWorld()).updateVP(Math.cos(angle)*speed, Math.sin(angle)*speed);
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
}
