package ramy_wiedzy;
import shop.Ruch;
import  lokalizacja.Lokalizacja;;

public class Agent {
	double gotówka;
	double ladownosc;
	Lokalizacja lokalizacja;
	int x;
	int y;
	
	private static Agent instance = null;
	public static Agent getInstance(){
		if (instance == null){
			instance = new Agent();
			}
		return instance;
	}
	public Agent(){
		lokalizacja = new Lokalizacja();
		lokalizacja.aktualizujLokalizacje(1,9);
		x=getLoc().pobierzWspolrzednaX() * 60;
		y=getLoc().pobierzWspolrzednaY() * 60;
	}
	public Lokalizacja getLoc(){
		return this.lokalizacja;
	}
	
	public void setLoc(int x,int y){
		lokalizacja.aktualizujLokalizacje(x, y);
		this.x = x * 60;
		this.y = y * 60;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	
	

}
