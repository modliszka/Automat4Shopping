package frameSection;

public class Yoghurts extends Product{
	
	private String[] brands = {"bakoma","danone","miliko","mlekovita","muller","tesco","zott"};
	private String[] kinds = {"owocowe","pitny","probiotyczny","kremowy","naturalny","dlaDzieci"};
	private String[] tastes = {"brak","truskawki_ziarna","sliwki_ziarna","jablka_pomarancze","truskawki","maliny","wisnie","kiwi"};
	
	public Yoghurts(){
		brand="";
		taste="";
		kind="";
		amount=price=-1; 
		
		setProductKind("jogurt");
	}
	
	public String[] getAllBrands() { return brands;}
	public String[] getAllKinds() { return kinds;}
	public String[] getAllTastes() { return tastes;}

}