import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends Entity
{
    private GreenfootImage bullet = new GreenfootImage("bullet.png");
    
    private int x;
    private int y;

    public Bullet(int x, int y){
        this.x = x;
        this.y = y;
        bullet.scale(35,15);
        setImage(bullet);
    }

    public void addedToWorld(World w){
        turnTowards(x,y);  
    }

    public void act()
    {
        move(4);
        
        Enemy enemy = (Enemy)getOneIntersectingObject(Enemy.class);
        if(enemy != null){
            enemy.takeDamage(100);
            getWorld().removeObject(this);
            return;
        }

        if(isAtEdge()){
            getWorld().removeObject(this);
            return;
        }
    }

    
}
