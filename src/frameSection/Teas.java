package frameSection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import DecisionTrees.TreeLearning;
import shop.Product;

public class Teas extends Products {
	//double weight;
	//Date expirationDate;
	private String tPackage, tTaste; 
	
	private String[] brands = {"lipton","tesco","tetley","herbapol","saga","dilmah","minutka","bigActive"};
	private String[] tastes = {"zwykly","pomarancza","pokrzywy","rumianek","malinowy","melisa","mieta"};
	private String[] kinds = {"czarna","zielona","biala","ziolowa"};
	private String[] packages = {"torebki","sypana","granulowana"};
	
	public Teas(){
		super();
		kind= tPackage= tTaste= brand="";
		amount=-1;
		price=-1;
	}	
	
	private List<Teas> teas;
	public List<Teas> getTeas(){ return teas;}
	
	public Teas(int t){
		//random 10 herbat
		teas = new ArrayList<Teas>();	
		TreeLearning treeT = null;
		try {
			treeT = new TreeLearning("teas");
			treeT.writeTree("teas");	
			treeT.treeTraining("teas");			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		Random r = new Random();
		
		for(int i=0; i<t; i++){
			Teas x = new Teas();
			int rAm = (2 + r.nextInt(18) )*10;
			double temp = Math.round(r.nextDouble()* 100.0) / 100.0;
			double rPr = 2.86 + 7*temp;
			
			String[] rABr = x.getAllBrands();
			String rBr = rABr[r.nextInt(rABr.length)];

			String[] rATa = x.getAllTastes();
			String rTa = rATa[r.nextInt(rATa.length)];			

			String[] rAKi = x.getAllKinds();
			String rKi = rAKi[r.nextInt(rAKi.length)];

			String[] rAPa = x.getAllPackages();
			String rPa = rAPa[r.nextInt(rAPa.length)];
			
			x.setAmount(rAm);
			x.setPrice(rPr);
			x.setBrand(rBr);
			x.setTaste(rTa);
			x.setKind(rKi);
			x.setPackage(rPa);
			
			//sprawdz czy klient bedzie chcial ten produkt
			String isGood="";
			try {
				isGood = treeT.checkTea(x);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(isGood=="tak") x.setIsGood(true);
			else x.setIsGood(false);
			
			teas.add(x);
		}		
	}
	
	public String[] getAllBrands() { return brands;}
	public String[] getAllTastes() { return tastes;}
	public String[] getAllKinds() { return kinds;}
	public String[] getAllPackages() { return packages;}
	
	public String getTaste() { return tTaste;}
	public String getPackage() { return tPackage;}
	
	public void setTaste(String x) { this.tTaste = x;}
	public void setPackage(String x) { this.tPackage = x;}
}
