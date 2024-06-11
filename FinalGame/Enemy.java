import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Entity
{
    protected int atkCooldown; // so character doesn't insta die when hit
    
    public Enemy(int hp, int speed, int atk, String imgURL){
        super(hp, speed, atk, imgURL);
    }
    
    protected void takeDamage(int damage){
        hp -= damage;
    }
}
