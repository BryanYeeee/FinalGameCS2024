
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * BasicSlash is part of the gun weapon catagory.
 * 
 * @author Ainson Z
 * @version June 2024
 */

public class TripleBullet extends Attack
{
    /**
     * Constructor of BasicSlash.
     * 
     * @param x The x-coordinate to look towards.
     * @param y The y-coordinate to look towards.
     */
    public TripleBullet(int x, int y) {
        super(x, y);
        //this.size = size;
        //this.speed = speed;
        
        animations = new GreenfootImage[8];
        for(int i = 0; i < animations.length; i++) {
            animations[i] = new GreenfootImage("images/Attacks/BasicSlash/BasicSlash" + i + ".png");
            int width = animations[i].getWidth();
            int height = animations[i].getHeight();  
            animations[i].scale(width * 5/2, height * 5/2);
        }
        setImage(animations[0]);
        imageOne = animations[0];
    }
    /**
     * Animate my attack.
     */
    public void animate() {
        
    }
    /**
     * The act method.
     */
    public void act()
    {
        // Add your action code here.

    }
    
}
