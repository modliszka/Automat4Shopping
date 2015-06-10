package shop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

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
		
		System.out.println(hor_k+" "+vert_k);
		System.out.println(mainWindow.board.shop[0][6]);
		//mainWindow.productsList.append("asdasdasdas"); // dodaje tekst do listy
		if(MainWindow.board.shop[hor_k][vert_k] != null){
			System.out.println("nie jest pusto!");
			for(Product p: MainWindow.board.shop[hor_k][vert_k].productsList){
				mainWindow.productsList.append(p.getBrand()+" "+p.getKind());
			}
		}
		
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

