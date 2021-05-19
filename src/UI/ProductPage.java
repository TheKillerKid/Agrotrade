package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Controller.ProductController;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
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

public class ProductPage extends JDialog {
	private JTextField barcodeTextField;
	private JTextField nameTextField;
	private JTextField purchasePriceTextField;
	private JTextField salePriceTextField;
	private JTextField leasePriceTextField;
	
	
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

	public ProductPage() throws SQLException {
		
		ProductController productCtrl = new ProductController();
		
		getContentPane().setBackground(SystemColor.menu);
		setBounds(100, 100, 740, 480);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{23, 0, 245, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		{
			JTextPane txtpnRegisterProduct = new JTextPane();
			txtpnRegisterProduct.setBackground(SystemColor.menu);
			txtpnRegisterProduct.setText("Register product");
			GridBagConstraints gbc_txtpnRegisterProduct = new GridBagConstraints();
			gbc_txtpnRegisterProduct.insets = new Insets(0, 0, 5, 5);
			gbc_txtpnRegisterProduct.fill = GridBagConstraints.BOTH;
			gbc_txtpnRegisterProduct.gridx = 2;
			gbc_txtpnRegisterProduct.gridy = 1;
			getContentPane().add(txtpnRegisterProduct, gbc_txtpnRegisterProduct);
		}
		{
			JTextPane txtpnBarcode = new JTextPane();
			txtpnBarcode.setBackground(SystemColor.menu);
			txtpnBarcode.setText("Barcode:");
			GridBagConstraints gbc_txtpnBarcode = new GridBagConstraints();
			gbc_txtpnBarcode.insets = new Insets(0, 0, 5, 5);
			gbc_txtpnBarcode.fill = GridBagConstraints.BOTH;
			gbc_txtpnBarcode.gridx = 1;
			gbc_txtpnBarcode.gridy = 2;
			getContentPane().add(txtpnBarcode, gbc_txtpnBarcode);
		}
		{
			barcodeTextField = new JTextField();
				try {
					barcodeTextField.setText(Long.toString(productCtrl.generateBarcode()));
				} catch (SQLException e) {
					throw e;
				}
			barcodeTextField.setEditable(false);
			GridBagConstraints gbc_barcodeTextField = new GridBagConstraints();
			gbc_barcodeTextField.insets = new Insets(0, 0, 5, 5);
			gbc_barcodeTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_barcodeTextField.gridx = 2;
			gbc_barcodeTextField.gridy = 2;
			getContentPane().add(barcodeTextField, gbc_barcodeTextField);
			barcodeTextField.setColumns(10);
		}
		{
			JTextPane txtpnName = new JTextPane();
			txtpnName.setBackground(SystemColor.menu);
			txtpnName.setText("Name:");
			GridBagConstraints gbc_txtpnName = new GridBagConstraints();
			gbc_txtpnName.insets = new Insets(0, 0, 5, 5);
			gbc_txtpnName.fill = GridBagConstraints.BOTH;
			gbc_txtpnName.gridx = 1;
			gbc_txtpnName.gridy = 3;
			getContentPane().add(txtpnName, gbc_txtpnName);
		}
		{
			nameTextField = new JTextField();
			GridBagConstraints gbc_nameTextField = new GridBagConstraints();
			gbc_nameTextField.insets = new Insets(0, 0, 5, 5);
			gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_nameTextField.gridx = 2;
			gbc_nameTextField.gridy = 3;
			getContentPane().add(nameTextField, gbc_nameTextField);
			nameTextField.setColumns(10);
		}
		{
			JTextPane txtpnCategory = new JTextPane();
			txtpnCategory.setBackground(SystemColor.menu);
			txtpnCategory.setText("Category:");
			GridBagConstraints gbc_txtpnCategory = new GridBagConstraints();
			gbc_txtpnCategory.insets = new Insets(0, 0, 5, 5);
			gbc_txtpnCategory.fill = GridBagConstraints.BOTH;
			gbc_txtpnCategory.gridx = 1;
			gbc_txtpnCategory.gridy = 4;
			getContentPane().add(txtpnCategory, gbc_txtpnCategory);
		}
		{
			JComboBox categoryComboBox = new JComboBox();
			GridBagConstraints gbc_categoryComboBox = new GridBagConstraints();
			gbc_categoryComboBox.insets = new Insets(0, 0, 5, 5);
			gbc_categoryComboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_categoryComboBox.gridx = 2;
			gbc_categoryComboBox.gridy = 4;
			getContentPane().add(categoryComboBox, gbc_categoryComboBox);
		}
		{
			JTextPane txtpnPurchasePrice = new JTextPane();
			txtpnPurchasePrice.setBackground(SystemColor.menu);
			txtpnPurchasePrice.setText("Purchase price:");
			GridBagConstraints gbc_txtpnPurchasePrice = new GridBagConstraints();
			gbc_txtpnPurchasePrice.insets = new Insets(0, 0, 5, 5);
			gbc_txtpnPurchasePrice.fill = GridBagConstraints.BOTH;
			gbc_txtpnPurchasePrice.gridx = 1;
			gbc_txtpnPurchasePrice.gridy = 5;
			getContentPane().add(txtpnPurchasePrice, gbc_txtpnPurchasePrice);
		}
		{
			purchasePriceTextField = new JTextField();
			GridBagConstraints gbc_purchasePriceTextField = new GridBagConstraints();
			gbc_purchasePriceTextField.insets = new Insets(0, 0, 5, 5);
			gbc_purchasePriceTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_purchasePriceTextField.gridx = 2;
			gbc_purchasePriceTextField.gridy = 5;
			getContentPane().add(purchasePriceTextField, gbc_purchasePriceTextField);
			purchasePriceTextField.setColumns(10);
		}
		{
			JTextPane txtpnSalePrice = new JTextPane();
			txtpnSalePrice.setBackground(SystemColor.menu);
			txtpnSalePrice.setText("Sale price:");
			GridBagConstraints gbc_txtpnSalePrice = new GridBagConstraints();
			gbc_txtpnSalePrice.insets = new Insets(0, 0, 5, 5);
			gbc_txtpnSalePrice.fill = GridBagConstraints.BOTH;
			gbc_txtpnSalePrice.gridx = 1;
			gbc_txtpnSalePrice.gridy = 6;
			getContentPane().add(txtpnSalePrice, gbc_txtpnSalePrice);
		}
		{
			salePriceTextField = new JTextField();
			GridBagConstraints gbc_salePriceTextField = new GridBagConstraints();
			gbc_salePriceTextField.insets = new Insets(0, 0, 5, 5);
			gbc_salePriceTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_salePriceTextField.gridx = 2;
			gbc_salePriceTextField.gridy = 6;
			getContentPane().add(salePriceTextField, gbc_salePriceTextField);
			salePriceTextField.setColumns(10);
		}
		{
			JTextPane txtpnLeasePrice = new JTextPane();
			txtpnLeasePrice.setBackground(SystemColor.menu);
			txtpnLeasePrice.setText("Lease price:");
			GridBagConstraints gbc_txtpnLeasePrice = new GridBagConstraints();
			gbc_txtpnLeasePrice.insets = new Insets(0, 0, 5, 5);
			gbc_txtpnLeasePrice.fill = GridBagConstraints.BOTH;
			gbc_txtpnLeasePrice.gridx = 1;
			gbc_txtpnLeasePrice.gridy = 7;
			getContentPane().add(txtpnLeasePrice, gbc_txtpnLeasePrice);
		}
		{
			leasePriceTextField = new JTextField();
			GridBagConstraints gbc_leasePriceTextField = new GridBagConstraints();
			gbc_leasePriceTextField.insets = new Insets(0, 0, 5, 5);
			gbc_leasePriceTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_leasePriceTextField.gridx = 2;
			gbc_leasePriceTextField.gridy = 7;
			getContentPane().add(leasePriceTextField, gbc_leasePriceTextField);
			leasePriceTextField.setColumns(10);
		}
		{
			JTextPane txtpnUnit = new JTextPane();
			txtpnUnit.setBackground(SystemColor.menu);
			txtpnUnit.setText("Unit:");
			GridBagConstraints gbc_txtpnUnit = new GridBagConstraints();
			gbc_txtpnUnit.insets = new Insets(0, 0, 5, 5);
			gbc_txtpnUnit.fill = GridBagConstraints.BOTH;
			gbc_txtpnUnit.gridx = 1;
			gbc_txtpnUnit.gridy = 8;
			getContentPane().add(txtpnUnit, gbc_txtpnUnit);
		}
		{
			JComboBox unitComboBox = new JComboBox();
			GridBagConstraints gbc_unitComboBox = new GridBagConstraints();
			gbc_unitComboBox.insets = new Insets(0, 0, 5, 5);
			gbc_unitComboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_unitComboBox.gridx = 2;
			gbc_unitComboBox.gridy = 8;
			getContentPane().add(unitComboBox, gbc_unitComboBox);
		}
		{
			JTextPane txtpnSupplier = new JTextPane();
			txtpnSupplier.setBackground(SystemColor.menu);
			txtpnSupplier.setText("Supplier:");
			GridBagConstraints gbc_txtpnSupplier = new GridBagConstraints();
			gbc_txtpnSupplier.insets = new Insets(0, 0, 5, 5);
			gbc_txtpnSupplier.fill = GridBagConstraints.BOTH;
			gbc_txtpnSupplier.gridx = 1;
			gbc_txtpnSupplier.gridy = 9;
			getContentPane().add(txtpnSupplier, gbc_txtpnSupplier);
		}
		{
			JComboBox supplierComboBox = new JComboBox();
			GridBagConstraints gbc_supplierComboBox = new GridBagConstraints();
			gbc_supplierComboBox.insets = new Insets(0, 0, 5, 5);
			gbc_supplierComboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_supplierComboBox.gridx = 2;
			gbc_supplierComboBox.gridy = 9;
			getContentPane().add(supplierComboBox, gbc_supplierComboBox);
		}
		{
			Button button = new Button("Cancel");
			GridBagConstraints gbc_button = new GridBagConstraints();
			gbc_button.insets = new Insets(0, 0, 5, 5);
			gbc_button.gridx = 1;
			gbc_button.gridy = 11;
			getContentPane().add(button, gbc_button);
		}
		{
			Button button = new Button("Save");
			GridBagConstraints gbc_button = new GridBagConstraints();
			gbc_button.insets = new Insets(0, 0, 5, 5);
			gbc_button.gridx = 2;
			gbc_button.gridy = 11;
			getContentPane().add(button, gbc_button);
		}
	}

}
