import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Character here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Character extends SuperSmoothMover
{
    private int speed;
    public Character(){
        speed = 2;
    }
    public void act()
    {
        MouseInfo m = Greenfoot.getMouseInfo();
        if(m != null){
            turnTowards(m.getX(), m.getY());
            if(getX() != m.getX() || getY() != m.getY()){
                move(speed);
            }
        }
    }
    
    public int getCharacterX(){
        return getX();
    }
    
    public int getCharacterY(){
        return getY();
    }
}
