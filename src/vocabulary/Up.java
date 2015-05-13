package vocabulary;


public class Up implements Map_object{
	
	private static Up instance = null;
	protected Up(){}
	public static Up getInstance(){
		if (instance == null){
			instance = new Up();
			}
		return instance;
	}
	@Override
	public void method() {
		/*Movement.kierunek = 'g';
		if (Movement.ilosc!=0){
			Movement.getInstance().move_yourself(Movement.kierunek,Movement.ilosc);
			Movement.kierunek = 'n';Movement.ilosc = 0;
		}*/
		Map.ud=-1;
		Map.flag=true;
		if(Map.y==1) {if(Map.x[0]!='v') Map.x[1]='v';}
		else Map.x[0]='v';
		Map.y++;
	}


}
