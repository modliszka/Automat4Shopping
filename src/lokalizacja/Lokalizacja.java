package lokalizacja;

public class Lokalizacja {
	
	private int x;
	private int y;
	
	// pocz¹tkowe po³o¿enie w sklepie przypisane w konstruktorze
	public Lokalizacja(){x=0; y=0;} 
	
	// po przemieszczeniu podanie nowych wspó³rzêdnych do lokalizacji agenta
	public void aktualizujLokalizacje(int nowe_x,int nowe_y)
	{ 
	   this.x = nowe_x;
	   this.y = nowe_y;
	}

	// pobieranie pozycji
    public int pobierzWspolrzednaX(){return x;}
    public int pobierzWspolrzednaY(){return y;}

}
