import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Entity here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Entity extends SuperSmoothMover
{
    protected EntitySprite sprite;
    protected int speed;
    protected int hp;
    protected int atk;
    protected MyWorld world;

    public Entity(int hp, int speed, String imgURL){
        this.hp = hp;
        this.speed = speed;
        sprite = new EntitySprite(this, imgURL);
    }

    public void setTransparency(int amount){
        getImage().setTransparency(amount);
    }
    
    public void addedToWorld(World w) {
        this.world = (MyWorld)w;
        w.addObject(sprite,0,0);
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
