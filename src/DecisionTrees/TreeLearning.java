package DecisionTrees;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.*;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;

import java.util.*;

//tworzenie instances od zera
//https://ianma.wordpress.com/2010/01/16/weka-with-java-eclipse-getting-started/

//przyklad na uzycie WEKA
//http://www.programcreek.com/2013/01/a-simple-machine-learning-example-in-java/

//dzielenie na zestaw trenujacy i testowy
//https://weka.wikispaces.com/How+do+I+divide+a+dataset+into+training+and+test+set%3F

public class TreeLearning
{
	public static void main(String[] args)
	{
		new TreeLearning();
	}

	J48 learntModel;
	Instances train;

	public TreeLearning()
	{
		loadTree();
		checkExample();
		learnTree();
		
	}

	public void loadTree()
	{
		try {
			String filename="./src/DecisionTrees/data/teas.arff";
			// Load the data source and learn the model
			DataSource source = new DataSource(filename);
			train = source.getDataSet();			
			train.setClassIndex(train.numAttributes() - 1);

			learntModel = new J48();
			learntModel.buildClassifier(train);
			// Print out the loaded tree
			System.out.println("");
			System.out.println(learntModel.toString());
			
			//pobieranie rezultatów
			//http://stackoverflow.com/questions/28031068/get-weka-classifier-results
			System.out.println("=====");
			//System.out.println(train.toSummaryString());
			
		}
		catch (Exception e)
		{
			System.out.println("");
			System.out.println(e.toString());
			System.out.println("File could not be loaded, please try again");
			System.out.println("");
		}
	}
	
	public void checkExample()
	{
		try {			
			String predictFilename="./src/DecisionTrees/data/teas_predict.arff";
			DataSource predictSource = new DataSource(predictFilename);
	        Instances test = predictSource.getDataSet();
	        test.setClassIndex(test.numAttributes() - 1);
	        
	        double label = learntModel.classifyInstance(test.instance(0));
	        test.instance(0).setClassValue(label);

	        System.out.println("=====");
	        System.out.println("czy można wziąć ten produkt? Odp: "+test.instance(0).stringValue(4));		

		}
		catch (Exception e)
		{
			System.out.println("");
			System.out.println(e.toString());
			System.out.println("File could not be loaded, please try again");
			System.out.println("");
		}
	}

	
	public void learnTree()
	{
		// Apply the decision tree against read in cases
		if (train == null)
		{
			System.out.println("Training data must be loaded from a .arff file to determine attributes");
			System.out.println("");
		}
		
		//wczytywanie z konsoli
		/*
		//Clone the set of instances
		Instances newCases = new Instances(train);
		newCases.delete();
		
		while (true)
		{
			Console console = System.console();
			Instance newInsatnce = new DenseInstance(newCases.numAttributes());
			System.out.println("");
			System.out.println("Enter values for the attributes...");

			for (int i = 0; i < newCases.numAttributes(); ++i)
			{
				Attribute attribute = newCases.attribute(i);
				String value = console.readLine(attribute.name() + ": ");

				newInsatnce.setValue(attribute, value);
			}

			newCases.add(newInsatnce);

			System.out.println("");

			if ( ! console.readLine("Add another case? [y/n] ").toLowerCase().equals("y"))
				break;
		}*/
		
		/*
		 * wczytywanie pojedynczej instancji
		 * 
		//Clone the set of instances
		Instances newCases = new Instances(train);
		newCases.delete();

		Instance newInstance = new DenseInstance(newCases.numAttributes());
		//String[] atrr = {"biala","torebki","pomaranczy","od35do70","tak"};
		
		String[] atrr = {"zielona","granulowana","pomaranczy","od35do70","nie"};
		
		System.out.println("newCases.numAttributes(): "+newCases.numAttributes()+"\n");
		for (int i = 0; i < newCases.numAttributes(); ++i)
		{
			Attribute attribute = newCases.attribute(i);
			String value = atrr[i];
			System.out.println(attribute.name()+" ");
			newInstance.setValue(attribute, value);
		}
		newCases.add(newInstance);				
		*/

		// Load the data source and learn the model
		String testSetFilename="./src/DecisionTrees/data/teas_test.arff";		
		Instances testSet = null;
		try {
			DataSource testSetSource = new DataSource(testSetFilename);
			testSet = testSetSource.getDataSet();
			testSet.setClassIndex(testSet.numAttributes() - 1);
		} catch (Exception e1) {
			System.out.println("Problem z wczytaniem pliku .arff: "+e1.toString());
		}		
		

		try
		{
			// Do evaluate model against the learnt model
			Evaluation eval = new Evaluation(testSet);	//testSet zamiast newCases

			if (testSet.numInstances() >= 10)//&& console.readLine("Cross Validate? [y/n]: ").toLowerCase().equals("y"))
			{
				eval.crossValidateModel(learntModel, testSet, 10, new Random(1));
				
				/*
				 * Sprawdzian krzyżowy (lub walidacja krzyżowa, kroswalidacja, sprawdzanie krzyżowe) – metoda statystyczna, 
				 * polegająca na podziale próby statystycznej na podzbiory, a następnie przeprowadzaniu wszelkich analiz na niektórych z nich (zbiór uczący), 
				 * podczas gdy pozostałe służą do potwierdzenia wiarygodności jej wyników (zbiór testowy, zbiór walidacyjny).
				 * */				
			}
			else
			{
				eval.evaluateModel(learntModel, testSet);
			}

			// Print confusion matrix
			System.out.println(eval.toSummaryString("\nResults\n======\n", false));
			System.out.println(eval.toMatrixString());
		}
		catch (Exception e)
		{
			System.out.println("");
			System.out.println(e.toString());
			System.out.println("Unable to cross validate and create confusion matrix against entered cases");
			System.out.println("");
		}
		/*
		try {
			learntModel.buildClassifier(testSet);
			System.out.println(learntModel.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}
