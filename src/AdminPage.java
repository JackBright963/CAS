import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminPage {

	private JFrame frame;

	public AdminPage() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnViewList = new JButton("View the list of products");
		btnViewList.setBounds(118, 99, 187, 23);
		frame.getContentPane().add(btnViewList);
		btnViewList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminStockList().setVisible(true);
			}
		});
		
		JButton btnAdd = new JButton("Add a product");
		btnAdd.setBounds(157, 152, 114, 23);
		frame.getContentPane().add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddProduct().setVisible(true);
			}
		});
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setBounds(169, 227, 89, 23);
		frame.getContentPane().add(btnQuit);
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}	
		});
		
		JLabel lblWelcome = new JLabel("Welcome back Admin");
		lblWelcome.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
		lblWelcome.setBounds(118, 32, 210, 30);
		frame.getContentPane().add(lblWelcome);
	}

	public void setVisible(boolean b) {
		frame.setVisible(true);
		
	}
}
