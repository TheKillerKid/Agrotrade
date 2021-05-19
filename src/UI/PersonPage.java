package UI;

import java.awt.BorderLayout; 

import java.awt.EventQueue;

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
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Model.Model.PersonPageType;

public class PersonPage extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Panel buttonsPanel;
	private JTextField cprNoField;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField emailField;
	private JTextField phoneField;
	private JTextField cityField;
	private JTextField streetField;
	private JTextField streetNoField;
	private JTextField departmentField;
	private JTextField positionField;
	private JTextField cvrNoField;
	private JTextField companyNameField;
	private JButton btnBack;
	private JButton btnSave;
	private HomePage homePage = new HomePage();

	public static void start(PersonPageType type) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonPage dialog = new PersonPage(type);
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
	public PersonPage(PersonPageType type) {
		setBounds(100, 100, 740, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 106, 140, 110, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		{
			JLabel lblFirstName = new JLabel("First Name");
			GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
			gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
			gbc_lblFirstName.anchor = GridBagConstraints.WEST;
			gbc_lblFirstName.gridx = 1;
			gbc_lblFirstName.gridy = 3;
			contentPanel.add(lblFirstName, gbc_lblFirstName);
		}
		{
			firstNameField = new JTextField();
			GridBagConstraints gbc_firstNameField = new GridBagConstraints();
			gbc_firstNameField.insets = new Insets(0, 0, 5, 5);
			gbc_firstNameField.fill = GridBagConstraints.HORIZONTAL;
			gbc_firstNameField.gridx = 2;
			gbc_firstNameField.gridy = 3;
			contentPanel.add(firstNameField, gbc_firstNameField);
			firstNameField.setColumns(10);
		}
		{
			JLabel lblLastName = new JLabel("LastName");
			GridBagConstraints gbc_lblLastName = new GridBagConstraints();
			gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
			gbc_lblLastName.anchor = GridBagConstraints.WEST;
			gbc_lblLastName.gridx = 1;
			gbc_lblLastName.gridy = 4;
			contentPanel.add(lblLastName, gbc_lblLastName);
		}
		{
			lastNameField = new JTextField();
			GridBagConstraints gbc_lastNameField = new GridBagConstraints();
			gbc_lastNameField.insets = new Insets(0, 0, 5, 5);
			gbc_lastNameField.fill = GridBagConstraints.HORIZONTAL;
			gbc_lastNameField.gridx = 2;
			gbc_lastNameField.gridy = 4;
			contentPanel.add(lastNameField, gbc_lastNameField);
			lastNameField.setColumns(10);
		}
		{
			JLabel lblEmail = new JLabel("Email");
			GridBagConstraints gbc_lblEmail = new GridBagConstraints();
			gbc_lblEmail.anchor = GridBagConstraints.WEST;
			gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
			gbc_lblEmail.gridx = 1;
			gbc_lblEmail.gridy = 5;
			contentPanel.add(lblEmail, gbc_lblEmail);
		}
		{
			emailField = new JTextField();
			GridBagConstraints gbc_emailField = new GridBagConstraints();
			gbc_emailField.insets = new Insets(0, 0, 5, 5);
			gbc_emailField.fill = GridBagConstraints.HORIZONTAL;
			gbc_emailField.gridx = 2;
			gbc_emailField.gridy = 5;
			contentPanel.add(emailField, gbc_emailField);
			emailField.setColumns(10);
		}
		{
			JLabel lblPhone = new JLabel("Phone");
			GridBagConstraints gbc_lblPhone = new GridBagConstraints();
			gbc_lblPhone.insets = new Insets(0, 0, 5, 5);
			gbc_lblPhone.anchor = GridBagConstraints.WEST;
			gbc_lblPhone.gridx = 1;
			gbc_lblPhone.gridy = 6;
			contentPanel.add(lblPhone, gbc_lblPhone);
		}
		{
			phoneField = new JTextField();
			GridBagConstraints gbc_phoneField = new GridBagConstraints();
			gbc_phoneField.insets = new Insets(0, 0, 5, 5);
			gbc_phoneField.fill = GridBagConstraints.HORIZONTAL;
			gbc_phoneField.gridx = 2;
			gbc_phoneField.gridy = 6;
			contentPanel.add(phoneField, gbc_phoneField);
			phoneField.setColumns(10);
		}
		{
			JLabel lblCity = new JLabel("City");
			GridBagConstraints gbc_lblCity = new GridBagConstraints();
			gbc_lblCity.insets = new Insets(0, 0, 5, 5);
			gbc_lblCity.anchor = GridBagConstraints.WEST;
			gbc_lblCity.gridx = 1;
			gbc_lblCity.gridy = 7;
			contentPanel.add(lblCity, gbc_lblCity);
		}
		{
			cityField = new JTextField();
			GridBagConstraints gbc_textField_4 = new GridBagConstraints();
			gbc_textField_4.insets = new Insets(0, 0, 5, 5);
			gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_4.gridx = 2;
			gbc_textField_4.gridy = 7;
			contentPanel.add(cityField, gbc_textField_4);
			cityField.setColumns(10);
		}
		{
			JLabel lblStreet = new JLabel("Street");
			GridBagConstraints gbc_lblStreet = new GridBagConstraints();
			gbc_lblStreet.insets = new Insets(0, 0, 5, 5);
			gbc_lblStreet.anchor = GridBagConstraints.WEST;
			gbc_lblStreet.gridx = 1;
			gbc_lblStreet.gridy = 8;
			contentPanel.add(lblStreet, gbc_lblStreet);
		}
		{
			streetField = new JTextField();
			GridBagConstraints gbc_streetField = new GridBagConstraints();
			gbc_streetField.insets = new Insets(0, 0, 5, 5);
			gbc_streetField.fill = GridBagConstraints.HORIZONTAL;
			gbc_streetField.gridx = 2;
			gbc_streetField.gridy = 8;
			contentPanel.add(streetField, gbc_streetField);
			streetField.setColumns(10);
		}
		{
			JLabel lblStreetNo = new JLabel("Street No");
			GridBagConstraints gbc_lblStreetNo = new GridBagConstraints();
			gbc_lblStreetNo.insets = new Insets(0, 0, 5, 5);
			gbc_lblStreetNo.anchor = GridBagConstraints.WEST;
			gbc_lblStreetNo.gridx = 1;
			gbc_lblStreetNo.gridy = 9;
			contentPanel.add(lblStreetNo, gbc_lblStreetNo);
		}
		{
			streetNoField = new JTextField();
			GridBagConstraints gbc_streetNoField = new GridBagConstraints();
			gbc_streetNoField.insets = new Insets(0, 0, 5, 5);
			gbc_streetNoField.fill = GridBagConstraints.HORIZONTAL;
			gbc_streetNoField.gridx = 2;
			gbc_streetNoField.gridy = 9;
			contentPanel.add(streetNoField, gbc_streetNoField);
			streetNoField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[]{107, 72, 325, 65, 87, 0};
			gbl_buttonPane.rowHeights = new int[]{23, 0};
			gbl_buttonPane.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_buttonPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			buttonPane.setLayout(gbl_buttonPane);
			{
				btnBack = new JButton("Back");
				GridBagConstraints gbc_btnBack = new GridBagConstraints();
				gbc_btnBack.insets = new Insets(0, 0, 0, 5);
				gbc_btnBack.gridx = 1;
				gbc_btnBack.gridy = 0;
				buttonPane.add(btnBack, gbc_btnBack);
			}
			{
				btnSave = new JButton("Save");
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
				lblCreateEmployee.setFont(new Font("Tahoma", Font.BOLD, 14));
				GridBagConstraints gbc_lblCreateEmployee = new GridBagConstraints();
				gbc_lblCreateEmployee.insets = new Insets(0, 0, 5, 5);
				gbc_lblCreateEmployee.gridx = 2;
				gbc_lblCreateEmployee.gridy = 1;
				contentPanel.add(lblCreateEmployee, gbc_lblCreateEmployee);
			}
			{
				JLabel lblNewLabel = new JLabel("CPR No");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel.gridx = 1;
				gbc_lblNewLabel.gridy = 2;
				contentPanel.add(lblNewLabel, gbc_lblNewLabel);
			}
			{
				cprNoField = new JTextField();
				GridBagConstraints gbc_cprNoField = new GridBagConstraints();
				gbc_cprNoField.insets = new Insets(0, 0, 5, 5);
				gbc_cprNoField.fill = GridBagConstraints.HORIZONTAL;
				gbc_cprNoField.gridx = 2;
				gbc_cprNoField.gridy = 2;
				contentPanel.add(cprNoField, gbc_cprNoField);
				cprNoField.setColumns(10);
			}
			{
				JLabel lblDepartment = new JLabel("Deparment");
				GridBagConstraints gbc_lblDepartment = new GridBagConstraints();
				gbc_lblDepartment.anchor = GridBagConstraints.WEST;
				gbc_lblDepartment.insets = new Insets(0, 0, 5, 5);
				gbc_lblDepartment.gridx = 1;
				gbc_lblDepartment.gridy = 10;
				contentPanel.add(lblDepartment, gbc_lblDepartment);
			}
			{
				departmentField = new JTextField();
				GridBagConstraints gbc_departmentField = new GridBagConstraints();
				gbc_departmentField.insets = new Insets(0, 0, 5, 5);
				gbc_departmentField.fill = GridBagConstraints.HORIZONTAL;
				gbc_departmentField.gridx = 2;
				gbc_departmentField.gridy = 10;
				contentPanel.add(departmentField, gbc_departmentField);
				departmentField.setColumns(10);
			}
			{
				JLabel lblPosition = new JLabel("Position");
				GridBagConstraints gbc_lblPosition = new GridBagConstraints();
				gbc_lblPosition.anchor = GridBagConstraints.WEST;
				gbc_lblPosition.insets = new Insets(0, 0, 5, 5);
				gbc_lblPosition.gridx = 1;
				gbc_lblPosition.gridy = 11;
				contentPanel.add(lblPosition, gbc_lblPosition);
			}
			{
				positionField = new JTextField();
				GridBagConstraints gbc_positionField = new GridBagConstraints();
				gbc_positionField.insets = new Insets(0, 0, 5, 5);
				gbc_positionField.fill = GridBagConstraints.HORIZONTAL;
				gbc_positionField.gridx = 2;
				gbc_positionField.gridy = 11;
				contentPanel.add(positionField, gbc_positionField);
				positionField.setColumns(10);
			}
		}
		
		if(type == PersonPageType.SUPPLIER) {
			{
				JLabel lblCreateSupplier = new JLabel("Create Supplier");
				lblCreateSupplier.setFont(new Font("Tahoma", Font.BOLD, 14));
				GridBagConstraints gbc_lblCreateSupplier = new GridBagConstraints();
				gbc_lblCreateSupplier.insets = new Insets(0, 0, 5, 5);
				gbc_lblCreateSupplier.gridx = 2;
				gbc_lblCreateSupplier.gridy = 1;
				contentPanel.add(lblCreateSupplier, gbc_lblCreateSupplier);
			}
			{
				JLabel lblCVRNo = new JLabel("CVR No");
				GridBagConstraints gbc_lblCVRNo = new GridBagConstraints();
				gbc_lblCVRNo.anchor = GridBagConstraints.WEST;
				gbc_lblCVRNo.insets = new Insets(0, 0, 5, 5);
				gbc_lblCVRNo.gridx = 1;
				gbc_lblCVRNo.gridy = 10;
				contentPanel.add(lblCVRNo, gbc_lblCVRNo);
			}
			{
				cvrNoField = new JTextField();
				GridBagConstraints gbc_cvrNoField = new GridBagConstraints();
				gbc_cvrNoField.insets = new Insets(0, 0, 5, 5);
				gbc_cvrNoField.fill = GridBagConstraints.HORIZONTAL;
				gbc_cvrNoField.gridx = 2;
				gbc_cvrNoField.gridy = 10;
				contentPanel.add(cvrNoField, gbc_cvrNoField);
				cvrNoField.setColumns(10);
			}
			{
				JLabel lblCompanyName = new JLabel("Company Name");
				GridBagConstraints gbc_lblCompanyName = new GridBagConstraints();
				gbc_lblCompanyName.anchor = GridBagConstraints.WEST;
				gbc_lblCompanyName.insets = new Insets(0, 0, 0, 5);
				gbc_lblCompanyName.gridx = 1;
				gbc_lblCompanyName.gridy = 11;
				contentPanel.add(lblCompanyName, gbc_lblCompanyName);
			}
			{
				companyNameField = new JTextField();
				GridBagConstraints gbc_companyNameField = new GridBagConstraints();
				gbc_companyNameField.insets = new Insets(0, 0, 0, 5);
				gbc_companyNameField.fill = GridBagConstraints.HORIZONTAL;
				gbc_companyNameField.gridx = 2;
				gbc_companyNameField.gridy = 11;
				contentPanel.add(companyNameField, gbc_companyNameField);
				companyNameField.setColumns(10);
			}
			
		}
		if(type == PersonPageType.CUSTOMER) {
			
			{
				JLabel lblCreateCustomer = new JLabel("Create Customer");
				lblCreateCustomer.setFont(new Font("Tahoma", Font.BOLD, 14));
				GridBagConstraints gbc_lblCreateCustomer = new GridBagConstraints();
				gbc_lblCreateCustomer.insets = new Insets(0, 0, 5, 5);
				gbc_lblCreateCustomer.gridx = 2;
				gbc_lblCreateCustomer.gridy = 1;
				contentPanel.add(lblCreateCustomer, gbc_lblCreateCustomer);
			}
			
			{
				JLabel lblCvrNo = new JLabel("CVR No");
				GridBagConstraints gbc_lblCvrNo = new GridBagConstraints();
				gbc_lblCvrNo.anchor = GridBagConstraints.WEST;
				gbc_lblCvrNo.insets = new Insets(0, 0, 5, 5);
				gbc_lblCvrNo.gridx = 1;
				gbc_lblCvrNo.gridy = 10;
				contentPanel.add(lblCvrNo, gbc_lblCvrNo);
			}
			{
				cvrNoField = new JTextField();
				GridBagConstraints gbc_cvrNoField = new GridBagConstraints();
				gbc_cvrNoField.insets = new Insets(0, 0, 5, 5);
				gbc_cvrNoField.fill = GridBagConstraints.HORIZONTAL;
				gbc_cvrNoField.gridx = 2;
				gbc_cvrNoField.gridy = 10;
				contentPanel.add(cvrNoField, gbc_cvrNoField);
				cvrNoField.setColumns(10);
			}
		}
	}
			
}
