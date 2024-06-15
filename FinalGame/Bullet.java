import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * Bullet is what gun-type weapons shoot.
 * 
 * @author Ainson Z, Jamison H
 * @version June 2024
 */
public class Bullet extends SuperSmoothMover
{
    private GreenfootImage bullet = new GreenfootImage("bullet.png");
    
    private int x;
    private int y;
    
    MyWorld world;

    /**
     * Constructor of Bullet.
     * 
     * @param x The x-coordinate to look towards.
     * @param y The y-coordinate to look towards.
     */
    public Bullet(int x, int y){
        this.x = x;
        this.y = y;
        bullet.scale(35,15);
        setImage(bullet);
    }

    /**
     * Once added to world, turn towards an enemy.
     * 
     * @param w The world I am added to.
     */
    public void addedToWorld(World w){
        turnTowards(x,y);  
        world = (MyWorld)w;
    }

    /**
     * The act method, damage enemies hit.
     */
    public void act()
    {
        move(4);
        
        Enemy enemy = (Enemy)getOneIntersectingObject(Enemy.class);
        if(enemy != null){
            enemy.takeDamage(world.getPlayer().getATK());
            getWorld().removeObject(this);
            return;
        }

        if(isTouching(Wall.class)){
            getWorld().removeObject(this);
            return;
        }
    }

    
}
