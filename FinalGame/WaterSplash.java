import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * WaterSplash is part of the trident weapon catagory.
 * 
 * @author Ainson Z
 * @version June 2024
 */
public class WaterSplash extends Attack
{
    /**
     * Constructor of WaterSplash.
     * 
     * @param x The x-coordinate to look towards.
     * @param y The y-coordinate to look towards.
     */
    public WaterSplash(int x, int y) {
        super(x, y);
        //this.size = size;
        //this.speed = speed;
        
        animations = new GreenfootImage[7];
        for(int i = 0; i < animations.length; i++) {
            animations[i] = new GreenfootImage("images/Attacks/WaterSplash/WaterSplash" + i + ".png");
            int width = animations[i].getWidth();
            int height = animations[i].getHeight();  
            animations[i].scale(width * 4, height * 4);
        }
        setImage(animations[0]);
        imageOne = animations[0];
    }
    /**
     * Animate my attack.
     */
    public void animate() {
        if (actCount >= 3) { // Adjust timing as needed
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
