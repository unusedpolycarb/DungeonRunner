 import java.awt.*; import java.awt.event.*; import javax.swing.*; import javax.imageio.ImageIO; import java.io.File; import java.util.TimerTask; //import java.util.Timer;

public class Mob // this class will render the mobs
{
  //Y,
  Image[] mobs = new Image[3];
  int[] mobX =new int[3];
  int[] mobY =new int[3];
  static int[] slimeSlopeX = {0,0,0};  
  static int[] slimeSlopeY = {0,0,0};   
  int[] type = {0,0,0};
  int allX = 0;
  int allY = 0;
  Timer slime ;
  int rand = (int)(Math.random()*10 + 1);
  
  public Mob()// this is the constructor for the mod class
  {
    for(int i = 0 ; i< mobs.length ; i++)
    {
      
      if(rand >= 1 && rand <= 5)
      {
       try{
         mobs[i] = ImageIO.read(new File("snake1.png"));
                            
         }
      catch(Exception e)
      {
        System.out.println("snake1 not found");
      } 
      type[i] = 2;
      }
      
      if(rand >= 6 && rand <= 10)
      {
        try{
         mobs[i] = ImageIO.read(new File("slime1.png"));
                            
         }
      catch(Exception e)
      {
        System.out.println("slime1 not found");
      } 
      type[i] = 1;
      
      }
    }
    
   
  }
  
  public void Spawn() // this is to acquire the start position
  {
   // public void actionPerformed(ActionEvent e)
   // {
    for(int i = 0 ; i< mobs.length ; i++)
    {
      if(mobX[i] !=-200 && mobY[i] !=-200  )
        System.out.println("slime1 not found");
        else{
      mobX[i] = ((int)(Math.random()*(5201)) - 200);
      System.out.println("slime1 : " + mobX[0]);
      System.out.println("slime2 : " + mobX[1]);
      mobY[i] = ((int)(Math.random()*(5201)) - 200);
      }
    }
    //}
  }
  
  public void Slime(int shiftX ,int shiftY, int slippedX, int slippedY, int face)// this method detirmines the movement of the Slime
  {
    for(int i = 0 ; i< mobs.length ; i++)
    {
      if(mobs[i].equals("slime1.png"));
         {
           if(i == face)
           {
      slimeSlopeX[i] = (220 - (mobX[i] + shiftX + slippedX))/8;
      slimeSlopeY[i] = (220 - (mobY[i] + shiftY + slippedY))/8;
           }
         }
      }
    
    
  }
  
  /*
  public void Snake(int shiftX, int shiftY, int snakedX , int snakedY)
  {
    
    
  }
  */
  
  
  
  
}


