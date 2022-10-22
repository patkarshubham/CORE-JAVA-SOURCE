import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
class BounceBall extends JFrame
{
  JLabel la=new JLabel();
  JLabel ball=new JLabel(new ImageIcon("pics/ball.png"));
  int ox,oy,ballx,bally,xd,rbi,ow,wally=40,partx=400,nob=0,wi=0;
  Random ra=new Random();
  JLabel start=new JLabel("<html><b>Press ENTER to continue</b></html>");
  boolean gs=false;	
  JLabel [] balls=new JLabel[7];	
  JLabel []wall=new JLabel[3];
  JLabel part=new JLabel();
  JLabel part1=new JLabel();
  int [] startx={50,60,70,150,160,170,180,190,200,210,220,300,310,320};
  JPanel wp[]=new JPanel[3];
  public BounceBall()
  {
	restart();
	setSize(500,500);
	setLayout(null);
	setResizable(false);
	getContentPane().setBackground(Color.white);
	setLocationRelativeTo(null);
	start.setBounds(0,200,400,80);
	start.setOpaque(true);
	start.setBackground(Color.yellow);
	start.setHorizontalAlignment(JLabel.CENTER);
	add(start);
	la.setBorder(BorderFactory.createLineBorder(Color.black,25));
	la.setBounds(160,445,ow,25);
	add(la);
	add(ball);
	addWall();
	addPart();
	winPanel();
	addKeyListener(new MO());
	setVisible(true);
  }
  void winPanel()
  {
	for(int i=0;i<wp.length;i++)
	{
	  wp[i]=new JPanel();
	  wp[i].setBackground(Color.white);
	  add(wp[i]);
	  wp[i].setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
	}
	wp[0].setBounds(0,8,100,30);
	wp[2].setBounds(300,8,100,30);
  }
  void addPart()
  {
	part.setBorder(BorderFactory.createLineBorder(Color.red,20));	
	part.setBounds(partx,0,20,500);
	add(part);
	part1.setBorder(BorderFactory.createLineBorder(Color.blue,20));	
	part1.setBounds(420,230,80,20);
	add(part1);
	addBalls();
  }
  void addBalls()
  {
	JPanel pballs=new JPanel();
	pballs.setBackground(Color.white);
	pballs.setBounds(435,13,30,220);
	add(pballs);
	pballs.setLayout(new GridLayout(7,1));
	Icon icon=new ImageIcon("pics/ball.png");
	for(int i=0;i<balls.length;i++)
	{
	  balls[i]=new JLabel(icon);
	  pballs.add(balls[i]);
	}
  }	
  void addWall()
  {
	for(int i=0;i<wall.length;i++)
	{
	  wall[i]=new JLabel();
	  wall[i].setBorder(BorderFactory.createLineBorder(Color.blue,20));
	  add(wall[i]);
	}
	wall[0].setBounds(0,wally,100,20);
	wall[1].setBounds(170,wally,60,20);
	wall[2].setBounds(300,wally,100,20);
  }
  class MBall extends Thread
  {
     public void run()
     {
	boolean dd=true;
	while(true)
	{
	  if(dd)
	  {
	    bally+=5;
	    int yd=oy-bally;
	    xd=ballx-ox;
	    if(yd==30 && xd>=-25 && xd<=55)
	      dd=false;
	    else if(bally>=500)
	    {
	      if(rbi==7)
	      {
	        gameover();
	        return;
	      }
	      JOptionPane.showMessageDialog(getContentPane(),"Oooops!!!(Press ok)");
	      newb(); 
	      break;
	    }
	  }
	  else
	  {
	     if(bally==0)
	     {
	       if(rbi==7)	
	       {
	        gameover();
	        return;
	       }
	       JOptionPane.showMessageDialog(getContentPane(),"Well done!!!(Press ok)");
	       nob++;
	       if(nob==4)
	        wi=2;
	       wp[wi].add(new JLabel(new ImageIcon("pics/ball.png")));
	       newb();
	       break;
	     }	
	     if(xd<=-15)
	      ballx+=3;
	     else if(xd<=-5)
	      ballx+=2;
	     else if(xd<=5)
	      ballx+=1;
	     else if(xd>=50)
	      ballx-=3;
	     else if(xd>=40)
	      ballx-=2;
	     else if(xd>=25)
	      ballx-=1;
	     bally-=5;
	     if(bally==55 && ((ballx>=105 && ballx<=140) || (ballx>=230 && ballx<=265)))
	     {
	       continue;
	     }
	     if(bally==55)
	       dd=true;
	  }
	  if((bally!=wally) && (ballx==0 || ballx>=(partx-28)))
	  {
		if(rbi==7)
		  rbi=0;
		JOptionPane.showMessageDialog(getContentPane(),"'Ooops!!!(Press ok)");
		newb();
		break;
	  }
	  ball.setLocation(ballx,bally);
	  try{
	   sleep(10);
	  }catch(Exception ex){}
	}
     }	
  }
  void gameover()
  {
	start.setText("<html><h2 style='color:red'>Game is over</h2><b>Press ENTER to restart</b></html>");
	start.setVisible(true); 
	la.setBounds(160,445,ow,25);	
	gs=false;
  }
  class MO extends KeyAdapter
  {
     public void keyPressed(KeyEvent evt)
     {
	if(evt.getKeyCode()==KeyEvent.VK_ESCAPE)
	{
	  System.exit(0);
	}
	if(! gs && evt.getKeyCode()==KeyEvent.VK_ENTER)
	{
	  rbi=0;
	  for(int i=0;i<balls.length;i++)
	    balls[i].setVisible(true);
	  newb();
	  start.setVisible(false);
	}
	if(evt.getKeyCode()==KeyEvent.VK_LEFT)
	{
	  if(ox<=0)
	   return;
	  ox-=15; 
	  la.setLocation(ox,445);
	}
	if(evt.getKeyCode()==KeyEvent.VK_RIGHT)
	{
	  if(ox>=340)
	   return;
	  ox+=15; 
	  la.setLocation(ox,445);
	}
     } 	
  }
  void newb()
  {
	restart();
	balls[rbi++].setVisible(false);
	ballx=startx[ra.nextInt(startx.length)];
	ball.setBounds(ballx,bally,30,30);
	la.setBounds(ox,445,ow,25);
	new MBall().start();
	gs=true;	  
  }
  void restart()
  {
     ox=160;oy=445;ballx=0;bally=60;xd=0;ow=60;
  }
  public static void main(String ...s)
  {
	JFrame.setDefaultLookAndFeelDecorated(true);
	new BounceBall();
  }
}








