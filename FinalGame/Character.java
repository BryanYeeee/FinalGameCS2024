import java.util.List;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Character here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Character extends SuperSmoothMover
{
    private int speed;
    private int pickupRange = 100;
    private int myXP = 0;
    private int level = 0;
    public Character(){
        speed = 2;
    }

    public void act()
    {
        MouseInfo m = Greenfoot.getMouseInfo();
        if(m != null){
            if(distanceFrom(m.getX(), m.getY())>speed){
                turnTowards(m.getX(), m.getY());
                if(getX() != m.getX() || getY() != m.getY()){
                    move(speed);
                }
            }
        }
        pickupItems();
        determineLevel();
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
    
    public void increaseLevel(){
        level++;
    }
    
    private void determineLevel(){
        if(myXP == 10 && level == 0){
            Greenfoot.setWorld(new UpgradeWorld((MyWorld)getWorld()));
        }
        if(myXP == 25 && level == 1){
            Greenfoot.setWorld(new UpgradeWorld((MyWorld)getWorld()));
        }
    }

    /**
     * Return the distance between myself and another (x,y) coordinate pair.
     * 
     * @param x         The other x coordinate.
     * @param y         The other y coordinate.
     * @return double   The distance between myself and another coordinate.
     */
    public double distanceFrom(int x, int y) {
        int deltaX = getX() - x; 
        int deltaY = getY() - y; 
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY); 
    }
}
