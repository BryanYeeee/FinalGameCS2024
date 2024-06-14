import greenfoot.*;

/**
 * Viewport class to generate the world for the player's screen
 * 
 * @author 
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
    public void move(int xMove, int yMove){
        lx+=xMove;
        ly+=yMove;
        rx+=xMove;
        ry+=yMove;
                
        for(SuperSmoothMover t: w.getObjects(SuperSmoothMover.class)){
            if(t instanceof Player || t instanceof Gun)continue;
            // System.out.println((t.getX()-xMove)+ " " + xMove + " | " +(t.getY()-yMove) + " " +yMove);
            t.setLocation(t.getX()-xMove, t.getY()-yMove);
        }
            //System.out.println("========");
        
        renderMap();
    }
    
    public void renderMap() {
        Tile[][] map = w.getMap();
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                double tileX = j*Tile.TILE_LENGTH, tileY = i*Tile.TILE_LENGTH;
                if(tileX >= lx-128 && tileX <= rx  && tileY >= ly-128 && tileY <= ry){
                    if(!w.getObjects(Tile.class).contains(map[i][j])){
                // System.out.println((int)(tileX-lx+64.0)+" "+ (int)(tileY-ly+64.0) );
                        w.addObject(map[i][j], (int)(tileX-lx+64.0), (int)(tileY-ly+64.0));
                    }
                }else{
                    map[i][j].rml();
                    w.removeObject(map[i][j]);
                    //System.out.println(i + " " +j);
                }
            }
        }
    }
    public double lx(){return lx;};
    public double ly(){return ly;};
    public double rx(){return rx;};
    public double ry(){return ry;};
}
