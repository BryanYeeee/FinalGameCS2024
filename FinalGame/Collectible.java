import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Collectible here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Collectible extends SuperSmoothMover
{
    /**
     * Act - do whatever the Collectible wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    protected int actCount=0;
    
    protected double hoverSpeed=1;
    protected int despawnSeconds = -1; // seconds it takes for collectible to despawn
                                  // -1 means collectible does not despawn
    
    protected boolean pickup;
    protected Player player;
    protected double pickupSpeed = -10;
    protected double deltaY;
    protected int myType; // 0=green, 1=yellow, 2=red
    
    public Collectible() {
        this(20, -1, -1);
    }
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
                switch (myType){
                    case 0:
                        ((MyWorld)getWorld()).getCharacter().increaseExp(1);
                        break;
                    case 1:
                        ((MyWorld)getWorld()).getCharacter().increaseExp(2);
                        break;
                    case 2:
                        ((MyWorld)getWorld()).getCharacter().increaseExp(5);
                        break;
                }
                
                ((MyWorld)getWorld()).getPlayer().increaseExp(1);
                /* debug
                int xp = ((MyWorld)getWorld()).getPlayer().getXP();
                System.out.println(xp);
                int level = ((MyWorld)getWorld()).getPlayer().getLevel();
                System.out.println(level);
                */
                getWorld().removeObject(this);
            }
        }    
    }
    
    /**
     * Set the size of the item image.
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
    public void pickup(Player player) {
        this.player = player;
        pickup = true;
    }
    
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
