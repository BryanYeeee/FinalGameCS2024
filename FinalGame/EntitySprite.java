import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CharacterSprite here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EntitySprite extends SuperSmoothMover
{
    private Entity entity;
    
    public EntitySprite(Entity entity){
        this.entity = entity;
    }
    
    public void act()
    {
        setLocation(entity.getX(), entity.getY());
    }
}
