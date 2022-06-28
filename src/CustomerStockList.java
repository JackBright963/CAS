import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CustomerStockList extends JFrame {

	private JPanel contentPane;

	public CustomerStockList(final UserInfo currentUser) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ArrayList<String> customerStockList = new ArrayList<String>();
		final ArrayList<StockItem> productList = new ArrayList<StockItem>();
		final ArrayList<StockItem> customerBasket = new ArrayList<StockItem>();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("./Stock.txt"));
			String line = null;
			StockItem product = null;
			while ((line = reader.readLine()) != null) {
				product = new StockItem(line);
				customerStockList.add(product.forSaleDetails());
				productList.add(product);
			}
		} catch (IOException f) {
            f.printStackTrace();
        }
		
		String[] customerStockItems = customerStockList.toArray(new String[]{});
		final JList list = new JList(customerStockItems);
		list.setBounds(10, 34, 414, 181);
		contentPane.add(list);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnBack.setBounds(295, 227, 89, 23);
		contentPane.add(btnBack);
		
		JButton btnAddBasket = new JButton("Add to Basket");
		btnAddBasket.setBounds(167, 226, 118, 23);
		contentPane.add(btnAddBasket);
		btnAddBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int itemIndex = list.getSelectedIndex();
				customerBasket.add(productList.get(itemIndex));
				
			}
		});
	
		
		JButton btnBasket = new JButton("");
		btnBasket.setBounds(387, 0, 37, 33);
		contentPane.add(btnBasket);
		btnBasket.setIcon(new ImageIcon("./basketIcon.png"));
		btnBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Basket(customerBasket, currentUser).setVisible(true);
				
			}
		});
	}
}
