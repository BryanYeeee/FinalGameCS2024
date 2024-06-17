import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Intro to the game.
 * 
 * @author Jamison H
 * @version June 2024
 */
public class IntroWorld extends AllWorld
{
    private SuperTextBox start;
    private String[] guide = {"Use the cursor to move", "Click this to start"};
    private boolean play = true;
    
    /**
     * Constructor of IntroWorld.
     */
    public IntroWorld()
    {
        super(AllWorld.WORLD_WIDTH, AllWorld.WORLD_HEIGHT, 1,true);
        SimulationFont.initalizeFont("BigSpace.ttf");
        setBackground(new GreenfootImage("TitleScreen.png"));
        start = new SuperTextBox(guide, bgColor, Color.BLACK, SimulationFont.loadCustomFont("BigSpace.ttf", 45), true, 550, 10, Color.BLACK);
        addObject(start, AllWorld.WORLD_WIDTH/2, 690); 
    }

    /**
     * The act method, play sound and swap worlds once user clicks on the button.
     */
    public void act(){
        if(play){
            sm.playSound("TitleMusic");
            play = false;
        }
        if(Greenfoot.mouseClicked(start)){
            sm.playSound("Click");
            sm.stopSoundLoop("TitleMusic");
            Greenfoot.setWorld(new MyWorld());
        }
    }
}
