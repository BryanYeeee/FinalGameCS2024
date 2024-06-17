import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * MyWorld is the main game world. <p>
 * This game is heavily based off of survivor.io and holocure, and mainly features a scrolling map, weapon tiers and upgrades. <p>
 * Assets:<p>
 * All attacks and sounds are from Holocure.<p>
 * Gun - https://survivorio.fandom.com/wiki/Shotgun <p>
 * Bullet - https://www.shutterstock.com/image-vector/cartoon-game-objects-2d-art-object-656359261 <p>
 * BasicHorde - https://gamerant.com/survivorio-codes/ <p>
 * Character - https://penzilla.itch.io/hooded-protagonist <p>
 * Font - https://www.fontspace.com/big-space-font-f32275 <p>
 * UpgradeBox Background - https://www.vecteezy.com/vector-art/2884046-abstract-futuristic-black-and-red-gaming-background-with-black-color-dynamic-overlap-on-metal-abstract-background-vector-illustration?autodl_token=f341dbd66ecbf4a98c5e5438aeed250c4ed30a0d4f43e1fd12a4a3387cdf8e57454a4c68bc9c56b6b0f679e4adbd574aaafc722e796be432c7accb3629bd123c <p>
 * Yellow XP orb - https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.youtube.com%2Fwatch%3Fv%3DCiGAiBIwJoc&psig=AOvVaw2nFllnWrdKzrfGJcHuYG1a&ust=1718196362810000&source=images&cd=vfe&opi=89978449&ved=0CBIQjhxqFwoTCOjyscbK04YDFQAAAAAdAAAAABAE <p>
 * Red XP orb - https://minecraft.novaskin.me/skin/6315693033/Red-Experience-Orb <p>
 * Magnet - https://www.vecteezy.com/vector-art/6563728-cartoon-magnet-vector-illustration <p>
 * EXP Gain - https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.planetminecraft.com%2Fblog%2Fwhy-do-we-smash-xp-bottles%2F&psig=AOvVaw1iudoeggKI56L8S5hio4M0&ust=1718368042939000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCLCW1JXK2IYDFQAAAAAdAAAAABAE  <p>
 * Health Boost - https://minecraft.fandom.com/wiki/Health_Boost <p>
 * ATK boost - https://stock.adobe.com/ca/images/crossed-swords-icon-combat-with-melee-weapons-flat-vector-illustration/523395253 <p>
 * Spd boost -https://www.flaticon.com/free-icon/magic-boot_1643170 <p>
 * 
 * 
 * @author Bryan Y, Jamison H, Ainson Z 
 * @version June 2024
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
    
    private SuperStatBar playerBar;
    
    private SuperTextBox score = new SuperTextBox("0", bgColor, Color.BLACK, SimulationFont.loadCustomFont("BigSpace.ttf", 50), true, 100, 5, Color.BLACK);
    private SuperTextBox restart = new SuperTextBox("RESTART", bgColor, Color.BLACK, SimulationFont.loadCustomFont("BigSpace.ttf", 50), true, 250, 5, Color.BLACK);

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
        hordeLimit = 20;
        gameState = 0;
        speed = 2;
        ScoreTracker.resetScore();
        Sprite.init();
        map = new Map();
        vp = new Viewport(this);
        xp = new SuperStatBar(p.getRequiredXPForNextLevel(), p.getXP(), null, 400, 50, 0, Color.GREEN, Color.DARK_GRAY);
        addObject(xp, 225, 50);
        playerBar = new SuperStatBar(p.getMaxHP(), p.getHP(), p, 50, 15, 40, Color.GREEN, Color.RED, false, Color.BLACK, 2);
        addObject(playerBar, p.getPlayerX(), p.getPlayerY());
        
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
        actCount++;
        if(actCount == 1) {
            sm.playSound("GameBGM");
        }
        if(actCount % 600 == 0 && hordeLimit < 250){ // raise limit every 10 seconds
            hordeLimit += 10;
        }
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
            sm.stopSoundLoop("GameBGM");
            sm.playSound("Gameover");
            addObject(new SuperTextBox("GAME OVER", bgColor, Color.BLACK, SimulationFont.loadCustomFont("BigSpace.ttf", 150), true, 750, 0, Color.BLACK), AllWorld.WORLD_WIDTH/2, AllWorld.WORLD_HEIGHT/2);
            addObject(restart, AllWorld.WORLD_WIDTH/2, 650);
            ScoreTracker.readScore();
            System.out.println(ScoreTracker.getScore());
            System.out.println(ScoreTracker.getHighScore());
            ScoreTracker.determineHigh();
            ScoreTracker.writeScore();
            gameState = 2;
            return;
        } else if (gameState == 2){
            addObject(new SuperTextBox("Your score: " + ScoreTracker.getScore(), transparentColor, Color.BLACK, SimulationFont.loadCustomFont("BigSpace.ttf", 50), true, 500, 0, Color.BLACK), AllWorld.WORLD_WIDTH/2, AllWorld.WORLD_HEIGHT/2 + 100);
            addObject(new SuperTextBox("High score: " + ScoreTracker.getHighScore(), transparentColor, Color.BLACK, SimulationFont.loadCustomFont("BigSpace.ttf", 50), true, 500, 0, Color.BLACK), AllWorld.WORLD_WIDTH/2, AllWorld.WORLD_HEIGHT/2 + 150);

            if(Greenfoot.mouseClicked(restart)){
                sm.playSound("Click");
                Greenfoot.setWorld(new IntroWorld());
            }
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
    
    /**
     * Updates the XP bar according to the player's level.
     */
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
    
    /**
     * Determine if the player is qualified for an upgrade.
     */
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
    
    /**
     * Updates the player's HP bar.
     */
    public void updateBar(){
        playerBar.setMaxVal(p.getMaxHP());
        playerBar.update(p.getHP());
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
