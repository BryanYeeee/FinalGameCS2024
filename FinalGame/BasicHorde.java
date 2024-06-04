import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BasicHorde here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BasicHorde extends Enemy
{
    private Character target;
    private MyWorld world;
    
    public BasicHorde(){
        hp = 100;
    }

    public void act()
    {
        if(target != null){
            turnTowards(target.getX(), target.getY());
            move(1);
        } else {
            world = (MyWorld)getWorld();
            target = world.getCharacter();
        }
        if(hp <= 0){
            world.removeObject(this);
            return;
        }
    }
    
    
}
