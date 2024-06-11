import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A box to contain a panel for a character upgrade. <p>
 * Contains name, image, description
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UpgradeBox extends Actor
{
    private GreenfootImage background = new GreenfootImage("upgrade-box.jpg");
    private GreenfootImage upgradeImage;
    private SuperTextBox upgradeName;
    private SuperTextBox upgradeDescription;
    
    // Color 
    private Color bgColor = new Color(119, 136, 153);
    private Color borderColor = new Color(192, 192, 192);
    private Color transparentColor = new Color(0, 0, 0, 0);
    private Color textColor = new Color(250, 249, 246);
    
    // Coordinates
    private int xCoord;
    private int yCoord;
    /**
     * Constructor for Upgrade Box
     * Gets a desired location to place texts and images
     * 
     * @param imagePath     The file path of the upgrade's image
     * @param name          The name of the upgrade
     * @param description   The description of the upgrade
     * @param x             The x-coordinate of the box
     * @param y             The y-coordinate of the box
     */
    public UpgradeBox(String imagePath, String name, String description, int x, int y){
        //upgradeImage = new GreenfootImage(imagePath);
        //upgradeName = new SuperTextBox(name, bgColor, textColor, SimulationFont.loadCustomFont("BigSpace.ttf", 20), true, 150, 5, borderColor);
        //upgradeDescription = new SuperTextBox(description, bgColor, textColor, SimulationFont.loadCustomFont("BigSpace.ttf", 20), true, 150, 5, borderColor);
        //xCoord = x;
        //yCoord = y;
        background.scale(350, 450);
        setImage(background);
    }
    public void act()
    {
        // Add your action code here.
    }
}
