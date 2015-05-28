package shop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import si.Pair;


public class Ruch extends Plansza implements ActionListener {
	private static final long serialVersionUID = 1L;
	public static int poz_k = ramy_wiedzy.Agent.getInstance().getLoc().pobierzWspolrzednaX();;//miejsce docelowe
	public static int pion_k = ramy_wiedzy.Agent.getInstance().getLoc().pobierzWspolrzednaY();
	private final int PRZERWA=30;
	public static Timer czas;
	ArrayList<Pair> points = new ArrayList<Pair>();
	public int krok=1;
	
	
	private static Ruch instance = null;
	public static Ruch getInstance(){
		if (instance == null){
			instance = new Ruch();
			}
		return instance;
	}
	public Ruch(){
		
		czas=new javax.swing.Timer(PRZERWA, this);
	}
	
	public void rusz_sie(){
		
		Ramka.blokada=true;
		czas.start();
		
	}

	public void rusz_sie(int a, int b){
		
		
		poz_k += a;
		pion_k += b;
		//System.out.println("ide do: " + poz_k +", " + pion_k);
		if(poz_k<0)poz_k=0;
		if(poz_k>9)poz_k=9;
		if(pion_k<0)pion_k=0;
		if(pion_k>9)pion_k=9;
		
		points.add(new Pair(poz_k,pion_k));
		rusz_sie();
		
		//System.out.println("ide do: " + poz_k +", " + pion_k);
		
		
		
	}

	
	public void actionPerformed(ActionEvent e){
		//System.out.println(points.toString());
		int pozk = points.get(0).getX();
		int piok = points.get(0).getY();
		if(pozk*60!=ramy_wiedzy.Agent.getInstance().getX() || piok*60!=ramy_wiedzy.Agent.getInstance().getY()){

			if(ramy_wiedzy.Agent.getInstance().getX()<pozk*60){ /*Ramka.poz+=3*/;ramy_wiedzy.Agent.getInstance().setX(ramy_wiedzy.Agent.getInstance().getX() + 3);Plansza.opcja=3;}
				else if(ramy_wiedzy.Agent.getInstance().getX()>pozk*60) {ramy_wiedzy.Agent.getInstance().setX(ramy_wiedzy.Agent.getInstance().getX() - 3);Plansza.opcja=2;}
					else if(ramy_wiedzy.Agent.getInstance().getY()>piok*60) {ramy_wiedzy.Agent.getInstance().setY(ramy_wiedzy.Agent.getInstance().getY() - 3);Plansza.opcja=4;}
						else if(ramy_wiedzy.Agent.getInstance().getY()<piok*60) {ramy_wiedzy.Agent.getInstance().setY(ramy_wiedzy.Agent.getInstance().getY() + 3);Plansza.opcja=1;}
			switch (krok) {
				case 1:Plansza.abc="b";krok++;break;
				case 2:Plansza.abc="a";krok++;break;
				case 3:Plansza.abc="b";krok++;break;
				case 4:Plansza.abc="c";krok++;break;
				case 5:Plansza.abc="b";krok=1;break;
			}
			
			//System.out.println("wsp " + ramy_wiedzy.Agent.getInstance().getX() + ", " + ramy_wiedzy.Agent.getInstance().getY());
			Ramka.plansza.repaint();
			
		}else{	
			
			points.remove(0);
			//System.out.println(points.toString());
			if(points.size()==0){
				Plansza.opcja=1;
				czas.stop();
				Ramka.plansza.repaint();
				Ramka.blokada=false;
			}
			
		}
	}
	
}

