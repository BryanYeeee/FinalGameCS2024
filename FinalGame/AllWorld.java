import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AllWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AllWorld extends World
{
    // Height and width of all worlds
    public static final int WORLD_HEIGHT = 768;
    public static final int WORLD_WIDTH = 1024;
    
    // Color 
    protected Color bgColor = new Color(119, 136, 153);
    protected Color borderColor = new Color(192, 192, 192);
    protected Color transparentColor = new Color(0, 0, 0, 0);
    protected Color textColor = new Color(250, 249, 246);
    
    /**
     * Constructor for AllWorld, simliar to a normal world constructor but without the boolean bounded. 
     * This constructor will initalize sounds and values for fading between worlds.
     * 
     * @param width     The width of the world
     * @param height    The height of the world
     * @param cellSize  The size of each cell of the world, typically 1
     * @param bounded   Whether or not the world has boundaries or not
     */
    public AllWorld(int width, int height, int cellSize, boolean bounded)
    {    
        super(width, height, cellSize, bounded);
    }
}
