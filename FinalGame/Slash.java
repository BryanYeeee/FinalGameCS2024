import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Attack here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Slash extends Attack{

    public Slash() {
        //this.size = size;
        //this.speed = speed;
        animations = new GreenfootImage[6];
        actCount = 0;
        for(int i = 0; i < slashes1.length; i++) {
            animations[i] = new GreenfootImage("images/Attacks/Slash/Slash" + i + ".png");
            int width = animations[i].getWidth();
            int height = animations[i].getHeight();  
            animations[i].scale(width*3/2, height*3/2);
        }
        setImage(animations[0]);
        imageOne = animations[0];
    }

    public void act() {
        super.act();
    }
}
