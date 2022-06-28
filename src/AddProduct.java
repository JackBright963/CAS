import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AddProduct extends JFrame {
	private JPanel contentPane;
	private JTextField textFieldBarcode;
	private JTextField textFieldBrand;
	private JTextField textFieldColour;
	private JTextField textFieldConnectivity;
	private JTextField textFieldQuantity;
	private JTextField textFieldOriginalCost;
	private JTextField textFieldRetailPrice;
	private JTextField textFieldType;
	private JTextField textFieldLayoutOrButtons;

	public AddProduct() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldBarcode = new JTextField();
		textFieldBarcode.setToolTipText("");
		textFieldBarcode.setBounds(98, 42, 86, 20);
		contentPane.add(textFieldBarcode);
		textFieldBarcode.setColumns(10);
		
		textFieldBrand = new JTextField();
		textFieldBrand.setBounds(98, 73, 86, 20);
		contentPane.add(textFieldBrand);
		textFieldBrand.setColumns(10);
		
		textFieldColour = new JTextField();
		textFieldColour.setBounds(98, 104, 86, 20);
		contentPane.add(textFieldColour);
		textFieldColour.setColumns(10);
		
		textFieldConnectivity = new JTextField();
		textFieldConnectivity.setBounds(98, 135, 86, 20);
		contentPane.add(textFieldConnectivity);
		textFieldConnectivity.setColumns(10);
		
		textFieldQuantity = new JTextField();
		textFieldQuantity.setBounds(98, 166, 86, 20);
		contentPane.add(textFieldQuantity);
		textFieldQuantity.setColumns(10);
		
		JLabel lblBarcode = new JLabel("Barcode");
		lblBarcode.setBounds(10, 45, 73, 14);
		contentPane.add(lblBarcode);
		
		JLabel lblBrand = new JLabel("Brand");
		lblBrand.setBounds(10, 76, 46, 14);
		contentPane.add(lblBrand);
		
		JLabel lblColour = new JLabel("Colour");
		lblColour.setBounds(10, 107, 46, 14);
		contentPane.add(lblColour);
		
		JLabel lblConnectivity = new JLabel("Connectivity");
		lblConnectivity.setBounds(10, 138, 73, 14);
		contentPane.add(lblConnectivity);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(10, 169, 89, 14);
		contentPane.add(lblQuantity);
		
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(192, 134, 89, 23);
		contentPane.add(btnCancel);
		
		final JComboBox comboBoxDevice = new JComboBox();
		comboBoxDevice.setModel(new DefaultComboBoxModel(new String[] {"keyboard", "mouse"}));
		comboBoxDevice.setBounds(98, 11, 86, 20);
		contentPane.add(comboBoxDevice);
		
		
		JLabel lblDeviceType = new JLabel("Device");
		lblDeviceType.setBounds(10, 14, 46, 14);
		contentPane.add(lblDeviceType);
		
		textFieldOriginalCost = new JTextField();
		textFieldOriginalCost.setBounds(297, 11, 86, 20);
		contentPane.add(textFieldOriginalCost);
		textFieldOriginalCost.setColumns(10);
		
		textFieldRetailPrice = new JTextField();
		textFieldRetailPrice.setBounds(297, 42, 86, 20);
		contentPane.add(textFieldRetailPrice);
		textFieldRetailPrice.setColumns(10);
		
		textFieldType = new JTextField();
		textFieldType.setBounds(297, 73, 86, 20);
		contentPane.add(textFieldType);
		textFieldType.setColumns(10);
		
		textFieldLayoutOrButtons = new JTextField();
		textFieldLayoutOrButtons.setBounds(297, 104, 86, 20);
		contentPane.add(textFieldLayoutOrButtons);
		textFieldLayoutOrButtons.setColumns(10);
		
		JLabel lblOprice = new JLabel("Original Cost");
		lblOprice.setBounds(207, 14, 74, 14);
		contentPane.add(lblOprice);
		
		JLabel lblRprice = new JLabel("Retail Price");
		lblRprice.setBounds(207, 45, 74, 14);
		contentPane.add(lblRprice);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(207, 76, 73, 14);
		contentPane.add(lblType);
		
		final JLabel lblLayout = new JLabel("Layout");
		lblLayout.setBounds(207, 107, 74, 14);
		contentPane.add(lblLayout);
		
		JButton btnFinish = new JButton("Finish");
		btnFinish.setBounds(297, 134, 89, 23);
		contentPane.add(btnFinish);
		
		comboBoxDevice.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	String comboOption = (String) comboBoxDevice.getSelectedItem();
		    	if (comboOption.equals("mouse")) {
		    		lblLayout.setText("Buttons");
		    	}
		    	else {
		    		lblLayout.setText("Layout");
		    	}
		    }
		});
		
		comboBoxDevice.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	String comboOption = (String) comboBoxDevice.getSelectedItem();
		    	if (comboOption.equals("mouse")) {
		    		textFieldLayoutOrButtons.addKeyListener(new KeyAdapter() {
		    			public void keyPressed(KeyEvent EVT) {
		    					if (EVT.getKeyChar() >= '0' && EVT.getKeyChar() <= '9' || EVT.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
		    						textFieldLayoutOrButtons.getText().contentEquals(null);
		    						textFieldLayoutOrButtons.setEditable(true);
		    					} else {
		    						textFieldLayoutOrButtons.setEditable(false);	
		    					}
		    				}
		    			}
		    		);
		    		lblLayout.setText("Buttons");
		    	}
		    	else {
		    		textFieldLayoutOrButtons.addKeyListener(new KeyAdapter() {
		    			public void keyPressed(KeyEvent EVT) {
		    					if (EVT.getKeyChar() >= '0' && EVT.getKeyChar() <= '9' || EVT.getKeyChar() == '.') {
		    						textFieldLayoutOrButtons.setEditable(false);
		    					} else {
		    						textFieldLayoutOrButtons.getText().contentEquals(null);
		    						textFieldLayoutOrButtons.setEditable(true);	
		    					}
		    				}
		    			}
		    		);
		    		lblLayout.setText("Layout");
		    	} 	
		    }
		});
		
		
		textFieldBarcode.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent EVT) {
				if (EVT.getKeyChar() >= '0' && EVT.getKeyChar() <= '9' || EVT.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					textFieldBarcode.setEditable(true);
				} else {
					textFieldBarcode.setEditable(false);
				}
			}
		});
		
		
		textFieldQuantity.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent EVT) {
				if (EVT.getKeyChar() >= '0' && EVT.getKeyChar() <= '9' || EVT.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					textFieldQuantity.setEditable(true);
				} else {
					textFieldQuantity.setEditable(false);
					textFieldQuantity.setText("");					
				}
			}
		});
		
		
		textFieldOriginalCost.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent EVT) {
				if (EVT.getKeyChar() >= '0' && EVT.getKeyChar() <= '9' || EVT.getKeyChar() == '.' || EVT.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					textFieldOriginalCost.setEditable(true);
				} else {
					textFieldOriginalCost.setEditable(false);	
				}
			}
		});
		
		
		textFieldRetailPrice.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent EVT) {
				if (EVT.getKeyChar() >= '0' && EVT.getKeyChar() <= '9' || EVT.getKeyChar() == '.' || EVT.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					textFieldRetailPrice.setEditable(true);
				} else {
					textFieldRetailPrice.setEditable(false);
				}
			}
		});
		
		textFieldColour.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent EVT) {
					if (EVT.getKeyChar() >= '0' && EVT.getKeyChar() <= '9' || EVT.getKeyChar() == '.') {
						textFieldColour.setEditable(false);
					} else {
						textFieldColour.setEditable(true);	
					}
				}
			}
		);
		
		textFieldConnectivity.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent EVT) {
					if (EVT.getKeyChar() >= '0' && EVT.getKeyChar() <= '9' || EVT.getKeyChar() == '.') {
						textFieldConnectivity.setEditable(false);
					} else {
						textFieldConnectivity.setEditable(true);	
					}
				}
			}
		);
		
		btnFinish.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
                FileWriter writer;
                try {
                	
                boolean finish = true;
                    writer = new FileWriter("./Stock.txt", true);
                    StockItem item = new StockItem(textFieldBarcode.getText(), (String) comboBoxDevice.getSelectedItem(), textFieldType.getText(), textFieldBrand.getText(), textFieldColour.getText(), textFieldConnectivity.getText(), textFieldQuantity.getText(), textFieldOriginalCost.getText(), textFieldRetailPrice.getText(), textFieldLayoutOrButtons.getText());
                    
                    if (textFieldBarcode.getText().equals("") && textFieldType.getText().equals("") && textFieldBrand.getText().equals("") && textFieldColour.getText().equals("") && textFieldConnectivity.getText().equals("") && textFieldQuantity.getText().equals("") && textFieldOriginalCost.getText().equals("") && textFieldRetailPrice.getText().equals("") && textFieldLayoutOrButtons.getText().equals("")) {
                    	finish = false;
                    	while(finish == false) {
//                    		btnFinish.setEnabled(false);	
                    		break;
                    	}
                    	
                    }
                    else if (textFieldBarcode.getText() != "" && textFieldBrand.getText() != "" && textFieldColour.getText() != "" && textFieldConnectivity.getText() != "" && textFieldQuantity.getText() != "" && textFieldOriginalCost.getText() != "" && textFieldRetailPrice.getText() != "" && textFieldBarcode.getText() != "" && textFieldType.getText() != "" && textFieldLayoutOrButtons.getText() != ""){
                    		finish = true;
                    		while (finish == true) {
                    		  writer.write(item.allDetails());
                              writer.close();
                    		}
                    	 }
   
                } catch (IOException f) {
                    f.printStackTrace();
            	}

            }
        });
	}
}

