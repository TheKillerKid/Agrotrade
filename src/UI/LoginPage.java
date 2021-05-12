package UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.JSplitPane;
import javax.swing.UIManager;

public class LoginPage extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private JTextField txtLabel;
	private JPasswordField passwordField;
	private JLabel message;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LoginPage dialog = new LoginPage();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LoginPage() {
		setBounds(300, 300, 750, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		
		contentPanel.setBorder(new EmptyBorder(100, 100, 100, 100));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] {114, 0, 275};
		gbl_contentPanel.rowHeights = new int[] {0, 0, 0, 0, 0, 30};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		contentPanel.setLayout(gbl_contentPanel);
		
		lblNewLabel_2 = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 0;
		contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon("/Users/adampetricek/eclipse-workspace/Agrotrade/logo.PNG"));
		
		JLabel lblNewLabel = new JLabel("Email");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		
				txtLabel = new JTextField();
				txtLabel.setBackground(UIManager.getColor("Button.background"));
				txtLabel.setToolTipText("");
				GridBagConstraints gbc_txtLabel = new GridBagConstraints();
				gbc_txtLabel.insets = new Insets(0, 0, 5, 0);
				gbc_txtLabel.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtLabel.gridx = 2;
				gbc_txtLabel.gridy = 1;
				contentPanel.add(txtLabel, gbc_txtLabel);
				txtLabel.setColumns(10);		
				
				JLabel lblNewLabel_1 = new JLabel("Password");
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_1.gridx = 0;
				gbc_lblNewLabel_1.gridy = 2;
				contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
				
					passwordField = new JPasswordField();
					passwordField.setBackground(UIManager.getColor("Button.background"));
					GridBagConstraints gbc_passwordField = new GridBagConstraints();
					gbc_passwordField.insets = new Insets(0, 0, 5, 0);
					gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
					gbc_passwordField.gridx = 2;
					gbc_passwordField.gridy = 2;
					contentPanel.add(passwordField, gbc_passwordField);
					
					message = new JLabel("");
					GridBagConstraints gbc_message = new GridBagConstraints();
					gbc_message.insets = new Insets(0, 0, 5, 0);
					gbc_message.anchor = GridBagConstraints.WEST;
					gbc_message.gridx = 2;
					gbc_message.gridy = 3;
					contentPanel.add(message, gbc_message);
					
					JButton btnNewButton = new JButton("Log In");
					btnNewButton.setBackground(Color.WHITE);
					GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
					gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
					gbc_btnNewButton.anchor = GridBagConstraints.EAST;
					gbc_btnNewButton.gridx = 2;
					gbc_btnNewButton.gridy = 4;
					contentPanel.add(btnNewButton, gbc_btnNewButton);
	}

}
