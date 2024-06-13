import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Entity
{
    protected Character target;

    protected int targetX;
    protected int targetY;
    protected int atkCooldown; // so character doesn't insta die when hit
    
    public Enemy(int hp, int speed, int atk){
        super(hp, speed, atk);
        setEntityName("player");
        setAction("run");
    }
    
    
    
    protected void takeDamage(int damage){
        
        hp -= damage;
    }
}
