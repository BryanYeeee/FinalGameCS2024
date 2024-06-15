import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tile extends SuperSmoothMover
{
    public static final int TILE_LENGTH = 128;
    Label l;
    
    public Tile(String imgURL, int i) {
        
        getImage().scale(TILE_LENGTH,TILE_LENGTH);
         GreenfootImage image = new GreenfootImage(imgURL);
         image.scale(TILE_LENGTH, TILE_LENGTH);
         setImage(image);
        l = new Label(i,50);
    }
    
    public void act() {
        getWorld().removeObject(l);
        getWorld().addObject(l, getX()+30, getY()+30);
    }
    public void rml(){
        if(getWorld()==null||l==null)return;
        getWorld().removeObject(l);
    }
    
    
}
