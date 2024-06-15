import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BasicBullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BasicBullet extends Attack
{
    private GreenfootImage image;
    
    public BasicBullet(int x, int y) {
        super(x, y);
        //this.size = size;
        //this.speed = speed;
        image = new GreenfootImage("BasicBullet.png");
        
        setImage(image);
    }
    
    public void animate() {
        
    }
    
    /**
     * Act - do whatever the BasicBullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        move(6);
        Enemy enemy = (Enemy)getOneIntersectingObject(Enemy.class);
        if(enemy != null && enemy.getAlive() == true){
            enemy.takeDamage(world.getPlayer().getATK());
            getWorld().removeObject(this);
            return;
        }

        if(isAtEdge()){
            getWorld().removeObject(this);
            return;
        }
    }
}
