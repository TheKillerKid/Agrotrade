package UI;

import java.awt.EventQueue; 

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import java.awt.Color;
import javax.swing.JMenu;
import java.awt.GridBagLayout;

import Model.Model.Employee;
import Model.Model.LoginContainer;
import Model.Model.PersonPageType;

import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
 
public class HomePage extends JDialog {
	
	private LoginContainer loginContainer = LoginContainer.getInstance();
	
	private JLabel nameValue = new JLabel("");
	private JLabel addressValue = new JLabel("");
	private JLabel emailValue = new JLabel("");
	private JLabel phoneValue = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage dialog = new HomePage();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public HomePage() {
		setBounds(100, 100, 745, 480);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.BLACK);
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Order");
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("Product");
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("Person");
		menuBar.add(mnNewMenu_2);
		
		nameValue.setText(getFullname());
		addressValue.setText(getFullAddress());
		emailValue.setText(getEmail());
		phoneValue.setText(getPhone());
		
		JMenuItem registerEmployeeMntm = new JMenuItem("Register employee");
		mnNewMenu_2.add(registerEmployeeMntm);
		registerEmployeeMntm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PersonPage.start(PersonPageType.EMPLOYEE);
				dispose();
			}
		});
		
		JMenuItem registerCustomerMntm = new JMenuItem("Register customer ");
		mnNewMenu_2.add(registerCustomerMntm);
		registerCustomerMntm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PersonPage.start(PersonPageType.CUSTOMER);
				dispose();
				
			}
		});
		
		JMenuItem registerSupplierMntm = new JMenuItem("Register supplier");
		mnNewMenu_2.add(registerSupplierMntm);
		registerSupplierMntm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PersonPage.start(PersonPageType.SUPPLIER);
				dispose();
				
			}
		});
		
		JMenuItem peopleListMntm = new JMenuItem("People list");
		mnNewMenu_2.add(peopleListMntm);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton createOrderBtn = new JButton("Register order");
		GridBagConstraints gbc_createOrderBtn = new GridBagConstraints();
		gbc_createOrderBtn.insets = new Insets(0, 0, 5, 5);
		gbc_createOrderBtn.gridx = 0;
		gbc_createOrderBtn.gridy = 0;
		panel.add(createOrderBtn, gbc_createOrderBtn);
		createOrderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderPage.start();
				
			}
		});
		
		JButton createEmployeeBtn = new JButton("Register employee");
		createEmployeeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PersonPage.start(PersonPageType.EMPLOYEE);
				dispose();
			}
		});
		
		JButton orderListBtn = new JButton("Order list");
		GridBagConstraints gbc_orderListBtn = new GridBagConstraints();
		gbc_orderListBtn.insets = new Insets(0, 0, 5, 0);
		gbc_orderListBtn.gridx = 1;
		gbc_orderListBtn.gridy = 0;
		panel.add(orderListBtn, gbc_orderListBtn);
		orderListBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				OrderListPage.start();
			}
		});
		
		GridBagConstraints gbc_createEmployeeBtn = new GridBagConstraints();
		gbc_createEmployeeBtn.insets = new Insets(0, 0, 5, 5);
		gbc_createEmployeeBtn.gridx = 0;
		gbc_createEmployeeBtn.gridy = 1;
		panel.add(createEmployeeBtn, gbc_createEmployeeBtn);
		
		JButton peopleListBtn = new JButton("People list");
		GridBagConstraints gbc_peopleListBtn = new GridBagConstraints();
		gbc_peopleListBtn.insets = new Insets(0, 0, 5, 0);
		gbc_peopleListBtn.gridx = 1;
		gbc_peopleListBtn.gridy = 1;
		panel.add(peopleListBtn, gbc_peopleListBtn);
		
		JButton createProductBtn = new JButton("Register product");
		GridBagConstraints gbc_createProductBtn = new GridBagConstraints();
		gbc_createProductBtn.insets = new Insets(0, 0, 5, 5);
		gbc_createProductBtn.gridx = 0;
		gbc_createProductBtn.gridy = 2;
		panel.add(createProductBtn, gbc_createProductBtn);
		createProductBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductPage.start();
				
			}
		});
		
		JButton productListButton = new JButton("Product list");
		GridBagConstraints gbc_productListButton = new GridBagConstraints();
		gbc_productListButton.insets = new Insets(0, 0, 5, 0);
		gbc_productListButton.gridx = 1;
		gbc_productListButton.gridy = 2;
		panel.add(productListButton, gbc_productListButton);
		productListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductListPage.start();
				
			}
		});
		
		Panel loggedUserDetail = new Panel();
		GridBagConstraints gbc_loggedUserDetail = new GridBagConstraints();
		gbc_loggedUserDetail.fill = GridBagConstraints.HORIZONTAL;
		gbc_loggedUserDetail.insets = new Insets(0, 0, 5, 5);
		gbc_loggedUserDetail.gridx = 3;
		gbc_loggedUserDetail.gridy = 1;
		getContentPane().add(loggedUserDetail, gbc_loggedUserDetail);
		GridBagLayout gbl_loggedUserDetail = new GridBagLayout();
		gbl_loggedUserDetail.columnWidths = new int[]{61, 61, 0};
		gbl_loggedUserDetail.rowHeights = new int[]{16, 0, 0, 0, 0};
		gbl_loggedUserDetail.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_loggedUserDetail.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		loggedUserDetail.setLayout(gbl_loggedUserDetail);
		
		JLabel nameLbl = new JLabel("Name:");
		GridBagConstraints gbc_nameLbl = new GridBagConstraints();
		gbc_nameLbl.anchor = GridBagConstraints.NORTHWEST;
		gbc_nameLbl.insets = new Insets(0, 0, 5, 5);
		gbc_nameLbl.gridx = 0;
		gbc_nameLbl.gridy = 0;
		loggedUserDetail.add(nameLbl, gbc_nameLbl);
		
		GridBagConstraints gbc_nameValue = new GridBagConstraints();
		gbc_nameValue.anchor = GridBagConstraints.NORTHWEST;
		gbc_nameValue.insets = new Insets(0, 0, 5, 0);
		gbc_nameValue.gridx = 1;
		gbc_nameValue.gridy = 0;
		loggedUserDetail.add(nameValue, gbc_nameValue);
		
		JLabel addressLbl = new JLabel("Address:");
		GridBagConstraints gbc_addressLbl = new GridBagConstraints();
		gbc_addressLbl.anchor = GridBagConstraints.NORTHWEST;
		gbc_addressLbl.insets = new Insets(0, 0, 5, 5);
		gbc_addressLbl.gridx = 0;
		gbc_addressLbl.gridy = 1;
		loggedUserDetail.add(addressLbl, gbc_addressLbl);
		
		GridBagConstraints gbc_addressValue = new GridBagConstraints();
		gbc_addressValue.insets = new Insets(0, 0, 5, 0);
		gbc_addressValue.anchor = GridBagConstraints.NORTHWEST;
		gbc_addressValue.gridx = 1;
		gbc_addressValue.gridy = 1;
		loggedUserDetail.add(addressValue, gbc_addressValue);
		
		JLabel emailLbl = new JLabel("Email:");
		GridBagConstraints gbc_emailLbl = new GridBagConstraints();
		gbc_emailLbl.anchor = GridBagConstraints.NORTHWEST;
		gbc_emailLbl.insets = new Insets(0, 0, 5, 5);
		gbc_emailLbl.gridx = 0;
		gbc_emailLbl.gridy = 2;
		loggedUserDetail.add(emailLbl, gbc_emailLbl);
		
		GridBagConstraints gbc_emailValue = new GridBagConstraints();
		gbc_emailValue.anchor = GridBagConstraints.NORTHWEST;
		gbc_emailValue.insets = new Insets(0, 0, 5, 0);
		gbc_emailValue.gridx = 1;
		gbc_emailValue.gridy = 2;
		loggedUserDetail.add(emailValue, gbc_emailValue);
		
		JLabel phoneLbl = new JLabel("Phone:");
		GridBagConstraints gbc_phoneLbl = new GridBagConstraints();
		gbc_phoneLbl.anchor = GridBagConstraints.NORTHWEST;
		gbc_phoneLbl.insets = new Insets(0, 0, 0, 5);
		gbc_phoneLbl.gridx = 0;
		gbc_phoneLbl.gridy = 3;
		loggedUserDetail.add(phoneLbl, gbc_phoneLbl);
		
		GridBagConstraints gbc_phoneValue = new GridBagConstraints();
		gbc_phoneValue.anchor = GridBagConstraints.NORTHWEST;
		gbc_phoneValue.gridx = 1;
		gbc_phoneValue.gridy = 3;
		loggedUserDetail.add(phoneValue, gbc_phoneValue);
	}
	
	private String getFullname() {
		Employee user = loginContainer.getCurrentUser();
		if(user != null) {
			return user.getFirstName() + " " + user.getLastName();	
		}
		return null;
	}
	
	private String getFullAddress() {
		Employee user = loginContainer.getCurrentUser();
		if(user != null) {
			return user.getAddress().getStreet() + " " + 
					user.getAddress().getStreetNo() + ", " + 
					user.getAddress().getCity() + ", " + 
					user.getAddress().getPostalCode() + ", " + 
					user.getAddress().getCountry();
		}
		return null;
	}
	
	private String getEmail() {
		Employee user = loginContainer.getCurrentUser();
		if(user != null) {
			return user.getEmail();
		}
		return null;
	}
	
	private String getPhone() {
		Employee user = loginContainer.getCurrentUser();
		if(user != null) {
			return user.getPhone();
		}
		return null;
	}
}
