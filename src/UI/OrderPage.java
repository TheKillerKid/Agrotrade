package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
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
import javax.swing.table.DefaultTableModel;

import Model.Model.StockProduct;
import Model.Model.Supplier;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JList;

import Controller.OrderController;
import Controller.ParsingHelper;
import Model.Model.LoginContainer;
import Model.Model.Order;
import Model.Model.OrderLine;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OrderPage extends JDialog {
	private OrderController orderCtrl = new OrderController();
	
	private final JPanel contentPanel = new JPanel();
	private ScrollPane scrollPane = new ScrollPane();
	private JTextField customerCvrNoField;
	private JTextField quantityField;
	private JTextField noteField;
	JComboBox<String> stockProductsComboBox;
	private JLabel onStockValue;
	
	private ArrayList<StockProduct> stockProducts = new ArrayList<StockProduct>();
	
	private DefaultComboBoxModel<String> stockProductsDefaultModel = new DefaultComboBoxModel<String>();
	
	private StockProduct selectedStockProduct = null;
	private ArrayList<OrderLine> orderLines = new ArrayList<OrderLine>();
	private Order order = null;
	
	private Object[][] ols = new Object[0][];
	
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
	 * Create the dialog.
	 */
	public OrderPage() {
		setBounds(150, 150, 1280, 800);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 100, 259, 84, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 45, 0, 28, 0, 0, 24, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel title = new JLabel("Create Order");
			title.setFont(new Font("Tahoma", Font.BOLD, 14));
			GridBagConstraints gbc_title = new GridBagConstraints();
			gbc_title.insets = new Insets(0, 0, 5, 5);
			gbc_title.gridx = 2;
			gbc_title.gridy = 1;
			contentPanel.add(title, gbc_title);
		}
		{
			JLabel customerCvrNoLbl = new JLabel("Customer CVR");
			GridBagConstraints gbc_customerCvrNoLbl = new GridBagConstraints();
			gbc_customerCvrNoLbl.anchor = GridBagConstraints.WEST;
			gbc_customerCvrNoLbl.insets = new Insets(0, 0, 5, 5);
			gbc_customerCvrNoLbl.gridx = 1;
			gbc_customerCvrNoLbl.gridy = 2;
			contentPanel.add(customerCvrNoLbl, gbc_customerCvrNoLbl);
		}
		{
			customerCvrNoField = new JTextField();
			GridBagConstraints gbc_customerCvrNoField = new GridBagConstraints();
			gbc_customerCvrNoField.insets = new Insets(0, 0, 5, 5);
			gbc_customerCvrNoField.fill = GridBagConstraints.HORIZONTAL;
			gbc_customerCvrNoField.gridx = 2;
			gbc_customerCvrNoField.gridy = 2;
			contentPanel.add(customerCvrNoField, gbc_customerCvrNoField);
			customerCvrNoField.setColumns(10);
		}
		{
			JButton checkNameBtn = new JButton("Check");
			checkNameBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					
				}
				
			});
			GridBagConstraints gbc_checkNameBtn = new GridBagConstraints();
			gbc_checkNameBtn.insets = new Insets(0, 0, 5, 5);
			gbc_checkNameBtn.gridx = 3;
			gbc_checkNameBtn.gridy = 2;
			contentPanel.add(checkNameBtn, gbc_checkNameBtn);
		}
		{
			JLabel customerNameLbl = new JLabel("Customer Name");
			GridBagConstraints gbc_customerNameLbl = new GridBagConstraints();
			gbc_customerNameLbl.anchor = GridBagConstraints.WEST;
			gbc_customerNameLbl.insets = new Insets(0, 0, 5, 5);
			gbc_customerNameLbl.gridx = 1;
			gbc_customerNameLbl.gridy = 3;
			contentPanel.add(customerNameLbl, gbc_customerNameLbl);
		}
		{
			String customerName = null;
			JLabel customerNameValue = new JLabel(customerName);
			
			GridBagConstraints gbc_customerNameValue = new GridBagConstraints();
			gbc_customerNameValue.anchor = GridBagConstraints.WEST;
			gbc_customerNameValue.insets = new Insets(0, 0, 5, 5);
			gbc_customerNameValue.gridx = 2;
			gbc_customerNameValue.gridy = 3;
			contentPanel.add(customerNameValue, gbc_customerNameValue);
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
				JLabel selectedProductLbl = new JLabel("Select Product");
				GridBagConstraints gbc_selectedProductLbl = new GridBagConstraints();
				gbc_selectedProductLbl.anchor = GridBagConstraints.WEST;
				gbc_selectedProductLbl.insets = new Insets(0, 0, 5, 5);
				gbc_selectedProductLbl.gridx = 0;
				gbc_selectedProductLbl.gridy = 0;
				panel.add(selectedProductLbl, gbc_selectedProductLbl);
			}
			{

				stockProductsComboBox = new JComboBox<String>(stockProductsDefaultModel);
				stockProductsComboBox.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						getCurrentStock();
					}
				});
				GridBagConstraints gbc_comboBox = new GridBagConstraints();
				gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBox.insets = new Insets(0, 0, 5, 5);
				gbc_comboBox.gridx = 1;
				gbc_comboBox.gridy = 0;
				panel.add(stockProductsComboBox, gbc_comboBox);
			}
			{
				JLabel onStockLbl = new JLabel("On Stock");
				GridBagConstraints gbc_onStockLbl = new GridBagConstraints();
				gbc_onStockLbl.anchor = GridBagConstraints.WEST;
				gbc_onStockLbl.insets = new Insets(0, 0, 5, 5);
				gbc_onStockLbl.gridx = 0;
				gbc_onStockLbl.gridy = 1;
				panel.add(onStockLbl, gbc_onStockLbl);
			}
			{
				onStockValue = new JLabel("");
				GridBagConstraints gbc_onStockValue = new GridBagConstraints();
				gbc_onStockValue.anchor = GridBagConstraints.WEST;
				gbc_onStockValue.insets = new Insets(0, 0, 5, 5);
				gbc_onStockValue.gridx = 1;
				gbc_onStockValue.gridy = 1;
				panel.add(onStockValue, gbc_onStockValue);
			}
			{
				JLabel quantityLbl = new JLabel("Quantity");
				GridBagConstraints gbc_quantityLbl = new GridBagConstraints();
				gbc_quantityLbl.anchor = GridBagConstraints.WEST;
				gbc_quantityLbl.insets = new Insets(0, 0, 0, 5);
				gbc_quantityLbl.gridx = 0;
				gbc_quantityLbl.gridy = 2;
				panel.add(quantityLbl, gbc_quantityLbl);
			}
			{
				quantityField = new JTextField();
				GridBagConstraints gbc_quantityField = new GridBagConstraints();
				gbc_quantityField.fill = GridBagConstraints.HORIZONTAL;
				gbc_quantityField.insets = new Insets(0, 0, 0, 5);
				gbc_quantityField.gridx = 1;
				gbc_quantityField.gridy = 2;
				panel.add(quantityField, gbc_quantityField);
				quantityField.setColumns(10);
			}
			{
				JButton btnAdd = new JButton("Add");
				btnAdd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int requestedAmount = ParsingHelper.tryParseInt(quantityField.getText());
						int amount = requestedAmount > selectedStockProduct.getAmount() ? selectedStockProduct.getAmount() : requestedAmount;
						orderLines.add(new OrderLine(-1, requestedAmount, amount, selectedStockProduct));
						ols = new Object[orderLines.size()][];
						
						for(int i = 0; i < orderLines.size(); i++) {
							OrderLine ol = orderLines.get(i);
							Object [] newData = {
								ol.getStockProduct().getProduct().getBarcode(),
								ol.getStockProduct().getProduct().getName(),
								Integer.toString(ol.getAmount()),
								Integer.toString(ol.getStockProduct().getAmount()),
							};

							ols[i] = newData;
						}
						
						quantityField.setText("");
						
						scrollPane.revalidate();
						scrollPane.repaint();
					}
				});
				GridBagConstraints gbc_btnAdd = new GridBagConstraints();
				gbc_btnAdd.gridx = 2;
				gbc_btnAdd.gridy = 2;
				panel.add(btnAdd, gbc_btnAdd);
			}
		}
		{
			Object[][] data = new Object[0][];
			String columns[]={"Barcode", "Name", "On stock", "Quantity"};
			DefaultTableModel model = new DefaultTableModel(data, columns);
			JTable table = new JTable( model );
			
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
			gbc_scrollPane.gridx = 2;
			gbc_scrollPane.gridy = 5;
			contentPanel.add(scrollPane, gbc_scrollPane);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Note");
			GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
			gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_3.gridx = 1;
			gbc_lblNewLabel_3.gridy = 6;
			contentPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		}
		{
			noteField = new JTextField();
			noteField.setColumns(10);
			GridBagConstraints gbc_noteField = new GridBagConstraints();
			gbc_noteField.insets = new Insets(0, 0, 5, 5);
			gbc_noteField.fill = GridBagConstraints.HORIZONTAL;
			gbc_noteField.gridx = 2;
			gbc_noteField.gridy = 6;
			contentPanel.add(noteField, gbc_noteField);
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
				JButton saveBtn = new JButton("Save");
				GridBagConstraints gbc_saveBtn = new GridBagConstraints();
				gbc_saveBtn.anchor = GridBagConstraints.WEST;
				gbc_saveBtn.insets = new Insets(0, 0, 0, 5);
				gbc_saveBtn.gridx = 3;
				gbc_saveBtn.gridy = 0;
				buttonPane.add(saveBtn, gbc_saveBtn);
			}
		}
		
		getCurrentStock();
		
		try {
			loadData();
			
			stockProducts.stream().forEach(stockProduct -> stockProductsDefaultModel.addElement(String.valueOf(stockProduct.getProduct().getBarcode()) + " - " + stockProduct.getProduct().getName()));
		} catch (SQLException e) {
			e.printStackTrace();
			//errorMsgLbl.setText("Something went wrong with database. Try it again later.");
		}
	}
	
	private void loadData () throws SQLException {	
		try {
			stockProducts = orderCtrl.getStockProducts(LoginContainer.getInstance().getCurrentUser().getWarehouse().getId());
		} catch (SQLException e) {
			throw e;
		}
	}
	
	private void getCurrentStock() {
		if(stockProductsComboBox.getSelectedItem() != null) {
			String stringCurrentStock = String.valueOf(stockProductsComboBox.getSelectedItem()).substring(0,12);
			selectedStockProduct = stockProducts.stream()
										 .filter(sp -> stringCurrentStock.equals(sp.getProduct().getBarcode()))
										 .findAny()
										 .orElse(null);
			onStockValue.setText(String.valueOf(selectedStockProduct.getAmount()));
		}
	}
}