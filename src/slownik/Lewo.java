package slownik;

public class Lewo implements Obiekt_mapy{
	
	private static Lewo instance = null;
	protected Lewo(){}
	public static Lewo getInstance(){
		if (instance == null){
			instance = new Lewo();
			}
		return instance;
	}
	@Override
	public void metoda() {
		/*Ruch.kierunek = 'l';
		if (Ruch.ilosc!=0){
			Ruch.getInstance().rusz_sie(Ruch.kierunek,Ruch.ilosc);
			Ruch.kierunek = 'n';Ruch.ilosc = 0;
		}*/
		Mapa.pl=-1;
		Mapa.flaga=true;
		if(Mapa.y==1) {if(Mapa.x[0]!='h') Mapa.x[1]='h';}
		else Mapa.x[0]='h';
		Mapa.y++;
	}


}
