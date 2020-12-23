/**
 * Linear Golf 2.0 beta
 * VR compatible (very)
 *
 * @authors Nathan Edmondson, Logan Schoffstall
 * @version 11/26/2020
 */

import java.awt.*;
import java.applet.Applet;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LinearGolf implements LineListener
{
    
        //nonsense
        static int hit = 0; static int clubDist = 0;
        static double pow = 0; static boolean club = false;
        static boolean play = true; static int error = 0;
        static int totalDist = 0; static int toHole = 0;
        static int holeLength = 0; static boolean inHole = false;
        static int totalStrokes= 0; static double sandError = 0;
        static int difficulty = 3; static boolean begin = false;                
        static String ask2 = ""; static boolean begin2 = false;
        static int wind = 0; static String windDir = "against";
        static Scanner in = new Scanner(System.in);
        static int money = 500; static String name = "";
        static int lastScore; static int minusSkill = 0;
        static boolean addClubs = true; static String hy;
        static String iron1; static String PW; static String GW;
        static boolean startFront = true; static boolean rngMode;
        static int lastPow; static boolean playCompleted;
        static boolean loopMusic = false; static  boolean inShop=false;
        static int bestTPlayer; static boolean wonT1;
        static int diff; static boolean wonT2;
        static int tPlace; static boolean wonT3;
        static boolean wonT4; static boolean behind;
        static boolean inT1; static boolean inT2;
        static boolean inT3; static boolean inT4;
        
        //Betting
        private static Scanner scanner = new Scanner(System.in);
        private static int[] deck;   // An array of 52 Cards, representing the deck.
        private static int currentPosition; // Current position in the deck
        private static Vector hand;   // The cards in the hand.
        
        //Clothing
        static boolean hasGlove = false; static boolean hasHat = false;
        static boolean hasBall = false; static boolean hasBook = false;
        
        //scorekeeping
        static int oneScore = 0; static int twoScore = 0;
        static int threeScore = 0; static int fourScore = 0;
        static int fiveScore = 0; static int sixScore = 0;
        static int sevenScore = 0; static int eightScore = 0;        
        static int nineScore = 0; static int strokes = 0;
        static int putts = 0; static int luck = 0;
        static int tenScore = 0; static int elevenScore = 0;
        static int twelveScore = 0; static int thirteenScore = 0;
        static int fourteenScore = 0; static int fifteenScore = 0;
        static int sixteenScore = 0; static int seventeenScore = 0;
        static int eighteenScore = 0; static int mishits;
        static int totalHoles = 0; static int hio = 0;
        static int par = 3; static int birdies = 0;
        static int pars = 0; 
        
        //Skills
        static int playPower = 1; static int playAcc = 1;
        static int playPutt = 1; static int playIron = 1;
        static int playDriv = 1; 
        //clubs
        static int distDr = 215; static int dist1 = 185;
        static int distHy = 180; static int dist5 = 150;
        static int dist7 = 130; static int dist9 = 110;
        static int distPW = 80; static int distSW = 50;
        static int distGW = 20; static int waterPos = 0;
        static int drUpgrade = 0; static boolean hasGap = false;
        static boolean hasOne = false; static boolean hasHy = false;
        static boolean hasPW = false; static Clip audioClip;
               
    public LinearGolf()
    {
                
    }

    void play(String audioFilePath) {        
        File audioFile = new File(audioFilePath);
 
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
 
            AudioFormat format = audioStream.getFormat();
 
            DataLine.Info info = new DataLine.Info(Clip.class, format);
 
            Clip audioClip = (Clip) AudioSystem.getLine(info);
            
            audioClip.addLineListener(this);
 
            audioClip.open(audioStream);
            audioClip.start() ;                    
            if (loopMusic)
              audioClip.loop(Clip.LOOP_CONTINUOUSLY);     
                                          
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }
         
    }
    
    
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();
        
        if (type == LineEvent.Type.START) {
            //System.out.println("Playback started.");
             
        } else if (type == LineEvent.Type.STOP) {
            playCompleted = true;
            //System.out.println("Playback completed."); 
        }
    }
        
    public static void readSave() throws IOException {
        File fileName = new File(name+".txt");
        Scanner inFile = new Scanner(fileName);
               
        
        while (inFile.hasNext() ) {
              difficulty = inFile.nextInt();
              money = inFile.nextInt();
              playPower = inFile.nextInt();
              playAcc = inFile.nextInt();
              playPutt = inFile.nextInt();
              lastScore = inFile.nextInt();
              playDriv = inFile.nextInt();
              playIron = inFile.nextInt();
              hasGap = inFile.nextBoolean();
              hasOne = inFile.nextBoolean();
              hasHy = inFile.nextBoolean();
              hasPW = inFile.nextBoolean();
              hasGlove = inFile.nextBoolean();
              hasHat = inFile.nextBoolean();
              hasBall = inFile.nextBoolean();
              hasBook = inFile.nextBoolean();
              totalHoles = inFile.nextInt();
              hio = inFile.nextInt();
              birdies = inFile.nextInt();              
              pars = inFile.nextInt();
              wonT1 = inFile.nextBoolean();
              wonT2 = inFile.nextBoolean();
              wonT3 = inFile.nextBoolean();
              wonT4 = inFile.nextBoolean();
              
        }
        inFile.close();
    }
    
    public static void teeOff()
    {
        Scanner in = new Scanner(System.in);
        pow = 0;        
        wind = (int)((difficulty)*(Math.random()*8-4));
               //1 or 3 or 5       -4 to 4
        
        clubDist(playPower);
        lastPow = playPower;
               
        int displayWind = Math.abs(wind);       
        if (wind>0)
           windDir = "with";
        else if (wind<0)
           windDir = "against";
        else {
        } //nowind 
        
           
        
        
        System.out.println(" || You are teeing off || ");
        System.out.println(" The wind is blowing "+displayWind+" mph "+windDir+" you");
        
        System.out.println("   Choose your club: ");
        System.out.println("[D] Driver ~ " + distDr + " yds");
        if (hasHy)
           System.out.println("[H] Hybrid ~ " + distHy + " yds");
        if (hasOne) 
          System.out.println("[1] 1 Iron ~ " + dist1 + " yds");
        System.out.println("[5] 5 Iron ~ " + dist5 + " yds");
        System.out.println("[7] 7 Iron ~ " + dist7 + " yds");
        String asdf = in.next();
        
        while (pow<20 || pow>100) //allow for user error
         {
           System.out.println("Enter power (20-100%)");
           pow = in.nextInt();
           
           if (20>pow || 100<pow)
            {System.out.println("Please select a valid power");
           
            }    else if (19<pow || 101>pow)            
              continue;
            else 
              System.out.println("Please select a valid power");
        }
        do {
           if (asdf.equalsIgnoreCase("d")) {          
           calcDrive(pow, distDr, .5); club=true; }
        else if (asdf.equalsIgnoreCase("1") && hasOne) {          
           calcDrive(pow, dist1, .4); club=true; }  
        else if (asdf.equalsIgnoreCase("h") && hasHy) {          
           calcDrive(pow, distHy, .6); club=true; }  
        else if (asdf.equalsIgnoreCase("5")) {         
           calcDrive(pow, dist5, .8); club=true; } 
        else if (asdf.equalsIgnoreCase("7")) {          
           calcDrive(pow, dist7, .9); club=true; }
        else if (asdf.equalsIgnoreCase("9")) {          
           calcDrive(pow, dist9, 1.0); club=true; }
           else if (asdf.equalsIgnoreCase("icheat"))  { 
           calcDrive(pow, 500, 0);  club=true; }
        else {
         System.out.println("You did not choose a club, try again"); club = false; }
        } while (club=false);
    }
    
    public static int calcDrive(double pow, int x, double y)
    {
        pow = (pow)/100.0;        
        error = (int)((2*difficulty*(4.0/(playAcc+1.5))*(Math.random()*3-2))*pow);
        double windError = y*-wind*.01;
        hit = (int)(pow*x) + error;
        hit = hit - (int)(hit*windError);
        System.out.println("You hit " + hit + " yards");
        
        return hit;
    }
    
    public static void chooseClub()
    {
        Scanner in = new Scanner(System.in);        
        int hit = 0; int clubDist = 0;
        double pow = -1; String hitS = "Biden2020";
                  
        System.out.println("Select Your Club");
        //System.out.println("[D] Driver ~ " + distDr + " yds");
        if (hasHy)
          System.out.println("[H] Hybrid ~ " + distHy + " yds");
        if (hasOne) 
          System.out.println("[1] 1 Iron ~ " + dist1 + " yds");
        System.out.println("[5] 5 Iron ~ " + dist5 + " yds");
        System.out.println("[7] 7 Iron ~ " + dist7 + " yds");
        System.out.println("[9] 9 Iron ~ " + dist9 + " yds");
        if (hasPW)
         System.out.println("[P] P Wedge ~ " + distPW + " yds");
        System.out.println("[S] S Wedge ~ " + distSW + " yds");
        if (hasGap)
          System.out.println("[G] G Wedge ~ " + distGW + " yds");
        System.out.println();
        hitS = in.next();
        
        while (pow<20 || pow>100) //allow for user error
         {
           System.out.println("Enter power (20-100%)");
           pow = in.nextInt();
           
           if (20>pow || 100<pow)
            {System.out.println("Please select a valid power");
           
            }    else if (19<pow || 101>pow)            
              continue;
            else 
              System.out.println("Please select a valid power");
        }
        
        
        //Club Distance    
        do {
         if (hitS.equalsIgnoreCase("icheat"))  { 
           calcHit(pow, 500, 0); club=true; } 
        else if (hitS.equalsIgnoreCase("h") && hasHy) {           
           calcHit(pow, distHy, .5); club=true; } 
        else if (hitS.equalsIgnoreCase("1") && hasOne) {
           calcHit(pow, dist1, .55); club=true; }
        else if (hitS.equalsIgnoreCase("5")) {          
           calcHit(pow, dist5, .6); club=true; } 
        else if (hitS.equalsIgnoreCase("7")) {          
           calcHit(pow, dist7, .65); club=true; } 
        else if (hitS.equalsIgnoreCase("9")) {          
           calcHit(pow, dist9, .7); club=true; }
        else if (hitS.equalsIgnoreCase("p") && hasPW) {          
           calcHit(pow, distPW, .6); club=true; }
        else if (hitS.equalsIgnoreCase("s")) {          
           calcHit(pow, distSW, .6); club=true; sandError = 0; }
        else if (hitS.equalsIgnoreCase("g") && hasGap) {
           calcHit(pow,distGW, .6); club=true; }
        //else if (hitS.equalsIgnoreCase("b"))
           //calcHit(pow,-50, .6);         
        else {
        
           System.out.println("You did not choose a club!"); club = false; }
        }while (club=false) ;}
    
    public static void clubDist(int playPower) 
    {
        if (!(lastPow==playPower)) {
          distDr = 215;dist1 = 185;
         distHy = 180; dist5 = 150;
        dist7 = 130; dist9 = 110;
        distPW = 80; distSW = 50;
        distGW = 20; 
        distDr = distDr + (int)(30*(playPower/5.0)) + (int)(20*(playDriv/6.0));
        distHy = distHy + (int)(25*(playPower/5.0)) + (int)(15*(playDriv/6.0));
        dist1 = dist1 + (int)(25*(playPower/5.0)) + (int)(20*(playIron/6.0));
        dist5 = dist5 + (int)(25*(playPower/5.0)) + (int)(15*(playIron/6.0));
        dist7 = dist7 + (int)(25*(playPower/5.0)) + (int)(15*(playIron/6.0));
        dist9 = dist9 + (int)(25*(playPower/5.0)) + (int)(10*(playIron/6.0));
        distPW = distPW + (int)(20*(playPower/5.0));
        distSW = distSW + (int)(20*(playPower/5.0));
        distGW = distGW + (int)(20*(playPower/5.0)); }
    }
    
    public static int calcHit(double pow, int clubDist, double y)
    {
        pow = (pow)/100.0;        
        error = (int)((2*difficulty*(4.0/(playAcc+1.5))*(Math.random()*3-2))*pow);
        hit = (int)(pow*clubDist) + error - (int)(pow*(Math.random()*11)*sandError);  
        double windError = y*-wind*.01;
        hit = hit - (int)(hit*windError);           
        System.out.println("You hit " + hit + " yards");
        return hit;
    }
    
    public static int overhit() {
        while (totalDist - 10 > holeLength) {
        pow = 0;
        String backClub;
        mishits++;
        System.out.println("You overshot the greens! Select a club to hit backwards: ");
        System.out.println("[9] 9 Iron ~ " + dist9 + " yds");
        if (hasPW)
          System.out.println("[P] P Wedge ~ " + distPW + " yds");
        System.out.println("[S] S Wedge ~ " + distSW + " yds");
        if (hasGap) 
          System.out.println("[G] G Wedge ~ " + distGW + " yds");
        backClub = in.next();
        
        while (pow<20 || pow>100) //allow for user error
         {
           System.out.println("Enter power (20-100%)");
           pow = in.nextInt();
           
           if (20>pow || 100<pow)
            {System.out.println("Please select a valid power");
           
            }    else if (19<pow || 101>pow)            
              continue;
            else 
              System.out.println("Please select a valid power");
        }
        do {
        if (backClub.equalsIgnoreCase("9"))    {       
           calcHit(pow, -dist9, 0); club=true; }
        else if (backClub.equalsIgnoreCase("p") && hasPW)   {        
           calcHit(pow, -distPW, 0); club=true; }
        else if (backClub.equalsIgnoreCase("s"))      {     
           calcHit(pow, -distSW, 0); club=true; }
        else if (backClub.equalsIgnoreCase("g") && hasGap) {
           calcHit(pow,-distGW, 0);  club=true; }               
        else {
        
           System.out.println("You did not choose a club!"); club = false; }
        }while (club=false);  
           totalDist+=hit;
           strokes++;
           toHole= Math.abs(holeLength - totalDist);           
           System.out.println(toHole + " to hole."); }
           return hit; 
    }
          
    public static int putt(int totalDist, int toHole)
    {
        Scanner in = new Scanner(System.in);
        inHole = false;
        
        int puttDist = (int)(18 - 8*(playPutt/10.0));
        if (puttDist == 11)
           puttDist = 10;
        
        System.out.println();
        System.out.println(" || You are on the green || ");       
        while (!inHole) {
            System.out.println("To hole: " + toHole + " yards");
            System.out.println(" * Putter hits a max of " + puttDist + " yards * ");
            System.out.println("   Enter power (10-100%)");
            pow = in.nextInt();
          while (pow<10 || pow>100) //allow for user error
          {
            if (10>pow || 100<pow)
            {System.out.println("Please select a valid power");
           
            }    else 
              continue;
              System.out.println("Enter power (10-100%)");
            pow = in.nextInt();
           
            
          }
          calcPutt(pow, puttDist);      
          toHole= Math.abs(toHole-hit);
          System.out.println("you hit " + hit + " yds");
          holeIn();
        }        
        return strokes;
    }
    
    public static int calcPutt(double pow, int p) {
        
         hit = (int)(((pow/100.0)*p)+(3.0/playPutt)*(Math.random()*2-1.4)); //add error or hill or wind         
         strokes++;
         putts++;
         toHole= Math.abs(toHole-hit);
         return hit;    
    }
    
    public static void holeIn() {
          if (toHole == 0) {
              inHole = true;
            }
          else if (hit<toHole) {
             System.out.println(" You underhit! Try again");             
            }
          else if (hit>toHole) {
              System.out.println(" You overhit! Try again"); }
    }
    
    public static void welcome() throws IOException
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to Linear Golf 1.5.1!");
        System.out.println("Select Option");
        System.out.println("[S] Start Game");
        System.out.println("[D] Difficulty");
        System.out.println("[C] Continue from last save");
        System.out.println("[T] Tips");
        System.out.println("[E] Exit");
        String ask = in.next();
        
        begin = false;
        while (!begin) {
          if (ask.equalsIgnoreCase("s")) {
            System.out.println("Good luck!");         
            begin = true;
            if (addClubs) {
              hasHy = true; hasPW = true;}
            customize();
          }
          else if (ask.equalsIgnoreCase("t")) {
            System.out.println("Landing within 10 yards of the hole starts putting."); 
            System.out.println("Wind is constant for every hole then changes.");
            System.out.println("Club distance represents a 100% shot.");
            System.out.println("It's more likely to under hit than over hit.");
            System.out.println("Press S to begin, D for difficulty, or E to leave");
          }
          else if (ask.equalsIgnoreCase("d")) {
            System.out.println("Difficulty changes how much your shot randomly varies from"); 
            System.out.println("your input. Easier difficulties also give you more skill points");
            System.out.println("Select: ");
            System.out.println("[1] Easy career");
            System.out.println("[2] Medium career");
            System.out.println("[3] Hard career");
            System.out.println("[E] Easy quickplay");
            System.out.println("[M] Medium quickplay");
            System.out.println("[H] Hard quickplay");
            System.out.println("[R] RNG mode");
            ask2 = in.next();
             while (!begin2) {
               if (ask2.equalsIgnoreCase("1")) {           
               difficulty = 1; minusSkill = 5; begin2 = true; addClubs = false;}
              else if (ask2.equalsIgnoreCase("2")) {          
               difficulty = 3; minusSkill = 6; begin2 = true; addClubs = false;}
              else if (ask2.equalsIgnoreCase("3")) {          
               difficulty = 5; minusSkill = 7;begin2 = true; addClubs = false;}
              else if (ask2.equalsIgnoreCase("e")) {           
               difficulty = 1; addClubs = true; begin2 = true; }
              else if (ask2.equalsIgnoreCase("m")) {          
               difficulty = 3; addClubs = true; begin2 = true; }
              else if (ask2.equalsIgnoreCase("h")) {          
               difficulty = 5; addClubs = true; begin2 = true; }
               else if (ask2.equalsIgnoreCase("r")) {          
               difficulty = 3; addClubs = true; begin2 = true; rngMode=true; }
              else 
                System.out.println("Menu option not selected, try again");
              if (!begin2) {
                ask2 = in.next();   
              }
            }
            System.out.println("Difficulty selected. Press S to begin, C for last save, T for tips, or E to leave.");
          }
          else if (ask.equalsIgnoreCase("e")) {
            System.exit(0);          
          }
          else if (ask.equalsIgnoreCase("c")) {
              String save;
              System.out.println("Enter your name (or fileName)");
              name = in.next();
              readSave();              
              userStats();             
              begin = true;
              
            }
          else {
            System.out.println("Menu option not selected, try again");           
          } 
          if (!begin) {
             ask = in.next();
          }
        }
         }
   
    public static int holeOne()
    {
        holeLength = 180 + (int)(10*(Math.random()*2-1));
        
        System.out.println();
        /*System.out.println("Hole 1: Par 3");
        System.out.println("---------------+----+");
        System.out.println("U              |  O |");
        System.out.println("---------------+----+");
        System.out.println(" " + holeLength + " to hole"); */
        dispImage("hole1", ""+holeLength+" to hole");
        
        System.out.println(); par = 3;
        beforeHole(); 
        
           teeOff();
           totalDist+=hit;
           strokes++;
           toHole= Math.abs(holeLength - totalDist);           
           System.out.println(toHole + " to hole.");
           
           
        if (totalDist - 10 > holeLength) {
            overhit();
        }
        while ( toHole>10) {
           chooseClub();
           totalDist+=hit;
           strokes++;
           toHole= Math.abs(holeLength - totalDist);           
           System.out.println(toHole + " to hole.");
           if (totalDist - 10 > holeLength) {
             overhit();
           }
        }        
        if (toHole==0) {
          inHole=true; System.out.println("You made it!"); }
        else{
         while (!inHole) {
           putt(totalDist, toHole);
           
         }
        }        
        afterHole();
        oneScore = strokes;
        return strokes;
        
    }
    public static int holeTwo()
    {
        holeLength = 340 + (int)(15*(Math.random()*2-1));
        waterPos = 215 + (int)(11*(Math.random()*2-1));
        
        System.out.println(); /*
        System.out.println("Hole 2: Par 4");
        System.out.println("----------------------+----+");
        System.out.println("U         WWWWWW      |  O |");
        System.out.println("----------------------+----+"); */
        dispImage("hole2", "" + holeLength + " to hole, Water 170-"+waterPos); 
        System.out.println(); par = 4;
        beforeHole();
        while (totalDist == 0) {
           teeOff();
           totalDist+=hit;
           strokes++;
           if (totalDist>169 && totalDist <waterPos+1){
             totalDist-=hit;
             mishits++;
             System.out.println("You hit into the water! Start from last shot.");
           }
           toHole= Math.abs(holeLength - totalDist);           
           System.out.println(toHole + " to hole.");
        }      
        while ( toHole>10) {
           chooseClub();
           totalDist+=hit;
           strokes++;
           if (totalDist>169 && totalDist <waterPos+1){
             totalDist-=hit;
             mishits++;
             System.out.println("You hit into the water! Start from last shot.");
           }
           toHole= Math.abs(holeLength - totalDist);
           System.out.println(toHole + " to hole.");
           
           if (totalDist - 10 > holeLength) {
             overhit();
           }
        }
        if (toHole==0) {
          inHole=true;System.out.println("You made it!"); }
              
          else{
         while (!inHole) {
           putt(totalDist, toHole);
           
         }
        }
        afterHole();
        twoScore = strokes;
        return strokes;
    }
    
    public static int holeThree()
    {
        holeLength = 395 + (int)(10*(Math.random()*2-1));
        waterPos = 250 + (int)(10*(Math.random()*2-1));
        
        System.out.println();
        /* System.out.println("Hole 3: Par 5");
        System.out.println("---------------------------+----+");
        System.out.println("U           WWWWWW         |  O |");
        System.out.println("---------------------------+----+");
        */
        dispImage("hole3", "" + holeLength + " to hole, Water 200-"+waterPos);
        System.out.println(); par = 5;
        beforeHole();
        while (totalDist == 0) {
           teeOff();
           totalDist+=hit;
           strokes++;
           if (totalDist>199 && totalDist <waterPos+1){
             mishits++;
               totalDist-=hit;
             System.out.println("You hit into the water! Start from last shot.");
           }
           toHole= Math.abs(holeLength - totalDist);           
           System.out.println(toHole + " to hole."); 
        }
        while ( toHole>10) {
           chooseClub();
           totalDist+=hit;
           strokes++;
           if (totalDist>199 && totalDist <waterPos+1){
             mishits++;
               totalDist-=hit;
             System.out.println("You hit into the water! Start from last shot.");
           }
           toHole= Math.abs(holeLength - totalDist);
           System.out.println(toHole + " to hole.");
           
           if (totalDist - 10 > holeLength) {
             overhit();
           }
        }
        if (toHole==0) {
          inHole=true;System.out.println("You made it!"); }
              
          else{
         while (!inHole) {
           putt(totalDist, toHole);
           
         }
        }
        afterHole();
        threeScore = strokes;
        return strokes;
    }
    
    public static int holeFour()
    {
        holeLength = 370 + (int)(15*(Math.random()*2-1));
        waterPos = 183 + (int)(7*(Math.random()*2-1));
        
        System.out.println();/*
        System.out.println("Hole 4: Par 4");
        System.out.println("---------------------------+----+");
        System.out.println("U        SSSS  SSSSSSSS    |  O |");
        System.out.println("---------------------------+----+");
        */ 
        dispImage("hole4", "" + holeLength + " to hole, Sand 150-"+waterPos+" and 200-270");
        System.out.println(); par = 4;
        beforeHole();
        while (totalDist == 0) {
           teeOff();
           totalDist+=hit;
           strokes++;
           if (totalDist>149 && totalDist <waterPos+1){             
             mishits++;
               System.out.println("You hit into the sand! Next shot has higher error.");
             sandError = 3;
           }
           if (totalDist>199 && totalDist <271){             
             mishits++;
               System.out.println("You hit into the sand! Next shot has higher error.");
             sandError = 3;
           }
           toHole= Math.abs(holeLength - totalDist);           
           System.out.println(toHole + " to hole.");
        }
        while ( toHole>10) {
           chooseClub();
           sandError=0;
           totalDist+=hit;
           strokes++;
           if (totalDist>149 && totalDist <waterPos+1){             
             mishits++;
               System.out.println("You hit into the sand! Next shot has higher error.");
             sandError = 3;
           }
           if (totalDist>199 && totalDist <271){             
             mishits++;
               System.out.println("You hit into the sand! Next shot has higher error.");
             sandError = 3;
           }
           toHole= Math.abs(holeLength - totalDist);
           System.out.println(toHole + " to hole.");
           
           if (totalDist - 10 > holeLength) {
             overhit();
           }
        }
        if (toHole==0) {
          inHole=true;System.out.println("You made it!"); }
              
          else{
         while (!inHole) {
           putt(totalDist, toHole);
           
         }
        }
        afterHole();
        fourScore = strokes;
        return strokes;
    }
    
    public static int holeFive()
    {
        holeLength = 460 + (int)(20*(Math.random()*2-1));
        waterPos = 201 + (int)(9*(Math.random()*2-1));        
        
        System.out.println();/*
        System.out.println("Hole 5: Par 5");
        System.out.println("---------------------------+----+");
        System.out.println("U WWWWWWW                  |  O |");
        System.out.println("---------------------------+----+");*/
        dispImage("hole5", "" + holeLength + " to hole, Water 10-"+waterPos);
        System.out.println(); par = 5;
        beforeHole();
        while (totalDist == 0) {
           teeOff();
           totalDist+=hit;
           strokes++;
           if (totalDist>10 && totalDist <waterPos+1){
             mishits++;
               totalDist-=hit;
             System.out.println("You hit into the water! Start from last shot.");
           }
           toHole= Math.abs(holeLength - totalDist);           
           System.out.println(toHole + " to hole.");
        }
        while ( toHole>10) {
           chooseClub();
           sandError=0;
           totalDist+=hit;
           strokes++;
           if (totalDist>10 && totalDist <waterPos+1){
             mishits++;
               totalDist-=hit;
             System.out.println("You hit into the water! Start from last shot.");
           }
           toHole= Math.abs(holeLength - totalDist);
           System.out.println(toHole + " to hole.");
           
           if (totalDist - 10 > holeLength) {
             overhit();
           }
        }
        if (toHole==0) {
          inHole=true;System.out.println("You made it!"); }
              
          else{
         while (!inHole) {
           putt(totalDist, toHole);
           
         }
        }
        afterHole();
        fiveScore = strokes;
        return strokes;
    }
    public static int holeSix()
    {
        holeLength = 168 + (int)(5*(Math.random()*2-1));
        waterPos = holeLength-11;
        
        System.out.println();/*
        System.out.println("Hole 6: Par 3 ");
        System.out.println("----------------+----+");
        System.out.println("U WWWWWWWWWWWWWW|  O |");
        System.out.println("----------------+----+");*/ 
        dispImage("hole6", "" + holeLength + " to hole, Water 10-"+waterPos);
        System.out.println(); par = 3;
        beforeHole();
        while (totalDist == 0) {
           teeOff();
           totalDist+=hit;
           strokes++;
           if (totalDist>9 && totalDist <waterPos+1){
             mishits++;
               totalDist-=hit;
             System.out.println("You hit into the water! Start from last shot.");
           }
           toHole= Math.abs(holeLength - totalDist);           
           System.out.println(toHole + " to hole.");
           if (totalDist - 10 > holeLength) {
             overhit();
           }
        }
        while ( toHole>10) {
           chooseClub();
           sandError=0;
           totalDist+=hit;
           strokes++;
           if (totalDist>9 && totalDist <waterPos+1){
             mishits++;
               totalDist-=hit;
             System.out.println("You hit into the water! Start from last shot.");
           }
           
           toHole= Math.abs(holeLength - totalDist);
           System.out.println(toHole + " to hole.");
           
           if (totalDist - 10 > holeLength) {
             overhit();
           }
        }
        if (toHole==0) {
          inHole=true;System.out.println("You made it!"); }
              
          else{
         while (!inHole) {
           putt(totalDist, toHole);
           
         }
        }
        afterHole();
        sixScore = strokes;
        return strokes;
    }
    
    public static int holeSeven()
    {
        holeLength = 245 + (int)(15*(Math.random()*2-1));
        waterPos = 220 + (int)(7*(Math.random()*2-1));
        
        System.out.println();/*
        System.out.println("Hole 7: Par 4");
        System.out.println("--------------------+----+");
        System.out.println("U      WWWWW     SS |  O |");
        System.out.println("--------------------+----+");*/ 
        dispImage("hole7", "" + holeLength + " to hole, Water 90-135, Sand 190-"+waterPos);
        System.out.println(); par = 4;
        beforeHole();
        while (totalDist == 0) {
           teeOff();
           totalDist+=hit;
           strokes++;
           if (totalDist>89 && totalDist <136){
             mishits++;
               totalDist-=hit;
             System.out.println("You hit into the water! Start from last shot.");
           }
           if (totalDist>189 && totalDist <waterPos+1){             
             mishits++;
               System.out.println("You hit into the sand! Next shot has higher error.");
             sandError = 3;
           }
           toHole= Math.abs(holeLength - totalDist);           
           System.out.println(toHole + " to hole.");
           
           if (totalDist - 10 > holeLength) {
             overhit();
           }
        }
        while ( toHole>10) {
           chooseClub();
           sandError=0;
           totalDist+=hit;
           strokes++;
           if (totalDist>89 && totalDist <136){
             mishits++;
               totalDist-=hit;
             System.out.println("You hit into the water! Start from last shot.");
           }
           if (totalDist>189 && totalDist <waterPos+1){             
             mishits++;
               System.out.println("You hit into the sand! Next shot has higher error.");
             sandError = 3;
           }
           toHole= Math.abs(holeLength - totalDist);
           System.out.println(toHole + " to hole.");
           
           if (totalDist - 10 > holeLength) {
             overhit();
           }
        }
        if (toHole==0) {
          inHole=true;System.out.println("You made it!"); }
              
          else{
         while (!inHole) {
           putt(totalDist, toHole);
           
         }
        }
        afterHole();
        sevenScore = strokes;
        return strokes;
    }
    
    public static int holeEight()
    {
        holeLength = 295 + (int)(15*(Math.random()*2-1));
        waterPos = 235 + (int)(10*(Math.random()*2-1));
       
        
        System.out.println();/*
        System.out.println("Hole 8: Par 4");
        System.out.println("--------------------+----+");
        System.out.println("U WWWWWWWWSSSSSSSS  |  O |");
        System.out.println("--------------------+----+");*/
        dispImage("hole8", "" + holeLength + " to hole, Water 25-165, Sand 166-"+waterPos);
        System.out.println(); par = 4;
        beforeHole();
        while (totalDist == 0) {
           teeOff();
           totalDist+=hit;           
           strokes++;
           double rng = Math.random();
           if (rng<.05) {
             System.out.println(" Your shot hit a bird and stopped halfway ");
             totalDist/=2;
            }
           if (totalDist>24 && totalDist <166){
             mishits++;
               totalDist=0;
             System.out.println("You hit into the water! Start from last shot.");
           }           
           if (totalDist>165 && totalDist <waterPos+1){             
             mishits++;
               System.out.println("You hit into the sand! Next shot has higher error.");
             sandError = 3;
           }
           toHole= Math.abs(holeLength - totalDist);           
           System.out.println(toHole + " to hole.");
        }
        while ( toHole>10) {
           chooseClub();
           sandError=0;
           totalDist+=hit;
           strokes++;
           if (totalDist>0 && totalDist <166){
             mishits++;
               totalDist-=hit;
             System.out.println("You hit into the water! Start from last shot.");
           }           
           if (totalDist>165 && totalDist <waterPos+1){             
             mishits++;
               System.out.println("You hit into the sand! Next shot has higher error.");
             sandError = 3;
           }
           toHole= Math.abs(holeLength - totalDist);
           System.out.println(toHole + " to hole.");
           
           if (totalDist - 10 > holeLength) {
             overhit();
           }
        }
        if (toHole==0) {
          inHole=true;System.out.println("You made it!"); }
              
          else{
         while (!inHole) {
           putt(totalDist, toHole);
           
         }
        }
        afterHole();
        eightScore = strokes;
        return strokes;
    }
    
    public static int holeNine()
    {
        holeLength = 325 + (int)(15*(Math.random()*2-1));
        waterPos = 255 + (int)(4*(Math.random()*2-1));
        
        System.out.println();/*
        System.out.println("Hole 9: Par 4");
        System.out.println("--------------------+----+");
        System.out.println("U        WW  WWWWW  |  O |");
        System.out.println("--------------------+----+");*/
        dispImage("hole9", "" + holeLength + " to hole, Water 232-246,"+waterPos+"-291.");
        System.out.println();  par = 4;
        beforeHole();
        while (totalDist == 0) {
           teeOff();
           totalDist+=hit;
           strokes++;
           if (totalDist>231 && totalDist <247){
             mishits++;
               totalDist-=hit;
             System.out.println("You hit into the water! Start from last shot.");
           }
           if (totalDist>waterPos-1 && totalDist <292){
             mishits++;
               totalDist-=hit;
             System.out.println("You hit into the water! Start from last shot.");
           }
           toHole= Math.abs(holeLength - totalDist);           
           System.out.println(toHole + " to hole.");
        }
        while ( toHole>10) {
           chooseClub();
           sandError=0;
           totalDist+=hit;
           strokes++;
           if (totalDist>231 && totalDist <247){
             mishits++;
               totalDist-=hit;
             System.out.println("You hit into the water! Start from last shot.");
           }
           if (totalDist>waterPos-1 && totalDist <292){
             mishits++;
               totalDist-=hit;
             System.out.println("You hit into the water! Start from last shot.");
           }
           
           toHole= Math.abs(holeLength - totalDist);
           System.out.println(toHole + " to hole.");
           
           if (totalDist - 10 > holeLength) {
             overhit();
           }
        }
        if (toHole==0) {
           inHole=true;System.out.println("You made it!"); }
              
          else{
         while (!inHole) {
           putt(totalDist, toHole);
           
         }
        }
        afterHole();
        nineScore = strokes;
        return strokes;
    }
    
    public static int holeTen()
    {
        holeLength = 197 + (int)(16*(Math.random()*2-1));
        waterPos = holeLength - 11;
        
        System.out.println();/*
        System.out.println("Hole 10: Par 3");
        System.out.println("--------------------+----+");
        System.out.println("U            SSSSSS |  O |");
        System.out.println("--------------------+----+");*/
        dispImage("hole10", "" + holeLength + " to hole, Sand 150- "+waterPos);
        System.out.println();  par = 3;
        beforeHole();
        while (totalDist == 0) {
           teeOff();
           totalDist+=hit;
           strokes++;
           if (totalDist>149 && totalDist <waterPos+1){
             mishits++;
               System.out.println("You hit into the sand! Next shot has higher error.");
             sandError = 3;
           }
           
           toHole= Math.abs(holeLength - totalDist);           
           System.out.println(toHole + " to hole.");
           if (totalDist - 10 > holeLength) {
             overhit();
           }
        }
        while ( toHole>10) {
           chooseClub();
           sandError=0;
           totalDist+=hit;
           strokes++;
           if (totalDist>149 && totalDist <waterPos+1){
             mishits++;
               System.out.println("You hit into the sand! Next shot has higher error.");
             sandError = 3;
           }
           
           toHole= Math.abs(holeLength - totalDist);
           System.out.println(toHole + " to hole.");
           
           if (totalDist - 10 > holeLength) {
             overhit();
           }
        }
        if (toHole==0) {
           inHole=true;System.out.println("You made it!"); }
              
          else{
         while (!inHole) {
           putt(totalDist, toHole);
           
         }
        }
        afterHole();
        tenScore = strokes;
        return strokes;
    }
    
    public static int holeEleven()
    {
        holeLength = 333 + (int)(15*(Math.random()*2-1));
        waterPos = 263 + (int)(5*(Math.random()*2-1));
        
        System.out.println();/*
        System.out.println("Hole 11: Par 4");
        System.out.println("----------------------+----+");
        System.out.println("U      SSSSWWWWWWW    |  O |");
        System.out.println("----------------------+----+");*/
        dispImage("hole11", "" + holeLength + " to hole, Sand 149-194, Water 195-"+waterPos);
        System.out.println();  par = 4;
        beforeHole();
        midHole(194, waterPos, 148, 195);
        afterHole();
        elevenScore = strokes;
        return strokes;
    }
    
    public static int holeTwelve()
    {
        holeLength = 480 + (int)(13*(Math.random()*2-1));
        waterPos = 450 + (int)(7*(Math.random()*2-1));
        
        System.out.println();/*
        System.out.println("Hole 12: Par 5");
        System.out.println("----------------------+----+");
        System.out.println("U      SSSS      SSSS |  O |");
        System.out.println("----------------------+----+");*/
        dispImage("hole12", "" + holeLength + " to hole, Sand 183-236, 400-"+waterPos);
        System.out.println(); par = 5;
        beforeHole();
        while (totalDist == 0) {
           teeOff();
           totalDist+=hit;
           strokes++;
           if (totalDist>182 && totalDist <237){
             mishits++;
               System.out.println("You hit into the sand! Next shot has higher error.");
             sandError = 3;
           }
           if (totalDist>399 && totalDist <waterPos){
             mishits++;
               System.out.println("You hit into the sand! Next shot has higher error.");
             sandError = 3;
           }
           toHole= Math.abs(holeLength - totalDist);           
           System.out.println(toHole + " to hole.");
        }
        while ( toHole>10) {
           chooseClub();
           sandError=0;
           totalDist+=hit;
           strokes++;
           if (totalDist>182 && totalDist <237){
             mishits++;
               System.out.println("You hit into the sand! Next shot has higher error.");
             sandError = 3;
           }
           if (totalDist>399 && totalDist <waterPos){
             mishits++;
               System.out.println("You hit into the sand! Next shot has higher error.");
             sandError = 3;
           }
           
           toHole= Math.abs(holeLength - totalDist);
           System.out.println(toHole + " to hole.");
           
           if (totalDist - 10 > holeLength) {
             overhit();
           }
        }
        if (toHole==0) {
           inHole=true;System.out.println("You made it!"); }
              
          else{
         while (!inHole) {
           putt(totalDist, toHole);
           
         }
        }
        afterHole();
        twelveScore = strokes;
        return strokes;
    }
    
    public static int holeThirteen()
    {
        holeLength = 300 + (int)(13*(Math.random()*2-1));
        waterPos = holeLength-30;
        
        System.out.println();/*
        System.out.println("Hole 13: Par 4");
        System.out.println("----------------------+----+");
        System.out.println("U               :::...|  O |");
        System.out.println("----------------------+----+");*/
        dispImage("hole13", "" + holeLength + " to hole, Downhill "+waterPos+" to green");;
        System.out.println(); par = 4;
        beforeHole();
        while (totalDist == 0) {
           teeOff();
           totalDist+=hit;
           strokes++;
            if (totalDist>waterPos-1 && totalDist <holeLength-9){             
               System.out.println("The ball rolled onto the green."); 
               totalDist = holeLength-8;
           }
           
           toHole= Math.abs(holeLength - totalDist);           
           System.out.println(toHole + " to hole.");
        }
        while ( toHole>10) {
           chooseClub();
           sandError=0;
           totalDist+=hit;
           strokes++;
            if (totalDist>waterPos-1 && totalDist <holeLength-9){             
               System.out.println("The ball rolled onto the green."); 
               totalDist = holeLength-8;
           }
           
           toHole= Math.abs(holeLength - totalDist);
           System.out.println(toHole + " to hole.");
           
           if (totalDist - 10 > holeLength) {
             overhit();
           }
        }
        if (toHole==0) {
           inHole=true;System.out.println("You made it!"); }
              
          else{
         while (!inHole) {
           putt(totalDist, toHole);
           
         }
        }
        afterHole();
        thirteenScore = strokes;
        return strokes;
    }
    
    public static int holeFourteen()
    {
        holeLength = 345 + (int)(15*(Math.random()*2-1));
        waterPos = 255 + (int)(4*(Math.random()*2-1));
        int otherPos = holeLength-20;
        int otherotherPos = holeLength-10;
        
        System.out.println();/*
        System.out.println("Hole 14: Par 4");
        System.out.println("----------------------+----+");
        System.out.println("U        WW  WWWWW..::|  O |");
        System.out.println("----------------------+----+");*/
        System.out.println(" "+holeLength+" to hole, Water 232-243, "+waterPos+"-"+otherPos+". ");
        System.out.println(" Steep uphill "+otherotherPos+" to green. ");
        dispImage("hole14", "" + holeLength + " to hole, Water 232-246, "+waterPos+"-"+otherPos+", uphill "+otherPos+" to green");
        System.out.println();  par = 4;
        beforeHole();
        while (totalDist == 0) {
           teeOff();
           totalDist+=hit;
           strokes++;
           if (totalDist>231 && totalDist <244){
             mishits++;
               totalDist-=hit;
             System.out.println("You hit into the water! Start from last shot.");
           }
           if (totalDist>waterPos-1 && totalDist <292){
             mishits++;
               totalDist-=hit;
             System.out.println("You hit into the water! Start from last shot.");
           }
            if (totalDist>waterPos-1 && totalDist <holeLength-9){             
               System.out.println("The ball rolled backwards into the water. Start from last shot."); 
               totalDist-=hit;
               mishits++;
           }
           toHole= Math.abs(holeLength - totalDist);           
           System.out.println(toHole + " to hole.");
        }
        while ( toHole>10) {
           chooseClub();
           sandError=0;
           totalDist+=hit;
           strokes++;
           if (totalDist>231 && totalDist <244){
             mishits++;
               totalDist-=hit;
             System.out.println("You hit into the water! Start from last shot.");
           }
           if (totalDist>waterPos-1 && totalDist <292){
             mishits++;
               totalDist-=hit;
             System.out.println("You hit into the water! Start from last shot.");
           }
            if (totalDist>waterPos-1 && totalDist <holeLength-9){             
               System.out.println("The ball rolled backwards into the water. Start from last shot."); 
               totalDist-=hit;
               mishits++;
           }
           
           toHole= Math.abs(holeLength - totalDist);
           System.out.println(toHole + " to hole.");
           
           if (totalDist - 10 > holeLength) {
             overhit();
           }
        }
        if (toHole==0) {
           inHole=true;System.out.println("You made it!"); }
              
          else{
         while (!inHole) {
           putt(totalDist, toHole);
           
         }
        }
        afterHole();
        fourteenScore = strokes;
        return strokes;
    }
    
    public static int holeFifteen()
    {
        holeLength = 355 + (int)(15*(Math.random()*2-1));
        waterPos = 215 + (int)(4*(Math.random()*2-1));        
        
        System.out.println();/*
        System.out.println("Hole 15: Par 4");
        System.out.println("----------------------+----+");
        System.out.println("U        ::...  ....::|  O |");
        System.out.println("----------------------+----+");*/
        dispImage("hole15", "" + holeLength + " to hole, downhill 180-210, uphill "+waterPos+" to green");
        System.out.println();  par = 4;
        beforeHole(); 
        while (totalDist == 0) {
           teeOff();
           totalDist+=hit;
           strokes++;
            if (totalDist>179 && totalDist <211){             
               System.out.println("The ball rolled forwards."); 
               totalDist=211;               
           }
            else if (totalDist>waterPos-1 && totalDist <holeLength-9){             
                System.out.println("The ball rolled backwards."); 
               totalDist= waterPos-1;
           }
           toHole= Math.abs(holeLength - totalDist);           
           System.out.println(toHole + " to hole.");
        }
        while ( toHole>10) {
           chooseClub();
           sandError=0;
           totalDist+=hit;
           strokes++;
            if (totalDist>waterPos-1 && totalDist <holeLength-9){             
               System.out.println("The ball rolled forwards."); 
               totalDist=211;               
           }
            if (totalDist>waterPos-1 && totalDist <holeLength-9){             
                System.out.println("The ball rolled backwards."); 
               totalDist= waterPos-1;
           }
           
           toHole= Math.abs(holeLength - totalDist);
           System.out.println(toHole + " to hole.");
           
           if (totalDist - 10 > holeLength) {
             overhit();
           }
        }
        if (toHole==0) {
           inHole=true;System.out.println("You made it!"); }
              
          else{
         while (!inHole) {
           putt(totalDist, toHole);
           
         }
        }
        afterHole();
        fifteenScore = strokes;
        return strokes;
    }
    
    public static int holeSixteen()
    {
        holeLength = 350 + (int)(15*(Math.random()*2-1));
        waterPos = 183 + (int)(7*(Math.random()*2-1));
        
        System.out.println();/*
        System.out.println("Hole 16: Par 4");
        System.out.println("---------------------------+----+");
        System.out.println("U            SSSSSSSSSS    |  O |");
        System.out.println("---------------------------+----+");*/
        dispImage("hole16", "" + holeLength + " to hole, Sand "+waterPos+"-290");
        System.out.println();  par = 4;
        beforeHole();
        while (totalDist == 0) {
           teeOff();
           totalDist+=hit;
           strokes++;
           
           if (totalDist>waterPos-1 && totalDist <291){             
             mishits++;
               System.out.println("You hit into the sand! Next shot has higher error.");
             sandError = 3;
           }
           toHole= Math.abs(holeLength - totalDist);           
           System.out.println(toHole + " to hole.");
        }
        while ( toHole>10) {
           chooseClub();
           sandError=0;
           totalDist+=hit;
           strokes++;
           if (totalDist>waterPos-1 && totalDist <291){             
             mishits++;
               System.out.println("You hit into the sand! Next shot has higher error.");
             sandError = 3;
           }
           toHole= Math.abs(holeLength - totalDist);
           System.out.println(toHole + " to hole.");
           
           if (totalDist - 10 > holeLength) {
             overhit();
           }
        }
        if (toHole==0) {
          inHole=true;System.out.println("You made it!"); }
              
          else{
         while (!inHole) {
           putt(totalDist, toHole);
           
         }
        }
        afterHole();
        sixteenScore = strokes;
        return strokes;
    }
    
    public static int holeSeventeen()
    {
        holeLength = 235 + (int)(15*(Math.random()*2-1));
        waterPos = 205 + (int)(7*(Math.random()*2-1));
        
        System.out.println(); /*
        System.out.println("Hole 17: Par 3");
        System.out.println("--------------------+----+");
        System.out.println("U                SS |  O |");
        System.out.println("--------------------+----+");*/
        dispImage("hole17", "" + holeLength + " to hole, Sand 190-"+waterPos);
        System.out.println(); par = 3;
        beforeHole();
        while (totalDist == 0) {
           teeOff();
           totalDist+=hit;
           strokes++;
           
           if (totalDist>189 && totalDist <waterPos+1){             
             mishits++;
               System.out.println("You hit into the sand! Next shot has higher error.");
             sandError = 3;
           }
           toHole= Math.abs(holeLength - totalDist);           
           System.out.println(toHole + " to hole.");
           
           if (totalDist - 10 > holeLength) {
             overhit();
           }
        }
        while ( toHole>10) {
           chooseClub();
           sandError=0;
           totalDist+=hit;
           strokes++;           
           if (totalDist>189 && totalDist <waterPos+1){             
             mishits++;
               System.out.println("You hit into the sand! Next shot has higher error.");
             sandError = 3;
           }
           toHole= Math.abs(holeLength - totalDist);
           System.out.println(toHole + " to hole.");
           
           if (totalDist - 10 > holeLength) {
             overhit();
           }
        }
        if (toHole==0) {
          inHole=true;System.out.println("You made it!"); }
              
          else{
         while (!inHole) {
           putt(totalDist, toHole);
           
         }
        }
        afterHole();
        seventeenScore = strokes;
        return strokes;
    }
    
    public static int holeEighteen()
    {
        holeLength = 520 + (int)(15*(Math.random()*2-1));
        waterPos = 480 + (int)(4*(Math.random()*2-1));                 
        
        System.out.println();/*
        System.out.println("Hole 18: Par 5");
        System.out.println("--------------------------+----+");
        System.out.println("U      ::.. WWW ...:::SS  |  O |");
        System.out.println("--------------------------+----+");*/
        dispImage("hole14", "" + holeLength + " to hole, downhill 187-240, Water 242-299, uphill 301-436. Sand 437-"+waterPos);
        System.out.println();  par = 5;
        beforeHole();
        while (totalDist == 0) {
           teeOff();
           totalDist+=hit;
           strokes++;
            if (totalDist>186 && totalDist <241){             
               System.out.println("The ball rolled forwards."); 
               totalDist=241;               
           }
            if (totalDist>300 && totalDist <437){             
                System.out.println("The ball rolled backwards."); 
               totalDist= 300;
           }
           if (totalDist>241 && totalDist <300){
             mishits++;
               totalDist-=hit;
             System.out.println("You hit into the water! Start from last shot.");
           }
           if (totalDist>436 && totalDist <waterPos+1){
             mishits++;
               System.out.println("You hit into the sand! Next shot has higher error.");
             sandError = 3;
           }
           toHole= Math.abs(holeLength - totalDist);           
           System.out.println(toHole + " to hole.");
        }
        while ( toHole>10) {
           chooseClub();
           sandError=0;
           totalDist+=hit;
           strokes++;
            if (totalDist>186 && totalDist <241){             
               System.out.println("The ball rolled forwards."); 
               totalDist=241;               
           }
            if (totalDist>300 && totalDist <437){             
                System.out.println("The ball rolled backwards."); 
               totalDist= 300;
           }
           if (totalDist>241 && totalDist <300){
             mishits++;
               totalDist-=hit;
             System.out.println("You hit into the water! Start from last shot.");
           }
           if (totalDist>436 && totalDist <waterPos+1){
             mishits++;
               System.out.println("You hit into the sand! Next shot has higher error.");
             sandError = 3;
           }
           
           toHole= Math.abs(holeLength - totalDist);
           System.out.println(toHole + " to hole.");
           
           if (totalDist - 10 > holeLength) {
             overhit();
           }
        }
        if (toHole==0) {
           inHole=true;System.out.println("You made it!"); }
              
          else{
         while (!inHole) {
           putt(totalDist, toHole);
           
         }
        }
        afterHole();
        eighteenScore = strokes;
        return strokes;
    }
    
    public static void midHole(int water1, int water2, int sand1, int sand2) {
        while (totalDist == 0) {
           teeOff();
           totalDist+=hit;
           strokes++;
           if (totalDist>water1 && totalDist <water2){
             mishits++;
               totalDist-=hit;
             System.out.println("You hit into the water! Start from last shot.");
           }
           if (totalDist>sand1 && totalDist <sand2){
             mishits++;
               System.out.println("You hit into the sand! Next shot has higher error.");
             sandError = 3;
           }
           toHole= Math.abs(holeLength - totalDist);           
           System.out.println(toHole + " to hole.");
        }
        while ( toHole>10) {
           chooseClub();
           sandError=0;
           totalDist+=hit;
           strokes++;
           if (totalDist>water1 && totalDist <water2){
             mishits++;
               totalDist-=hit;
             System.out.println("You hit into the water! Start from last shot.");
           }
           if (totalDist>sand1 && totalDist <sand2){
             mishits++;
               System.out.println("You hit into the sand! Next shot has higher error.");
             sandError = 3;
           }
           
           toHole= Math.abs(holeLength - totalDist);
           System.out.println(toHole + " to hole.");
           
           if (totalDist - 10 > holeLength) {
             overhit();
           }
        }
        if (toHole==0) {
           inHole=true;System.out.println("You made it!"); }
              
          else{
         while (!inHole) {
           putt(totalDist, toHole);
           
         }
        }
    }
    
    public static void midHole(int water1, int water2, int sand1, int sand2, int hill1, int hill2) {
        while (totalDist == 0) {
           teeOff();
           totalDist+=hit;
           strokes++;
           if (totalDist>sand1 && totalDist <sand2){
             mishits++;
               System.out.println("You hit into the sand! Next shot has higher error.");
             sandError = 3;
           }
             else if (totalDist>hill1 && totalDist <hill2){             
                System.out.println("The ball rolled backwards."); 
               totalDist= hill1;
           }
           if (totalDist>water1 && totalDist <water2){
             mishits++;
               totalDist-=hit;
             System.out.println("You hit into the water! Start from last shot.");
           }
           
           toHole= Math.abs(holeLength - totalDist);           
           System.out.println(toHole + " to hole.");
        }
        while ( toHole>10) {
           chooseClub();
           sandError=0;
           totalDist+=hit;
           strokes++;
           if (totalDist>sand1 && totalDist <sand2){
             mishits++;
               System.out.println("You hit into the sand! Next shot has higher error.");
             sandError = 3;
           }
             else if (totalDist>hill1 && totalDist <hill2){             
                System.out.println("The ball rolled backwards."); 
               totalDist= hill1;
           }
           if (totalDist>water1 && totalDist <water2){
             mishits++;
               totalDist-=hit;
             System.out.println("You hit into the water! Start from last shot.");
           }
           
           toHole= Math.abs(holeLength - totalDist);
           System.out.println(toHole + " to hole.");
           
           if (totalDist - 10 > holeLength) {
             overhit();
           }
        }
        if (toHole==0) {
           inHole=true;System.out.println("You made it!"); }
              
          else{
         while (!inHole) {
           putt(totalDist, toHole);
           
         }
        }
    }
    
    public static void beforeHole(/*String fileName*/) {
        //dispImage(fileName);
        System.out.println(); totalDist = 0;
        inHole = false;        
        toHole = holeLength;    
        strokes = 0;
    }
    
    public static void afterHole() {
        totalStrokes+=strokes;
        if (strokes==1) { hio++;System.out.println(" ~~~ Hole in one! ~~~ ");}
        if (strokes==par) { pars++;System.out.println(" ~~~ On par ~~~ ");}
        if (strokes== par-1) { birdies++;System.out.println(" ~~~ Birdie! ~~~ ");}
        System.out.println();        
        System.out.println(" ~~~ You completed this hole in " + strokes + " strokes ~~~");
        System.out.println(" ~~~ Your current score is " + totalStrokes + " strokes ~~~");        
        totalHoles++;
    }
    
    public static void ask()
    {
        Scanner in = new Scanner(System.in); 
        
        System.out.println("Move onto the next hole?");
        System.out.println("[Y] or [N]");
        String ask = in.next();
        if (ask.equalsIgnoreCase("n")) 
        {
            play = false; 
        }
    }
    
    public static void dispImage(String fileName, String text) {
         JFrame frame = new JFrame();
         Panel p2 = new Panel();
         ImageIcon icon = new ImageIcon(fileName+".jpg");         
         JLabel label = new JLabel(icon);
         JLabel label2= new JLabel();
         label2.setText("<html><span style='font-size:18px'>"+text+"</span></html>");
         
         frame.add(label);
         frame.setDefaultCloseOperation
         (JFrame.DISPOSE_ON_CLOSE);
         frame.pack();
         frame.setVisible(true);
         frame.add(label2, BorderLayout.SOUTH);
         frame.setDefaultCloseOperation
         (JFrame.DISPOSE_ON_CLOSE);
         frame.pack();
         frame.setVisible(true);       
    }
    
    public static void scorecard()
    {
        int halfStrokes = oneScore+twoScore+threeScore+fourScore+fiveScore+sixScore+sevenScore+eightScore+nineScore;;
        int totalStrokes = halfStrokes+tenScore+elevenScore+twelveScore+thirteenScore+fourteenScore+fifteenScore+sixteenScore+seventeenScore+eighteenScore;
        System.out.println("\t  Scorecard");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Hole: \t    Score: \t Par: \t| Hole:    Score: \t Par:                                            ");           
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("  1 \t     " + oneScore + " \t           3\t|  10 \t      "+tenScore+" \t    3");
        System.out.println("  2 \t     " + twoScore + " \t           4\t|  11 \t      "+elevenScore+" \t    4 ");
        System.out.println("  3 \t     " + threeScore + " \t           5\t|  12 \t      "+twelveScore+" \t    5");
        System.out.println("  4 \t     " + fourScore + " \t           4\t|  13 \t      "+thirteenScore+" \t    4");
        System.out.println("  5 \t     " + fiveScore + " \t           5\t|  14 \t      "+fourteenScore+" \t    4");
        System.out.println("  6 \t     " + sixScore + " \t           3\t|  15 \t      "+fifteenScore+" \t    4");
        System.out.println("  7 \t     " + sevenScore + " \t           4\t|  16 \t      "+sixteenScore+" \t    4");
        System.out.println("  8 \t     " + eightScore + " \t           4\t|  17 \t      "+seventeenScore+" \t    3");
        System.out.println("  9 \t     " + nineScore + " \t           4\t|  18 \t      "+eighteenScore+" \t    5");
        System.out.println("Total      " + halfStrokes + "            36\t| Total       "+totalStrokes+"            72");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Hazards / Overhits: " + mishits);
        System.out.println("Putts: " + putts);
        System.out.println("Luck: " + luck);
        System.out.println("Money: " + money);
    }
    
    public static void customize()
    {
        Scanner in = new Scanner(System.in);
        int skillPoints = 18 - difficulty - minusSkill;
        
        System.out.println(" ###  Customize your golfer's skills  ### ");
        System.out.println("You have "+skillPoints+" skills points to add, each category has a maximum of 10.");
        
        
         while (skillPoints>0) {
            System.out.println();
            System.out.println("Current skills: Power: " +playPower+ " , Accuracy: " +playAcc+ " , Putting: " +playPutt);
            System.out.println("How many points do you want to add to power?");
            int add = in.nextInt();
             while (0>add && 11<add) {
                 System.out.println("Select a valid number (0-9)");
                 add = in.nextInt();            
                }
            playPower+=add;
            skillPoints-=add; 
            
            System.out.println();
            System.out.println("Current skills: Power: " +playPower+ " , Accuracy: " +playAcc+ " , Putting: " +playPutt);
            System.out.println("You have " + skillPoints + " skill points left");
            System.out.println("How many points do you want to add to accuracy?");
            add = in.nextInt();
             while (0>add && 11<add) {
                 System.out.println("Select a valid number (0-9)");
                 add = in.nextInt();            
                }
             while ( add>skillPoints) {
                 System.out.println("You don't have enough points, try again");
                 add = in.nextInt();
                }
                
            playAcc+=add;
            skillPoints-=add;
            
            System.out.println();
            System.out.println("Current skills: Power: " +playPower+ " , Accuracy: " +playAcc+ " , Putting: " +playPutt);
            System.out.println("You have " + skillPoints + " skill points left");
            System.out.println("How many points do you want to add to putting?");
            add = in.nextInt();
             while (0>add && 11<add) {
                 System.out.println("Select a valid number (0-9)");
                 add = in.nextInt();            
                }
             while ( add>skillPoints) {
                 System.out.println("You don't have enough points, try again");
                 add = in.nextInt();
             }
            playPutt+=add;
            skillPoints-=add;
              
         }
         System.out.println("Current skills: Power: " +playPower+ " , Accuracy: " +playAcc+ " , Putting: " +playPutt);
         System.out.println("Continue to shop. any key...");
         String anyKey = in.next();
    }
    
    public static void puttCheck(int n)
    {
         if (!startFront && n>10) 
          n-=9;
         if (putts > 2*n  && playPutt>1) {
            playPutt--;
            luck--;
            System.out.println();
            System.out.println("You're not putting well today. You lose confidence in your putting.");
            System.out.println("Putting skill decreased to "+playPutt);
            System.out.println();
            System.out.println("any key to continue...");
            String anyKey = in.next();
         }
         else if (putts < (2*n - 1)  && playPutt<10) {
            playPutt++;
            luck++;
            System.out.println();
            System.out.println("You're putting well today. You gain confidence in your putting.");
            System.out.println("Putting skill increased to "+playPutt);
            System.out.println();
            System.out.println("any key to continue...");
            String anyKey = in.next();
        }
        
    }
    
    public static void accCheck(int n)
    {
         if (mishits > 1 && playAcc >2) {
            playAcc--;
            luck--;
            System.out.println();
            System.out.println("You're missing a lot today. You lose confidence in your accuracy.");
            System.out.println("Accuracy skill decreased to "+playAcc);
            System.out.println();
            System.out.println("any key to continue...");
            String anyKey = in.next();
         }
         else if (mishits==0 && playAcc<10) {
            playAcc++;
            luck++;
            System.out.println();
            System.out.println("You're aim is good today. You gain confidence in your accuracy.");
            System.out.println("Accuracy skill increased to "+playAcc);
            System.out.println();
            System.out.println("any key to continue...");
            String anyKey = in.next();
        }        
        pow = 0;
    }
    
    public static void randomEvent() {
        Scanner in = new Scanner(System.in);
        double rng = Math.random();
        if (rng>.06 && hasBall) rng-=.05;
        
        if (rng <.06) {
            System.out.println("<<<");            
            luck++;
            distDr+=10;
            System.out.println(" You find a better driver laying on the course. ");
            System.out.println(" Driver distance increased by ten. ");
            System.out.println(">>>");
        }
        else if (rng < .12) {
            System.out.println("<<<");                       
            System.out.println(" You just remembered that your caddy's name isn't Jim. ");
            System.out.println(">>>");
        }
        else if ( rng < .2 && playPower<10) {
            System.out.println("<<<");
            playPower++;
            luck++;
            System.out.println(" Your caddy passed you steroids. Power is increased.");
            System.out.println(" Current power: " +playPower);
            System.out.println(">>>");
        }
        else if ( rng < .3 ) {
            System.out.println("<<<");
            money+=150;
            luck++;
            System.out.println(" You find $150 on the ground. ");
            System.out.println(" Current money: " +money);
            System.out.println(">>>");
        }
        else if (rng <.4 && playPower>1 && !hasBook ) {
            System.out.println("<<<");
            playPower--;
            luck--;
            System.out.println(" The old lady in front of you out-drives you. You've never felt");
            System.out.println(" weaker. Power skill down.");
            System.out.println(" Current power: " +playPower);
            System.out.println(">>>");
        }
        else if (rng<.5 ) {
            System.out.println("<<<");  
            System.out.println(" You realize you never put deodorant on. ");
            System.out.println(" You smell awful. ");          
            System.out.println(">>>");
        }        
        else if (rng <.51 ) {            
            System.out.println("<<<");
            playPower-=3;
            playAcc-=3;
            playPutt-=3;
            luck-=8;
            System.out.println(" Your wife left you. Your life is in shambles. ");
            System.out.println(" All skills down by 2.");
            System.out.println("Current skills: Power: " +playPower+ " , Accuracy: " +playAcc+ " , Putting: " +playPutt);
            System.out.println(">>>");                    
        }
        
        
        else if (rng < .6 && playAcc>1) {
            System.out.println("<<<");
            playAcc--;
            luck--;
            System.out.println(" A bird pooped in your eyes. Accuracy down.");
            System.out.println(" Current accuracy: " +playAcc);
            System.out.println(">>>");           
        }
        else if (rng > .95 && playAcc>1 && playPutt>1 && playPower>1) {
            System.out.println("<<<");
            playAcc--;
            playPower--;
            playPutt--;
            luck -=3;
            System.out.println(" You missed the birth of your firstborn child because of you are");
            System.out.println(" playing golf. All skills down");
            System.out.println("Current skills: Power: " +playPower+ " , Accuracy: " +playAcc+ " , Putting: " +playPutt);
            System.out.println(">>>");
         }
        else if (rng > .88) {            
            System.out.println("<<<");
            playAcc++;
            luck++;
            System.out.println(" A new pair of glasses fall from the sky. Accuracy up.");
            System.out.println(" Current accuracy: " +playAcc);
            System.out.println(">>>");  
            
        }
           else if (rng <.7 && !hasHat && playAcc>1) {
            System.out.println("<<<");
            playAcc--;
            luck--;
            System.out.println(" The sun is shining right in your eyes. Accuracy down.");
            System.out.println(" Current accuracy: " +playAcc);
            System.out.println(">>>");
            }
            else if (rng <.7 && !hasGlove && playAcc>1) {
            System.out.println("<<<");
            playAcc--;
            luck--;
            System.out.println(" You've developed blisters on your hand. You are unable");
            System.out.println(" to firmly grasp the club. You accuracy suffers.");
            System.out.println("Current accuracy: " +playAcc);
            System.out.println(">>>");
            }
            
         else if (rng <.8 && playPutt<10) {
            System.out.println("<<<");
            playPutt++;
            luck++;
            System.out.println(" Your caddy tripped and it was funny. Putting skill up.");
            System.out.println(" Current putting: " +playPutt);
            System.out.println(">>>");
            }
          else if (rng>.82 && rng<.83) {
             System.out.println("<<<");            
             System.out.println(" Thanos snapped and removed half of your strokes ");
             System.out.println(">>>"); 
             luck+=23;
             totalStrokes= (int)(totalStrokes/2.0);
            }
                      
          else if (playPower == playPutt && playPutt == playAcc) {
            System.out.println("<<<"); 
            playAcc++;
            playPower++;
            playPutt++;
            luck+=3;
            System.out.println("Your skills are perfectly balanced, as all things should be.");            
            System.out.println(" All skills up!");
            System.out.println("Current skills: Power: " +playPower+ " , Accuracy: " +playAcc+ " , Putting: " +playPutt);

            System.out.println(">>>"); 
            }
         
            else {
            /*System.out.println("<<<");     
            int superstition = 0; superstition+=100;
            System.out.println(" A UFO flies overhead and abducts your caddy. What now?");            
            System.out.println(" Current superstition: "+superstition);
            System.out.println(">>>"); */
        }
        System.out.println("any key to continue...");
        String anyKey = in.next();
    }
    
    public static void frontNine() {
        if (play) {
          holeOne();      
          ask(); }
        if (play) {
          holeTwo();
          ask(); 
          randomEvent(); }
        if (play) {
          holeThree();
          ask(); }
        if (play) {
          holeFour();
          ask();
          puttCheck(4);
          randomEvent();}
        if (play) {
          holeFive();
          ask(); 
          accCheck(5); }
        if (play) {
          holeSix();
          ask(); 
          randomEvent(); }
        if (play) { 
          holeSeven();
          ask(); }
        if (play) {
          holeEight();
          ask(); }
        if (play) {
          holeNine();
          randomEvent(); }
        scorecard();
        System.out.println("Move onto the back nine or stop? (stats are recorded for 18 holes only)");
        System.out.println("[Y] or [N]");
        String ask = in.next();
        if (ask.equalsIgnoreCase("y")) 
        {
            backNine();
        }
    }
    
    public static void backNine() {
        if (play) {
          holeTen();
          ask(); }
        if (play) {
          holeEleven();
          ask();
          puttCheck(11);  }
        if (play) {
          holeTwelve();
          ask(); 
          randomEvent();}  
        if (play) {
          holeThirteen();
          ask();
          randomEvent(); }  
        if (play) {
          holeFourteen();
          ask(); }  
        if (play) {
          holeFifteen();
          ask();
          accCheck(15); }
        if (play) {
          holeSixteen();
          ask();
           }
        if (play) {
          holeSeventeen();
          ask();
          randomEvent();}
        if (play) {
          holeEighteen();          
           }
        System.out.println("Move onto the front Nine or stop? (stats are recorded for 18 holes only)");
        System.out.println("[Y] or [N]");
        String ask = in.next();
        if (ask.equalsIgnoreCase("y")) 
        {
            frontNine(); 
        }          
        scorecard();
    }
    
    public static void play() {
        String choice;
        System.out.println();
        System.out.println(" Start on the front 9 or back 9? ");
        System.out.println(" [F] Front         [B] Back      ");
        choice = in.next();
        
        if (choice.equalsIgnoreCase("f")) {
          frontNine(); startFront = true; }
        else if (choice.equalsIgnoreCase("B")) { backNine(); startFront = false; }
        
        System.out.println("any key to continue...");
        String anyKey = in.next();
        
        int addMoney = 5;
        if (nineScore>0) {
         addMoney = (int)((250)*(72.0/totalStrokes)); }
         System.out.println(" Your performance earned "+addMoney+" dollars.");
         money +=addMoney; 
    }
    
    public static void rngMode() {
        if (play) {
          holeOne(); 
          randomEvent(); 
          randomEvent(); 
          ask(); }
        if (play) {
          holeTwo();           
          randomEvent();
          randomEvent(); 
          ask();}
        if (play) {
          holeThree();
          randomEvent(); 
          randomEvent(); 
          ask(); }
        if (play) {
          holeFour();
          randomEvent(); 
          randomEvent(); 
          ask(); }
        if (play) {
          holeFive();
          randomEvent(); 
          randomEvent(); 
          ask(); }
        if (play) {
          holeSix();
          randomEvent(); 
          randomEvent(); 
          ask();  }
        if (play) { 
          holeSeven();
          randomEvent(); 
          randomEvent(); 
          ask(); }
        if (play) {
          holeEight();
          randomEvent(); 
          randomEvent(); 
          randomEvent(); 
          randomEvent(); 
          ask(); }
        if (play) {
          holeNine();
          randomEvent(); }
    }
    
    public static void save() throws IOException {
        PrintWriter outFile = new PrintWriter(new File(name+".txt"));
        
        outFile.println(difficulty);
        outFile.println(money);
        outFile.println(playPower);
        outFile.println(playAcc);
        outFile.println(playPutt);
        outFile.println(totalStrokes);
        outFile.println(playDriv);
        outFile.println(playIron);
        outFile.println(hasGap);
        outFile.println(hasOne);
        outFile.println(hasHy);
        outFile.println(hasPW);
        outFile.println(hasGlove);
        outFile.println(hasHat);
        outFile.println(hasBall);
        outFile.println(hasBook);
        outFile.println(totalHoles);
        outFile.println(hio);
        outFile.println(birdies);
        outFile.println(pars);
        outFile.println(wonT1);
        outFile.println(wonT2);
        outFile.println(wonT3);
        outFile.println(wonT4);
        
        outFile.close();
        
    }
    
    public static void userStats() {
        System.out.println("     *** Your Career Stats ***       ");
        System.out.println("-------------------------------------");
        System.out.println(totalHoles+" holes played     "+lastScore+" last score");
        System.out.println(money+" dollars        "+hio+" holes in one ");
        System.out.println(birdies+" birdies          "+pars+" pars ");
        System.out.println();
        checkInv();
        System.out.println("              Clubs:              ");
        System.out.println("[D] Driver                        ");
        System.out.println(iron1+"        "+hy+"      ");
        System.out.println("[5] 5 Iron        "+PW+"     ");
        System.out.println("[7] 7 Iron        [S] Sand Wedge      ");
        System.out.println("[9] 9 Iron        "+GW+"      ");
        System.out.println();
        System.out.println("           Tournaments            ");
        if (wonT1) System.out.println("Winner of Beginner Tournament");
        if (wonT2) System.out.println("Winner of Intermediate Tournament");
        if (wonT3) System.out.println("Winner of Difficult Tournament");
        if (wonT4) System.out.println("Winner of Masters Tournament");
        System.out.println();
            System.out.println("any key to continue to shop...");
            String anyKey = in.next();
        
    }
    
    public static void checkInv() {
        if (hasOne) 
         iron1 = "[1] 1 Iron";
        else 
         iron1 = "    ??    ";
        if (hasHy) 
         hy = "[H] Hybrid";
        else 
         hy = "    ??    ";
         if (hasGap) 
         GW = "[G] Gap Wedge";
        else 
         GW = "    ??    ";
        if (hasPW) 
         PW = "[P] Pitching Wedge";
        else 
         PW = "    ??    ";
    }
    
    public static void shop() {
        String shop; inShop = true; boolean inClubs = false;
        boolean inLess = false; boolean inAcc = false;
        boolean inBJ = false; boolean upDrive = false;
        boolean inTourns = false;
      while (inShop) {
        System.out.println();
        System.out.println();
        System.out.println(" $$ Welcome to the LinearGolf shop! $$ ");
        System.out.println(" You currently have $"+money+" to spend.");
        System.out.println();
        System.out.println("Pick what you'd like to look at: ");
        System.out.println("[C] Clubs ");
        System.out.println("[L] Lessons ");
        System.out.println("[A] Accessories ");
        System.out.println("[T] Tournaments ");
        System.out.println("[B] Blackjack ");
        System.out.println("[E] Exit ");
        System.out.println();
        shop = in.next();
        
        if (shop.equalsIgnoreCase("c"))           
           inClubs = true; 
        else if (shop.equalsIgnoreCase("l"))           
           inLess = true; 
        else if (shop.equalsIgnoreCase("a"))           
           inAcc = true;
        else if (shop.equalsIgnoreCase("e"))           
           inShop = false;
        else if (shop.equalsIgnoreCase("b"))        
           inBJ = true;
        else if (shop.equalsIgnoreCase("t"))
           inTourns = true;
        else {
           System.out.println("You did not choose an option, try again");
           shop = in.next(); }
        
        //Tournaments 
        while (inTourns) {
         System.out.println(" You currently have $"+money+" to spend.");
         System.out.println("** Tournament Passes **");
         System.out.println("Beat one tournament to unlock the next");
         System.out.println("[B] Beginner - $50   $500 prize. 9 Holes at Little Creek");
         System.out.println("[I] Intermediate - $250   $2,000 prize. 18 Holes at Little Creek ");
         System.out.println("[D] Difficult - $750   $5,000 prize. 9 Holes at The Bridges");
         System.out.println("[M] Masters - $2000   $25,000 prize. 18 Holes at The Bridges");
         System.out.println("[E] Exit ");
         System.out.println();
         shop = in.next();
        
         if (shop.equalsIgnoreCase("b") && money>49) {          
           money-=50; inT1 = true; inTourns=false;
           } 
         else if (shop.equalsIgnoreCase("i") && money>249 && wonT1) {      
           money-=250; inT2 = true; inTourns=false;
            }
         else if (shop.equalsIgnoreCase("d") && money>749 && wonT2) {         
           money-=750; inT3=true; inTourns=false;
         }
          else if (shop.equalsIgnoreCase("m") && money>1999 && wonT3) {
           money-=2000; inT4=true; inTourns=false; }
           
        else if (shop.equalsIgnoreCase("e")) {
            inTourns=false;
        }
         else {
           System.out.println("You did not choose a valid option and/or have enough money, try again");
           shop = in.next(); }
           if (inT1 || inT2 || inT3 || inT4) {
            System.out.println();
           System.out.println("~~ You entered in a tournament! ~~");
          System.out.println("   Leave shop to begin ");
            System.out.println(); }
        }

        
           
           
        //BlackJack
        while (inBJ) {
          run(); inBJ = false;  
        }
           
        //Clubs
        while (inClubs) {
         System.out.println(" You currently have $"+money+" to spend.");
         System.out.println("Pick a club: ");
         if (!upDrive)
           System.out.println("[D] Longer Driver - $350");
         if (!hasHy)
          System.out.println("[H] Hybrid - $650");
         if (!hasOne)
          System.out.println("[1] 1 Iron - $450");
         System.out.println("[7] Trusty 7 Iron - $7777");
         if (!hasPW)
          System.out.println("[P] Pitching Wedge - $375");
         if (!hasGap)
          System.out.println("[G] Gap Wedge - $210");
         System.out.println("[E] Exit ");
         System.out.println();
         shop = in.next();
        
         if (shop.equalsIgnoreCase("d") && money>349 && !upDrive) {          
           money-=350; distDr+=10; upDrive= true;
           } 
         else if (shop.equalsIgnoreCase("1") && money>449 && !hasOne) {      
           money-=450; hasOne = true;
            }
         else if (shop.equalsIgnoreCase("7") && money>7776) {         
           money-=7777; dist7+=77;
         }
          else if (shop.equalsIgnoreCase("h") && money>649 && !hasHy) {
           money-=650; hasHy = true;
        }
         else if (shop.equalsIgnoreCase("p") && money>374 && !hasPW) {
           money-=375; hasPW = true;
        }
         else if (shop.equalsIgnoreCase("g") && money>209 && !hasGap) {
           money-=210; hasGap = true;
        }
         else if (shop.equalsIgnoreCase("e"))           
            inClubs = false;
         else {
           System.out.println("You did not choose an option and/or have enough money, try again");
           shop = in.next(); }
        }
           
        //Lessons
        while (inLess) {
         System.out.println(" You currently have $"+money+" to spend.");
         System.out.println("Pick a lesson: ");
         System.out.println("[D] Driving - $650");
         System.out.println("[I] Irons - $600");
         System.out.println("[S] Short Game - $400");
         System.out.println("[A] Accuracy and Consistency - $450");
         System.out.println("[E] Exit ");
         System.out.println();
         shop = in.next();
        
        
         if (shop.equalsIgnoreCase("d") && money>649 && playDriv<11) {          
           money-=650; playDriv+=2; System.out.println("Your driving skill increased by 2"); }
         else if (shop.equalsIgnoreCase("i") && money>599 && playIron<11) {          
           money-=600; playIron+=2; System.out.println("Your iron skills increased by 2"); }
         else if (shop.equalsIgnoreCase("s") && money>399 && playPutt<11) {          
           money-=400; playPutt++; System.out.println("Your putting skill increased by 1"); }
         else if (shop.equalsIgnoreCase("a") && money>449 && playAcc<11) {          
           money-=450; playAcc++; System.out.println("Your accuracy skill increased by 1"); }
         else if (shop.equalsIgnoreCase("e"))           
           inLess = false;
         else {
           System.out.println("You did not choose an option and/or have enough money, try again");
           shop = in.next(); }
        }
        
        //Accessories
        while (inAcc) {
         System.out.println(" You currently have $"+money+" to spend.");
         System.out.println("Pick an Accessory: ");
         if (!hasGlove)
          System.out.println("[G] Golf Glove - $35");
         if (!hasHat)
          System.out.println("[H] Golf Hat - $55");
         if (!hasBall)
          System.out.println("[L] Lucky Ball - $750");
         if (!hasBook) 
         System.out.println("[B] Goldstein's Book - $1984 ");
         System.out.println("[E] Exit ");
         System.out.println();
         shop = in.next();
        
         if (shop.equalsIgnoreCase("g") && money>34 && !hasGlove)  {         
           money-=35; hasGlove = true; }
         else if (shop.equalsIgnoreCase("h") && money>54 && !hasHat) {          
           money-=55; hasHat = true; }
         else if (shop.equalsIgnoreCase("l") && money>749 && !hasBall) {          
           money-=750; hasBall = true; }
         else if (shop.equalsIgnoreCase("b") && money>1983 && !hasBook) {          
           money-=1984; System.out.println("Ignorance is Strength"); 
           System.out.println("War is Peace");
           System.out.println("Freedom is Slavery"); hasBook = true;}
         else if (shop.equalsIgnoreCase("e"))           
           inAcc = false;
         else {
           System.out.println("You did not choose an option and/or have enough money, try again");
           shop = in.next(); }
        }
      }
    }
    
    public static void main(String [] args) throws IOException
    {                                     
        LinearGolf player = new LinearGolf(); 
        player.play("LinearGolfStartUp.wav");        
        dispImage("title", "");
        
        welcome();
        String save;                 
        if (!rngMode){          
            player.play("LinearGolfShop.wav"); shop();                        
        } 
        
        if (inT1) {
             loopMusic = true; player.play("LinearGolf.wav"); beginnerTournament();   
        }
        else if (inT2) {
             loopMusic = true; player.play("LinearGolf.wav"); intermediateTournament();}
        else if (inT3) {
             loopMusic = true; player.play("LinearGolf.wav"); difficultTournament(); }  
        else if (!rngMode) {
            loopMusic = true;  player.play("LinearGolf.wav");
            play(); }
        else {  loopMusic = true; player.play("LinearGolf.wav"); rngMode(); }
        
                        
        System.out.println("Save? [Y] or [N]");
        save = in.next();
        if (save.equalsIgnoreCase("y")) {
            
            System.out.println("Enter your name (needed for save file)");
            name = in.next();
            save();
        }
          
        System.out.println("Thanks for playing!");
        System.exit(0);
        
    }
    
    public static void tScoreCheck(int h, int par) {
     System.out.println("any key to continue..."); String shop;
     shop = in.next();

     bestTPlayer = par - (int)(h/2);   
     diff = Math.abs(totalStrokes - bestTPlayer);
     tPlace = 1+diff;

     System.out.println("After "+h+" holes, your score is "+totalStrokes);
     if (totalStrokes<bestTPlayer) 
       System.out.println(" ~~ You are in first. You are "+diff+" strokes ahead ~~");  
     else if (totalStrokes==bestTPlayer) 
       System.out.println(" ~~ You are tied for first. ~~"); 
     else if (totalStrokes>bestTPlayer) {
       System.out.println(" ~~ You are "+diff+" strokes behind the leader. ~~ ");
       System.out.println("Current place: "+tPlace); behind=true; }
       System.out.println("any key to continue..."); 
     shop = in.next();
    }

     public static void endT1() {
     System.out.println();
     bestTPlayer = 33;
     tPlace = (totalStrokes-bestTPlayer)+1;
     if (totalStrokes<bestTPlayer) {
        System.out.println("*** You got first place! You win $500! *** ");
        money+=500; wonT1 = true; }
     else if (totalStrokes==bestTPlayer) {
        System.out.println("*** You tied for first place! You win $250! *** ");
        money+=250; wonT1 = true; }
     else if (totalStrokes==bestTPlayer+1) {
        System.out.println("*** You got 2nd place. You win $150! ***");
        money+=150; wonT1=false; }
     else if (totalStrokes==bestTPlayer+2) {
        System.out.println("*** You got 3rd place. You win $100! ***");
        money+=100; wonT1=false; }
     else {
        System.out.println("*** You got "+tPlace+"th place. ***"); wonT1=false; }

     if (wonT1) System.out.println("You can now move onto the next tournament");
     System.out.println();

}

public static void endT2() {
     System.out.println();
     bestTPlayer = 66;
     tPlace = (totalStrokes-bestTPlayer)+1;
     if (totalStrokes<bestTPlayer) {
        System.out.println("*** You got first place! You win $2000! *** ");
        money+=500; wonT2 = true; }
     else if (totalStrokes==bestTPlayer) {
        System.out.println("*** You tied for first place! You win $1000! *** ");
        money+=250; wonT2 = true; }
     else if (totalStrokes==bestTPlayer+1) {
        System.out.println("*** You got 2nd place. You win $500! ***");
        money+=150; wonT2=false; }
     else if (totalStrokes==bestTPlayer+2) {
        System.out.println("*** You got 3rd place. You win $200! ***");
        money+=100; wonT2=false; }
     else {
        System.out.println("*** You got "+tPlace+"th place. ***"); wonT2=false; }

     if (wonT2) System.out.println("You can now move onto the next tournament");
     System.out.println();

}

public static void endT3() {
     System.out.println();
     bestTPlayer = 30;
     tPlace = (totalStrokes-bestTPlayer)+1;
     if (totalStrokes<bestTPlayer) {
        System.out.println("*** You got first place! You win $5000! *** ");
        money+=5000; wonT3 = true; }
     else if (totalStrokes==bestTPlayer) {
        System.out.println("*** You tied for first place! You win $2500! *** ");
        money+=2500; wonT3 = true; }
     else if (totalStrokes==bestTPlayer+1) {
        System.out.println("*** You got 2nd place. You win $1250! ***");
        money+=1250; wonT3=false; }
     else if (totalStrokes==bestTPlayer+2) {
        System.out.println("*** You got 3rd place. You win $1000! ***");
        money+=1000; wonT3=false; }
     else {
        System.out.println("*** You got "+tPlace+"th place. ***"); wonT3=false; }

     if (wonT3) System.out.println("You can now move onto the next tournament");
     System.out.println();

}

public static void beginnerTournament() {
   String shop;
   System.out.println("~~ The beginner tournament has begun! ~~");
   System.out.println("You start on hole 1 with two other golfers.");
   System.out.println("You get ready to tee off... any key to continue...");
   holeOne(); if(totalStrokes>3)   System.out.println("You're not off to a great start...");
   holeTwo(); tScoreCheck(2, 7);
   holeThree();
   holeFour(); tScoreCheck(4, 16);
   holeFive();
   holeSix(); tScoreCheck(6, 24);
   holeSeven();
   holeEight(); tScoreCheck(8, 32);
   holeNine(); 
   System.out.println("any key to see results...");
   shop = in.next();
   endT1();
   System.out.println("any key to see scorecard...");
   shop = in.next();
   scorecard();
}
    
public static void intermediateTournament() {
    String shop;
   System.out.println("~~ The intermediate tournament has begun! ~~");
   System.out.println("You start on hole 10 with two other golfers.");
   System.out.println("You get ready to tee off... any key to continue...");
   holeTen(); if(totalStrokes>3)   System.out.println("You're not off to a great start...");
   holeEleven(); tScoreCheck(2, 7);
   holeTwelve();
   holeThirteen(); 
   holeFourteen(); tScoreCheck(5, 20);
   holeFifteen();
   holeSixteen();  tScoreCheck(7, 28);
   holeSeventeen();
   holeEighteen(); 
   System.out.println("any key to see current scorecard..."); shop = in.next();
   scorecard(); 
   System.out.println("any key to move on..."); shop = in.next();
   System.out.println("You feel relieved as you move onto the front nine. You've won here");
   System.out.println("before, you can do it again");
   holeOne(); 
   holeTwo(); tScoreCheck(11, 43);
   holeThree();
   holeFour(); tScoreCheck(13, 52);
   holeFive();
   holeSix(); tScoreCheck(15, 60);
   holeSeven();
   holeEight(); tScoreCheck(17, 68);
   holeNine(); 
   System.out.println("any key to see results...");
   shop = in.next();
   endT2();
   System.out.println("any key to see scorecard...");
   shop = in.next();
   scorecard();
}

public static void difficultTournament() {
   String shop;
   System.out.println("~~ The difficult tournament has begun! ~~");
   System.out.println("You start on hole 1 with two other golfers.");
   System.out.println("You've never seen this course before...");
   System.out.println("You get ready to tee off... any key to continue...");
   holeOne(1); if(totalStrokes>3)   System.out.println("You're not off to a great start...");
   holeTwo(1); tScoreCheck(2, 7);
   holeThree(1);
   holeFour(1); tScoreCheck(4, 15);
   holeFive(1);
   holeSix(1); tScoreCheck(6, 23);
   holeSeven(1);
   holeEight(1); tScoreCheck(8, 31);
   holeNine(1); 
   System.out.println("any key to see results...");
   shop = in.next();
   endT3();
   System.out.println("any key to see scorecard...");
   shop = in.next();
   scorecard();
}

    public static void run()
    {
        

        int bet;            // Amount user bets on a game.
        boolean userWins;   // Did the user win the game?

        System.out.println("Welcome to the game of blackjack.");
        System.out.println();


        while (true)
        {
            System.out.println("You have " + money + " dollars.");
            do
            {
                System.out.println("How many dollars do you want to bet?  (Enter 0 to end.)");
                System.out.print("? ");
                bet = scanner.nextInt();
                if (bet < 0 || bet > money)
                {
                    System.out.println("Your answer must be between 0 and " + money + '.');
                }
            } while (bet < 0 || bet > money);
            if (bet == 0)
            {
                break;
            }
            userWins = playBlackjack();
            if (userWins)
            {
                money = money + bet;
            } else
            {
                money = money - bet;
            }
            System.out.println();
            if (money == 0)
            {
                System.out.println("Looks like you've are out of money!");
                break;
            }
        }

        System.out.println();
        System.out.println("You leave with $" + money + '.');

    } // 

    private static boolean playBlackjack()
    {
        // Let the user play one game of Blackjack.
        // Return true if the user wins, false if the user loses.

        Vector dealerHand;   // The dealer's hand.
        Vector userHand;     // The user's hand.

        // Create an unshuffled deck of cards.
        deck = new int[52];
        int cardCt = 0; // How many cards have been created so far.
        for (int suit = 0; suit <= 3; suit++)
        {
            for (int value = 1; value <= 13; value++)
            {
                deck[cardCt] = value;
                cardCt++;
            }
        }
        currentPosition = 0;

        dealerHand = new Vector();
        userHand = new Vector();

        /*  Shuffle the deck, then deal two cards to each player. */

        shuffle();

        dealerHand.addElement(dealCard());
        dealerHand.addElement(dealCard());
        userHand.addElement(dealCard());
        userHand.addElement(dealCard());

        System.out.println();
        System.out.println();

        /* Check if one of the players has Blackjack (two cards totaling to 21).
        The player with Blackjack wins the game.  Dealer wins ties.
         */

        if (value(dealerHand) == 21)
        {
            System.out.println("Dealer has the " + showCard(getCard(dealerHand, 0)) + " and the " + showCard(getCard(dealerHand, 1)) + ".");
            System.out.println("User has the " + showCard(getCard(userHand, 0)) + " and the " + showCard(getCard(userHand, 1)) + ".");
            System.out.println();
            System.out.println("Dealer has Blackjack.  Dealer wins.");
            return false;
        }

        if (value(userHand) == 21)
        {
            System.out.println("Dealer has the " + showCard(getCard(dealerHand, 0)) + " and the " + showCard(getCard(dealerHand, 1)) + ".");
            System.out.println("User has the " + showCard(getCard(userHand, 0)) + " and the " + showCard(getCard(userHand, 1)) + ".");
            System.out.println();
            System.out.println("You have Blackjack.  You win.");
            return true;
        }

        /*  If neither player has Blackjack, play the game.  The user gets a chance
        to draw cards (i.e., to "Hit").  The while loop ends when the user
        chooses to "Stand" or when the user goes over 21.
         */

        while (true)
        {

            /* Display user's cards, and let user decide to Hit or Stand. */

            System.out.println();
            System.out.println();
            System.out.println("Your cards are:");
            for (int i = 0; i < userHand.size(); i++)
            {
                System.out.println("    " + showCard(getCard(userHand, i)));
            }
            System.out.println("Your total is " + value(userHand));
            System.out.println();
            System.out.println("Dealer is showing the " + showCard(getCard(dealerHand, 0)));
            System.out.println();
            System.out.print("Hit (H) or Stand (S)? ");
            char userAction;  // User's response, 'H' or 'S'.
            do
            {
                userAction = Character.toUpperCase(scanner.next().charAt(0));
                if (userAction != 'H' && userAction != 'S')
                {
                    System.out.print("Please respond H or S:  ");
                }
            } while (userAction != 'H' && userAction != 'S');

            /* If the user Hits, the user gets a card.  If the user Stands, the
            dealer gets a chance to draw and the game ends.
             */

            if (userAction == 'S')
            {
                // Loop ends; user is done taking cards.
                break;
            } else
            {  // userAction is 'H'.
                // Give the user a card.  If the user goes over 21, the user loses.
                int newCard = dealCard();
                userHand.addElement(newCard);
                System.out.println();
                System.out.println("User hits.");
                System.out.println("Your card is the " + showCard(newCard));
                System.out.println("Your total is now " + value(userHand));
                if (value(userHand) > 21)
                {
                    System.out.println();
                    System.out.println("You busted by going over 21.  You lose.");
                    System.out.println("Dealer's other card was the " + showCard(getCard(dealerHand, 1)));
                    return false;
                }
            }

        } // end while loop

        /* If we get to this point, the user has Stood with 21 or less.  Now, it's
        the dealer's chance to draw.  Dealer draws cards until the dealer's total is > 16.
         */

        System.out.println();
        System.out.println("User stands.");
        System.out.println("Dealer's cards are");
        System.out.println("    " + showCard(getCard(dealerHand, 0)));
        System.out.println("    " + showCard(getCard(dealerHand, 1)));
        while (value(dealerHand) <= 16)
        {
            int newCard = dealCard();
            System.out.println("Dealer hits and gets the " + showCard(newCard));
            dealerHand.addElement(newCard);
        }
        System.out.println("Dealer's total is " + value(dealerHand));

        /* Now, the winner can be declared. */

        System.out.println();
        if (value(dealerHand) > 21)
        {
            System.out.println("Dealer busted by going over 21.  You win.");
            return true;
        } else
        {
            if (value(dealerHand) == value(userHand))
            {
                System.out.println("Dealer wins on a tie.  You lose.");
                return false;
            } else
            {
                if (value(dealerHand) > value(userHand))
                {
                    System.out.println("Dealer wins, " + value(dealerHand) + " points to " + value(userHand) + ".");
                    return false;
                } else
                {
                    System.out.println("You win, " + value(userHand) + " points to " + value(dealerHand) + ".");
                    return true;
                }
            }
        }

    }  // end playBlackjack()

    public static int dealCard()
    {
        // Deals one card from the deck and returns it.
        if (currentPosition == 52)
        {
            shuffle();
        }
        currentPosition++;
        return deck[currentPosition - 1];
    }

    public static void shuffle()
    {
        // Put all the used cards back into the deck, and shuffle it into
        // a random order.
        for (int i = 51; i > 0; i--)
        {
            int rand = (int) (Math.random() * (i + 1));
            int temp = deck[i];
            deck[i] = deck[rand];
            deck[rand] = temp;
        }
        currentPosition = 0;
    }

    public static int getCard(Vector hand, int position)
    {
        // Get the card from the hand in given position, where positions
        // are numbered starting from 0.  If the specified position is
        // not the position number of a card in the hand, then null
        // is returned.
        if (position >= 0 && position < hand.size())
        {
            return ((Integer)hand.elementAt(position)).intValue();
        } else
        {
            return 0;
        }
    }

    public static int value(Vector hand)
    {
        // Returns the value of this hand for the
        // game of Blackjack.

        int val;      // The value computed for the hand.
        boolean ace;  // This will be set to true if the
        //   hand contains an ace.
        int cards;    // Number of cards in the hand.

        val = 0;
        ace = false;
        cards = hand.size();

        for (int i = 0; i < cards; i++)
        {
            // Add the value of the i-th card in the hand.
            int card;    // The i-th card;
            int cardVal;  // The blackjack value of the i-th card.
            card = getCard(hand, i);
            cardVal = getCardValue(card);  // The normal value, 1 to 13.
            if (cardVal > 10)
            {
                cardVal = 10;   // For a Jack, Queen, or King.
            }
            if (cardVal == 1)
            {
                ace = true;     // There is at least one ace.
            }
            val = val + cardVal;
        }

        // Now, val is the value of the hand, counting any ace as 1.
        // If there is an ace, and if changing its value from 1 to
        // 11 would leave the score less than or equal to 21,
        // then do so by adding the extra 10 points to val.

        if (ace == true && val + 10 <= 21)
        {
            val = val + 10;
        }

        return val;

    }
    public static int getCardValue(int card)
    {
        int result = card;
        switch (card)
        {
            case 11:
            case 12:
            case 13:
                result =  10;
        }
        return result;
    }
    public static String showCard(int card)
    {
        switch (card)
        {
            case 1:
                return "Ace";
            case 2:
                return "2";
            case 3:
                return "3";
            case 4:
                return "4";
            case 5:
                return "5";
            case 6:
                return "6";
            case 7:
                return "7";
            case 8:
                return "8";
            case 9:
                return "9";
            case 10:
                return "10";
            case 11:
                return "Jack";
            case 12:
                return "Queen";
            case 13:
                return "King";
            default:
                return "??";
        }
    }
    
    public static void holeFour(int nhl) {
        holeLength = 333 + (int)(15*(Math.random()*2-1));
        waterPos = holeLength-11;
        
        System.out.println();
        dispImage("c2h4", holeLength+"to hole, Water 155-235, Sand 280-"+waterPos);
        System.out.println();  par = 4;
        beforeHole();
        midHole(154, 236, 279, waterPos+1);
        afterHole();
        fourScore = strokes;
    }
    
    public static void holeTwo(int nhl) {
        holeLength = 333 + (int)(15*(Math.random()*2-1));
        waterPos = holeLength-11;
        
        System.out.println();
        dispImage("c2h2", holeLength+"to hole, Sand 200-243, Water behind greens");
        System.out.println();  par = 4;
        beforeHole();
        midHole(holeLength+11, 6000, 199, 244);
        afterHole();
        twoScore = strokes;
    }
    
    public static void holeOne(int nhl) {
        holeLength = 200 + (int)(15*(Math.random()*2-1));
        
        System.out.println();
        dispImage("c2h1", holeLength+"to hole, Sand 147-173");
        System.out.println();  par = 3;
        beforeHole();
        midHole(1, 1, 1, 1);
        afterHole();
        oneScore = strokes;
    }
    
    public static void holeThree(int nhl) {
        holeLength = 475 + (int)(15*(Math.random()*2-1));
        
        System.out.println();
        dispImage("c2h3", holeLength+"to hole, Water 369-406, Sand 421-449");
        System.out.println();  par = 5;
        beforeHole();
        midHole(368, 407, 420, 450);
        afterHole();
        threeScore = strokes;
    }
    
    public static void holeFive(int nhl) {
        holeLength = 485 + (int)(15*(Math.random()*2-1));
        
        System.out.println();
        dispImage("c2h5", holeLength+"to hole, Sand 251-284, Water 369-401");
        System.out.println();  par = 5;
        beforeHole();
        midHole(368, 402, 250, 285);
        afterHole();
        fiveScore = strokes;
    }
    
    public static void holeSix(int nhl) {
        holeLength = 193 + (int)(2*(Math.random()*2-1));
        
        System.out.println();
        dispImage("c2h6", holeLength+"to hole, Sand 54-81, River 175-180");
        System.out.println();  par = 3;
        beforeHole();
        midHole(174, 181, 53, 82);
        afterHole();
        sixScore = strokes;
    }

    public static void holeSeven(int nhl) {
        holeLength = 401 + (int)(15*(Math.random()*2-1));
        
        System.out.println();
        dispImage("c2h7", holeLength+"to hole, Downhill 176-244, Sand 251-284, Water 329-372");
        System.out.println();  par = 4;
        beforeHole();
        midHole(328, 373, 250, 285, 175, 245);
        afterHole();
        sevenScore = strokes;
    }
    
    public static void holeEight(int nhl) {
        holeLength = 386 + (int)(5*(Math.random()*2-1));
        
        System.out.println();
        dispImage("c2h8", holeLength+"to hole, Sand 343-367");
        System.out.println();  par = 4;
        beforeHole();
        midHole(1, 1, 342, 368);
        afterHole();
        eightScore = strokes;
        
    }
    
    public static void holeNine(int nhl) {
        holeLength = 367 + (int)(5*(Math.random()*2-1));
        
        System.out.println();
        dispImage("c2h9", holeLength+" to hole, River 140-153, Sand 161-213, Steep downihll 214 to green.");
        System.out.println();  par = 4;
        beforeHole();
        while (totalDist == 0) {
           teeOff();
           totalDist+=hit;
           strokes++;
            if (totalDist>213 && totalDist <holeLength-10){             
                System.out.println("The ball rolled backwards into the sand. Next shot has higher error."); 
               totalDist= 213;
               sandError=3;
           }
           if (totalDist>139 && totalDist <154){
             mishits++;
               totalDist-=hit;
             System.out.println("You hit into the water! Start from last shot.");
           }
           if (totalDist>160 && totalDist <213){
             mishits++;
               System.out.println("You hit into the sand! Next shot has higher error.");
             sandError = 3;
           }
           toHole= Math.abs(holeLength - totalDist);           
           System.out.println(toHole + " to hole.");
        }
        while ( toHole>10) {
           chooseClub();
           sandError=0;
           totalDist+=hit;
           strokes++;
            if (totalDist>213 && totalDist <holeLength-10){             
                System.out.println("The ball rolled backwards into the sand. Next shot has higher error."); 
               totalDist= 213;
               sandError=3;
           }
           if (totalDist>139 && totalDist <154){
             mishits++;
               totalDist-=hit;
             System.out.println("You hit into the water! Start from last shot.");
           }
           if (totalDist>160 && totalDist <213){
             mishits++;
               System.out.println("You hit into the sand! Next shot has higher error.");
             sandError = 3;
           }
           
           toHole= Math.abs(holeLength - totalDist);
           System.out.println(toHole + " to hole.");
           
           if (totalDist - 10 > holeLength) {
             overhit();
           }
        }
        if (toHole==0) {
           inHole=true;System.out.println("You made it!"); }
              
          else{
         while (!inHole) {
           putt(totalDist, toHole);
           
         }
        }
        afterHole();
        nineScore = strokes;
        
    }   
}