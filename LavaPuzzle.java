/*
 * Jainam Doshi and Ritvik Dutta
 * LavaPuzzle.java
 */

import java.awt.*; import java.awt.event.*; import javax.swing.*; import javax.imageio.ImageIO; import java.io.File;

public class LavaPuzzle extends JFrame
 {
   
  public LavaPuzzle()
  {
    super("Duneon Runner");
    setSize(1000,800);
    setLocation(0,0);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    Panel pan = new Panel();
    setContentPane(pan);
    setResizable(false);
    setVisible(true);
    
  }
   
  
  public static void main(String[] args)
  {
    LavaPuzzle puz = new LavaPuzzle();
    
    
  }
  
   class Panel extends JPanel
  {
    
   
    
    public void paintComponent(Graphics g)
    {
      super.paintComponent(g);
      g.setColor(Color.BLACK);
      
      for( int i = 0; i< 19 ; i++)
      {
        g.drawLine(i*77,0, i* 77, 800);
      }
      
      for( int i = 0 ; i < 13; i++)
        g.drawLine(0,i*62,1000, i*62);
    }
    
    
    
    
  }
 }


