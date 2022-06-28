
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.Font;

public class MainPage extends JFrame {
	

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 406, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		List<String> usernameList = new ArrayList<String>();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("./UserAccounts.txt"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				final String[] usernames = line.split(", ");
				usernameList.add(usernames[1]);
			}
		} catch (IOException f) {
            f.printStackTrace();
        }
		
		String[] lineArray = usernameList.toArray(new String[]{});
		final JComboBox<?> cbUser = new JComboBox<Object>(lineArray);
		cbUser.setBounds(10, 101, 141, 22);
		contentPane.add(cbUser);
		
		JLabel lblWelcomeLabel = new JLabel("Welcome to Computer Accessories Shop!");
		lblWelcomeLabel.setFont(new Font("Tw Cen MT", Font.BOLD, 17));
		lblWelcomeLabel.setBounds(10, 11, 370, 32);
		contentPane.add(lblWelcomeLabel);
		
		JButton btnLogIn = new JButton("Log in");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BufferedReader reader;
				UserInfo currentUser = null;
				try {
					reader = new BufferedReader(new FileReader("./UserAccounts.txt"));
					String line = null;
					UserInfo info = null;
					while ((line = reader.readLine()) != null) {
						info = new UserInfo(line);
						if (info.usernameEquals(cbUser.getSelectedItem().toString())) {
							currentUser = info;
						}
					}
				} catch (IOException f) {
	                f.printStackTrace();
				}
				if(currentUser.roleEquals("admin")) {
					new AdminPage().setVisible(true);
				}
				else {
					new CustomerPage(currentUser).setVisible(true);
				}
			}
		});
		btnLogIn.setBounds(222, 101, 89, 23);
		contentPane.add(btnLogIn);
		
		JLabel lblPick = new JLabel("Pick a user to log in with");
		lblPick.setBounds(10, 68, 192, 22);
		contentPane.add(lblPick);
		
		JButton btnExit = new JButton("Exit app");
		btnExit.setBounds(222, 149, 89, 23);
		contentPane.add(btnExit);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}	
		});
	}
}
