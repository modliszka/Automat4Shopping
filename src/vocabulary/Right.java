package vocabulary;


public class Right implements Map_object{
	
	private static Right instance = null;
	protected Right(){}
	public static Right getInstance(){
		if (instance == null){
			instance = new Right();
			}
		return instance;
	}
	@Override
	public void method() {
		/*Movement.kierunek = 'p';
		if (Movement.ilosc!=0){
			Movement.getInstance().move_yourself(Movement.kierunek,Movement.ilosc);
			Movement.kierunek = 'n';Movement.ilosc = 0;
		}*/

		Map.flag=true;
		Map.rl=1;
		if(Map.y==1) {if(Map.x[0]!='h') Map.x[1]='h';}
		else Map.x[0]='h';
		Map.y++;
}}
