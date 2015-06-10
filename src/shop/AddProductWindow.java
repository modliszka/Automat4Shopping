package shop;


import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.text.NumberFormatter;



public class AddProductWindow extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	public static int H=400, V=300;
	private static JTextArea characteristics;
	private static JButton addProd;
	private static JComboBox<String> list;
	private static Label lab1;
	private static JFormattedTextField amount;
	
	public AddProductWindow(){
		super("Add Product Window");
		setSize(V, H); 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		
		String[] listOfProducts = {"mleko","chleb","mas³o","herbata"};
		list=new JComboBox<String>(listOfProducts);
		list.setSelectedIndex(0);
		list.setBounds(25, 25, 250, 25);
		add(list);
		
		lab1=new Label();
		lab1.setText("Ilosc:");
		lab1.setBounds(25, 75, 100, 25);
		add(lab1);
		
		NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(1000);
	    // If you want the value to be committed on each keystroke instead of focus lost
	    formatter.setCommitsOnValidEdit(true);
	    amount = new JFormattedTextField(formatter);
	    amount.setBounds(125, 75, 150, 25);
	    add(amount);
		
		//przycisk do okna z tworzeniem listy zakupow
		addProd = new JButton("Dodaj do listy");
		addProd.setVerticalTextPosition(AbstractButton.CENTER);
		addProd.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
		addProd.setBounds(25, 325, 250, 25);
		addProd.setEnabled(true);
		addProd.addActionListener(this);
		add(addProd);
		
		//Lista produktow po prawej stronie
		characteristics=new JTextArea();
		characteristics.setBounds(25, 125, 250, 100);
		characteristics.setLineWrap(true);
		characteristics.setWrapStyleWord(true);
		add(characteristics);
		

		setVisible(true);
		
		
	}
	/*
	 gdzies w tym pliku 
	 wyskakiwanie dod okienka do wprowadzania tych danych:
	 Board.shop[0][0]=new Place("R","water","beverages");
		Product p=new Product();
		p.nazwa="Woda mineralna ABC";
		p.rodzaj="Woda";//jak 3 linie wyzej
		p.wielkosc=1.5;
		p.jednostka="l";
		p.ilosc=0;
		p.glRozroznik="gazowana";
		p.dodRozroznik="";
		Board.shop[0][0].add(p);
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try{
			MainWindow.updateProductList((String)list.getSelectedItem(), Integer.parseInt( amount.getText()), characteristics.getText());
		}
		catch(NumberFormatException ex){
			
		}
		
	}
	
}