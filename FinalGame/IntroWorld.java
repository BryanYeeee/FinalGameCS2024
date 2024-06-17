import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class IntroWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IntroWorld extends AllWorld
{
    private SuperTextBox start;
    private String[] guide = {"Use the cursor to move", "Click this to start"};
    private boolean play = true;
    public IntroWorld()
    {
        super(AllWorld.WORLD_WIDTH, AllWorld.WORLD_HEIGHT, 1,true);
        setBackground(new GreenfootImage("TitleScreen.png"));
        start = new SuperTextBox(guide, bgColor, Color.BLACK, SimulationFont.loadCustomFont("BigSpace.ttf", 45), true, 550, 10, Color.BLACK);
        addObject(start, AllWorld.WORLD_WIDTH/2, 690); 
    }
    
    public void act(){
        if(play){
            sm.playSound("GameBGM");
            play = false;
        }
        if(Greenfoot.mouseClicked(start)){
            sm.playSound("Click");
            sm.stopSoundLoop("GameBGM");
            Greenfoot.setWorld(new MyWorld());
        }
    }
}
