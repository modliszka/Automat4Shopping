package shop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;


public class Movement extends Board implements ActionListener {
	private static final long serialVersionUID = 1L;
	public static int hor_k;//miejsce docelowe
	public static int vert_k;
	private final int INTERVAL=30;
	public static Timer time;
	public int step=1;
	
	
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
		Custom_frame.blockade=true;
		time.start();
	}

	public void move_yourself(int a, int b){
		hor_k=Custom_frame.hor/60+a;
		vert_k=Custom_frame.vert/60+b;
		if(hor_k<0)hor_k=0;
		if(hor_k>9)hor_k=9;
		if(vert_k<0)vert_k=0;
		if(vert_k>9)vert_k=9;
		
		move_yourself();
	}

	
	public void actionPerformed(ActionEvent e){
		
		if(hor_k*60!=Custom_frame.hor || vert_k*60!=Custom_frame.vert){

			if(Custom_frame.hor<hor_k*60){ Custom_frame.hor+=3;Board.option=3;}
				else if(Custom_frame.hor>hor_k*60) {Custom_frame.hor-=3;Board.option=2;}
					else if(Custom_frame.vert>vert_k*60) {Custom_frame.vert-=3;Board.option=4;}
						else if(Custom_frame.vert<vert_k*60) {Custom_frame.vert+=3;Board.option=1;}
			switch (step) {
				case 1:Board.abc="b";step++;break;
				case 2:Board.abc="a";step++;break;
				case 3:Board.abc="b";step++;break;
				case 4:Board.abc="c";step++;break;
				case 5:Board.abc="b";step=1;break;
			}
			
			Custom_frame.board.repaint();
			
		}else{			
			Board.option=1;
			time.stop();
			Custom_frame.board.repaint();
			Custom_frame.blockade=false;
		}
	}	
}

