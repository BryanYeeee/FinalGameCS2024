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
    protected Character character;
    protected double pickupSpeed = 10;
    protected double deltaY;
    
    protected int initialX, initialY;
    
    public Collectible() {
        this(20, -1);
    }
    public Collectible(int size, int despawnSeconds) {
        setSize(size);
        this.despawnSeconds = despawnSeconds;
        pickup = false;
    }
    protected void addedToWorld(World world)
    {
        initialX = getX();
        initialY = getY();
    }
    /*
    public void act()
    {
        actCount++;
        if(!pickup){
            hover(actCount);
            if(actCount/60==despawnSeconds){
                System.out.println(despawnSeconds);
                getWorld().removeObject(this);
            }
        }
        else{
            if(distanceFrom(character.getX(),character.getY())>=pickupSpeed){
                deltaY=distanceFrom(character.getX(),character.getY())/2;
                turnTowards(character.getX(),character.getY()+(int)Math.round(deltaY));
                move(pickupSpeed);
                pickupSpeed+=0.5;
            }
            else{
                pickupEffect();
                getWorld().removeObject(this);
            }
        }    
    }*/
     public void act()
    {
        actCount++;
        if(!pickup) {
            hover(actCount);
            if(actCount/60 == despawnSeconds) {
                getWorld().removeObject(this);
            }
        } else {
            if(distanceFrom(character.getX(), character.getY()) >= pickupSpeed) {
                deltaY = distanceFrom(character.getX(), character.getY()) / 2;
                turnTowards(character.getX(), character.getY() + (int)Math.round(deltaY));
                move(pickupSpeed);
                pickupSpeed += 0.5;
            } else {
                pickupEffect();
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
    public void pickup(Character character) {
        this.character = character;
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
    
    public void updatePosition(int scrollX, int scrollY) {
        setLocation(initialX - scrollX, initialY - scrollY);
    }
}
