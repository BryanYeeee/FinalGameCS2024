import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Attack here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Attack extends SuperSmoothMover
{
    protected GreenfootImage[] animations;

    protected int size;
    protected int actCount;
    protected GreenfootImage imageOne;
    protected boolean notImageOne;

    protected int animationDelay = 1;
    protected int animationLength = 7;
    protected int imageIndex;

    /**
     * Act - do whatever the Attack wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        actCount++;
        animate();
        finishAnimation();
    }

    public void animate() {
        if(actCount < 60) {
            return;
        }
        setImage(animations[imageIndex]);
        imageIndex = (imageIndex + 1) % animations.length;

    }

    /* 
     public void animate() {
        //how fast the animation plays
        if(animationTimer.millisElapsed() < (60)) {
            return;
        }
        animationTimer.mark();
        
        setImage(animations[imageIndex]);
        imageIndex = (imageIndex + 1) % animations.length;
    } */

    /**
     * Makes sure animation plays once and then removes the instance of a itself
     * 
     * might want to make an effect so other classes can use this
     * 
     * 
     * Borrowed
     * @Author(Recorsi)
     * 
     */
    public void finishAnimation () {
        if((getImage() != imageOne) != notImageOne) {
            notImageOne = !notImageOne;
            if(!notImageOne) {
                getWorld().removeObject(this);
            }
        }
    }
}