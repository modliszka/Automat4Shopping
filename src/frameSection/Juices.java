package frameSection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import DecisionTrees.TreeLearning;

public class Juices extends Products {
	private String taste, wrapping;
	private double carbohydrates,vitaminA,vitaminB3,vitaminC,vitaminE;
	private int energy;

	private String[] brands = {"cappy","caprio","dawton","drWitt","fortuna","garden","hortex","kubus","leon","tymbark","tesco","vitellia"};
	private String[] tastes = {"multiwitamina","ananas","mandarynka","pomaranczy","truskawkowy","jablkowy","porzeczkowy"," cytrynowy","brzoskwiniowy"};
	private String[] kinds = {"sok","napoj","nektar"};
	private String[] wrappings = {"karton","butelkaPlast","butelkaSzklana"};
	
	public Juices(){
		brand="";
		taste="";
		kind="";
		wrapping="";
		carbohydrates=vitaminA=vitaminB3=vitaminC=vitaminE=-1;
		amount=energy=-1;
	}

	private List<Juices> juices;
	public List<Juices> getJuices(){ return juices;}
	
	public Juices(int j){
		Random r = new Random();
		
		juices = new ArrayList<Juices>();
		TreeLearning tree = null;
		try {
			tree = new TreeLearning("juices");
			tree.writeTree("juices");				
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		for(int i=0; i<j; i++){
			Juices x = new Juices();
			int rAm = (7 + r.nextInt(23) )*10;
			int rEn = 500 + r.nextInt(100);
			double rPr = 1.23 + 8*Math.round(r.nextDouble()* 100.0) / 100.0; //Math.round(a * 100.0) / 100.0;
			
			String[] rABr = x.getAllBrands();
			String rBr = rABr[r.nextInt((rABr.length-1))];

			String[] rATa = x.getAllTastes();
			String rTa = rATa[r.nextInt((rATa.length-1))];			

			String[] rAKi = x.getAllKinds();
			String rKi = rAKi[r.nextInt((rAKi.length-1))];

			String[] rAWr = x.getAllWrappings();
			String rWr = rAWr[r.nextInt((rAWr.length-1))];
			
			x.setAmount(rAm);
			x.setEnergy(rEn);
			x.setPrice(rPr);
			x.setBrand(rBr);
			x.setTaste(rTa);
			x.setKind(rKi);
			x.setWrapping(rWr);
			
			//sprawdz czy klient bedzie chcial ten produkt
			String isGood="";
			try {
				isGood = tree.checkJuice(x);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(isGood=="tak") x.setIsGood(true);
			else x.setIsGood(false);
			
			juices.add(x);
		}
	}
	
	
	public String[] getAllBrands() { return brands;}
	public String[] getAllTastes() { return tastes;}
	public String[] getAllKinds() { return kinds;}
	public String[] getAllWrappings() { return wrappings;}
	
	public String getTaste() { return taste;}
	public String getWrapping() { return wrapping;}
	public int getEnergy() { return energy;}
	public double getCarbohydrates() { return carbohydrates;}
	public double getVitaminA() { return vitaminA;}
	public double getVitaminB3() { return vitaminB3;}
	public double getVitaminC() { return vitaminC;}
	public double getVitaminE() { return vitaminE;}
	
	public void setTaste(String x) { this.taste = x;}
	public void setWrapping(String x) { this.wrapping = x;}
	public void setEnergy(int x) { this.energy = x;} 
	public void setCarbohydrates(double x) { this.carbohydrates = x;}
	public void setVitaminA(double x) { this.vitaminA = x;}
	public void setVitaminB3(double x) { this.vitaminB3 = x;}
	public void setVitaminC(double x) { this.vitaminC = x;}
	public void setVitaminE(double x) { this.vitaminE = x;}
	
}
