package UI;

import java.awt.BorderLayout; 
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;

import Controller.CategoryController;
import Controller.ParsingHelper;
import Controller.ProductController;
import Controller.StockProductContoller;
import Controller.SupplierController;
import Controller.UnitController;
import Model.Model.Category;
import Model.Model.LoginContainer;
import Model.Model.MessagesEnum;
import Model.Model.Price;

import Model.Model.PriceType;
import Model.Model.Product;
import Model.Model.StockProduct;
import Model.Model.Supplier;
import Model.Model.Unit;

import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.GridBagConstraints;
import java.awt.Panel;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class ProductPage extends JDialog {
	private JLabel txtpnRegisterProduct;
	private Panel buttonsPanel;
	private JButton backBtn;
	private JButton saveBtn;
	private JTextField barcodeField;
	private JTextField nameField;
	private JTextField purchasePriceField;
	private JTextField salePriceField;
	private JTextField leasePriceField;
	private JTextField minStockField;
	private JTextField maxStockField;
	private JComboBox<String> categoryComboBox;
	private JComboBox<String> unitComboBox;
	private JComboBox<String> supplierComboBox;
	
	private JLabel msgLbl;
	private JLabel errorBarcodeLbl;
	
	private ProductController productCtrl = new ProductController();
	private UnitController unitCtrl = new UnitController();
	private CategoryController categoryCtrl = new CategoryController();
	private SupplierController supplierCtrl = new SupplierController();
	private StockProductContoller stockProductController = new StockProductContoller();
	
	private Product product = null;
	private long stockProductId = -1;
	
	private ArrayList<Unit> units = new ArrayList<Unit>();
	private ArrayList<Category> categories = new ArrayList<Category>();
	private ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
	
	private DefaultComboBoxModel<String> suppliersDefaultModel = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> unitsDefaultModel = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> categoriesDefaultModel = new DefaultComboBoxModel<String>();
	private JLabel lblNewLabel_8;
	private JTextField currentStockField;

	
	public static void start(long stockProductId) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductPage dialog = new ProductPage(stockProductId);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					throw e;
				}
			}
		});
	}

	public ProductPage(long stockProductId) {
		this.stockProductId = stockProductId;
		
		getContentPane().setBackground(SystemColor.menu);
		setBounds(150, 150, 1280, 800);

		getContentPane().setLayout(new BorderLayout(0, 0));
		
		{
			Panel panel = new Panel();
			getContentPane().add(panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{8, 89, 127, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 45, 0, -2, 0, 0, 0, 0, 0, 0, 25, 25, 0, 0, 30, 0, 0};
			gbl_panel.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				txtpnRegisterProduct = new JLabel();
				txtpnRegisterProduct.setFont(new Font("Tahoma", Font.BOLD, 14));
				if(stockProductId == -1) {
					txtpnRegisterProduct.setText("Register product");
				}
				else {
					txtpnRegisterProduct.setText("Product detail");
				}
				txtpnRegisterProduct.setBackground(SystemColor.menu);
				GridBagConstraints gbc_txtpnRegisterProduct = new GridBagConstraints();
				gbc_txtpnRegisterProduct.insets = new Insets(0, 0, 5, 5);
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
				errorBarcodeLbl = new JLabel("");
				errorBarcodeLbl.setForeground(Color.RED);
				GridBagConstraints gbc_errorBarcodeLbl = new GridBagConstraints();
				gbc_errorBarcodeLbl.insets = new Insets(0, 0, 5, 5);
				gbc_errorBarcodeLbl.gridx = 2;
				gbc_errorBarcodeLbl.gridy = 3;
				panel.add(errorBarcodeLbl, gbc_errorBarcodeLbl);
			}
			{
				barcodeField = new JTextField();
				try {
					barcodeField.setText(productCtrl.generateBarcode());
				} catch (SQLException e) {
					e.printStackTrace();
					errorBarcodeLbl.setText("Something went wrong with database. Try it again later.");
				}
				barcodeField.setEditable(false);
				barcodeField.setColumns(10);
				GridBagConstraints gbc_textField = new GridBagConstraints();
				gbc_textField.insets = new Insets(0, 0, 5, 5);
				gbc_textField.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField.gridx = 2;
				gbc_textField.gridy = 2;
				panel.add(barcodeField, gbc_textField);
			}
			{
				JLabel lblNewLabel = new JLabel("Name:");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel.gridx = 1;
				gbc_lblNewLabel.gridy = 4;
				panel.add(lblNewLabel, gbc_lblNewLabel);
			}
			{
				nameField = new JTextField();
				nameField.setColumns(10);
				GridBagConstraints gbc_nameField = new GridBagConstraints();
				gbc_nameField.insets = new Insets(0, 0, 5, 5);
				gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
				gbc_nameField.gridx = 2;
				gbc_nameField.gridy = 4;
				panel.add(nameField, gbc_nameField);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Category");
				GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
				gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_2.gridx = 1;
				gbc_lblNewLabel_2.gridy = 5;
				panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
			}
			{
				categoryComboBox = new JComboBox<String>(categoriesDefaultModel);
				GridBagConstraints gbc_categoryComboBox = new GridBagConstraints();
				gbc_categoryComboBox.insets = new Insets(0, 0, 5, 5);
				gbc_categoryComboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_categoryComboBox.gridx = 2;
				gbc_categoryComboBox.gridy = 5;
				panel.add(categoryComboBox, gbc_categoryComboBox);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("Purchase price:");
				GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
				gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_3.gridx = 1;
				gbc_lblNewLabel_3.gridy = 6;
				panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
			}
			{
				purchasePriceField = new JTextField();
				purchasePriceField.setColumns(10);
				GridBagConstraints gbc_purchasePriceField = new GridBagConstraints();
				gbc_purchasePriceField.insets = new Insets(0, 0, 5, 5);
				gbc_purchasePriceField.fill = GridBagConstraints.HORIZONTAL;
				gbc_purchasePriceField.gridx = 2;
				gbc_purchasePriceField.gridy = 6;
				panel.add(purchasePriceField, gbc_purchasePriceField);
			}
			{
				JLabel lblNewLabel_4 = new JLabel("Sale price:");
				GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
				gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_4.gridx = 1;
				gbc_lblNewLabel_4.gridy = 7;
				panel.add(lblNewLabel_4, gbc_lblNewLabel_4);
			}
			{
				salePriceField = new JTextField();
				salePriceField.setColumns(10);
				GridBagConstraints gbc_salePriceField = new GridBagConstraints();
				gbc_salePriceField.insets = new Insets(0, 0, 5, 5);
				gbc_salePriceField.fill = GridBagConstraints.HORIZONTAL;
				gbc_salePriceField.gridx = 2;
				gbc_salePriceField.gridy = 7;
				panel.add(salePriceField, gbc_salePriceField);
			}
			{
				JLabel lblNewLabel_5 = new JLabel("Lease price:");
				GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
				gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_5.gridx = 1;
				gbc_lblNewLabel_5.gridy = 8;
				panel.add(lblNewLabel_5, gbc_lblNewLabel_5);
			}
			{
				leasePriceField = new JTextField();
				leasePriceField.setColumns(10);
				GridBagConstraints gbc_leasePriceField = new GridBagConstraints();
				gbc_leasePriceField.insets = new Insets(0, 0, 5, 5);
				gbc_leasePriceField.fill = GridBagConstraints.HORIZONTAL;
				gbc_leasePriceField.gridx = 2;
				gbc_leasePriceField.gridy = 8;
				panel.add(leasePriceField, gbc_leasePriceField);
			}
			{
				lblNewLabel_8 = new JLabel("Current stock:");
				GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
				gbc_lblNewLabel_8.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_8.gridx = 1;
				gbc_lblNewLabel_8.gridy = 9;
				panel.add(lblNewLabel_8, gbc_lblNewLabel_8);
			}
			{
				currentStockField = new JTextField();
				GridBagConstraints gbc_currentStockField = new GridBagConstraints();
				gbc_currentStockField.insets = new Insets(0, 0, 5, 5);
				gbc_currentStockField.fill = GridBagConstraints.HORIZONTAL;
				gbc_currentStockField.gridx = 2;
				gbc_currentStockField.gridy = 9;
				panel.add(currentStockField, gbc_currentStockField);
				currentStockField.setColumns(10);
				currentStockField.setEditable(false);
			}
			{
				JLabel minStockLbl = new JLabel("Minimum Stock:");
				GridBagConstraints gbc_minStockLbl = new GridBagConstraints();
				gbc_minStockLbl.anchor = GridBagConstraints.WEST;
				gbc_minStockLbl.insets = new Insets(0, 0, 5, 5);
				gbc_minStockLbl.gridx = 1;
				gbc_minStockLbl.gridy = 10;
				panel.add(minStockLbl, gbc_minStockLbl);
			}
			{
				minStockField = new JTextField();
				GridBagConstraints gbc_minStockField = new GridBagConstraints();
				gbc_minStockField.insets = new Insets(0, 0, 5, 5);
				gbc_minStockField.fill = GridBagConstraints.HORIZONTAL;
				gbc_minStockField.gridx = 2;
				gbc_minStockField.gridy = 10;
				panel.add(minStockField, gbc_minStockField);
				minStockField.setColumns(10);
			}
			{
				JLabel maxStockLbl = new JLabel("Maximum Stock:");
				GridBagConstraints gbc_maxStockLbl = new GridBagConstraints();
				gbc_maxStockLbl.anchor = GridBagConstraints.WEST;
				gbc_maxStockLbl.insets = new Insets(0, 0, 5, 5);
				gbc_maxStockLbl.gridx = 1;
				gbc_maxStockLbl.gridy = 11;
				panel.add(maxStockLbl, gbc_maxStockLbl);
			}
			{
				maxStockField = new JTextField();
				GridBagConstraints gbc_maxStockField = new GridBagConstraints();
				gbc_maxStockField.insets = new Insets(0, 0, 5, 5);
				gbc_maxStockField.fill = GridBagConstraints.HORIZONTAL;
				gbc_maxStockField.gridx = 2;
				gbc_maxStockField.gridy = 11;
				panel.add(maxStockField, gbc_maxStockField);
				maxStockField.setColumns(10);
			}
			{
				JLabel lblNewLabel_6 = new JLabel("Unit:");
				GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
				gbc_lblNewLabel_6.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_6.gridx = 1;
				gbc_lblNewLabel_6.gridy = 12;
				panel.add(lblNewLabel_6, gbc_lblNewLabel_6);
			}
			{
				unitComboBox = new JComboBox<String>(unitsDefaultModel);
				GridBagConstraints gbc_unitComboBox = new GridBagConstraints();
				gbc_unitComboBox.insets = new Insets(0, 0, 5, 5);
				gbc_unitComboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_unitComboBox.gridx = 2;
				gbc_unitComboBox.gridy = 12;
				panel.add(unitComboBox, gbc_unitComboBox);
			}
			{
				JLabel lblNewLabel_7 = new JLabel("Supplier:");
				GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
				gbc_lblNewLabel_7.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_7.gridx = 1;
				gbc_lblNewLabel_7.gridy = 13;
				panel.add(lblNewLabel_7, gbc_lblNewLabel_7);
			}
			{			
				supplierComboBox = new JComboBox<String>(suppliersDefaultModel);
				GridBagConstraints gbc_supplierComboBox = new GridBagConstraints();
				gbc_supplierComboBox.insets = new Insets(0, 0, 5, 5);
				gbc_supplierComboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_supplierComboBox.gridx = 2;
				gbc_supplierComboBox.gridy = 13;
				panel.add(supplierComboBox, gbc_supplierComboBox);
			}
			{
				msgLbl = new JLabel("");
				GridBagConstraints gbc_errorMsgLbl = new GridBagConstraints();
				gbc_errorMsgLbl.insets = new Insets(0, 0, 5, 5);
				gbc_errorMsgLbl.gridx = 2;
				gbc_errorMsgLbl.gridy = 14;
				panel.add(msgLbl, gbc_errorMsgLbl);
			}
		}
		{
			buttonsPanel = new Panel();
			getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
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
						if (stockProductId == -1) {
							HomePage.start();							
						} else {
							ProductListPage.start();
						}
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
						try {
							
							String stringCategory = String.valueOf(categoryComboBox.getSelectedItem());
							Category category = categories.stream()
														  .filter(cat -> stringCategory.equals(cat.getName()))
														  .findAny()
														  .orElse(null);

							String stringUnit = String.valueOf(unitComboBox.getSelectedItem());
							Unit unit = units.stream()
											 .filter(u -> stringUnit.equals(u.getName()))
											 .findAny()
											 .orElse(null);
							
							String stringSupplierCvrNo = String.valueOf(supplierComboBox.getSelectedItem()).substring(0,8);
							Supplier supplier = suppliers.stream()
														 .filter(s -> stringSupplierCvrNo.equals(s.getCvrNo()))
														 .findAny()
														 .orElse(null);
							Price purchasePrice = null;
							Price salePrice = null;
							Price leasePrice = null;
							
							if(!purchasePriceField.getText().isEmpty()) {
								purchasePrice = new Price(-1, ParsingHelper.tryParseDouble(purchasePriceField.getText()), LocalDate.now(), PriceType.PURCHASE);
							}
							if(!salePriceField.getText().isEmpty()) {
								salePrice = new Price(-1, ParsingHelper.tryParseDouble(salePriceField.getText()), LocalDate.now(), PriceType.SALE);
							}
							if(!leasePriceField.getText().isEmpty()) {
								leasePrice = new Price(-1, ParsingHelper.tryParseDouble(leasePriceField.getText()), LocalDate.now(), PriceType.LEASE);
							}
							
							product = new Product(-1, 
												  barcodeField.getText(), 
												  nameField.getText(),
												  category,
												  purchasePrice,
												  salePrice,
												  leasePrice,
												  unit,
												  supplier);
							
							product = productCtrl.createProduct(product, ParsingHelper.tryParseInt(minStockField.getText()), ParsingHelper.tryParseInt(maxStockField.getText()));
							
							StockProduct stockProduct = productCtrl.getStockProductByProductId(product.getId(), LoginContainer.getInstance().getCurrentUser().getWarehouse().getId());
							
							setStockProductId(stockProduct.getId());
							
							txtpnRegisterProduct.setText("Edit product");
							getContentPane().revalidate();
							getContentPane().repaint();
							
							buttonsPanel.remove(saveBtn);
							buttonsPanel.revalidate();
							buttonsPanel.repaint();
							
							msgLbl.setForeground(Color.GREEN);
							msgLbl.setText(MessagesEnum.PRODUCTSAVED.text);	
						} catch (NumberFormatException e1) {
							e1.printStackTrace();
							msgLbl.setForeground(Color.RED);
							msgLbl.setText(MessagesEnum.PARSEERROR.text);
							return;
							
						} catch (SQLException e2) {
							e2.printStackTrace();
							msgLbl.setForeground(Color.RED);
							msgLbl.setText(MessagesEnum.DBERROR.text);
							return;
						}
					}
				});
				GridBagConstraints gbc_saveBtn = new GridBagConstraints();
				gbc_saveBtn.insets = new Insets(0, 0, 0, 5);
				gbc_saveBtn.anchor = GridBagConstraints.NORTH;
				gbc_saveBtn.gridx = 3;
				gbc_saveBtn.gridy = 0;
				if(stockProductId == -1) {
					buttonsPanel.add(saveBtn, gbc_saveBtn);
				}
			}
		}
		try {
			loadComboBoxData();
			
			suppliers.stream().forEach(supplier -> suppliersDefaultModel.addElement(String.valueOf(supplier.getCvrNo()) + " - " + supplier.getSupplierName()));
			units.stream().forEach(unit -> unitsDefaultModel.addElement(unit.getName()));
			categories.stream().forEach(category -> categoriesDefaultModel.addElement(category.getName()));
			
			if (stockProductId != -1) {				
				loadData();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			msgLbl.setText("Something went wrong with database. Try it again later.");
		}
	}

	public void loadComboBoxData() throws SQLException {
		try {
			units = unitCtrl.getUnits();
			categories = categoryCtrl.getCategories();
			suppliers = supplierCtrl.getSuppliers();
		} catch (SQLException e) {
			throw e;
		}
	}

	public void loadData() throws SQLException {
		try {
			txtpnRegisterProduct.setText("Edit product");
			
			StockProduct stockProduct = stockProductController.getStockProduct(stockProductId, LoginContainer.getInstance().getCurrentUser().getWarehouse().getId());
			
			currentStockField.setText(String.valueOf(stockProduct.getAmount()));
			barcodeField.setText(stockProduct.getProduct().getBarcode());
			nameField.setText(stockProduct.getProduct().getName());
			if(stockProduct.getProduct().getPurchasePrice() != null) {
				purchasePriceField.setText(Double.toString(stockProduct.getProduct().getPurchasePrice().getAmount()));
			}
			if(stockProduct.getProduct().getSalePrice() != null) {
				salePriceField.setText(Double.toString(stockProduct.getProduct().getSalePrice().getAmount()));
			}
			if(stockProduct.getProduct().getLeasePrice() != null) {
				leasePriceField.setText(Double.toString(stockProduct.getProduct().getLeasePrice().getAmount()));
			}
			currentStockField.setText(Integer.toString(stockProduct.getAmount()));
			minStockField.setText(Integer.toString(stockProduct.getMinStock()));
			maxStockField.setText(Integer.toString(stockProduct.getMaxStock()));
			String supplier = (String.valueOf(stockProduct.getProduct().getSupplier().getCvrNo()) + " - " + stockProduct.getProduct().getSupplier().getSupplierName());
			supplierComboBox.getModel().setSelectedItem(supplier);
			categoryComboBox.getModel().setSelectedItem(stockProduct.getProduct().getCategory().getName());
			unitComboBox.getModel().setSelectedItem(stockProduct.getProduct().getUnit().getName());
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public void setStockProductId(long stockProductId) {
		this.stockProductId = stockProductId;
	}
}
