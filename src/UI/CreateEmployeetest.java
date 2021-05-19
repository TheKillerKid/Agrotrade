package UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

public class CreateEmployeetest extends JDialog {

	private final JPanel contentPanel = new JPanel();
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateEmployeetest dialog = new CreateEmployeetest();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateEmployeetest() {
		setBounds(100, 100, 740, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 65, 146, 69, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
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
			JLabel lblLastName = new JLabel("Last Name");
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
			gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
			gbc_lblEmail.anchor = GridBagConstraints.WEST;
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
			GridBagConstraints gbc_cityField = new GridBagConstraints();
			gbc_cityField.insets = new Insets(0, 0, 5, 5);
			gbc_cityField.fill = GridBagConstraints.HORIZONTAL;
			gbc_cityField.gridx = 2;
			gbc_cityField.gridy = 7;
			contentPanel.add(cityField, gbc_cityField);
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
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[]{105, 16, 306, 84, 0, 0};
			gbl_buttonPane.rowHeights = new int[]{23, 0};
			gbl_buttonPane.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_buttonPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			buttonPane.setLayout(gbl_buttonPane);
			{
				JButton BackBtn = new JButton("Back");
				GridBagConstraints gbc_BackBtn = new GridBagConstraints();
				gbc_BackBtn.insets = new Insets(0, 0, 0, 5);
				gbc_BackBtn.gridx = 1;
				gbc_BackBtn.gridy = 0;
				buttonPane.add(BackBtn, gbc_BackBtn);
			}
			{
				JButton SaveBtn = new JButton("Save");
				SaveBtn.setActionCommand("Cancel");
				GridBagConstraints gbc_SaveBtn = new GridBagConstraints();
				gbc_SaveBtn.insets = new Insets(0, 0, 0, 5);
				gbc_SaveBtn.anchor = GridBagConstraints.NORTHWEST;
				gbc_SaveBtn.gridx = 3;
				gbc_SaveBtn.gridy = 0;
				buttonPane.add(SaveBtn, gbc_SaveBtn);
			}
		}
	}

}
