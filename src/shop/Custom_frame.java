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
	protected JTextField command;
	public static boolean blockade=false;
	public static Map custom_map = new Map();
	public static Custom_frame instance;
	
	public Custom_frame(){
		super("Shop");
		instance=this;
		setSize(800, 750); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);

		board=new Board();
		board.setBounds(5,5,601,601);		
		add(board);

		
		/*
		czat=new JTextArea();
		czat.setFocusable(false);
		czat.setOpaque(false);//przezr.tlo
		czat.setBounds(5, 615, 600, 50);
		czat.setLineWrap(true);
		czat.setWrapStyleWord(true);
		add(czat);*/
		
		command=new JTextField();
		command.setFocusable(true);
		command.setBounds(5, 670, 600, 20);
		command.addKeyListener(this); 
		add(command);

		setVisible(true);
		command.requestFocusInWindow(); 
	}
	/*
	 gdzie� w tym pliku 
	 wyskakiwanie dod okienka do wprowadzania tych danych:
	 Board.shop[0][0]=new Place("P","Woda","Napoje");
		Product p=new Product();
		p.nazwa="Woda mineralna ABC";
		p.rodzaj="Woda";//jak 3 linie wy�ej
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
		int keyCode = e.getKeyCode();
		//System.out.println(keyCode);
	    switch( keyCode ) {
		    case KeyEvent.VK_ENTER : {
		    	if(blockade==false){				
					String text = command.getText();
					if(text!=""){
						text = text.toLowerCase();
						try {
							custom_map.whatToDo(text);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
					}
					command.setText("");
				}
		    }
			case KeyEvent.VK_UP:
		        // handle up
		    	//System.out.println("jest gora");
		    	Movement.getInstance().move_yourself(0, -1); //poziom,pion (na minus to w górę)
		        break;
		    case KeyEvent.VK_DOWN:
		        // handle down 
		    	Movement.getInstance().move_yourself(0, 1);
		        break;
		    case KeyEvent.VK_LEFT:
		        // handle left
		    	Movement.getInstance().move_yourself(-1, 0);
		        break;
		    case KeyEvent.VK_RIGHT :
		        // handle right
		    	Movement.getInstance().move_yourself(1, 0);	
		        break;
	    }
	}
	
}