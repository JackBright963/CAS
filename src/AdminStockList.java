import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.UIManager;

import java.awt.Window.Type;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminStockList extends JFrame {

	private JPanel contentPane;


	public AdminStockList() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAdd = new JButton("Add a new product");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddProduct second = new AddProduct();
				second.setVisible(true);
			}
			
		});
		btnAdd.setBounds(33, 309, 148, 29);
		contentPane.add(btnAdd);
		
		
		
		ArrayList<String> stockList = new ArrayList<String>();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("./Stock.txt"));
			String line = null;
			StockItem product = null;
			while ((line = reader.readLine()) != null) {
				product = new StockItem(line);
				stockList.add(product.allDetails());
			}
		} catch (IOException f) {
            f.printStackTrace();
        }
		
		String[] stockItems = stockList.toArray(new String[]{});
		final JList<?> list = new JList<Object>(stockItems);
		list.setBounds(33, 43, 416, 255);
		getContentPane().add(list);	
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(360, 309, 89, 29);
		contentPane.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
	}
}
