package shop;

import java.util.ArrayList;

public class Place {
	
	boolean problem;
	ArrayList<Product> list;
	
	protected Place(String shelfSide, String product, String productKind){
		switch (shelfSide) {
			case "L"://left
				problem=false;
				break;
			case "R"://right
				problem=false;
				break;
			case "T"://top
				problem=false;
				break;
			case "B"://bottom
				problem=false;
				break;
			case "A"://all
				problem=false;
				break;
			default:
				problem=true;
				break;
		}
		
		if(problem){
			System.out.println("B³êdny kierunek pó³ki");
		}else{
			list= new ArrayList<Product>();
		}
	}
	
	public void add(Product p){
		if(!problem){
			list.add(p);
		}else{
			System.out.println("Nie ma pó³ki");
		}
	}
}
