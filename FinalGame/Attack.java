import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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

    protected int x;
    protected int y;
    
    protected MyWorld world;
    
    public Attack(int x, int y) {
        actCount = 0;
        this.x = x + 100;
        this.y = y + 100;
    }

    /**
     * Act - do whatever the Attack wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        actCount++;
        animate();
        
        Enemy enemy = (Enemy)getOneIntersectingObject(Enemy.class);
        if(enemy != null){
            enemy.takeDamage(world.getPlayer().getATK());
            //getWorld().removeObject(this);
        }
        
        //finishAnimation();
    }
    
    public void addedToWorld(World w){
        turnTowards(x,y);  
        world = (MyWorld)w;
    }

    public void animate() {
        /*
        if(actCount < 10) {
            return;
        }
        if (animations != null && animations.length > 0) {
            setImage(animations[imageIndex]);
            imageIndex = (imageIndex + 1) % animations.length;
        }
        */
        if (actCount >= 120) { // Adjust timing as needed
            if (animations != null && animations.length > 0) {
                setImage(animations[imageIndex]);
                System.out.println("Animating: " + imageIndex); // Debugging line
                imageIndex = (imageIndex + 1) % animations.length;
            }
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
        /*
        if((getImage() != imageOne) != notImageOne) {
            notImageOne = !notImageOne;
            if(!notImageOne) {
                getWorld().removeObject(this);
            }
        }
        */
        if (imageOne != null && (getImage() != imageOne) != notImageOne) {
            notImageOne = !notImageOne;
            if (!notImageOne) {
                getWorld().removeObject(this);
            }
        }

    }
}