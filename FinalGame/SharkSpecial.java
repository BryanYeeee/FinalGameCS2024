import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * SharkSpecial is part of the trident weapon catagory.
 * 
 * @author Ainson Z
 * @version June 2024
 */
public class SharkSpecial extends Attack
{
    /**
     * Constructor of SharkSpecial.
     * 
     * @param x The x-coordinate to look towards.
     * @param y The y-coordinate to look towards.
     */
    public SharkSpecial(int x, int y) {
        super(x, y);
        
        animations = new GreenfootImage[18];
        for(int i = 0; i < animations.length; i++) {
            animations[i] = new GreenfootImage("images/Attacks/SharkSpecial/SharkSpecial" + i + ".png");
            int width = animations[i].getWidth();
            int height = animations[i].getHeight();  
            animations[i].scale(width * 2, height * 2);
        }
        setImage(animations[0]);
        imageOne = animations[0];
    }
    /**
     * Animate my attack.
     */
    public void animate() {
        if (actCount >= 2) { // Adjust timing as needed
            setImage(animations[imageIndex]);
            imageIndex = (imageIndex + 1) % animations.length;
            actCount = 0;
        }   
    }
    
    /**
     * The act method.
     */
    public void act()
    {
        super.act();
    }
}
