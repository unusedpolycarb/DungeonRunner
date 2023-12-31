
import java.awt.*; import java.awt.event.*; import javax.swing.*; import javax.imageio.ImageIO; import java.io.File;
public class BossMobs// initalizes methods used for mob movement
{
  Image[] demon = new Image[2];
  int[] demonX = new int[2];
  int[] demonY = new int[2];
  int[] currentX = new int[2];
  int[] currentY = new int[2];
  boolean[] spawn = {true,true};
  //this is for the demon projectiles
  Image[] demonProj = new Image[2];
  int[] demonProjX = new int[2];
  int[] demonProjY = new int[2];
  int[] currentProjX = new int[2];
  int[] currentProjY = new int[2];
  boolean[] appear = {false, false};
  // this is for the water spirit warlock
  Image[] water = new Image[1];
  int[] waterX = new int[1];
  int[] waterY = new int[1];
  boolean left = false;
  Image[] waterProj1 = new Image[2];
  int[] waterProj1X = new int[2];
  int[] waterProj1Y = new int[2];
  int[] currentProj1X = new int[2];
  int[] currentProj1Y = new int[2];
  boolean[] waterAppear = {false, false};
  //these next set of variables are going to be for the last suriken of the water spirit
  Image lastProj;
  int lastProjX;
  int lastProjY;
  int currentLastX;
  int currentLastY;
  boolean lastAppear = false;
  // these will be all the variables for joeboticus
  Image[] joeBot = new Image[4];
  int currentImage = 0;
  int finalX = 430;
  int finalY = 300;
  Image[] bossProj = new Image[8];
  int[] bossProjX = new int[8];
  int[] bossProjY = new int[8];
  int[] currentBossX = new int[8];
  int[] currentBossY = new int[8];
  boolean[] bossAppear = {true, true};
 
  
 public BossMobs()// collets the png files for all the mob sprites
 {
   for(int i = 0 ; i< demon.length ; i++)
   {
   try{
         demon[i] = ImageIO.read(new File("demonWizard.png"));
                            
         }
      catch(Exception e)
      {
        System.out.println("demonWizard not found");
      } 
   }
   
   for(int i = 0 ; i< demonProj.length ; i++)
   {
   try{
         demonProj[i] = ImageIO.read(new File("demonProj.png"));
                            
         }
      catch(Exception e)
      {
        System.out.println("demonProj not found");
      } 
   }
   
   for(int i = 0 ; i< demon.length ; i++)
   {
     currentX[i]  = 250 + 120*i;
     currentY[i]  = 200 ;
   }
   
   // this will set the pic for the water spirit mob
   try{
         water[0] = ImageIO.read(new File("divya1.png"));
                            
         }
      catch(Exception e)
      {
        System.out.println("divya1 not found");
      } 
      // this sets the pic for the divya static proj
      try{
         waterProj1[0] = ImageIO.read(new File("divyaProj.png"));
                            
         }
      catch(Exception e)
      {
        System.out.println("divya1 not found");
      } 
      
      
       try{
         waterProj1[1] = ImageIO.read(new File("divyaProj.png"));
                            
         }
      catch(Exception e)
      {
        System.out.println("divya1 not found");
      } 
      
      try{
         lastProj = ImageIO.read(new File("divyaProj.png"));
                            
         }
      catch(Exception e)
      {
        System.out.println("divya1/lastProj not found");
      } 
      
      for(int i = 0; i < joeBot.length; i++)
      {
        try{
         joeBot[i] = ImageIO.read(new File("joe" + i + ".png"));
                            
         }
      catch(Exception e)
      {
        System.out.println("joe not found");
      }
      // this will set the images of the numebr of projectiles I have for the boss
      for(int l = 0; l< bossProj.length; l++)
      {
        try{
         bossProj[l] = ImageIO.read(new File("bossOrb.png"));
                            
         }
      catch(Exception e)
      {
        System.out.println("bossOrb not found");
      } 
      }
        
      }
      waterX[0] = 450;
      waterY[0] = 80;
      // this sets the slopes of the normal boss bullets
      waterProj1X[0] = -77;
      waterProj1Y[0] = -62;
      waterProj1X[1] = 77;
      waterProj1Y[1] = 62;
      currentProj1X[0] = waterX[0];
      currentProj1Y[0] = waterY[0];
      currentProj1X[1] = waterX[0];
      currentProj1Y[1] = waterY[0];
      // these variables are going to be for the last proj
      currentLastX = waterX[0];
      currentLastY = waterY[0];
      // these are the variables for the boss projetiles
      currentBossX[0] = finalX + 20;
      currentBossY[0] = finalY + 20;
      bossProjX[0] = 0;
      bossProjY[0] = 35;
      currentBossX[1] = finalX + 20;
      currentBossY[1] = finalY + 20;
      bossProjX[1] = 35;
      bossProjY[1] = 0;
      currentBossX[2] = finalX + 20;
      currentBossY[2] = finalY + 20;
      bossProjX[2] = 0;
      bossProjY[2] = -35;
      currentBossX[3] = finalX + 20;
      currentBossY[3] = finalY + 20;
      bossProjX[3] = -35;
      bossProjY[3] = 0;
      currentBossX[4] = finalX + 20;
      currentBossY[4] = finalY + 20;
      bossProjX[4] = 45/2;
      bossProjY[4] = -45/2;
      currentBossX[5] = finalX + 20;
      currentBossY[5] = finalY + 20;
      bossProjX[5] = -45/2;
      bossProjY[5] = -45/2;
      currentBossX[6] = finalX + 20;
      currentBossY[6] = finalY + 20;
      bossProjX[6] = -45/2;
      bossProjY[6] = 45/2;
      currentBossX[7] = finalX + 20;
      currentBossY[7] = finalY + 20;
      bossProjX[7] = 45/2;
      bossProjY[7] = 45/2;
   
 }
  
  
  
}