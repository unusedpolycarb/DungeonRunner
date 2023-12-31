import java.awt.*;  //all imports below are important 
import java.awt.event.*;
import javax.swing.*; 
import javax.imageio.ImageIO; 
import java.io.File; 
import javax.swing.event.*; 
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.lang.InterruptedException;
import java.util.concurrent.locks.ReentrantLock;
import java.awt.*; import java.awt.event.*; import javax.swing.*; import javax.imageio.ImageIO; import java.io.File; import java.util.TimerTask;

public class PuzzleGame extends JFrame //new JFrame for testing new game
{
 Image character3 = new ImageIcon("wizard3.png").getImage(); //all listed below are Images of the character's movement
 Image character4 = new ImageIcon("wizard4.png").getImage();
 Image character7 = new ImageIcon("wizard7.png").getImage();
 Image character8 = new ImageIcon("wizard8.png").getImage();
 Image character5 = new ImageIcon("wizard5.png").getImage();
 Image character6 = new ImageIcon("wizard6.png").getImage();
 Image character1 = new ImageIcon("wizard1.png").getImage();
 Image character2 = new ImageIcon("wizard2.png").getImage();
 Image character11 = new ImageIcon("wizard10.png").getImage();
 Image character12 = new ImageIcon("wizard9.png").getImage();
 public PuzzleGame() //constructor for frame
 {
  super("Puzzle");
     setSize(1000,800);
     setLocation(0,0);
     setDefaultCloseOperation(EXIT_ON_CLOSE);
     IcePuzzle ip = new IcePuzzle();
     setContentPane(ip);
     setResizable(false);
     setVisible(true);
 }
 public static void main(String[] args) //runs method 
 {
  PuzzleGame pg = new PuzzleGame();
 }
    class IcePuzzle extends JPanel implements KeyListener, Runnable //class for the Panel that has the actual game on it 
    {
     int frame = 0;
     int xCord = 100;
     int yCord = -80;
     boolean sliding, switchFrame, mapOne, mapTwo, mapThree, wPressed, aPressed, sPressed, dPressed;
     javax.swing.Timer characterMovement;
     Thread t1;
     private final ReentrantLock lock = new ReentrantLock();
     public IcePuzzle() //constructor for the Panel with the game 
     {
      CharacterMover cm = new CharacterMover();
      characterMovement = new Timer(140, cm);
      setFocusable(true);
      addKeyListener(this);
      requestFocus();
      mapOne = true;
      t1 = new Thread(this);
      t1.start();
      ForPortal fp = new ForPortal();
      Timer portal = new Timer(140, fp);
      portal.start();
  }
     public void paintComponent(Graphics g) //paints the images when it moves and the character and map
     {
      super.paintComponent(g);
      if(mapOne)//checks what maze player is on
      {
       Image map = new ImageIcon("eazymaze1.gif").getImage();
       g.drawImage(map, 0, 0, 1000, 765, null);
       Image character = new ImageIcon("wizard10.png").getImage();
       if(frame == 0) g.drawImage(character, xCord, yCord, 270, 270, null);
      }
      if(mapTwo) //checks if player has completed the first map
      {
       Image map2 = new ImageIcon("easymaze2.gif").getImage();
       g.drawImage(map2, 0, 0, 1000, 800, null);
       Image character90 = new ImageIcon("wizard9.png").getImage();
       if(frame == 0) g.drawImage(character90, xCord, yCord, 270, 270, null);
      }
      if(mapThree) 
      {
       Image map3 = new ImageIcon("easymaze3.gif").getImage();
       g.drawImage(map3, 0, 0, 1000, 765, null);
       Image character76 = new ImageIcon("wizard9.png").getImage();
       if(frame == 0) g.drawImage(character76, xCord, yCord, 270, 270, null);
      }
   if(frame == 3) //switches between the 2 images we use to get the up movement
   {
    if(switchFrame == false) g.drawImage(character3, xCord, yCord, 270, 270, null);
    else g.drawImage(character4, xCord, yCord, 270, 270, null);
    repaint();
   }
   if(frame == 7) //switches between the 2 images we use to get the left movement
   {
    if(switchFrame == false) g.drawImage(character7, xCord, yCord, 270, 270, null);
    else g.drawImage(character8, xCord, yCord, 270, 270, null);
    repaint();
   }
   if(frame == 5) //switches between the 2 images we use to get the down movement
   { 
    if(switchFrame == false) g.drawImage(character5, xCord, yCord, 270, 270, null);
    else g.drawImage(character6, xCord, yCord, 270, 270, null);
    repaint();
   }
   if(frame == 1) //switches between the 2 images we use to get the right movement
   {
    
    if(switchFrame == false) g.drawImage(character1, xCord, yCord, 270, 270, null);
    else g.drawImage(character2, xCord, yCord, 270, 270, null);
    repaint();
   }
   if(frame == 11) g.drawImage(character12, xCord, yCord, 270, 270, null);
   if(frame == 12) g.drawImage(character11, xCord, yCord, 270, 270, null);
   if(frame == 13) g.drawImage(character8, xCord, yCord, 270, 270, null);
   if(frame == 14) g.drawImage(character1, xCord, yCord, 270, 270, null);
  }
  public void keyTyped(KeyEvent e) {}//this method is unused
  public void keyReleased(KeyEvent e)
  {
   if(frame == 3) frame = 11;
   if(frame == 5) frame = 12;
   if(frame == 7) frame = 13;
   if(frame == 1) frame = 14; 
  }
  public void run() //thread method to multithread
  {
   while(true) //making sure that the thread always runs this while it is active 
   {
    try
    {
     Thread.currentThread().sleep(1); //allows time to repaint
    }  
    catch(InterruptedException ex) //catches interruption of thread
    {
     System.err.println("not working");
    }
    if(wPressed) //checks for the key pressed
    {
     while(sliding) //allows slide movement
     {
      if(mapOne == true) //checks for the map used 
      {
       if(yCord == -100 || yCord == 520 && xCord >= 750|| yCord == 80 && xCord >= 500 && xCord <= 560 || xCord == 400 && yCord == 165 || xCord == -100 && yCord == 520 || xCord == 740 && yCord == 160|| xCord == 400 && yCord == 155 || xCord == -5 && yCord == 255) //hardcoded stoppers for the player so he doesn't slide through the icicles
       {
        sliding = false; //make slide stop
       }
       else 
       {
        yCord-=5; //movement of coordinate
        try 
        {
         Thread.currentThread().sleep(30); //allows time to repaint
        }
        catch(InterruptedException exception)
        {
         System.err.println("WEFWEF");
        }
       }
      }
      else if(mapTwo)//checks for the map used 
      {
       if(yCord == -100 || yCord == 220 && xCord == 820|| xCord == -100 && yCord == 460 || xCord == 425 && yCord == 300 || xCord == 795 && yCord == 220)  //hardcoded stoppers for the player so he doesn't slide through the icicles
       {
        sliding = false; //make slide stop
       }
       else
       {       
        yCord-=5;//changes coordinate for movement
        try
        {
         Thread.currentThread().sleep(30); //allows time to repaint
        }
        catch(InterruptedException exception)
        {
         System.err.println("WEFWEF");
        }
       }
      }
      else if(mapThree) //checks for the map used 
      {
       if(yCord == -100 || xCord == 330 && yCord == 365 || xCord == 790 && yCord == 285 || xCord == 820 && yCord == 285 || xCord == 415 && yCord == -25 || xCord == -100 && yCord == 130)
       { 
        sliding = false; //make slide stop
       }
       else
       {       
        yCord-=5;//changes coordinate for movement
        try
        {
         Thread.currentThread().sleep(30); //allows time to repaint
        }
        catch(InterruptedException exception)
        {
         System.err.println("WEFWEF");
        }
       }
      }
     }
    }
    else if(aPressed)
    {
     while(sliding)
     {
      if(mapOne == true)
      {
       if(xCord == -100 || xCord == 500 && yCord <= 550 && yCord >= 455 || xCord == 500 && yCord == 80 || xCord == 740 && yCord == 170 || xCord == 100 && yCord == 595 || xCord == 400 && yCord == 185 || xCord == -5 && yCord == 455 || xCord == 400 && yCord == 160|| xCord == -5 && yCord == 255 || xCord == 800 && yCord == 95 || xCord == 305 && yCord == -100) //hardcoded stoppers for the player so he doesn't slide through the icicles
       {
        sliding = false; //make slide stop
       }
       else
       {
        xCord-=5;//changes coordinate for movement
        try
        {
         Thread.currentThread().sleep(30); //allows time to repaint
        }
        catch(InterruptedException exception)
        {
         System.err.println("WEFWEF");
        }
       }
      }
      else if(mapTwo)
      {
       if(xCord == -100 || xCord == 590 && yCord == 465 || yCord == 220 && xCord == 795 || yCord == 465 && xCord == 595 || xCord == 105 && yCord == 300)  //hardcoded stoppers for the player so he doesn't slide through the icicles
       {
        sliding = false; //make slide stop
       }
       else
       {       
        xCord-=5;//changes coordinate for movement
        try
        {
         Thread.currentThread().sleep(30); //allows time to repaint
        }
        catch(InterruptedException exception)
        {
         System.err.println("WEFWEF");
        }
       }
      }
      else if(mapThree)
      {
       if(xCord == -100 || xCord == 790 && yCord == 595 || xCord == 415 && yCord == 285 || xCord == 495 && yCord == -100 || xCord == 495 && yCord == 595)
       { 
        sliding = false; //make slide stop
       }
       else
       {       
        xCord-=5;//changes coordinate for movement
        try
        {
         Thread.currentThread().sleep(30);//allows time to repaint
        }
        catch(InterruptedException exception)
        {
         System.err.println("WEFWEF");
        }
       }
      }
     }
    }
    else if(sPressed)
    { 
     while(sliding)
     {
      if(mapOne == true)
      {
       if(yCord == 595 || yCord == 180 && xCord >= -60 && xCord <= 0 || xCord == 640 && yCord == 185 || xCord == 400 && yCord == 455 || xCord == -5 && yCord == 540 || xCord == 820 && yCord == 95 || xCord == -100 && yCord == 180 || xCord == 305 && yCord == 90 || xCord == -5 && yCord == 535)
       { 
        sliding = false; //make slide stop
       }
       else
       {
        yCord+=5;//changes coordinate for movement
        try
        {
         Thread.currentThread().sleep(30);
        }
        catch(InterruptedException exception)
        {
         System.err.println("WEFWEF");
        }
       }
      }
      else if(mapTwo)
      {
       if(yCord == 595 || xCord == 630 && yCord == 465 || xCord == 630 && yCord == 465 || xCord >= -100 && xCord <= -65 && yCord == 300 || xCord == 595 && yCord == 465)
       {
        sliding = false; //make slide stop
       }
       else
       {       
        yCord+=5;//changes coordinate for movement
        try
        {
         Thread.currentThread().sleep(30);
        }
        catch(InterruptedException exception)
        {
         System.err.println("WEFWEF");
        }
       }
       if(xCord == 105 && yCord == 370)
       {
        mapThree = true;
        mapTwo = false;
        frame = 0;
        xCord = 105;
           yCord = 520;
       }
      }
      else if(mapThree)
      {
       if(yCord == 595 || xCord == 415 && yCord == 520 || xCord == -100 && yCord == -5 || xCord == 820 && yCord == 140)
       { 
        sliding = false; //make slide stop
       }
       else
       {       
        yCord+=5;//changes coordinate for movement
        try
        {
         Thread.currentThread().sleep(30);
        }
        catch(InterruptedException exception)
        {
         System.err.println("WEFWEF");
        }
       }
       if(xCord == 625 && yCord == 570) System.exit(1);
      }
     }
    }
    else if(dPressed)
    {
     while(sliding)
     {
      if(mapOne == true) 
      { 
       if(xCord == 820 || xCord == 150 && yCord <= -30 || xCord == -60 && yCord <= 280 && yCord >= 150 || xCord == 340 && yCord <= 150 && yCord >= 10|| xCord == 640 && yCord == 80 || xCord == 740 && yCord == 185 || xCord == -60 && yCord == 595|| xCord == 740 && yCord == 165 || xCord == 320 && yCord == 255 || xCord == 335 && yCord == 90 || xCord == 740 && yCord == 160)
       {
        sliding = false; //make slide stop
       }
       else 
       {
        xCord +=5;//changes coordinate for movement
        try
        {
         Thread.currentThread().sleep(30);
        }
        catch(InterruptedException exception)
        {
         System.err.println("WEFWEF");
        }
       }
       if(xCord == 320 && yCord == 255) //if character steps in portal it switches maps
       {
        mapTwo = true;
        mapOne = false;
        frame = 0;
        xCord = -100;
           yCord = 290;
       }
      }
      else if(mapTwo)
      {
       if(xCord == -65 && yCord >= 290 && yCord <= 305 || xCord == 820 || xCord == 630 && yCord == -100 || xCord == 425 && yCord == 460 || xCord == 630 && yCord == 300)
       { 
        sliding = false; //make slide stop
       }
       else
       {       
        xCord+=5;//changes coordinate for movement
        try
        {
         Thread.currentThread().sleep(30);
        }
        catch(InterruptedException exception)
        {
         System.err.println("WEFWEF");
        }
       }
      }
      else if(mapThree)
      {
       if(xCord == 820 || xCord == 135 && yCord == 520 || xCord == 330 && yCord == 595 || xCord == 625 && yCord == -5 || xCord == 625 && yCord == -25)
       { 
        sliding = false; //make slide stop
       }
       else
       {       
        xCord+=5;//changes coordinate for movement
        try
        {
         Thread.currentThread().sleep(30);
        }
        catch(InterruptedException exception)
        {
         System.err.println("WEFWEF");
        }
       }
       if(xCord == 615 && yCord == 595) System.exit(1);
      }
     }
    }
    repaint();
    aPressed = false;
    sPressed = false;
    wPressed = false;
    dPressed = false;
   }
  }
  public void keyPressed(KeyEvent e)//this method allows user to type wasd to check controls as well 
  {
   char phase = e.getKeyChar();
   if((phase == 'w'))
   {
    sliding = true;
    frame = 3;
    characterMovement.start(); //starts timer
    wPressed = true;
   }
   else if(phase == 'a')
   {
    sliding = true;
    frame = 7;
    characterMovement.start(); //starts timer
    aPressed = true;
   }  
   else if(phase == 's')
   { 
    sliding = true;
    frame = 5;
    characterMovement.start(); //starts timer
    sPressed = true;
   } 
   else if(phase == 'd')
   {
    sliding = true;
    frame = 1;
    characterMovement.start(); //starts timer
    dPressed = true;
   }
  }

  class CharacterMover implements ActionListener //timer class
  {
   public void actionPerformed(ActionEvent e) //every time the timer hits 140 milliseconds,
   {           // it turns the boolean to switch frame true and false
    switchFrame = !(switchFrame);  // whenever it is true, it draws one image, whenever its false
   }           // it draws the other image
  }
  class ForPortal implements ActionListener
  {
   public void actionPerformed(ActionEvent e)
   {
    repaint();
   }
  }
 }
}


