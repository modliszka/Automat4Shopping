package shop;

import java.util.ArrayList;
import java.util.Random;

import DecisionTrees.TreeLearning;
import frameSection.Chocolate;
import frameSection.Juice;
import frameSection.Pasta;
import frameSection.Product;
import frameSection.Tea;
import frameSection.Water;
import frameSection.Yoghurts;

public class Place {
	
	boolean problem;
	ArrayList<Product> productsList;
	
	protected Place(){
		productsList= new ArrayList<>();
		int[] cookingTime={5,10,3,15,10,5,8,7,12,8};
		String[] brands = {"adriana","animak","barilla","lubella","malma","melissa","tesco","animak","barilla","lubella"};
		String[] kinds = {"spaghetti","piora","swiderki","krajanka","muszelki","nitki","wstazki","rurki","lasagne","bucatini"};
		String[] flourKinds = {"zwykly","jajeczny","wyborowy","zMakiGraham","zwykly","jajeczny","wyborowy","zMakiGraham","jajeczny","wyborowy"};

		int[] amounts = {300,400,500,500,650,300,650,400,500,400}; //g
		double[] prices = {2.5, 1.4, 6.12, 7.1, 6.0, 2.15, 4.2, 6.75, 2.65, 3.20};
		
		for(int i=0; i<10; i++){
			Pasta x = new Pasta();

			//en od 310 do 420
			//ct 6-15
			//am 300,400,500,500,650
			//pr 2.1-9.5
			
			x.setAmount(amounts[i]);
			x.setPrice(prices[i]);//
			x.setBrand(brands[i]);
			x.setTaste(flourKinds[i]);
			x.setKind(kinds[i]);
			x.setCookingTime(cookingTime[i]);//
			
			x.setAdditionalFeature(kinds[i]+" "+flourKinds[i]+" "+amounts[i]+"g");
			
			//String[] yn = {"tak","nie"};
			String[] isGood={"nie","tak","nie","tak","nie","tak","tak","nie","tak","nie"};
			
			//sprawdz czy klient bedzie chcial ten produkt
			/*String isGood="";
			try {
				isGood = treeP.checkTea(x);
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			
			if(isGood[i]=="tak") x.setIsGood(true);
			else x.setIsGood(false);
			
			productsList.add(x);
		}
	}
	
	protected Place(String shelfSide, String product, String productKind, int howMany){
		switch (shelfSide) {
			case "L"://left
				problem=false;
				break;
			case "R"://right
				problem=false;
				break;
			case "T"://top
				problem=false;
				break;
			case "B"://bottom
				problem=false;
				break;
			case "A"://all
				problem=false;
				break;
			default:
				problem=true;
				break;
		}
		
		if(problem){
			System.out.println("Wrong shelf side");
		}else{
			Random r = new Random();				
			productsList= new ArrayList<>();
			
			if(product =="chocolates"){		//random 10 czekolad				
				TreeLearning treeC = null;
				try {
					treeC = new TreeLearning(product);
					treeC.writeTree(product);				
				} catch (Exception e) {
					System.out.println(e);
				}				
				
				for(int i=0; i<howMany; i++){
					Chocolate x = new Chocolate();
					int rAm = (7 + r.nextInt(23) )*10;
					int rCoCo = r.nextInt(85);
					int rEn = 500 + r.nextInt(100);
					double rPr = 1.23 + 8*r.nextInt(100) / 100.0; 
					
					String[] rABr = x.getAllBrands();
					String rBr = rABr[r.nextInt(rABr.length)];

					String[] rATa = x.getAllTastes();
					String rTa = rATa[r.nextInt(rATa.length)];			

					String[] rAKi = x.getAllKinds();
					String rKi = rAKi[r.nextInt(rAKi.length)];

					String[] rAAd = x.getAllAdditives();
					String rAd = rAAd[r.nextInt(rAAd.length)];
					
					x.setAmount(rAm);
					x.setCocoaContent(rCoCo);
					x.setEnergy(rEn);
					x.setPrice(rPr);
					x.setBrand(rBr);
					x.setTaste(rTa);
					x.setKind(rKi);
					x.setAdditive(rAd);
					
					x.setAdditionalFeature(rTa+" "+rKi+" "+rAd);
					
					//sprawdz czy klient bedzie chcial ten produkt
					String isGood="";
					try {
						isGood = treeC.checkChocolate(x);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					if(isGood=="tak") x.setIsGood(true);
					else x.setIsGood(false);
					
					productsList.add(x);
				}
			}
			else if (product=="teas"){
				//random 10 herbat	
				TreeLearning treeT = null;
				try {
					treeT = new TreeLearning(product);
					treeT.writeTree(product);	
					treeT.treeTraining(product);			
				} catch (Exception e) {
					System.out.println(e);
				}				
				
				for(int i=0; i<howMany; i++){
					Tea x = new Tea();
					int rAm = (2 + r.nextInt(18) )*10;
					double rPr = 2.86 + 7*r.nextInt(100) / 100.0;
					
					String[] rABr = x.getAllBrands();
					String rBr = rABr[r.nextInt(rABr.length)];

					String[] rATa = x.getAllTastes();
					String rTa = rATa[r.nextInt(rATa.length)];			

					String[] rAKi = x.getAllKinds();
					String rKi = rAKi[r.nextInt(rAKi.length)];

					String[] rAPa = x.getAllPackages();
					String rPa = rAPa[r.nextInt(rAPa.length)];
					
					x.setAmount(rAm);
					x.setPrice(rPr);
					x.setBrand(rBr);
					x.setTaste(rTa);
					x.setKind(rKi);
					x.setPackage(rPa);
					
					x.setAdditionalFeature(rKi+" "+rTa+" "+rPa);
					
					//sprawdz czy klient bedzie chcial ten produkt
					String isGood="";
					try {
						isGood = treeT.checkTea(x);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					if(isGood=="tak") x.setIsGood(true);
					else x.setIsGood(false);
					
					productsList.add(x);
				}		
			}
			else if (product=="juices"){
				TreeLearning tree = null;
				try {
					tree = new TreeLearning(product);
					tree.writeTree(product);				
				} catch (Exception e) {
					System.out.println(e);
				}		
				
				for(int i=0; i<howMany; i++){
					Juice x = new Juice();		
					
					int rEn = 32 + r.nextInt(35);
					double rPr = 1.23 + 8*r.nextInt(100)/ 100.0; //ThreadLocalRandom.current().nextInt(1000)/100.0
					double rCa = 6 + 5*r.nextInt(100) / 100.0;

					int[] rAAm = x.getAllAmounts();
					int rAm = rAAm[r.nextInt((rAAm.length))];
					
					String[] rABr = x.getAllBrands();
					String rBr = rABr[r.nextInt(rABr.length)];

					String[] rATa = x.getAllTastes();
					String rTa = rATa[r.nextInt(rATa.length)];			

					String[] rAKi = x.getAllKinds();
					String rKi = rAKi[r.nextInt(rAKi.length)];

					String[] rAWr = x.getAllWrappings();
					String rWr = rAWr[r.nextInt(rAWr.length)];
					
					String[] rAVi = x.getAllVitamins();
					String rVi = rAVi[r.nextInt(rAVi.length)];
					
					//System.out.println(rBr+","+rWr+","+rKi+","+rTa+","+rVi+","+rAm+","+rEn+","+rCa+","+rPr+",");
					
					x.setAmount(rAm);
					x.setEnergy(rEn);
					x.setPrice(rPr);
					x.setCarbohydrates(rCa);
					x.setVitamin(rVi);
					
					x.setBrand(rBr);
					x.setTaste(rTa);
					x.setKind(rKi);
					x.setWrapping(rWr);			
					
					x.setAdditionalFeature(rKi+" "+rTa+" "+rWr+" "+rAm+"0ml");
										
					//sprawdz czy klient bedzie chcial ten produkt
					String isGood="";
					try {
						isGood = tree.checkJuice(x);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					if(isGood=="tak") x.setIsGood(true);
					else x.setIsGood(false);
					
					productsList.add(x);
				}
			}
			else if (product=="pastas"){
				/*TreeLearning treeP = null;
				try {
					treeP = new TreeLearning(product);
					treeP.writeTree(product);	
					treeP.treeTraining(product);			
				} catch (Exception e) {
					System.out.println(e);
				}*/	
				
				for(int i=0; i<howMany; i++){
					Pasta x = new Pasta();
					double rPr = 2.1 + 7.4*r.nextInt(100) / 100.0;
					int rCt = 6 + r.nextInt(9);

					int[] rAAm = x.getAllAmounts();
					int rAm = rAAm[r.nextInt((rAAm.length))];
					
					//en od 310 do 420
					//ct 6-15
					//am 300,400,500,500,650
					//pr 2.1-9.5
					
					String[] rABr = x.getAllBrands();
					String rBr = rABr[r.nextInt(rABr.length)];

					String[] rAFk = x.getAllflourKinds();
					String rTa = rAFk[r.nextInt(rAFk.length)];			

					String[] rAKi = x.getAllKinds();
					String rKi = rAKi[r.nextInt(rAKi.length)];
					
					x.setAmount(rAm);
					x.setPrice(rPr);
					x.setBrand(rBr);
					x.setTaste(rTa);
					x.setKind(rKi);
					x.setCookingTime(rCt);
					
					x.setAdditionalFeature(rKi+" "+rTa+" "+rAm+"g");
					
					String[] yn = {"tak","nie"};
					String isGood=yn[r.nextInt(2)];
					
					//sprawdz czy klient bedzie chcial ten produkt
					/*String isGood="";
					try {
						isGood = treeP.checkTea(x);
					} catch (Exception e) {
						e.printStackTrace();
					}*/
					
					if(isGood=="tak") x.setIsGood(true);
					else x.setIsGood(false);
					
					productsList.add(x);
				}		
			}
			else if (product=="waters"){
				/*TreeLearning treeP = null;
				try {
					treeP = new TreeLearning(product);
					treeP.writeTree(product);	
					treeP.treeTraining(product);			
				} catch (Exception e) {
					System.out.println(e);
				}*/	
				
				for(int i=0; i<howMany; i++){
					Water x = new Water();
					double rPr = 0.69 + 5*r.nextInt(100) / 100.0;
					double rMi = (23 + r.nextInt(120))*10;
					//23-120 [10mg]

					int[] rAAm = x.getAllAmounts();
					int rAm = rAAm[r.nextInt((rAAm.length))]*10;
										
					String[] rABr = x.getAllBrands();
					String rBr = rABr[r.nextInt(rABr.length)];

					String[] rATa = x.getAllTastes();
					String rTa = rATa[r.nextInt(rATa.length)];			

					String[] rAKi = x.getAllKinds();
					String rKi = rAKi[r.nextInt(rAKi.length)];
					
					x.setAmount(rAm);
					x.setPrice(rPr);
					x.setBrand(rBr);
					x.setTaste(rTa);
					x.setKind(rKi);
					x.setMinerals(rMi);
					
					x.setAdditionalFeature(rKi+" "+rTa+" "+rAm+"ml");
					
					String[] yn = {"tak","nie"};
					String isGood=yn[r.nextInt(2)];
					
					//sprawdz czy klient bedzie chcial ten produkt
					/*String isGood="";
					try {
						isGood = treeP.checkTea(x);
					} catch (Exception e) {
						e.printStackTrace();
					}*/
					
					if(isGood=="tak") x.setIsGood(true);
					else x.setIsGood(false);
					
					productsList.add(x);
				}		
			}
			else if (product=="yoghurts"){
				/*TreeLearning treeP = null;
				try {
					treeP = new TreeLearning(product);
					treeP.writeTree(product);	
					treeP.treeTraining(product);			
				} catch (Exception e) {
					System.out.println(e);
				}*/	
				
				for(int i=0; i<howMany; i++){
					Yoghurts x = new Yoghurts();
					double rPr = 0.69 + 6*r.nextInt(100) / 100.0;
					
					int rAm = 150 + r.nextInt(520); //150-550 ml
										
					String[] rABr = x.getAllBrands();
					String rBr = rABr[r.nextInt(rABr.length)];

					String[] rATa = x.getAllTastes();
					String rTa = rATa[r.nextInt(rATa.length)];			

					String[] rAKi = x.getAllKinds();
					String rKi = rAKi[r.nextInt(rAKi.length)];
					
					x.setAmount(rAm);
					x.setPrice(rPr);
					x.setBrand(rBr);
					x.setTaste(rTa);
					x.setKind(rKi);
					
					x.setAdditionalFeature(rKi+" "+rTa+" "+rAm+"ml");
					
					String[] yn = {"tak","nie"};
					String isGood=yn[r.nextInt(2)];
					
					//sprawdz czy klient bedzie chcial ten produkt
					/*String isGood="";
					try {
						isGood = treeP.checkTea(x);
					} catch (Exception e) {
						e.printStackTrace();
					}*/
					
					if(isGood=="tak") x.setIsGood(true);
					else x.setIsGood(false);
					
					productsList.add(x);
				}		
			}
		}
	}
	
	/*public void add(Product p){
		if(!problem){
			list.add(p);
		}else{
			System.out.println("There's no shelf");
		}
	}*/
}
