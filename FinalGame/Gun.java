import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Gun here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gun extends Entity
{
    private GreenfootImage gun = new GreenfootImage("gun.png");
    private int actCount;
    private int frequency; // this affects fire rate of the gun, a lower number = faster firing
    public Gun(){
        gun.scale(40, 40);
        gun.rotate(-45);
        gun.mirrorHorizontally();
        setImage(gun);
        frequency = 15;
        actCount = 0;
    }

    public void act()
    {
        Enemy closestEnemy = getNearestEnemy();
        if(closestEnemy != null){
            turnTowards(closestEnemy.getEnemyX(), closestEnemy.getEnemyY());
            if(actCount % frequency == 0){
                getWorld().addObject(new Bullet(closestEnemy.getEnemyX(), closestEnemy.getEnemyY()),getX(), getY());
            }
        }
        MyWorld world = (MyWorld)getWorld();
        Character c = world.getCharacter();
        setLocation(c.getX() +30, c.getY());
        actCount++;
    }

    //add this code to your player class;
    public double getDistance(Actor actor) {
        return Math.hypot(actor.getX() - getX(), actor.getY() - getY());
    }

    /**
     * Code from Gevater_Tod4711 in forum page:
     * https://www.greenfoot.org/topics/4911
     */
    public Enemy getNearestEnemy() {
        List<Enemy> nearEnemies = getObjectsInRange(400, Enemy.class);//here you can use the radius you want and maybe another class;
        Enemy nearestEnemy = null;
        double nearestDistance = 2000;// some high number
        double distance;
        for (int i = 0; i < nearEnemies.size(); i++) {
            distance = getDistance(nearEnemies.get(i));
            if (distance < nearestDistance) {
                nearestEnemy = nearEnemies.get(i);
                nearestDistance = distance;
            }
        }
        return nearestEnemy;
    }
}
