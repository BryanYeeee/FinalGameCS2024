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
    private int weaponLevel = 0;
    private int speed;
    

    private int actCount = 0;

    private String action = "run";
    private String myWeapon = "";

    //Animation Variables
    private int animationDelay = 30;
    private int animationLength = 8;
    private int imageIndex;
    protected String entityName = "player";
    protected char dirChar = 'L';
    private MyWorld world;
    
    public Player(MyWorld world){
        
        super(300000,2,45);
        setEntityName("player");
        setAction("run");
        this.world = world;

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

    public void calculateAnimationTimes(){
        if (action.equals("run")) {
            animationDelay = 7;
            animationLength = 8;
        } else if (action.equals("idle")) {
            animationDelay = 50;
            animationLength = 2;
        }
        else if(action.equals("attack")){
            animationLength = 4;
        } 
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
    
    /*
    public int getMaxXPForLevel() {
        // Return the max XP required for the current level
        switch(level) {
            case 0: return 10;
            case 1: return 25;
            case 2: return 40;
            case 3: return 70;
            default: return 100; // Default value for higher levels
        }
    }
    */
    
    public void increaseExp(int amount){
        myXP += amount;
        world.updateXPBar();
        world.determineLevel();
    }
    
    
    public int getXP(){
        return myXP;
    }

    
    public void increaseLevel(){
        level++;
        myXP = 0;
        world.updateXPBar();
    }
    
    
    public int getRequiredXPForNextLevel() {
        if(level == 0) {
            return 0;
        }
        // Define the logic to calculate required XP for the next level
        return 5 + level * 10;
        //return 5 + level * 15;  // Example logic, modify as needed
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
    
    public void increaseWeaponLevel(){
        weaponLevel++;
    }
    
    public int getWeaponLevel(){
        return weaponLevel;
    }
    
    public void setWeapon(String weapon){
        myWeapon = weapon;
    }
    
    public String getWeapon(){
        return myWeapon;
    }
}
