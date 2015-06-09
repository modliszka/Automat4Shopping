package frameSection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import DecisionTrees.TreeLearning;

public class AvaibleProducts {
	private List<Chocolates> chocs;
	private List<Teas> teas;
	
	public AvaibleProducts() throws Exception {
		//utworz liste dostepnych czekolad w sklepie
		//wczytane z pliku lub random
		Random r = new Random();
		
		//random 10 czekolad
		chocs = new ArrayList<Chocolates>();
		TreeLearning treeC = null;
		try {
			treeC = new TreeLearning("chocolates");
			treeC.writeTree("chocolates");				
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		for(int i=0; i<10; i++){
			Chocolates x = new Chocolates();
			int rAm = (7 + r.nextInt(23) )*10;
			int rCoCo = r.nextInt(85);
			int rEn = 500 + r.nextInt(100);
			double rPr = 1.23 + 8*Math.round(r.nextDouble()* 100.0) / 100.0; //Math.round(a * 100.0) / 100.0;
			
			String[] rABr = x.getAllBrands();
			String rBr = rABr[r.nextInt((rABr.length-1))];

			String[] rATa = x.getAllTastes();
			String rTa = rATa[r.nextInt((rATa.length-1))];			

			String[] rAKi = x.getAllKinds();
			String rKi = rAKi[r.nextInt((rAKi.length-1))];

			String[] rAAd = x.getAllAdditives();
			String rAd = rAAd[r.nextInt((rAAd.length-1))];
			
			x.setAmount(rAm);
			x.setCocoaContent(rCoCo);
			x.setEnergy(rEn);
			x.setPrice(rPr);
			x.setBrand(rBr);
			x.setTaste(rTa);
			x.setKind(rKi);
			x.setAdditive(rAd);
			
			//sprawdz czy klient bedzie chcial ten produkt
			String isGood="";
			isGood = treeC.checkChocolate(x);
			
			if(isGood=="tak") x.setIsGood(true);
			else x.setIsGood(false);
			
			chocs.add(x);
		}
		
		/*
		//random 10 herbat
		teas = new ArrayList<Teas>();	
		TreeLearning treeT = null;
		try {
			treeT = new TreeLearning("teas");
			treeT.writeTree("teas");		
			treeT.treeTraining("teas");		
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		for(int i=0; i<10; i++){
			Teas x = new Teas();
			int rAm = (2 + r.nextInt(18) )*10;
			double temp = Math.round(r.nextDouble()* 100.0) / 100.0;
			double rPr = 2.86 + 7*temp;
			
			String[] rABr = x.getAllBrands();
			String rBr = rABr[r.nextInt((rABr.length-1))];

			String[] rATa = x.getAllTastes();
			String rTa = rATa[r.nextInt((rATa.length-1))];			

			String[] rAKi = x.getAllKinds();
			String rKi = rAKi[r.nextInt((rAKi.length-1))];

			String[] rAPa = x.getAllPackages();
			String rPa = rAPa[r.nextInt((rAPa.length-1))];
			
			x.setAmount(rAm);
			x.setPrice(rPr);
			x.setBrand(rBr);
			x.setTaste(rTa);
			x.setKind(rKi);
			x.setPackage(rPa);
			
			//sprawdz czy klient bedzie chcial ten produkt
			String isGood="";
			isGood = treeT.checkTea(x);
			
			if(isGood=="tak") x.setIsGood(true);
			else x.setIsGood(false);
			
			teas.add(x);
		}	*/	
	}
	
	public List<Chocolates> getChocolates(){ return chocs;}
	public List<Teas> getTeas(){ return teas;}
}
