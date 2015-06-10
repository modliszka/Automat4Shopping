package shop;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import frameSection.Product;
import ProductInterface.ProductToList;
import vocabulary.Map;

public class MainWindow extends JFrame implements KeyListener, ActionListener{
	public static MainWindow instance;
	private static final long serialVersionUID = 1L;
	public static int hor=60,vert=540; //pozycja agenta
	public static Board board;
	public static JTextArea productsShoppingList;
	private static JTextArea productsInShopList;
	public static JTextArea productsInTrolley;
	public static JLabel productsShoppingListTitle, productsInShopListTitle, productsInTrolleyTitle;
	protected static JButton doProductListButton;
	protected static JButton clearShoppingListButton;
	protected static JTextField command;
	public static boolean blockade=false;
	public static Map custom_map = new Map();
	
	public static  List<ProductToList> myProductsList;
	public static  List<Product> myShoppingTrolleyList;
	
	public MainWindow(){
		super("Shop");
		instance=this;
		myProductsList = new ArrayList<ProductToList>();

		setSize(1024, 750); 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		
		addKeyListener(this);
		setFocusable(true);
		//requestFocus();

		//Plansza
		board=new Board();
		board.setBounds(5,5,601,601);		
		add(board);		
				
		//przycisk do okna z tworzeniem listy zakupow
		doProductListButton = new JButton("Dodaj produkt do listy zakupów...");
		//doProductListButton.setBounds(60, 400, 220, 30);
		doProductListButton.setBounds(675, 5, 240, 30);
		doProductListButton.setVerticalTextPosition(AbstractButton.CENTER);
		doProductListButton.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
		doProductListButton.setEnabled(true);
		doProductListButton.setVisible(true);
		doProductListButton.setFocusable(false);
		
		doProductListButton.addActionListener(this);
		/*(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddProductWindow.function();				
			}
		});*/
		add(doProductListButton);		
		
		clearShoppingListButton = new JButton("Wyczyść listę zakupów");
		clearShoppingListButton.setBounds(675, 40, 240, 30);
		clearShoppingListButton.setVerticalTextPosition(AbstractButton.CENTER);
		clearShoppingListButton.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
		clearShoppingListButton.setEnabled(true);
		clearShoppingListButton.setVisible(true);
		clearShoppingListButton.setFocusable(false);
		
		clearShoppingListButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				productsShoppingList.setText("");
				myProductsList.clear();
				
			}
		});
		add(clearShoppingListButton);	

		productsShoppingListTitle=new JLabel("Lista zakupów:");
		productsShoppingListTitle.setBounds(650, 100, 150, 30);
		getContentPane().add(productsShoppingListTitle);
		
		//Lista produktow po prawej stronie
		productsShoppingList=new JTextArea();
		productsShoppingList.setFocusable(false);
		productsShoppingList.setOpaque(false);//przezr.tlo
		productsShoppingList.setBounds(650, 135, 150, 150);
		//productsList.setLineWrap(true);
		//productsList.setWrapStyleWord(true);
		productsShoppingList.setEnabled(true);
		productsShoppingList.setVisible(true);
		//frmProdhelper.getContentPane().add(textArea);
		getContentPane().add(productsShoppingList);
		//productsList.setText("yufutfty");
		
		JScrollPane areaScrollPL = new JScrollPane(productsShoppingList);
		areaScrollPL.setBounds(650, 135, 150, 150);
		getContentPane().add(areaScrollPL);
		
		
		//Lista produktow w koszyku
		productsInTrolleyTitle=new JLabel("Lista produktów w koszyku:");
		productsInTrolleyTitle.setBounds(830, 100, 200, 30);
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
		
		productsInShopListTitle = new JLabel("Lista produktów w sklepie:");
		productsInShopListTitle.setBounds(650, 300, 300, 30);
		getContentPane().add(productsInShopListTitle);		
		
		setProductsInShopList(new JTextArea());
		getProductsInShopList().setFocusable(false);
		getProductsInShopList().setOpaque(false);//przezr.tlo
		getProductsInShopList().setEnabled(true);
		getProductsInShopList().setVisible(true);
		getContentPane().add(getProductsInShopList());		
		
		//dodaj scrolla do listy dostępnych produktów
		JScrollPane areaScrollPane = new JScrollPane(getProductsInShopList());
		areaScrollPane.setBounds(650, 335, 300, 250);
		getContentPane().add(areaScrollPane);
		
		
		//pole do poleceń dot. chodzenia
		command=new JTextField();
		command.setFocusable(true);
		command.setBounds(5, 670, 600, 20);
		command.addKeyListener(this); 
		add(command);

		setVisible(true);
		//command.requestFocusInWindow(); 
		
		
		Movement.getInstance().setMainWindow(this);
	}
	
	private void addProductsToBoard() {
		board.shop[0][1]=new Place("R","chocolates","candies");
		board.shop[0][6]=new Place("R","juices","beverages");
		board.shop[3][3]=new Place("R","teas","teas");
		board.shop[6][5]=new Place("R","pastas","dry food");
		board.shop[6][4]=new Place("R","waters","beverages");
		board.shop[6][6]=new Place("R","yoghurts","dairy");
		
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//System.out.println("otwieram okno..");
		new AddProductWindow();		
	}

	public static JTextArea getProductsInShopList() {
		return productsInShopList;
	}

	public static void setProductsInShopList(JTextArea productsInShopList) {
		MainWindow.productsInShopList = productsInShopList;
	}
	
}