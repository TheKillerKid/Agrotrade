package UI;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controller.LoginController;
import Model.DB.DBConnection;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class LoginPage extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private JTextField emailField;
	private JPasswordField passwordField;
	private JLabel messageLabel;
	private JLabel logo;
	private LoginController loginCtrl = new LoginController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		LoginPage.start();
		DBConnection.getInstance().getConnection();
	}
	
	public static void start() {
		try {
			LoginPage dialog = new LoginPage();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void logIn() {
		try {
			String password = String.valueOf(passwordField.getPassword());
			boolean loggedIn = loginCtrl.login(emailField.getText(), password);

			if (loggedIn) {
				dispose();
				HomePage.start();
			}
			else {
				messageLabel.setText("Wrong credentials. Please try again or contact the administrator.");
			}
		} catch (SQLException e) {
				e.printStackTrace();
				messageLabel.setText("Wrong credentials. Please try again or contact the administrator.");
		}
	}

	/**
	 * Create the dialog.
	 */
	public LoginPage() {		
		setBounds(150, 150, 1280, 800);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		
		contentPanel.setBorder(new EmptyBorder(100, 100, 100, 100));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] {114, 0, 275};
		gbl_contentPanel.rowHeights = new int[] {0, 0, 0, 0, 20};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		contentPanel.setLayout(gbl_contentPanel);
		
		logo = new JLabel("");
		GridBagConstraints gbc_logo = new GridBagConstraints();
		gbc_logo.insets = new Insets(0, 0, 5, 0);
		gbc_logo.gridx = 2;
		gbc_logo.gridy = 0;
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setIcon(new ImageIcon(LoginPage.class.getResource("/Resources/logo.PNG")));
		contentPanel.add(logo, gbc_logo);
		
		JLabel lblNewLabel = new JLabel("Email");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		
		emailField = new JTextField();
		emailField.setBackground(UIManager.getColor("Button.background"));
		emailField.setToolTipText("");
		GridBagConstraints gbc_emailField = new GridBagConstraints();
		gbc_emailField.insets = new Insets(0, 0, 5, 0);
		gbc_emailField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailField.gridx = 2;
		gbc_emailField.gridy = 1;
		contentPanel.add(emailField, gbc_emailField);
		emailField.setColumns(10);		
		
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
		
		JButton btnNewButton = new JButton("Log In");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				logIn();
			}
		});
		btnNewButton.addKeyListener(new KeyAdapter() {						
			@Override
			public void keyPressed(KeyEvent e) {
			    if (e.getKeyCode()==KeyEvent.VK_ENTER){
				        logIn();
			    }
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 3;
		contentPanel.add(btnNewButton, gbc_btnNewButton);
		
		messageLabel = new JLabel("");
		messageLabel.setForeground(Color.RED);
		GridBagConstraints gbc_messageLabel = new GridBagConstraints();
		gbc_messageLabel.anchor = GridBagConstraints.WEST;
		gbc_messageLabel.gridx = 2;
		gbc_messageLabel.gridy = 4;
		contentPanel.add(messageLabel, gbc_messageLabel);
	}
}
