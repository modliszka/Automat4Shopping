package searchingShelf;

import java.util.Stack;

public class Search {
	int[][] boxes=new int[4][20];
	static int szukanyTowar;
	static float costMoveDown=0.3f;
	static float costMoveSide=0.4f;
	static float costTake=0.5f;
	Stack<Change> stos;

	
	
	float move(int from,int to){
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
	
	//heurystyka
	float h_move(int from){
		int i=0;
		int j=0;
		while(boxes[from][j]!=szukanyTowar){
		//	System.out.print(j+" ");
			j++;
		}
	//	System.out.print("|");
		while(boxes[from][j+i]!=-1){
	//		System.out.print(i+" ");
			i++;
		}
	//	System.out.println("");
		return 3.0f*costMoveSide*i;
	}
	
	void re_move(int from,int to){
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
	
	float take(int from){
		int i=0;
		while(boxes[from][i+1]!=-1){
			i++;
		}
		boxes[from][i]=-1;
		return costTake;
	}
	
	void re_take(int from){
		int i=0;
		while(boxes[from][i]!=-1){
			i++;
		}
		boxes[from][i]=szukanyTowar;
		boxes[from][i+1]=-1;
	}
	
	void start(int[][] boxes){
		//Start.arrayCopy(boxes, this.boxes);
		this.boxes=Start.cloneArray(boxes);
		//Start.fringe.offer(new Node(Search.boxes,0,100,new Stack<Change>()));
		succ();
	}
	static void show(int[][] boxes){
		
		int i=0;
		int j;
		while(true){
			System.out.print(i+1+":");
			j=0;
			while( boxes[i][j]!=-1){
				System.out.print(boxes[i][j]+" ");
				++j;
			}
			System.out.println("");
			if(i==3&&boxes[i][j]==-1)break;
			else i++;
		}
		System.out.println("");
	}

	void succ(){
		this.stos=new Stack<Change>();
			//float costNew=10000000000000000f;
		while(true){	
			int i=0;
			int j;
			while(true){
				j=0;
				while( (boxes[i][j]!=-1) && (boxes[i][j]!=szukanyTowar) ){
					++j;
				}
				if(boxes[i][j]==szukanyTowar)
				break;
				else i++;
			}
			
			if(Start.fringe.isEmpty()){
				Start.fringe.offer(new Node(boxes,0,h_move(i),this.stos));
			}
			
			Node node=Start.fringe.poll();
			boxes=Start.cloneArray(node.boxes);
			this.stos=node.st;
			
			//show(boxes);
			
			i=0;
			while(true){
				j=0;
				while( (boxes[i][j]!=-1) && (boxes[i][j]!=szukanyTowar) ){
					++j;
				}
				if(boxes[i][j]==szukanyTowar)
				break;
				else i++;
			}
			
			if(boxes[i][j+1]==-1){
				Change ch=new Change(szukanyTowar,'t',i,-1);
				this.stos.push(ch);
				Start.stos=this.stos;
				System.out.println("-------");
				break;
			//	return stos;//
			}else{
				int k=0;
		//		int where=-1;
				//Stack<Change> stos2=null;
				//float c=100000000000f;
				while(k<4){
					if(k!=i){
						//if(stos.empty()){
							//costNew=move(i,k)+cost;
							//stos=succ(costNew);
							//re_move(i, k);
							//where=k;
							int l=j;
							while(boxes[i][l+1]!=-1)++l;
							this.stos.push(new Change(boxes[i][l],'m',i,k));
							float costM=move(i,k);
							Start.fringe.offer(new Node(boxes,node.cost+costM,h_move(i),this.stos));
							re_move(i,k);
							this.stos.pop();
							//od 14:30
//						}else{
//							c=move(i,k)+cost;
//							stos2=succ(c);
//							re_move(i,k);
//						}
//						if(c<costNew){
//							costNew=c;
//							stos=stos2;
//							where=k;
//						}
					}
					k++;
				}
				//cost = costNew;
//				int l=j;
//				while(boxes[i][l+1]!=-1)++l;
//				Change ch=new Change(boxes[i][l],'m',i,where);
//				stos.push(ch);
//				
			//	return stos;
			}
		}
	}
	
}
