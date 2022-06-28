import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class PayPal extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldEmail;
	
	public static boolean valEmail(String input) {
		String emailRegex =  "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
		Pattern emailPat = Pattern.compile(emailRegex,Pattern.CASE_INSENSITIVE);
		Matcher matcher = emailPat.matcher(input);
		return matcher.find();
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayPal frame = new PayPal(null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PayPal(final ArrayList<StockItem> fullBasket, final UserInfo currentUser) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 328, 146);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(67, 11, 202, 20);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 14, 46, 14);
		contentPane.add(lblEmail);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = textFieldEmail.getText();
                boolean test = valEmail(name);
                if(test == true) {  		
					JOptionPane.showMessageDialog(null, "Your payment has been succssesfully processed.");
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
	                    	writer.write(currentUser.getID() + ", " + currentUser.getPostcode() + ", " + fullBasket.get(x).getBarcode() + ", " + fullBasket.get(x).getPrice() + ", " + fullBasket.get(x).getQuantity() + ", " + "purchased" + ", " + "PayPal" + "," + date.format(formatter) + "\n");
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
				else {
					JOptionPane.showMessageDialog(null, "Invalid email");
				}
            }
	});
		btnSubmit.setBounds(180, 42, 89, 23);
		contentPane.add(btnSubmit);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnBack.setBounds(67, 42, 72, 23);
		contentPane.add(btnBack);
	}
}
