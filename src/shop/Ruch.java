package shop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;


public class Ruch extends Plansza implements ActionListener {
	private static final long serialVersionUID = 1L;
	public static int poz_k;//miejsce docelowe
	public static int pion_k;
	private final int PRZERWA=30;
	public static Timer czas;
	public int krok=1;
	
	
	private static Ruch instance = null;
	public static Ruch getInstance(){
		if (instance == null){
			instance = new Ruch();
			}
		return instance;
	}
	protected Ruch(){}
	
	public void rusz_sie(){
		czas=new javax.swing.Timer(PRZERWA, this);
		Ramka.blokada=true;
		czas.start();
	}

	public void rusz_sie(int a, int b){
		poz_k=Ramka.poz/60+a;
		pion_k=Ramka.pion/60+b;
		if(poz_k<0)poz_k=0;
		if(poz_k>9)poz_k=9;
		if(pion_k<0)pion_k=0;
		if(pion_k>9)pion_k=9;
		
		rusz_sie();
	}

	
	public void actionPerformed(ActionEvent e){
		
		if(poz_k*60!=Ramka.poz || pion_k*60!=Ramka.pion){

			if(Ramka.poz<poz_k*60){ Ramka.poz+=3;Plansza.opcja=3;}
				else if(Ramka.poz>poz_k*60) {Ramka.poz-=3;Plansza.opcja=2;}
					else if(Ramka.pion>pion_k*60) {Ramka.pion-=3;Plansza.opcja=4;}
						else if(Ramka.pion<pion_k*60) {Ramka.pion+=3;Plansza.opcja=1;}
			switch (krok) {
				case 1:Plansza.abc="b";krok++;break;
				case 2:Plansza.abc="a";krok++;break;
				case 3:Plansza.abc="b";krok++;break;
				case 4:Plansza.abc="c";krok++;break;
				case 5:Plansza.abc="b";krok=1;break;
			}
			
			Ramka.plansza.repaint();
			
		}else{			
			Plansza.opcja=1;
			czas.stop();
			Ramka.plansza.repaint();
			Ramka.blokada=false;
		}
	}	
}

