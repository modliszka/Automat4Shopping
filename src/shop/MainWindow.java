package shop;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import vocabulary.Map;


public class MainWindow extends JFrame implements KeyListener{
	public static MainWindow instance;
	private static final long serialVersionUID = 1L;
	public static int hor=300,vert=300;
	public static Board board;
	public static JTextArea productsList;
	protected static JButton doProductListButton;
	protected static JTextField command;
	public static boolean blockade=false;
	public static Map custom_map = new Map();
	
	public MainWindow(){
		super("Shop");
		instance=this;
		setSize(800, 750); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);

		//Plansza
		board=new Board();
		board.setBounds(5,5,601,601);		
		add(board);		
		
		//Lista produktow po prawej stronie
		productsList=new JTextArea();
		productsList.setFocusable(false);
		productsList.setOpaque(false);//przezr.tlo
		productsList.setBounds(615, 5, 100, 100);
		productsList.setLineWrap(true);
		productsList.setWrapStyleWord(true);
		add(productsList);
		
		//przycisk do okna z tworzeniem listy zakupow
		doProductListButton = new JButton("Dodaj produkty do listy");
		doProductListButton.setVerticalTextPosition(AbstractButton.CENTER);
		doProductListButton.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
		doProductListButton.setEnabled(true);
		//doProductListButton.addActionListener(this);
		add(doProductListButton);
		
		//pole do poleceń dot. chodzenia
		command=new JTextField();
		command.setFocusable(true);
		command.setBounds(5, 670, 600, 20);
		command.addKeyListener(this); 
		add(command);

		setVisible(true);
		command.requestFocusInWindow(); 
		
		
		addProductsToBoard();
	}
	/*
	 gdzies w tym pliku 
	 wyskakiwanie dod okienka do wprowadzania tych danych:
	 Board.shop[0][0]=new Place("R","water","beverages");
		Product p=new Product();
		p.nazwa="Woda mineralna ABC";
		p.rodzaj="Woda";//jak 3 linie wyzej
		p.wielkosc=1.5;
		p.jednostka="l";
		p.ilosc=0;
		p.glRozroznik="gazowana";
		p.dodRozroznik="";
		Board.shop[0][0].add(p);
	 */
	
	private void addProductsToBoard() {
		Board.shop[0][1]=new Place("R","chocolates","candies");
		Board.shop[0][3]=new Place("R","teas","teas");
		Board.shop[0][6]=new Place("R","juices","beverages");
	}

	@Override
	public void keyPressed(KeyEvent e) {	}
	public void keyTyped(KeyEvent e) {	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(blockade==false){ //zeby nie reagowal na nacisniecie kilku przyciskow na raz
		    switch( keyCode ) {
			    case KeyEvent.VK_ENTER : {			
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
				case KeyEvent.VK_UP:
			        // handle up
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
	
}