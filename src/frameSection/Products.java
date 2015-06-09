package frameSection;

public class Products {
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
}
