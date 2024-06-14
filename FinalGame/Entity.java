import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Entity here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
    public Entity(int hp, int speed, int atk){
        this.hp = hp;
        this.speed = speed;
        this.atk = atk;
        enableStaticRotation();
        //sprite = new EntitySprite(this, imgURL);
    }
    
    public void addedToWorld(World w) {
        this.world = (MyWorld)w;
        //w.addObject(sprite,0,0);
    }
    
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
    
    public abstract void calculateAnimationTimes();
    
    public void setTransparency(int amount){
        getImage().setTransparency(amount);
    }

    

    public void setEntityName(String entityName){
        this.entityName = entityName;
    }
    
    public void setAction(String action){
        this.action = action;
    }
    
    public void setDirChar(char dirChar){
        this.dirChar = dirChar;
    }
    
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

    public int getSPD(){
        return speed;
    }

    public void increaseSPD(int amount){
        speed += amount;
    }

    public int getHP(){
        return hp;
    }

    public void increaseHP(int amount){
        hp += amount;
    }

    public void decreaseHP(int amount){
        hp -= amount;
    }

    public int getATK(){
        return atk;
    }

    public void increaseATK(int amount){
        atk += amount;
    }
}
