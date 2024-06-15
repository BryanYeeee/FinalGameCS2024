import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.HashMap;
/**
 * MyWorld is the main game world.
 * 
 * @author Bryan Y, Jamison H, Ainson Z 
 * @version (a version number or a date)
 */
public class MyWorld extends AllWorld
{
    private Player p;
    private Gun g;
    private int speed;
    private int randX;
    private int randY;
    private int hordeCount;
    private int hordeLimit; // the limit of how many horde enemys can exist at once
    private int actCount = 0;

    private int gameState; // 0 = ingame, 1 = game ended, 2 = full exit

    private Map map;
    private Viewport vp;
    private SuperStatBar xp;

    // key = level, returns upgrade req
    private HashMap<Integer, Integer> upgradeReq = new HashMap<Integer, Integer>();

    private SuperTextBox score = new SuperTextBox("0", bgColor, Color.BLACK, SimulationFont.loadCustomFont("BigSpace.ttf", 50), true, 100, 5, Color.BLACK);

    /**
     * Constructor for objects of class MyWorld.
     */
    public MyWorld()
    {    
        // Call the superclass constructor with the constants
        super(AllWorld.WORLD_WIDTH, AllWorld.WORLD_HEIGHT, 1,false);
        SimulationFont.initalizeFont("BigSpace.ttf");
        setPaintOrder(SuperTextBox.class,TempBox.class,SuperStatBar.class,Player.class,Attack.class,Enemy.class,Gun.class,Bullet.class,Collectible.class,Label.class,Tile.class);
        p = new Player(this);
        g = new Gun();
        addObject(p, AllWorld.WORLD_WIDTH/2, AllWorld.WORLD_HEIGHT/2);
        addObject(g, AllWorld.WORLD_WIDTH/2+30, AllWorld.WORLD_HEIGHT/2);
        hordeLimit = 30;
        gameState = 0;
        speed = 2;
        ScoreTracker.resetScore();
        Sprite.init();
        map = new Map();
        vp = new Viewport(this);
        xp = new SuperStatBar(p.getRequiredXPForNextLevel(), p.getXP(), null, 400, 50, 0, Color.GREEN, Color.DARK_GRAY);
        addObject(xp, 225, 50);
        
        //Change values for actual game
        upgradeReq.put(0, 0);
        upgradeReq.put(1, 15);
        upgradeReq.put(2, 25);
        upgradeReq.put(3, 35);
        upgradeReq.put(4, 45);
        upgradeReq.put(5, 55);
        upgradeReq.put(6, 65);
        upgradeReq.put(7, 75);
        upgradeReq.put(8, 85);
        upgradeReq.put(9, 95);
        upgradeReq.put(10, 105);
        
        // addObject(new Tile("",false,10),-55,675);
        // addObject(new Tile("",false,10),0,500);
    }
    
    /**
     * Updates the ViewPort (scrolling map) depending on the x and y direction required
     * 
     * @param xMove How far the map scrolls to the left.
     * @param yMove How far the map scrolls to the right.
     */

    public void updateVP(int xMove, int yMove) {
        vp.move(xMove, yMove);
    }

    /**
     * The act method of the game world. Defeat enemies, add and display score, end the game.
     */
    public void act(){
        if(gameState == 0){
            ArrayList<BasicHorde> horde = (ArrayList<BasicHorde>)getObjects(BasicHorde.class);
            if(horde.size() < hordeLimit){
                addHorde();
            }
            int currScore = ScoreTracker.getScore();
            if(score.getWorld() != null){
                removeObject(score);
            }
            // size of the box that hold the score depends on the # of digits
            if(currScore < 1000){
                score = new SuperTextBox(Integer.toString(currScore), bgColor, Color.BLACK, SimulationFont.loadCustomFont("BigSpace.ttf", 50), true, 100, 5, Color.BLACK);
                addObject(score, 900, 100);
            } else if(currScore >= 1000){
                score = new SuperTextBox(Integer.toString(currScore), bgColor, Color.BLACK, SimulationFont.loadCustomFont("BigSpace.ttf", 50), true, 140, 5, Color.BLACK);
                addObject(score, 900, 100);
            } else if (currScore >= 10000){
                score = new SuperTextBox(Integer.toString(currScore), bgColor, Color.BLACK, SimulationFont.loadCustomFont("BigSpace.ttf", 50), true, 180, 5, Color.BLACK);
                addObject(score, 900, 100);            
            }
            if(p.getHP() <= 0){ // GAME OVER
                gameState = 1;
                return;
            }
            determineLevel();
        } else if(gameState == 1) {
            ArrayList<Actor> actors = (ArrayList<Actor>)getObjects(Actor.class);
            for(Actor a : actors){
                removeObject(a);
            }
            removeObject(p);
            addObject(new TempBox(AllWorld.WORLD_WIDTH, AllWorld.WORLD_HEIGHT, bgColor), AllWorld.WORLD_WIDTH/2, AllWorld.WORLD_HEIGHT/2);
            addObject(new SuperTextBox("GAME OVER", bgColor, Color.BLACK, SimulationFont.loadCustomFont("BigSpace.ttf", 150), true, 750, 0, Color.BLACK), AllWorld.WORLD_WIDTH/2, AllWorld.WORLD_HEIGHT/2);
            ScoreTracker.readScore();
            System.out.println(ScoreTracker.getScore());
            System.out.println(ScoreTracker.getHighScore());
            ScoreTracker.determineHigh();
            ScoreTracker.writeScore();
            gameState = 2;
            return;
        }
    }
    
    /**
     * Gets the player that resides in the game world
     * 
     * @return Player   The player in the game world.
     */
    public Player getPlayer(){
        return p;
    }
    
    public void updateXPBar() {
        int maxXP = p.getRequiredXPForNextLevel();
        xp.setMaxVal(maxXP);
        xp.update(p.getXP());
    }

    /**
     * Gets the gun/weapon residing in the game world.
     * 
     * @return Gun  The gun/weapon in the game world.
     */
    public Gun getGun(){
        return g;
    }
    
    public void determineLevel(){
        if(p.getXP() >= p.getRequiredXPForNextLevel()){
            Greenfoot.setWorld(new UpgradeWorld(this, p.getLevel(), p));
        }
    }
    
    private void addHorde(){
        if(Greenfoot.getRandomNumber(2) == 0){
            if(Greenfoot.getRandomNumber(2) == 0){
                randX = 0;
            } else {
                randX = AllWorld.WORLD_WIDTH;
            }
            randY = Greenfoot.getRandomNumber(AllWorld.WORLD_HEIGHT-50) + 25; 
            addObject(new BasicHorde(), randX, randY);
        } else {
            randX = Greenfoot.getRandomNumber(AllWorld.WORLD_WIDTH-50) + 25;
            if(Greenfoot.getRandomNumber(2) == 0){
                randY = 0;
            } else {
                randY = AllWorld.WORLD_HEIGHT;
            }
            addObject(new BasicHorde(), randX, randY);
        }
    }
    /**
     * Gets the tiles used in the background map.
     * 
     * @return Tile[][] The tiles of the map.
     */
    public Tile[][] getMap() {
        return map.getTileMap();
    }

    /*
    public void debug() {
        System.out.println("lx: " + vp.lx());
        System.out.println("ly: " + vp.ly());
        System.out.println("rx: " + vp.rx());
        System.out.println("ry: " + vp.ry());
    }
    */
}
