package shop;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
//import javax.swing.JTextArea; //do czatu
import javax.swing.JTextField;

import vocabulary.Map;


public class Custom_frame extends JFrame implements KeyListener{
	private static final long serialVersionUID = 1L;
	//public static JTextArea czat;
	public static int hor=300,vert=300;
	public static Board board;
	protected JTextField polecenie;
	public static boolean blockade=false;
	public static Map mapka = new Map();
	public static Custom_frame instance;
	
	public Custom_frame(){
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
		
		board=new Board();
		board.setBounds(5,5,601,601);
		
		
		add(board);
		
		polecenie.addKeyListener(this); 
		
		//add(czat);
		add(polecenie);
		setVisible(true);

		polecenie.requestFocusInWindow();
		
	}
	/*
	 gdzieœ w tym pliku 
	 wyskakiwanie dod okienka do wprowadzania tych danych:
	 Board.shop[0][0]=new Place("P","Woda","Napoje");
		Product p=new Product();
		p.nazwa="Woda mineralna ABC";
		p.rodzaj="Woda";//jak 3 linie wy¿ej
		p.wielkosc=1.5;
		p.jednostka="l";
		p.ilosc=0;
		p.glRozroznik="gazowana";
		p.dodRozroznik="";
		Board.shop[0][0].add(p);
	 */
	
	@Override
	public void keyPressed(KeyEvent e) {	}
	public void keyTyped(KeyEvent e) {	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			if(blockade==false){
				
			String text = polecenie.getText();
			 if(text!=""){
				 text = text.toLowerCase();
				try {
					mapka.whatToDo(text);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			 }
			polecenie.setText("");
			}
			
		}
	}
	
}