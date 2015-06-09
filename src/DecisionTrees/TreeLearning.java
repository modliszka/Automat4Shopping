package DecisionTrees;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.*;
import weka.classifiers.trees.J48;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import frameSection.Chocolates;
import frameSection.Juices;
import frameSection.Teas;

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
		//System.out.println(treeModel.graph());
		
		//pobieranie rezultatów
		//http://stackoverflow.com/questions/28031068/get-weka-classifier-results
		System.out.println("=====");
	}
	
	
	public String checkChocolate(Chocolates choc) throws Exception
	{
		String path = "./src/DecisionTrees/predict/chocolate.arff";
		String fileContent = 
				"@relation chocolates\n"
				+ "@attribute brand {alpenGold,wedel,goplana,heidi,milka,terravita,tesco,wawel}\n"
				+ "@attribute taste {mleczna,gorzka,biala}\n"
				+ "@attribute kind {zwykla,babelkowa,nadziewana,zowocami}\n"
				+ "@attribute additives {brak,karmelowa,orzechowa,wisniowa,malinowa,truskawkowa,fistaszki,kokosowym,pomaranczowa,jogurtowa,tiramisu,kasztanki,michalki,wisiniaChili}\n"
				+ "@attribute cocoaContent numeric\n"
				+ "@attribute amount numeric\n"
				+ "@attribute energy numeric\n"
				+ "@attribute fats numeric\n"
				+ "@attribute carbohydrates numeric\n"
				+ "@attribute protein numeric\n"
				+ "@attribute roughage numeric\n"
				+ "@attribute price numeric\n"
				+ "@attribute tolerancy {tak,nie}\n"
				+ "\n"
				+ "@data\n";
		
		String br = choc.getBrand()=="" ? "?" : choc.getBrand();
		String ta = choc.getTaste()=="" ? "?" : choc.getTaste();
		String ki = choc.getKind()=="" ? "?" : choc.getKind();
		String ad = choc.getAdditive()=="" ? "?" : choc.getAdditive();
		String co = choc.getCocoaContent()==0 ? "?" : ""+choc.getCocoaContent();
		String am = choc.getAmount()==-1 ? "?" : ""+choc.getAmount();
		String en = choc.getEnergy()==-1 ? "?" : ""+choc.getEnergy();
		String fa = choc.getFats()==-1 ? "?" : ""+choc.getFats();
		String ca = choc.getCarbohydrates()==-1 ? "?" : ""+choc.getCarbohydrates();
		String prot = choc.getProtein()==-1 ? "?" : ""+choc.getProtein();
		String ro = choc.getRoughage()==-1 ? "?" : ""+choc.getRoughage();
		String pri = choc.getPrice()==-1 ? "?" : ""+choc.getPrice();
		
		String line = fileContent + br +","+ ta +","+ ki +","+ ad +","+co +","+ am +","+ en +","+ fa +","+
				ca +","+ prot +","+ ro + ","+ pri +",?\n";		
				
		return Predict(path,line);
	}
	
	public String checkTea(Teas tea) throws Exception
	{
		String path = "./src/DecisionTrees/predict/tea.arff";
		String fileContent = 
				"@relation teas\n"
				+ "@attribute brand     {lipton,tesco,tetley,herbapol,saga,dilmah,minutka,bigActive}\n"
				+ "@ATTRIBUTE kind		{czarna,zielona,biala,ziolowa}\n"
				+ "@ATTRIBUTE package	{torebki,sypana,granulowana}\n"
				+ "@ATTRIBUTE taste	    {zwykly,pomarancza,pokrzywy,rumianek,malinowy,melisa,mieta}\n"
				+ "@attribute amount    numeric\n"
				+ "@attribute price     numeric\n"
				+ "@attribute tolerancy {tak,nie}\n"
				+ "\n"
				+ "@data\n";
		
		
		String br = tea.getBrand()=="" ? "?" : tea.getBrand();
		String ki = tea.getKind()=="" ? "?" : tea.getKind();
		String pa = tea.getPackage()=="" ? "?" : tea.getPackage();
		String ta = tea.getTaste()=="" ? "?" : tea.getTaste();
		String am = tea.getAmount()==-1 ? "?" : ""+tea.getAmount();
		String pri = tea.getPrice()==-1 ? "?" : ""+tea.getPrice();
		
		String line = fileContent + br +","+ ki +","+ pa +","+ ta+","+ am +","+ pri +",?\n";	
		
		return Predict(path,line);
	}
	
	
	public String checkJuice(Juices juice) throws Exception
	{
		String path = "./src/DecisionTrees/predict/tea.arff";
		String fileContent = 
				"@relation teas\n"
				+ "@attribute brand     {cappy,caprio,dawton,drWitt,fortuna,garden,hortex,kubus,leon,tymbark,tesco,vitellia}\n"
				+ "@ATTRIBUTE package   {karton,butelkaPlast,butelkaSzklana}\n"
				+ "@ATTRIBUTE kind {sok,napoj,nektar}\n"
				+ "@ATTRIBUTE taste {multiwitamina,ananas,mandarynka,pomaranczy,truskawkowy,jablkowy,porzeczkowy, cytrynowy,brzoskwiniowy}\n"
				+ "@attribute vitamin   {brak,a,b3,c,a_c,a_b3_c,e,a_e}\n"
				+ "@attribute amount    numeric\n"
				+ "@attribute energy    numeric\n"
				+ "@attribute carbohydrates    numeric\n"
				+ "@attribute price     numeric\n"
				+ "@attribute tolerancy {tak,nie}\n"
				+ "\n"
				+ "@data\n";

		
		String br = juice.getBrand()=="" ? "?" : juice.getBrand();
		String pa = juice.getWrapping()=="" ? "?" : juice.getWrapping();
		String ki = juice.getKind()=="" ? "?" : juice.getKind();
		String ta = juice.getTaste()=="" ? "?" : juice.getTaste();
		String vi = juice.getVitamin()=="" ? "?" : juice.getVitamin();
		String en = juice.getEnergy()==-1 ? "?" : ""+juice.getEnergy();
		String ca = juice.getCarbohydrates()==-1 ? "?" : ""+juice.getCarbohydrates();
		String am = juice.getAmount()==-1 ? "?" : ""+juice.getAmount();
		String pri = juice.getPrice()==-1 ? "?" : ""+juice.getPrice();
		
		String line = fileContent + br +","+ pa +","+ ki +","+ ta+","+vi+","+ am +","+ en +","+ ca +","+ pri +",?\n";	
		
		return Predict(path,line);
	}

	private String Predict(String path,String line) throws Exception {
		BufferedWriter writer = null;	
		writer = new BufferedWriter(new FileWriter(path));
		writer.write(line);
		writer.close();
		//BufferedReader reader = new BufferedReader(new FileReader(path));
		
		//DataSource predictSource = new DataSource(path);
		//Instances item = predictSource.getDataSet();
		String decision = "";
		
		BufferedReader reader = new BufferedReader(new FileReader(path));
		Instances item = new Instances(reader);
		item.setClassIndex(item.numAttributes() - 1);		
		double clsLabel = treeModel.classifyInstance(item.instance(0));
		item.instance(0).setClassValue(clsLabel);
		reader.close();
		item.setClassIndex(item.numAttributes() - 1);
		
		double label = treeModel.classifyInstance(item.instance(0));
		item.instance(0).setClassValue(label);
		
		//File file = new File(path);
		//file.delete();
		
		String itemLine = item.instance(0).toString();
		if(itemLine.contains("tak")) decision = "tak";
		else if (itemLine.contains("nie")) decision = "nie";
		
		
		System.out.println("=====");
		//System.out.println(line);
        System.out.println("Odp: "+item.instance(0).toString());	
		
        return decision;
	}
}
