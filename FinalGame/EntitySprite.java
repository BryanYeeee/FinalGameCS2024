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
    
    private GreenfootImage spriteR;
    private GreenfootImage spriteL;
    
    public EntitySprite(Entity entity, String imgURL){
        this.entity = entity;
        
        //try catch
        spriteL = new GreenfootImage(imgURL);
        spriteR = new GreenfootImage(imgURL);
        spriteR.mirrorHorizontally();
        
        setImage(spriteL);
    }
    
    public void act()
    {
        if(entity.getWorld() == null) { 
            getWorld().removeObject(this);
            return;
        }
        
        setLocation(entity.getX(), entity.getY());
        if(entity.getX() < AllWorld.WORLD_WIDTH/2) {
            setImage(spriteR);
        } else {
            setImage(spriteL);
        }
    }
}
