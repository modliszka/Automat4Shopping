package vocabulary;

public class Left implements Map_object{
	
	private static Left instance = null;
	protected Left(){}
	public static Left getInstance(){
		if (instance == null){
			instance = new Left();
			}
		return instance;
	}
	@Override
	public void method() {
		/*Movement.kierunek = 'l';
		if (Movement.ilosc!=0){
			Movement.getInstance().move_yourself(Movement.kierunek,Movement.ilosc);
			Movement.kierunek = 'n';Movement.ilosc = 0;
		}*/
		Map.rl=-1;
		Map.flag=true;
		if(Map.y==1) {if(Map.x[0]!='h') Map.x[1]='h';}
		else Map.x[0]='h';
		Map.y++;
	}


}
