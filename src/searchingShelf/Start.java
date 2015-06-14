package searchingShelf;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

public class Start {
	static String f="baza1.txt";
	public static PriorityQueue<Node> fringe=null;
	public static int[][] boxes=new int[4][20];
	public static int[][] boxes_start;
	static Stack<Change> stos=new Stack<Change>();
	
	public Start(int a) throws FileNotFoundException{
		 File file = new File(f);
	      Scanner in = new Scanner(file);
	 
	      Search.szukanyTowar = Integer.parseInt( in.nextLine());
	      Search.szukanyTowar = a;
	      System.out.println();
	      System.out.println("Szukamy nr: "+Search.szukanyTowar+"\n");
	      int n;
	      for(int j=0;j<4;++j){
	    	  int i;
	    	  n=Integer.parseInt( in.nextLine());
		      for(i=0;i<n;++i){
		    	  boxes[j][i]=Integer.parseInt( in.nextLine());
		    //	  System.out.println(i+">>"+Search.boxes[j][i]);
		      }
		      boxes[j][i]=-1;
		    //  System.out.println("////");
		    //  System.out.println(i+">>"+Search.boxes[j][i]);
	      }
	      
	    //  System.out.println();
	      
	      in.close();
	      //arrayCopy(boxes, boxes_start);
	      boxes_start=cloneArray(boxes);
	      
	      Comparator<Node> comparator = new NodeComparator();
	      fringe= new PriorityQueue<Node>(100,comparator);
	    //  fringe.offer(new Node(Search.boxes,0,new Stack<Change>()));
	      Search s=new Search();
	      s.start(boxes);
	      
	      Stack<Change> stos2=new Stack<Change>();
	      while(!stos.empty())stos2.push(stos.pop());
	      
	      //System.out.println(fringe.size());
	      while(!stos2.empty()){
	    	  Change ch=stos2.pop();
	    	  if(ch.operation=='m'){
	    		  show();
	    		  System.out.println("Przemieœæ: "+ch.box+" ze stosu "+(ch.from+1)+" na stos "+(ch.to+1));
	    		  move(ch.from, ch.to);
	    	  }
	    	  if(ch.operation=='t'){
	    		  show();
	    		  System.out.println("Zabierz: "+ch.box+" ze stosu "+(ch.from+1));
	    		  take(ch.from);
	    	  }
	    	  
	      }
	      show();
	      System.out.println("Koniec");
	}
	
	public static int[][] cloneArray(int[][] src) {
	    int length = src.length;
	    int[][] target = new int[length][src[0].length];
	    for (int i = 0; i < length; i++) {
	        System.arraycopy(src[i], 0, target[i], 0, src[i].length);
	    }
	    return target;
	}
	
	void move(int from,int to){
		int i=0;
		while(boxes_start[from][i+1]!=-1){
			i++;
		}

		int j=0;
		while(boxes_start[to][j]!=-1){
			j++;
		}
		
		boxes_start[to][j+1]=-1;
		boxes_start[to][j]=boxes_start[from][i];
		boxes_start[from][i]=-1;		
		
	}
	
	void take(int from){
		int i=0;
		while(boxes_start[from][i+1]!=-1){
			i++;
		}
		boxes_start[from][i]=-1;
	}
	
	static void show(){
		
		int i=0;
		int j;
		while(true){
			System.out.print(i+1+":");
			j=0;
			while( boxes_start[i][j]!=-1){
				System.out.print(boxes_start[i][j]+" ");
				++j;
			}
			System.out.println("");
			if(i==3&&boxes_start[i][j]==-1)break;
			else i++;
		}
		System.out.println("");
	}
}
