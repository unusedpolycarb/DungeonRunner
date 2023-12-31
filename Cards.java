/*
Jainam Doshi & Ritvik Dutta
5/1/19
Cards.java
Period 7 
*/
import java.awt.*;  //all imports below are important 
import java.awt.event.*;
import javax.swing.*; 
import javax.imageio.ImageIO; 
import java.io.File; 
import javax.swing.event.*; 
public class Cards extends JFrame //JFrame class to "hold" all other GUI
{
 JPanel menu;
 JPanel cardholder;
 CardLayout cl;
 Timer timer;
 JButton credits, playGame, background, controls, quit;
 //GamePanel gp; commented out for experimental purposes
 public Cards() //frame constructor
 {
  JFrame frame = new JFrame("DungeonRunner.java"); //setting specs of the frame in this contructor
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.getContentPane().setBackground(Color.BLACK);   
  frame.setSize(1000, 800);
  frame.setResizable(false);
  MenuPanel menu = new MenuPanel();
  CreditsPanel credits = new CreditsPanel();
  ControlPanel controls = new ControlPanel();
  //GamePanel mp = new GamePanel(); commented out for experimental purposes
  cardholder = new JPanel();
  cl = new CardLayout();
  cardholder.setLayout(cl);
  cardholder.add(menu, "Menu Panel");
  cardholder.add(credits, "Credits Panel");
  cardholder.add(controls, "Controls Panel");
  //cardholder.add(mp, "Game Start"); commented out for experimental purposes
  cardholder.setVisible(true);
  frame.setVisible(true);
  frame.add(cardholder);
 }
 public static void main(String[] args) 
 {
  Cards card = new Cards(); //main created Frame object 
 }

 class MenuPanel extends JPanel implements MouseListener, MouseMotionListener, ActionListener //this is the main menu panel class
 {
  Font fontBig = new Font("arial", Font.BOLD, 32);
  Font fontMedium = new Font("arial", Font.BOLD, 28);
  Font font = new Font("arial", Font.BOLD, 24);
  JLabel spacer,spacer2;
  JPanel buttons;
  Image titleImage = null; //images to create game title anc menu panel background below 
  Image backgroundImage = null;
  JPanel cards;
  int mouseX;
  int mouseY;
  Color customColor = new Color(230, 10, 28);
  public MenuPanel() //main menu constructor
  {
   setBackground(Color.BLACK);
   spacer = new JLabel("   "); 
   spacer2 = new JLabel("   "); 
   credits = new JButton("Credits");
   credits.setForeground(Color.WHITE);
   credits.setOpaque(false);
   credits.setContentAreaFilled(false);
   credits.setBorderPainted(false);
   credits.setFocusPainted(false);
   credits.setFont(fontMedium);
   credits.addActionListener(this);//actionlistener added in order for cardlayout to function
   credits.setActionCommand("Credits Panel");
   credits.addMouseMotionListener(new MouseMotionListener() { //added this listener to all buttons to change to red font color when hovered over
       public void mouseDragged(MouseEvent e) {} //unused
      public void mouseMoved(MouseEvent e) //checks for entering into button to change color to red
      {
           mouseX = e.getX();
    mouseY = e.getY();
    if(credits.contains(mouseX, mouseY)) 
    credits.setForeground(customColor);
   }});
   credits.addMouseListener(new MouseListener() //added this listener to all buttons in this panel to change back to white font color when not hovered over
   {
    public void mouseExited(MouseEvent e) //makes the button change back to white when exited
    {
     mouseX = e.getX();
     mouseY = e.getY();
     if(!(credits.contains(mouseX, mouseY))) 
      credits.setForeground(Color.WHITE);
    }
    public void mouseEntered(MouseEvent e){} //this method has no function
    public void mousePressed(MouseEvent e){} //this method has no function 
    public void mouseClicked(MouseEvent e){} //this method has no function
    public void mouseReleased(MouseEvent e){}//this method has no function
   });
   playGame = new JButton("Play");
   playGame.setOpaque(false); //makes the Button transparent
   playGame.setContentAreaFilled(false);
   playGame.setBorderPainted(false);
   playGame.setFont(fontBig);
   playGame.setFocusPainted(false);
   playGame.addActionListener(this);//actionlistener added in order for cardlayout to function
   playGame.setActionCommand("Game Start");
   playGame.setForeground(Color.WHITE);
   playGame.addMouseMotionListener(new MouseMotionListener() { //added this listener to all buttons to change to red font color when hovered over
       public void mouseDragged(MouseEvent e) {} //this method has no function
      public void mouseMoved(MouseEvent e)//checks for entering into button to change color to red
      {
           mouseX = e.getX();
    mouseY = e.getY();
    if(playGame.contains(mouseX, mouseY)) 
    playGame.setForeground(customColor);
   }});
   playGame.addMouseListener(new MouseListener() //added this listener to all buttons in this panel to change back to white font color when not hovered over
   {
    public void mouseEntered(MouseEvent e){}//this method has no function
    public void mouseExited(MouseEvent e)//makes the button change back to white when exited
    {
     mouseX = e.getX();
     mouseY = e.getY();
     if(!(playGame.contains(mouseX, mouseY))) 
      playGame.setForeground(Color.WHITE);
    }
    public void mousePressed(MouseEvent e){}//this method has no function
    public void mouseClicked(MouseEvent e){}//this method has no function
    public void mouseReleased(MouseEvent e){}//this method has no function
   });
   background = new JButton("Background");
   background.setOpaque(false);//makes the Button transparent
   background.setContentAreaFilled(false);
   background.setBorderPainted(false);
   background.setFont(fontMedium);
   background.setFocusPainted(false);
   background.setForeground(Color.WHITE);
   background.addMouseMotionListener(new MouseMotionListener() { //added this listener to all buttons to change to red font color when hovered over
       public void mouseDragged(MouseEvent e) {}//this method has no function
      public void mouseMoved(MouseEvent e)//checks for entering into button to change color to red
      {
           mouseX = e.getX();
    mouseY = e.getY();
    if(background.contains(mouseX, mouseY)) 
    background.setForeground(customColor);
    else background.setForeground(Color.WHITE);
   }});
   background.addMouseListener(new MouseListener() //added this listener to all buttons in this panel to change back to white font color when not hovered over
   {
    public void mouseEntered(MouseEvent e){}//this method has no function
    public void mouseExited(MouseEvent e)//makes the button change back to white when exited
    {
     mouseX = e.getX();
     mouseY = e.getY();
     if(!(background.contains(mouseX, mouseY))) 
      background.setForeground(Color.WHITE);
    }
    public void mousePressed(MouseEvent e){}//this method has no function
    public void mouseClicked(MouseEvent e){}//this method has no function
    public void mouseReleased(MouseEvent e){}//this method has no function
   });
   controls = new JButton("Controls");
   controls.addActionListener(this);//actionlistener added in order for cardlayout to function
   controls.setActionCommand("Controls Panel");
   controls.setOpaque(false);//makes the Button transparent
   controls.setContentAreaFilled(false);
   controls.setBorderPainted(false);
   controls.setFont(font);
   controls.setFocusPainted(false);
   controls.setForeground(Color.WHITE);
   controls.addMouseMotionListener(new MouseMotionListener() { //added this listener to all buttons to change to red font color when hovered over
       public void mouseDragged(MouseEvent e) {}//this method has no function
      public void mouseMoved(MouseEvent e)//checks for entering into button to change color to red
      {
           mouseX = e.getX();
    mouseY = e.getY();
    if(controls.contains(mouseX, mouseY)) 
    controls.setForeground(customColor);
   }});
   controls.addMouseListener(new MouseListener() //added this listener to all buttons in this panel to change back to white font color when not hovered over
   {
    public void mouseEntered(MouseEvent e){}//this method has no function
    public void mouseExited(MouseEvent e)//makes the button change back to white when exited//makes the button change back to white when exited
    {
     mouseX = e.getX();
     mouseY = e.getY();
     if(!(controls.contains(mouseX, mouseY))) 
      controls.setForeground(Color.WHITE);
    }
    public void mousePressed(MouseEvent e){}//this method has no function
    public void mouseClicked(MouseEvent e){}//this method has no function
    public void mouseReleased(MouseEvent e){}//this method has no function
   });
   quit = new JButton("Quit");
   quit.setOpaque(false);//makes the Button transparent
   quit.setContentAreaFilled(false);
   quit.setBorderPainted(false);
   quit.setFont(font);
   quit.setFocusPainted(false);
   quit.setForeground(Color.WHITE);
   quit.setActionCommand("Quit");
   quit.addActionListener(this);//actionlistener added in order for cardlayout to function
   quit.addMouseMotionListener(new MouseMotionListener() { //added this listener to all buttons to change to red font color when hovered over
       public void mouseDragged(MouseEvent e) {}//this method has no function
      public void mouseMoved(MouseEvent e)//checks for entering into button to change color to red
      {
           mouseX = e.getX();
    mouseY = e.getY();
    if(quit.contains(mouseX, mouseY)) 
    quit.setForeground(customColor);
    else quit.setForeground(Color.WHITE);
   }});
   quit.addMouseListener(new MouseListener() //added this listener to all buttons in this panel to change back to white font color when not hovered over
   {
    public void mouseEntered(MouseEvent e){}//this method has no function
    public void mouseExited(MouseEvent e)//makes the button change back to white when exited
    {
     mouseX = e.getX();
     mouseY = e.getY();
     if(!(quit.contains(mouseX, mouseY))) 
      quit.setForeground(Color.WHITE);
    }
    public void mousePressed(MouseEvent e){}//this method has no function
    public void mouseClicked(MouseEvent e){}//this method has no function
    public void mouseReleased(MouseEvent e){}//this method has no function
   });
   setLayout(new GridLayout(5,1));
   buttons = new JPanel(); //holds all the buttons in specific layout
   buttons.setPreferredSize(new Dimension(500, 500));
   buttons.setLayout(new GridLayout(1,5));
   buttons.add(controls);
   buttons.add(background);
   buttons.add(playGame);
   buttons.add(credits);
   buttons.add(quit);
   buttons.setOpaque(false);
   buttons.setVisible(true); //setting button layout visible 
   add(spacer);
   add(spacer2);
   add(buttons);
  }
  public void paintComponent(Graphics g) //draws Images on the main menu panel-decoration
  {
   g.setColor(Color.BLACK);
   backgroundImage = new ImageIcon("backgroundtitle.jpg").getImage();
   g.drawImage(backgroundImage, 0, 0, 1000, 1000, null);
   titleImage = new ImageIcon("title.png").getImage();
   g.drawImage(titleImage, 100, 100, 800, 200, null);
   titleImage = new ImageIcon("strikeninja.png").getImage();
   g.drawImage(titleImage, 600, 410, 400, 400, null);
   titleImage = new ImageIcon("Dark_magician.png").getImage();
   g.drawImage(titleImage, 0, 400, 400, 400, null);
   titleImage = new ImageIcon("knighttitle.png").getImage();
   g.drawImage(titleImage, 300, 400, 400, 400, null);
  }
  public void mouseMoved(MouseEvent e){}//this method has no function
  public void mouseDragged(MouseEvent e) {}//this method has no function
  public void mouseEntered(MouseEvent e){}//this method has no function
  public void mouseExited(MouseEvent e){}//this method has no function
  public void mousePressed(MouseEvent e){}//this method has no function
  public void mouseClicked(MouseEvent e){}//this method has no function
  public void mouseReleased(MouseEvent e){}//this method has no function
  public void actionPerformed(ActionEvent evt) //Allows CardLayout to function through button command
    {
      if (evt.getActionCommand().equals("Credits Panel")) 
      {
       cl.show(cardholder, evt.getActionCommand()); //switches to credits panel
       timer.start();
      }
      if (evt.getActionCommand().equals("Quit"))
      {
       System.exit(1); //quits game
      }
      if (evt.getActionCommand().equals("Controls Panel"))
      {
       cl.show(cardholder, evt.getActionCommand());//switches to controls panel
      }
      if (evt.getActionCommand().equals("Game Start"))
      {
       cl.show(cardholder, evt.getActionCommand());//switches to pre game-starting panel
      }
  }
 }
 class CreditsPanel extends JPanel //this is the credits panel
 {
  JButton returnButton;
  JPanel cp; 
  JTextArea title, credits1, credits2, credits3, credits4, credits5;
  int yCo = 200;
  int counter = 0;
  Font font = new Font("arial", Font.PLAIN, 28);
  public CreditsPanel() //credits panel constructor
  {
   setBackground(Color.BLACK);
   setLayout(new BorderLayout());
   //title = new JTextArea("        Credits");
   //title.setForeground(Color.WHITE);
   //title.setOpaque(false);
   //title.setFont(font);
   //title.setBorder(null);
   //title.setVisible(true);
   //title.setEditable(false);
   //setParameters(credits1);
   MoveCredits shifter = new MoveCredits(); 
   timer = new Timer(100, shifter); //timer allows the credits slide to "scroll" down automatically
   //add(title, BorderLayout.NORTH);
   //add(credits1);
   setVisible(true);
  }
  public void setParameters(JTextArea unusable)
  {
   //unusable = new JTextArea(getString());
   unusable.setForeground(Color.WHITE);
   unusable.setOpaque(false);
   unusable.setFont(font);
   unusable.setBorder(null);
   unusable.setVisible(true);
   unusable.setEditable(false);
   unusable.setLocation((int)(Math.random() * 400), yCo);
  }
  public String getString()
  {
   int chooser = (int)(Math.random() * 6);
   if(chooser == 0) return "Game Designers:\nJainam Doshi\nRitvik Dutta";
   else if(chooser == 1) return "Inspiration for Main Boss:\nJoe Bob Kim";
   else if(chooser == 2) return "Artists:\nRitvik Dutta";
   else if(chooser == 3) return "Game Writers:\nJainam Doshi\nRitvik Dutta";
   else if(chooser == 4) return "Lead Game Programmers:\nJainam Doshi";
   else if(chooser == 5) return "Other Lead Programmers:\nRitvik Dutta";
   return "";
  }
  class MoveCredits implements ActionListener //timer class allowing the JTextArea to move down the screen
  {
   public void actionPerformed(ActionEvent e) //shifts credits down
   {
    counter++;
    yCo+=5; //changes the y coordinate for the
    credits1.setLocation(0, yCo); //setting of location every time the timer hits 100 milliseconds
    if(counter == 1)
    {
     setParameters(credits2);
     add(credits2);
    }
   }
  }
 }
 
 class ControlPanel extends JPanel implements ActionListener, KeyListener //this is the controls panel, explaining all the controls for the player
 {
  Image realBackOnButton;
  Image backOnButton;
  JButton back;
  int frameNumber1, frameNumber2;
  boolean switchChanger;
  JTextArea disclaimer;
  JButton blankKey1, blankKey2, wKey, aKey, sKey, dKey;
  JLabel mouseGif;
  JPanel keys, movementOne, movementTwo, movementThree;
  Timer switchP;
  Font buttonFont = new Font("arial", Font.BOLD, 48);
  Font grabFont = new Font("arial", Font.BOLD, 14);
  public ControlPanel() //controls panel constructor
  {
   setBackground(Color.BLACK);
   setLayout(new GridLayout(2,3,10,10));
   backOnButton = new ImageIcon("backbutton.png").getImage();
   Image realBackOnButton = backOnButton.getScaledInstance(60, 60, Image.SCALE_DEFAULT); //rescaling image using this method 
   back = new JButton();
   try //checks for image to put into back button
   {
    back.setIcon(new ImageIcon(realBackOnButton));
   }
   catch(Exception e)
   {
    System.err.println("Image not found.");
   }
   back.addActionListener(this);
   back.setActionCommand("Menu Panel");
   back.setFocusPainted(false);
   addKeyListener(this); //allows Key input to function
   back.setOpaque(false);
   back.setContentAreaFilled(false);
   back.setBorderPainted(false);
   back.setBackground(Color.BLACK);
   blankKey1 = new JButton();
   blankKey1.setOpaque(true); //makes the Button transparent
   blankKey1.setContentAreaFilled(false);
   blankKey1.setBorderPainted(false);
   blankKey1.setFocusPainted(false);
   blankKey1.setVisible(true);
   blankKey2 = new JButton();
   blankKey2.setOpaque(true); //makes the Button transparent
   blankKey2.setContentAreaFilled(false);
   blankKey2.setBorderPainted(false);
   blankKey2.setFocusPainted(false);
   blankKey2.setVisible(true);
   wKey = new JButton("W"); //looks like keyboard keys
   wKey.setFont(buttonFont);
   aKey = new JButton("A");//looks like keyboard keys
   aKey.setFont(buttonFont);
   sKey = new JButton("S");//looks like keyboard keys
   sKey.setFont(buttonFont);
   dKey = new JButton("D");//looks like keyboard keys
   dKey.setFont(buttonFont);
   wKey.addActionListener(this);//allows user button input to check movement controls
   aKey.addActionListener(this);//allows user button input to check movement controls
   sKey.addActionListener(this);//allows user button input to check movement controls
   dKey.addActionListener(this);//allows user button input to check movement controls
   wKey.setFocusPainted(false);
   aKey.setFocusPainted(false);
   sKey.setFocusPainted(false);
   dKey.setFocusPainted(false);
   wKey.setOpaque(false);
   aKey.setOpaque(false);
   sKey.setOpaque(false);
   dKey.setOpaque(false);
   wKey.setVisible(true);
   aKey.setVisible(true);
   sKey.setVisible(true);
   dKey.setVisible(true);
   keys = new JPanel();
   keys.setLayout(new GridLayout(2,3, 30, 30));
   keys.add(blankKey1);
   keys.add(wKey);
   keys.add(blankKey2);
   keys.add(aKey);
   keys.add(sKey);
   keys.add(dKey);
   Anima animate = new Anima();
   switchP = new Timer(140, animate); //timer switches between the two images per animation frame
   movementOne = new JPanel();
   movementOne.setOpaque(false);
   movementTwo = new JPanel();
   ImageIcon gif = new ImageIcon("mouseclicking.gif"); 
   mouseGif = new JLabel(gif); //possible for gifs to work if used in JLabel
   add(keys);
   disclaimer = new JTextArea("Please click one of the keyboard outputs to learn its function\nor type it on your keyboard.");
   disclaimer.setForeground(Color.WHITE);
   disclaimer.setOpaque(false);
   disclaimer.setEditable(false);
   disclaimer.setFont(grabFont);
   disclaimer.addKeyListener(this);
   disclaimer.setFocusable(true); 
   disclaimer.requestFocus();  
   movementOne.add(disclaimer);
   movementTwo.setLayout(new FlowLayout(FlowLayout.RIGHT));
   movementTwo.add(back);
   add(movementOne);
   add(mouseGif);
   add(movementTwo);
   setVisible(true);
  }
  public void paintComponent(Graphics g) //paintcomponent to draw frames of animation
  {
   super.paintComponent(g);
   if(frameNumber1 == 3) //switches between the 2 images we use to get the up movement
   {
    Image yeet3 = new ImageIcon("wizard3.png").getImage();
    Image yeet4 = new ImageIcon("wizard4.png").getImage();
    if(switchChanger == false) g.drawImage(yeet3, 590, 90, 270, 270, null);
    else g.drawImage(yeet4, 585, 90, 270, 270, null);
    repaint();
   }
   if(frameNumber1 == 7) //switches between the 2 images we use to get the left movement
   {
    Image yeet7 = new ImageIcon("wizard7.png").getImage();
    Image yeet8 = new ImageIcon("wizard8.png").getImage();
    if(switchChanger == false) g.drawImage(yeet7, 590, 90, 270, 270, null);
    else g.drawImage(yeet8, 585, 90, 270, 270, null);
    repaint();
   }
   if(frameNumber1 == 5) //switches between the 2 images we use to get the down movement
   {
    Image yeet5 = new ImageIcon("wizard5.png").getImage();
    Image yeet6 = new ImageIcon("wizard6.png").getImage();
    if(switchChanger == false) g.drawImage(yeet5, 590, 90, 270, 270, null);
    else g.drawImage(yeet6, 585, 90, 270, 270, null);
    repaint();
   }
   if(frameNumber1 == 1) //switches between the 2 images we use to get the right movement
   {
    Image yeet1 = new ImageIcon("wizard1.png").getImage();
    Image yeet2 = new ImageIcon("wizard2.png").getImage();
    if(switchChanger == false) g.drawImage(yeet1, 590, 90, 270, 270, null);
    else g.drawImage(yeet2, 585, 90, 270, 270, null);
    repaint();
   }
  }
  public void keyTyped(KeyEvent e) //this method allows user to type wasd to check controls as well
  {
   grabFocus();
   disclaimer.setText(""); 
   char ease = e.getKeyChar();
   if((ease == 'w') || (ease == 'W'))
   {
    frameNumber1 = 3;
    switchP.start(); //starts timer
   }
   else if(ease == 'a' || ease == 'A')
   {
    frameNumber1 = 7;
    switchP.start(); //starts timer
   }  
   else if(ease == 's' || ease == 'S')
   {
    frameNumber1 = 5;
    switchP.start(); //starts timer
   } 
   else if(ease == 'd' || ease == 'D')
   {
    frameNumber1 = 1;
    switchP.start(); //starts timer
   } 
  }
  public void keyReleased(KeyEvent e){} //this method is unused
  public void keyPressed(KeyEvent e){} //this method is unused
  public void actionPerformed(ActionEvent e) //recieves button command and then sends information 
  {             //to the paintcomponent class to allow animation 
   disclaimer.setText("");       //of frames
   String command = e.getActionCommand();
   if(command.equals("W")) 
   {
    frameNumber1 = 3;
    switchP.start(); //starts timer
   }
   if(command.equals("A"))  
   {
    frameNumber1 = 7;
    switchP.start(); //starts timer
   }
   if(command.equals("S"))  
   {
    frameNumber1 = 5;
    switchP.start();//starts timer
   }
   if(command.equals("D"))
   {
    frameNumber1 = 1;
    switchP.start();//starts timer
   }
   if(command.equals("Menu Panel"))
      {
       controls.setForeground(Color.WHITE);
       cl.show(cardholder, command);//switches to menu panel
      }
  }
  class Anima implements ActionListener //timer class
  {
   public void actionPerformed(ActionEvent e) //every time the timer hits 140 milliseconds,
   {           // it turns the boolean to switch frame true and false
    switchChanger = !(switchChanger);  // whenever it is true, it draws one image, whenever its false
   }           // it draws the other image
  }
 }
}