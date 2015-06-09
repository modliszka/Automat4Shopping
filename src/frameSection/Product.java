package frameSection;

import shop.Shelf;

public class Product {
	protected String brand;
	protected String kind;
	protected double amount;
	protected double price;
	protected boolean isGood;	

	public String getBrand() { return brand;}
	public String getKind() { return kind;}
	public double getAmount() { return amount;}	
	public double getPrice() { return price;}
	public boolean getIsGood() { return isGood;}

	public void setBrand(String x) { this.brand = x;}
	public void setKind(String x) { this.kind = x;}
	public void setAmount(int x) { this.amount = x;} 
	public void setPrice(double x) { this.price = x;} 
	public void setIsGood(boolean x) { this.isGood = x;}
	
	
	
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
