import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Attack here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
    protected int cooldown = 60;

    protected int x;
    protected int y;
    
    protected MyWorld world;
    
    public Attack(int x, int y) {
        actCount = 0;
        this.x = x;
        this.y = y;
    }

    /**
     * Act - do whatever the Attack wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        actCount++;
        animate();
        
        if(cooldown == 60) {
            /*
            Enemy enemy = (Enemy)getOneIntersectingObject(Enemy.class);
            if(enemy != null){
                enemy.takeDamage(world.getPlayer().getATK());
                
                cooldown = 0;
            }
            */
            
            ArrayList<Enemy> enemies = (ArrayList<Enemy>)getIntersectingObjects(Enemy.class);
            for(Enemy e : enemies) {
                e.takeDamage(world.getPlayer().getATK());
                cooldown = 0;
            }
        }
        cooldown++;
        finishAnimation();
    }
    
    public void addedToWorld(World w){
        turnTowards(x,y);  
        world = (MyWorld)w;
    }

    public void animate() {
        if (actCount >= 4) { // Adjust timing as needed
            setImage(animations[imageIndex]);
            System.out.println("Animating: " + imageIndex); // Debugging line
            imageIndex = (imageIndex + 1) % animations.length;
            actCount = 0;
        }   
    }
    
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