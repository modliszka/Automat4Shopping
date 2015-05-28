package ramy_wiedzy;

public class Map {
	Boolean[][] map = new Boolean[10][10];
	private static Map instance;
	public static Map getInstance(){
		if(instance==null){
			instance=new Map();
		}
		return instance;
	}
	
	public Map(){
		for(int i=0;i<map.length;i++){
			for(int j =0;j<map[i].length;j++){
				map[i][j]=false;
			}
		}
		for(int i=1;i<map.length-1;i++){
			for(int j =1;j<map[i].length-1;j++){
				map[i][j]=true;
			}
		}
		map[1][9] = true;
		map[8][9] = true;
		map[3][3] = false;
		map[3][4] = false;
		map[6][3] = false;
		map[6][4] = false;
		map[6][5] = false;
		map[6][6] = false;
		map[5][6] = false;
		map[7][6] = false;
		
		
	}
	
	public boolean isFree(int x, int y){
		//System.out.println(x +" " + y);
		return map[x][y];
	}
	
}
