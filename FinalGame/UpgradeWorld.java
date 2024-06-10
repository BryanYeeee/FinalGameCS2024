import greenfoot.*;

/**
  * Base code of copying prev. world's background taken from danpost's Pause World class - https://www.greenfoot.org/scenarios/7314
  */
public class UpgradeWorld extends AllWorld
{
    private MyWorld mainWorld;
    private int currLevel;
    private Character character;
    
    // Background
    private GreenfootImage background; //new GreenfootImage("upgrade-world-background.jpg");
    // Font
    //private greenfoot.Font gameFont;
    
    // Color 
    private Color bgColor = new Color(119, 136, 153);
    private Color borderColor = new Color(192, 192, 192);
    private Color transparentColor = new Color(0, 0, 0, 0);
    private Color textColor = new Color(250, 249, 246);
    
    // Text
    private SuperTextBox bruteInfo = new SuperTextBox("Choose an upgrade:", bgColor, textColor, SimulationFont.loadCustomFont("BigSpace.ttf", 20), true, 150, 5, borderColor);
    /**
     * Creates a background image of the current visual state of the given world.
     *
     * @param world the given world whose visual state is to be duplicated in the background of this world
     * @param actorClasses an array of all actor classes in the given world
     * @param paintOrder an array of actor classes specifying the paint order in the given world
     */
    public UpgradeWorld(MyWorld world, int level, Character c){    
        super(AllWorld.WORLD_WIDTH, AllWorld.WORLD_HEIGHT, 1);
        // copies the background of the previous world
        // draw the images of all non-paint order objects on the background of the new world
        for(Entity e: world.getObjects(Entity.class))
        {
            e.fade();
            drawActorImage(e);
        }
        mainWorld = world;
        currLevel = level;
        character = c;
        character.increaseLevel();
        /*
        background = new GreenfootImage(world.getBackground());
        background.setTransparency(200);
        setBackground(background); 
        */
        //background.scale(AllWorld.WORLD_WIDTH, AllWorld.WORLD_HEIGHT);
        //setBackground(background);
        SimulationFont.initalizeFont("BigSpace.ttf");
    }
     
    public void act(){
        if (Greenfoot.mouseClicked(this)) Greenfoot.setWorld(mainWorld);
        //displayUpgrades();
    }
    
    /**
     * Draws the image of the given actor onto the background image of this world at the same location as it appears in its world
     * 
     * @param actor the actor whose image is to be drawn
     */
    private void drawActorImage(Entity actor)
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
    // depending on the character's level, give different upgrades?
    private void displayUpgrades(){
        switch(currLevel){
            case 0:
                
        }
    }
}
