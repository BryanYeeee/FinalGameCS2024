import greenfoot.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Base code of copying prev. world's background taken from danpost's Pause World class - https://www.greenfoot.org/scenarios/7314
 */
public class UpgradeWorld extends AllWorld
{
    private MyWorld mainWorld;
    private int currLevel;
    private Player player;

    // Background
    private GreenfootImage background;
    // Font
    //private greenfoot.Font gameFont;
    // Text
    private SuperTextBox upgradeNotice = new SuperTextBox("CHOOSE AN UPGRADE", bgColor, Color.BLACK, SimulationFont.loadCustomFont("BigSpace.ttf", 75), true, 750, 10, Color.BLACK);
    private SuperTextBox select0 = new SuperTextBox("SELECT", bgColor, Color.BLACK, SimulationFont.loadCustomFont("BigSpace.ttf", 75), true, 750, 10, Color.BLACK);
    private SuperTextBox select1 = new SuperTextBox("SELECT", bgColor, Color.BLACK, SimulationFont.loadCustomFont("BigSpace.ttf", 75), true, 750, 10, Color.BLACK);
    private SuperTextBox select2 = new SuperTextBox("SELECT", bgColor, Color.BLACK, SimulationFont.loadCustomFont("BigSpace.ttf", 75), true, 750, 10, Color.BLACK);

    
    // Temp Boxes
    private TempBox blur;
    private TempBox border0 = new TempBox(310, 410, transparentColor, borderColor, 10);
    private TempBox border1 = new TempBox(310, 410, transparentColor, borderColor, 10);
    private TempBox border2 = new TempBox(310, 410, transparentColor, borderColor, 10);

    // Upgrades
    private ArrayList<UpgradeBox> upgrades = new ArrayList<UpgradeBox>();
    private ArrayList<String> repeatUpgrades = new ArrayList<>(Arrays.asList("HP BUFF", "ATK BUFF", "SPD BUFF", "MAGNET", "EXP MASTERY"));
    private UpgradeBox[] currUpgrades = new UpgradeBox[3];
    private static ArrayList<String> removedUpgrades = new ArrayList<String>();
    
    /**
     * Creates a background image of the current visual state of the given world.
     *
     * @param world the given world whose visual state is to be duplicated in the background of this world
     */
    public UpgradeWorld(MyWorld world, int level, Player p){    
        super(AllWorld.WORLD_WIDTH, AllWorld.WORLD_HEIGHT, 1,true);
        SimulationFont.initalizeFont("BigSpace.ttf");
        // copies the background of the previous world
        for(SuperSmoothMover s: world.getObjects(SuperSmoothMover.class))
        {
            if(s instanceof EntitySprite){
                break;
            } else {
                drawActorImage(s);
            }
        }
        mainWorld = world;
        currLevel = level;
        player = p;
        player.increaseLevel();
        // add blur
        blur = new TempBox(AllWorld.WORLD_WIDTH, AllWorld.WORLD_HEIGHT, bgColor);
        blur.getImage().setTransparency(100);
        addObject(blur, AllWorld.WORLD_WIDTH/2, AllWorld.WORLD_HEIGHT/2);
        // add notice text 
        addObject(upgradeNotice, AllWorld.WORLD_WIDTH/2, 100);
        // do upgrade things
        initalizeUpgrades();
        determineUpgrades();
        displayUpgrades();
    }

    public void act(){
        if(Greenfoot.mouseClicked(border0)){
            buff(currUpgrades[0]);
            switchWorld();
        }
        if(Greenfoot.mouseClicked(border1)){
            buff(currUpgrades[1]);
            switchWorld();
        }
        if(Greenfoot.mouseClicked(border2)){
            buff(currUpgrades[2]);
            switchWorld();
        }
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
        if(actor.getX() < AllWorld.WORLD_WIDTH/2){
            actor.setRotation(0);
        } else {
            actor.setRotation(180);
        }
        GreenfootImage actorImg = actor.getImage();
        int w = actorImg.getWidth();
        int h = actorImg.getHeight();
        int max = Math.max(w, h);
        GreenfootImage image = new GreenfootImage(max*2, max*2);
        image.drawImage(actorImg, max-actorImg.getWidth()/2, max-actorImg.getHeight()/2);
        image.rotate(actor.getRotation());
        return image;
    }

    private void initalizeUpgrades(){
        upgrades.add(new UpgradeBox("magnet.png", "MAGNET", new String[] {"Increases EXP", "pickup range by 1."}));
        upgrades.add(new UpgradeBox("XPPotion.png", "EXP MASTERY", new String[] {"Increases EXP", "gain by 30%."}));
        upgrades.add(new UpgradeBox("HPBoost.png", "HP BUFF", new String[] {"Increases HP by 20"}));
        upgrades.add(new UpgradeBox("ATKBoost.png", "ATK BUFF", new String[] {"Increases ATK by 5"}));
        upgrades.add(new UpgradeBox("SPDBoost.png", "SPD BUFF", new String[] {"Increases SPD by 1"}));
        upgrades.add(new UpgradeBox("images/Attacks/Trident/Trident1.png", "TRIDENT", new String[] {"Obtain the weapon", "of Poseidon."}));
        upgrades.add(new UpgradeBox("images/Attacks/SlashSpecial/SlashSpecial1.png", "FLAME", new String[] {"Obtain the power", "of the blue flame."}));
        upgrades.add(new UpgradeBox("images/Attacks/WaterSplash/WaterSplash1.png", "WATER", new String[] {"Harness the power", "of the seas."}));

        ArrayList<UpgradeBox> removeableUpgrades = new ArrayList<UpgradeBox>();
        System.out.println(removedUpgrades);
        for(String s : removedUpgrades){
            for(UpgradeBox u : upgrades){
                if(u.getName() == s){
                    removeableUpgrades.add(u);
                }
            }
        }
        upgrades.removeAll(removeableUpgrades);
        for(int j = 0; j < upgrades.size(); j++){
            System.out.println(upgrades.get(j).getName());
        }
        System.out.println("=====");
    }
    // logic will become more complex, say if user as this upgrade they only show this upgrade, for now simple filling
    private void determineUpgrades(){
        for(int i = 0; i < 3; i++){
            int randNum =  Greenfoot.getRandomNumber(upgrades.size());
            currUpgrades[i] = upgrades.get(randNum);
            upgrades.remove(randNum); // remove the upgrade from the list so there isn't a duplicate
            switch (i){
                case 0:
                    currUpgrades[i].giveCoords(180, 500);
                    break;
                case 1:
                    currUpgrades[i].giveCoords(AllWorld.WORLD_WIDTH/2, 500);
                    break;
                case 2:
                    currUpgrades[i].giveCoords(AllWorld.WORLD_WIDTH-180, 500);
                    break;
            }
        }
        // For non-one time upgrades, add them back in the upgrades list
        for(UpgradeBox u : currUpgrades){
            removedUpgrades.add(u.getName());
            if(repeatUpgrades.contains(u.getName())){
                upgrades.add(u);
                removedUpgrades.remove(u.getName());
            }
        }
    }

    private void displayUpgrades(){
        // add the border last so anywhere in the box the user clicks, it will register
        addObject(currUpgrades[0], 180, 500);
        currUpgrades[0].addToWorld(this);
        addObject(border0, 180, 500);
        addObject(currUpgrades[1], AllWorld.WORLD_WIDTH/2, 500);
        currUpgrades[1].addToWorld(this);
        addObject(border1, AllWorld.WORLD_WIDTH/2, 500);
        addObject(currUpgrades[2], AllWorld.WORLD_WIDTH-180, 500);
        currUpgrades[2].addToWorld(this);
        addObject(border2, AllWorld.WORLD_WIDTH-180, 500);
    }
    // actually buff stats here
    private void buff(UpgradeBox u){
        switch(u.getName()){
            case "ATK BUFF":
                mainWorld.getPlayer().increaseATK(5);
                break;
            case "SPD BUFF":
                mainWorld.getPlayer().increaseSPD(1);
                break;
            case "HP BUFF":
                mainWorld.getPlayer().increaseHP(20);
                break;
            case "MAGNET":
                mainWorld.getPlayer().increasePickUpRange(20);
                break;
            case "EXP MASTERY":
                mainWorld.getPlayer().increaseEXPBuff(1);
                break;
            case "TRIDENT":
                mainWorld.getGun().addAttack("Trident");
                break;
            case "FLAME":
                mainWorld.getGun().addAttack("Flame");
                break;
            case "WATER":
                mainWorld.getGun().addAttack("Water");
                break;
        }
    }
    
    public static void resetUpgrades(){
        removedUpgrades = new ArrayList<String>();
    }
}
