import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * BasicHorde is a low-tier enemy.
 * 
 * @author Jamison H, Jeff G, Bryan Y
 */
public class BasicHorde extends Enemy
{
    private Player target;

    private int targetX;
    private int targetY;
    //private boolean isAlive = true;
    public BasicHorde(){

        super(50,1, 20);
        setEntityName("basicEnemy");
        setAction("run");
        setAnimationLength(4);

        //speed = Math.random() + 1.0; // varied speed
    }

    /**
     * The act method, get a target and move towards it.
     */
    public void act(){
        if(isAlive){
            super.act();
            repel();
            if(target != null && target.getWorld() != null){
                turnTowards(target.getX(), target.getY());
                move(speed);            
            } else {
                target = world.getPlayer();
            }

            if(atkCooldown == 0){
                Player c = (Player)getOneIntersectingObject(Player.class);
                if(c != null && c.getWorld() != null){
                    c.decreaseHP(atk);
                    atkCooldown = 30;
                }
            }
            if(atkCooldown > 0){
                atkCooldown--;
            }

            if(hp <= 0){
                isAlive=false;
                imageIndex=2;
            }
        }
        else{
            
            action="death";
            super.act();
            if(imageIndex==7){
                world.addObject(new XP(0), getX(), getY());
                ScoreTracker.increaseScore(10);
                getWorld().removeObject(this);
                return;
            }
            
        }
    }

    /**
     * Modifed from the "work in progress" - Repel Pedestrian Experiment.
     * Gathers all enemies that are close and repels to prevent stacking.
     * 
     * @author Jordan Cohen
     * @since Feb 2023
     */
    public void repel() {
        ArrayList<Enemy> enemiesTouching = (ArrayList<Enemy>)getIntersectingObjects(Enemy.class);

        ArrayList<Actor> actorsTouching = new ArrayList<Actor>();

        actorsTouching.addAll(enemiesTouching);

        pushAwayFromObjects(actorsTouching, 0.3);
    }

    /**
     * Modifed from the new repel method
     * Can be used in both directions, but for now
     * commented out movement on x so players are only "repelled" in a y-direction.
     * 
     * @author Mr Cohen
     * @since February 2023
     */
    public void pushAwayFromObjects(ArrayList<Actor> nearbyObjects, double minDistance) {
        // Get the current position of this actor
        int currentX = getX();
        int currentY = getY();

        // Iterate through the nearby objects
        for (Actor object : nearbyObjects) {
            // Get the position and bounding box of the nearby object
            int objectX = object.getX();
            int objectY = object.getY();
            int objectWidth = object.getImage().getWidth()/4;
            int objectHeight = object.getImage().getHeight()/4;

            // Calculate the distance between this actor and the nearby object's bounding oval
            double distance = Math.sqrt(Math.pow(currentX - objectX, 2) + Math.pow(currentY - objectY, 2));

            // Calculate the effective radii of the bounding ovals
            double thisRadius = Math.max(getImage().getWidth() / 2.0, getImage().getHeight() / 2.0);
            double objectRadius = Math.max(objectWidth / 2.0, objectHeight / 2.0);

            // Check if the distance is less than the sum of the radii
            if (distance < (thisRadius + objectRadius + minDistance)) {
                // Calculate the direction vector from this actor to the nearby object
                int deltaX = objectX - currentX;
                int deltaY = objectY - currentY;

                // Calculate the unit vector in the direction of the nearby object
                double length = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
                double unitX = deltaX / length;
                double unitY = deltaY / length;

                // Calculate the amount by which to push the nearby object
                double pushAmount = (thisRadius + objectRadius + minDistance) - distance;

                // Update the position of the nearby object to push it away

                object.setLocation(objectX, objectY + (int)(pushAmount * unitY));

                // 2d version, allows pushing on x and y axis, commented out for now but it works, just not the
                // effect I'm after:
                object.setLocation(objectX + (int)(pushAmount * unitX), objectY + (int)(pushAmount * unitY));
            }
        }
    }
}
