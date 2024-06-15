import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Attack holds all the user's possible attacks and their animations.
 * 
 * @author Ainson Z
 * @version June 2024
 */
public abstract class Attack extends SuperSmoothMover
{
    protected GreenfootImage[] animations;

    protected int size;
    protected int actCount;
    protected GreenfootImage imageOne;
    protected boolean notImageOne;
    protected int imageIndex;
    
    protected int cooldownCount;
    protected int cooldown = 50;

    protected int x;
    protected int y;
    
    protected MyWorld world;
    /**
     * Constructor of Attack. Reset act count and obtain the coordinates which I will look towards.
     * 
     * @param x The x-coordinate to look towards.
     * @param y The y-coordinate to look towards.
     */
    public Attack(int x, int y) {
        actCount = 0;
        this.x = x;
        this.y = y;
    }

    /**
     * The act method of Attack. Animate and deal damage to enemies.
     */
    public void act()
    {
        actCount++;
        animate();
        
        if(cooldown >= 60) {
            ArrayList<Enemy> enemies = (ArrayList<Enemy>)getIntersectingObjects(Enemy.class);
            for(Enemy e : enemies) {
                if(this instanceof SlashSpecial || this instanceof Lightning || this instanceof SharkSpecial || this instanceof ChargeShot) {
                    e.takeDamage(world.getPlayer().getATK() + 6);
                } else if(this instanceof WaterSplash || this instanceof FireSlash) {
                    e.takeDamage(world.getPlayer().getATK() + 3);
                } else {
                    e.takeDamage(world.getPlayer().getATK());
                }
                cooldown = 20;
            }
        }
        cooldown++;
        finishAnimation();
    }
    
    /**
     * Once added to World, turn towards the given coordinates.
     * 
     * @param w The world which I was added to.
     */
    public void addedToWorld(World w){
        if((this instanceof Slash) || (this instanceof SlashSpecial) || (this instanceof Trident) || (this instanceof WaterSplash) || (this instanceof ChargeShot) || (this instanceof BasicSlash) || (this instanceof FireSlash) || (this instanceof BasicBullet)) {
            turnTowards(x, y);
        }
        world = (MyWorld)w;
    }
    
    /**
     * Each attack must implement their own animation.
     */
    public abstract void animate();
    
    /**
     * Makes sure animation plays once and then removes the instance of a itself
     * 
     * might want to make an effect so other classes can use this
     * 
     * Borrowed
     * @Author(Recorsi)
     */
    public void finishAnimation () {
        if((getImage() != imageOne) != notImageOne) {
            notImageOne = !notImageOne;
            if(!notImageOne) {
                getWorld().removeObject(this);
            }
        }
    }
}