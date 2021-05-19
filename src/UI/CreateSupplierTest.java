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
	JPanel panel = new JPanel();
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField emailField;
	private JTextField phoneField;
	private JTextField textField_4;
	private JTextField streetField;
	private JTextField streetNoField;
	private JTextField cvrNoField;
	private JTextField companyNameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateCustomerTest dialog = new CreateCustomerTest();
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
			gbl_panel.rowHeights = new int[]{45, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblCreateSupplier = new JLabel("Create Supplier");
				GridBagConstraints gbc_lblCreateSupplier = new GridBagConstraints();
				gbc_lblCreateSupplier.insets = new Insets(0, 0, 5, 0);
				gbc_lblCreateSupplier.gridx = 1;
				gbc_lblCreateSupplier.gridy = 0;
				panel.add(lblCreateSupplier, gbc_lblCreateSupplier);
				lblCreateSupplier.setFont(new Font("Tahoma", Font.BOLD, 14));
			}
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
				JLabel lblLastName = new JLabel("LastName");
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
				textField_4 = new JTextField();
				GridBagConstraints gbc_textField_4 = new GridBagConstraints();
				gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField_4.insets = new Insets(0, 0, 5, 0);
				gbc_textField_4.gridx = 1;
				gbc_textField_4.gridy = 5;
				panel.add(textField_4, gbc_textField_4);
				textField_4.setColumns(10);
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
				JLabel lblCvrNo = new JLabel("CVR No");
				GridBagConstraints gbc_lblCvrNo = new GridBagConstraints();
				gbc_lblCvrNo.anchor = GridBagConstraints.WEST;
				gbc_lblCvrNo.insets = new Insets(0, 0, 5, 5);
				gbc_lblCvrNo.gridx = 0;
				gbc_lblCvrNo.gridy = 8;
				panel.add(lblCvrNo, gbc_lblCvrNo);
			}
			{
				cvrNoField = new JTextField();
				GridBagConstraints gbc_cvrNoField = new GridBagConstraints();
				gbc_cvrNoField.insets = new Insets(0, 0, 5, 0);
				gbc_cvrNoField.fill = GridBagConstraints.HORIZONTAL;
				gbc_cvrNoField.gridx = 1;
				gbc_cvrNoField.gridy = 8;
				panel.add(cvrNoField, gbc_cvrNoField);
				cvrNoField.setColumns(10);
			}
		}
		{
			JLabel lblCompanyName = new JLabel("CompanyName");
			GridBagConstraints gbc_lblCompanyName = new GridBagConstraints();
			gbc_lblCompanyName.anchor = GridBagConstraints.WEST;
			gbc_lblCompanyName.insets = new Insets(0, 0, 5, 5);
			gbc_lblCompanyName.gridx = 0;
			gbc_lblCompanyName.gridy = 9;
			panel.add(lblCompanyName, gbc_lblCompanyName);
		}
		{
			companyNameField = new JTextField();
			GridBagConstraints gbc_companyNameField = new GridBagConstraints();
			gbc_companyNameField.insets = new Insets(0, 0, 5, 0);
			gbc_companyNameField.fill = GridBagConstraints.HORIZONTAL;
			gbc_companyNameField.gridx = 1;
			gbc_companyNameField.gridy = 9;
			panel.add(companyNameField, gbc_companyNameField);
			companyNameField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[]{107, 72, 325, 65, 87, 0};
			gbl_buttonPane.rowHeights = new int[]{40, 0};
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
