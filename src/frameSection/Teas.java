package frameSection;

import java.util.Date;

import shop.Product;

public class Teas extends Product {
	//double weight;
	//Date expirationDate;
	private String tKind, tPackage, tTaste, tBrand; 
	private int amount;
	private double price;
	private boolean isGood;
	
	private String[] brands = {"lipton","tesco","tetley","herbapol","saga","dilmah","minutka","bigActive"};
	private String[] tastes = {"zwykly","pomarancza","pokrzywy","rumianek","malinowy","melisa","mieta"};
	private String[] kinds = {"czarna","zielona","biala","ziolowa"};
	private String[] packages = {"torebki","sypana","granulowana"};
	
	public Teas(){
		tKind= tPackage= tTaste= tBrand="";
		amount=0;
		price=0;
	}	
	
	public String[] getAllBrands() { return brands;}
	public String[] getAllTastes() { return tastes;}
	public String[] getAllKinds() { return kinds;}
	public String[] getAllPackages() { return packages;}
	
	public String getBrand() { return tBrand;}
	public String getTaste() { return tTaste;}
	public String getKind() { return tKind;}
	public String getPackage() { return tPackage;}
	public int getAmount() { return amount;}
	public double getPrice() { return price;}
	public boolean getIsGood() { return isGood;}
	
	public void setBrand(String x) { this.tBrand = x;}
	public void setTaste(String x) { this.tTaste = x;}
	public void setKind(String x) { this.tKind = x;}
	public void setPackage(String x) { this.tPackage = x;}
	public void setAmount(int x) { this.amount = x;} 
	public void setPrice(double x) { this.price = x;} 
	public void setIsGood(boolean x) { this.isGood = x;}
}
