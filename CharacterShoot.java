import java.awt.*; import java.awt.event.*; import javax.swing.*; import javax.imageio.ImageIO; import java.io.File;

class CharacterShoot// helps to manage the bullets that will be regulated in this class
{
  // hava an add method which adds missles to the array
  // one method whicich tracks the movement and position of each missile
  //method that checks if there is space for another projectile object
  //method that removes the object when needed
  //variables: x and y for the startin point of the object and x and y for the mouse click, make an array of positions for the x and y components of each missile object
  //have a method which calucaltes the position of each object, have an array o images and then reset the coordinates each time a bullet is used up. this will allow for the player to keep reseting them
  //MainPanel pan2 = new MainPanel(), why would this be the problem
  Image[] attack = new Image[5];
  int[] ProjX = new int[5];
  int[] ProjY = new int[5];
  boolean isSpace;
  
 public CharacterShoot(){// will initialize variables related to the shoot function of the class
  
    for( int i = 0 ; i < attack.length ; i++)
    {
      try
      {
        
        attack[i] = ImageIO.read(new File("fireball.png"));
        
      }
      catch(Exception e)
      {
        System.out.println(" The pic fireball.png is not found");
        
      }
      
    }
    
    
  }
 
 
 public void check(){}// to check if there is space available
 
 public void add(){}// to add each image to the attack array
 
 public void remove(){}// based on conditions to add to each of the attack arrays
 
 public void draw(Graphics g){} //to draw the projectiles whenever it was nessecary
 
 
 
  
  
}