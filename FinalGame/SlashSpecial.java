import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SlashSpecial here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SlashSpecial extends Attack
{
    public SlashSpecial(int x, int y) {
        super(x, y);
        //this.size = size;
        //this.speed = speed;
        
        animations = new GreenfootImage[9];
        for(int i = 0; i < animations.length; i++) {
            animations[i] = new GreenfootImage("images/Attacks/SlashSpecial/SlashSpecial" + i + ".png");
            int width = animations[i].getWidth();
            int height = animations[i].getHeight();  
            animations[i].scale(width * 4, height * 4);
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
     * Act - do whatever the SlashSpecial wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
}
