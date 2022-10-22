import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class Calculator extends JFrame
{
 JPanel pa=new JPanel();
 CalButton [] bt=new CalButton[20];
 JTextField tb=new JTextField("0");
 int num=0;
 String op=""; 
 boolean cal=false; 
 public Calculator()
 {
	super("Calculator");
	setSize(300,300);
	setLocationRelativeTo(null);
	setResizable(false);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLayout(null);
	addTextBox();
	addButtons();
	setVisible(true); 
 }
 private void addTextBox()
 {
	tb.setBounds(10,10,270,50);
	tb.setEditable(false);
	tb.setBackground(Color.white);
	tb.setBorder(BorderFactory.createLineBorder(Color.black,1));
	tb.setFont(new Font("arial",1,20));
	//tb.setHorizontalAlignment(4);
	tb.setHorizontalAlignment(JTextField.RIGHT);
	add(tb);
 } 
 private void addButtons()
 {
	pa.setBounds(10,75,270,180);
	//pa.setBackground(Color.yellow);	
	add(pa);
	pa.setLayout(new GridLayout(5,4,5,5));
	String []str={"Back","CE","C","%","7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+"};
	Font fo=new Font("Latin",0,18);
	Insets ins=new Insets(0,0,0,0);
	CalListener listener=new CalListener();
	for(int i=0;i<bt.length;i++)
	{
	  bt[i]=new CalButton(str[i],i);
	  bt[i].addActionListener(listener);
	  bt[i].setFont(fo);
	  bt[i].setMargin(ins);
	  bt[i].setForeground(Color.blue);
	  if(i==3 || i==7 || i==11 || i==15 || i==19)
	    bt[i].setForeground(Color.red);
	  pa.add(bt[i]);
	}
 }
 class CalListener implements ActionListener
 { 
   String tbtext="";
   public void actionPerformed(ActionEvent evt)
   {
	CalButton bc=(CalButton)evt.getSource();
	String bctext=bc.getText();
	tbtext=tb.getText();
	if(!tbtext.equals("0"))
	{
	  if(bc.bid==3||bc.bid==7||bc.bid==11||bc.bid==15||bc.bid==19)
	  {
		if(num!=0)
		  cal();
		num=Integer.parseInt(tb.getText());
		op=bctext;
		cal=true;
	  }
	}
	if(bc.bid==4||bc.bid==5||bc.bid==6||bc.bid==8||bc.bid==9||bc.bid==10||bc.bid==12||bc.bid==13||bc.bid==14||bc.bid==16)
	{
	  if(tbtext.equals("0") || cal)
	  {
	    tb.setText(bctext);
	    cal=false;
	  }
	  else
	    tb.setText(tbtext+bctext);
	}
	if(bc.bid==18)
	{
	  cal();
	}
   }
   private void cal()
   {
	  if(op.equals("+"))
	   tb.setText(String.valueOf(num+Integer.parseInt(tbtext)));
	  if(op.equals("-"))
	   tb.setText(String.valueOf(num-Integer.parseInt(tbtext)));
	  if(op.equals("*"))
	   tb.setText(String.valueOf(num*Integer.parseInt(tbtext)));
	  if(op.equals("/"))
	   tb.setText(String.valueOf((float)num/Integer.parseInt(tbtext)));

   }
 }   
 public static void main(String args[])
 {
	JFrame.setDefaultLookAndFeelDecorated(true);
	new Calculator();
 }
}