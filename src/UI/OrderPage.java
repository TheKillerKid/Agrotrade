package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;

import Model.Model.StockProduct;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JList;

import Controller.OrderController;
import Model.Model.LoginContainer;

public class OrderPage extends JDialog {
	private OrderController orderCtrl = new OrderController();
	private final JPanel contentPanel = new JPanel();
	private JTextField customerCVRLbl;
	private JTextField textField_2;
	private JTextField textField_3;
	
	
	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderPage dialog = new OrderPage();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					throw e;
				}
			}
		});
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			OrderPage dialog = new OrderPage();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Create the dialog.
	 */
	public OrderPage() {
		
		//loaddata method
		
		setBounds(100, 100, 740, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 100, 259, 84, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 45, 0, 28, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("Create Order");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 2;
			gbc_lblNewLabel.gridy = 1;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Customer CVR");
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 1;
			gbc_lblNewLabel_1.gridy = 2;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			customerCVRLbl = new JTextField();
			GridBagConstraints gbc_customerCVRLbl = new GridBagConstraints();
			gbc_customerCVRLbl.insets = new Insets(0, 0, 5, 5);
			gbc_customerCVRLbl.fill = GridBagConstraints.HORIZONTAL;
			gbc_customerCVRLbl.gridx = 2;
			gbc_customerCVRLbl.gridy = 2;
			contentPanel.add(customerCVRLbl, gbc_customerCVRLbl);
			customerCVRLbl.setColumns(10);
		}
		{
			JButton btnCheck = new JButton("Check");
			GridBagConstraints gbc_btnCheck = new GridBagConstraints();
			gbc_btnCheck.anchor = GridBagConstraints.WEST;
			gbc_btnCheck.insets = new Insets(0, 0, 5, 5);
			gbc_btnCheck.gridx = 3;
			gbc_btnCheck.gridy = 2;
			contentPanel.add(btnCheck, gbc_btnCheck);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Customer Name");
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_2.gridx = 1;
			gbc_lblNewLabel_2.gridy = 3;
			contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		}
		{
			JLabel customerNameLbl = new JLabel("New label");
			GridBagConstraints gbc_customerNameLbl = new GridBagConstraints();
			gbc_customerNameLbl.anchor = GridBagConstraints.WEST;
			gbc_customerNameLbl.insets = new Insets(0, 0, 5, 5);
			gbc_customerNameLbl.gridx = 2;
			gbc_customerNameLbl.gridy = 3;
			contentPanel.add(customerNameLbl, gbc_customerNameLbl);
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Add Products", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridwidth = 3;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 4;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{100, 259, 84, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblNewLabel_4 = new JLabel("Select Product");
				GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
				gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_4.gridx = 0;
				gbc_lblNewLabel_4.gridy = 0;
				panel.add(lblNewLabel_4, gbc_lblNewLabel_4);
			}
			{
				// How to get name from product ArrayList<StockProduct> stockProducts here into combobox
				// new JComboBox(array);
				JComboBox comboBox = new JComboBox();
				//comboBox.setModel(new ComboBoxModel());
				GridBagConstraints gbc_comboBox = new GridBagConstraints();
				gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBox.insets = new Insets(0, 0, 5, 5);
				gbc_comboBox.gridx = 1;
				gbc_comboBox.gridy = 0;
				panel.add(comboBox, gbc_comboBox);
			}
			{
				JLabel lblNewLabel_5 = new JLabel("On Stock");
				GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
				gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_5.gridx = 0;
				gbc_lblNewLabel_5.gridy = 1;
				panel.add(lblNewLabel_5, gbc_lblNewLabel_5);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("New label");
				GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
				gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_3.gridx = 1;
				gbc_lblNewLabel_3.gridy = 1;
				panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
			}
			{
				JLabel lblNewLabel_5 = new JLabel("Quantity");
				GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
				gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_5.insets = new Insets(0, 0, 0, 5);
				gbc_lblNewLabel_5.gridx = 0;
				gbc_lblNewLabel_5.gridy = 2;
				panel.add(lblNewLabel_5, gbc_lblNewLabel_5);
			}
			{
				textField_2 = new JTextField();
				GridBagConstraints gbc_textField_2 = new GridBagConstraints();
				gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField_2.insets = new Insets(0, 0, 0, 5);
				gbc_textField_2.gridx = 1;
				gbc_textField_2.gridy = 2;
				panel.add(textField_2, gbc_textField_2);
				textField_2.setColumns(10);
			}
			{
				JButton btnAdd = new JButton("Add");
				GridBagConstraints gbc_btnAdd = new GridBagConstraints();
				gbc_btnAdd.gridx = 2;
				gbc_btnAdd.gridy = 2;
				panel.add(btnAdd, gbc_btnAdd);
			}
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Note");
			GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
			gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_3.gridx = 1;
			gbc_lblNewLabel_3.gridy = 5;
			contentPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		}
		{
			textField_3 = new JTextField();
			textField_3.setColumns(10);
			GridBagConstraints gbc_textField_3 = new GridBagConstraints();
			gbc_textField_3.insets = new Insets(0, 0, 5, 5);
			gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_3.gridx = 2;
			gbc_textField_3.gridy = 5;
			contentPanel.add(textField_3, gbc_textField_3);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[]{81, 70, 311, 80, 84, 0};
			gbl_buttonPane.rowHeights = new int[]{23, 0};
			gbl_buttonPane.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_buttonPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			buttonPane.setLayout(gbl_buttonPane);
			{
				JButton backBtn = new JButton("Back");
				GridBagConstraints gbc_backBtn = new GridBagConstraints();
				gbc_backBtn.anchor = GridBagConstraints.EAST;
				gbc_backBtn.insets = new Insets(0, 0, 0, 5);
				gbc_backBtn.gridx = 1;
				gbc_backBtn.gridy = 0;
				buttonPane.add(backBtn, gbc_backBtn);
				backBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
			}
			{
				JButton saveBtn = new JButton("Regitster Order");
				GridBagConstraints gbc_saveBtn = new GridBagConstraints();
				gbc_saveBtn.anchor = GridBagConstraints.WEST;
				gbc_saveBtn.insets = new Insets(0, 0, 0, 5);
				gbc_saveBtn.gridx = 3;
				gbc_saveBtn.gridy = 0;
				buttonPane.add(saveBtn, gbc_saveBtn);
			}
		}
	}
	
	//loadData method. Is this the right way?
	public void loadData () {		
	//how to get the warehouseId from LoginContainer
		
	//long warehouseId = loginContainer get  
	//orderCtrl.getStockProducts(warehouseId);
	}
}