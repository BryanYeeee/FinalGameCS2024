import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class Gun here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gun extends SuperSmoothMover
{
    private GreenfootImage gun = new GreenfootImage("TwinSwords.png");
    private int actCount;
    private int frequency; // this affects fire rate of the gun, a lower number = faster firing

    private String myAttack = "";
    public Gun(){
        gun.scale(40, 40);
        gun.rotate(-45);
        gun.mirrorHorizontally();
        setImage(gun);
        //frequency = 40;
        frequency = 100;
        actCount = 0;
    }

    public void act()
    {
        Enemy closestEnemy = getNearestEnemy();
        if(closestEnemy != null){
            turnTowards(closestEnemy.getX(), closestEnemy.getY());
            if(actCount % frequency == 0){
                switch (myAttack){
                    case "TRIDENT":
                        getWorld().addObject(new Trident(closestEnemy.getX(), closestEnemy.getY()), getX() + (closestEnemy.getX() - getX()) / 2, getY() + (closestEnemy.getY() - getY()) / 2);
                        break;
                    case "TRIDENT1":
                        getWorld().addObject(new SharkBite(closestEnemy.getX(), closestEnemy.getY()), getX() + (closestEnemy.getX() - getX()) / 2, getY() + (closestEnemy.getY() - getY()) / 2);
                        break;
                    case "TRIDENT2":
                        getWorld().addObject(new WaterSplash(closestEnemy.getX(), closestEnemy.getY()), getX() + (closestEnemy.getX() - getX()) / 2, getY() + (closestEnemy.getY() - getY()) / 2);
                        break;
                    case "TRIDENT3":
                        getWorld().addObject(new SharkSpecial(closestEnemy.getX(), closestEnemy.getY()), getX() + (closestEnemy.getX() - getX()) / 2, getY() + (closestEnemy.getY() - getY()) / 2);
                        break;
                    case "SLASH":
                        getWorld().addObject(new BasicSlash(closestEnemy.getX(), closestEnemy.getY()), getX() + (closestEnemy.getX() - getX()) / 2, getY() + (closestEnemy.getY() - getY()) / 2);
                        break;
                    case "SLASH1":
                        getWorld().addObject(new Slash(closestEnemy.getX(), closestEnemy.getY()), getX() + (closestEnemy.getX() - getX()) / 2, getY() + (closestEnemy.getY() - getY()) / 2);
                        break;
                    case "SLASH2":
                        getWorld().addObject(new FireSlash(closestEnemy.getX(), closestEnemy.getY()), getX() + (closestEnemy.getX() - getX()) / 2, getY() + (closestEnemy.getY() - getY()) / 2);
                        break;
                    case "SLASH3":
                        getWorld().addObject(new SlashSpecial(closestEnemy.getX(), closestEnemy.getY()), getX() + (closestEnemy.getX() - getX()) / 2, getY() + (closestEnemy.getY() - getY()) / 2);
                        break;
                    case "GUN":
                        break;
                    case "GUN1":
                        break;
                    case "GUN2":
                        break;
                    case "GUN3":
                        break;
                }
            }
        }
        MyWorld world = (MyWorld)getWorld();
        Player c = world.getPlayer();
        if(c.getWorld() != null){
            setLocation(c.getX() +30, c.getY());
        }
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

    public void setAttack(String attack){
        myAttack = attack;
    }
}
