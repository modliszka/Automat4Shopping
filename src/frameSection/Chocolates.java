package frameSection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import DecisionTrees.TreeLearning;

public class Chocolates extends Products {
	private String taste, additive;
	private double fats,carbohydrates,protein,roughage;
	private int cocoaContent,energy;

	private String[] brands = {"alpenGold","wedel","goplana","heidi","milka","terravita","tesco","wawel"};
	private String[] tastes = {"mleczna","gorzka","biala"};
	private String[] kinds = {"zwykla","babelkowa","nadziewana","zowocami"};
	private String[] additives = {"brak","karmelowa","orzechowa","wisniowa","malinowa","truskawkowa","fistaszki","kokosowym","pomaranczowa","jogurtowa","tiramisu","kasztanki","michalki","wisiniaChili"};
	
	public Chocolates(){
		brand="";
		taste="";
		kind="";
		additive="";
		fats=carbohydrates=protein=roughage=price=-1;
		amount=cocoaContent=energy=-1;
	}

	private List<Chocolates> chocs;
	public List<Chocolates> getChocolates(){ return chocs;}
	
	public Chocolates(int c){
		Random r = new Random();
		
		//random 10 czekolad
		chocs = new ArrayList<Chocolates>();
		TreeLearning treeC = null;
		try {
			treeC = new TreeLearning("chocolates");
			treeC.writeTree("chocolates");				
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		for(int i=0; i<c; i++){
			Chocolates x = new Chocolates();
			int rAm = (7 + r.nextInt(23) )*10;
			int rCoCo = r.nextInt(85);
			int rEn = 500 + r.nextInt(100);
			double rPr = 1.23 + 8*Math.round(r.nextDouble()* 100.0) / 100.0; //Math.round(a * 100.0) / 100.0;
			
			String[] rABr = x.getAllBrands();
			String rBr = rABr[r.nextInt(rABr.length)];

			String[] rATa = x.getAllTastes();
			String rTa = rATa[r.nextInt(rATa.length)];			

			String[] rAKi = x.getAllKinds();
			String rKi = rAKi[r.nextInt(rAKi.length)];

			String[] rAAd = x.getAllAdditives();
			String rAd = rAAd[r.nextInt(rAAd.length)];
			
			x.setAmount(rAm);
			x.setCocoaContent(rCoCo);
			x.setEnergy(rEn);
			x.setPrice(rPr);
			x.setBrand(rBr);
			x.setTaste(rTa);
			x.setKind(rKi);
			x.setAdditive(rAd);
			
			//sprawdz czy klient bedzie chcial ten produkt
			String isGood="";
			try {
				isGood = treeC.checkChocolate(x);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(isGood=="tak") x.setIsGood(true);
			else x.setIsGood(false);
			
			chocs.add(x);
		}
	}
	
	
	public String[] getAllBrands() { return brands;}
	public String[] getAllTastes() { return tastes;}
	public String[] getAllKinds() { return kinds;}
	public String[] getAllAdditives() { return additives;}
	
	public String getTaste() { return taste;}
	public String getAdditive() { return additive;}
	public int getCocoaContent() { return cocoaContent;}
	public int getEnergy() { return energy;}
	public double getFats() { return fats;}
	public double getCarbohydrates() { return carbohydrates;}
	public double getProtein() { return protein;}
	public double getRoughage() { return roughage;}
	
	public void setTaste(String x) { this.taste = x;}
	public void setAdditive(String x) { this.additive = x;}
	public void setCocoaContent(int x) { this.cocoaContent = x;}
	public void setEnergy(int x) { this.energy = x;} 
	public void setFats(double x) { this.fats = x;}
	public void setCarbohydrates(double x) { this.carbohydrates = x;}
	public void setProtein(double x) { this.protein = x;}
	public void setRoughage(double x) { this.roughage = x;}
	
}
