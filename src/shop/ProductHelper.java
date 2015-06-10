package shop;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import frameSection.Chocolate;
import frameSection.Juice;
import frameSection.Tea;
import ProductInterface.ProductToList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ProductHelper {

	private JFrame frmProdhelper;
	private JTextField textField;
	private JTextArea textArea;
	private DefaultListModel<String> listModel;
	private JList productKindList, productsList;
	private String[] kindsList;

	/**
	 * Launch the application.
	 */
	public static void function() {
	
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					ProductHelper window = new ProductHelper();
					window.frmProdhelper.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

	/**
	 * Create the application.
	 */
	public ProductHelper() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProdhelper = new JFrame();
		frmProdhelper.setTitle("prodHelper");
		frmProdhelper.setBounds(100, 100, 300, 400);
		frmProdhelper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProdhelper.getContentPane().setLayout(null);
		
		
		// tutaj lista dostępnych produktów
		final String[] ar = {"Czekolada","Sok/Napój","Herbata"};//{"Mleko", "Bułka", "Lody"};
		
		listModel = new DefaultListModel();		
		productKindList = new JList(listModel);		
		productKindList.setBounds(10, 112, 98, 200);	
		
		productsList = new JList(ar);		
		productsList.setBounds(10, 11, 98, 100);		
		productsList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				listModel.removeAllElements();
				switch(productsList.getSelectedIndex()){
					case 0:
						Chocolate c = new Chocolate();
						kindsList = c.getAllKinds();
						for(String s: kindsList)
							listModel.addElement(s);
						break;
					case 1:
						Juice j = new Juice();
						kindsList = j.getAllKinds();
						for(String s: kindsList)
							listModel.addElement(s);
						break;
					case 2:
						Tea t = new Tea();
						kindsList = t.getAllKinds();
						for(String s: kindsList)
							listModel.addElement(s);
						break;
				}
			}			
		});
		
		frmProdhelper.getContentPane().add(productsList);
		frmProdhelper.getContentPane().add(productKindList);

		
		/*JLabel lblIlo = new JLabel("Ilosc");
		lblIlo.setBounds(118, 44, 46, 14);
		frmProdhelper.getContentPane().add(lblIlo);
		
		JLabel lblDodatkoweCechy = new JLabel("Dodatkowe cechy:");
		lblDodatkoweCechy.setBounds(129, 97, 105, 14);
		frmProdhelper.getContentPane().add(lblDodatkoweCechy);
		
		textField = new JTextField();
		textField.setBounds(164, 41, 86, 20);
		frmProdhelper.getContentPane().add(textField);
		textField.setColumns(10);*/
		
		JButton btnNewButton = new JButton("Dodaj do listy produktów");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    int indexPr = productsList.getSelectedIndex();
                int indexKind = productKindList.getSelectedIndex();
                
                productsList.setSelectedIndex(indexPr);
                productKindList.setSelectedIndex(indexKind);
                
				ProductToList product = new ProductToList();
				product.name = ar[indexPr];
				product.describe = kindsList[indexKind];
				//product.count = textField.getText();
				
				Shop.myProductsList.add(product);
				
				if(!Shop.myProductsList.isEmpty()){   
					MainWindow.productsList.setText("");
					
					for(int i = 0; i<Shop.myProductsList.size(); i++){
						String text = (Shop.myProductsList.get(i)).name;
						/*if((Shop.myProductsList.get(i)).count != ""){	//nie działa
							text += " x "+(Shop.myProductsList.get(i)).count;
						}*/
						text += " "+(Shop.myProductsList.get(i)).describe;
						
						MainWindow.productsList.append(text+"\n");
					}
				}
				
				frmProdhelper.dispose();
			}
		});
		btnNewButton.setBounds(109, 223, 155, 23);
		frmProdhelper.getContentPane().add(btnNewButton);
				
	}
}
