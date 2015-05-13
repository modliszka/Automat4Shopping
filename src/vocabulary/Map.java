package vocabulary;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.TreeMap;

import shop.*;

public class Map {
	TreeMap<String,Map_object> map = new TreeMap<String,Map_object>();
	TreeMap<String,String> custom_words=new TreeMap<String,String>();
	public static String[] words;
	public static int word_nr = 0;
	protected static String key;
	protected static String value;	
	public static int rl;//right+ lewo-
	public static int ud;//gora- dol+
	public static int o_hor=0;//odl.hor
	public static int o_vert=0;//
	public static boolean flag=false;
	public static char[] x=new char[2];
	public static int y=0;
	public static boolean doesomething=false;
	public static boolean WaitingForResponse = false;
	public static TreeMap<String,Integer> wybor = new TreeMap<String,Integer>();

	
	public Map(){
		FillEquipmentMap();

		try{
		StreamTokenizer file=new StreamTokenizer(new FileReader("vocabulary.txt"));
		while(file.nextToken()!=StreamTokenizer.TT_EOF){
			key=file.sval;
			file.nextToken();
			value=file.sval;
			custom_words.put(key, value);
		}}
		catch (FileNotFoundException e){
			System.out.println("Error loading file");
		}
		catch(IOException e){System.out.println("Error");}

		
		map.put("right",Right.getInstance());
		map.put("left",Left.getInstance());
		map.put("up", Up.getInstance());
		map.put("down", Down.getInstance());
		
		}
	private void FillEquipmentMap(){
		
	}
	public void whatToDo(String key) throws InterruptedException{
		
		key=key.replace('.', ' ');
		key=key.replace(',', ' ');
		key=key.replace('!', ' ');
		key=key.replace('?', ' ');
		key=key.replace(';', ' ');
		words = key.split("\\s+");
		if(WaitingForResponse==false){
			int a=0,b=0;
			while (word_nr < words.length){
				if(a==0 || b==0)try{
					if(a==0)a=Integer.parseInt(words[word_nr]);
					else b=Integer.parseInt(words[word_nr]);
					word_nr++;
				}catch(NumberFormatException e){}
				if ((word_nr < words.length)&&(custom_words.containsKey(words[word_nr]))){
					map.get(custom_words.get(words[word_nr])).method();}
				word_nr++;
	
			}
			if(x[0]=='v')o_vert=a*ud;
			if(x[0]=='h')o_hor=a*rl;
			if(x[1]=='v')o_vert=b*ud;
			if(x[1]=='h')o_hor=b*rl;
			if(flag){Movement.getInstance().move_yourself(o_hor, o_vert);doesomething=true;}
			//if(!doesomething)Custom_frame.czat.setText("Mo¿esz powtórzyæ? Nie bardzo rozumiem :(");
			doesomething=false;
			word_nr=0;	
			a=0;
			b=0;
			x[0]=0;
			x[1]=0;
			o_vert=0;
			o_hor=0;
			y=0;
			flag=false;
		}

	}
	
}
