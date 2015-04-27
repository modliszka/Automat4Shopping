package slownik;


public class Gora implements Obiekt_mapy{
	
	private static Gora instance = null;
	protected Gora(){}
	public static Gora getInstance(){
		if (instance == null){
			instance = new Gora();
			}
		return instance;
	}
	@Override
	public void metoda() {
		/*Ruch.kierunek = 'g';
		if (Ruch.ilosc!=0){
			Ruch.getInstance().rusz_sie(Ruch.kierunek,Ruch.ilosc);
			Ruch.kierunek = 'n';Ruch.ilosc = 0;
		}*/
		Mapa.gd=-1;
		Mapa.flaga=true;
		if(Mapa.y==1) {if(Mapa.x[0]!='v') Mapa.x[1]='v';}
		else Mapa.x[0]='v';
		Mapa.y++;
	}


}
