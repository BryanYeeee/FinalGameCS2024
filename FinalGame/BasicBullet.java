import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * BasicBullet is part of the gun weapon catagory.
 * 
 * @author Ainson Z
 * @version June 2024
 */
public class BasicBullet extends Attack
{
    private GreenfootImage image;
    
    /**
     * Constructor of BasicBullet.
     * 
     * @param x The x-coordinate to look towards.
     * @param y The y-coordinate to look towards.
     */
    public BasicBullet(int x, int y) {
        super(x, y);
        //this.size = size;
        //this.speed = speed;
        image = new GreenfootImage("BasicBullet.png");
        
        setImage(image);
    }
    
    /**
     * The default animation is a basic bullet.
     */
    public void animate() {
        
    }
    
    /**
     * The act method, move a certain speed.
     */
    public void act()
    {
        move(6);
        super.act();
    }
}
