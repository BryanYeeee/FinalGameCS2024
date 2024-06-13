import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SharkSpecial here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SharkSpecial extends Attack
{
    
    public SharkSpecial(int x, int y) {
        super(x, y);
        //this.size = size;
        //this.speed = speed;
        
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
    
    public void animate() {
        if (actCount >= 2) { // Adjust timing as needed
            setImage(animations[imageIndex]);
            imageIndex = (imageIndex + 1) % animations.length;
            actCount = 0;
        }   
    }
    
    /**
     * Act - do whatever the SharkSpecial wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
}
