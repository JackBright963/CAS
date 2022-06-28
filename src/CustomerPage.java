import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerPage extends JFrame {

	private JPanel contentPane;

	public CustomerPage(final UserInfo currentUser) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setToolTipText("Search");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnViewList = new JButton("View the list of products");
		btnViewList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CustomerStockList(currentUser).setVisible(true);
			}
		});
		btnViewList.setBounds(125, 78, 184, 23);
		contentPane.add(btnViewList);
		
		JLabel lblChooseOption = new JLabel("Choose an option");
		lblChooseOption.setBounds(157, 11, 250, 29);
		contentPane.add(lblChooseOption);
		
		JButton btnSearch = new JButton("Search for product");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Search(currentUser).setVisible(true);
			}
		});
		btnSearch.setBounds(130, 124, 173, 23);
		contentPane.add(btnSearch);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setBounds(174, 194, 89, 23);
		contentPane.add(btnQuit);
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}	
		});
	}	
}
