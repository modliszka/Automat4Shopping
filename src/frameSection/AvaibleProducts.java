package frameSection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import DecisionTrees.TreeLearning;

public class AvaibleProducts {

	public AvaibleProducts() {
		//utworz liste dostepnych czekolad w sklepie
		//wczytane z pliku lub random
		Random r = new Random();
		
		//random 10 czekolad
		List<Chocolates> chocs = new ArrayList<Chocolates>();		
		for(int i=0; i<10; i++){
			Chocolates x = new Chocolates();
			int rAm = (7 + 23 * r.nextInt() )*10;
			int rCoCo = r.nextInt(85);
			int rEn = 500 + 100 * r.nextInt();
			
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
			x.setBrand(rBr);
			x.setTaste(rTa);
			x.setKind(rKi);
			x.setAdditive(rAd);
			
			//sprawdz czy klient bedzie chcial ten produkt
			String isGood="";
			try {
				TreeLearning tree = new TreeLearning("chocolates");
				tree.writeTree("chocolates");
				isGood = tree.checkChocolate(x);				
			} catch (Exception e) {
				System.out.println(e);
			}
			
			if(isGood=="tak") x.setIsGood(true);
			else x.setIsGood(false);
			
			chocs.add(x);
		}
		
	}
}
