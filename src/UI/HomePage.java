package UI;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import java.awt.Color;
import javax.swing.JMenu;
import java.awt.GridBagLayout;

import Model.Model.Address;
import Model.Model.Employee;
import Model.Model.LoginContainer;
import Model.Model.OrderPageType;
import Model.Model.PersonPageType;
import Model.Model.PositionType;

import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
 
public class HomePage extends JDialog {
	
	private LoginContainer loginContainer = LoginContainer.getInstance();
	
	private JLabel nameValue = new JLabel("");
	private JLabel addressValue = new JLabel("");
	private JLabel emailValue = new JLabel("");
	private JLabel phoneValue = new JLabel("");
	private PositionType position = loginContainer.getCurrentUser().getPosition();
	private LoadingPage loadingPage;
	
	private JButton stopLoadingBtn;
	private GridBagConstraints gbc_stopLoadingBtn;
	
	private JButton startLoadingBtn;
	private GridBagConstraints gbc_startLoadingBtn;

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
					throw e;
				} finally {
					LoadingPage loadingPage = LoadingPage.getInstance();
					loadingPage.destroy();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public HomePage() {
		setBounds(150, 150, 1280, 800);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.BLACK);
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Order");
		menuBar.add(mnNewMenu);
		
		
		if(position == PositionType.ADMIN || position == PositionType.SALESMAN) {
			JMenuItem registerSaleMntm = new JMenuItem("Register sale");
			registerSaleMntm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					loadingPage = LoadingPage.getInstance();
					new Thread(loadingPage, "thread_loading").start();
					
					OrderPage.start(OrderPageType.SALE, null);
					dispose();
				}
			});
			mnNewMenu.add(registerSaleMntm);
		}
		
		if(position == PositionType.ADMIN || position == PositionType.SALESMAN) {
			JMenuItem registerLeaseMntm = new JMenuItem("Register lease");
			registerLeaseMntm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					loadingPage = LoadingPage.getInstance();
					new Thread(loadingPage, "thread_loading").start();
					
					OrderPage.start(OrderPageType.LEASE, null);
					dispose();
				}
			});
			mnNewMenu.add(registerLeaseMntm);
		}
		
		if(position == PositionType.ADMIN || position == PositionType.SALESMAN) {
			JMenuItem registerPurchaseMntm = new JMenuItem("Register purchase");
			registerPurchaseMntm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					loadingPage = LoadingPage.getInstance();
					new Thread(loadingPage, "thread_loading").start();
					
					OrderPage.start(OrderPageType.PURCHASE, null);
					dispose();
				}
			});
			mnNewMenu.add(registerPurchaseMntm);
		}
		
		JMenuItem orderListMntm = new JMenuItem("Order list");
		orderListMntm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadingPage = LoadingPage.getInstance();
				new Thread(loadingPage, "thread_loading").start();
				
				OrderListPage.start();
				dispose();
			}
		});
		mnNewMenu.add(orderListMntm);
		
		JMenu mnNewMenu_1 = new JMenu("Product");
		menuBar.add(mnNewMenu_1);
		
		if(position == PositionType.ADMIN || position == PositionType.SALESMAN) {
			JMenuItem registerProductMntm = new JMenuItem("Register product");
			registerProductMntm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					loadingPage = LoadingPage.getInstance();
					new Thread(loadingPage, "thread_loading").start();
					
					ProductPage.start(-1);
					dispose();
				}
			});
			mnNewMenu_1.add(registerProductMntm);
		}
		
		JMenuItem productListMntm = new JMenuItem("Product list");
		productListMntm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				loadingPage = LoadingPage.getInstance();
				new Thread(loadingPage, "thread_loading").start();
				
				ProductListPage.start();
				dispose();
			}
		});
		mnNewMenu_1.add(productListMntm);
		
		JMenu mnNewMenu_2 = new JMenu("Person");
		menuBar.add(mnNewMenu_2);
		
		if(position == PositionType.ADMIN || position == PositionType.SALESMAN) {
			JMenuItem registerEmployeeMntm = new JMenuItem("Register employee");
			mnNewMenu_2.add(registerEmployeeMntm);
			registerEmployeeMntm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					loadingPage = LoadingPage.getInstance();
					new Thread(loadingPage, "thread_loading").start();
					
					PersonPage.start(PersonPageType.EMPLOYEE, -1);
					dispose();
				}
			});
		}
		
		
		if(position == PositionType.ADMIN || position == PositionType.SALESMAN) {
			JMenuItem registerCustomerMntm = new JMenuItem("Register customer ");
			mnNewMenu_2.add(registerCustomerMntm);
			registerCustomerMntm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					loadingPage = LoadingPage.getInstance();
					new Thread(loadingPage, "thread_loading").start();
					
					PersonPage.start(PersonPageType.CUSTOMER, -1);
					dispose();
					
				}
			});
		}
		
		if(position == PositionType.ADMIN || position == PositionType.SALESMAN) {
			JMenuItem registerSupplierMntm = new JMenuItem("Register supplier");
			mnNewMenu_2.add(registerSupplierMntm);
			registerSupplierMntm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					loadingPage = LoadingPage.getInstance();
					new Thread(loadingPage, "thread_loading").start();
					
					PersonPage.start(PersonPageType.SUPPLIER, -1);
					dispose();
					
				}
		});
		}
		
		JMenuItem peopleListMntm = new JMenuItem("People list");
		peopleListMntm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				loadingPage = LoadingPage.getInstance();
				new Thread(loadingPage, "thread_loading").start();
				
				PeopleListPage.start();
				dispose();
			}
		});
		mnNewMenu_2.add(peopleListMntm);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 146, 0, 0, 182, 0, 100};
		gridBagLayout.rowHeights = new int[]{0, 155, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel betaPanel = new JPanel();
		GridBagConstraints gbc_betaPanel = new GridBagConstraints();
		gbc_betaPanel.anchor = GridBagConstraints.EAST;
		gbc_betaPanel.fill = GridBagConstraints.VERTICAL;
		gbc_betaPanel.insets = new Insets(0, 0, 5, 0);
		gbc_betaPanel.gridx = 7;
		gbc_betaPanel.gridy = 0;
		getContentPane().add(betaPanel, gbc_betaPanel);
		GridBagLayout gbl_betaPanel = new GridBagLayout();
		gbl_betaPanel.columnWidths = new int[] {0, 130, 130, 0, 0};
		gbl_betaPanel.rowHeights = new int[]{40, 40, 0};
		gbl_betaPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_betaPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		betaPanel.setLayout(gbl_betaPanel);
		
		JCheckBox betaCheckbox = new JCheckBox("Beta version");
		
		betaCheckbox.setSelected(LoginContainer.getInstance().betaEnabled);
		betaCheckbox.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				LoginContainer.getInstance().betaEnabled = betaCheckbox.isSelected();
				if(LoginContainer.getInstance().betaEnabled) {
					betaPanel.add(startLoadingBtn, gbc_startLoadingBtn);
					betaPanel.add(stopLoadingBtn, gbc_stopLoadingBtn);
				}
				else {
					betaPanel.remove(startLoadingBtn);
					betaPanel.remove(stopLoadingBtn);
				}
				
				betaPanel.revalidate();
				betaPanel.repaint();
			}
		});
		GridBagConstraints gbc_betaCheckbox = new GridBagConstraints();
		gbc_betaCheckbox.insets = new Insets(0, 0, 5, 5);
		gbc_betaCheckbox.gridx = 2;
		gbc_betaCheckbox.gridy = 0;
		betaPanel.add(betaCheckbox, gbc_betaCheckbox);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 1;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton orderListBtn = new JButton("Order list");
		GridBagConstraints gbc_orderListBtn = new GridBagConstraints();
		gbc_orderListBtn.insets = new Insets(0, 0, 5, 0);
		gbc_orderListBtn.gridx = 1;
		gbc_orderListBtn.gridy = 0;
		panel.add(orderListBtn, gbc_orderListBtn);
		orderListBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				loadingPage = LoadingPage.getInstance();
				new Thread(loadingPage, "thread_loading").start();
				
				OrderListPage.start();
				dispose();
			}
		});
		
		
		JButton peopleListBtn = new JButton("People list");
		GridBagConstraints gbc_peopleListBtn = new GridBagConstraints();
		gbc_peopleListBtn.insets = new Insets(0, 0, 5, 0);
		gbc_peopleListBtn.gridx = 1;
		gbc_peopleListBtn.gridy = 1;
		panel.add(peopleListBtn, gbc_peopleListBtn);
		peopleListBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				loadingPage = LoadingPage.getInstance();
				new Thread(loadingPage, "thread_loading").start();
				
				PeopleListPage.start();
				dispose();
			}
		});
		
		JButton productListButton = new JButton("Product list");
		GridBagConstraints gbc_productListButton = new GridBagConstraints();
		gbc_productListButton.gridx = 1;
		gbc_productListButton.gridy = 2;
		panel.add(productListButton, gbc_productListButton);
		productListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				loadingPage = LoadingPage.getInstance();
				new Thread(loadingPage, "thread_loading").start();
				
				ProductListPage.start();
				dispose();
			}
		});
		
		nameValue.setText(getFullname());
		addressValue.setText(getFullAddress());
		emailValue.setText(getEmail());
		
		Panel loggedUserDetail = new Panel();
		GridBagConstraints gbc_loggedUserDetail = new GridBagConstraints();
		gbc_loggedUserDetail.fill = GridBagConstraints.HORIZONTAL;
		gbc_loggedUserDetail.insets = new Insets(0, 0, 5, 5);
		gbc_loggedUserDetail.gridx = 5;
		gbc_loggedUserDetail.gridy = 1;
		getContentPane().add(loggedUserDetail, gbc_loggedUserDetail);
		GridBagLayout gbl_loggedUserDetail = new GridBagLayout();
		gbl_loggedUserDetail.columnWidths = new int[]{90, 61, 0};
		gbl_loggedUserDetail.rowHeights = new int[]{16, 0, 0, 0, 0, 0, 0, 0};
		gbl_loggedUserDetail.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_loggedUserDetail.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		gbc_phoneLbl.insets = new Insets(0, 0, 5, 5);
		gbc_phoneLbl.gridx = 0;
		gbc_phoneLbl.gridy = 3;
		loggedUserDetail.add(phoneLbl, gbc_phoneLbl);
		phoneValue.setText(getPhone());
		
		GridBagConstraints gbc_phoneValue = new GridBagConstraints();
		gbc_phoneValue.insets = new Insets(0, 0, 5, 0);
		gbc_phoneValue.anchor = GridBagConstraints.NORTHWEST;
		gbc_phoneValue.gridx = 1;
		gbc_phoneValue.gridy = 3;
		loggedUserDetail.add(phoneValue, gbc_phoneValue);
		
		JLabel warehouseLbl = new JLabel("Warehouse:");
		GridBagConstraints gbc_warehouseLbl = new GridBagConstraints();
		gbc_warehouseLbl.anchor = GridBagConstraints.WEST;
		gbc_warehouseLbl.insets = new Insets(0, 0, 5, 5);
		gbc_warehouseLbl.gridx = 0;
		gbc_warehouseLbl.gridy = 4;
		loggedUserDetail.add(warehouseLbl, gbc_warehouseLbl);
		
		JLabel warehouseValue = new JLabel(getWarehouseAddress());
		GridBagConstraints gbc_warehouseValue = new GridBagConstraints();
		gbc_warehouseValue.anchor = GridBagConstraints.WEST;
		gbc_warehouseValue.insets = new Insets(0, 0, 5, 0);
		gbc_warehouseValue.gridx = 1;
		gbc_warehouseValue.gridy = 4;
		loggedUserDetail.add(warehouseValue, gbc_warehouseValue);
		
		JLabel positionLbl = new JLabel("Position:");
		GridBagConstraints gbc_positionLbl = new GridBagConstraints();
		gbc_positionLbl.anchor = GridBagConstraints.WEST;
		gbc_positionLbl.insets = new Insets(0, 0, 5, 5);
		gbc_positionLbl.gridx = 0;
		gbc_positionLbl.gridy = 5;
		loggedUserDetail.add(positionLbl, gbc_positionLbl);
		
		JLabel positionValue = new JLabel(position.name());
		GridBagConstraints gbc_positionValue = new GridBagConstraints();
		gbc_positionValue.anchor = GridBagConstraints.WEST;
		gbc_positionValue.insets = new Insets(0, 0, 5, 0);
		gbc_positionValue.gridx = 1;
		gbc_positionValue.gridy = 5;
		loggedUserDetail.add(positionValue, gbc_positionValue);
		
		JButton logoutBtn = new JButton("Log out");
		GridBagConstraints gbc_logoutBtn = new GridBagConstraints();
		gbc_logoutBtn.anchor = GridBagConstraints.EAST;
		gbc_logoutBtn.gridx = 1;
		gbc_logoutBtn.gridy = 6;
		loggedUserDetail.add(logoutBtn, gbc_logoutBtn);
		logoutBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginContainer.getInstance().logout();
				dispose();
				LoginPage.start();
			}
		});
		
		startLoadingBtn = new JButton("Start loading");
		gbc_startLoadingBtn = new GridBagConstraints();
		gbc_startLoadingBtn.insets = new Insets(0, 0, 0, 5);
		gbc_startLoadingBtn.gridx = 1;
		gbc_startLoadingBtn.gridy = 1;
		if(betaCheckbox.isSelected()) {
			betaPanel.add(startLoadingBtn, gbc_startLoadingBtn);
		}
		startLoadingBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadingPage = LoadingPage.getInstance();
				new Thread(loadingPage, "thread_loading").start();
			}
		});
		
		stopLoadingBtn = new JButton("Stop loading");
		gbc_stopLoadingBtn = new GridBagConstraints();
		gbc_stopLoadingBtn.gridx = 2;
		gbc_stopLoadingBtn.gridy = 1;
		if(betaCheckbox.isSelected()) {
			betaPanel.add(stopLoadingBtn, gbc_stopLoadingBtn);
		}
		stopLoadingBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadingPage = LoadingPage.getInstance();
				loadingPage.destroy();
			}
		});
		
		if(position == PositionType.ADMIN || position == PositionType.SALESMAN) {
			JButton createSaleBtn = new JButton("Register sale");
			GridBagConstraints gbc_createSaleBtn = new GridBagConstraints();
			gbc_createSaleBtn.insets = new Insets(0, 0, 5, 5);
			gbc_createSaleBtn.gridx = 0;
			gbc_createSaleBtn.gridy = 0;
			panel.add(createSaleBtn, gbc_createSaleBtn);
			createSaleBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					loadingPage = LoadingPage.getInstance();
					new Thread(loadingPage, "thread_loading").start();
					
					OrderPage.start(OrderPageType.SALE, null);
					dispose();
					
				}
			});
		}
		
		if(position == PositionType.ADMIN || position == PositionType.SALESMAN) {
			JButton createEmployeeBtn = new JButton("Register employee");
			createEmployeeBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					loadingPage = LoadingPage.getInstance();
					new Thread(loadingPage, "thread_loading").start();
					
					PersonPage.start(PersonPageType.EMPLOYEE, -1);
					dispose();
				}
			});
			
			GridBagConstraints gbc_createEmployeeBtn = new GridBagConstraints();
			gbc_createEmployeeBtn.insets = new Insets(0, 0, 5, 5);
			gbc_createEmployeeBtn.gridx = 0;
			gbc_createEmployeeBtn.gridy = 1;
			panel.add(createEmployeeBtn, gbc_createEmployeeBtn);
		}
		
		if(position == PositionType.ADMIN || position == PositionType.SALESMAN) {
			JButton createProductBtn = new JButton("Register product");
			GridBagConstraints gbc_createProductBtn = new GridBagConstraints();
			gbc_createProductBtn.insets = new Insets(0, 0, 0, 5);
			gbc_createProductBtn.gridx = 0;
			gbc_createProductBtn.gridy = 2;
			panel.add(createProductBtn, gbc_createProductBtn);
			createProductBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					loadingPage = LoadingPage.getInstance();
					new Thread(loadingPage, "thread_loading").start();
					
					ProductPage.start(-1);
					dispose();
				}
			});
		}
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
	
	private String getWarehouseAddress() {
		Address address = loginContainer.getCurrentUser().getWarehouse().getAddress();
		if(address != null) {
			return address.getStreet() + " " + 
				   address.getStreetNo() + ", " + 
				   address.getCity() + ", " + 
				   address.getPostalCode() + ", " + 
				   address.getCountry();
		}
		return null;
	}
}
