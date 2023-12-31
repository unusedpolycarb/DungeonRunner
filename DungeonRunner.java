/*
Jainam Doshi & Ritvik Dutta
5/10/19
DungeonRunner.java
Period 7 
*/  
import javax.swing.event.*; 
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;
import javax.imageio.ImageIO; 
import java.io.File;
import java.lang.InterruptedException; 
public class DungeonRunner extends JFrame //JFrame class to "hold" all other GUI
{
 TeleportPanel tp;
 JPanel menu;
 JPanel cardholder;
 CardLayout cl;
 Timer oneSec;
 Timer timer;
 Timer scroller;
 ControlPanel controlsP;
 CreditsPanel creditsP;
 PreGamePanel pgp;
 Panel test;
 JButton credits, playGame, background, controls, quit;
 int counterKill, health;
 boolean change, wizardChosen, knightChosen, ninjaChosen;
 public DungeonRunner() //frame constructor
 {
  JFrame frame = new JFrame("DungeonRunner.java"); //setting specs of the frame in this contructor
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.getContentPane().setBackground(Color.BLACK);   
  frame.setSize(1000, 900);
  frame.setResizable(false);
  MenuPanel menu = new MenuPanel();
  creditsP = new CreditsPanel();
  controlsP = new ControlPanel();
  pgp = new PreGamePanel();
  BackgroundPanel yp = new BackgroundPanel(); 
  cardholder = new JPanel();
  cl = new CardLayout();
  cardholder.setLayout(cl);
  cardholder.add(menu, "Menu Panel");
  cardholder.add(creditsP, "Credits Panel");
  cardholder.add(controlsP, "Controls Panel");
  cardholder.add(pgp, "Game Start");
  cardholder.add(yp, "Background Panel"); 
  cardholder.setVisible(true);
  frame.setVisible(true);
  frame.add(cardholder);
 }
 public static void main(String[] args) 
 {
  DungeonRunner dr = new DungeonRunner(); //main created Frame object 
 }

 class MenuPanel extends JPanel implements MouseListener, MouseMotionListener, ActionListener //this is the main menu panel class
 {
  Font fontBig = new Font("arial", Font.BOLD, 32);
  Font fontMedium = new Font("arial", Font.BOLD, 28);
  Font font = new Font("arial", Font.BOLD, 24);
  JLabel spacer,spacer2;
  JPanel buttons;
  Image titleImage = null; //images to create game title and menu panel background below 
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
   background.addActionListener(this);
   background.setActionCommand("Background Panel");
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
   g.drawImage(titleImage, 600, 510, 400, 400, null);
   titleImage = new ImageIcon("Dark_magician.png").getImage();
   g.drawImage(titleImage, 0, 500, 400, 400, null);
   titleImage = new ImageIcon("knighttitle.png").getImage();
   g.drawImage(titleImage, 300, 500, 400, 400, null);
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
      if (evt.getActionCommand().equals("Quit")) //quits the game
      {
       System.exit(1); //quits game
      }
      if (evt.getActionCommand().equals("Controls Panel"))
      {
       cl.show(cardholder, evt.getActionCommand());//switches to controls panel
       controlsP.requestFocus();
      }
      if (evt.getActionCommand().equals("Game Start"))
      {
       cl.show(cardholder, evt.getActionCommand());//switches to pre game-starting panel
      }
      if (evt.getActionCommand().equals("Background Panel"))
      {
       cl.show(cardholder, evt.getActionCommand());//switches to background panel
       scroller.start();
      }
  }
 }
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
     if(wizardChosen)
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
     if(knightChosen)
     {
       for(int i = 1; i<= hero.length; i++)
       {
         try{
           hero[i-1] = ImageIO.read( new File("knight" + i + ".PNG"));
         }
         catch(Exception e)
         {
           System.out.println("image" + hero[i-1] + " not found.");
         }
       }  
     }
     if(ninjaChosen)
     {
       for(int i = 1; i<= hero.length; i++)
       {
         try{
           hero[i-1] = ImageIO.read( new File("ninja" + i + ".PNG"));
         }
         catch(Exception e)
         {
           System.out.println("image" + hero[i-1] + " not found.");
         }
       }  
     }
   }
   
   
   
   
   
 }
 
 class TeleportPanel extends JPanel
 {
  Image teleport;
  int counter;
  Timer oneSec;
  public TeleportPanel()
  {
   TeleportingTimer tt = new TeleportingTimer();
   oneSec = new Timer(10, tt);
  }
  class TeleportingTimer implements ActionListener
  {
   public void actionPerformed(ActionEvent evt)
   {
    counter++;
    repaint();
    if(counter == 10)
    {
     oneSec.stop();
     cardholder.remove(tp);
    }
   }
  }
 }
 class CreditsPanel extends JPanel implements ActionListener//this is the credits panel
 {
  Image realBackOnButton;
  Image backOnButton;
  JButton backCredits;
  JButton returnButton;
  JPanel cp; 
  JTextArea title, credits1, credits2, credits3, credits4, credits5;
  int yCo1, yCo2, yCo3, yCo4, yCo5, xCo1, xCo2, xCo3, xCo4, xCo5 = 0; 
  int counter = 0;
  boolean stay; 
  Font font = new Font("arial", Font.PLAIN, 28);
  boolean addNew2, addNew3, addNew4, addNew5;
  public CreditsPanel() //credits panel constructor
  {
   backOnButton = new ImageIcon("backbutton.png").getImage();
   Image realBackOnButton = backOnButton.getScaledInstance(100, 100, Image.SCALE_DEFAULT); //rescaling image using this method 
   backCredits = new JButton();
   try //checks for image to put into back button
   {
    backCredits.setIcon(new ImageIcon(realBackOnButton));
   }
   catch(Exception e)
   {
    System.err.println("Image not found.");
   }
   backCredits.addActionListener(this);
   backCredits.setActionCommand("Menu Panel");
   backCredits.setFocusPainted(false);
   backCredits.setOpaque(false);
   backCredits.setContentAreaFilled(false);
   backCredits.setBorderPainted(false);
   credits1 = new JTextArea();
   credits2 = new JTextArea();
   credits3 = new JTextArea();
   credits4 = new JTextArea();
   credits5 = new JTextArea();
   setBackground(Color.BLACK);
   setParameters(credits1, 1);
   setParameters(credits2, 2);
   while(credits2.getText().equals(credits1.getText())) //used to make sure that no 2 JTextAreas on screen will be the same 
   {
    credits2.setText(getString());
   }
   setParameters(credits3, 3);
   while((credits3.getText().equals(credits2.getText())) || (credits3.getText().equals(credits1.getText()))) //used to make sure that no 2 JTextAreas on screen will be the same 
   {
    credits3.setText(getString());
   }
   setParameters(credits4, 4);
   while((credits4.getText().equals(credits3.getText())) || (credits4.getText().equals(credits2.getText())) || (credits4.getText().equals(credits1.getText()))) //used to make sure that no 2 JTextAreas on screen will be the same 
   {
    credits4.setText(getString());
   }
   setParameters(credits5, 5);
   while((credits5.getText().equals(credits4.getText())) || (credits5.getText().equals(credits3.getText())) || (credits5.getText().equals(credits2.getText())) || (credits5.getText().equals(credits1.getText()))) //used to make sure that no 2 JTextAreas on screen will be the same 
   {
    credits5.setText(getString());
   }
   MoveCredits shifter = new MoveCredits(); 
   timer = new Timer(150, shifter); //timer allows the credits slide to "scroll" down automatically
   add(credits1);
   add(credits2);
   add(credits3);
   add(credits4);
   add(credits5);
   add(backCredits);
   setVisible(true);
  }
  public void paintComponent(Graphics g) //background image
  {
   super.paintComponent(g);
   Image bc = new ImageIcon("creditsbackground.jpg").getImage();
   g.drawImage(bc, 0, 0, 1000, 900, null);
  }
  public void actionPerformed(ActionEvent e) //uses button input to reset everything on panel
  {
   if(e.getActionCommand().equals("Menu Panel"))
      {
       credits.setForeground(Color.WHITE);
       cl.show(cardholder, e.getActionCommand());//switches to menu panel
       timer.stop();
       yCo1 = 0; //reset coordinates
       yCo2 = -150;
       yCo3 = -300;
       yCo4 = -450;
       yCo5 = -600;
      }
  }
  public void setParameters(JTextArea unusable, int order) //sets JTextArea's parameters so I do not repeat same code multiple times
  {
   unusable.setText((getString()));
   unusable.setForeground(Color.WHITE);
   unusable.setOpaque(false);
   unusable.setFont(font);
   unusable.setBorder(null);
   unusable.setVisible(true);
   unusable.setEditable(false);
   if(order == 1) 
   {
    xCo1 = (int)(Math.random() * 495); //random x-coordinate
    credits1.setLocation(xCo1, yCo1);
   }
   if(order == 2)
   {
    xCo2 = (int)(Math.random() * 495); //random x-coordinate
    yCo2-=150;//offset so that JTextArea's do not interfere with one another
    credits2.setLocation(xCo2, yCo2);
   }
   if(order == 3)
   {
    xCo3 = (int)(Math.random() * 495); //random x-coordinate
    yCo3-=300;//offset so that JTextArea's do not interfere with one another
    credits3.setLocation(xCo3, yCo3);
   }
   if(order == 4)
   {
    xCo4 = (int)(Math.random() * 495); //random x-coordinate
    yCo4-=450;//offset so that JTextArea's do not interfere with one another
    credits4.setLocation(xCo4, yCo4);
   }
   if(order == 5)
   {
    xCo5 = (int)(Math.random() * 495); //random x-coordinate
    yCo5-=600;//offset so that JTextArea's do not interfere with one another
    credits5.setLocation(xCo5, yCo5);
   }
  }
  public String getString() //gets a random string for the credits 
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
    yCo1+=20;//changes the y coordinate for the
    yCo2+=20; 
    yCo3+=20;
    yCo4+=20;
    yCo5+=20;
    if(stay) backCredits.setLocation(800, 600);
    else backCredits.setLocation(800, yCo1);
    if(yCo1 == 600) stay = true;
    if(yCo1 == 800) 
    {
     yCo1 = 0;
     xCo1 = (int)(Math.random() * 495);
    }
    if(yCo2 == 800)
    {
     yCo2 = 0;
     xCo2 = (int)(Math.random() * 495);
    }
    if(yCo3 == 800) 
    {
     yCo3 = 0;
     xCo3 = (int)(Math.random() * 495);
    }
    if(yCo4 == 800)
    {
     yCo4 = 0;
     xCo4 = (int)(Math.random() * 495);
    }
    if(yCo5 == 800) 
    {
     yCo5 = 0;
     xCo5 = (int)(Math.random() * 495);
    }
    credits1.setLocation(xCo1, yCo1); //setting of location every time the timer hits 100 milliseconds
    credits2.setLocation(xCo2, yCo2); //setting of location every time the timer hits 100 milliseconds
    credits3.setLocation(xCo3, yCo3); //setting of location every time the timer hits 100 milliseconds
    credits4.setLocation(xCo4, yCo4); //setting of location every time the timer hits 100 milliseconds
    credits5.setLocation(xCo5, yCo5); //setting of location every time the timer hits 100 milliseconds
   }
  }
 }
 class PreGamePanel extends JPanel implements ActionListener //this is the panel that you get when you click the play button before 
 {
 JPanel jp;
 JLabel pgptitle;
 JButton chooseWizard, chooseKnight, chooseNinja, preGameBack, spacer;
 Image wizardchoice, knightchoice, ninjachoice;
 JTextArea jt1, jt2, jt3;
 Font chooseyourclass = new Font("arial", Font.BOLD, 40);
 Font forAreas = new Font("arial", Font.BOLD, 24);
  public PreGamePanel() //constructor for the Panel
  {
   Image pbb = new ImageIcon("backbutton.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);  
   preGameBack = new JButton();
   try //checks for image to put into back button
   {
    preGameBack.setIcon(new ImageIcon(pbb));
   }
   catch(Exception e)
   {
    System.err.println("Image not found.");
   }
   preGameBack.addActionListener(this);
   preGameBack.setActionCommand("Menu Panel");
   preGameBack.setFocusPainted(false);
   preGameBack.setOpaque(false);
   preGameBack.setContentAreaFilled(false);
   preGameBack.setBorderPainted(false);
   preGameBack.setLocation(600, 600);
   setBackground(Color.BLACK);
   pgptitle = new JLabel("Choose your class", JLabel.CENTER);
   pgptitle.setPreferredSize(new Dimension(1000, 100));
   pgptitle.setFont(chooseyourclass);
   pgptitle.setForeground(Color.WHITE);
   pgptitle.setOpaque(false);
   jp = new JPanel();
   jp.setOpaque(false);
   setLayout(new BorderLayout());
   jp.setLayout(new GridLayout(4,2));
   chooseWizard = new JButton();
   chooseWizard.addActionListener(this);
   chooseWizard.setActionCommand("Wizard Chosen");
   chooseWizard.setOpaque(false);//makes the Button transparent
   chooseWizard.setContentAreaFilled(false);
   chooseWizard.setBorderPainted(false);
   chooseWizard.setFocusPainted(false);
   chooseNinja = new JButton();
   chooseNinja.addActionListener(this);
   chooseNinja.setActionCommand("Ninja Chosen");
   chooseNinja.setOpaque(false);//makes the Button transparent
   chooseNinja.setContentAreaFilled(false);
   chooseNinja.setBorderPainted(false);
   chooseNinja.setFocusPainted(false);
   chooseKnight = new JButton();
   chooseKnight.addActionListener(this);
   chooseKnight.setActionCommand("Knight Chosen");
   chooseKnight.setOpaque(false);//makes the Button transparent
   chooseKnight.setContentAreaFilled(false);
   chooseKnight.setBorderPainted(false);
   chooseKnight.setFocusPainted(false);
   jt1 = new JTextArea("The wizard shoots his fireballs from afar,\nachieving an average prowess all over.\nLegend has it Harry Potter stood no chance against this mage and thereby\ngiving up magic as a whole.");
   jt1.setFont(forAreas);
   jt1.setForeground(Color.WHITE);
   jt1.setOpaque(false);
   jt2 = new JTextArea("The knight is known in the dungeon for\nbeing the strongest being. Armed with a\nheavy sword, shield, and strong armor,\nthis knight can tank projectiles very well\nbut is very slow to move.");
   jt2.setFont(forAreas);
   jt2.setForeground(Color.WHITE);
   jt2.setOpaque(false);
   jt3 = new JTextArea("The ninja is the master of stealth.\nKnown for its shurikens, speed,\nand high damage output. Unfortunately, it\nis also the weakest character defense-wise\nand can die very easily.");
   jt3.setFont(forAreas);
   jt3.setForeground(Color.WHITE);
   jt3.setOpaque(false);
   jt1.setEditable(false);
   jt2.setEditable(false);
   jt3.setEditable(false); 
   spacer = new JButton();
   spacer.setVisible(false);
   wizardchoice = new ImageIcon("wizard1.png").getImage();
   knightchoice = new ImageIcon("knight.png").getImage();
   ninjachoice = new ImageIcon("ninja.png").getImage();
   jp.add(chooseWizard);
   jp.add(jt1);
   jp.add(chooseKnight);
   jp.add(jt2);
   jp.add(chooseNinja);
   jp.add(jt3);
   jp.add(spacer);
   jp.add(preGameBack);
   add(pgptitle, BorderLayout.NORTH);
   add(jp, BorderLayout.CENTER);
  }
  public void actionPerformed(ActionEvent evt)
  {
   String command = evt.getActionCommand();
   if(command == "Wizard Chosen")
   {
     wizardChosen = true;
     test = new Panel();
     cardholder.add(test, "Game");
     cl.show(cardholder, "Game");
     test.requestFocus();
   }
   else if(command == "Knight Chosen") 
   {
     knightChosen = true;
     test = new Panel();
     cardholder.add(test, "Game");
     cl.show(cardholder, "Game");
     test.requestFocus();
   }
   else if(command == "Ninja Chosen")
   {
     System.out.println("yeeh");
     ninjaChosen = true;
     test = new Panel();
     cardholder.add(test, "Game");
     cl.show(cardholder, "Game");
     test.requestFocus();
   }
   if(command == "Menu Panel") 
   {
    cl.show(cardholder, evt.getActionCommand());
    playGame.setForeground(Color.WHITE);
   }
  }
  public void paintComponent(Graphics g)
  {
   super.paintComponent(g);
   Image pgpBackground = new ImageIcon("pgp.jpg").getImage();
   g.drawImage(pgpBackground, 0, 0, 1000, 900, null);
   g.drawImage(wizardchoice, 30, -60, 450, 450, null);
   g.drawImage(knightchoice, 125, 200, 450, 450, null);
   g.drawImage(ninjachoice, 70, 345, 450, 450, null);
  }
 }
 class BackgroundPanel extends JPanel implements ActionListener //this is the background panel, explaining the lore of the game
 {
  JPanel mp;
  int slideVal, yint;
  Image backgroundback;
  JButton backgroundPanelBack;
  JTextArea story;
  JSlider speedController;
  Font font = new Font("arial", Font.PLAIN, 32);
  public BackgroundPanel()//background panel constructor
  {
   mp = new JPanel();
   mp.setOpaque(false);
   Image bbb = new ImageIcon("backbutton.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);  
   backgroundPanelBack = new JButton();
   try //checks for image to put into back button
   {
    backgroundPanelBack.setIcon(new ImageIcon(bbb));
   }
   catch(Exception e)
   {
    System.err.println("Image not found.");
   }
   backgroundPanelBack.addActionListener(this);
   backgroundPanelBack.setActionCommand("Menu Panel");
   backgroundPanelBack.setFocusPainted(false);
   backgroundPanelBack.setOpaque(false);
   backgroundPanelBack.setContentAreaFilled(false);
   backgroundPanelBack.setBorderPainted(false);
   backgroundPanelBack.setLocation(0, 0);
   mp.setLayout(new BorderLayout());
   story = new JTextArea("\tLord Joebotticus, a well-known\n\tdungeon keeper is known for his\n\tcruel and sadistic ways. Recently\n\thaving captured un unnamed human,\n\tLord Joebotticus sent them to his\n\tfavorite and most coveted dungeon-\n\tEichenvald Bunker 203. Before\n\tsending them there, Lord Joebotticus\n\tpromised the human freedom\n\tif they were able to collect the 3\n\tkeys needed to open the dungeon door\n\tleading to the exit. Unknowing to\n\tLord Joebotticus, the human was\n\tactually a very skilled fighter and\n\thad special skills. In order to obtain\n\ta key, the human must kill 30\n\tmonsters in the dungeon. This also\n\topens up the door to another room\n\tin the dungeon where more keys\n\tmay be waiting. Monsters vary in\n\tdifficulty to kill and have different\n\tattack patterns, so watch out.");
   story.setOpaque(false);
   story.setEditable(false);
   story.setFont(font);
   story.setForeground(Color.WHITE);
   mp.add(story, BorderLayout.CENTER);
   speedController = new JSlider(JSlider.VERTICAL, 0, 100, 10);
   speedController.setMajorTickSpacing(10);
   speedController.setLabelTable(speedController.createStandardLabels(10)); // create labels on tick marks
   speedController.setForeground(Color.WHITE);
   speedController.setPaintLabels(true);
   speedController.setPaintTicks(true);
   SliderListener sl = new SliderListener();
   speedController.addChangeListener(sl);
   mp.add(speedController, BorderLayout.EAST);
   PanelScroller scroll = new PanelScroller();
   slideVal = 10;
   speedController.setOpaque(false);
   scroller = new Timer(500, scroll);
   add(mp);
   add(backgroundPanelBack);
  }
  public void paintComponent(Graphics g)
  {
   super.paintComponent(g);
   Image bb = new ImageIcon("backgroundbackground.jpg").getImage();
   g.drawImage(bb, 0, 0, 1000, 900, null);
  }
  public void actionPerformed(ActionEvent e)
  {
   String command = e.getActionCommand();
   if(command == "Menu Panel") 
   {
    cl.show(cardholder, e.getActionCommand());
    background.setForeground(Color.WHITE);
   }
  }
  class SliderListener implements ChangeListener //Listens to the JSlider command 
  {
   public void stateChanged(ChangeEvent e)
   {
    slideVal = speedController.getValue();
    repaint();
   }
  }
  class PanelScroller implements ActionListener//Timer class-scrolls the JTextArea down
  {
   public void actionPerformed(ActionEvent e)
   {  
    yint-=slideVal/2; //changes the coordinates by the slider's value
    story.setLocation(0, yint);
   }
  }
 }
  class Panel extends JPanel implements KeyListener, MouseListener // this is the class for the main Panel, creates the contents for the game
   {
    boolean isPressed = false;
    Character cha = new Character();
    Mob mod = new Mob();
      //TimerTask stuff = new TimerTask();
    CharacterShoot shot = new CharacterShoot();
    Image map;
    int xShift = 0;
    int yShift = 0; 
    // these are the variables for the Panel class
    double clickedX;
    double clickedY;
    int slopeX;
    int slopeY;
    int slope;
    int counterKill;
    Image proj;
    Timer yee;
    int counter;
    int[] slipX = {0,0,0};
    int[] slipY = {0,0,0};
    private Timer mover ;
    private Timer mover2;
    private Timer mover3;
    private Timer mover4;
    private Timer mover5;
    private Timer mover6;
    int currentX = -1000;// to set the bullet out of the screen
    int currentY = -1000;
    boolean burstDone = true;
    int projRatio = 0; 
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
    int health;
   Image gif;
    public Panel() // the constructor makes all the intializes all the field variables and also makes the listeners for the panel. Makes variables for a few permanent images on the panel
    {
      grabFocus();
      setFocusable(true);
      addKeyListener(this);
      addMouseListener(this);
      if(wizardChosen == true)
      {
        gif = new ImageIcon("finalwiztelp.gif").getImage();
      }
      if(knightChosen == true)
      {
        gif = new ImageIcon("knighttelepfinal.gif").getImage();
      }
      if(ninjaChosen == true)
      {
        gif = new ImageIcon("ninjatelepfinal.gif").getImage();
      }
      HeroMover move = new HeroMover();
      mover = new Timer(140, move);
      BurstMover move2 = new BurstMover();
      mover2 = new Timer(60, move2);
      SlimeMover move3 = new SlimeMover();
      mover3 = new Timer(60, move3);//
      SlimeMover2 move4 = new SlimeMover2();
      mover4 = new Timer(60, move4);//
      SlimeMover3 move5 = new SlimeMover3();
      mover5 = new Timer(60, move5);//
      health = 0 ;
      try
      {
        map = ImageIO.read( new File("dun.png"));
      }
      catch(Exception e)
      {
        System.out.println("image not found.");
      }
      if(wizardChosen)
      {
        try{
          Image fakeproj = ImageIO.read(new File("fireball.png"));
          proj = fakeproj.getScaledInstance(5, 3, Image.SCALE_DEFAULT); //rescaling image using this method 
          
        }
        catch(Exception e)
        {
          System.out.println("proj not found");
        } 
      }
      if(knightChosen)
      {
        try{
          Image fakeproj = ImageIO.read(new File("knight_proj.png"));
          proj = fakeproj.getScaledInstance(5, 3, Image.SCALE_DEFAULT); //rescaling image using this method 
          
        }
        catch(Exception e)
        {
          System.out.println("proj not found");
        } 
      }
      if(ninjaChosen)
      {
        try{
          Image fakeproj = ImageIO.read(new File("shuriken.png"));
          proj = fakeproj.getScaledInstance(5, 3, Image.SCALE_DEFAULT); //rescaling image using this method 
          
        }
        catch(Exception e)
        {
          System.out.println("proj not found");
        } 
      }
    }

    class TimerImage implements ActionListener
 {
  public void actionPerformed(ActionEvent e)
  {
   counter++;
   if(counter == 15)
   {
    PuzzleGame pg = new PuzzleGame();
    cardholder.add(pg, "Puzzle");
    cl.show(cardholder, "Puzzle");
    pg.requestFocus();
    yee.stop();
   }
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
      g.drawString("Health:", 625, 20);
      g.drawString("Kills till boss room opens: " + ( 15 - counterKill),5 ,20);
      if(15 - counterKill <= 0)
      {
        mover.stop();
        mover2.stop();
        mover3.stop();
        mover4.stop();
        mover5.stop();
        g.drawImage(gif, 0, 0, 1000, 900, null);
        TimerImage ti = new TimerImage();
        yee = new Timer(2000, ti);
        yee.start();
      }
      else
      {
        g.setColor(Color.RED);
        g.fillRect(700, 5, 100, 20);
        g.setColor(Color.GREEN);
        g.fillRect(700, 5, 100 - health, 20);
      }
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
        if(health >= 100)
        {
          cl.show(cardholder, "Menu Panel");
        }
      }
     repaint();
    }
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
   if(wizardChosen)
   {
    for( int i = 0 ; i < attack.length ; i++)
    {
      try
      {
        
        attack[i] = ImageIO.read(new File("fireball.png"));
        
      }
      catch(Exception e)
      {
        System.out.println(" The pic wizardProj.png is not found");
        
      }

    }
   }
   if(knightChosen)
   {
     System.out.println("Im here");
    for( int i = 0 ; i < attack.length ; i++)
    {
      try
      {
        
        attack[i] = ImageIO.read(new File("knight_proj.png"));
        
      }
      catch(Exception e)
      {
        System.out.println(" The pic wizardProj.png is not found");
        
      }

    }
   }
   if(ninjaChosen)
   {
    for( int i = 0 ; i < attack.length ; i++)
    {
      try
      {
        
        attack[i] = ImageIO.read(new File("shuriken.png"));
        
      }
      catch(Exception e)
      {
        System.out.println(" The pic wizardProj.png is not found");
        
      }

    }
   }
  }
 
 
 public void check(){}// to check if there is space available
 
 public void add(){}// to add each image to the attack array
 
 public void remove(){}// based on conditions to add to each of the attack arrays
 
 public void draw(Graphics g){} //to draw the projectiles whenever it was nessecary
 
 
 
  
  
}
class PuzzleGame extends JPanel implements KeyListener, Runnable //class for the Panel that has the actual game on it 
{
 Image character3; //all listed below are Images of the character's movement
 Image character4;
 Image character7;
 Image character8;
 Image character5;
 Image character6;
 Image character1;
 Image character2;
 Image character11;
 Image character12;
 Image character;
 Image character76;
 Image character90;
 int frame = 0;
 int xCord = 100;
 int yCord = -80;
 int counter1, counter2, counter3;
 boolean sliding, switchFrame, mapOne, mapTwo, mapThree, wPressed, aPressed, sPressed, dPressed;
 javax.swing.Timer characterMovement, ti1, ti2, ti3;;
 Thread t1;
 private final String stopper = "stop";
 public PuzzleGame() //constructor for the Panel with the game 
 {
  if(wizardChosen)
  {
    character90 = new ImageIcon("wizard9.png").getImage();
    character = new ImageIcon("wizard10.png").getImage();
    character3 = new ImageIcon("wizard3.png").getImage(); //all listed below are Images of the character's movement
    character4 = new ImageIcon("wizard4.png").getImage();
    character7 = new ImageIcon("wizard7.png").getImage();
    character8 = new ImageIcon("wizard8.png").getImage();
    character5 = new ImageIcon("wizard5.png").getImage();
    character6 = new ImageIcon("wizard6.png").getImage();
    character1 = new ImageIcon("wizard1.png").getImage();
    character2 = new ImageIcon("wizard2.png").getImage();
    character11 = new ImageIcon("wizard10.png").getImage();
    character12 = new ImageIcon("wizard9.png").getImage();
    character76 = new ImageIcon("wizard9.png").getImage();
  }
   if(knightChosen)
  {
    character90 = new ImageIcon("knight9.png").getImage();
    character = new ImageIcon("knight10.png").getImage();
    character3 = new ImageIcon("knight3.png").getImage(); //all listed below are Images of the character's movement
    character4 = new ImageIcon("knight4.png").getImage();
    character7 = new ImageIcon("knight7.png").getImage();
    character8 = new ImageIcon("knight8.png").getImage();
    character5 = new ImageIcon("knight5.png").getImage();
    character6 = new ImageIcon("knight6.png").getImage();
    character1 = new ImageIcon("knight1.png").getImage();
    character2 = new ImageIcon("knight2.png").getImage();
    character11 = new ImageIcon("knight10.png").getImage();
    character12 = new ImageIcon("knight9.png").getImage();
    character76 = new ImageIcon("knight9.png").getImage();
  }
    if(ninjaChosen)
  {
    character90 = new ImageIcon("ninja9.png").getImage();
    character = new ImageIcon("ninja10.png").getImage();
    character3 = new ImageIcon("ninja3.png").getImage(); //all listed below are Images of the character's movement
    character4 = new ImageIcon("ninja4.png").getImage();
    character7 = new ImageIcon("ninja7.png").getImage();
    character8 = new ImageIcon("ninja8.png").getImage();
    character5 = new ImageIcon("ninja5.png").getImage();
    character6 = new ImageIcon("ninja6.png").getImage();
    character1 = new ImageIcon("ninja1.png").getImage();
    character2 = new ImageIcon("ninja2.png").getImage();
    character11 = new ImageIcon("ninja10.png").getImage();
    character12 = new ImageIcon("ninja9.png").getImage();
    character76 = new ImageIcon("ninja9.png").getImage();
  }
  CharacterMover cm = new CharacterMover();
  characterMovement = new Timer(140, cm);
  setFocusable(true);
  addKeyListener(this);
  requestFocus();
  mapOne = true;
  t1 = new Thread(this);
  t1.start();
  ForPortal fp = new ForPortal();
  Timer portal = new Timer(20, fp);
  portal.start();
 }
 public void paintComponent(Graphics g) //paints the images when it moves and the character and map
 {
  super.paintComponent(g);
  if(mapOne)//checks what maze player is on
  {
   Image map = new ImageIcon("easymaze1.gif").getImage();
   g.drawImage(map, 0, 0, 1000, 800, null);
   Image border = new ImageIcon("border.png").getImage();
   g.drawImage(border, 0, 795, 1000, 100, null);
   if(frame == 0) g.drawImage(character, xCord, yCord, 270, 270, null);
  }
  if(mapTwo) //checks if player has completed the first map
  {
   Image map2 = new ImageIcon("easymaze2.gif").getImage();
   g.drawImage(map2, 0, 0, 1000, 800, null);
   Image border = new ImageIcon("border.png").getImage();
   g.drawImage(border, 0, 795, 1000, 100, null);
   if(frame == 0) g.drawImage(character90, xCord, yCord, 270, 270, null);
  }
  if(mapThree) 
  {
   Image map3 = new ImageIcon("easymaze3.gif").getImage();
   g.drawImage(map3, 0, 0, 1000, 800, null);
   Image border = new ImageIcon("border.png").getImage();
   g.drawImage(border, 0, 795, 1000, 100, null);
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
  while(true)
  {
   synchronized(stopper) 
   {
    try
    {
     stopper.wait(20);
    }
    catch(InterruptedException ex)
    {
     System.err.println("ef");
    }
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
        Thread.currentThread().sleep(10); //allows time to repaint
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
        Thread.currentThread().sleep(10); //allows time to repaint
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
        Thread.currentThread().sleep(10); //allows time to repaint
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
      if(xCord == -100 || xCord == 500 && yCord <= 550 && yCord >= 455 || xCord == 500 && yCord == 80 || xCord == 740 && yCord == 170 || xCord == 100 && yCord == 625 || xCord == 400 && yCord == 185 || xCord == -5 && yCord == 455 || xCord == 400 && yCord == 160|| xCord == -5 && yCord == 255 || xCord == 800 && yCord == 95 || xCord == 305 && yCord == -100) //hardcoded stoppers for the player so he doesn't slide through the icicles
      {
       sliding = false; //make slide stop
      }
      else
      {
       xCord-=5;//changes coordinate for movement
       try
       {
        Thread.currentThread().sleep(10); //allows time to repaint
       }
       catch(InterruptedException exception)
       {
        System.err.println("WEFWEF");
       }
      }
     }
     else if(mapTwo)
     {
      if(xCord == -100 || xCord == 590 && yCord == 465 || yCord == 220 && xCord == 795 || yCord == 465 && xCord == 625 || xCord == 105 && yCord == 300)  //hardcoded stoppers for the player so he doesn't slide through the icicles
      {
       sliding = false; //make slide stop
      }
      else
      {       
       xCord-=5;//changes coordinate for movement
       try
       {
        Thread.currentThread().sleep(10); //allows time to repaint
       }
       catch(InterruptedException exception)
       {
        System.err.println("WEFWEF");
       }
      }
     }
     else if(mapThree)
     {
      if(xCord == -100 || xCord == 790 && yCord == 625 || xCord == 415 && yCord == 285 || xCord == 495 && yCord == -100 || xCord == 495 && yCord == 625)
      { 
       sliding = false; //make slide stop
      }
      else
      {       
       xCord-=5;//changes coordinate for movement
       try
       {
        Thread.currentThread().sleep(10);//allows time to repaint
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
      if(yCord == 625 || yCord == 180 && xCord >= -60 && xCord <= 0 || xCord == 640 && yCord == 185 || xCord == 400 && yCord == 455 || xCord == -5 && yCord == 540 || xCord == 820 && yCord == 95 || xCord == -100 && yCord == 180 || xCord == 305 && yCord == 90 || xCord == -5 && yCord == 535)
      { 
       sliding = false; //make slide stop
      }
      else
      {
       yCord+=5;//changes coordinate for movement
       try
       {
        Thread.currentThread().sleep(10);
       }
       catch(InterruptedException exception)
       {
        System.err.println("WEFWEF");
       }
      }
     }
     else if(mapTwo)
     {
      if(yCord == 625 || xCord == 630 && yCord == 465 || xCord == 630 && yCord == 465 || xCord >= -100 && xCord <= -65 && yCord == 300 || xCord == 625 && yCord == 465)
      {
       sliding = false; //make slide stop
      }
      else
      {       
       yCord+=5;//changes coordinate for movement
       try
       {
        Thread.currentThread().sleep(10);
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
      if(yCord == 625 || xCord == 415 && yCord == 520 || xCord == -100 && yCord == -5 || xCord == 820 && yCord == 140)
      { 
       sliding = false; //make slide stop
      }
      else
      {       
       yCord+=5;//changes coordinate for movement
       try
       {
        Thread.currentThread().sleep(10);
       }
       catch(InterruptedException exception)
       {
        System.err.println("WEFWEF");
       }
      }      if(xCord == 625 && yCord == 570)
      {
        BossPanel bp = new BossPanel();
        cardholder.add(bp, "Boss");
        cl.show(cardholder, "Boss");
        bp.requestFocus();
      }
     }
    }
   }
   else if(dPressed)
   {
    while(sliding)
    {
     if(mapOne == true) 
     { 
      if(xCord == 820 || xCord == 150 && yCord <= -30 || xCord == -60 && yCord <= 280 && yCord >= 150 || xCord == 340 && yCord <= 150 && yCord >= 10|| xCord == 640 && yCord == 80 || xCord == 740 && yCord == 185 || xCord == -60 && yCord == 625|| xCord == 740 && yCord == 165 || xCord == 320 && yCord == 255 || xCord == 335 && yCord == 90 || xCord == 740 && yCord == 160)
      {
       sliding = false; //make slide stop
      }
      else 
      {
       xCord +=5;//changes coordinate for movement
       try
       {
        Thread.currentThread().sleep(10);
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
        Thread.currentThread().sleep(10);
       }
       catch(InterruptedException exception)
       {
        System.err.println("WEFWEF");
       }
      }
     }
     else if(mapThree)
     {
      if(xCord == 820 || xCord == 135 && yCord == 520 || xCord == 330 && yCord == 625 || xCord == 625 && yCord == -5 || xCord == 625 && yCord == -25)
      { 
       sliding = false; //make slide stop
      }
      else
      {       
       xCord+=5;//changes coordinate for movement
       try
       {
        Thread.currentThread().sleep(10);
       }
       catch(InterruptedException exception)
       {
        System.err.println("WEFWEF");
       }
      }
      if(xCord == 615 && yCord == 625)
      {
        BossPanel bp = new BossPanel();
        cardholder.add(bp, "Boss");
        cl.show(cardholder, "Boss");
        bp.requestFocus();
      }
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
   synchronized(stopper)
   {
    stopper.notifyAll();
   }
  }
  else if(phase == 'a')
  {
   sliding = true;
   frame = 7;
   characterMovement.start(); //starts timer
   aPressed = true;
   synchronized(stopper)
   {
    stopper.notifyAll();
   }
  }  
  else if(phase == 's')
  { 
   sliding = true;
   frame = 5;
   characterMovement.start(); //starts timer
   sPressed = true;
   synchronized(stopper)
   {
    stopper.notifyAll();
   }
  } 
  else if(phase == 'd')
  {
   sliding = true;
   frame = 1;
   characterMovement.start(); //starts timer
   dPressed = true;
   synchronized(stopper)
   {
    stopper.notifyAll();
   }
  }
 }

 class CharacterMover implements ActionListener //timer class
 {
  public void actionPerformed(ActionEvent e) //every time the timer hits 140 milliseconds,
  {           // it turns the boolean to switch frame true and false
   switchFrame = !(switchFrame);
   repaint();  // whenever it is true, it draws one image, whenever its false
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
  if(cha.currentFrame == 1 || cha.currentFrame == 2) cha.currentFrame = 1;
  if(cha.currentFrame == 7 || cha.currentFrame == 8) cha.currentFrame = 8;
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
    currentY = 300;
    
    projRatio = 75;
    
    
    
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
  
 class BossPanel extends JPanel implements KeyListener, MouseListener // this class creates the JFrame
{
  Character cha = new Character();
  HeroBullet bullet = new HeroBullet();
  BossMobs mod = new BossMobs();
  int player = 75;
  int waterSpirit = 1000;
  int joeboticus = 1000;
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
    
    public BossPanel()// stores all the variables for the panel
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
      
      
      if(player <= 0) 
      {
        cl.show(cardholder, "Menu Panel");
      }
      
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
      if(joeboticus == 0) 
      {
        cl.show(cardholder, "Menu Panel");
      }
      
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
        g.drawImage(bullet.wizardShot[i],bullet.currentX[i] -70 ,bullet.currentY[i] -70,70,70,null);
        
        
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
   class HeroBullet // regulates the shooting for the character
{
  Image[] wizardShot = new Image[3];
  boolean[] appear = {false,false,false}; 
  int[] currentX = {0,0,0};
  int[] currentY = {0,0,0};
  int[] bulletX = {0,0,0};
  int[] bulletY = {0,0,0};
  
  
 public HeroBullet()// assgins the .png files for the character bullets
 {
   if(wizardChosen)
   {
     
   for( int i = 0 ; i < wizardShot.length ; i++)
    {
      try
      {
        
        wizardShot[i] = ImageIO.read(new File("fireball.png"));
        
      }
      catch(Exception e)
      {
        System.out.println(" The pic boss wizard proj is not found");
        
      }
      
    }
   }
   if(ninjaChosen)
   {
     
   for( int i = 0 ; i < wizardShot.length ; i++)
    {
      try
      {
        
        wizardShot[i] = ImageIO.read(new File("shuriken.png"));
        
      }
      catch(Exception e)
      {
        System.out.println(" The pic boss wizard proj is not found");
        
      }
      
    }
   }
   if(knightChosen)
   {
     
   for( int i = 0 ; i < wizardShot.length ; i++)
    {
      try
      {
        
        wizardShot[i] = ImageIO.read(new File("knight_proj.png"));
        
      }
      catch(Exception e)
      {
        System.out.println(" The pic boss wizard proj is not found");
        
      }
      
    }
   }
 }
   
  
} 
  }
  

 class ControlPanel extends JPanel implements ActionListener, KeyListener //this is the controls panel, explaining all the controls for the player
 {
  Image realBackOnButton, backOnButton, wImage, aImage, sImage, dImage, realWImage, realAImage, realSImage, realDImage;
  JButton back;
  int frameNumber1, frameNumber2;
  boolean switchChanger;
  JTextArea disclaimer;
  JButton blankKey1, blankKey2, wKey, aKey, sKey, dKey;
  JLabel mouseGif, shootgif;
  JPanel keys, movementOne, movementTwo, movementThree;
  Timer switchP;
  Font buttonFont = new Font("arial", Font.BOLD, 48);
  Font grabFont = new Font("arial", Font.BOLD, 14);
  public ControlPanel() //controls panel constructor
  {
   setBackground(Color.BLACK);
   setLayout(new GridLayout(2,2,10,0));
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
   back.setOpaque(false);
   back.setContentAreaFilled(false);
   back.setBorderPainted(false);
   addKeyListener(this); //allows Key input to function
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
   wKey = new JButton(); //looks like keyboard keys
   wImage = new ImageIcon("WButton.png").getImage();
   Image realWImage = wImage.getScaledInstance(350, 350, Image.SCALE_DEFAULT); //rescaling image using this method 
   try //checks for image to put into back button
   {
    wKey.setIcon(new ImageIcon(realWImage));
   }
   catch(Exception e)
   {
    System.err.println("Image not found.");
   }

   aKey = new JButton();//looks like keyboard keys
   aImage = new ImageIcon("abutt.png").getImage(); 
   Image realAImage = aImage.getScaledInstance(350, 350, Image.SCALE_DEFAULT); //rescaling image using this method 
   try //checks for image to put into back button
   {
    aKey.setIcon(new ImageIcon(realAImage));
   }
   catch(Exception e)
   {
    System.err.println("Image not found.");
   }
   sKey = new JButton();//looks like keyboard keys
   sImage = new ImageIcon("SButton.png").getImage();
   Image realSImage = sImage.getScaledInstance(350, 350, Image.SCALE_DEFAULT); //rescaling image using this method 
   try //checks for image to put into back button
   {
    sKey.setIcon(new ImageIcon(realSImage));
   }
   catch(Exception e)
   {
    System.err.println("Image not found.");
   }
   dKey = new JButton();//looks like keyboard keys
   dImage = new ImageIcon("DButton.png").getImage();
   Image realDImage = dImage.getScaledInstance(350, 350, Image.SCALE_DEFAULT); //rescaling image using this method 
   try //checks for image to put into back button
   {
    dKey.setIcon(new ImageIcon(realDImage));
   }
   catch(Exception e)
   {
    System.err.println("Image not found.");
   }
   wKey.addActionListener(this);//allows user button input to check movement controls
   aKey.addActionListener(this);//allows user button input to check movement controls
   sKey.addActionListener(this);//allows user button input to check movement controls
   dKey.addActionListener(this);//allows user button input to check movement controls
   wKey.setActionCommand("W");
   aKey.setActionCommand("A");
   sKey.setActionCommand("S");
   dKey.setActionCommand("D");
   wKey.setContentAreaFilled(false);
   wKey.setBorderPainted(false);
   aKey.setContentAreaFilled(false);
   aKey.setBorderPainted(false);
   sKey.setContentAreaFilled(false);
   sKey.setBorderPainted(false);
   dKey.setContentAreaFilled(false);
   dKey.setBorderPainted(false);
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
   keys.setBackground(Color.BLACK);
   Anima animate = new Anima();
   switchP = new Timer(140, animate); //timer switches between the two images per animation frame
   movementOne = new JPanel();
   movementOne.setOpaque(false);
   movementTwo = new JPanel();
   Image secondgif = new ImageIcon("shootgif.gif").getImage().getScaledInstance(495, 395, Image.SCALE_DEFAULT);
   Image gif = new ImageIcon("mouseclicking.gif").getImage().getScaledInstance(600, 500, Image.SCALE_DEFAULT); 
   mouseGif = new JLabel(new ImageIcon(gif)); //possible for gifs to work if used in JLabel
   shootgif = new JLabel(new ImageIcon(secondgif));
   add(keys);
   disclaimer = new JTextArea("Please click one of the keyboard outputs to learn its function\nor type it on your keyboard.");
   disclaimer.setForeground(Color.WHITE);
   disclaimer.setOpaque(false);
   disclaimer.setEditable(false);
   disclaimer.setFont(grabFont);
   disclaimer.addKeyListener(this);
   //movementOne.setFocusable(true); 
   //movementOne.requestFocus();  
   movementOne.add(disclaimer);
   movementTwo.setLayout(new FlowLayout(FlowLayout.RIGHT));
   movementTwo.add(back);
   movementTwo.add(shootgif);
   //CharacterShoot movement2 = new CharacterShoot();
   add(movementOne);
   add(mouseGif);
   add(movementTwo);
   setVisible(true);
  }
  public void paintComponent(Graphics g) //paintcomponent to draw frames of animation
  {
   super.paintComponent(g);
   if(frameNumber1 == 0)
   {
    g.setColor(Color.BLACK);
    g.fillRect(500, 0, 500, 500);
    repaint();
   }
   if(frameNumber1 == 3) //switches between the 2 images we use to get the up movement
   {
    Image yeet3 = new ImageIcon("wizard3.png").getImage();
    Image yeet4 = new ImageIcon("wizard4.png").getImage();
    if(switchChanger == false) g.drawImage(yeet3, 590, 90, 270, 270, null);
    else g.drawImage(yeet4, 590, 90, 270, 270, null);
    repaint();
   }
   if(frameNumber1 == 7) //switches between the 2 images we use to get the left movement
   {
    Image yeet7 = new ImageIcon("wizard7.png").getImage();
    Image yeet8 = new ImageIcon("wizard8.png").getImage();
    if(switchChanger == false) g.drawImage(yeet7, 590, 90, 270, 270, null);
    else g.drawImage(yeet8, 590, 90, 270, 270, null);
    repaint();
   }
   if(frameNumber1 == 5) //switches between the 2 images we use to get the down movement
   {
    Image yeet5 = new ImageIcon("wizard5.png").getImage();
    Image yeet6 = new ImageIcon("wizard6.png").getImage();
    if(switchChanger == false) g.drawImage(yeet5, 590, 90, 270, 270, null);
    else g.drawImage(yeet6, 590, 90, 270, 270, null);
    repaint();
   }
   if(frameNumber1 == 1) //switches between the 2 images we use to get the right movement
   {
    Image yeet1 = new ImageIcon("wizard1.png").getImage();
    Image yeet2 = new ImageIcon("wizard2.png").getImage();
    if(switchChanger == false) g.drawImage(yeet1, 590, 90, 270, 270, null);
    else g.drawImage(yeet2, 590, 90, 270, 270, null);
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
       frameNumber1 = 0;
       disclaimer.setText("Please click one of the keyboard outputs to learn its function\nor type it on your keyboard.");
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


