import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Entity
{
    protected int hp;
    
    public void act()
    {
        // Add your action code here.
    }
    
    protected void takeDamage(int damage){
        hp -= damage;
    }
    
    public int getEnemyX(){
        return getX();
    }
    
    public int getEnemyY(){
        return getY();
    }
}
