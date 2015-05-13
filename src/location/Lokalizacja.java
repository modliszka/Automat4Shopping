package location;

public class Location {
	
	private int x;
	private int y;
	
	// pocz¹tkowe po³o¿enie w sklepie przypisane w konstruktorze
	public Location(){x=0; y=0;} 
	
	// po przemieszczeniu podanie nowych wspó³rzêdnych do lokalizacji agenta
	public void updateLocation(int new_x,int new_y)
	{ 
	   this.x = new_x;
	   this.y = new_y;
	}

	// pobieranie pozycji
    public int downloadCoordinateX(){return x;}
    public int downloadCoordinateY(){return y;}

}
