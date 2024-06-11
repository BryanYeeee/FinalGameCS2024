import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Entity
{
    public Enemy(int hp, int speed, String imgURL){
        super(hp, speed, imgURL);
    }
    
    protected void takeDamage(int damage){
        hp -= damage;
    }
}
