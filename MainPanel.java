import java.awt.*; import java.awt.event.*; import javax.swing.*; import javax.imageio.ImageIO; import java.io.File; import java.util.TimerTask; //import java.util.Timer;
/*
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
*/


public class MainPanel extends JFrame // this class sets up the main Panel that will be used to displa the main Program
{
  //
 
  Boolean isPressed = false;
  Character cha = new Character();
  Mob mod = new Mob();
  
  //TimerTask stuff = new TimerTask();
  CharacterShoot shot = new CharacterShoot();
  Panel pan = new Panel();
  Image map;
  static int xShift = 0;
  static int yShift = 0; 
  // these are the variables for the Panel class
  boolean burstDone;
  double clickedX;
  double clickedY;
  int slopeX;
  int slopeY;
  int slope;
  int currentX;
  int currentY;
  
  int projRatio;
  int counterKill;
  
  Image proj;
  
  static int[] slipX = {0,0,0};
  static int[] slipY = {0,0,0};
    
 
  private Timer mover ;
  private Timer mover2;
  private Timer mover3;
  private Timer mover4;
  private Timer mover5;
  private Timer mover6;
 
  
  
  
  
  public MainPanel()//this constructor initializes all the field variables for the main Panel, and then also sets the constraints for the main Panel
  {
    super("DungeonRunner");
    setSize(1000,800);
    setLocation(0,0);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setContentPane(pan);
    setResizable(false);
    setVisible(true);
    
    
    clickedX = 0;
    clickedY = 0;
    slopeX = 0;
    slopeY = 0;
    slope= 0;//g
    counterKill = 0;
    currentX = -1000;// to set the bullet out of the screen
    currentY = -1000;
    burstDone = true;
    projRatio = 0; 
    
  }
  
  public static void main(String[] args)//starts the run of the main Program
  {
        MainPanel rat = new MainPanel();
        rat.run();
  }
  
  public void run() // this method sets the start of the thread sequencnce which results in a constant loop of the game
  {
    cha.detirmine();
     mod.Spawn();
    
  }
  
  class Panel extends JPanel implements KeyListener, MouseListener // this is the class for the main Panel, creates the contents for the game
  {
    int startX = -2060 ;//+ xShift;
    int startY = -2200 ;//+ yShift;
    int setX = 5000 ; //+xShift;
    int setY = 5000 ;// +yShift;
    
    //this are for the slime movement
    private float progress = 0.0f; // a number between 0.0 and 1.0
    
    boolean a = false;
    boolean s = false;
    boolean d = false;
    boolean w = false;
    int health ;
   
    

        
    
    
    public Panel() // the constructor makes all the intializes all the field variables and also makes the listeners for the panel. Makes variables for a few permanent images on the panel
    {
      setFocusable(true);
      addKeyListener(this);
      addMouseListener(this);
      HeroMover move = new HeroMover();
      mover = new Timer(140, move);
      BurstMover move2 = new BurstMover();
      mover2 = new Timer(100, move2);//
      SlimeMover move3 = new SlimeMover();
      mover3 = new Timer(100, move3);//
      SlimeMover2 move4 = new SlimeMover2();
      mover4 = new Timer(100, move4);//
      SlimeMover3 move5 = new SlimeMover3();
      mover5 = new Timer(100, move5);//
      health = 0 ;
      
     
      try{
        map = ImageIO.read( new File("dun.png"));
      }
      catch(Exception e)
      {
        System.out.println("image not found.");
      }
      
      try{
        proj = ImageIO.read(new File("wizardProj.png"));
                            
         }
      catch(Exception e)
      {
        System.out.println("proj not found");
      } 
      
    }
    public void paintComponent(Graphics g)// this is where all the drawing of the components happens, and there is also an additional repaint() in the method which allows everthing to update at once
    {
      //if(counterKill == 0) mod.Spawn();
      super.paintComponent(g);
      g.drawImage(map,startX,startY,setX,setY,null);
      //
      g.drawImage(cha.hero[cha.currentFrame-1], 220,220, 270,270 ,null);
      
      for(int i = 0; i< shot.attack.length ; i++)
      g.drawImage(proj,shot.ProjX[i],shot.ProjY[i],projRatio,projRatio,null);
      
      for(int i = 0; i< mod.mobs.length ; i++)
      g.drawImage(mod.mobs[i],mod.mobX[i] + xShift + slipX[i] ,mod.mobY[i] + yShift + slipY[i] ,270,270,null);
      
      ((Graphics2D)g).setStroke(new BasicStroke(6));
      g.setColor(Color.WHITE);
      g.setFont(new Font("Serif", Font.BOLD, 20));
      g.drawString("Kills to the boss: " + ( 15 - counterKill),5 ,15);
      if(15 - counterKill <= 0) System.exit(1);
      
      g.setColor(Color.RED);
      g.fillRect(325,400,55, 10);
      g.setColor(Color.GREEN);
      g.fillRect(325,400,55 - health, 10);
      
      /*
      for(int i = 0; i< mod.mobs.length ; i++)
      if((mod.mobX[i] + xShift + slipX[i] + 40 >= 210 && mod.mobX[i] + xShift + slipX[i] + 40 <= 250) && (mod.mobY[i] + yShift + slipY[i] + 40 >= 210 && mod.mobY[i] + yShift + slipY[i] + 40 <= 250) )  health += 1;
      
      if(health >= 55) 
      */
      
      
      // for the start and stop of each mob
      for(int i = 0; i< mod.mobs.length ; i++)
      {
        
      if(mod.mobX[i] + xShift + slipX[i] <= 210 || mod.mobX[i] + xShift + slipX[i] >= 240 ) 
      {
        if(i==0)
        mover3.start();
        if(i==1)
        mover4.start();
        if(i==2)
        mover5.start();
        
      }
      if(mod.mobY[i] + yShift + slipY[i] <= 210 || mod.mobY[i] + yShift + slipY[i] >= 240 ) 
      {
        if(i==0)
        mover3.start();
        
        if(i==1)
        mover4.start();
        
        if(i==2)
        mover5.start();
        
        
      }
        
        
        
      }
     repaint();
    }    
    
    
   class HeroMover implements ActionListener// this sets the order of the frames of movement for the hero
   {
     public void actionPerformed(ActionEvent e)// this is the Action Listener method
     {
        if(cha.switchFrame == true)
        {
         cha.currentFrame = cha.startingFrame;
          cha.switchFrame = !cha.switchFrame;
        } 
        else  {
          cha.currentFrame = cha.endingFrame;
          cha.switchFrame = true;
        }
       
     }
   }
   
   class BurstMover implements ActionListener// this sets the speed, position, availabilit, and direction for all the bullet bursts
   {
     public void actionPerformed(ActionEvent e)// this is the method for the Action Listener
     {
    // currentX += slopeX/3;
    // currentY += slopeY/3;
     
     for(int j = 0 ; j < shot.ProjX.length ; j++)
     {
        shot.ProjX[j] = currentX += slopeX/3;
        shot.ProjY[j] = currentY += slopeY/3;
       
     }
     
   //  System.out.println("inHere");
     // System.out.println("CurrentX: " + currentX);
     // System.out.println("CurrentY: " + currentY);//g
     // System.out.println("SlopeX:  " + slopeX + "   SlopeY:  " +  slopeY);//
     /*
      System.out.println("ClicckedX :   " + clickedX  + "ClickedNotX:  " + clickedY); 
      System.out.println("slopeX :   " +slopeX  + "slopeY:  " + slopeY); 
      System.out.println("mobX :   " +mod.mobX[0]  + "mobY:  " + mod.mobY[0]);*/   
     
     
     for(int k = 0; k< shot.ProjX.length; k++)
     {
       for(int l= 0; l< mod.mobs.length; l++)
       {
         if((shot.ProjX[k] >= mod.mobX[l] + xShift + slipX[l] -140  && shot.ProjX[k] <= mod.mobX[l] + xShift + slipX[l] + 140) || (shot.ProjX[k] + 270 >= mod.mobX[l] + xShift + slipX[l] - 140 && shot.ProjX[k] + 550 <= mod.mobX[l] + xShift + slipX[l] + 140))
         {
         if((shot.ProjY[k] >= mod.mobY[l] + yShift + slipY[l] - 140 && shot.ProjY[k] <= mod.mobY[l] + yShift + slipY[l] + 140) || (shot.ProjY[k] + 270 >= mod.mobY[l] + yShift + slipY[l] - 140  && shot.ProjY[k] + 550 <= mod.mobY[l] + yShift + slipY[l] + 140))
         {
           shot.ProjX[k] = -1000;
           shot.ProjY[k] = -1000;
           mod.mobX[l]  = ((int)(Math.random()*(5201)) - 200);
           mod.mobY[l]  = ((int)(Math.random()*(5201)) - 200);
           counterKill += 1;
          // mod.rand = (int)(Math.random()*10 + 1);
           xShift = 0;
           yShift = 0;
           
           
         }
         }
       }
     }
     
     // this is for the movement of the bullets
     
     if(  shot.ProjX[4]   < -1000 ||  shot.ProjX[4]  > 1200) {
       currentX = -1000;
       currentY = -1000;
       
       burstDone = true;
       projRatio = 0;
       mover2.stop();
     }
     if( shot.ProjY[4]   < -1000 ||  shot.ProjY[4]  > 1200) //
     {
       currentX = -1000;
       currentY = -1000;
       burstDone = true;
       projRatio = 0;
       mover2.stop();
       
     }
     
     
     // this is to check the collision of the mob
     
    
     
     
     }
   }
   
   
   
  class SlimeMover implements ActionListener // this method ontrols the slime movement towards the character
  {
   // mod.Slime();
    
    public void actionPerformed(ActionEvent e)// this is the method for the Action Listener
    {
     
      for(int k = 0; k< mod.mobs.length; k++)
      {
        if(k == 0)
        {
          
      slipX[k]  += mod.slimeSlopeX[k];
      slipY[k]  += mod.slimeSlopeY[k];
      mod.Slime(xShift,yShift,slipX[k],slipY[k],k);
      
      if(mod.mobX[k] + xShift + slipX[k] >= 210 && mod.mobX[k] + xShift + slipX[k] <= 240 ) 
      {
        
        mover3.stop();
        
      }
      if(mod.mobY[k] + yShift + slipY[k] >= 210 && mod.mobY[k] + yShift + slipY[k] <= 240 ) 
      {
        
        mover3.stop();
        
      }
      
        }
    
      }
      
      for(int i = 0; i< mod.mobs.length ; i++)
      if((mod.mobX[i] + xShift + slipX[i] + 40 >= 200 && mod.mobX[i] + xShift + slipX[i] + 40 <= 500) && (mod.mobY[i] + yShift + slipY[i] + 40 >= 200 && mod.mobY[i] + yShift + slipY[i] + 40 <= 500) )  health += 2;
      
      if(health >= 55) System.exit(1);
    
  }

}
  
  
  class SlimeMover2 implements ActionListener // this method ontrols the slime movement towards the character
  {
   // mod.Slime();
    
    public void actionPerformed(ActionEvent e)// this is the method for the Action Listener
    {
      
     for(int k = 0; k< mod.mobs.length; k++)
      {
       if(k == 1){
       
      slipX[k]  += mod.slimeSlopeX[k];
      slipY[k]  += mod.slimeSlopeY[k];
      mod.Slime(xShift,yShift,slipX[k],slipY[k],k);
      if(mod.mobX[k] + xShift + slipX[k] >= 210 && mod.mobX[k] + xShift + slipX[k] <= 240 ) 
      {
        
        
        mover4.stop();
      }
      if(mod.mobY[k] + yShift + slipY[k] >= 210 && mod.mobY[k] + yShift + slipY[k] <= 240 ) 
      {
        
        
        mover4.stop();
      }
     
    }
      
     }
    }
     
    
    
  }
  
  class SlimeMover3 implements ActionListener // this method ontrols the slime movement towards the character
  {
   // mod.Slime();
    
    public void actionPerformed(ActionEvent e)// this is the method for the Action Listener
    {
      
     for(int k = 0; k< mod.mobs.length; k++)
      {
       if(k == 2){
       
      slipX[k]  += mod.slimeSlopeX[k];
      slipY[k]  += mod.slimeSlopeY[k];
      mod.Slime(xShift,yShift,slipX[k],slipY[k],k);
      if(mod.mobX[k] + xShift + slipX[k] >= 210 && mod.mobX[k] + xShift + slipX[k] <= 240 ) 
      {
        
        
        mover5.stop();
      }
      if(mod.mobY[k] + yShift + slipY[k] >= 210 && mod.mobY[k] + yShift + slipY[k] <= 240 ) 
      {
        
        
        mover5.stop();
      }
     
    }
      
     }
    }
     
    
    
  }
  
  
   
    public void keyPressed(KeyEvent e) { //this controls the frames of the hero when the up,down,left, and right is pressed
  
    int now = e.getKeyChar();// constant used to check the ASCII number of the key pressed
    
    
    if(now == 119) //KeyEvent.VK_UP
    {
      startY += 10;
      setY += 10;
      yShift += 10;
      cha.startingFrame = 3;
      cha.endingFrame = 4;
      cha.keyPressed = true;
      w = true;
      mover.start();
      
      
    }
    
    if(now == 115) //KeyEvent.VK_UP
    {
      startY -= 10;
      setY -= 10;
      yShift -= 10;
      cha.startingFrame = 5;
      cha.endingFrame = 6;
      cha.keyPressed = true;
      s = true;
      mover.start();
       
   }
    
    if(now == 100)//KeyEvent.VK_UP
    {
      startX -= 10;
      setX -= 10;
      xShift -= 10;
      cha.startingFrame = 1;
      cha.endingFrame = 2;
      cha.keyPressed = true;
      d = true;
      mover.start();
      /*
      cha.currentFrame = cha.startingFrame;
      cha.currentFrame = cha.endingFrame;
      */
    }
    
    if(now == 97)//KeyEvent.VK_LEFT
    {
      startX += 10;
      setX += 10;
      xShift += 10;
      cha.startingFrame = 7;
      cha.endingFrame = 8;
      cha.keyPressed = true;
      a = true;
      mover.start();
      /*
      cha.currentFrame = cha.startingFrame;
      cha.currentFrame = cha.endingFrame;
      */
    }
    
    if( w == true && d == true)
    {
      startY += 5;
      setY += 5;
      yShift += 5;
      
      startX -= 5;
      setX -= 5;
      xShift -= 5;
      
      cha.startingFrame = 3;
      cha.endingFrame = 4;
      cha.keyPressed = true;
      mover.start();
    }
    
     if( w == true && a == true)
    {
      startY += 5;
      setY += 5;
      yShift += 5;
      
      startX += 5;
      setX += 5;
      xShift += 5;
      
      cha.startingFrame = 3;
      cha.endingFrame = 4;
      cha.keyPressed = true;
      mover.start();
    }
     
     if( s == true && a == true)
    {
      startY -= 5;
      setY -= 5;
      yShift -= 5;
      
      startX += 5;
      setX += 5;
      xShift += 5;
      
      cha.startingFrame = 5;
      cha.endingFrame = 6;
      cha.keyPressed = true;
      mover.start();
    }
     
     if( s == true && d == true)
    {
      startY -= 5;
      setY -= 5;
      yShift -= 5;
      
      startX -= 5;
      setX -= 5;
      xShift -= 5;
      
      cha.startingFrame = 5;
      cha.endingFrame = 6;
      cha.keyPressed = true;
      mover.start();
    }
     
     
  
    repaint();
  }
  public void keyReleased(KeyEvent e){ // when the ke is released, the program stops the hero mover timer and stops the hero movement entirel
  cha.keyPressed = false;// basically stops movement of the character when the keys are not pressed
  mover.stop();
  w = false;
  a = false;
  s = false;
  d = false;
  if(cha.currentFrame == 1 || cha.currentFrame ==2) cha.currentFrame = 1;
  if(cha.currentFrame == 7 || cha.currentFrame ==8) cha.currentFrame = 8;
  if(cha.currentFrame == 3 || cha.currentFrame == 4) cha.currentFrame = 9;
  if(cha.currentFrame == 5 || cha.currentFrame == 6) cha.currentFrame = 10;
  
  repaint();
  }
  public void keyTyped(KeyEvent e) {}
  
  //this is where the Mouse Listener Methods start
  
  public  void mouseClicked(MouseEvent e){ // when the Mouse is clicked the coordintaes are recirded and a slope is made to calculate the path of the bullets and will send a command to start the timer of the bullets
  }  
  public  void mouseEntered(MouseEvent e){}  // this is a listener method
  public  void mouseExited(MouseEvent e){} // this is a listener method
  public  void mousePressed(MouseEvent e){
    while(burstDone)
    {
      
     
    burstDone = false;
    currentX = 270;
    currentY = 220;
    
    projRatio = 270;
    
    
    
    clickedX = e.getX();
    clickedY = e.getY();
    //
    slopeX = (int)clickedX - 400;
    slopeY = (int)clickedY - 340;
    //ptha therom
    
    mover2.start();
    }
 
  repaint();}  // this is a listener method
  public  void mouseReleased(MouseEvent e){} // this is a listener method
    
  }
  
  //
  
}



