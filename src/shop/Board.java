package shop;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Plansza extends JPanel {
	private static final long serialVersionUID = 1L;

	private Image agentIcon;
	//public static Plansza instance;
	static int opcja=1;
	static String abc="b";
	public static Place[][] shop;
	
//	public static Plansza getInstance(){
//		if (instance == null){
//			instance = new Plansza();
//		}
//		return instance;
//	}
	protected Plansza(){
		//RozmiescRegaly();
		shop=new Place[10][10];
		setFocusable(true);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		//g.drawImage((Image) new ImageIcon(this.getClass().getResource("plansza.gif")).getImage(), 0, 0, this); //t³o planszy
		g.drawImage((Image) new ImageIcon(this.getClass().getResource("MapkaSklep.gif")).getImage(), 0, 0, this); //t³o planszy
		
		
		for(int z=0;z<=600;z+=60){
		 g.drawLine(z, 0, z, 600);
		}
		for(int z=0;z<=600;z+=60){
		 g.drawLine(0, z, 600, z);
		}
				
		agentIcon= new ImageIcon(this.getClass().getResource("agent/"+opcja+abc+".gif")).getImage();
		g.drawImage((Image) agentIcon,35+Ramka.poz,17+Ramka.pion,this);
	//	g.drawImage((Image) agentIcon,15+Ramka.poz,10+Ramka.pion,this);
		
	
	}	
}