package frameSection;

public class Chocolates {
	private String brand, taste, kind, additive;
	private double fats,carbohydrates,protein,roughage,price;
	private int amount,cocoaContent,energy;
	private boolean isGood;

	private String[] brands = {"alpenGold","wedel","goplana","heidi","milka","terravita","tesco","wawel"};
	private String[] tastes = {"mleczna","gorzka","biala"};
	private String[] kinds = {"zwykla","babelkowa","nadziewana","zowocami"};
	private String[] additives = {"brak","karmelowa","orzechowa","wisniowa","malinowa","truskawkowa","fistaszki","kokosowym","pomaranczowa","jogurtowa","tiramisu","kasztanki","michalki","wisiniaChili"};
	
	public Chocolates(){
		brand="";
		taste="";
		kind="";
		additive="";
		fats=carbohydrates=protein=roughage=price=0;
		amount=cocoaContent=energy=0;
	}
	
	
	public String[] getAllBrands() { return brands;}
	public String[] getAllTastes() { return tastes;}
	public String[] getAllKinds() { return kinds;}
	public String[] getAllAdditives() { return additives;}
	
	public String getBrand() { return brand;}
	public String getTaste() { return taste;}
	public String getKind() { return kind;}
	public String getAdditive() { return additive;}
	public int getCocoaContent() { return cocoaContent;}
	public int getAmount() { return amount;}
	public int getEnergy() { return energy;}
	public double getFats() { return fats;}
	public double getCarbohydrates() { return carbohydrates;}
	public double getProtein() { return protein;}
	public double getRoughage() { return roughage;}
	public double getPrice() { return price;}
	public boolean getIsGood() { return isGood;}
	
	public void setBrand(String x) { this.brand = x;}
	public void setTaste(String x) { this.taste = x;}
	public void setKind(String x) { this.kind = x;}
	public void setAdditive(String x) { this.additive = x;}
	public void setCocoaContent(int x) { this.cocoaContent = x;}
	public void setAmount(int x) { this.amount = x;} 
	public void setEnergy(int x) { this.energy = x;} 
	public void setFats(double x) { this.fats = x;}
	public void setCarbohydrates(double x) { this.carbohydrates = x;}
	public void setProtein(double x) { this.protein = x;}
	public void setRoughage(double x) { this.roughage = x;}
	public void setPrice(double x) { this.price = x;} 
	public void setIsGood(boolean x) { this.isGood = x;}
	
}
