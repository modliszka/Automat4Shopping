package shop;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Board extends JPanel {
	private static final long serialVersionUID = 1L;

	private Image agentIcon;
	//public static Board instance;
	static int option=1;
	static String abc="b";
	public static Place[][] shop;
	
//	public static Board getInstance(){
//		if (instance == null){
//			instance = new Board();
//		}
//		return instance;
//	}
	protected Board(){
		//RozmiescRegaly();
		
		shop=new Place[10][10];
		setFocusable(true);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		//g.drawImage((Image) new ImageIcon(this.getClass().getResource("board.gif")).getImage(), 0, 0, this);
		g.drawImage((Image) new ImageIcon(this.getClass().getResource("MapkaSklep.gif")).getImage(), 0, 0, this); //tło planszy
		
		
		for(int z=0;z<=600;z+=60){
		 g.drawLine(z, 0, z, 600);
		}
		for(int z=0;z<=600;z+=60){
		 g.drawLine(0, z, 600, z);
		}
				
		agentIcon= new ImageIcon(this.getClass().getResource("agent/"+option+abc+".gif")).getImage();
		g.drawImage((Image) agentIcon,35+MainWindow.hor,17+MainWindow.vert,this);
	//	g.drawImage((Image) agentIcon,15+Custom_frame.hor,10+Custom_frame.vert,this);
		
	
	}	
}