import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * XP is what the players uses to get upgrades.
 * 
 * @author Jeff G
 * @version June 2024
 */
public class XP extends Collectible
{
    /**
     * Constructor of XP
     * 
     * @param type  The type of orb I am.
     */
    public XP(int type){
        super(20,60,type);
    }
    
    /**
     * Effect is default to the main pickup effect.
     */
    public void pickupEffect(){
        
    }
}
