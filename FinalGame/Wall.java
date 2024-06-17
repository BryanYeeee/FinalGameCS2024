import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Wall is a visual indicator of the edge of the map.
 * 
 * @author Bryan Y 
 * @version June 2024
 */
public class Wall extends Tile
{
    // public static final int TILE_LENGTH = 128;
    // Label l;
    
    /**
     * Constructor for Wall(). Sets the image of wall.
     */
    public Wall() {
        super("Wall.png",0);
        // getImage().scale(TILE_LENGTH,TILE_LENGTH);
         // GreenfootImage image = new GreenfootImage(imgURL);
         // image.scale(TILE_LENGTH, TILE_LENGTH);
         // setImage(image);
        // l = new Label(i,50);
    }
    
    public void act() {
        // getWorld().removeObject(l);
        // getWorld().addObject(l, getX()+30, getY()+30);
    }
    // public void rml(){
        // if(getWorld()==null||l==null)return;
        // getWorld().removeObject(l);
    // }
    
    
}
