import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * UpgradeImage holds the image of the upgrade (since the box can't be set to 2 images at once).
 * 
 * @author Jamison H
 * @version June 2024
 */
public class UpgradeImage extends Actor
{
    /**
     * Constructor of UpgradeImage.
     * 
     * @param image The image to be set to.
     */
    public UpgradeImage(GreenfootImage image){
        image.scale(60,60);
        setImage(image);
    }
}
