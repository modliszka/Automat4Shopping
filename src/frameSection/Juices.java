package frameSection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import DecisionTrees.TreeLearning;

public class Juices extends Products {
	private String taste, wrapping, vitamin;
	private double carbohydrates;
	private int energy;

	private String[] brands = {"cappy","caprio","dawton","drWitt","fortuna","garden","hortex","kubus","leon","tymbark","tesco","vitellia"};
	private String[] tastes = {"multiwitamina","ananas","mandarynka","pomaranczy","truskawkowy","jablkowy","porzeczkowy","cytrynowy","brzoskwiniowy"};
	private String[] kinds = {"sok","napoj","nektar"};
	private String[] wrappings = {"karton","butelkaPlast","butelkaSzklana"};
	private String[] vitamins = {"brak","a","b3","c","a_c","a_b3_c","e","a_e"};	

	private int[] amounts = {10,25,200,200,100,30,100,50,200,75,100,100,200,100,150,200}; //*10ml
	
	public Juices(){
		brand=vitamin="";
		taste="";
		kind="";
		wrapping="";
		carbohydrates=-1;
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
			
			int rEn = 32 + r.nextInt(35);
			double rPr = 1.23 + 8*Math.round(r.nextDouble()* 100.0) / 100.0; //Math.round(a * 100.0) / 100.0;
			double rCa = 6 + 5*Math.round(r.nextDouble()* 100.0) / 100.0;

			int[] rAAm = x.getAllAmounts();
			int rAm = rAAm[r.nextInt((rAAm.length))];
			
			String[] rABr = x.getAllBrands();
			String rBr = rABr[r.nextInt(rABr.length)];

			String[] rATa = x.getAllTastes();
			String rTa = rATa[r.nextInt(rATa.length)];			

			String[] rAKi = x.getAllKinds();
			String rKi = rAKi[r.nextInt(rAKi.length)];

			String[] rAWr = x.getAllWrappings();
			String rWr = rAWr[r.nextInt(rAWr.length)];
			
			String[] rAVi = x.getAllVitamins();
			String rVi = rAVi[r.nextInt(rAVi.length)];
			
			//System.out.println(rBr+","+rWr+","+rKi+","+rTa+","+rVi+","+rAm+","+rEn+","+rCa+","+rPr+",");
			
			x.setAmount(rAm);
			x.setEnergy(rEn);
			x.setPrice(rPr);
			x.setCarbohydrates(rCa);
			x.setVitamin(rVi);
			
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
	

	public int[] getAllAmounts() { return amounts;}
	public String[] getAllBrands() { return brands;}
	public String[] getAllTastes() { return tastes;}
	public String[] getAllKinds() { return kinds;}
	public String[] getAllWrappings() { return wrappings;}
	public String[] getAllVitamins() { return vitamins;}
	
	public String getTaste() { return taste;}
	public String getWrapping() { return wrapping;}
	public String getVitamin() { return vitamin;}
	public int getEnergy() { return energy;}
	public double getCarbohydrates() { return carbohydrates;}
	
	public void setTaste(String x) { this.taste = x;}
	public void setWrapping(String x) { this.wrapping = x;}
	public void setEnergy(int x) { this.energy = x;} 
	public void setCarbohydrates(double x) { this.carbohydrates = x;}
	public void setVitamin(String x) { this.vitamin = x;}
	
}
