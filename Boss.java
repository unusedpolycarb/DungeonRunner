/*
 * Jainam Doshi and Ritvik dutta
 * Boss.java
 * 5.14.19
 */

import java.awt.*; import java.awt.event.*; import javax.swing.*; import javax.imageio.ImageIO; import java.io.File;

public class Boss extends JFrame // this class creates the JFrame
{
  Panel pan = new Panel();
  Character cha = new Character();
  HeroBullet bullet = new HeroBullet();
  BossMobs mod = new BossMobs();
  int player;
  int waterSpirit;
  int joeboticus;
  int pattern;
  boolean joebot = false;
  Timer mover;
  Timer mover2;
  Timer mover3;
  Timer mover4;
  Timer mover5;
  Timer mover6;
  Timer mover7;
  Timer mover8;
  Timer mover9;
  
 public Boss()// initializes methods of the JPanel
 {
   super("Boss");
    setSize(1000,800);
    setLocation(0,0);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setContentPane(pan);
    setResizable(false);
    setVisible(true);
    
    player = 75;
    waterSpirit = 1000; // remember to set this back to 1000 after you are done with joeBot
    joeboticus = 1000;
 }
  
  public static void main(String[] args)// this is the main method
  {
    Boss hoss = new Boss();
    hoss.run();
  }
  
  public void run()// runs the proramwd
  {
   cha.detirmine(); 
  }
  
  class Panel extends JPanel implements KeyListener, MouseListener
  {
    
    Image map;
    // variables for the hero
    int heroX = 450;
    int heroY = 450;
    int heroL1 = 175;
    int heroL2 = 175;
    boolean w = false;
    boolean a = false;
    boolean s = false;
    boolean d = false;
    int pattern = 40;
    
    // variables for the heros bullets
    int mouseClickX;
    int mouseClickY;
    
    public Panel()// stores all the variables for the panel
    {
      setFocusable(true);
      addKeyListener(this);
      addMouseListener(this);
      
      HeroMover move = new HeroMover();
      mover = new Timer(140, move);
      BulletMover move2 = new BulletMover();
      mover2 = new Timer(100, move2);
      DemWizMover move3 = new DemWizMover();
      mover3 = new Timer(130, move3);
      DemWizMoverShoot move4 = new DemWizMoverShoot();
      mover4 = new Timer(130, move4);
      WaterMover move5 = new WaterMover();
      mover5 = new Timer(140, move5);
      WaterProj1Mover move6 = new WaterProj1Mover();
      mover6 = new Timer(140, move6);
      LastMover move7 = new LastMover();
      mover7 = new Timer(140,move7);
      JoeMover move8 = new JoeMover();
      mover8 = new Timer(600,move8);
      JoeProjMover move9 = new JoeProjMover();
      mover9 = new Timer(150,move9);
      
      
      try{
        map = ImageIO.read( new File("mainBoss.png"));
      }
      catch(Exception e)
      {
        System.out.println("mainBoss not found.");
      }
      
      
    }
    public void paintComponent(Graphics g)// paints all the components on to the panel
    {
      super.paintComponent(g);
      
      mover2.start();
      mover3.start();
      mover5.start();
      
      
      g.drawImage(map,-400,0,1800,800,null);
      
      
      if(player <= 0) System.exit(1);
      
      g.setColor(Color.WHITE);
      /*
      for( int i = 0; i< 19 ; i++)
      {
        g.drawLine(i*77,0, i* 77, 800);
      }
      
      for( int i = 0 ; i < 13; i++)
        g.drawLine(0,i*62,1000, i*62); 
      */

      if(waterSpirit <= 0) joebot = true;
      if(joeboticus < 500) pattern = 130;
      if(joeboticus == 0) System.exit(1);;
      
      g.drawImage(cha.hero[cha.currentFrame-1],heroX,heroY,heroL1,heroL2,null);
      
      g.setColor(Color.WHITE);
      //g.drawRect(heroX + 50, heroY + 50, heroL1 - 100, heroL2 - 100);
      
      //these variables set the heatlh box for the player
        g.setColor(Color.RED);
        g.fillRect(heroX  + 50, heroY +  125, 75, 5);
        g.setColor(Color.GREEN);
        g.fillRect(heroX  + 50, heroY +  125, player, 5);
      
      for(int i = 0 ; i < bullet.wizardShot.length ; i++)
      {
        if(bullet.appear[i] == true)
        {
       // System.out.println("inHere3");
        g.drawImage(bullet.wizardShot[i],bullet.currentX[i] -70 ,bullet.currentY[i] -70,150,150,null);
        
        
        for(int j = 0 ; j < bullet.wizardShot.length ; j++)
       {
          for(int k = 0 ; k < mod.demon.length ; k++)
          {
          if(((bullet.currentX[j] - 70  >= mod.currentX[k]  &&  bullet.currentX[j]- 70 <= mod.currentX[k] + 75 ) && (bullet.currentY[j]- 70 >= mod.currentY[k]  &&  bullet.currentY[j]- 70 <= mod.currentY[k] + 75 )))
         {
           bullet.appear[j] = false;
           //System.out.println(bullet.appear[j]);
           bullet.currentX[j] = 0;
           bullet.currentY[j] = 0;
           bullet.bulletX[j] = 0;
           bullet.bulletY[j] = 0;
           // for the demon wizards
           mod.spawn[k] = false;
           mod.demonX[k] = 0;
           mod.demonY[k] = 0;
           mod.currentX[k]  = 250 + 120*k;
           mod.currentY[k]  = 200 ;
         }
          
          
          else if( (bullet.currentX[j] <= 0 ||  bullet.currentX[j]>=1000) || (bullet.currentY[j] <= 0 ||  bullet.currentY[j]>=800))
          {
           bullet.appear[j] = false;
           bullet.currentX[j] = 0;
           bullet.currentY[j] = 0;
           bullet.bulletX[j] = 0;
           bullet.bulletY[j] = 0;
          }
          
          }
        
       }
       
        // this for loop will check the collisions for the water spirit
        for(int j = 0 ; j < bullet.wizardShot.length ; j++)
       {
          for(int k = 0 ; k < mod.water.length ; k++)
          {
            if(((bullet.currentX[j]   >= mod.waterX[k]  &&  bullet.currentX[j] <= mod.waterX[k] + 100 ) && (bullet.currentY[j] >= mod.waterY[k]  &&  bullet.currentY[j] <= mod.waterY[k] + 100 )))
         {
           bullet.appear[j] = false;
           waterSpirit -= 30;
           //System.out.println(bullet.appear[j]);
           bullet.currentX[j] = 0;
           bullet.currentY[j] = 0;
           bullet.bulletX[j] = 0;
           bullet.bulletY[j] = 0;
            }
            
          }
        }
        
      }
      }
      
      // this is when the final boss is on the field
      if(joebot == true)
      {
        //mover9.setInitialDelay(5000);
        mover8.start();
        mover9.start();
        mover3.stop();
        mover4.stop();
        mover5.stop();
        mover6.stop();
        g.drawImage(mod.joeBot[mod.currentImage] , mod.finalX, mod.finalY, 125, 125, null);
        
        for(int i = 0; i < mod.bossProj.length ; i++)
        g.drawImage(mod.bossProj[i],mod.currentBossX[i], mod.currentBossY[i], pattern, pattern, null);
        
       // g.drawRect(mod.currentBossX[0], mod.currentBossY[0], pattern, pattern);
        
        g.setColor(Color.RED);
        g.fillRect(0,0,1000,62);
        g.setColor(Color.GREEN);
        g.fillRect(0,0,joeboticus,62);
        //this checks the collisons for the final boss
        for(int j = 0 ; j < bullet.wizardShot.length ; j++)
       {
          
          if(((bullet.currentX[j]   >= 430  &&  bullet.currentX[j] <= 505 ) && (bullet.currentY[j] >= 300  &&  bullet.currentY[j] <= 425 )))
         {
           bullet.appear[j] = false;
           //System.out.println(bullet.appear[j]);
           bullet.currentX[j] = 0;
           bullet.currentY[j] = 0;
           bullet.bulletX[j] = 0;
           bullet.bulletY[j] = 0;
           joeboticus -= 20;
           
         }
      }
      }
      
      
      if(joebot == false)
      {
      // this is for the spawnin of the demon wizards
      if(mod.spawn[0] == false && mod.spawn[1] == false)
      {
        mod.spawn[0] = true;
        mod.spawn[1] = true;
        }
      
      for(int i = 0 ; i < mod.demon.length ; i++){
        //System.out.println("inHere3");
        if(mod.spawn[i] == true)
        {
        g.drawImage(mod.demon[i],mod.currentX[i] , mod.currentY[i] ,175,175,null);
        }
      }
      
       // definin the slope for hte demon shooter
      if(mod.appear[0]  == false && mod.appear[1]== false)
      {
        // this sets the bullet movement for the first demon wizard
        mod.currentProjX[0] = mod.currentX[0];
        mod.currentProjY[0] = mod.currentY[0];
        mod.demonProjX[0] = (mod.currentProjX[0] - heroX)/10;
        mod.demonProjY[0] = (mod.currentProjY[0] - heroY)/10;
        // this sets the bullet movement for the second demon wizard
        mod.currentProjX[1] = mod.currentX[1];
        mod.currentProjY[1] = mod.currentY[1];
        mod.demonProjX[1] = (mod.currentProjX[1] - heroX)/10;
        mod.demonProjY[1] = (mod.currentProjY[1] - heroY)/10;
      }
       // mover4.setDelay(3000);
        mover4.start();
       
        if(mod.appear[0] == false && mod.appear[1] == false)
        {
          mod.appear[0] = true;
          mod.appear[1] = true;
        }
        
        // this contolsthe appearance of the demon wizard bullets
        for( int i = 0; i < mod.demonProj.length ; i++)
        {
          
        if(mod.appear[i] == true && mod.spawn[i] == true )
        {
        g.drawImage(mod.demonProj[i],mod.currentProjX[i] , mod.currentProjY[i] ,175,175,null);
        }
        
        }
        
        if(mod.waterAppear[0] == false && mod.waterAppear[1] == false )
        {
         mod.currentProj1X[0] = mod.waterX[0];
         mod.currentProj1Y[0] = mod.waterY[0];
         mod.waterAppear[0] = true;
         // this will reset the variables for the second static bullet of the water spirit
         mod.currentProj1X[1] = mod.waterX[0];
         mod.currentProj1Y[1] = mod.waterY[0];
         mod.waterAppear[1] = true;
         
        }
        
        //mover6.initialDelay(2000);
        mover6.start();
        for(int i = 0; i < mod.waterProj1.length ; i++){
         if(mod.waterAppear[i] == true)
        g.drawImage(mod.waterProj1[i], mod.currentProj1X[i],mod.currentProj1Y[i], 50, 50, null); 
        }
        // this will be where all the last projectile stuff will be instantiated
        if(mod.lastAppear == false)
        {
         mod.currentLastX = mod.waterX[0];
         mod.currentLastY = mod.waterY[0];
         mod.lastAppear = true;
        }
        mover7.start();
        if(mod.lastAppear == true)
          g.drawImage(mod.lastProj, mod.currentLastX,mod.currentLastY, 50, 50, null); 
        
        //this is where the water spirit is printed
      g.drawImage(mod.water[0],mod.waterX[0],mod.waterY[0],100,100,null);
       
      
      // this will set the health bar for the water spirit
      g.setColor(Color.RED);
      g.fillRect(0,0,1000,62);
      g.setColor(Color.GREEN);
      g.fillRect(0,0,waterSpirit,62);
        
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
     
      class BulletMover implements ActionListener// this sets the speed, position, availabilit, and direction for all the bullet bursts
   {
     public void actionPerformed(ActionEvent e)// this is the method for the Action Listener
     {
       for(int j = 0 ; j < bullet.wizardShot.length ; j++)
     {
         if(bullet.appear[j] == true)
         {
         //  System.out.println("inHere2");
        bullet.currentX[j] += bullet.bulletX[j];
        bullet.currentY[j] += bullet.bulletY[j];
        
         }
     }
       
       
       
     }
   }
      
      class DemWizMover implements ActionListener// this sets the speed, position, availabilit, and direction for all the bullet bursts
   {
     public void actionPerformed(ActionEvent e)// this is the method for the Action Listener
     {
       for(int i = 0 ; i< mod.demon.length ; i++)
       {
         //System.out.println("inHere2");
        mod.demonX[i] = ((heroX + 45) - mod.currentX[i])/30;
        mod.demonY[i] = ((heroY + 45) - mod.currentY[i])/30;
        
        mod.currentX[i] += mod.demonX[i];
        mod.currentY[i] += mod.demonY[i];
        //System.out.println(mod.currentX[i]);
       }
     
     }
   }
      
      class DemWizMoverShoot implements ActionListener// this sets the speed, position, availabilit, and direction for all the bullet bursts
   {
     public void actionPerformed(ActionEvent e)// this is the method for the Action Listener
     {
       
       for(int i = 0 ; i< mod.demonProj.length ; i++)
       {
        if(mod.appear[i] == true)
        {
          
         
         // this is to the slope of the bullet to the mob
         mod.currentProjX[i]  -= mod.demonProjX[i];
         mod.currentProjY[i]  -= mod.demonProjY[i];
         
         if((mod.currentProjX[i] >= heroX && mod.currentProjX[i] <= heroX + 50) && (mod.currentProjY[i] >= heroY  && mod.currentProjY[i]  <= heroY + 50))
         {
         System.out.println("is hit");
         player -= 1;
         }
         
         if((mod.currentProjX[i] >= 1000 || mod.currentProjX[i] <= 0) || (mod.currentProjY[i] >= 800 || mod.currentProjY[i] <= 0))
         {
          mod.appear[i] = false;  
          mod.currentProjX[i] = 0;
          mod.currentProjY[i] = 0;
          
         }
         
        }
         
       }
         
       
       
     }
   }
      // this starts the water spirit mover class
      
       class WaterMover implements ActionListener// this sets the speed, position, availabilit, and direction for all the bullet bursts
   {
     public void actionPerformed(ActionEvent e)// this is the method for the Action Listener
     {
        if(mod.waterX[0]  <= 693  && mod.left == false)
        {
            mod.waterX[0] += 10;
            if(mod.waterX[0] >= 690)
            mod.left  = true;
        }
        
        if(mod.waterX[0]  >= 280  && mod.left == true)
        {
          mod.waterX[0] -= 10;
            if(mod.waterX[0] <= 310 )
            mod.left  = false;
        }
     }
   }
       
       class WaterProj1Mover implements ActionListener// this sets the speed, position, availabilit, and direction for all the bullet bursts
   {
     public void actionPerformed(ActionEvent e)// this is the method for the Action Listener
     {
      mod.currentProj1X[0] += mod.waterProj1X[0]/2;
      mod.currentProj1Y[0] -= mod.waterProj1Y[0]/2;
      // this sets the slope for the second boss bullet
      mod.currentProj1X[1] += mod.waterProj1X[1]/2;
      mod.currentProj1Y[1] += mod.waterProj1Y[1]/2;
      
      if((mod.currentProj1X[1] <= 0 || mod.currentProj1X[1] >= 1000) || (mod.currentProj1Y[1] <= 0 || mod.currentProj1Y[1] >=800))
      {
       mod.waterAppear[1] =  false; 
      
      }
      
      if((mod.currentProj1X[0] <= 0 || mod.currentProj1X[0] >= 1000) || (mod.currentProj1Y[0] <= 0 || mod.currentProj1Y[0] >=800))
      {
       mod.waterAppear[0] =  false;
       
      }
      
      for(int i = 0; i < mod.waterProj1.length; i++)
      if((mod.currentProj1X[i] >= heroX &&  mod.currentProj1X[i] <= heroX + 70)  && (mod.currentProj1Y[i] >= heroY && mod.currentProj1Y[i] <= heroY + 70) || (mod.currentProj1X[i]+ 50 >= heroX &&  mod.currentProj1X[i] +50 <= heroX + 70)  && (mod.currentProj1Y[i]+50 >= heroY && mod.currentProj1Y[i] + 50 <= heroY + 70)) 
      { System.out.println("boss has sturck");
        player -= 2;
      }
     }
   }
       
       
        class LastMover implements ActionListener// this sets the speed, position, availabilit, and direction for all the bullet bursts
   {
     public void actionPerformed(ActionEvent e)// this is the method for the Action Listener
     {
       if(mod.currentLastY  <= 372)
       {
      mod.lastProjX = (heroX - mod.currentLastX)/4;
      mod.lastProjY = (heroY - mod.currentLastY)/4;
      
      mod.currentLastX += mod.lastProjX;
      mod.currentLastY += mod.lastProjY;
       }
      
       if(mod.currentLastY >= 372)
       {
         mod.currentLastX += mod.lastProjX;
         mod.currentLastY += mod.lastProjY;
         
         if((mod.currentLastX >= 1000 ||  mod.currentLastX <= 0)  || (mod.currentLastY >= 800 || mod.currentLastY <= 0) ) 
        mod.lastAppear = false;
       }
       
       if((mod.currentLastX >= heroX &&  mod.currentLastX <= heroX + 100)  && (mod.currentLastY >= heroY && mod.currentLastY <= heroY + 100) || (mod.currentLastX+ 10 >= heroX &&  mod.currentLastX +10 <= heroX + 100)  && (mod.currentLastY+10 >= heroY && mod.currentLastY + 10 <= heroY + 100)) 
         {
           mod.lastAppear = false;
           player -=1;
         }
     }
   }
        
         class JoeMover implements ActionListener// this sets the speed, position, availabilit, and direction for all the bullet bursts
   {
     public void actionPerformed(ActionEvent e)// this is the method for the Action Listener
     {
       if(joeboticus > 500)
       {
         mod.currentImage += 1;
         if(mod.currentImage == 2) mod.currentImage = 0;
         
       }
       if(joeboticus < 500)
       {
         mod.currentImage += 1;
         if(mod.currentImage == 4) mod.currentImage = 2;
         
       }
       
         
         
       
     }
   }
         
         class JoeProjMover implements ActionListener// this sets the speed, position, availabilit, and direction for all the bullet bursts
   {
     public void actionPerformed(ActionEvent e)// this is the method for the Action Listener
     {
       //add the moving commands for the first bullet an dhte hit box for the character, then do thsi 8 more times, and add an attack animations, just keep it going as long as the bullets are ont he field
       // then add health for both, and when the boss gets to half health draw a cirble that increases in size that basically stunts your health half way and that will be it fot he boss leavel
       for(int i = 0; i < mod.bossProj.length ; i++)
       {
        // if( mod.bossAppear[0] == true && mod.bossAppear[1] == true)
         //{
         mod.currentBossX[i] += mod.bossProjX[i];
         mod.currentBossY[i] += mod.bossProjY[i];
       //  }
         
         if(mod.currentBossX[i] >= 1000 || mod.currentBossX[i] <= 0 || mod.currentBossY[i] >= 800 || mod.currentBossY[i] <=0)
         {
           mod.currentBossX[i] = mod.finalX + 20;
           mod.currentBossY[i] = mod.finalY + 20;
           
         }
         
         // this will check the collison with the player
         if( mod.currentBossX[i] + pattern/2 >= heroX +50 && mod.currentBossX[i] + pattern/2 <= (heroX + 50) + (heroL1-100) && mod.currentBossY[i] >= heroY + 50  && mod.currentBossY[i] <= (heroY + 50 + heroL2 - 100))
         {
          System.out.println("Joebot has sturck again"); 
          player -= 7;
         }
         
         /*
         if( mod.bossAppear[0] == false && mod.bossAppear[1] == false)
         {
         mod.bossAppear[0] = true;
         mod.bossAppear[1] = true;
         }
         */
         
       }
     }
   }
      
    
    public void keyPressed(KeyEvent e) { //this controls the frames of the hero when the up,down,left, and right is pressed
  
    int now = e.getKeyChar();// constant used to check the ASCII number of the key pressed
    
    
    if(now == 119) //KeyEvent.VK_UP
    {
      if( heroY >=800 || heroY <= 0) heroY+=0;
      else{
      heroY -= 10;
      }
      
      cha.startingFrame = 3;
      cha.endingFrame = 4;
       cha.keyPressed = true;
      w = true;
      mover.start();
      
      
    }
    
    if(now == 115) //KeyEvent.VK_UP
    {
     if( heroY >=800 || heroY <= 0) heroY+=0;
      else{
      heroY += 10;
      }
      
      
      cha.startingFrame = 5;
      cha.endingFrame = 6;
      cha.keyPressed = true;
      s = true;
      mover.start();
       
   }
    
    if(now == 100)//KeyEvent.VK_UP
    {
      
      if( heroX >= 1000 || heroX <= 0) heroX+=0;
      else{
       heroX += 10;
      }
      
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
      
     if( heroX >= 1000 || heroX <= 0) heroX+=0;
      else{
       heroX -= 10;
      }
      
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
      heroX += 5;
      heroY -= 5;
      
      cha.startingFrame = 3;
      cha.endingFrame = 4;
      cha.keyPressed = true;
      mover.start();
    }
    
     if( w == true && a == true)
    {
      heroX -= 5;
      heroY -= 5;
      
      cha.startingFrame = 3;
      cha.endingFrame = 4;
      cha.keyPressed = true;
      mover.start();
    }
     
     if( s == true && a == true)
    {
      heroX -= 5;
      heroY += 5;
      
      cha.startingFrame = 5;
      cha.endingFrame = 6;
      cha.keyPressed = true;
      mover.start();
    }
     
     if( s == true && d == true)
    {
      heroX += 5;
      heroY += 5;
      
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
   
   // this is where the MouseListener will start
    
   public  void mouseClicked(MouseEvent e){} // when the Mouse is clicked the coordintaes are recirded and a slope is made to calculate the path of the bullets and will send a command to start the timer of the bullets  
  public  void mouseEntered(MouseEvent e){}  // this is a listener method
  public  void mouseExited(MouseEvent e){} // this is a listener method
  public  void mousePressed(MouseEvent e){
  
    
      //System.out.println("inHere0");
      if(bullet.appear[0] == false)
      {
       // System.out.println("inHere");
        bullet.appear[0] = true;
        mouseClickX = e.getX();
        mouseClickY = e.getY();
        bullet.currentX[0] = heroX + 100 ;
        bullet.currentY[0] = heroY  + 100;
        bullet.bulletX[0] = (mouseClickX - bullet.currentX[0])/3;
        bullet.bulletY[0] = (mouseClickY - bullet.currentY[0])/3;
      }
      
      else if(bullet.appear[1] == false)
      {
       // System.out.println("inHere");
        bullet.appear[1] = true;
        mouseClickX = e.getX();
        mouseClickY = e.getY();
        bullet.currentX[1] = heroX + 100 ;
        bullet.currentY[1] = heroY  + 100;
        bullet.bulletX[1] = (mouseClickX - bullet.currentX[1])/3;
        bullet.bulletY[1] = (mouseClickY - bullet.currentY[1])/3;
      }
      
      else if(bullet.appear[2] == false)
      {
       // System.out.println("inHere");
        bullet.appear[2] = true;
        mouseClickX = e.getX();
        mouseClickY = e.getY();
        bullet.currentX[2] = heroX + 100 ;
        bullet.currentY[2] = heroY  + 100;
        bullet.bulletX[2] = (mouseClickX - bullet.currentX[2])/3;
        bullet.bulletY[2] = (mouseClickY - bullet.currentY[2])/3;
      }
    
  
  
  }
  public  void mouseReleased(MouseEvent e){} // this is a listener method
    
  }
  
  
  
}