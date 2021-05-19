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
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class CreateSupplierTest extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField emailField;
	private JTextField phoneField;
	private JTextField cityField;
	private JTextField streetField;
	private JTextField streetNoField;
	private JTextField cvrNoField;
	private JTextField companyNameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateSupplierTest dialog = new CreateSupplierTest();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateSupplierTest() {
		setBounds(100, 100, 740, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{83, 88, 113, 83, 66, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
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
			JLabel lblFirstName = new JLabel("First Name");
			GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
			gbc_lblFirstName.anchor = GridBagConstraints.WEST;
			gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
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
			gbc_lblLastName.anchor = GridBagConstraints.WEST;
			gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
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
			gbc_lblPhone.anchor = GridBagConstraints.WEST;
			gbc_lblPhone.insets = new Insets(0, 0, 5, 5);
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
			gbc_lblCity.anchor = GridBagConstraints.WEST;
			gbc_lblCity.insets = new Insets(0, 0, 5, 5);
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
			gbc_lblStreet.anchor = GridBagConstraints.WEST;
			gbc_lblStreet.insets = new Insets(0, 0, 5, 5);
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
			gbc_lblStreetNo.anchor = GridBagConstraints.WEST;
			gbc_lblStreetNo.insets = new Insets(0, 0, 5, 5);
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
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[]{121, 47, 419, 0, 67, 0};
			gbl_buttonPane.rowHeights = new int[]{23, 0};
			gbl_buttonPane.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_buttonPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			buttonPane.setLayout(gbl_buttonPane);
			{
				JButton btnBack = new JButton("Back");
				GridBagConstraints gbc_btnBack = new GridBagConstraints();
				gbc_btnBack.insets = new Insets(0, 0, 0, 5);
				gbc_btnBack.gridx = 1;
				gbc_btnBack.gridy = 0;
				buttonPane.add(btnBack, gbc_btnBack);
			}
			{
				JButton btnSave = new JButton("Save");
				GridBagConstraints gbc_btnSave = new GridBagConstraints();
				gbc_btnSave.insets = new Insets(0, 0, 0, 5);
				gbc_btnSave.gridx = 3;
				gbc_btnSave.gridy = 0;
				buttonPane.add(btnSave, gbc_btnSave);
			}
		}
	}

}
