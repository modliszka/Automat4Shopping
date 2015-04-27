package slownik;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.TreeMap;

import shop.*;

public class Mapa {
	TreeMap<String,Obiekt_mapy> map = new TreeMap<String,Obiekt_mapy>();
	TreeMap<String,String> slowa=new TreeMap<String,String>();
	public static String[] words;
	public static int nr_slowa = 0;
	protected static String key;
	protected static String value;	
	public static int pl;//prawo+ lewo-
	public static int gd;//gora- dol+
	public static int o_poz=0;//odl.poz
	public static int o_pion=0;//
	public static boolean flaga=false;
	public static char[] x=new char[2];
	public static int y=0;
	public static boolean cosrobi=false;
	public static boolean CzekaNaOdp = false;
	public static TreeMap<String,Integer> wybor = new TreeMap<String,Integer>();

	
	public Mapa(){
		WypelnijMapeEkwipunku();

		try{
		StreamTokenizer plik=new StreamTokenizer(new FileReader("slownik.txt"));
		while(plik.nextToken()!=StreamTokenizer.TT_EOF){
			key=plik.sval;
			plik.nextToken();
			value=plik.sval;
			slowa.put(key, value);
		}}
		catch (FileNotFoundException e){
			System.out.println("Problem z wczytaniem pliku");
		}
		catch(IOException e){System.out.println("problem");}

		
		map.put("prawo",Prawo.getInstance());
		map.put("lewo",Lewo.getInstance());
		map.put("góra", Gora.getInstance());
		map.put("dó³", Dol.getInstance());
		
		}
	private void WypelnijMapeEkwipunku(){
		
	}
	public void coRobic(String klucz) throws InterruptedException{
		
		klucz=klucz.replace('.', ' ');
		klucz=klucz.replace(',', ' ');
		klucz=klucz.replace('!', ' ');
		klucz=klucz.replace('?', ' ');
		klucz=klucz.replace(';', ' ');
		words = klucz.split("\\s+");
		if(CzekaNaOdp==false){
			int a=0,b=0;
			while (nr_slowa < words.length){
				if(a==0 || b==0)try{
					if(a==0)a=Integer.parseInt(words[nr_slowa]);
					else b=Integer.parseInt(words[nr_slowa]);
					nr_slowa++;
				}catch(NumberFormatException e){}
				if ((nr_slowa < words.length)&&(slowa.containsKey(words[nr_slowa]))){
					map.get(slowa.get(words[nr_slowa])).metoda();}
				nr_slowa++;
	
			}
			if(x[0]=='v')o_pion=a*gd;
			if(x[0]=='h')o_poz=a*pl;
			if(x[1]=='v')o_pion=b*gd;
			if(x[1]=='h')o_poz=b*pl;
			if(flaga){Ruch.getInstance().rusz_sie(o_poz, o_pion);cosrobi=true;}
			//if(!cosrobi)Ramka.czat.setText("Mo¿esz powtórzyæ? Nie bardzo rozumiem :(");
			cosrobi=false;
			nr_slowa=0;	
			a=0;
			b=0;
			x[0]=0;
			x[1]=0;
			o_pion=0;
			o_poz=0;
			y=0;
			flaga=false;
		}

	}
	
}
