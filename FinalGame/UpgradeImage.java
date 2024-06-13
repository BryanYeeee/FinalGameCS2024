import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class UpgradeImage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UpgradeImage extends Actor
{
    private GreenfootImage myImage;
    public UpgradeImage(String imagePath){
        myImage = new GreenfootImage(imagePath);
        myImage.scale(40,40);
        setImage(myImage);
    }
}
