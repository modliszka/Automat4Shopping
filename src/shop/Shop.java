package shop;

import java.awt.EventQueue;

public class Shop{
	public static void main(String[] args){
	      EventQueue.invokeLater(new Runnable() {
	          @Override
	          public void run() {
	              new Ramka();
	          }
	      });
	}
}