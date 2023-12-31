import java.awt.*; import java.awt.event.*; import javax.swing.*; import javax.imageio.ImageIO; import java.io.File;
class Character extends JPanel // decides the sprite sheet of the character movements of the hero 
{
  
  int startingFrame;
  int endingFrame;
  int currentFrame = 1;
  boolean switchFrame = true;
  boolean keyPressed;
  
  Image[] hero = new Image[10];
  public Character(){
  detirmine();
  
  }
  
  public void detirmine()//  based of a number that the player will choose, a specific array of sprites for the hero willbe detirmined here
  {
    for(int i = 1; i<= hero.length; i++)
    {
      try{
        hero[i-1] = ImageIO.read( new File("wizard" + i + ".PNG"));
      }
      catch(Exception e)
      {
        System.out.println("image" + hero[i-1] + " not found.");
      }
      
      
    }
   
  }
  
  
  
  
  
}