import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * ChargeShot is a random upgrade obtained.
 * 
 * @author Ainson Z
 * @version June 2024
 */
public class ChargeShot extends Attack
{
    /**
     * Constructor of ChargeShot.
     * 
     * @param x The x-coordinate to look towards.
     * @param y The y-coordinate to look towards.
     */
    public ChargeShot(int x, int y) {
        super(x, y);
        
        animations = new GreenfootImage[8];
        for(int i = 0; i < animations.length; i++) {
            animations[i] = new GreenfootImage("images/Attacks/ChargeShot/ChargeShot" + i + ".png");
            int width = animations[i].getWidth();
            int height = animations[i].getHeight();  
            animations[i].scale(width * 3/2, height * 3/2);
        }
        setImage(animations[0]);
        imageOne = animations[0];
    }
    /**
     * Animate my attack.
     */
    public void animate() {
        if (actCount >= 6) { // Adjust timing as needed
            setImage(animations[imageIndex]);
            imageIndex = (imageIndex + 1) % animations.length;
            actCount = 0;
        }   
    }
    
    /**
     * The act method, move a certain speed.
     */
    public void act()
    {
        move(6);
        animate();
        ArrayList<Enemy> enemies = (ArrayList<Enemy>)getIntersectingObjects(Enemy.class);
        for(Enemy e : enemies) {
            e.takeDamage(world.getPlayer().getATK() + 6);
        }
        if(isTouching(Wall.class)){
            getWorld().removeObject(this);
            return;
        }
        finishAnimation();
    }
}
