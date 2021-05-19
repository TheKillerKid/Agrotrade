package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Controller.ProductController;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.GridBagConstraints;
import java.awt.Panel;
import java.awt.Insets;
import java.awt.Button;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.ComboBoxModel;
import java.awt.Font;

public class ProductPage extends JDialog {

	private Panel buttonsPanel;
	private JButton backBtn;
	private JButton saveBtn;
	private JTextField textField;
	private JTextField nameField;
	private JTextField purchasePriceField;
	private JTextField salePriceField;
	private JTextField leasePriceField;
	
	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductPage dialog = new ProductPage();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ProductPage() {
		
		ProductController productCtrl = new ProductController();
		
		getContentPane().setBackground(SystemColor.menu);
		setBounds(100, 100, 740, 480);

		{
			DefaultComboBoxModel options = new DefaultComboBoxModel();
		}
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		{
			buttonsPanel = new Panel();
			getContentPane().add(buttonsPanel);
			GridBagLayout gbl_buttonsPanel = new GridBagLayout();
			gbl_buttonsPanel.columnWidths = new int[]{0, 75, 242, 75, 0, 0};
			gbl_buttonsPanel.rowHeights = new int[]{29, 0};
			gbl_buttonsPanel.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_buttonsPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			buttonsPanel.setLayout(gbl_buttonsPanel);
			{
				backBtn = new JButton("Back");
				backBtn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						HomePage.start();
						dispose();
					}
				});
				GridBagConstraints gbc_backBtn = new GridBagConstraints();
				gbc_backBtn.anchor = GridBagConstraints.NORTH;
				gbc_backBtn.insets = new Insets(0, 0, 0, 5);
				gbc_backBtn.gridx = 1;
				gbc_backBtn.gridy = 0;
				buttonsPanel.add(backBtn, gbc_backBtn);
			}
			{
				saveBtn = new JButton("Save");
				saveBtn.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						omePage.start();
						dispose();
					}
				});
				GridBagConstraints gbc_saveBtn = new GridBagConstraints();
				gbc_saveBtn.insets = new Insets(0, 0, 0, 5);
				gbc_saveBtn.anchor = GridBagConstraints.NORTH;
				gbc_saveBtn.gridx = 3;
				gbc_saveBtn.gridy = 0;
				buttonsPanel.add(saveBtn, gbc_saveBtn);
			}
		}
		{
			Panel panel = new Panel();
			getContentPane().add(panel, BorderLayout.NORTH);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{-29, 0, 276, 0, 0};
			gbl_panel.rowHeights = new int[]{73, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 94, 0};
			gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JTextPane txtpnRegisterProduct = new JTextPane();
				txtpnRegisterProduct.setFont(new Font("Tahoma", Font.BOLD, 14));
				txtpnRegisterProduct.setText("Register product");
				txtpnRegisterProduct.setBackground(SystemColor.menu);
				GridBagConstraints gbc_txtpnRegisterProduct = new GridBagConstraints();
				gbc_txtpnRegisterProduct.insets = new Insets(0, 0, 5, 5);
				gbc_txtpnRegisterProduct.fill = GridBagConstraints.VERTICAL;
				gbc_txtpnRegisterProduct.gridx = 2;
				gbc_txtpnRegisterProduct.gridy = 1;
				panel.add(txtpnRegisterProduct, gbc_txtpnRegisterProduct);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Barcode:");
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_1.gridx = 1;
				gbc_lblNewLabel_1.gridy = 2;
				panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
			}
			{
				textField = new JTextField();
				textField.setText("0");
				textField.setEditable(false);
				textField.setColumns(10);
				GridBagConstraints gbc_textField = new GridBagConstraints();
				gbc_textField.insets = new Insets(0, 0, 5, 5);
				gbc_textField.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField.gridx = 2;
				gbc_textField.gridy = 2;
				panel.add(textField, gbc_textField);
			}
			{
				JLabel lblNewLabel = new JLabel("Name:");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel.gridx = 1;
				gbc_lblNewLabel.gridy = 3;
				panel.add(lblNewLabel, gbc_lblNewLabel);
			}
			{
				nameField = new JTextField();
				nameField.setColumns(10);
				GridBagConstraints gbc_nameField = new GridBagConstraints();
				gbc_nameField.insets = new Insets(0, 0, 5, 5);
				gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
				gbc_nameField.gridx = 2;
				gbc_nameField.gridy = 3;
				panel.add(nameField, gbc_nameField);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Category");
				GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
				gbc_lblNewLabel_2.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_2.gridx = 1;
				gbc_lblNewLabel_2.gridy = 4;
				panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
			}
			{
				JComboBox categoryComboBox = new JComboBox((ComboBoxModel) null);
				categoryComboBox.setSelectedIndex(0);
				GridBagConstraints gbc_categoryComboBox = new GridBagConstraints();
				gbc_categoryComboBox.insets = new Insets(0, 0, 5, 5);
				gbc_categoryComboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_categoryComboBox.gridx = 2;
				gbc_categoryComboBox.gridy = 4;
				panel.add(categoryComboBox, gbc_categoryComboBox);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("Purchase price:");
				GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
				gbc_lblNewLabel_3.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_3.gridx = 1;
				gbc_lblNewLabel_3.gridy = 5;
				panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
			}
			{
				purchasePriceField = new JTextField();
				purchasePriceField.setColumns(10);
				GridBagConstraints gbc_purchasePriceField = new GridBagConstraints();
				gbc_purchasePriceField.insets = new Insets(0, 0, 5, 5);
				gbc_purchasePriceField.fill = GridBagConstraints.HORIZONTAL;
				gbc_purchasePriceField.gridx = 2;
				gbc_purchasePriceField.gridy = 5;
				panel.add(purchasePriceField, gbc_purchasePriceField);
			}
			{
				JLabel lblNewLabel_4 = new JLabel("Sale price:");
				GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
				gbc_lblNewLabel_4.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_4.gridx = 1;
				gbc_lblNewLabel_4.gridy = 6;
				panel.add(lblNewLabel_4, gbc_lblNewLabel_4);
			}
			{
				salePriceField = new JTextField();
				salePriceField.setColumns(10);
				GridBagConstraints gbc_salePriceField = new GridBagConstraints();
				gbc_salePriceField.insets = new Insets(0, 0, 5, 5);
				gbc_salePriceField.fill = GridBagConstraints.HORIZONTAL;
				gbc_salePriceField.gridx = 2;
				gbc_salePriceField.gridy = 6;
				panel.add(salePriceField, gbc_salePriceField);
			}
			{
				JLabel lblNewLabel_5 = new JLabel("Lease price:");
				GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
				gbc_lblNewLabel_5.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_5.gridx = 1;
				gbc_lblNewLabel_5.gridy = 7;
				panel.add(lblNewLabel_5, gbc_lblNewLabel_5);
			}
			{
				leasePriceField = new JTextField();
				leasePriceField.setColumns(10);
				GridBagConstraints gbc_leasePriceField = new GridBagConstraints();
				gbc_leasePriceField.insets = new Insets(0, 0, 5, 5);
				gbc_leasePriceField.fill = GridBagConstraints.HORIZONTAL;
				gbc_leasePriceField.gridx = 2;
				gbc_leasePriceField.gridy = 7;
				panel.add(leasePriceField, gbc_leasePriceField);
			}
			{
				JLabel lblNewLabel_6 = new JLabel("Unit:");
				GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
				gbc_lblNewLabel_6.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_6.gridx = 1;
				gbc_lblNewLabel_6.gridy = 8;
				panel.add(lblNewLabel_6, gbc_lblNewLabel_6);
			}
			{
				JComboBox unitComboBox = new JComboBox();
				GridBagConstraints gbc_unitComboBox = new GridBagConstraints();
				gbc_unitComboBox.insets = new Insets(0, 0, 5, 5);
				gbc_unitComboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_unitComboBox.gridx = 2;
				gbc_unitComboBox.gridy = 8;
				panel.add(unitComboBox, gbc_unitComboBox);
			}
			{
				JLabel lblNewLabel_7 = new JLabel("Supplier:");
				GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
				gbc_lblNewLabel_7.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_7.gridx = 1;
				gbc_lblNewLabel_7.gridy = 9;
				panel.add(lblNewLabel_7, gbc_lblNewLabel_7);
			}
			{
				JComboBox supplierComboBox = new JComboBox();
				GridBagConstraints gbc_supplierComboBox = new GridBagConstraints();
				gbc_supplierComboBox.insets = new Insets(0, 0, 5, 5);
				gbc_supplierComboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_supplierComboBox.gridx = 2;
				gbc_supplierComboBox.gridy = 9;
				panel.add(supplierComboBox, gbc_supplierComboBox);
			}
		}
	}
}
