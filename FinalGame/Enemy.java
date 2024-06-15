import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Entity
{
    protected Player target;

    protected int targetX;
    protected int targetY;
    protected int atkCooldown; // so character doesn't insta die when hit
    protected boolean isAlive = true;
    public Enemy(int hp, int speed, int atk){
        super(hp, speed, atk);
        
    }
    
    public void calculateAnimationTimes(){
        if (action.equals("run")) {
            animationDelay = 12;
            animationLength = 4;
        } else if (action.equals("death")) {
            animationDelay = 8;
            animationLength = 8;
        }
        
    }
    
    protected void takeDamage(int damage){
         String key = "basicEnemy_hit_" + dirChar + "_0";
        GreenfootImage currentImage = Sprite.getFrame(key);

        try {
            currentImage.scale(64, 64);
        }
        catch (NullPointerException e) {
            System.out.println(key);
        }

        setImage(currentImage);
        hp -= damage;
    }
    
    public boolean getAlive() {
        return isAlive;
    }
}
