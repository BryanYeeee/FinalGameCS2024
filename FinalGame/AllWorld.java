import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * AllWorld holds all constants and traits relating to all worlds in the game.
 * 
 * @author Ainson Z, Jamison H
 * @version June 2024
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
    
    protected static SoundManager sm = new SoundManager();
    
    /**
     * Constructor for AllWorld, simliar to a normal world constructor but without the boolean bounded. 
     * This constructor will initalize sounds and values for fading between worlds.
     * 
     * @param width     The width of the world.
     * @param height    The height of the world.
     * @param cellSize  The size of each cell of the world, typically 1.
     * @param bounded   Whether or not the world has boundaries or not.
     */
    public AllWorld(int width, int height, int cellSize, boolean bounded)
    {    
        super(width, height, cellSize, bounded);
        // SoundManager.initSounds();
    }
    
    /**
     * Play music using the sound manager.
     */
    public void started() {
        sm.resumeSounds();
    }
    
    /**
     * Stop music using the sound manager.
     */
    public void stopped() {
        sm.pauseSounds();
    }
    
    /**
     * Gets an instance of SoundManager.
     * 
     * @return SoundManager The instance of SoundManager.
     */
    public SoundManager getSM() {
        return sm;
    }
}
