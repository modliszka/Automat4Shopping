package shop;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import ProductInterface.ProductToList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

public class ProductHelper {

	private JFrame frmProdhelper;
	private JTextField textField;
	private JTextArea textArea;

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
		frmProdhelper.setBounds(100, 100, 276, 295);
		frmProdhelper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProdhelper.getContentPane().setLayout(null);
		
		
		// tutaj lista dost�pnych produkt�w
		final String[] ar = {"Czekolada","Sok/Napój","Herbata"};//{"Mleko", "Bułka", "Lody"};
		final JList list = new JList(ar);
		
		list.setBounds(10, 11, 98, 200);
		
		frmProdhelper.getContentPane().add(list);
		
		JLabel lblIlo = new JLabel("Ilosc");
		lblIlo.setBounds(118, 44, 46, 14);
		frmProdhelper.getContentPane().add(lblIlo);
		
		JLabel lblDodatkoweCechy = new JLabel("Dodatkowe cechy:");
		lblDodatkoweCechy.setBounds(129, 97, 105, 14);
		frmProdhelper.getContentPane().add(lblDodatkoweCechy);
		
		textField = new JTextField();
		textField.setBounds(164, 41, 86, 20);
		frmProdhelper.getContentPane().add(textField);
		textField.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setBounds(118, 122, 132, 90);
		frmProdhelper.getContentPane().add(textArea);
		
		JButton btnNewButton = new JButton("Dodaj do listy produktów");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    int index = list.getSelectedIndex();
                list.setSelectedIndex(index);
                
				ProductToList product = new ProductToList();
				product.name = ar[index];
				product.count = textField.getText();
				product.describe = textArea.getText();
				/*
				System.out.print(product.name);
				System.out.print(product.count);
				System.out.print(product.describe);
				*/
				Shop.myProductsList.add(product);
				
				if(!Shop.myProductsList.isEmpty())
				{   MainWindow.productsList.setText("");
					for(int i = 0; i<Shop.myProductsList.size(); i++)
					{
						MainWindow.productsList.append((Shop.myProductsList.get(i)).name+" X "+(Shop.myProductsList.get(i)).count+"\n");
					}
				}
				
				frmProdhelper.dispose();
			}
		});
		btnNewButton.setBounds(55, 223, 155, 23);
		frmProdhelper.getContentPane().add(btnNewButton);
				
	}
}
