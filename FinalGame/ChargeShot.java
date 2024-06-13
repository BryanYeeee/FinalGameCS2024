import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ChargeShot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ChargeShot extends Attack
{
    
    public ChargeShot(int x, int y) {
        super(x, y);
        //this.size = size;
        //this.speed = speed;
        
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
    
    public void animate() {
        if (actCount >= 10) { // Adjust timing as needed
            setImage(animations[imageIndex]);
            imageIndex = (imageIndex + 1) % animations.length;
            actCount = 0;
        }   
    }
    
    /**
     * Act - do whatever the ChargeShot wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        move(6);
        super.act();
    }
}
