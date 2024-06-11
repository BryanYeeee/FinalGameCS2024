import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Gun here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gun extends SuperSmoothMover
{
    private boolean pressed;
    public void addedToWorld(World w) {
        Character c = ((MyWorld)w).getCharacter();
        setLocation(c.getX() -20, c.getY() -20);
    }
    
    public void act()
    {
        MouseInfo m = Greenfoot.getMouseInfo();
        if(m != null){
            turnTowards(m.getX(), m.getY());
            if(Greenfoot.mousePressed(null)) pressed = true;
            else if(Greenfoot.mouseClicked(null)) pressed = false;
            if(pressed){
                getWorld().addObject(new Bullet(m.getX(), m.getY()),getX(), getY());
            }
        }
    }
}
