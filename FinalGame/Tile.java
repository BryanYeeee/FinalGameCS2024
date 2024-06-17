import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A Tile is any 128x128 pixel square in the map
 * 
 * @author Bryan Y
 * @version June 2024
 */
public class Tile extends SuperSmoothMover
{
    public static final int TILE_LENGTH = 128;
    Label l;
    /**
     * Constructor for Tile, set a 128x128 image.
     */
    public Tile(String imgURL, int i) {
        
        getImage().scale(TILE_LENGTH,TILE_LENGTH);
         GreenfootImage image = new GreenfootImage(imgURL);
         image.scale(TILE_LENGTH, TILE_LENGTH);
         setImage(image);
        l = new Label(i,50);
    }
    /**
     * The act method, constantly remove and add myself to make a scrolling effect.
     */
    public void act() {
        getWorld().removeObject(l);
        //getWorld().addObject(l, getX()+30, getY()+30);
    }
    /**
     * Remove the labels.
     */
    public void rml(){
        if(getWorld()==null||l==null)return;
        getWorld().removeObject(l);
    }
    
    
}
