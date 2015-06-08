package DecisionTrees;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.*;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import frameSection.Chocolates;

//tworzenie instances od zera
//https://ianma.wordpress.com/2010/01/16/weka-with-java-eclipse-getting-started/

//przyklad na uzycie WEKA
//http://www.programcreek.com/2013/01/a-simple-machine-learning-example-in-java/

//dzielenie na zestaw trenujacy i testowy
//https://weka.wikispaces.com/How+do+I+divide+a+dataset+into+training+and+test+set%3F

public class TreeLearning
{
	private J48 treeModel;
	private Instances train;

	public TreeLearning(String trainFileName) throws Exception
	{
		String trainFile = "./src/DecisionTrees/data/"+trainFileName+".arff";
		// Load the data source and learn the model
		DataSource source = new DataSource(trainFile);
		train = source.getDataSet();			
		train.setClassIndex(train.numAttributes() - 1);
		
		treeModel = new J48();
		treeModel.buildClassifier(train);		
	}
	
	public void treeTraining(String testFileName) throws Exception {
		String trainFile = "./src/DecisionTrees/tests/"+testFileName+"Train.arff";
		BufferedReader reader = new BufferedReader(new FileReader(trainFile));
		Instances test = new Instances(reader);
		test.setClassIndex(test.numAttributes() - 1);		
		reader.close();
		
		Instances predicted = new Instances(test);		
		for(int i = 0; i < test.numInstances(); i++)
		{
			double clsLabel = treeModel.classifyInstance(test.instance(i));
			predicted.instance(i).setClassValue(clsLabel);
		}
		
		String filePath = "./src/DecisionTrees/tests/"+testFileName+"Output.arff";
		BufferedWriter writer = null;	
		writer = new BufferedWriter(new FileWriter(filePath));
		writer.write(predicted.toString());
		writer.close();		
	}
	

 
	
	public void writeTree(String treeGraph) throws IOException, Exception
	{
		/*BufferedWriter writer = null;		
		writer = new BufferedWriter(new FileWriter(treeGraph));
		writer.write(treeModel.toString()  + "\n\n" + treeModel.graph());		
		writer.close();*/
		

		System.out.println("");
		System.out.println(treeModel.toString());
		System.out.println("\n\n");
		System.out.println(treeModel.graph());
		
		//pobieranie rezultatów
		//http://stackoverflow.com/questions/28031068/get-weka-classifier-results
		System.out.println("=====");
	}
	
	
	public String checkChocolate(Chocolates choc) throws Exception
	{
		String path = "./src/DecisionTrees/predict/chocolate.arff";
		String decision = "";
		String fileContent = 
				"@relation chocolates\n"
				+ "@attribute brand {alpenGold,wedel,goplana,heidi,milka,terravita,tesco,wawel}\n"
				+ "@attribute taste {mleczna,gorzka,biala}\n"
				+ "@attribute kind {zwykla,babelkowa,nadziewana,zowocami}\n"
				+ "@attribute additives {brak,karmelowa,orzechowa,wisniowa,malinowa,truskawkowa,fistaszki,kokosowym,pomaranczowa,jogurtowa,tiramisu,kasztanki,michalki,wisiniaChili}\n"
				+ "@attribute cocoaContent numeric\n"
				+ "@attribute amount numeric %g\n"
				+ "@attribute energy numeric\n"
				+ "@attribute fats numeric\n"
				+ "@attribute carbohydrates numeric\n"
				+ "@attribute protein numeric\n"
				+ "@attribute roughage numeric\n"
				+ "@attribute price numeric\n"
				+ "@attribute tolerancy {tak,nie}\n"
				+ "\n"
				+ "@data\n";
		
		String line = fileContent +","+ choc.getBrand() +","+ choc.getTaste() +","+ choc.getKind() +","+ choc.getAdditive() +","+
				choc.getCocoaContent() +","+ choc.getAmount() +","+ choc.getEnergy() +","+ choc.getFats() +","+
				choc.getCarbohydrates() +","+ choc.getProtein() +","+ choc.getPrice() + ",?";
		
		BufferedWriter writer = null;	
		writer = new BufferedWriter(new FileWriter(path));
		writer.write(line);
		writer.close();
		
		//BufferedReader reader = new BufferedReader(new FileReader(path));
		
		DataSource predictSource = new DataSource(path);
		Instances item = predictSource.getDataSet();
		item.setClassIndex(item.numAttributes() - 1);
		
		double label = treeModel.classifyInstance(item.instance(0));
		item.instance(0).setClassValue(label);
		
		//File file = new File(path);
		//file.delete();
		
		String itemLine = item.instance(0).toString();
		if(itemLine.contains("tak")) decision = "tak";
		else if (itemLine.contains("nie")) decision = "nie";
		
		
		System.out.println("=====");
		System.out.println(line);
        System.out.println("Odp: "+item.instance(0).stringValue(4));	
		
		return decision;
	}
}
