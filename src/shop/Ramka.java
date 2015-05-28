package shop;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
//import javax.swing.JTextArea; //do czatu
import javax.swing.JTextField;

import slownik.Mapa;


public class Ramka extends JFrame implements KeyListener{
	private static final long serialVersionUID = 1L;
	//public static JTextArea czat;
	public static int poz=300,pion=300;
	public static Plansza plansza;
	protected JTextField polecenie;
	public static boolean blokada=false;
	public static Mapa mapka = new Mapa();
	public static Ramka instance;
	public static ramy_wiedzy.Agent ramyAgent = new ramy_wiedzy.Agent();
	
	public Ramka(){
		super("Shop");
		instance=this;
		setSize(800, 750); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);

		/*
		czat=new JTextArea();
		czat.setFocusable(false);
		czat.setOpaque(false);//przezr.tlo
		czat.setBounds(5, 615, 600, 50);
		czat.setLineWrap(true);
		czat.setWrapStyleWord(true);*/
		
		polecenie=new JTextField();
		polecenie.setFocusable(true);
		polecenie.setBounds(5, 670, 600, 20);
		
		plansza=new Plansza();
		plansza.setBounds(5,5,601,601);
		
		
		add(plansza);
		
		polecenie.addKeyListener(this); 
		
		//add(czat);
		add(polecenie);
		setVisible(true);

		polecenie.requestFocusInWindow();
		
		new si.Astar();
		
	}
	/*
	 gdzieœ w tym pliku 
	 wyskakiwanie dod okienka do wprowadzania tych danych:
	 Plansza.shop[0][0]=new Place("P","Woda","Napoje");
		Product p=new Product();
		p.nazwa="Woda mineralna ABC";
		p.rodzaj="Woda";//jak 3 linie wy¿ej
		p.wielkosc=1.5;
		p.jednostka="l";
		p.ilosc=0;
		p.glRozroznik="gazowana";
		p.dodRozroznik="";
		Plansza.shop[0][0].add(p);
	 */
	
	@Override
	public void keyPressed(KeyEvent e) {	}
	public void keyTyped(KeyEvent e) {	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			if(blokada==false){
				
			String text = polecenie.getText();
			 if(text!=""){
				 text = text.toLowerCase();
				try {
					mapka.coRobic(text);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			 }
			polecenie.setText("");
			}
			
		}
	}
	
}