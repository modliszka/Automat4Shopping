package shop;

import java.awt.EventQueue;

import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import frameSection.Chocolate;
import frameSection.Juice;
import frameSection.Pasta;
import frameSection.Tea;
import frameSection.Water;
import frameSection.Yoghurts;
import ProductInterface.ProductToList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AddProductWindow extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private DefaultListModel<String> listModel, listModelProdAttr;
	private JList<String> productKindList, productsList, productAttrList;
	private String[] kindsList, attrList, products= {"Czekolada","Sok/Napój","Herbata","Makaron","Woda","Jogurt"};
	public static int V=500, H=400;

	public AddProductWindow() {
		super("Add Product Window");

		setSize(V, H); 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		setFocusable(false);
				
		listModel = new DefaultListModel<String>();		
		productKindList = new JList(listModel);		
		//productKindList.setBounds(110, 11, 98, 100);	
		JScrollPane productKindListScroll = new JScrollPane(productKindList);
		productKindListScroll.setBounds(170, 10, 150, 200);	
		getContentPane().add(productKindListScroll);
		

		listModelProdAttr = new DefaultListModel<String>();		
		productAttrList = new JList(listModelProdAttr);	
		//productAttrList.setBounds(10, 112, 98, 200);	
		JScrollPane productAttrListScroll = new JScrollPane(productAttrList);
		productAttrListScroll.setBounds(340, 10, 150, 200);
		getContentPane().add(productAttrListScroll);
		
		productsList = new JList(products);		
		//productsList.setBounds(10, 11, 98, 100);		
		productsList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				listModel.removeAllElements();
				listModelProdAttr.removeAllElements();
				
				switch(productsList.getSelectedIndex()){
					case 0:
						Chocolate c = new Chocolate();
						kindsList = c.getAllKinds();
						for(String s: kindsList)
							listModel.addElement(s);
						attrList = c.getAllTastes();
						for(String s: attrList)
							listModelProdAttr.addElement(s);
						break;
					case 1:
						Juice j = new Juice();
						kindsList = j.getAllKinds();
						for(String s: kindsList)
							listModel.addElement(s);
						attrList = j.getAllTastes();
						for(String s: attrList)
							listModelProdAttr.addElement(s);
						break;
					case 2:
						Tea t = new Tea();
						kindsList = t.getAllKinds();
						for(String s: kindsList)
							listModel.addElement(s);
						attrList = t.getAllTastes();
						for(String s: attrList)
							listModelProdAttr.addElement(s);
						break;
					case 3:
						Pasta p = new Pasta();
						kindsList = p.getAllKinds();
						for(String s: kindsList)
							listModel.addElement(s);
						attrList = p.getAllflourKinds();
						for(String s: attrList)
							listModelProdAttr.addElement(s);
						break;
					case 4:
						Water w = new Water();
						kindsList = w.getAllKinds();
						for(String s: kindsList)
							listModel.addElement(s);
						attrList = w.getAllTastes();
						for(String s: attrList)
							listModelProdAttr.addElement(s);
						break;
					case 5:
						Yoghurts y = new Yoghurts();
						kindsList = y.getAllKinds();
						for(String s: kindsList)
							listModel.addElement(s);
						attrList = y.getAllTastes();
						for(String s: attrList)
							listModelProdAttr.addElement(s);
						break;
				}
			}			
		});
		JScrollPane productsListScroll = new JScrollPane(productsList);
		productsListScroll.setBounds(10, 10, 150, 200);
		getContentPane().add(productsListScroll);
//		add(productsList);
//		add(productKindList);
//		add(productAttrList);
			
		
		/*JLabel lblIlo = new JLabel("Ilosc");
		lblIlo.setBounds(118, 44, 46, 14);
		add(lblIlo);
		
		JLabel lblDodatkoweCechy = new JLabel("Dodatkowe cechy:");
		lblDodatkoweCechy.setBounds(129, 97, 105, 14);
		add(lblDodatkoweCechy);
		
		textField = new JTextField();
		textField.setBounds(164, 41, 86, 20);
		add(textField);
		textField.setColumns(10);*/
		
		
		
		//przycisk do okna z tworzeniem listy zakupow
		JButton addProd = new JButton("Dodaj do listy produktów");
		addProd.setVerticalTextPosition(AbstractButton.CENTER);
		addProd.setHorizontalTextPosition(AbstractButton.LEADING);
		addProd.addActionListener(this);
		addProd.setBounds(109, 223, 200, 23);
		add(addProd);
				
		setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		int indexPr = productsList.getSelectedIndex();
        int indexKind = productKindList.getSelectedIndex();
        int indexAttr = productAttrList.getSelectedIndex();
        
        productsList.setSelectedIndex(indexPr);
        productKindList.setSelectedIndex(indexKind);
        productAttrList.setSelectedIndex(indexAttr);
        
		ProductToList product = new ProductToList();
		product.name = products[indexPr];
		product.describe = kindsList[indexKind];
		product.attribute = attrList[indexAttr];
		//product.count = textField.getText();
		
		MainWindow.myProductsList.add(product);
		
		if(!MainWindow.myProductsList.isEmpty()){   
			MainWindow.productsShoppingList.setText("");
			
			for(int i = 0; i<MainWindow.myProductsList.size(); i++){
				String text = (MainWindow.myProductsList.get(i)).name;
				/*if((Shop.myProductsList.get(i)).count != ""){	//nie działa
					text += " x "+(Shop.myProductsList.get(i)).count;
				}*/
				text += " "+(MainWindow.myProductsList.get(i)).describe;
				text += " "+(MainWindow.myProductsList.get(i)).attribute;
				
				MainWindow.productsShoppingList.append(text+"\n");
			}
		}
		dispose();
		
	}
}
