import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Entity holds all traits and behaviours of enemies and player objects.
 * 
 * @author Bryan Y, Jeff G
 * @version June 2024
 */
public abstract class Entity extends SuperSmoothMover
{
    //protected EntitySprite sprite;
    protected int speed;
    protected int hp;
    protected int atk;
    protected MyWorld world;
    private int actCount = 0;

    protected String action;

    //Animation Variables
    protected int animationDelay=1;
    protected int animationLength=1;
    protected int imageIndex;
    protected String entityName;
    protected char dirChar;
    /**
     * Constructor of Entity.
     * 
     * @param hp    The entity's HP.
     * @param speed The entity's speed.
     * @param atk   The entity's ATK.
     */
    public Entity(int hp, int speed, int atk){
        this.hp = hp;
        this.speed = speed;
        this.atk = atk;
        enableStaticRotation();
        //sprite = new EntitySprite(this, imgURL);
    }
    
    /**
     * Once added to world, let me know what world it is.
     * 
     * @param w The world I am added to.
     */
    public void addedToWorld(World w) {
        this.world = (MyWorld)w;
        //w.addObject(sprite,0,0);
    }
    
    /**
     * The act method, set my rotation.
     */
    public void act(){
        actCount++;
        if(getRotation()>=90&&getRotation()<=270){
            dirChar='L';
        }
        else{
            dirChar='R';
        }
        animate();
    }
    
    /**
     * Animate my movement.
     */
    public void animate() {
        calculateAnimationTimes();
        //frames only change at intervals of animationDelay
        if (actCount % animationDelay != 0) {
            return;
        }
        
        //Preset animationLengths per action, and delay
        
        
        imageIndex = (imageIndex + 1) % animationLength;
        String key = entityName + "_" + action + "_" + dirChar + "_" + imageIndex;
        GreenfootImage currentImage = Sprite.getFrame(key);

        try {
            currentImage.scale(64, 64);
        }
        catch (NullPointerException e) {
            System.out.println(key);
        }

        setImage(currentImage);

    }
    
    /**
     * Have a different calculation for every entity.
     */
    public abstract void calculateAnimationTimes();

    /**
     * Set the transparency of my image.
     * 
     * @param amount    The transparency of my image.
     */
    public void setTransparency(int amount){
        getImage().setTransparency(amount);
    }

    /**
     * Set my name.
     * 
     * @param entityName    The entity's name.
     */
    public void setEntityName(String entityName){
        this.entityName = entityName;
    }
    
    /**
     * Set my action.
     * 
     * @param action    My action.
     */
    public void setAction(String action){
        this.action = action;
    }
    
    /**
     * Set the character relating to my direction.
     * 
     * @param dirChar   The directional character.
     */
    public void setDirChar(char dirChar){
        this.dirChar = dirChar;
    }
    
    /**
     * Set my animation length.
     * 
     * @param animationLength   The animation length.
     */
    public void setAnimationLength (int animationLength){
        this.animationLength = animationLength;
    }
    
    /**
     * Return the distance between myself and another (x,y) coordinate pair.
     * 
     * @param x         The other x coordinate.
     * @param y         The other y coordinate.
     * @return double   The distance between myself and another coordinate.
     */
    public double distanceFrom(int x, int y) {
        int deltaX = getX() - x; 
        int deltaY = getY() - y; 
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY); 
    }

    /**
     * Get my speed.
     * 
     * @return int  The speed.
     */
    public int getSPD(){
        return speed;
    }
    /**
     * Increase my speed by a certain amount.
     * 
     * @param amount  The amount to increase.
     */
    public void increaseSPD(int amount){
        speed += amount;
    }
    /**
     * Get my HP.
     * 
     * @return int  The HP.
     */
    public int getHP(){
        return hp;
    }
    /**
     * Increase my HP by a certain amount.
     * 
     * @param amount The amount to increase.
     */
    public void increaseHP(int amount){
        hp += amount;
    }
    /**
     * Decrease my HP by a certain amount.
     * 
     * @param amount The amount to decrease.
     */
    public void decreaseHP(int amount){
        hp -= amount;
    }
    /**
     * Get my ATK.
     * 
     * @return int  The ATK.
     */
    public int getATK(){
        return atk;
    }
    /**
     * Increase my ATK by a certain amount.
     * 
     * @param amount The amount to increase.
     */
    public void increaseATK(int amount){
        atk += amount;
    }
}
