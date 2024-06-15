import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Enemy is the player's main entity to kill and gain score, XP.
 * 
 * @author Bryan Y, Jeff G
 * @version June 2024
 */
public class Enemy extends Entity
{
    protected Player target;

    protected int targetX;
    protected int targetY;
    protected int atkCooldown; // so character doesn't insta die when hit
    
    /**
     * Constructor of Enemy.
     * 
     * @param hp    My HP.
     * @param speed My speed.
     * @param atk   My ATK.
     */
    public Enemy(int hp, int speed, int atk){
        super(hp, speed, atk);
        
    }
    
    /**
     * Calculate my animation time based on actions.
     */
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
}
