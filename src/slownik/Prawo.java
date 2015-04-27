package slownik;


public class Prawo implements Obiekt_mapy{
	
	private static Prawo instance = null;
	protected Prawo(){}
	public static Prawo getInstance(){
		if (instance == null){
			instance = new Prawo();
			}
		return instance;
	}
	@Override
	public void metoda() {
		/*Ruch.kierunek = 'p';
		if (Ruch.ilosc!=0){
			Ruch.getInstance().rusz_sie(Ruch.kierunek,Ruch.ilosc);
			Ruch.kierunek = 'n';Ruch.ilosc = 0;
		}*/

		Mapa.flaga=true;
		Mapa.pl=1;
		if(Mapa.y==1) {if(Mapa.x[0]!='h') Mapa.x[1]='h';}
		else Mapa.x[0]='h';
		Mapa.y++;
}}
