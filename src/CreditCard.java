import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CreditCard extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCardNumber;
	private JButton btnBack;
	private JTextField textFieldCVC;

	public CreditCard(final ArrayList<StockItem> fullBasket, final UserInfo currentUser) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 319, 172);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldCardNumber = new JTextField();
		textFieldCardNumber.setBounds(147, 11, 146, 20);
		contentPane.add(textFieldCardNumber);
		textFieldCardNumber.setColumns(10);
		textFieldCardNumber.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent EVT) {
					if (EVT.getKeyChar() >= '0' && EVT.getKeyChar() <= '9' || EVT.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
						textFieldCardNumber.setEditable(true);
					} else {
						textFieldCardNumber.setEditable(false);	
					}
					if(textFieldCardNumber.getText().length() == 16) {
						textFieldCardNumber.setEditable(false);
					}
					
				}
			}
		);
		
		JLabel lblCardNumber = new JLabel("Credit Card Number");
		lblCardNumber.setBounds(10, 14, 127, 14);
		contentPane.add(lblCardNumber);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(166, 99, 89, 23);
		contentPane.add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener() {
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
                    	writer.write(currentUser.getID() + ", " + currentUser.getPostcode() + ", " + fullBasket.get(x).getBarcode() + ", " + fullBasket.get(x).getPrice() + ", " + fullBasket.get(x).getQuantity() + ", " + "purchased" + ", " + "Credit Card" + "," + date.format(formatter) + "\n");
                    }
                    for(int i = 0; i < oldFile.size(); i++) {
                    	writer.write(oldFile.get(i) + "\n");
                    }
                    writer.close();
                } catch (IOException f) {
                f.printStackTrace();
                }
				System.exit(0);
            }
	});
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnBack.setBounds(77, 99, 79, 23);
		contentPane.add(btnBack);
		
		JLabel lblCVC = new JLabel("CVC code");
		lblCVC.setBounds(70, 51, 67, 14);
		contentPane.add(lblCVC);
		
		textFieldCVC = new JTextField();
		textFieldCVC.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent EVT) {
					if (EVT.getKeyChar() >= '0' && EVT.getKeyChar() <= '9' || EVT.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
						textFieldCVC.setEditable(true);
					} else {
						textFieldCVC.setEditable(false);	
					}
					if(textFieldCVC.getText().length() == 3) {
						textFieldCVC.setEditable(false);
					}
					
				}
			}
		);
		textFieldCVC.setColumns(10);
		textFieldCVC.setBounds(147, 49, 67, 17);
		contentPane.add(textFieldCVC);
	}

}
