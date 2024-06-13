import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BasicSlash here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BasicSlash extends Attack
{
    
    public BasicSlash(int x, int y) {
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
    
    public void animate() {
        if (actCount >= 4) { // Adjust timing as needed
            setImage(animations[imageIndex]);
            imageIndex = (imageIndex + 1) % animations.length;
            actCount = 0;
        }   
    }
    
    /**
     * Act - do whatever the BasicSlash wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
}
