package frameSection;

import shop.Shelf;

public class Product {
	protected String productKind;
	protected String brand;
	protected String kind;
	protected double amount;
	protected double price;
	protected boolean isGood;	
	protected String feature;
	protected String taste;
	protected int energy;

	public String getBrand() { return brand;}
	public String getKind() { return kind;}
	public double getAmount() { return amount;}	
	public double getPrice() { return price;}
	public boolean getIsGood() { return isGood;}
	public String getProductKind() { return productKind;}
	public String getTaste() { return taste;}
	public int getEnergy() { return energy;}

	public void setEnergy(int x) { this.energy = x;} 
	public void setBrand(String x) { this.brand = x;}
	public void setKind(String x) { this.kind = x;}
	public void setAmount(int x) { this.amount = x;} 
	public void setPrice(double x) { this.price = x;} 
	public void setIsGood(boolean x) { this.isGood = x;}
	public void setProductKind(String x) { this.productKind = x;}
	public void setTaste(String x) { this.taste = x;}
	
	public String getAdditionalFeature(){ return feature; }
	public void setAdditionalFeature(String x){ this.feature = x; }
	
	
	
//	public String nazwa;
//	public String rodzaj;
//	public double wielkosc; // np. 200
//	public String jednostka; // np. ml możnaby jakiś enum zrobic
//	public int ilosc;
//	public double cena;
//	public String glRozroznik; //nazwy do dogadania:)
//	public String dodRozroznik;
	String name;
	String packaging;
	Shelf shelf; // półka przypisana do danego produktu
}
