package frameSection;

public class Tea extends Product {
	//double weight;
	//Date expirationDate;
	private String tPackage, tTaste; 
	
	private String[] brands = {"lipton","tesco","tetley","herbapol","saga","dilmah","minutka","bigActive"};
	private String[] tastes = {"zwykly","pomarancza","pokrzywy","rumianek","malinowy","melisa","mieta"};
	private String[] kinds = {"czarna","zielona","biala","ziolowa"};
	private String[] packages = {"torebki","sypana","granulowana"};
	
	public Tea(){
		super();
		kind= tPackage= tTaste= brand="";
		amount=-1;
		price=-1;
	}	
	
	public String[] getAllBrands() { return brands;}
	public String[] getAllTastes() { return tastes;}
	public String[] getAllKinds() { return kinds;}
	public String[] getAllPackages() { return packages;}
	
	public String getTaste() { return tTaste;}
	public String getPackage() { return tPackage;}
	
	public void setTaste(String x) { this.tTaste = x;}
	public void setPackage(String x) { this.tPackage = x;}
}
