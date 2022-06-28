import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.AbstractListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Search extends JFrame {
	private JTextField textFieldBrandSearch;
	private JTextField textFieldLayoutSearch;
	
	public Search(final UserInfo currentUser) {
		getContentPane().setLayout(null);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(417, 11, 89, 23);
		getContentPane().add(btnSearch);
		
		textFieldBrandSearch = new JTextField();
		textFieldBrandSearch.setBounds(10, 12, 190, 22);
		getContentPane().add(textFieldBrandSearch);
		textFieldBrandSearch.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(417, 342, 89, 23);
		getContentPane().add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		textFieldLayoutSearch = new JTextField();
		textFieldLayoutSearch.setBounds(217, 12, 190, 22);
		getContentPane().add(textFieldLayoutSearch);
		textFieldLayoutSearch.setColumns(10);
		
		
		textFieldLayoutSearch.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent EVT) {
					if (EVT.getKeyChar() >= '0' && EVT.getKeyChar() <= '9' || EVT.getKeyChar() == '.') {
						textFieldLayoutSearch.setEditable(false);
					} else {
						textFieldLayoutSearch.setEditable(true);	
					}
				}
			}
		);
		
		final ArrayList<StockItem> customerBasket = new ArrayList<StockItem>();		
		final ArrayList<StockItem> productList = new ArrayList<StockItem>();
		final DefaultListModel searchListModel = new DefaultListModel();
		btnSearch.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				searchListModel.clear();
				productList.clear();
				BufferedReader reader;
				try {
					reader = new BufferedReader(new FileReader("./Stock.txt"));
					String line = null;
					int resultsCount = 0;
					StockItem item = null;
					 while ((line = reader.readLine()) != null) {
						 item = new StockItem(line);
						 if ((item.brandEquals(textFieldBrandSearch.getText()) && textFieldLayoutSearch.getText().equals("")) || (item.brandEquals(textFieldBrandSearch.getText()) && item.layoutEquals(textFieldLayoutSearch.getText())) || (textFieldBrandSearch.getText().equals("") && item.layoutEquals(textFieldLayoutSearch.getText()))) {
							 searchListModel.addElement(item.forSaleDetails());
							 productList.add(item);
							 resultsCount = resultsCount + 1;
						 } 
					 }
					 if (resultsCount == 0) {
						 searchListModel.addElement("No items found.");
					 }
				} catch (IOException f) {
                f.printStackTrace();
				}
			}
		});
	
		final JList resultList = new JList(searchListModel);
		resultList.setBounds(20, 45, 496, 287);
		getContentPane().add(resultList);
		
		JButton btnAddToBasket = new JButton("Add to basket");
		btnAddToBasket.setBounds(195, 342, 116, 23);
		getContentPane().add(btnAddToBasket);
		btnAddToBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int itemIndex = resultList.getSelectedIndex();
				customerBasket.add(productList.get(itemIndex));
			}
		});
		
		JButton btnBasketIcon = new JButton("");
		btnBasketIcon.setBounds(30, 336, 48, 29);
		getContentPane().add(btnBasketIcon);
		btnBasketIcon.setIcon(new ImageIcon("./basketIcon.png"));
		btnBasketIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Basket(customerBasket, currentUser).setVisible(true);
			}
		});
		
	}
}
