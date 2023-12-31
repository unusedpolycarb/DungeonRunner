
import java.awt.*; import java.awt.event.*; import javax.swing.*; import javax.imageio.ImageIO; import java.io.File;
public class HeroBullet// regulates the shooting for the character
{
  Image[] wizardShot = new Image[3];
  boolean[] appear = {false,false,false}; 
  int[] currentX = {0,0,0};
  int[] currentY = {0,0,0};
  int[] bulletX = {0,0,0};
  int[] bulletY = {0,0,0};
  
  
 public HeroBullet()// assgins the .png files for the character bullets
 {
   for( int i = 0 ; i < wizardShot.length ; i++)
    {
      try
      {
        
        wizardShot[i] = ImageIO.read(new File("wizardProj.png"));
        
      }
      catch(Exception e)
      {
        System.out.println(" The pic boss wizard proj is not found");
        
      }
      
    }
 }
   
  
}