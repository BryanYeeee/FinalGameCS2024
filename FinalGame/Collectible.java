import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Collectable is a class that holds the animation and properites of pickup-able items.
 * 
 * @author Jeff G, Jamison H
 * @version June 2024
 */
public abstract class Collectible extends SuperSmoothMover
{

    protected int actCount=0;
    
    protected double hoverSpeed=1;
    protected int despawnSeconds = -1; // seconds it takes for collectible to despawn
                                  // -1 means collectible does not despawn
    
    protected boolean pickup;
    protected Player player;
    protected double pickupSpeed = -10;
    protected double deltaY;
    protected int myType; // 0=green, 1=yellow, 2=red
    /**
     * Default constructor of Collectible. Sets no type.
     */
    public Collectible() {
        this(20, -1, -1);
    }
    /**
     * Constructor of Collectible.
     * 
     * @param size              The size of the image.
     * @param despawnSeconds    The time to despawn.
     * @param type              The type of the collectible (colour).
     */
    public Collectible(int size, int despawnSeconds, int type) {
        myType = type;
        if(myType == 0){
            setImage("greenXP.png");
        } else if (myType == 1){
            setImage("yellowXP.png");
            getImage().setTransparency(125);
        } else if (myType == 2){
            setImage("redXP.png");
            getImage().setTransparency(125);
        }
        setSize(size);
        this.despawnSeconds = despawnSeconds;
        pickup = false;
    }
    /**
     * The act method, animate my pickup and add exp to the player.
     */
    public void act()
    {
        actCount++;
        if(!pickup){
            hover(actCount);
            if(actCount/60==despawnSeconds){
                getWorld().removeObject(this);
            }
        }
        else{
            if(distanceFrom(player.getX(),player.getY())>=pickupSpeed){
                
                turnTowards(player.getX(),player.getY());
                move(pickupSpeed);
                pickupSpeed+=2;
            }
            else{
                pickupEffect();
                Player p = ((MyWorld)getWorld()).getPlayer();
                switch (myType){
                    case 0:
                        p.increaseExp(1 + p.getEXPBuff());
                        break;
                    case 1:
                        p.increaseExp(2 + p.getEXPBuff());
                        break;
                    case 2:
                        p.increaseExp(5 + p.getEXPBuff());
                        break;
                }
                getWorld().removeObject(this);
            }
        }    
    }
    
    /**
     * Set the size of the item image.
     * 
     * @param size  The width and height of the image.
     */
    public void setSize(int size){
        GreenfootImage image = getImage();
        image.scale(size,size);
        setImage(image);
    }

    
    /**
     * The hovering effect.
     * 
     * @param actCount  Act count is used to determine the stage in the hover animation.
     */
    public void hover(int actCount){
        if(actCount%10==0){
            setLocation(getX(),getY()+hoverSpeed);
        }
        if(actCount%60==0){
            hoverSpeed=hoverSpeed*-1;
        }
    }
    
    /**
     * Set the player picking me up, set myself to be picked up.
     * 
     * @param player    The player in the game world.
     */
    public void pickup(Player player) {
        this.player = player;
        pickup = true;
    }
    
    /**
     * All subclasses must have a pickup effect.
     */
    public abstract void pickupEffect();
    
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
