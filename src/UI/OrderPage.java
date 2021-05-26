package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import Model.Model.StockProduct;
import Model.Model.Supplier;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JList;

import Controller.OrderController;
import Controller.ParsingHelper;
import Model.Model.Customer;
import Model.Model.Lease;
import Model.Model.LoginContainer;
import Model.Model.MessagesEnum;
import Model.Model.Order;
import Model.Model.OrderLine;
import Model.Model.OrderPageType;
import Model.Model.PositionType;
import Model.Model.Purchase;
import Model.Model.Sale;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;

public class OrderPage extends JDialog {
	private OrderController orderCtrl = new OrderController();
	
	private OrderPageType type;
	private PositionType position = LoginContainer.getInstance().getCurrentUser().getPosition();
	
	private final JPanel contentPanel = new JPanel();
	private JPanel buttonPane = new JPanel();
	
	private JLabel title = new JLabel("");
	private JTextField quantityField = new JTextField();;
	private JTextField noteField;
	private JComboBox<String> customersComboBox;
	private JComboBox<String> stockProductsComboBox;
	
	private UtilDateModel shippingDateModel;
	private UtilDateModel deliveryDateModel;
	private JDatePanelImpl shippingDatePanel;
	private JDatePanelImpl deliveryDatePanel;
	private JDatePickerImpl shippingDatePicker;
	private JDatePickerImpl deliveryDatePicker;
	
	private UtilDateModel borrowDateModel;
	private UtilDateModel expectedReturnDateModel;
	private UtilDateModel realReturnDateModel;
	private JDatePanelImpl borrowDatePanel;
	private JDatePanelImpl expectedReturnDatePanel;
	private JDatePanelImpl realReturnDatePanel;
	private JDatePickerImpl borrowDatePicker;
	private JDatePickerImpl expectedReturnDatePicker;
	private JDatePickerImpl realReturnDatePicker;
	
	private JLabel onStockValue;
	private JLabel totalPriceValue;
	
	private JLabel msgLbl = new JLabel("");
	private JLabel msgOrderLineLbl = new JLabel("");
	
	private JButton btnAdd = new JButton("Add");
	private GridBagConstraints gbc_btnAdd = new GridBagConstraints();
	
	private JButton saveBtn = new JButton("Save");
	private GridBagConstraints gbc_saveBtn = new GridBagConstraints();
	
	private JPanel middleBtnPanel;
	private JButton setAsReceivedBtn;
	private JButton returnLeaseBtn;
	private JButton sendSaleBtn;
	private JButton saleDeliveredBtn;
	
	private ArrayList<StockProduct> stockProducts = new ArrayList<StockProduct>();
	private ArrayList<Customer> customers = new ArrayList<Customer>();
	
	private DefaultComboBoxModel<String> stockProductsDefaultModel = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> customersDefaultModel = new DefaultComboBoxModel<String>();
	
	private StockProduct selectedStockProduct = null;
	private int availableAmountOfSelectedProduct;
	private int currtentMaxAmountOfSelectedProduct;
	private ArrayList<OrderLine> orderLines = new ArrayList<OrderLine>();
	private Order order = null;
	
	private String columns[]={"Barcode", "Name", "On stock", "Requested quantity", "Quantity"};
	private Object[][] ols = new Object[0][];				
	private DefaultTableModel olsTabelModel;
	private JTable olsTable;
	private JScrollPane scrollPane;
	private GridBagConstraints gbc_scrollPane = new GridBagConstraints();
	
	private Long id;
	
	public static void start(OrderPageType type, Long id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderPage dialog = new OrderPage(type, id);
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
	public OrderPage(OrderPageType type, Long id) {
		this.type = type;
		this.id = id;
		setBounds(150, 150, 1280, 800);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 100, 259, 84, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 45, 0, 0, 200, 30, 24, 30, 30, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE, 1.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			setTitle();
			
			title.setFont(new Font("Tahoma", Font.BOLD, 14));
			GridBagConstraints gbc_title = new GridBagConstraints();
			gbc_title.insets = new Insets(0, 0, 5, 5);
			gbc_title.gridx = 2;
			gbc_title.gridy = 1;
			contentPanel.add(title, gbc_title);
		}
		if(type != OrderPageType.PURCHASE) {
			{
				JLabel customerCvrNoLbl = new JLabel("Customer");
				GridBagConstraints gbc_customerCvrNoLbl = new GridBagConstraints();
				gbc_customerCvrNoLbl.anchor = GridBagConstraints.WEST;
				gbc_customerCvrNoLbl.insets = new Insets(0, 0, 5, 5);
				gbc_customerCvrNoLbl.gridx = 1;
				gbc_customerCvrNoLbl.gridy = 2;
				contentPanel.add(customerCvrNoLbl, gbc_customerCvrNoLbl);
			}
			{
	
				customersComboBox = new JComboBox<String>(customersDefaultModel);
				customersComboBox.setEnabled(id == null);
				GridBagConstraints gbc_customerComboBox = new GridBagConstraints();
				gbc_customerComboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_customerComboBox.insets = new Insets(0, 0, 5, 5);
				gbc_customerComboBox.gridx = 2;
				gbc_customerComboBox.gridy = 2;
				contentPanel.add(customersComboBox, gbc_customerComboBox);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Add Products", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridwidth = 3;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 3;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{100, 259, 84, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
				Object[][] data = new Object[0][];
				
				olsTabelModel = new DefaultTableModel(data, columns);
				olsTable = new JTable(olsTabelModel);
				scrollPane = new JScrollPane(olsTable);
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				
				gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
				gbc_scrollPane.gridx = 2;
				gbc_scrollPane.gridy = 4;
				contentPanel.add(scrollPane, gbc_scrollPane);
			}
			{
				btnAdd.setEnabled(false);
				btnAdd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addProductToOrderLine();
					}
				});
				gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
				gbc_btnAdd.gridx = 2;
				gbc_btnAdd.gridy = 1;
				panel.add(btnAdd, gbc_btnAdd);
			}
			{
				JLabel quantityLbl = new JLabel("Quantity");
				GridBagConstraints gbc_quantityLbl = new GridBagConstraints();
				gbc_quantityLbl.anchor = GridBagConstraints.WEST;
				gbc_quantityLbl.insets = new Insets(0, 0, 5, 5);
				gbc_quantityLbl.gridx = 0;
				gbc_quantityLbl.gridy = 2;
				panel.add(quantityLbl, gbc_quantityLbl);
			}
			{
				quantityField.setEnabled(id == null);
				quantityField.getDocument().addDocumentListener(new DocumentListener() {
					public void changedUpdate(DocumentEvent e) {
						repaint();
					}
					public void removeUpdate(DocumentEvent e) {
						repaint();
					}
					public void insertUpdate(DocumentEvent e) {
						repaint();
					}
	
				    public void repaint() {
				    	panel.remove(btnAdd);
						boolean enabled = quantityField.getText().length() != 0;
						btnAdd.setEnabled(enabled);
						panel.add(btnAdd, gbc_btnAdd);
						
						panel.revalidate();
						panel.repaint();
					  }
					});
				GridBagConstraints gbc_quantityField = new GridBagConstraints();
				gbc_quantityField.fill = GridBagConstraints.HORIZONTAL;
				gbc_quantityField.insets = new Insets(0, 0, 5, 5);
				gbc_quantityField.gridx = 1;
				gbc_quantityField.gridy = 2;
				panel.add(quantityField, gbc_quantityField);
				quantityField.setColumns(10);
			}
			{
				GridBagConstraints gbc_msgOrderLineLbl = new GridBagConstraints();
				gbc_msgOrderLineLbl.anchor = GridBagConstraints.WEST;
				gbc_msgOrderLineLbl.insets = new Insets(0, 0, 0, 5);
				gbc_msgOrderLineLbl.gridx = 1;
				gbc_msgOrderLineLbl.gridy = 3;
				panel.add(msgOrderLineLbl, gbc_msgOrderLineLbl);
			}
		}
		{
			JLabel totalPriceLbl = new JLabel("Total Price ");
			GridBagConstraints gbc_totalPriceLbl = new GridBagConstraints();
			gbc_totalPriceLbl.anchor = GridBagConstraints.WEST;
			gbc_totalPriceLbl.insets = new Insets(0, 0, 5, 5);
			gbc_totalPriceLbl.gridx = 1;
			gbc_totalPriceLbl.gridy = 5;
			contentPanel.add(totalPriceLbl, gbc_totalPriceLbl);
		}
		{
			totalPriceValue = new JLabel("");
			GridBagConstraints gbc_totalPriceValue = new GridBagConstraints();
			gbc_totalPriceValue.anchor = GridBagConstraints.WEST;
			gbc_totalPriceValue.insets = new Insets(0, 0, 5, 5);
			gbc_totalPriceValue.gridx = 2;
			gbc_totalPriceValue.gridy = 5;
			contentPanel.add(totalPriceValue, gbc_totalPriceValue);
		}
		{
			JLabel noteLbl = new JLabel("Note");
			GridBagConstraints gbc_noteLbl = new GridBagConstraints();
			gbc_noteLbl.anchor = GridBagConstraints.WEST;
			gbc_noteLbl.insets = new Insets(0, 0, 5, 5);
			gbc_noteLbl.gridx = 1;
			gbc_noteLbl.gridy = 6;
			contentPanel.add(noteLbl, gbc_noteLbl);
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
		if(type == OrderPageType.SALE) {
			{
				JLabel shippingDateLbl = new JLabel("Shipping date");
				GridBagConstraints gbc_shippingDateLbl = new GridBagConstraints();
				gbc_shippingDateLbl.anchor = GridBagConstraints.WEST;
				gbc_shippingDateLbl.insets = new Insets(0, 0, 5, 5);
				gbc_shippingDateLbl.gridx = 1;
				gbc_shippingDateLbl.gridy = 7;
				contentPanel.add(shippingDateLbl, gbc_shippingDateLbl);
			}
			{
				shippingDateModel = new UtilDateModel();
				Properties p = new Properties();
				p.put("text.today", "Today");
				p.put("text.month", "Month");
				p.put("text.year", "Year");
				shippingDatePanel = new JDatePanelImpl(shippingDateModel, p);
				shippingDatePicker = new JDatePickerImpl(shippingDatePanel, new CalendarFormater());
				shippingDatePicker.getComponent(1).setEnabled(false);
				
				GridBagConstraints gbc_shippingDatePicker = new GridBagConstraints();
				gbc_shippingDatePicker.insets = new Insets(0, 0, 5, 5);
				gbc_shippingDatePicker.fill = GridBagConstraints.HORIZONTAL;
				gbc_shippingDatePicker.gridx = 2;
				gbc_shippingDatePicker.gridy = 7;
				contentPanel.add(shippingDatePicker, gbc_shippingDatePicker);
			}
			{
				JLabel deliveryDateLbl = new JLabel("Delivery date");
				GridBagConstraints gbc_deliveryDateLbl = new GridBagConstraints();
				gbc_deliveryDateLbl.anchor = GridBagConstraints.WEST;
				gbc_deliveryDateLbl.insets = new Insets(0, 0, 5, 5);
				gbc_deliveryDateLbl.gridx = 1;
				gbc_deliveryDateLbl.gridy = 8;
				contentPanel.add(deliveryDateLbl, gbc_deliveryDateLbl);
			}
			{
				deliveryDateModel = new UtilDateModel();
				Properties p = new Properties();
				p.put("text.today", "Today");
				p.put("text.month", "Month");
				p.put("text.year", "Year");
				deliveryDatePanel = new JDatePanelImpl(deliveryDateModel, p);
				deliveryDatePicker = new JDatePickerImpl(deliveryDatePanel, new CalendarFormater());
				deliveryDatePicker.getComponent(1).setEnabled(false);
				
				GridBagConstraints gbc_deliveryDatePicker = new GridBagConstraints();
				gbc_deliveryDatePicker.insets = new Insets(0, 0, 5, 5);
				gbc_deliveryDatePicker.fill = GridBagConstraints.HORIZONTAL;
				gbc_deliveryDatePicker.gridx = 2;
				gbc_deliveryDatePicker.gridy = 8;
				contentPanel.add(deliveryDatePicker, gbc_deliveryDatePicker);
			}
		}
		if(type == OrderPageType.LEASE){
			{
				JLabel borrowDateLbl = new JLabel("Borrow date");
				GridBagConstraints gbc_borrowDateLbl = new GridBagConstraints();
				gbc_borrowDateLbl.anchor = GridBagConstraints.WEST;
				gbc_borrowDateLbl.insets = new Insets(0, 0, 5, 5);
				gbc_borrowDateLbl.gridx = 1;
				gbc_borrowDateLbl.gridy = 7;
				contentPanel.add(borrowDateLbl, gbc_borrowDateLbl);
			}

			{
				borrowDateModel = new UtilDateModel();
				Properties p = new Properties();
				p.put("text.today", "Today");
				p.put("text.month", "Month");
				p.put("text.year", "Year");
				borrowDatePanel = new JDatePanelImpl(borrowDateModel, p);
				borrowDatePicker = new JDatePickerImpl(borrowDatePanel, new CalendarFormater());
				borrowDatePicker.getComponent(1).setEnabled(id == null);
				
				GridBagConstraints gbc_borrowDatePicker = new GridBagConstraints();
				gbc_borrowDatePicker.insets = new Insets(0, 0, 5, 5);
				gbc_borrowDatePicker.fill = GridBagConstraints.HORIZONTAL;
				gbc_borrowDatePicker.gridx = 2;
				gbc_borrowDatePicker.gridy = 7;
				contentPanel.add(borrowDatePicker, gbc_borrowDatePicker);
			}
			{
				JLabel expectedReturnDateLbl = new JLabel("Expected return date");
				GridBagConstraints gbc_expectedReturnDateLbl = new GridBagConstraints();
				gbc_expectedReturnDateLbl.anchor = GridBagConstraints.WEST;
				gbc_expectedReturnDateLbl.insets = new Insets(0, 0, 5, 5);
				gbc_expectedReturnDateLbl.gridx = 1;
				gbc_expectedReturnDateLbl.gridy = 8;
				contentPanel.add(expectedReturnDateLbl, gbc_expectedReturnDateLbl);
			}
			{
				expectedReturnDateModel = new UtilDateModel();
				Properties p = new Properties();
				p.put("text.today", "Today");
				p.put("text.month", "Month");
				p.put("text.year", "Year");
				expectedReturnDatePanel = new JDatePanelImpl(expectedReturnDateModel, p);
				expectedReturnDatePicker = new JDatePickerImpl(expectedReturnDatePanel, new CalendarFormater());
				expectedReturnDatePicker.getComponent(1).setEnabled(id == null);
				
				GridBagConstraints gbc_expectedReturnDatePicker = new GridBagConstraints();
				gbc_expectedReturnDatePicker.insets = new Insets(0, 0, 5, 5);
				gbc_expectedReturnDatePicker.fill = GridBagConstraints.HORIZONTAL;
				gbc_expectedReturnDatePicker.gridx = 2;
				gbc_expectedReturnDatePicker.gridy = 8;
				contentPanel.add(expectedReturnDatePicker, gbc_expectedReturnDatePicker);
			}
			{
				JLabel realReturnDateLbl = new JLabel("Real return date");
				GridBagConstraints gbc_realReturnDateLbl = new GridBagConstraints();
				gbc_realReturnDateLbl.anchor = GridBagConstraints.WEST;
				gbc_realReturnDateLbl.insets = new Insets(0, 0, 5, 5);
				gbc_realReturnDateLbl.gridx = 1;
				gbc_realReturnDateLbl.gridy = 9;
				contentPanel.add(realReturnDateLbl, gbc_realReturnDateLbl);
			}
			{
				realReturnDateModel = new UtilDateModel();
				Properties p = new Properties();
				p.put("text.today", "Today");
				p.put("text.month", "Month");
				p.put("text.year", "Year");
				realReturnDatePanel = new JDatePanelImpl(realReturnDateModel, p);
				realReturnDatePicker = new JDatePickerImpl(realReturnDatePanel, new CalendarFormater());
				realReturnDatePicker.getComponent(1).setEnabled(false);
				
				GridBagConstraints gbc_realReturnDatePicker = new GridBagConstraints();
				gbc_realReturnDatePicker.insets = new Insets(0, 0, 5, 5);
				gbc_realReturnDatePicker.fill = GridBagConstraints.HORIZONTAL;
				gbc_realReturnDatePicker.gridx = 2;
				gbc_realReturnDatePicker.gridy = 9;
				contentPanel.add(realReturnDatePicker, gbc_realReturnDatePicker);
			}
		}
		if(type == OrderPageType.PURCHASE){
			{
				JLabel deliveryDateLbl = new JLabel("Delivery date");
				GridBagConstraints gbc_deliveryDateLbl = new GridBagConstraints();
				gbc_deliveryDateLbl.anchor = GridBagConstraints.WEST;
				gbc_deliveryDateLbl.insets = new Insets(0, 0, 5, 5);
				gbc_deliveryDateLbl.gridx = 1;
				gbc_deliveryDateLbl.gridy = 7;
				contentPanel.add(deliveryDateLbl, gbc_deliveryDateLbl);
			}
			{
				deliveryDateModel = new UtilDateModel();
				Properties p = new Properties();
				p.put("text.today", "Today");
				p.put("text.month", "Month");
				p.put("text.year", "Year");
				deliveryDatePanel = new JDatePanelImpl(deliveryDateModel, p);
				deliveryDatePicker = new JDatePickerImpl(deliveryDatePanel, new CalendarFormater());
				deliveryDatePicker.getComponent(1).setEnabled(false);
				
				GridBagConstraints gbc_deliveryDatePicker = new GridBagConstraints();
				gbc_deliveryDatePicker.insets = new Insets(0, 0, 5, 5);
				gbc_deliveryDatePicker.fill = GridBagConstraints.HORIZONTAL;
				gbc_deliveryDatePicker.gridx = 2;
				gbc_deliveryDatePicker.gridy = 7;
				contentPanel.add(deliveryDatePicker, gbc_deliveryDatePicker);
			}
		}
		
		{
			msgLbl.setVerticalAlignment(SwingConstants.TOP);
			msgLbl.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_msgLbl = new GridBagConstraints();
			gbc_msgLbl.anchor = GridBagConstraints.NORTHWEST;
			gbc_msgLbl.insets = new Insets(0, 0, 0, 5);
			gbc_msgLbl.gridx = 2;
			if(type == OrderPageType.SALE) {
				gbc_msgLbl.gridy = 9;
			}
			if(type == OrderPageType.LEASE) {
				gbc_msgLbl.gridy = 10;
			}
			if(type == OrderPageType.PURCHASE) {
				gbc_msgLbl.gridy = 8;
			}
			
			contentPanel.add(msgLbl, gbc_msgLbl);
		}
		{
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[]{81, 70, 250, 0, 250, 80, 84, 0};
			gbl_buttonPane.rowHeights = new int[]{23, 0};
			gbl_buttonPane.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
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
						HomePage.start();
					}
				});
			}
			{
				middleBtnPanel = new JPanel();
				GridBagConstraints gbc_panel = new GridBagConstraints();
				gbc_panel.insets = new Insets(0, 0, 0, 5);
				gbc_panel.fill = GridBagConstraints.BOTH;
				gbc_panel.gridx = 3;
				gbc_panel.gridy = 0;
				buttonPane.add(middleBtnPanel, gbc_panel);
				
				
				setAsReceivedBtn = new JButton("Set as received");
				setAsReceivedBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Purchase purchase = (Purchase)order;
							
							orderCtrl.setAsReceived(purchase);
							
							loadLists();
							loadData();
							getCurrentStock();
							setValuesToFields();
							
							middleBtnPanel.remove(setAsReceivedBtn);
							buttonPane.revalidate();
							buttonPane.repaint();
							
							msgLbl.setText(MessagesEnum.LEASERETURNEDSUCCESS.text);
							msgLbl.setForeground(Color.GREEN); 
							
						} catch(SQLException e1) {
							e1.printStackTrace();
							msgLbl.setForeground(Color.RED);
							msgLbl.setText(MessagesEnum.DBUPDATEERROR.text);
						} catch (Exception e2) {
							e2.printStackTrace();
							msgLbl.setForeground(Color.RED);
							msgLbl.setText(e2.getMessage());
						}
						setTitle();
					}
				});
				
				
				returnLeaseBtn = new JButton("Return lease");
				returnLeaseBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Lease lease = (Lease)order;
							
							orderCtrl.returnLease(lease);
							
							loadLists();
							loadData();
							getCurrentStock();
							setValuesToFields();
							
							middleBtnPanel.remove(returnLeaseBtn);
							buttonPane.revalidate();
							buttonPane.repaint();
							
							msgLbl.setText(MessagesEnum.LEASERETURNEDSUCCESS.text);
							msgLbl.setForeground(Color.GREEN); 
							
						} catch(SQLException e1) {
							e1.printStackTrace();
							msgLbl.setForeground(Color.RED);
							msgLbl.setText(MessagesEnum.DBUPDATEERROR.text);
						} catch (Exception e2) {
							e2.printStackTrace();
							msgLbl.setForeground(Color.RED);
							msgLbl.setText(e2.getMessage());
						}
						setTitle();
					}
				});
				
				
				sendSaleBtn = new JButton("Send sale");
				saleDeliveredBtn = new JButton("Sale delivered");
				
				saleDeliveredBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Sale sale = (Sale)order;
							
							orderCtrl.saleDelivered(sale.getId());
							
							loadData();
							setValuesToFields();
							
							middleBtnPanel.remove(saleDeliveredBtn);
							buttonPane.revalidate();
							buttonPane.repaint();
							
							msgLbl.setText(MessagesEnum.SALEDELIVEREDSUCCESS.text);
							msgLbl.setForeground(Color.GREEN); 
							
						} catch(SQLException e1) {
							e1.printStackTrace();
							msgLbl.setForeground(Color.RED);
							msgLbl.setText(MessagesEnum.DBUPDATEERROR.text);
						} catch (Exception e2) {
							e2.printStackTrace();
							msgLbl.setForeground(Color.RED);
							msgLbl.setText(e2.getMessage());
						}
						setTitle();
					}
				});
				
				sendSaleBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Sale sale = (Sale)order;
							
							orderCtrl.sendSale(sale.getId());
							
							loadData();
							setValuesToFields();
							
							middleBtnPanel.remove(sendSaleBtn);
							if((position == PositionType.ADMIN || position == PositionType.SALESMAN)) {
								middleBtnPanel.add(saleDeliveredBtn);
							}
							buttonPane.revalidate();
							buttonPane.repaint();
							
							msgLbl.setText(MessagesEnum.SENDSALESUCCESS.text);
							msgLbl.setForeground(Color.GREEN); 
							
						} catch(SQLException e1) {
							e1.printStackTrace();
							msgLbl.setForeground(Color.RED);
							msgLbl.setText(MessagesEnum.DBUPDATEERROR.text);
						} catch (Exception e2) {
							e2.printStackTrace();
							msgLbl.setForeground(Color.RED);
							msgLbl.setText(e2.getMessage());
						}
						setTitle();
					}
				});
				
			}
			{
				saveBtn.setEnabled(false);
				gbc_saveBtn.anchor = GridBagConstraints.WEST;
				gbc_saveBtn.insets = new Insets(0, 0, 0, 5);
				gbc_saveBtn.gridx = 5;
				gbc_saveBtn.gridy = 0;
				buttonPane.add(saveBtn, gbc_saveBtn);
				saveBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(type == OrderPageType.SALE) {
							saveSale();
							if(order != null) {
								Sale sale = (Sale)order;
								setId(sale.getId());
								setValuesToFields();
								if((position == PositionType.ADMIN || position == PositionType.SALESMAN)) {
									middleBtnPanel.add(sendSaleBtn);
								}
							}
						}
						if(type == OrderPageType.LEASE) {
							saveLease();
							if(order != null) {
								Lease lease = (Lease)order;
								setId(lease.getId());
								middleBtnPanel.add(returnLeaseBtn);
							}
						}
						if(type == OrderPageType.PURCHASE) {
							savePurchase();
							if(order != null) {
								Purchase purchase = (Purchase)order;
								setId(purchase.getId());
								middleBtnPanel.add(setAsReceivedBtn);
							}
						}
						setTitle();
					}
				});
			}
		}
		
		getCurrentStock();
		
		try {
			loadLists();
			if(id != null) {
				loadData();
				setValuesToFields();
			}
			
			stockProducts.stream().forEach(stockProduct -> stockProductsDefaultModel.addElement(String.valueOf(stockProduct.getProduct().getBarcode()) + " - " + stockProduct.getProduct().getName()));
			customers.stream().forEach(customer -> customersDefaultModel.addElement(String.valueOf(customer.getCvrNo()) + " - " + customer.getFirstName() + " " + customer.getLastName()));
		} catch (SQLException e) {
			e.printStackTrace();
			msgLbl.setText(MessagesEnum.DBERROR.text);
			msgLbl.setForeground(Color.RED);
		}
		
		if(type == OrderPageType.PURCHASE && id != null){
			{
				middleBtnPanel.add(setAsReceivedBtn);
			}
		}
		if(type == OrderPageType.LEASE && id != null){
			{
				middleBtnPanel.add(returnLeaseBtn);
			}
		}
		if(type == OrderPageType.SALE && id != null){
			if((Date)shippingDatePicker.getModel().getValue() == null && (position == PositionType.ADMIN || position == PositionType.SALESMAN)){
				{
					middleBtnPanel.add(sendSaleBtn);
				}
			}
			if((Date)shippingDatePicker.getModel().getValue() != null && (Date)deliveryDatePicker.getModel().getValue() == null && (position == PositionType.ADMIN || position == PositionType.SALESMAN)){
				{
					middleBtnPanel.add(saleDeliveredBtn);
				}
			}
		}
	}
	
	private void loadLists () throws SQLException {	
		try {
			stockProducts = orderCtrl.getStockProducts(LoginContainer.getInstance().getCurrentUser().getWarehouse().getId());
			if(type == OrderPageType.SALE) {
				stockProducts = stockProducts.stream().filter(stockProduct -> stockProduct.getProduct().getSalePrice() != null).collect(Collectors.toCollection(ArrayList::new));
			}
			if(type == OrderPageType.LEASE) {
				stockProducts = stockProducts.stream().filter(stockProduct -> stockProduct.getProduct().getLeasePrice() != null).collect(Collectors.toCollection(ArrayList::new));
			}
			if(type == OrderPageType.PURCHASE) {
				stockProducts = stockProducts.stream().filter(stockProduct -> stockProduct.getProduct().getPurchasePrice() != null).collect(Collectors.toCollection(ArrayList::new));
			}
			customers = orderCtrl.getCustomers();
		} catch (SQLException e) {
			throw e;
		}
	}
	
	private void loadData() throws SQLException{
		try {
			order = this.orderCtrl.getOrder(type, id.longValue());
		} catch(SQLException e) {
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
	
	private Customer getCurrentCustomer() {
		if(customersComboBox.getSelectedItem() != null) {
			String stringCustomer = String.valueOf(customersComboBox.getSelectedItem()).substring(0,8);
			return customers.stream()
								.filter(c -> stringCustomer.equals(c.getCvrNo()))
								.findAny()
								.orElse(null);
		}
		return null;
	}
	
	private void saveSale() {

		try {
			order = new Sale(-1,
					  ParsingHelper.tryParseDouble(totalPriceValue.getText()),
					  noteField.getText(),
					  LocalDate.now(),
					  LoginContainer.getInstance().getCurrentUser().getWarehouse(),
					  orderLines,
					  null,
					  -1,
					  ParsingHelper.convertToLocalDateViaInstant((Date)shippingDatePicker.getModel().getValue()),
					  ParsingHelper.convertToLocalDateViaInstant((Date)deliveryDatePicker.getModel().getValue()),
					  getCurrentCustomer()
					);
			
			order = orderCtrl.createOrder(order);
			Sale sale = (Sale)order;
			setId(sale.getId());
			
			msgLbl.setText(MessagesEnum.SALECREATED.text);
			msgLbl.setForeground(Color.GREEN);
			
			loadLists();
			loadData();
			getCurrentStock();
			setValuesToFields();
			
			buttonPane.remove(saveBtn);
			buttonPane.revalidate();
			buttonPane.repaint();
			
		} catch(SQLException e) {
			e.printStackTrace();
			msgLbl.setText(MessagesEnum.DBERROR.text);
			msgLbl.setForeground(Color.RED);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			msgLbl.setForeground(Color.RED);
			msgLbl.setText(MessagesEnum.PARSEERROR.text);
		} catch (Exception e) {
			e.printStackTrace();
			msgLbl.setForeground(Color.RED);
			msgLbl.setText(e.getMessage());
		} finally {
			msgOrderLineLbl.setText("");
		}
	}
	
	private void saveLease() {
		try {
			LocalDate borrowDate = ParsingHelper.convertToLocalDateViaInstant((Date)borrowDatePicker.getModel().getValue());
			LocalDate expectedReturnDate = ParsingHelper.convertToLocalDateViaInstant((Date)expectedReturnDatePicker.getModel().getValue());
			
			if(borrowDate == null) {
				throw new Exception(MessagesEnum.EMPTYBORROWDATE.text);
			}
			if(expectedReturnDate == null) {
				throw new Exception(MessagesEnum.EMPTYEXPECTEDRETURNDATE.text);
			}
			
			order = new Lease(-1,
							  ParsingHelper.tryParseDouble(totalPriceValue.getText()),
							  noteField.getText(),
							  LocalDate.now(),
							  LoginContainer.getInstance().getCurrentUser().getWarehouse(),
							  orderLines,
							  null,
							  -1,
							  borrowDate,
							  expectedReturnDate,
							  ParsingHelper.convertToLocalDateViaInstant((Date)realReturnDatePicker.getModel().getValue()),
							  getCurrentCustomer()
							);
			
			order = orderCtrl.createOrder(order);
			Lease lease = (Lease)order;
			setId(lease.getId());
			
			msgLbl.setText(MessagesEnum.LEASECREATED.text);
			msgLbl.setForeground(Color.GREEN);
			
			loadLists();
			loadData();
			getCurrentStock();
			setValuesToFields();
			
			buttonPane.remove(saveBtn);
			buttonPane.revalidate();
			buttonPane.repaint();
			
		} catch(SQLException e) {
			e.printStackTrace();
			msgLbl.setText(MessagesEnum.DBERROR.text);
			msgLbl.setForeground(Color.RED);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			msgLbl.setForeground(Color.RED);
			msgLbl.setText(MessagesEnum.PARSEERROR.text);
		} catch (Exception e) {
			e.printStackTrace();
			msgLbl.setForeground(Color.RED);
			msgLbl.setText(e.getMessage());
		} finally {
			msgOrderLineLbl.setText("");
		}
	}
	
	private void savePurchase() {
		try {	
			order = new Purchase(-1,
							  ParsingHelper.tryParseDouble(totalPriceValue.getText()),
							  noteField.getText(),
							  LocalDate.now(),
							  LoginContainer.getInstance().getCurrentUser().getWarehouse(),
							  orderLines,
							  null,
							  -1,
							  ParsingHelper.convertToLocalDateViaInstant((Date)deliveryDatePicker.getModel().getValue())
							);
			
			order = orderCtrl.createOrder(order);
			Purchase purchase = (Purchase)order;
			setId(purchase.getId());
			
			msgLbl.setText(MessagesEnum.LEASECREATED.text);
			msgLbl.setForeground(Color.GREEN);
			
			loadLists();
			loadData();
			getCurrentStock();
			setValuesToFields();
			
			buttonPane.remove(saveBtn);
			buttonPane.revalidate();
			buttonPane.repaint();
			
		} catch(SQLException e) {
			e.printStackTrace();
			msgLbl.setText(MessagesEnum.DBERROR.text);
			msgLbl.setForeground(Color.RED);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			msgLbl.setForeground(Color.RED);
			msgLbl.setText(MessagesEnum.PARSEERROR.text);
		} catch (Exception e) {
			e.printStackTrace();
			msgLbl.setForeground(Color.RED);
			msgLbl.setText(e.getMessage());
		} finally {
			msgOrderLineLbl.setText("");
		}
	}
	
	private void addProductToOrderLine() {
		try {
			int requestedAmount = ParsingHelper.tryParseInt(quantityField.getText());
			
			if(requestedAmount < 1) {
				throw new Exception(MessagesEnum.VALUEHIGHERTHANZEROERROR.text);
			}
			
			int amount = 0;
			if(type != OrderPageType.PURCHASE) {
				availableAmountOfSelectedProduct = selectedStockProduct.getAmount();
				orderLines.stream().filter(ol -> ol.getStockProduct().getProduct().getBarcode() == selectedStockProduct.getProduct().getBarcode())
								   .forEach(ol -> {
									   availableAmountOfSelectedProduct -= ol.getAmount();
								   });
				amount = requestedAmount > availableAmountOfSelectedProduct ? availableAmountOfSelectedProduct : requestedAmount;
			}
			else {
				currtentMaxAmountOfSelectedProduct = selectedStockProduct.getMaxStock();
				orderLines.stream().filter(ol -> ol.getStockProduct().getProduct().getBarcode() == selectedStockProduct.getProduct().getBarcode())
				   .forEach(ol -> {
					   currtentMaxAmountOfSelectedProduct -= ol.getAmount();
				   });
				
				if(currtentMaxAmountOfSelectedProduct < requestedAmount + selectedStockProduct.getAmount()) {
					throw new Exception(MessagesEnum.PURCHASEREACHMAXPRODUCTS.text + (currtentMaxAmountOfSelectedProduct - selectedStockProduct.getAmount()));
				}
				amount = requestedAmount; 
			}
			
			orderLines.add(new OrderLine(-1, requestedAmount, amount, selectedStockProduct));
			
			totalPriceValue.setText(String.valueOf(orderCtrl.calculateTotalPrice(orderLines, type)));
			
			fillOrderLinesToTableAndRefresh();
			
			quantityField.setText("");
			
			buttonPane.remove(saveBtn);
			boolean enabled = orderLines.size() > 0;
			saveBtn.setEnabled(enabled);
			buttonPane.add(saveBtn, gbc_saveBtn);
			buttonPane.revalidate();
			buttonPane.repaint();
			
			msgOrderLineLbl.setText(MessagesEnum.PRODUCTADDEDTOORDER.text);
			msgOrderLineLbl.setForeground(Color.GREEN);
		}
		catch(NumberFormatException e1) {
			contentPanel.add(scrollPane, gbc_scrollPane);
			e1.printStackTrace();
			msgOrderLineLbl.setText(MessagesEnum.PARSEERROR.text);
			msgOrderLineLbl.setForeground(Color.RED);
		} catch (Exception e2) {
			contentPanel.add(scrollPane, gbc_scrollPane);
			e2.printStackTrace();
			msgOrderLineLbl.setText(e2.getMessage());
			msgOrderLineLbl.setForeground(Color.RED);
		}
	}
	
	private void fillOrderLinesToTableAndRefresh() {
		contentPanel.remove(scrollPane);
		
		ols = new Object[orderLines.size()][];
		
		for(int i = 0; i < orderLines.size(); i++) {
			OrderLine ol = orderLines.get(i);
			Object [] newData = {
				ol.getStockProduct().getProduct().getBarcode(),
				ol.getStockProduct().getProduct().getName(),
				Integer.toString(ol.getStockProduct().getAmount()),
				Integer.toString(ol.getRequestedAmount()),
				Integer.toString(ol.getAmount()),
			};

			ols[i] = newData;
		}
		
		olsTabelModel = new DefaultTableModel(ols, columns);
		olsTable = new JTable(olsTabelModel);
		scrollPane = new JScrollPane(olsTable);
		
		contentPanel.add(scrollPane, gbc_scrollPane);
		contentPanel.revalidate();
		contentPanel.repaint();
	}
	
	private void setId(long id) {
		this.id = Long.valueOf(id);
	}
	
	private void setValuesToFields() {
		totalPriceValue.setText(Double.toString(order.getTotalPrice()));
		getCurrentStock();
		
		orderLines = order.getOrderLines();
		fillOrderLinesToTableAndRefresh();
		
		if(order instanceof Sale){
			Sale sale = (Sale)order;
			
			customersComboBox.getModel().setSelectedItem(new String(sale.getCustomer().getCvrNo()) + " - " + sale.getCustomer().getFirstName() + " " + sale.getCustomer().getLastName());
			
			if(sale.getShippingDate() != null) {
				shippingDateModel.setDate(sale.getShippingDate().getYear(), sale.getShippingDate().getMonthValue(), sale.getShippingDate().getDayOfMonth());
				shippingDateModel.setSelected(true);
			}
			
			if(sale.getDeliveryDate() != null) {
				deliveryDateModel.setDate(sale.getDeliveryDate().getYear(), sale.getDeliveryDate().getMonthValue(), sale.getDeliveryDate().getDayOfMonth());
				deliveryDateModel.setSelected(true);
			}
		}
		
		if(order instanceof Lease){
			Lease lease = (Lease)order;
			
			customersComboBox.getModel().setSelectedItem(new String(lease.getCustomer().getCvrNo()) + " - " + lease.getCustomer().getFirstName() + " " + lease.getCustomer().getLastName());
			
			if(lease.getBorrowDate() != null) {
				borrowDateModel.setDate(lease.getBorrowDate().getYear(), lease.getBorrowDate().getMonthValue(), lease.getBorrowDate().getDayOfMonth());
				borrowDateModel.setSelected(true);
			}
			
			if(lease.getExpectedReturnDate() != null) {
				expectedReturnDateModel.setDate(lease.getExpectedReturnDate().getYear(), lease.getExpectedReturnDate().getMonthValue(), lease.getExpectedReturnDate().getDayOfMonth());
				expectedReturnDateModel.setSelected(true);
			}
			
			if(lease.getRealReturnDate() != null) {
				realReturnDateModel.setDate(lease.getRealReturnDate().getYear(), lease.getRealReturnDate().getMonthValue(), lease.getRealReturnDate().getDayOfMonth());
				realReturnDateModel.setSelected(true);
			}
		}
		
		if(order instanceof Purchase){
			Purchase purchase = (Purchase)order;
			
			if(purchase.getDeliveryDate() != null) {
				deliveryDateModel.setDate(purchase.getDeliveryDate().getYear(), purchase.getDeliveryDate().getMonthValue(), purchase.getDeliveryDate().getDayOfMonth());
				deliveryDateModel.setSelected(true);
			}
		}
	}
	
	private void setTitle() {
		if(type == OrderPageType.SALE) {
			if(id == null) {
				title.setText("Register sale");
			}
			else {
				title.setText("Sale detail");
			}
		}
		if(type == OrderPageType.LEASE) {
			if(id == null) {
				title.setText("Register lease");
			}
			else {
				title.setText("Lease detail");
			}
		}
		if(type == OrderPageType.PURCHASE) {
			if(id == null) {
				title.setText("Register purchase");
			}
			else {
				title.setText("Purchase detail");
			}
		}
	}
}