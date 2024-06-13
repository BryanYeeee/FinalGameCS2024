import greenfoot.*;
import java.util.ArrayList;

/**
 * Base code of copying prev. world's background taken from danpost's Pause World class - https://www.greenfoot.org/scenarios/7314
 */
public class UpgradeWorld extends AllWorld
{
    private MyWorld mainWorld;
    private int currLevel;
    private Character character;

    // Background
    private GreenfootImage background;
    // Font
    //private greenfoot.Font gameFont;
    // Text
    private SuperTextBox upgradeNotice = new SuperTextBox("CHOOSE AN UPGRADE", bgColor, Color.BLACK, SimulationFont.loadCustomFont("BigSpace.ttf", 75), true, 750, 10, Color.BLACK);
    // Temp Boxes
    TempBox blur;
    TempBox border0 = new TempBox(310, 410, transparentColor, borderColor, 10);
    TempBox border1 = new TempBox(310, 410, transparentColor, borderColor, 10);
    TempBox border2 = new TempBox(310, 410, transparentColor, borderColor, 10);

    // Upgrades
    ArrayList<UpgradeBox> upgrades = new ArrayList<UpgradeBox>();
    UpgradeBox[] currUpgrades = new UpgradeBox[3];
    /**
     * Creates a background image of the current visual state of the given world.
     *
     * @param world the given world whose visual state is to be duplicated in the background of this world
     */
    public UpgradeWorld(MyWorld world, int level, Character c){    
        super(AllWorld.WORLD_WIDTH, AllWorld.WORLD_HEIGHT, 1,true);
        SimulationFont.initalizeFont("BigSpace.ttf");
        // copies the background of the previous world
        // draw the images of all non-paint order objects on the background of the new world
        for(SuperSmoothMover s: world.getObjects(SuperSmoothMover.class))
        {
            drawActorImage(s);
        }
        mainWorld = world;
        currLevel = level;
        character = c;
        character.increaseLevel();
        // add blur
        blur = new TempBox(AllWorld.WORLD_WIDTH, AllWorld.WORLD_HEIGHT, bgColor);
        blur.getImage().setTransparency(100);
        addObject(blur, AllWorld.WORLD_WIDTH/2, AllWorld.WORLD_HEIGHT/2);
        // add notice text 
        addObject(upgradeNotice, AllWorld.WORLD_WIDTH/2, 100);
        // do upgrade things
        addUpgrades();
        determineUpgrades();
    }

    public void act(){
        if(Greenfoot.mouseClicked(currUpgrades[0])){
            buff(currUpgrades[0]);
            switchWorld();
        }
        if(Greenfoot.mouseClicked(currUpgrades[1])){
            buff(currUpgrades[1]);
            switchWorld();
        }
        if(Greenfoot.mouseClicked(currUpgrades[2])){
            buff(currUpgrades[2]);
            switchWorld();
        }
        displayUpgrades();
    }

    private void switchWorld(){
        for(SuperSmoothMover s: mainWorld.getObjects(SuperSmoothMover.class))
        {
            drawActorImage(s);
        }
        Greenfoot.setWorld(mainWorld);  
    }

    /**
     * Draws the image of the given actor onto the background image of this world at the same location as it appears in its world
     * 
     * @param actor the actor whose image is to be drawn
     */
    private void drawActorImage(SuperSmoothMover actor)
    {
        int x=actor.getX()*actor.getWorld().getCellSize()+actor.getWorld().getCellSize()/2;
        int y=actor.getY()*actor.getWorld().getCellSize()+actor.getWorld().getCellSize()/2; // the location of the actor in this world
        GreenfootImage img = getActorImage(actor); // the image of the actor
        int w=img.getWidth(), h=img.getHeight(); // the world dimensions
        getBackground().drawImage(img, x-w/2, y-h/2); // properly draws the image onto the given world's background image
    }

    /**
     * Gets an image that represents the current visual state of the image of an actor
     *
     * @param actor the actor whose current visual image state is to be represented in an image object
     * @return the image the represents the current visual state of the image of an actor
     */
    private GreenfootImage getActorImage(Actor actor)
    {
        GreenfootImage actorImg = actor.getImage();
        int w = actorImg.getWidth();
        int h = actorImg.getHeight();
        int max = Math.max(w, h);
        GreenfootImage image = new GreenfootImage(max*2, max*2);
        image.drawImage(actorImg, max-actorImg.getWidth()/2, max-actorImg.getHeight()/2);
        image.rotate(actor.getRotation());
        return image;
    }

    private void addUpgrades(){
        upgrades.add(new UpgradeBox("magnet.png", "MAGNET", "Increases EXP pickup range by 1"));
        upgrades.add(new UpgradeBox("XPPotion.png", "EXP MASTERY", "Increases EXP gain by 30%"));
        upgrades.add(new UpgradeBox("HPBoost.png", "HP BUFF", "Increases HP by 20"));
        upgrades.add(new UpgradeBox("a", "TRIDENT", "Obtain the trident weapon."));
    }
    // logic will become more complex, say if user as this upgrade they only show this upgrade, for now simple filling
    private void determineUpgrades(){
        for(int i = 0; i < 3; i++){
            currUpgrades[i] = upgrades.get(i);
        }
    }
    // depending on the character's level, give different upgrades?
    private void displayUpgrades(){
        addObject(border0, 180, 500);
        addObject(currUpgrades[0], 180, 500);
        addObject(border1, AllWorld.WORLD_WIDTH/2, 500);
        addObject(currUpgrades[1], AllWorld.WORLD_WIDTH/2, 500);
        addObject(border2, AllWorld.WORLD_WIDTH-180, 500);
        addObject(currUpgrades[2], AllWorld.WORLD_WIDTH-180, 500);
    }
    // actually buff stats here
    private void buff(UpgradeBox u){
        switch(u.getName()){
            case "ATK":
                mainWorld.getCharacter().increaseATK(5);
            case "SPD":
                mainWorld.getCharacter().increaseSPD(1);
            case "HP":
                mainWorld.getCharacter().increaseHP(20);
        }
    }
}
