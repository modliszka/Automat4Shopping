package frameSection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import DecisionTrees.TreeLearning;

public class Juice extends Product {
	private String wrapping, vitamin;
	private double carbohydrates;
	private int energy;

	private String[] brands = {"cappy","caprio","dawton","drWitt","fortuna","garden","hortex","kubus","leon","tymbark","tesco","vitellia"};
	private String[] tastes = {"multiwitamina","ananas","mandarynka","pomaranczy","truskawkowy","jablkowy","porzeczkowy","cytrynowy","brzoskwiniowy"};
	private String[] kinds = {"sok","napoj","nektar"};
	private String[] wrappings = {"karton","butelkaPlast","butelkaSzklana"};
	private String[] vitamins = {"brak","a","b3","c","a_c","a_b3_c","e","a_e"};	

	private int[] amounts = {10,25,200,200,100,30,100,50,200,75,100,100,200,100,150,200}; //*10ml
	
	public Juice(){
		brand=vitamin="";
		taste="";
		kind="";
		wrapping="";
		carbohydrates=-1;
		amount=energy=-1;
		
		setProductKind("sok/napój");
	}

	public int[] getAllAmounts() { return amounts;}
	public String[] getAllBrands() { return brands;}
	public String[] getAllTastes() { return tastes;}
	public String[] getAllKinds() { return kinds;}
	public String[] getAllWrappings() { return wrappings;}
	public String[] getAllVitamins() { return vitamins;}
	
	public String getWrapping() { return wrapping;}
	public String getVitamin() { return vitamin;}
	public int getEnergy() { return energy;}
	public double getCarbohydrates() { return carbohydrates;}
	
	public void setWrapping(String x) { this.wrapping = x;}
	public void setEnergy(int x) { this.energy = x;} 
	public void setCarbohydrates(double x) { this.carbohydrates = x;}
	public void setVitamin(String x) { this.vitamin = x;}
	
}
