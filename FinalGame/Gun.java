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
    private GreenfootImage gun = new GreenfootImage("FirstWeapon.png");
    private int actCount;
    private int frequency; // this affects fire rate of the gun, a lower number = faster firing
    private SoundManager sm;
    
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
                        setImage("TridentWeapon.png");
                        sm.playSound("TridentSlash");
                        getWorld().addObject(new Trident(closestEnemy.getX(), closestEnemy.getY()), getX() + (closestEnemy.getX() - getX()) / 2, getY() + (closestEnemy.getY() - getY()) / 2);
                        break;
                    case "TRIDENT1":
                        sm.playSound("SharkBite");
                        getWorld().addObject(new SharkBite(closestEnemy.getX(), closestEnemy.getY()), getX() + (closestEnemy.getX() - getX()) / 2, getY() + (closestEnemy.getY() - getY()) / 2);
                        break;
                    case "TRIDENT2":
                        sm.playSound("WaterSplash");
                        setImage("WaterWeapon.png");
                        getWorld().addObject(new WaterSplash(closestEnemy.getX(), closestEnemy.getY()), getX() + (closestEnemy.getX() - getX()) / 2, getY() + (closestEnemy.getY() - getY()) / 2);
                        break;
                    case "TRIDENT3":
                        sm.playSound("SharkSpecial");
                        setImage("MaxTridentWeapon.png");
                        getWorld().addObject(new SharkSpecial(closestEnemy.getX(), closestEnemy.getY()), getX() + (closestEnemy.getX() - getX()) / 2, getY() + (closestEnemy.getY() - getY()) / 2);
                        break;
                    case "SLASH":
                        setImage("BasicSword.png");
                        getWorld().addObject(new BasicSlash(closestEnemy.getX(), closestEnemy.getY()), getX() + (closestEnemy.getX() - getX()) / 2, getY() + (closestEnemy.getY() - getY()) / 2);
                        break;
                    case "SLASH1":
                        setImage("TwinSwords.png");
                        getWorld().addObject(new Slash(closestEnemy.getX(), closestEnemy.getY()), getX() + (closestEnemy.getX() - getX()) / 2, getY() + (closestEnemy.getY() - getY()) / 2);
                        break;
                    case "SLASH2":
                        setImage("FireSword.png");
                        getWorld().addObject(new FireSlash(closestEnemy.getX(), closestEnemy.getY()), getX() + (closestEnemy.getX() - getX()) / 2, getY() + (closestEnemy.getY() - getY()) / 2);
                        break;
                    case "SLASH3":
                        setImage("MaxTwinSwords.png");
                        getWorld().addObject(new SlashSpecial(closestEnemy.getX(), closestEnemy.getY()), getX() + (closestEnemy.getX() - getX()) / 2, getY() + (closestEnemy.getY() - getY()) / 2);
                        break;
                    case "GUN":
                        setImage("BasicGun.png");
                        frequency = 20;
                        getWorld().addObject(new BasicBullet(closestEnemy.getX(), closestEnemy.getY()), getX(), getY());
                        break;
                    case "GUN1":
                        setImage("gun.png");
                        frequency = 40;
                        getWorld().addObject(new BasicBullet(closestEnemy.getX(), closestEnemy.getY()), getX(), getY());
                        getWorld().addObject(new BasicBullet(closestEnemy.getX(), closestEnemy.getY()), getX() + 25, getY());
                        getWorld().addObject(new BasicBullet(closestEnemy.getX(), closestEnemy.getY()), getX() + 50, getY());
                        break;
                    case "GUN2":
                        frequency = 55;
                        getWorld().addObject(new BasicBullet(closestEnemy.getX(), closestEnemy.getY()), getX() + 25, getY());
                        getWorld().addObject(new BasicBullet(closestEnemy.getX(), closestEnemy.getY()), getX() + 25, getY() + 25);
                        getWorld().addObject(new BasicBullet(closestEnemy.getX(), closestEnemy.getY()), getX() + 25, getY() - 25);
                        getWorld().addObject(new BasicBullet(closestEnemy.getX(), closestEnemy.getY()), getX() + 15, getY() + 35);
                        getWorld().addObject(new BasicBullet(closestEnemy.getX(), closestEnemy.getY()), getX() + 15, getY() - 35);
                        /*
                        getWorld().addObject(new BasicBullet(closestEnemy.getX(), closestEnemy.getY()), getX(), getY());
                        getWorld().addObject(new BasicBullet(closestEnemy.getX(), closestEnemy.getY()), getX(), getY());
                        getWorld().addObject(new BasicBullet(closestEnemy.getX(), closestEnemy.getY()), getX(), getY());
                        getWorld().addObject(new BasicBullet(closestEnemy.getX(), closestEnemy.getY()), getX(), getY());
                        getWorld().addObject(new BasicBullet(closestEnemy.getX(), closestEnemy.getY()), getX(), getY());
                        */
                        break;
                    case "GUN3":
                        setImage("MaxGun.png");
                        frequency = 90;
                        getWorld().addObject(new ChargeShot(closestEnemy.getX(), closestEnemy.getY()), getX(), getY());
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
    
    public void setImage(String image) {
        GreenfootImage newImage = new GreenfootImage(image);
        newImage.scale(40, 40);
        newImage.rotate(-45);
        newImage.mirrorHorizontally();
        setImage(newImage);
    }
}
