package slownik;


public class Dol implements Obiekt_mapy{
	
	private static Dol instance = null;
	public static Dol getInstance(){
		if (instance == null){
			instance = new Dol();
			}
		return instance;
	}
	protected Dol(){}
	@Override
	public void metoda() {
	/*	Movement.kierunek = 'd';
		if (Movement.ilosc!=0){
			Movement.getInstance().move_yourself(Movement.kierunek,Movement.ilosc);
			Movement.kierunek = 'n';Movement.ilosc = 0;
		}*/
		Mapa.gd=1;
		Mapa.flaga=true;
		if(Mapa.y==1) {if(Mapa.x[0]!='v') Mapa.x[1]='v';}
		else Mapa.x[0]='v';
		Mapa.y++;
		
	}


}
