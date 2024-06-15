import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A box to contain a panel for a character upgrade.
 * Contains name, image, description.
 * 
 * @author Jamison H.
 * @version June 2024
 */
public class UpgradeBox extends Actor
{
    private GreenfootImage background = new GreenfootImage("upgrade-box.jpg");
    private GreenfootImage upgradeImage;
    
    private SuperTextBox upgradeNameBox;
    private SuperTextBox upgradeDescriptionBox;
    
    private String upgradeName;
    
    // Color 
    private Color bgColor = new Color(119, 136, 153);
    private Color borderColor = new Color(192, 192, 192);
    private Color transparentColor = new Color(0, 0, 0, 0);
    private Color textColor = new Color(250, 249, 246);
    
    // Coordinates
    private int xCoord = 0;
    private int yCoord = 0;
    
    /**
     * Constructor for Upgrade Box.
     * Gets a desired location to place texts and images.
     * 
     * @param imagePath     The file path of the upgrade's image
     * @param name          The name of the upgrade
     * @param description   The description of the upgrade
     */
    public UpgradeBox(String imagePath, String name, String[] description){
        upgradeImage = new GreenfootImage(imagePath);
        upgradeName = name;
        upgradeNameBox = new SuperTextBox(name, transparentColor, textColor, SimulationFont.loadCustomFont("BigSpace.ttf", 30), true, 200, 5, borderColor);
        upgradeDescriptionBox = new SuperTextBox(description, transparentColor, textColor, SimulationFont.loadCustomFont("BigSpace.ttf", 22), true, 200, 5, borderColor);
        background.scale(300, 400);
        setImage(background);
    }
    
    /**
     * Obtain given coords for my placement in the world.
     * 
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public void giveCoords(int x, int y){
        xCoord = x;
        yCoord = y;
    }
    
    /**
     * Display the box in the given world.
     * 
     * @param w The world to be displayed in.
     */
    public void addToWorld(UpgradeWorld w){
        // Add text to the world depending on my x and y coords
        w.addObject(upgradeNameBox, xCoord, yCoord-150);
        w.addObject(upgradeDescriptionBox, xCoord, yCoord + 40);
        w.addObject(new TempBox(70, 70, transparentColor,borderColor,5), xCoord, yCoord-50);
        w.addObject(new UpgradeImage(upgradeImage), xCoord, yCoord - 50);
    }
    
    /**
     * Get the name of my upgrade.
     * 
     * @return String   The name of my upgrade.
     */
    public String getName(){
        return upgradeName;
    }
}
