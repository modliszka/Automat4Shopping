package frameSection;

public class Pasta extends Product{
	String flourKind;
	int cookingTime;

	private String[] brands = {"adriana","animak","barilla","lubella","malma","melissa","tesco"};
	private String[] kinds = {"spaghetti","piora","swiderki","krajanka","muszelki","nitki","wstazki","rurki","lasagne","bucatini"};
	private String[] flourKinds = {"zwykly","jajeczny","wyborowy","zMakiGraham"};
	
	private int[] amounts = {300,400,500,500,650}; //g
	public int[] getAllAmounts() { return amounts;}
	
	public Pasta(){
		brand="";
		taste="";
		kind="";
		amount=price=-1;
		energy=cookingTime=-1;
		setProductKind("makaron");
	}
	
	public String[] getAllBrands() { return brands;}
	public String[] getAllKinds() { return kinds;}
	public String[] getAllflourKinds() { return flourKinds;}

	public String getFlourKind() { return flourKind;}
	public String getTaste() { return getFlourKind();} //nie ma smaku, ale jest rodzaj mąki
	public int getCookingTime() { return cookingTime;}

	public void setCookingTime(int x) { this.cookingTime = x;}
	public void setFlourKind(String x) { this.flourKind = x;}
	public void setTaste(String x) { setFlourKind(x);}
}
