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
        super(200,1, 20,"construction-enemy.png");
    }
    public void act()
    {
        if(target != null && target.getWorld() != null){
            turnTowards(target.getX(), target.getY());
            move(speed);
        } else {
            target = world.getCharacter();
        }
        
        if(atkCooldown == 0){
            Character c = (Character)getOneIntersectingObject(Character.class);
            if(c != null && c.getWorld() != null){
                c.decreaseHP(atk);
                atkCooldown = 30;
            }
        }
        if(atkCooldown > 0){
            atkCooldown--;
        }
        if(hp <= 0){
            world.addObject(new XP(0), getX(), getY());
            world.removeObject(this);
            return;
        }
    }
}
