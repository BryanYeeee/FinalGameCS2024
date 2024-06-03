import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Gun here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gun extends SuperSmoothMover
{
    /**
     * Act - do whatever the Gun wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        MouseInfo m = Greenfoot.getMouseInfo();
        if(m != null){
            turnTowards(m.getX(), m.getY());
            if(Greenfoot.mouseClicked(null)){
                getWorld().addObject(new Bullet(m.getX(), m.getY()),getX(), getY());
            }
        }
        MyWorld world = (MyWorld)getWorld();
        Character c = world.getCharacter();
        setLocation(c.getCharacterX() -20, c.getCharacterY() -20);
    }
}
