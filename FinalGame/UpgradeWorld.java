import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PauseWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UpgradeWorld extends AllWorld
{
    private MyWorld mainWorld;
    private int currLevel;
    private Character character;
    
    // Background
    private GreenfootImage background = new GreenfootImage("upgrade-world-background.jpg");
    // Font
    //private greenfoot.Font gameFont;
    
    // Color 
    private Color bgColor = new Color(119, 136, 153);
    private Color borderColor = new Color(192, 192, 192);
    private Color transparentColor = new Color(0, 0, 0, 0);
    private Color textColor = new Color(250, 249, 246);
    
    // Text
    private SuperTextBox bruteInfo = new SuperTextBox("Choose an upgrade:", bgColor, textColor, SimulationFont.loadCustomFont("BigSpace.ttf", 20), true, 150, 5, borderColor);
     
    public UpgradeWorld(MyWorld world, int level, Character c)
    {
        super(AllWorld.WORLD_WIDTH, AllWorld.WORLD_HEIGHT, 1);
        mainWorld = world;
        currLevel = level;
        character = c;
        character.increaseLevel();
        background.scale(AllWorld.WORLD_WIDTH, AllWorld.WORLD_HEIGHT);
        setBackground(background);
        SimulationFont.initalizeFont("BigSpace.ttf");
    }
     
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) Greenfoot.setWorld(mainWorld);
        displayUpgrades();
    }
    
    private void displayUpgrades(){
        switch(currLevel){
            case 0:
                
        }
    }
}
