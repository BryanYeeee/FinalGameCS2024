import java.util.List;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author Jamison H, Jeff G, Bryan Y, Ainson Z
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
    
    /**
     * Constructor of Player
     */
    public Player(MyWorld world){
        
        super(300000,2,45);
        setEntityName("player");
        setAction("run");
        this.world = world;

        speed = 2;
    }

    /**
     * The act method, update the map scrolling depending on the cursor location.
     */
    public void act()
    {
        super.act();
        MouseInfo m = Greenfoot.getMouseInfo();
        if(m != null){
            if(distanceFrom(m.getX(), m.getY())>MOVEMENT_RANGE){
                turnTowards(m.getX(), m.getY());
                if(getX() != m.getX() || getY() != m.getY()){
                    double angle = Math.toRadians(getPreciseRotation());
                    int xMove = (int)Math.round(Math.cos(angle))*speed;
                    int yMove = (int)Math.round(Math.sin(angle))*speed;
                    Wall w = (Wall)getOneObjectAtOffset(xMove,yMove,Wall.class);
                    if(w == null) ((MyWorld)getWorld()).updateVP(xMove, yMove);
                }
            }
        }
        
        pickupItems();
    }

    /**
     * Calculate my animation times based on actions.
     */
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
    
    /**
     * Get my x-coordinate.
     * 
     * @return int  The x-coordinate.
     */
    public int getPlayerX(){
        return getX();
    }
    /**
     * Get my y-coordinate.
     * 
     * @return int  The y-coordinate.
     */
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
    
    /**
     * Return the XP needed for each level.
     * 
     * @return int  The XP needed.
     */
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
    
    /**
     * Increase my exp by a certain amount.
     * 
     * @param amount    The amount to increase.
     */
    public void increaseExp(int amount){
        myXP += amount;
        world.updateXPBar();
        world.determineLevel();
    }
    
    /**
     * Get my current amount of XP.
     * 
     * @return int  My XP.
     */
    public int getXP(){
        return myXP;
    }

    /**
     * Increase my level by one.
     */
    
    public void increaseLevel(){
        level++;
        myXP = 0;
        world.updateXPBar();
    }
    
    /**
     * Get the XP needed for the next level.
     * 
     * @return int  The XP needed.
     */
    public int getRequiredXPForNextLevel() {
        if(level == 0){
            return 0;
        }
        return 5 + level * 10;
    }

    /**
     * Get my current level.
     * 
     * @return int The current level.
     */
    public int getLevel(){
        return level;
    }
    
    /**
     * Increase my pickup range of objects by a certain amount.
     * 
     * @param amount    The amount to increase.
     */
    public void increasePickUpRange(int amount){
        pickupRange += amount;
    }
    /**
     * Increase my rate of exp gains by a certain amount.
     * 
     * @param amount    The amount to increase.
     */
    public void increaseEXPBuff(int amount){
        EXPBuff += amount;
    }
    
    /**
     * Get my rate of exp gains.
     * 
     * @return int    My exp gain rate.
     */
    public int getEXPBuff(){
        return EXPBuff;
    }
    
    /**
     * Increase my weapon level by one.
     */
    public void increaseWeaponLevel(){
        weaponLevel++;
    }
    
    /**
     * Get my weapon level.
     * 
     * @return int    My weapon level.
     */
    public int getWeaponLevel(){
        return weaponLevel;
    }
    /**
     * Set my weapon.
     * 
     * @param weapon    The weapon to be set to.
     */
    public void setWeapon(String weapon){
        myWeapon = weapon;
    }
    
    /**
     * Get the current weapon.
     * 
     * @return String   The current weapon.
     */
    public String getWeapon(){
        return myWeapon;
    }
}
