package frameSection;

public class Chocolate extends Product {
	private String taste, additive;
	private double fats,carbohydrates,protein,roughage;
	private int cocoaContent,energy;

	private String[] brands = {"alpenGold","wedel","goplana","heidi","milka","terravita","tesco","wawel"};
	private String[] tastes = {"mleczna","gorzka","biala"};
	private String[] kinds = {"zwykla","babelkowa","nadziewana","zowocami"};
	private String[] additives = {"brak","karmelowa","orzechowa","wisniowa","malinowa","truskawkowa","fistaszki","kokosowym","pomaranczowa","jogurtowa","tiramisu","kasztanki","michalki","wisiniaChili"};
	
	public Chocolate(){
		brand="";
		taste="";
		kind="";
		additive="";
		fats=carbohydrates=protein=roughage=price=-1;
		amount=cocoaContent=energy=-1;
		
		setProductKind("czekolada");
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
