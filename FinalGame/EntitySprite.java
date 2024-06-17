import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * EntitySprite is a class supporting left and right facing images.
 * 
 * @author Bryan Y
 * @version June 2024
 */

public class EntitySprite extends SuperSmoothMover
{
    private Entity entity;
    
    private GreenfootImage spriteR;
    private GreenfootImage spriteL;
    
    /**
     * Constructor of EntitySprite.
     * 
     * @param entity    The entity to have left and right images of.
     * @param imgURL    The image path.
     */
    public EntitySprite(Entity entity, String imgURL){
        this.entity = entity;
        
        //try catch
        spriteL = new GreenfootImage(imgURL);
        spriteR = new GreenfootImage(imgURL);
        spriteR.mirrorHorizontally();
        
        setImage(spriteL);
    }
    /**
     * The act method, depending on the entity's location in the world swap b/w L and R images.
     */
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
