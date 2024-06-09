import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BuilderWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BuilderWorld extends World
{
    private GreenfootImage image;
    
    private int[][] tilemap = {
        {1, 3, 3, 3, 3, 3, 3, 3, 3, 3},
        {3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
        {3, 3, 1, 3, 3, 2, 3, 3, 3, 3},
        {3, 3, 3, 3, 3, 2, 3, 2, 3, 3},
        {3, 3, 3, 1, 3, 3, 3, 3, 3, 3},
        {3, 3, 3, 3, 3, 2, 3, 3, 4, 3},
        {3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
    };
    
    /**
     * Constructor for objects of class BuilderWorld.
     * 
     */
    public BuilderWorld()
    {    
        super(1250, 875, 1);
        
        //image = new GreenfootImage("Grassland.png");
        //image.scale(1200, 850);
        //setBackground(image);
        buildWorld();
    }
    
    private void buildWorld() {
        int tileSize = 125; 
        for (int y = 0; y < tilemap.length; y++) {
            for (int x = 0; x < tilemap[y].length; x++) {
                int tileType = tilemap[y][x];
                Tiles tile = null;

                switch (tileType) {
                    case 0:
                        tile = new GrassTile();
                        break;
                    case 3:
                        tile = new CroppedGrassTile();
                        break;
                    case 1:
                        tile = new TreeTile();
                        break;
                    case 2:
                        tile = new RockTile();
                        break;
                    case 4:
                        tile = new TownHall();
                        break;
                }

                if (tile != null) {
                    addObject(tile, 60 + (125 * x), 60 + (125 * y));
                }
            }
        }
    }
}
