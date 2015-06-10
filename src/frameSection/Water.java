package frameSection;

public class Water extends Product{
	double minerals; //calciumCations=magnesiumCations=bicarbonateAnions

	private String[] brands = {"polanicka","nestle","cisowianka","dobrowianka"," kroplaBeskidu","muszyna","primavera","zywiec","muszynianka"};
	private String[] kinds = {"gazowana","niegazowana"};
	private String[] tastes = {"naturalny","naturalny","naturalny","naturalny","pomaranczy","truskawkowy"," jablkowy","porzeczkowy","cytrynowy","brzoskwiniowy"};
	
	private int[] amounts = {25,150,50,150,75,50,100,150,50,50,500,150}; //*10ml
	public int[] getAllAmounts() { return amounts;}
	
	public Water(){
		brand="";
		taste="";
		kind="";
		minerals=amount=price=-1;
		
		setProductKind("woda");
	}
	
	public String[] getAllBrands() { return brands;}
	public String[] getAllKinds() { return kinds;}
	public String[] getAllTastes() { return tastes;}

	public double getMinerals() { return minerals;}
	public void setMinerals(double x) { this.minerals = x;}
}
