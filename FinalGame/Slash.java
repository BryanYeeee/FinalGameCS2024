import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Slash is part of the sword weapon catagory.
 * 
 * @author Ainson Z
 * @version June 2024
 */
public class Slash extends Attack
{
    /**
     * Constructor of Slash.
     * 
     * @param x The x-coordinate to look towards.
     * @param y The y-coordinate to look towards.
     */
    public Slash(int x, int y) {
        super(x, y);
        //this.size = size;
        //this.speed = speed;
        
        animations = new GreenfootImage[6];
        for(int i = 0; i < animations.length; i++) {
            animations[i] = new GreenfootImage("images/Attacks/Slash/Slash" + i + ".png");
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
        if (actCount >= 4) { // Adjust timing as needed
            setImage(animations[imageIndex]);
            imageIndex = (imageIndex + 1) % animations.length;
            actCount = 0;
        }   
    }
    /**
     * The act method.
     */
    public void act() {
        super.act();
    }
}
