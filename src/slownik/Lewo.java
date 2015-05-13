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
		/*Movement.kierunek = 'l';
		if (Movement.ilosc!=0){
			Movement.getInstance().move_yourself(Movement.kierunek,Movement.ilosc);
			Movement.kierunek = 'n';Movement.ilosc = 0;
		}*/
		Mapa.pl=-1;
		Mapa.flaga=true;
		if(Mapa.y==1) {if(Mapa.x[0]!='h') Mapa.x[1]='h';}
		else Mapa.x[0]='h';
		Mapa.y++;
	}


}
