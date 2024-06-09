import greenfoot.*;

/**
 * Viewport class to generate the world for the player's screen
 * 
 * @author Dawson
 * @version January 2024
 */
public class Viewport extends Actor
{
    private int lx, ly, rx, ry;
    private MyWorld w;
    
    /**
     * Constructor for the ViewPort
     * 
     * @param       width of the world
     * @param       height of the world
     */
    public Viewport(MyWorld w){
        this.w = w;
        lx = 0;
        ly = 0;
        rx = MyWorld.WORLD_WIDTH;
        ry = MyWorld.WORLD_HEIGHT;
        renderMap();
    }
    
    /**
     * Method to move the render of the world when the player moves
     * 
     * @param addX      total movement in x direction
     * @param addY      total movement in y direction
     */
    public void move(double xMove, double yMove){
        lx+=xMove;
        ly+=yMove;
        rx+=xMove;
        ry+=yMove;
        System.out.println(xMove + " " + yMove);
                
        for(Tile t: w.getObjects(Tile.class)){
            t.setLocation(t.getX()-xMove, t.getY()-yMove);
        }
        
        renderMap();
    }
    
    public void renderMap() {
        Tile[][] map = w.getMap();
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                int tileX = j*Tile.TILE_LENGTH, tileY = i*Tile.TILE_LENGTH;
                if(tileX >= lx && tileX < rx && tileY >= ly && tileY < ry){
                    if(!w.getObjects(Tile.class).contains(map[i][j])){
                System.out.println((tileX-lx+Tile.TILE_LENGTH/2)+" "+ (tileY-ly+Tile.TILE_LENGTH/2) );
                        w.addObject(map[i][j], tileX-lx+Tile.TILE_LENGTH/2, tileY-ly+Tile.TILE_LENGTH/2);
                    }
                }else{
                    w.removeObject(map[i][j]);
                }
            }
        }
    }
}
