import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * Keeps track of current score and decides whether to update the highscore.
 * The save file only tracks the high score.
 */
public class ScoreTracker extends Actor
{
    private static String saveFile = "save.txt";
    private static int currScore;
    private static int highScore;
    private static Scanner readScan;

    public static void readScore(){
        try{
            readScan = new Scanner (new File(saveFile));
            highScore = readScan.nextInt();
        } catch (FileNotFoundException e){
            System.out.println("File Not Found");
        }
    }
    
    public static void writeScore(){
        try{
            FileWriter out = new FileWriter(saveFile);
            PrintWriter output = new PrintWriter(out);
            output.println(highScore);
            output.close();
        } catch (IOException e){
            System.out.println("Error: " + e);
        }
    }
    /**
     * Determines whether there is a new high score, sets the new high score if present.
     * 
     * @return      True if new highscore, false otherwise.  
     */
    public static boolean determineHigh(){
        if(currScore > highScore){
            highScore = currScore;
            return true;
        }
        return false;
    }

    public static void increaseScore(int amount){
        currScore += amount;
    }

    public static void resetScore(){
        currScore = 0;
    }

    public static void resetHighScore(){
        highScore = 0;
    }

    public static int getScore(){
        return currScore;
    }

    public static int getHighScore(){
        return highScore;
    }
}
