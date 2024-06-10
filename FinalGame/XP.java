import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class XP here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class XP extends Collectible
{
    private int fixedX;
    private int fixedY;
    
    /**
     * Act - do whatever the XP wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public XP(int x, int y){
        super(20,60);
        fixedX = x;
        fixedY = y;
    }
    
    public void pickupEffect(){
        
    }
    
    protected void addedToWorld(World world)
    {
        setLocation(fixedX - ((MyWorld) world).getScroller().getScrolledX(), 
                    fixedY - ((MyWorld) world).getScroller().getScrolledY());
    }
    
    public void scrollPosition(int dx, int dy) {
        fixedX -= dx;
        fixedY -= dy;
        setLocation(getX() - dx, getY() - dy);
    }
}
