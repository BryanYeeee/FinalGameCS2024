import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class XP here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class XP extends Collectible
{   
    /**
     * Act - do whatever the XP wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public XP(int x, int y){
        super(20,60);
        initialX = x;
        initialY = y;
    }
    
    public void pickupEffect(){
        
    }
    
    /*
    public void addedToWorld(World world)
    {
        super.addedToWorld(world);
        setLocation(initialX + ((MyWorld) world).getScroller().getScrolledX(),
                    initialY +  ((MyWorld) world).getScroller().getScrolledY());
    }
    */

    /*
    public void scrollPosition(int dx, int dy) {
        fixedX -= dx;
        fixedY -= dy;
        setLocation(getX() - dx, getY() - dy);
    }
    */
}
