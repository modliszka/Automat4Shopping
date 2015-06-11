package shop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import ProductInterface.ProductToList;
import frameSection.Product;


public class Movement extends Board implements ActionListener {
	private static final long serialVersionUID = 1L;
	public static int hor_k;//miejsce docelowe
	public static int vert_k;
	private final int INTERVAL=30;
	public static Timer time;
	public int step=1;
	
	private MainWindow mainWindow;
	
	public void setMainWindow(MainWindow mainWindow){
		this.mainWindow = mainWindow;
	}
	
	private static Movement instance = null;
	public static Movement getInstance(){
		if (instance == null){
			instance = new Movement();
			}
		return instance;
	}
	protected Movement(){}
	
	public void move_yourself(){
		time=new javax.swing.Timer(INTERVAL, this);
		MainWindow.blockade=true;
		time.start();
	}

	public void move_yourself(int a, int b){
		hor_k=MainWindow.hor/60+a;
		vert_k=MainWindow.vert/60+b;
		if(hor_k<0)hor_k=0;
		if(hor_k>9)hor_k=9;
		if(vert_k<0)vert_k=0;
		if(vert_k>9)vert_k=9;
		
		move_yourself();
		
		//System.out.println(hor_k+" "+vert_k);
		//mainWindow.productsList.append("asdasdasdas"); // dodaje tekst do listy
		if(MainWindow.board.shop[hor_k][vert_k] != null){
			boolean wasTitle=false;
			ArrayList<Product> list = new ArrayList<>();
			list = MainWindow.board.shop[hor_k][vert_k].productsList;
			String productKind = "";
			
			//pobieram kategorie produktu, na jaki trafilismy
			productKind = list.get(0).getProductKind().toLowerCase();
			System.out.println("znalezione "+productKind);
			
			//sprawdzamy czy już tu bylismy = mamy produkty na liście
			if(!productsInShop.containsAll(list)){
				for(Product p: list){
					if(!wasTitle) {
						wasTitle=true; 
						mainWindow.getProductsInShopList().append(productKind.toUpperCase()+"\n"); //p.getProductKind() = myProductList.name //toLowerCase
					}				
					productsInShop.add(p);
					String price = String.format("%.2f", p.getPrice());
					mainWindow.getProductsInShopList().append(p.getBrand()+" "+p.getAdditionalFeature()+" "+ price+"zł "+p.getIsGood()+"\n");
				}
				mainWindow.getProductsInShopList().append("\n");
			}

			//sprawdzam czy na liście jest produkt z listy zakupów
			mainWindow.productsInTrolley.setText("");
			for(ProductToList ptl: mainWindow.myProductsList){
				//System.out.println("na liscie " +ptl.name.toLowerCase());
				double maxPrice=0;
				ArrayList<Product> chosenProducts = new ArrayList<>();
				Product chosenProduct = new Product();
				
				// wybieramy produkty, ktore klient prawdopodobnie bedzie chcial
				for(Product p: productsInShop){
					if(p.getIsGood() && ptl.name.toLowerCase().equals(p.getProductKind())){	
						//System.out.println(p.getAdditionalFeature());
						chosenProducts.add(p);
					}
				}
				//szukamy pierwszego lepszego zgodnego z rodzajem i atrybutem produktu na liście zakupów
				for(Product p: chosenProducts){
					//System.out.println(p.getKind()+"="+ptl.describe+" "+p.getTaste()+"="+ptl.attribute);
					if(p.getKind().equals(ptl.describe) && p.getTaste().equals(ptl.attribute) && maxPrice < p.getPrice()){		
						maxPrice= p.getPrice();
						chosenProduct = p;
					}
				}
				//szukamy pierwszego lepszego zgodnego z rodzajem produktu na liście zakupów
				maxPrice=0;
				if(chosenProduct.getKind()==null){
					for(Product p: chosenProducts){
						//System.out.println(p.getKind()+"="+ptl.describe);
						if(p.getKind().equals(ptl.describe) && maxPrice<p.getPrice()){		//biorę najdroższy, no bo sklep musi zarobić, by utrzymać agenta :D
							maxPrice= p.getPrice();
							chosenProduct = p;
						}
					}
				}
				//szukamy pierwszego lepszego zgodnego z atrybutem produktu na liście zakupów (domyślnie smak)
				maxPrice=0;
				if(chosenProduct.getKind()==null){
					for(Product p: chosenProducts){
						//System.out.println(p.getTaste()+"="+ptl.attribute);
						if(p.getKind().equals(ptl.attribute) && maxPrice<p.getPrice()){		
							maxPrice= p.getPrice();
							chosenProduct = p;
						}
					}
				}
				//jesli nie znaleziono produktu z okreslonym rodzajem i atrybutem, to dajemy inny produkt
				maxPrice=0;
				if(chosenProduct.getKind()==null){
					for(Product p: chosenProducts){
						if(maxPrice<p.getPrice()){
							maxPrice= p.getPrice();
							chosenProduct = p;
						}
					}
				}
				//if(ifCleaningTrolleyIsNeeding)
				
				if(chosenProduct.getKind()!=null)
					mainWindow.productsInTrolley.append(chosenProduct.getBrand()+" "+chosenProduct.getAdditionalFeature()+" "+
							String.format("%.2f", chosenProduct.getPrice())+"zł\n");
			}
			
		}
		
	}
	
	public void doMoves() {
		//Movement.getInstance().move_yourself(0, -1); //poziom,pion (na minus to w górę)
		move_yourself(0, -1);//g
		move_yourself(-1, 0);//l
		for(int i=1;i<9;i++)
			move_yourself(0, -1); //gx8
		move_yourself(1, 0);	//p
		
	}

	
	public void actionPerformed(ActionEvent e){
		
		if(hor_k*60!=MainWindow.hor || vert_k*60!=MainWindow.vert){

			if(MainWindow.hor<hor_k*60){ MainWindow.hor+=3;Board.option=3;}
				else if(MainWindow.hor>hor_k*60) {MainWindow.hor-=3;Board.option=2;}
					else if(MainWindow.vert>vert_k*60) {MainWindow.vert-=3;Board.option=4;}
						else if(MainWindow.vert<vert_k*60) {MainWindow.vert+=3;Board.option=1;}
			switch (step) {
				case 1:Board.abc="b";step++;break;
				case 2:Board.abc="a";step++;break;
				case 3:Board.abc="b";step++;break;
				case 4:Board.abc="c";step++;break;
				case 5:Board.abc="b";step=1;break;
			}
			
			MainWindow.board.repaint();
			
		}else{			
			Board.option=1;
			time.stop();
			MainWindow.board.repaint();
			MainWindow.blockade=false;
		}
	}	
}

