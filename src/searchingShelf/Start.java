package searchingShelf;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Start {
	static String f="baza1.txt";
	public static void main(String[] args) throws FileNotFoundException{
	      File file = new File(f);
	      Scanner in = new Scanner(file);
	 
	      Search.szukanyTowar = Integer.parseInt( in.nextLine());
	      int n;
	      for(int j=0;j<4;++j){
	    	  int i;
	    	  n=Integer.parseInt( in.nextLine());
		      for(i=0;i<n;++i){
		    	  Search.boxes[j][i]=Integer.parseInt( in.nextLine());
		    //	  System.out.println(i+">>"+Search.boxes[j][i]);
		      }
		      Search.boxes[j][i]=-1;
		    //  System.out.println("////");
		    //  System.out.println(i+">>"+Search.boxes[j][i]);
	      }
	      
	      System.out.println();
	      
	      in.close();
	      
	      Stack<Change> stos=Search.start();
	      while(!stos.empty()){
	    	  Change ch=stos.pop();
	    	  if(ch.operation=='m'){
	    		  show();
	    		  System.out.println("Przemieœæ: "+ch.box+" ze stosu "+(ch.from+1)+" na stos "+(ch.to+1));
	    		  Search.move(ch.from, ch.to);
	    	  }
	    	  if(ch.operation=='t'){
	    		  show();
	    		  System.out.println("Zabierz: "+ch.box+" ze stosu "+(ch.from+1));
	    		  Search.take(ch.from);
	    	  }
	    	  
	      }
	      show();
	      System.out.println("Koniec");
	  }
	static void show(){
		
		int i=0;
		int j;
		while(true){
			System.out.print(i+1+":");
			j=0;
			while( Search.boxes[i][j]!=-1){
				System.out.print(Search.boxes[i][j]+" ");
				++j;
			}
			System.out.println("");
			if(i==3&&Search.boxes[i][j]==-1)break;
			else i++;
		}
		System.out.println("");
	}
}
