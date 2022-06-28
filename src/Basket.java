import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;


public class Basket {

	public static JFrame frame;

	public Basket(final ArrayList<StockItem> fullBasket, final UserInfo currentUser) {
		frame = new JFrame();
		frame.setBounds(100, 100, 599, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		final DefaultListModel<String> chosenItems = new DefaultListModel<String>();
		double total = 0;
		if (fullBasket != null) {
			for(int x = 0; x < fullBasket.size(); x++) {
			chosenItems.addElement(fullBasket.get(x).forSaleDetails());
			double newCost = fullBasket.get(x).getPrice();
			total = total + newCost;
			}
		}		
		
		JTextArea totalCostArea = new JTextArea(Double.toString(total));
		totalCostArea.setBounds(451, 11, 103, 36);
		frame.getContentPane().add(totalCostArea);
		
		final JList<String> basketList = new JList<String>(chosenItems);
		basketList.setBounds(10, 51, 563, 152);
		frame.getContentPane().add(basketList);
		
		JLabel lblBasketLabel = new JLabel("Your shopping basket");
		lblBasketLabel.setBounds(56, 11, 140, 29);
		frame.getContentPane().add(lblBasketLabel);		
		
		JButton btnCancelAll = new JButton("Cancel all");
		btnCancelAll.setBounds(274, 227, 89, 23);
		frame.getContentPane().add(btnCancelAll);
		btnCancelAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileWriter writer;
				BufferedReader reader;
				LocalDate date = LocalDate.now();
				ArrayList<String> oldFile = new ArrayList<String>();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                try {
                	reader = new BufferedReader(new FileReader("./ActivityLog.txt"));
                	String line = null;
                	while ((line = reader.readLine()) != null) {
                		oldFile.add(line);
                	}
                	reader.close();
                	writer = new FileWriter("./ActivityLog.txt", false);                	
                    for(int x = 0; x < fullBasket.size(); x++) {
                    	writer.write(currentUser.getID() + ", " + currentUser.getPostcode() + ", " + fullBasket.get(x).getBarcode() + ", " + fullBasket.get(x).getPrice() + ", " + fullBasket.get(x).getQuantity() + ", " + "cancelled" + ", " + ", " + date.format(formatter) + "\n");
                    }
                    for(int i = 0; i < oldFile.size(); i++) {
                    	writer.write(oldFile.get(i) + "\n");
                    }
                    writer.close();
                } catch (IOException f) {
                f.printStackTrace();
                }
                chosenItems.clear();
				fullBasket.clear();
			}
		});
		
		JButton btnSaveForLater = new JButton("Save for later");
		btnSaveForLater.setBounds(373, 227, 110, 23);
		frame.getContentPane().add(btnSaveForLater);
		btnSaveForLater.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileWriter writer;
				BufferedReader reader;
				StockItem selectedProduct = null;
				LocalDate date = LocalDate.now();
				ArrayList<String> oldFile = new ArrayList<String>();
				int selectedIndex = basketList.getSelectedIndex();
				String oneSelectedItem = fullBasket.get(selectedIndex).allDetails();
				selectedProduct = new StockItem(oneSelectedItem);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				try {
                	reader = new BufferedReader(new FileReader("./ActivityLog.txt"));
                	String line = null;
                	while ((line = reader.readLine()) != null) {
                		oldFile.add(line);
                	}
                	reader.close();
                	writer = new FileWriter("./ActivityLog.txt", false);                	
                	writer.write(currentUser.getID() + ", " + currentUser.getPostcode() + ", " + selectedProduct.getBarcode() + ", " + selectedProduct.getPrice() + ", " + selectedProduct.getQuantity() + ", " + "saved" + ", " + ", " + date.format(formatter) + "\n");
                    for(int i = 0; i < oldFile.size(); i++) {
                    	writer.write(oldFile.get(i) + "\n");
                    }
                    writer.close();
                } catch (IOException f) {
                f.printStackTrace();
                }
                chosenItems.clear();
				fullBasket.clear();
			}
		});
			
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(493, 227, 80, 23);
		frame.getContentPane().add(btnBack);
		
		JButton btnPayPalPay = new JButton("Pay with PayPal");
		btnPayPalPay.setBounds(139, 227, 125, 23);
		frame.getContentPane().add(btnPayPalPay);
		btnPayPalPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PayPal(fullBasket, currentUser).setVisible(true);
			}
		});
		
		JButton btnCardPay = new JButton("Pay with card");
		btnCardPay.setBounds(10, 227, 119, 23);
		frame.getContentPane().add(btnCardPay);
		btnCardPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreditCard(fullBasket, currentUser).setVisible(true);
			}
		});
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTotal.setBounds(395, 18, 46, 14);
		frame.getContentPane().add(lblTotal);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
	}

	public void setVisible(boolean b) {
		frame.setVisible(true);
		
	}
}
