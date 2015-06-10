package shop;



import NeuralNetworkHelper.*;
import ProductInterface.*;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

public class Shop{
	
	//public static  List<ProductToList> myProductsList;
	
	public static void main(String[] args){
		
		
		
	      EventQueue.invokeLater(new Runnable() {
	          @Override
	          public void run() {
	      //       if(FileHelper.ChangesInDirectorySieciNeuronowe())
	       //      {
	        		 //myProductsList = new ArrayList<ProductToList>();
					new MainWindow();
	     //        }
					//	ProductHelper.function();
	          }
	      });
	}
}