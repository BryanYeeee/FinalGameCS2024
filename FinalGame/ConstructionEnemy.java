import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ConstructionEnemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ConstructionEnemy extends Enemy
{
    public ConstructionEnemy(){
        super(200,1, 20);
    }
    public void act()
    {
        if(target != null && target.getWorld() != null){
            turnTowards(target.getPlayerX(), target.getPlayerY());
            move(speed);
        } else {
            target = world.getPlayer();
        }
        
        if(atkCooldown == 0){
            Player p = (Player)getOneIntersectingObject(Player.class);
            if(p != null && p.getWorld() != null){
                p.decreaseHP(atk);
                atkCooldown = 30;
            }
        }
        if(atkCooldown > 0){
            atkCooldown--;
        }
        if(hp <= 0){
            world.addObject(new XP(0), getX(), getY());
            ScoreTracker.increaseScore(20);
            world.removeObject(this);
            return;
        }
    }
}
