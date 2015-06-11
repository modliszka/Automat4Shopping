package searchingShelf;

import java.util.Stack;

public class Search {
	static int[][] boxes=new int[4][20];
	static int szukanyTowar;
	static float costMoveDown=0.3f;
	static float costMoveSide=0.4f;
	static float costTake=0.5f;

	
	
	static float move(int from,int to){
		int i=0;
		while(boxes[from][i+1]!=-1){
			i++;
		}

		int j=0;
		while(boxes[to][j]!=-1){
			j++;
		}
		
		boxes[to][j+1]=-1;
		boxes[to][j]=boxes[from][i];
		boxes[from][i]=-1;		
		
		return ((float)Math.abs(from-to))*costMoveSide + costMoveDown*j;
	}
	
	static void re_move(int from,int to){
		int i=0;
		while(boxes[from][i]!=-1){
			i++;
		}

		int j=0;
		while(boxes[to][j+1]!=-1){
			j++;
		}
		
		boxes[from][i]=boxes[to][j];
		boxes[to][j]=-1;
		boxes[from][i+1]=-1;
	}
	
	static float take(int from){
		int i=0;
		while(boxes[from][i+1]!=-1){
			i++;
		}
		boxes[from][i]=-1;
		return costTake;
	}
	
	static void re_take(int from){
		int i=0;
		while(boxes[from][i]!=-1){
			i++;
		}
		boxes[from][i]=szukanyTowar;
		boxes[from][i+1]=-1;
	}
	
	static Stack<Change> start(){
		float cost=0;
		Stack<Change> stos=succ(cost);
		return stos;
	}
	
	static Stack<Change> succ(float cost){
	
		Stack<Change> stos=new Stack<Change>();
		float costNew=10000000000000000f;
		int i=0;
		int j;
		while(true){
			j=0;
			while( (boxes[i][j]!=-1) && (boxes[i][j]!=szukanyTowar) ){
				//System.out.println(boxes[i][j]);
				++j;
			}
			if(boxes[i][j]==szukanyTowar)
			break;
			else i++;
		}
		if(boxes[i][j+1]==-1){
			Change ch=new Change(szukanyTowar,'t',i,-1);
			stos.push(ch);
			
			return stos;
		}else{
			int k=0;
			int where=-1;
			Stack<Change> stos2=null;
			float c=100000000000f;
			while(k<4){
				if(k!=i){
					if(stos.empty()){
						costNew=move(i,k)+cost;
						stos=succ(costNew);
						re_move(i, k);
						where=k;
					}else{
						c=move(i,k)+cost;
						stos2=succ(c);
						re_move(i,k);
					}
					if(c<costNew){
						costNew=c;
						stos=stos2;
						where=k;
					}
				}
				k++;
			}
			cost = costNew;
			int l=j;
			while(boxes[i][l+1]!=-1)++l;
			Change ch=new Change(boxes[i][l],'m',i,where);
			stos.push(ch);
			
			return stos;
		}
	}
	
}
