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
	/*	Ruch.kierunek = 'd';
		if (Ruch.ilosc!=0){
			Ruch.getInstance().rusz_sie(Ruch.kierunek,Ruch.ilosc);
			Ruch.kierunek = 'n';Ruch.ilosc = 0;
		}*/
		Mapa.gd=1;
		Mapa.flaga=true;
		if(Mapa.y==1) {if(Mapa.x[0]!='v') Mapa.x[1]='v';}
		else Mapa.x[0]='v';
		Mapa.y++;
		
	}


}
