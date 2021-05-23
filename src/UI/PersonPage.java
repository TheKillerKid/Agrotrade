package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import Model.Model.Address;
import Model.Model.Customer;
import Model.Model.Employee;
import Model.Model.LoginContainer;
import Model.Model.MessagesEnum;
import Model.Model.Person;
import Model.Model.PersonPageType;
import Model.Model.StockProduct;
import Model.Model.Supplier;
import Controller.CustomerController;
import Controller.EmployeeController;
import Controller.ParsingHelper;
import Controller.PersonController;
import Controller.SupplierController;

public class PersonPage extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JPanel panel = new JPanel();
	JPanel buttonPane = new JPanel();
	
	private PersonController personCtrl = new PersonController();
	private EmployeeController employeeCtrl = new EmployeeController();
	
	private JLabel messageLabel;
	private JTextField cprNoField;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField emailField;
	private JTextField passwordField;
	private JTextField phoneField;
	private JTextField cityField;
	private JTextField streetField;
	private JTextField streetNoField;
	private JTextField postalCodeField;
	private JTextField countryField;
	private JTextField departmentField;
	private JTextField positionField;
	private JTextField cvrNoField;
	private JTextField companyNameField;
	private JTextField staticDiscountField;
	private JButton btnGeneratePassword;
	private JButton btnBack;
	private JButton btnSave;
	

	public static void start(PersonPageType type, long personId) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonPage dialog = new PersonPage(type, personId);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					throw e;
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public PersonPage(PersonPageType type, long personId) {
		setBounds(150, 150, 1280, 800);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 90, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 45, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 1;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{90, 300, 0};
			gbl_panel.rowHeights = new int[]{45, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
		}
		

		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		contentPanel.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{90, 300, 0};
		gbl_panel.rowHeights = new int[]{45, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		{
			JLabel lblFirstName = new JLabel("First Name");
			GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
			gbc_lblFirstName.anchor = GridBagConstraints.WEST;
			gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
			gbc_lblFirstName.gridx = 0;
			gbc_lblFirstName.gridy = 1;
			panel.add(lblFirstName, gbc_lblFirstName);
		}
		{
			firstNameField = new JTextField();
			
			GridBagConstraints gbc_firstNameField = new GridBagConstraints();
			gbc_firstNameField.fill = GridBagConstraints.HORIZONTAL;
			gbc_firstNameField.insets = new Insets(0, 0, 5, 0);
			gbc_firstNameField.gridx = 1;
			gbc_firstNameField.gridy = 1;
			panel.add(firstNameField, gbc_firstNameField);
			firstNameField.setColumns(10);
		}
		{
			JLabel lblLastName = new JLabel("Last Name");
			GridBagConstraints gbc_lblLastName = new GridBagConstraints();
			gbc_lblLastName.anchor = GridBagConstraints.WEST;
			gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
			gbc_lblLastName.gridx = 0;
			gbc_lblLastName.gridy = 2;
			panel.add(lblLastName, gbc_lblLastName);
		}
		{
			lastNameField = new JTextField();
			GridBagConstraints gbc_lastNameField = new GridBagConstraints();
			gbc_lastNameField.fill = GridBagConstraints.HORIZONTAL;
			gbc_lastNameField.insets = new Insets(0, 0, 5, 0);
			gbc_lastNameField.gridx = 1;
			gbc_lastNameField.gridy = 2;
			panel.add(lastNameField, gbc_lastNameField);
			lastNameField.setColumns(10);
		}
		{
			JLabel lblEmail = new JLabel("Email");
			GridBagConstraints gbc_lblEmail = new GridBagConstraints();
			gbc_lblEmail.anchor = GridBagConstraints.WEST;
			gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
			gbc_lblEmail.gridx = 0;
			gbc_lblEmail.gridy = 3;
			panel.add(lblEmail, gbc_lblEmail);
		}
		{
			emailField = new JTextField();
			GridBagConstraints gbc_emailField = new GridBagConstraints();
			gbc_emailField.fill = GridBagConstraints.HORIZONTAL;
			gbc_emailField.insets = new Insets(0, 0, 5, 0);
			gbc_emailField.gridx = 1;
			gbc_emailField.gridy = 3;
			panel.add(emailField, gbc_emailField);
			emailField.setColumns(10);
		}
		{
			JLabel lblPhone = new JLabel("Phone");
			GridBagConstraints gbc_lblPhone = new GridBagConstraints();
			gbc_lblPhone.anchor = GridBagConstraints.WEST;
			gbc_lblPhone.insets = new Insets(0, 0, 5, 5);
			gbc_lblPhone.gridx = 0;
			gbc_lblPhone.gridy = 4;
			panel.add(lblPhone, gbc_lblPhone);
		}
		{
			phoneField = new JTextField();
			GridBagConstraints gbc_phoneField = new GridBagConstraints();
			gbc_phoneField.fill = GridBagConstraints.HORIZONTAL;
			gbc_phoneField.insets = new Insets(0, 0, 5, 0);
			gbc_phoneField.gridx = 1;
			gbc_phoneField.gridy = 4;
			panel.add(phoneField, gbc_phoneField);
			phoneField.setColumns(10);
		}
		{
			JLabel lblCity = new JLabel("City");
			GridBagConstraints gbc_lblCity = new GridBagConstraints();
			gbc_lblCity.anchor = GridBagConstraints.WEST;
			gbc_lblCity.insets = new Insets(0, 0, 5, 5);
			gbc_lblCity.gridx = 0;
			gbc_lblCity.gridy = 5;
			panel.add(lblCity, gbc_lblCity);
		}
		{
			cityField = new JTextField();
			GridBagConstraints gbc_textField_4 = new GridBagConstraints();
			gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_4.insets = new Insets(0, 0, 5, 0);
			gbc_textField_4.gridx = 1;
			gbc_textField_4.gridy = 5;
			panel.add(cityField, gbc_textField_4);
			cityField.setColumns(10);
		}
		{
			JLabel lblStreet = new JLabel("Street");
			GridBagConstraints gbc_lblStreet = new GridBagConstraints();
			gbc_lblStreet.anchor = GridBagConstraints.WEST;
			gbc_lblStreet.insets = new Insets(0, 0, 5, 5);
			gbc_lblStreet.gridx = 0;
			gbc_lblStreet.gridy = 6;
			panel.add(lblStreet, gbc_lblStreet);
		}
		{
			streetField = new JTextField();
			GridBagConstraints gbc_streetField = new GridBagConstraints();
			gbc_streetField.fill = GridBagConstraints.HORIZONTAL;
			gbc_streetField.insets = new Insets(0, 0, 5, 0);
			gbc_streetField.gridx = 1;
			gbc_streetField.gridy = 6;
			panel.add(streetField, gbc_streetField);
			streetField.setColumns(10);
		}
		{
			JLabel lblStreetNo = new JLabel("Street No");
			GridBagConstraints gbc_lblStreetNo = new GridBagConstraints();
			gbc_lblStreetNo.anchor = GridBagConstraints.WEST;
			gbc_lblStreetNo.insets = new Insets(0, 0, 5, 5);
			gbc_lblStreetNo.gridx = 0;
			gbc_lblStreetNo.gridy = 7;
			panel.add(lblStreetNo, gbc_lblStreetNo);
		}
		{
			streetNoField = new JTextField();
			GridBagConstraints gbc_streetNoField = new GridBagConstraints();
			gbc_streetNoField.fill = GridBagConstraints.HORIZONTAL;
			gbc_streetNoField.insets = new Insets(0, 0, 5, 0);
			gbc_streetNoField.gridx = 1;
			gbc_streetNoField.gridy = 7;
			panel.add(streetNoField, gbc_streetNoField);
			streetNoField.setColumns(10);
		}
		{
			JLabel lblPostalCode = new JLabel("Postal Code");
			GridBagConstraints gbc_lblPostalCode = new GridBagConstraints();
			gbc_lblPostalCode.anchor = GridBagConstraints.WEST;
			gbc_lblPostalCode.insets = new Insets(0, 0, 5, 5);
			gbc_lblPostalCode.gridx = 0;
			gbc_lblPostalCode.gridy = 8;
			panel.add(lblPostalCode, gbc_lblPostalCode);
		}
		{
			postalCodeField = new JTextField();
			GridBagConstraints gbc_postalCodeField = new GridBagConstraints();
			gbc_postalCodeField.fill = GridBagConstraints.HORIZONTAL;
			gbc_postalCodeField.insets = new Insets(0, 0, 5, 0);
			gbc_postalCodeField.gridx = 1;
			gbc_postalCodeField.gridy = 8;
			panel.add(postalCodeField, gbc_postalCodeField);
			postalCodeField.setColumns(10);
		}
		{
			JLabel lblCountry = new JLabel("Country");
			GridBagConstraints gbc_lblCountry = new GridBagConstraints();
			gbc_lblCountry.anchor = GridBagConstraints.WEST;
			gbc_lblCountry.insets = new Insets(0, 0, 5, 5);
			gbc_lblCountry.gridx = 0;
			gbc_lblCountry.gridy = 9;
			panel.add(lblCountry, gbc_lblCountry);
		}
		{
			countryField = new JTextField();
			GridBagConstraints gbc_countryField = new GridBagConstraints();
			gbc_countryField.fill = GridBagConstraints.HORIZONTAL;
			gbc_countryField.insets = new Insets(0, 0, 5, 0);
			gbc_countryField.gridx = 1;
			gbc_countryField.gridy = 9;
			panel.add(countryField, gbc_countryField);
			countryField.setColumns(10);
		}
		{
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[]{107, 72, 325, 65, 87, 0};
			gbl_buttonPane.rowHeights = new int[]{40, 0};
			gbl_buttonPane.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_buttonPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			buttonPane.setLayout(gbl_buttonPane);
			{
				btnBack = new JButton("Back");
				btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						HomePage.start();
						dispose();
					}
				});
				GridBagConstraints gbc_btnBack = new GridBagConstraints();
				gbc_btnBack.insets = new Insets(0, 0, 0, 5);
				gbc_btnBack.gridx = 1;
				gbc_btnBack.gridy = 0;
				buttonPane.add(btnBack, gbc_btnBack);
			}
			{
				btnSave = new JButton("Save");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(type == PersonPageType.EMPLOYEE) {	
							try {
								Address address = new Address(0, streetField.getText(), 
										streetNoField.getText(), 
										cityField.getText(), 
										positionField.getText(),
										countryField.getText());
								
								Employee employee = new Employee(0,
										firstNameField.getText(),
										lastNameField.getText(),
										personCtrl.getDateOfBirth(cprNoField.getText()), //change to field
										address,
										phoneField.getText(),
										emailField.getText(),
										passwordField.getText(),
										cprNoField.getText(),
										departmentField.getText(),
										positionField.getText(), 
										LoginContainer.getInstance().getCurrentUser().getWarehouse());
								personCtrl.createPerson(employee);
							} catch(SQLException e1) {
								messageLabel.setText(MessagesEnum.DBERROR.text);
								e1.printStackTrace();
								return;
							} catch(NumberFormatException e2) {
								messageLabel.setText(MessagesEnum.PARSEERROR.text);
								e2.printStackTrace();
								return;
							} catch(Exception e3) {
								messageLabel.setText(e3.getMessage());
							}
						}
						if(type == PersonPageType.CUSTOMER) {
							int staticDiscount;
							try {
								staticDiscount = ParsingHelper.tryParseInt(staticDiscountField.getText());
								Customer customer = new Customer(0,
										firstNameField.getText(),
										lastNameField.getText(), 
										new Address(0, 
												streetField.getText(), 
												streetNoField.getText(), 
												cityField.getText(), 
												positionField.getText(),
												countryField.getText()),
										phoneField.getText(),
										emailField.getText(),
										cvrNoField.getText(),
										staticDiscount);
								personCtrl.createPerson(customer);
							} catch (NumberFormatException e1) {
							    messageLabel.setText(MessagesEnum.PARSEERROR.text);
								e1.printStackTrace();
							    return;
							} catch (SQLException e2) {
								messageLabel.setText("Something went wrong with database, please try again.");
								e2.printStackTrace();
								return;
							}	
						}
						if(type == PersonPageType.SUPPLIER) {
							try {
								Supplier supplier = new Supplier(0,
										firstNameField.getText(),
										lastNameField.getText(), 
										new Address(0, 
												streetField.getText(), 
												streetNoField.getText(), 
												cityField.getText(), 
												positionField.getText(),
												countryField.getText()),
										phoneField.getText(),
										emailField.getText(),
										cvrNoField.getText(),
										companyNameField.getText());
								personCtrl.createPerson(supplier);
							} catch (SQLException e2) {
								messageLabel.setText(MessagesEnum.PARSEERROR.text);
								e2.printStackTrace();
								return;	
							}
						}
						
						HomePage.start();
						dispose();
					}
				});
				GridBagConstraints gbc_btnSave = new GridBagConstraints();
				gbc_btnSave.insets = new Insets(0, 0, 0, 5);
				gbc_btnSave.gridx = 3;
				gbc_btnSave.gridy = 0;
				buttonPane.add(btnSave, gbc_btnSave);
			}
		}
			
		if(type == PersonPageType.EMPLOYEE) {
			{
				JLabel lblCreateEmployee = new JLabel("Create Employee");
				GridBagConstraints gbc_lblCreateEmployee = new GridBagConstraints();
				gbc_lblCreateEmployee.insets = new Insets(0, 0, 5, 0);
				gbc_lblCreateEmployee.gridx = 1;
				gbc_lblCreateEmployee.gridy = 0;
				panel.add(lblCreateEmployee, gbc_lblCreateEmployee);
				lblCreateEmployee.setFont(new Font("Tahoma", Font.BOLD, 14));
			}
			{
				JLabel lblCprNo = new JLabel("CPR No");
				GridBagConstraints gbc_lblCprNo = new GridBagConstraints();
				gbc_lblCprNo.anchor = GridBagConstraints.WEST;
				gbc_lblCprNo.insets = new Insets(0, 0, 5, 5);
				gbc_lblCprNo.gridx = 0;
				gbc_lblCprNo.gridy = 10;
				panel.add(lblCprNo, gbc_lblCprNo);
			}
			{
				cprNoField = new JTextField();
				GridBagConstraints gbc_cprNoField = new GridBagConstraints();
				gbc_cprNoField.insets = new Insets(0, 0, 5, 0);
				gbc_cprNoField.fill = GridBagConstraints.HORIZONTAL;
				gbc_cprNoField.gridx = 1;
				gbc_cprNoField.gridy = 10;
				panel.add(cprNoField, gbc_cprNoField);
				cprNoField.setColumns(10);
			}
			{
				JLabel lblDepartment = new JLabel("Department");
				GridBagConstraints gbc_lblDepartment = new GridBagConstraints();
				gbc_lblDepartment.anchor = GridBagConstraints.WEST;
				gbc_lblDepartment.insets = new Insets(0, 0, 5, 5);
				gbc_lblDepartment.gridx = 0;
				gbc_lblDepartment.gridy = 11;
				panel.add(lblDepartment, gbc_lblDepartment);
			}
			{
				departmentField = new JTextField();
				GridBagConstraints gbc_departmentField = new GridBagConstraints();
				gbc_departmentField.insets = new Insets(0, 0, 5, 0);
				gbc_departmentField.fill = GridBagConstraints.HORIZONTAL;
				gbc_departmentField.gridx = 1;
				gbc_departmentField.gridy = 11;
				panel.add(departmentField, gbc_departmentField);
				departmentField.setColumns(10);
			}
			{
				JLabel lblPosition = new JLabel("Position");
				GridBagConstraints gbc_lblPosition = new GridBagConstraints();
				gbc_lblPosition.anchor = GridBagConstraints.WEST;
				gbc_lblPosition.insets = new Insets(0, 0, 0, 5);
				gbc_lblPosition.gridx = 0;
				gbc_lblPosition.gridy = 12;
				panel.add(lblPosition, gbc_lblPosition);
			}
			{
				positionField = new JTextField();
				GridBagConstraints gbc_positionField = new GridBagConstraints();
				gbc_positionField.fill = GridBagConstraints.HORIZONTAL;
				gbc_positionField.gridx = 1;
				gbc_positionField.gridy = 12;
				panel.add(positionField, gbc_positionField);
				positionField.setColumns(10);
			}
			{
				JLabel lblPassword = new JLabel("Password");
				GridBagConstraints gbc_lblPassword = new GridBagConstraints();
				gbc_lblPassword.anchor = GridBagConstraints.WEST;
				gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
				gbc_lblPassword.gridx = 0;
				gbc_lblPassword.gridy = 13;
				panel.add(lblPassword, gbc_lblPassword);
			}
			{
				passwordField = new JTextField();
				GridBagConstraints gbc_passwordField = new GridBagConstraints();
				gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
				gbc_passwordField.insets = new Insets(0, 0, 5, 0);
				gbc_passwordField.gridx = 1;
				gbc_passwordField.gridy = 13;
				panel.add(passwordField, gbc_passwordField);
				passwordField.setColumns(10);
			}
			{
				btnGeneratePassword = new JButton("Generate");
				btnGeneratePassword.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						passwordField.setText(employeeCtrl.generateRandomPassword());
					}
				});
				GridBagConstraints gbc_btnGeneratePassword = new GridBagConstraints();
				gbc_btnGeneratePassword.gridx = 2;
				gbc_btnGeneratePassword.gridy = 13;
				panel.add(btnGeneratePassword, gbc_btnGeneratePassword);
			}
		}
		
		if(type == PersonPageType.SUPPLIER) {
			{
				JLabel lblCreateCustomer = new JLabel("Create Supplier");
				GridBagConstraints gbc_lblCreateCustomer = new GridBagConstraints();
				gbc_lblCreateCustomer.insets = new Insets(0, 0, 5, 0);
				gbc_lblCreateCustomer.gridx = 1;
				gbc_lblCreateCustomer.gridy = 0;
				panel.add(lblCreateCustomer, gbc_lblCreateCustomer);
				lblCreateCustomer.setFont(new Font("Tahoma", Font.BOLD, 14));
			}
			{
				JLabel lblCvrNo = new JLabel("CVR No");
				GridBagConstraints gbc_lblCvrNo = new GridBagConstraints();
				gbc_lblCvrNo.anchor = GridBagConstraints.WEST;
				gbc_lblCvrNo.insets = new Insets(0, 0, 5, 5);
				gbc_lblCvrNo.gridx = 0;
				gbc_lblCvrNo.gridy = 10;
				panel.add(lblCvrNo, gbc_lblCvrNo);
			}
			{
				cvrNoField = new JTextField();
				GridBagConstraints gbc_cvrNoField = new GridBagConstraints();
				gbc_cvrNoField.insets = new Insets(0, 0, 5, 0);
				gbc_cvrNoField.fill = GridBagConstraints.HORIZONTAL;
				gbc_cvrNoField.gridx = 1;
				gbc_cvrNoField.gridy = 10;
				panel.add(cvrNoField, gbc_cvrNoField);
				cvrNoField.setColumns(10);
			}
		{
				JLabel lblCompanyName = new JLabel("Company Name");
				GridBagConstraints gbc_lblCompanyName = new GridBagConstraints();
				gbc_lblCompanyName.anchor = GridBagConstraints.WEST;
				gbc_lblCompanyName.insets = new Insets(0, 0, 5, 5);
				gbc_lblCompanyName.gridx = 0;
				gbc_lblCompanyName.gridy = 11;
				panel.add(lblCompanyName, gbc_lblCompanyName);
			}
			{
				companyNameField = new JTextField();
				GridBagConstraints gbc_companyNameField = new GridBagConstraints();
				gbc_companyNameField.insets = new Insets(0, 0, 5, 0);
				gbc_companyNameField.fill = GridBagConstraints.HORIZONTAL;
				gbc_companyNameField.gridx = 1;
				gbc_companyNameField.gridy = 11;
				panel.add(companyNameField, gbc_companyNameField);
				companyNameField.setColumns(10);
			}
			{
				JLabel lblstaticDiscount = new JLabel("static Discount");
				GridBagConstraints gbc_lblstaticDiscount = new GridBagConstraints();
				gbc_lblstaticDiscount.anchor = GridBagConstraints.WEST;
				gbc_lblstaticDiscount.insets = new Insets(0, 0, 5, 5);
				gbc_lblstaticDiscount.gridx = 0;
				gbc_lblstaticDiscount.gridy = 12;
				panel.add(lblstaticDiscount, gbc_lblstaticDiscount);
			}
			{
				staticDiscountField = new JTextField();
				GridBagConstraints gbc_staticDiscountField = new GridBagConstraints();
				gbc_staticDiscountField.insets = new Insets(0, 0, 5, 0);
				gbc_staticDiscountField.fill = GridBagConstraints.HORIZONTAL;
				gbc_staticDiscountField.gridx = 1;
				gbc_staticDiscountField.gridy = 12;
				panel.add(staticDiscountField, gbc_staticDiscountField);
				staticDiscountField.setColumns(10);
			}
			}
			
		if(type == PersonPageType.CUSTOMER) {
			
			{
				JLabel lblCreateCustomer = new JLabel("Create Customer");
				GridBagConstraints gbc_lblCreateCustomer = new GridBagConstraints();
				gbc_lblCreateCustomer.insets = new Insets(0, 0, 5, 0);
				gbc_lblCreateCustomer.gridx = 1;
				gbc_lblCreateCustomer.gridy = 0;
				panel.add(lblCreateCustomer, gbc_lblCreateCustomer);
				lblCreateCustomer.setFont(new Font("Tahoma", Font.BOLD, 14));
			}
			
			{
				JLabel lblCvrNo = new JLabel("CVR No");
				GridBagConstraints gbc_lblCvrNo = new GridBagConstraints();
				gbc_lblCvrNo.anchor = GridBagConstraints.WEST;
				gbc_lblCvrNo.insets = new Insets(0, 0, 0, 5);
				gbc_lblCvrNo.gridx = 0;
				gbc_lblCvrNo.gridy = 10;
				panel.add(lblCvrNo, gbc_lblCvrNo);
			}
			{
				cvrNoField = new JTextField();
				GridBagConstraints gbc_cvrNoField = new GridBagConstraints();
				gbc_cvrNoField.fill = GridBagConstraints.HORIZONTAL;
				gbc_cvrNoField.gridx = 1;
				gbc_cvrNoField.gridy = 10;
				panel.add(cvrNoField, gbc_cvrNoField);
				cvrNoField.setColumns(10);
			}
		}
		messageLabel = new JLabel("");
		messageLabel.setForeground(Color.RED);
		GridBagConstraints gbc_messageLabel = new GridBagConstraints();
		gbc_messageLabel.anchor = GridBagConstraints.WEST;
		gbc_messageLabel.gridx = 1;
		gbc_messageLabel.gridy = 4;
		contentPanel.add(messageLabel, gbc_messageLabel);
				
	}
	
	public void loadData(PersonPageType type, long id) throws SQLException {
		try {
			Person person = null;

			/*
			firstNameField;
			lastNameField;
			*/
			if(type == PersonPageType.EMPLOYEE) {
				Supplier supplier = personCtrl.getSupplierById(id);
				person = supplier;
			}
			
			if(type == PersonPageType.CUSTOMER) {
			}
			
			if(type == PersonPageType.SUPPLIER) {
			}
			
			firstNameField.setText(person.getFirstName());

		} catch (SQLException e) {
			throw e;
		}
	}
}
			

