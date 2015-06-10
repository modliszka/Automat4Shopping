package shop;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import vocabulary.Map;

public class MainWindow extends JFrame implements KeyListener{
	public static MainWindow instance;
	private static final long serialVersionUID = 1L;
	public static int hor=300,vert=300;
	public static Board board;
	public static JTextArea productsList,productsListTitle, productsInShopList, productsInShopListTitle, productsInTrolley, productsInTrolleyTitle;
	protected static JButton doProductListButton;
	protected static JButton clearProductListButton;
	protected static JTextField command;
	public static boolean blockade=false;
	public static Map custom_map = new Map();
	//private static AddProductWindow addProductWindow;
	
	public MainWindow(){
		super("Shop");
		instance=this;

		setSize(1024, 750); 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);

		//Plansza
		board=new Board();
		board.setBounds(5,5,601,601);		
		add(board);		
				
		//przycisk do okna z tworzeniem listy zakupow
		doProductListButton = new JButton("Dodaj produkty do listy zakupów...");
		//doProductListButton.setBounds(60, 400, 220, 30);
		doProductListButton.setBounds(675, 5, 240, 30);
		doProductListButton.setVerticalTextPosition(AbstractButton.CENTER);
		doProductListButton.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
		doProductListButton.setEnabled(true);
		doProductListButton.setVisible(true);
		//doProductListButton.setLocation(700, 100);
		
		doProductListButton.addActionListener(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ProductHelper.function();				
			}
		});
		add(doProductListButton);		
		
		clearProductListButton = new JButton("Wyczyść listę produktów");
		clearProductListButton.setBounds(675, 40, 240, 30);
		clearProductListButton.setVerticalTextPosition(AbstractButton.CENTER);
		clearProductListButton.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
		clearProductListButton.setEnabled(true);
		clearProductListButton.setVisible(true);
		//clearProductListButton.setLocation(700, 200);
		
		clearProductListButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				productsList.setText("");
				Shop.myProductsList.clear();
				
			}
		});
		add(clearProductListButton);	

		productsListTitle=new JTextArea("Lista zakupów:");
		productsListTitle.setBounds(650, 100, 150, 30);
		productsListTitle.setFocusable(false);
		productsListTitle.setOpaque(false);//przezr.tlo
		productsListTitle.setEnabled(true);
		productsListTitle.setVisible(true);
		getContentPane().add(productsListTitle);
		
		//Lista produktow po prawej stronie
		productsList=new JTextArea();
		productsList.setFocusable(false);
		productsList.setOpaque(false);//przezr.tlo
//<<<<<<< HEAD
//		
//		//przycisk do okna z tworzeniem listy zakupow
//		doProductListButton = new JButton("Dodaj produkty do listy");
//		doProductListButton.setBounds(615, 670, 170, 20);
//		doProductListButton.setVerticalTextPosition(AbstractButton.CENTER);
//		doProductListButton.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
//		doProductListButton.setEnabled(true);
//		doProductListButton.addActionListener(this);
//		add(doProductListButton);
//=======
		productsList.setBounds(650, 135, 150, 150);
		//productsList.setLineWrap(true);
		//productsList.setWrapStyleWord(true);
		productsList.setEnabled(true);
		productsList.setVisible(true);
		//frmProdhelper.getContentPane().add(textArea);
		getContentPane().add(productsList);
		//productsList.setText("yufutfty");
		
		JScrollPane areaScrollPL = new JScrollPane(productsList);
		areaScrollPL.setBounds(650, 135, 150, 150);
		getContentPane().add(areaScrollPL);
		
		
		//Lista produktow w koszyku
		productsInTrolleyTitle=new JTextArea("Lista produktów w koszyku:");
		productsInTrolleyTitle.setBounds(830, 100, 150, 30);
		productsInTrolleyTitle.setFocusable(false);
		productsInTrolleyTitle.setOpaque(false);//przezr.tlo
		productsInTrolleyTitle.setEnabled(true);
		productsInTrolleyTitle.setVisible(true);
		getContentPane().add(productsInTrolleyTitle);
		
		productsInTrolley=new JTextArea();
		productsInTrolley.setFocusable(false);
		productsInTrolley.setOpaque(false);//przezr.tlo
		productsInTrolley.setEnabled(true);
		productsInTrolley.setVisible(true);
		getContentPane().add(productsInTrolley);
		
		JScrollPane areaScrollPK = new JScrollPane(productsInTrolley);
		areaScrollPK.setBounds(830, 135, 150, 150);
		getContentPane().add(areaScrollPK);

		//Utwórz instancje produktów ze sklepu i dodaj je do planszy
		addProductsToBoard();
		
		productsInShopListTitle = new JTextArea("Lista produktów w sklepie:");
		productsInShopListTitle.setBounds(650, 300, 300, 30);
		productsInShopListTitle.setFocusable(false);
		productsInShopListTitle.setOpaque(false);//przezr.tlo
		productsInShopListTitle.setEnabled(true);
		productsInShopListTitle.setVisible(true);
		getContentPane().add(productsInShopListTitle);
		
		
		productsInShopList = new JTextArea();
		productsInShopList.setFocusable(false);
		productsInShopList.setOpaque(false);//przezr.tlo
		productsInShopList.setEnabled(true);
		productsInShopList.setVisible(true);
		getContentPane().add(productsInShopList);		
		
		//dodaj scrolla do listy dostępnych produktów
		JScrollPane areaScrollPane = new JScrollPane(productsInShopList);
		areaScrollPane.setBounds(650, 335, 300, 250);
		getContentPane().add(areaScrollPane);
		
//>>>>>>> origin/english
		
		//pole do poleceń dot. chodzenia
		command=new JTextField();
		command.setFocusable(true);
		command.setBounds(5, 670, 600, 20);
		command.addKeyListener(this); 
		add(command);

		setVisible(true);
		command.requestFocusInWindow(); 
		
		
		Movement.getInstance().setMainWindow(this);
	}
	
	private void addProductsToBoard() {
		board.shop[0][1]=new Place("R","chocolates","candies");
		board.shop[3][5]=new Place("R","teas","teas");
		board.shop[0][6]=new Place("R","juices","beverages");
		
		/*for(Product p: Board.shop[0][6].productsList){
			productsList.append(p.getBrand()+" "+p.getKind());			
			
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
		}*/
	}
	
	public static void updateProductList(String name, int amount, String characteristics){
		String x="";
		if(amount>0){
			if(amount>1)x=" x"+amount;
			productsList.setText(productsList.getText()+"\n"+name+x);
		}
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

//	@Override
//	public void actionPerformed(ActionEvent arg0) {
//		// TODO Auto-generated method stub
//		new AddProductWindow();
//	}
	
}