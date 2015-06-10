package shop;

import java.util.ArrayList;
import java.util.Random;

import DecisionTrees.TreeLearning;
import frameSection.Chocolate;
import frameSection.Juice;
import frameSection.Product;
import frameSection.Tea;

public class Place {
	
	boolean problem;
	ArrayList<Product> productsList;
	
	protected Place(String shelfSide, String product, String productKind){
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
				
				for(int i=0; i<8; i++){
					Chocolate x = new Chocolate();
					int rAm = (7 + r.nextInt(23) )*10;
					int rCoCo = r.nextInt(85);
					int rEn = 500 + r.nextInt(100);
					double rPr = 1.23 + 8*Math.round(r.nextDouble()* 100.0) / 100.0; 
					
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
				
				for(int i=0; i<8; i++){
					Tea x = new Tea();
					int rAm = (2 + r.nextInt(18) )*10;
					double temp = Math.round(r.nextDouble()* 100.0) / 100.0;
					double rPr = 2.86 + 7*temp;
					
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
				
				for(int i=0; i<8; i++){
					Juice x = new Juice();		
					
					int rEn = 32 + r.nextInt(35);
					double rPr = 1.23 + 8*Math.round(r.nextDouble()* 100.0) / 100.0;
					double rCa = 6 + 5*Math.round(r.nextDouble()* 100.0) / 100.0;

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
