package vocabulary;


public class Down implements Map_object{
	
	private static Down instance = null;
	public static Down getInstance(){
		if (instance == null){
			instance = new Down();
			}
		return instance;
	}
	protected Down(){}
	@Override
	public void method() {
	/*	Movement.kierunek = 'd';
		if (Movement.ilosc!=0){
			Movement.getInstance().move_yourself(Movement.kierunek,Movement.ilosc);
			Movement.kierunek = 'n';Movement.ilosc = 0;
		}*/
		Map.ud=1;
		Map.flag=true;
		if(Map.y==1) {if(Map.x[0]!='v') Map.x[1]='v';}
		else Map.x[0]='v';
		Map.y++;
		
	}


}
