import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends SuperSmoothMover
{
    int x;
    int y;
    public Bullet(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void addedToWorld(World w){
        turnTowards(x,y);  
    }
    public void act()
    {
        move(4);
        if(isAtEdge()){
            getWorld().removeObject(this);
            return;
        }
    }
}
