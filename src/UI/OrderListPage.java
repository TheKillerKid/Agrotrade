package UI;

import java.awt.EventQueue;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.OrderController;
import Model.Model.OrderPageType;
import Model.Model.OrderView;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class OrderListPage extends JDialog {
	private JScrollPane sp;
	private OrderController orderController = new OrderController();
	private ButtonGroup typeGroup;
	private ButtonGroup saleGroup;
	private JCheckBox saleWithFilter;
	private JCheckBox purchaseWithFilter;
	private JCheckBox returnDateExceded;
	private JCheckBox returned;
	private ButtonGroup purchaseGroup;
	private final JPanel contentPanel = new JPanel();


	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderListPage dialog = new OrderListPage();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					throw e;
				}
			}
		});
	}
	
	private ArrayList<OrderView> loadData() {
		ArrayList<OrderView> res = new ArrayList<OrderView>();

		try {
			res = orderController.getOrders();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
	private Object[][] getData(String type, ArrayList<OrderView> list) {
		if (type == OrderPageType.SALE.toString()) {			
			ArrayList<OrderView> filteredList = list.stream()
					.filter((order) -> {
						Boolean isSale = order.getSaleId() != 0;

						if (saleWithFilter.isSelected() && saleGroup.getSelection().getActionCommand() == "shipped") {
							return isSale && order.getSaleShippingDate() != null;
						}
						
						if (saleWithFilter.isSelected() && saleGroup.getSelection().getActionCommand() == "delivered") {
							return isSale && order.getSaleDeliveryDate() != null;
						}
						
						return isSale;
					})
					.collect(Collectors.toCollection(ArrayList::new));
			Object[][] data = new Object[filteredList.size()][];

			for (int i = 0; i < filteredList.size(); i++) {
				OrderView order = filteredList.get(i);
				
				LocalDate deliveryDate = order.getSaleDeliveryDate();
				LocalDate shippingDate = order.getSaleShippingDate();
				
				Object [] newData = {
						order.getSaleId(),
						order.getOrderCreationDate(),
						order.getCvrNo(),
						String.format("%s DKK", order.getTotalPrice()),
						deliveryDate != null ? deliveryDate.format(DateTimeFormatter.ofPattern("dd MM yyyy")) : "–",
						shippingDate != null ? shippingDate.format(DateTimeFormatter.ofPattern("dd MM yyyy")) : "–",
						getType(order),
						"Open",
				};
				
				data[i] = newData;
				
			}
			
			return data;
		}
		
		if (type == OrderPageType.PURCHASE.toString()) {			
			ArrayList<OrderView> filteredList = list.stream()
			.filter((order) -> {
				Boolean isPurchase = order.getPurchaseId() != 0;
				
				if (purchaseWithFilter.isSelected() && purchaseGroup.getSelection().getActionCommand() == "received") {
					return isPurchase && order.getPurchaseDeliveryDate() != null;
				}

				if (purchaseWithFilter.isSelected() && purchaseGroup.getSelection().getActionCommand() == "notReceived") {
					return isPurchase && order.getPurchaseDeliveryDate() == null;
				}
				
				return isPurchase;
			})
			.collect(Collectors.toCollection(ArrayList::new));

			Object[][] data = new Object[filteredList.size()][];
			
			for (int i = 0; i < filteredList.size(); i++) {
				OrderView order = filteredList.get(i);
				
				LocalDate deliveryDate = order.getPurchaseDeliveryDate();
				
				Object [] newData = {
						order.getPurchaseId(),
						order.getOrderCreationDate(),
						order.getCvrNo(),
						String.format("%s DKK", order.getTotalPrice()),
						deliveryDate != null ? deliveryDate.format(DateTimeFormatter.ofPattern("dd MM yyyy")) : "–",
						getType(order),
						"Open",
				};
				
				data[i] = newData;
				
			}
			
			return data;
		}
		
		if (type == OrderPageType.LEASE.toString()) {			
			ArrayList<OrderView> filteredList = list.stream()
					.filter((order) -> {
						Boolean isLease = order.getLeaseId() != 0;
						Boolean isExceded = order.getLeaseRealReturnDate() != null && order.getLeaseRealReturnDate().isAfter(order.getLeaseExpectedReturnDate());
						
						if (returnDateExceded.isSelected()) {
							return isLease && isExceded;
						}
						
						if (returned.isSelected()) {
							return isLease && order.getLeaseRealReturnDate() != null;
						}
						
						if (returnDateExceded.isSelected() && returned.isSelected()) {
							return isLease && isExceded && order.getLeaseRealReturnDate() != null;
						}
						
						return isLease;
					})
					.collect(Collectors.toCollection(ArrayList::new));
			Object[][] data = new Object[filteredList.size()][];
			
			
			for (int i = 0; i < filteredList.size(); i++) {
				OrderView order = filteredList.get(i);
				LocalDate borrowDate = order.getLeaseBorrowDate();
				LocalDate expectedDate = order.getLeaseExpectedReturnDate();
				LocalDate realDate = order.getLeaseRealReturnDate();
				
				Object [] newData = {
						order.getLeaseId(),
						order.getOrderCreationDate(),
						order.getCvrNo(),
						String.format("%s DKK", order.getTotalPrice()),
						borrowDate != null ? borrowDate.format(DateTimeFormatter.ofPattern("dd MM yyyy")) : "–",
						expectedDate != null ? expectedDate.format(DateTimeFormatter.ofPattern("dd MM yyyy")) : "–",
						realDate != null ? realDate.format(DateTimeFormatter.ofPattern("dd MM yyyy")) : "–",
						getType(order),
						"Open",
				};
				
				data[i] = newData;
				
			}
			
			return data;
		}
		
		
		return new Object[0][];
	}
	
	private String[] getColumns(String type) {
		if (type == OrderPageType.SALE.toString()) {
			String column[] = {"Id", "Created at", "CVR", "Total price", "Delivery date", "Shipping date", "Type", ""};
			
			return column;
		}

		if (type == OrderPageType.PURCHASE.toString()) {
			String column[] = {"Id", "Created at", "CVR", "Total price", "Delivery date", "Type", ""};
			
			return column;
		}

		if (type == OrderPageType.LEASE.toString()) {
			String column[] = {"Id", "Created at", "CVR", "Total price", "Borrow date", "Expected return date", "Real return date", "Type", ""};
			
			return column;
		}

		return new String[0];
	}
	
	private OrderPageType getType(OrderView order) {
		if (order.getSaleId() != 0) {
			return OrderPageType.SALE;
		}
		if (order.getPurchaseId() != 0) {
			return OrderPageType.PURCHASE;
		}
		if (order.getLeaseId() != 0) {
			return OrderPageType.LEASE;
		}

		return null;
	}
	


	public OrderListPage() {
		setBounds(300, 300, 1280, 800);
		typeGroup = new ButtonGroup();
		purchaseGroup = new ButtonGroup();
		saleGroup = new ButtonGroup();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1279, 0};
		gridBagLayout.rowHeights = new int[]{738, 29, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_contentPanel = new GridBagConstraints();
		gbc_contentPanel.fill = GridBagConstraints.BOTH;
		gbc_contentPanel.insets = new Insets(0, 0, 5, 0);
		gbc_contentPanel.gridx = 0;
		gbc_contentPanel.gridy = 0;
		getContentPane().add(contentPanel, gbc_contentPanel);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{210, 0, 90, 0, 210, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{36, 0, 0, 0, 298, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JRadioButton saleRadio = new JRadioButton("Sale");
			saleRadio.setActionCommand(OrderPageType.SALE.toString());
			saleRadio.setSelected(true);
			typeGroup.add(saleRadio);
			GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
			gbc_rdbtnNewRadioButton.anchor = GridBagConstraints.WEST;
			gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 5);
			gbc_rdbtnNewRadioButton.gridx = 0;
			gbc_rdbtnNewRadioButton.gridy = 0;
			contentPanel.add(saleRadio, gbc_rdbtnNewRadioButton);
		}
		{
			saleWithFilter = new JCheckBox("With filter");
			GridBagConstraints gbc_saleWithFilter = new GridBagConstraints();
			gbc_saleWithFilter.anchor = GridBagConstraints.WEST;
			gbc_saleWithFilter.insets = new Insets(0, 0, 5, 5);
			gbc_saleWithFilter.gridx = 1;
			gbc_saleWithFilter.gridy = 0;
			contentPanel.add(saleWithFilter, gbc_saleWithFilter);
		}
		{
			JRadioButton saleShipped = new JRadioButton("Shipped");
			saleShipped.setActionCommand("shipped");
			GridBagConstraints gbc_saleShipped = new GridBagConstraints();
			gbc_saleShipped.anchor = GridBagConstraints.WEST;
			gbc_saleShipped.insets = new Insets(0, 0, 5, 5);
			gbc_saleShipped.gridx = 2;
			gbc_saleShipped.gridy = 0;
			contentPanel.add(saleShipped, gbc_saleShipped);
			saleGroup.add(saleShipped);
		}
		{
			JRadioButton saleDelivered = new JRadioButton("Delivered");
			saleDelivered.setActionCommand("delivered");
			GridBagConstraints gbc_saleDelivered = new GridBagConstraints();
			gbc_saleDelivered.anchor = GridBagConstraints.WEST;
			gbc_saleDelivered.insets = new Insets(0, 0, 5, 5);
			gbc_saleDelivered.gridx = 3;
			gbc_saleDelivered.gridy = 0;
			contentPanel.add(saleDelivered, gbc_saleDelivered);
			saleGroup.add(saleDelivered);
		}
		{
			JRadioButton purchaseRadio = new JRadioButton("Purchase");
			purchaseRadio.setActionCommand(OrderPageType.PURCHASE.toString());
			typeGroup.add(purchaseRadio);
			GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
			gbc_rdbtnNewRadioButton_1.anchor = GridBagConstraints.WEST;
			gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 5);
			gbc_rdbtnNewRadioButton_1.gridx = 0;
			gbc_rdbtnNewRadioButton_1.gridy = 1;
			contentPanel.add(purchaseRadio, gbc_rdbtnNewRadioButton_1);
		}
		{
			purchaseWithFilter = new JCheckBox("With filter");
			GridBagConstraints gbc_purchaseWithFilter = new GridBagConstraints();
			gbc_purchaseWithFilter.anchor = GridBagConstraints.WEST;
			gbc_purchaseWithFilter.insets = new Insets(0, 0, 5, 5);
			gbc_purchaseWithFilter.gridx = 1;
			gbc_purchaseWithFilter.gridy = 1;
			contentPanel.add(purchaseWithFilter, gbc_purchaseWithFilter);
		}
		{
			JRadioButton purchaseReceived = new JRadioButton("Recieved");
			GridBagConstraints gbc_purchaseReceived = new GridBagConstraints();
			gbc_purchaseReceived.insets = new Insets(0, 0, 5, 5);
			gbc_purchaseReceived.gridx = 2;
			gbc_purchaseReceived.gridy = 1;
			contentPanel.add(purchaseReceived, gbc_purchaseReceived);
			purchaseGroup.add(purchaseReceived);
			purchaseReceived.setActionCommand("received");
		}
		{
			JRadioButton purchaseNotRecieved = new JRadioButton("Not received");
			GridBagConstraints gbc_purchaseNotRecieved = new GridBagConstraints();
			gbc_purchaseNotRecieved.insets = new Insets(0, 0, 5, 5);
			gbc_purchaseNotRecieved.gridx = 3;
			gbc_purchaseNotRecieved.gridy = 1;
			contentPanel.add(purchaseNotRecieved, gbc_purchaseNotRecieved);
			purchaseGroup.add(purchaseNotRecieved);
			purchaseNotRecieved.setActionCommand("notReceived");
		}
		{
			JRadioButton leaseRadio = new JRadioButton("Lease");
			leaseRadio.setActionCommand(OrderPageType.LEASE.toString());
			typeGroup.add(leaseRadio);
			GridBagConstraints gbc_rdbtnNewRadioButton_2 = new GridBagConstraints();
			gbc_rdbtnNewRadioButton_2.anchor = GridBagConstraints.WEST;
			gbc_rdbtnNewRadioButton_2.insets = new Insets(0, 0, 5, 5);
			gbc_rdbtnNewRadioButton_2.gridx = 0;
			gbc_rdbtnNewRadioButton_2.gridy = 2;
			contentPanel.add(leaseRadio, gbc_rdbtnNewRadioButton_2);
		}
		{
			returnDateExceded = new JCheckBox("Return date exceded");
			GridBagConstraints gbc_returnDateExceded = new GridBagConstraints();
			gbc_returnDateExceded.insets = new Insets(0, 0, 5, 5);
			gbc_returnDateExceded.gridx = 1;
			gbc_returnDateExceded.gridy = 2;
			contentPanel.add(returnDateExceded, gbc_returnDateExceded);
		}
		{
			returned = new JCheckBox("Returned");
			GridBagConstraints gbc_returned = new GridBagConstraints();
			gbc_returned.insets = new Insets(0, 0, 5, 5);
			gbc_returned.gridx = 2;
			gbc_returned.gridy = 2;
			contentPanel.add(returned, gbc_returned);
		}
		{
			JButton searchButton = new JButton("Search");
			GridBagConstraints gbc_searchButton = new GridBagConstraints();
			gbc_searchButton.anchor = GridBagConstraints.WEST;
			gbc_searchButton.insets = new Insets(0, 0, 5, 5);
			gbc_searchButton.gridx = 0;
			gbc_searchButton.gridy = 3;
			contentPanel.add(searchButton, gbc_searchButton);
			searchButton.addActionListener(new ActionListener () {
				public void actionPerformed(ActionEvent e) {
					if (sp != null) {
						contentPanel.remove(sp);
					}

					String type = getTypeSelection();
					ArrayList<OrderView> orders = loadData();

					Object[][] data = getData(type, orders);
					String[] columns = getColumns(getTypeSelection());
					

					DefaultTableModel model = new DefaultTableModel(data, columns);
					JTable table = new JTable(model);
					
					AbstractAction open = new AbstractAction() {
						public void actionPerformed(ActionEvent e) {
					        JTable table = (JTable)e.getSource();
					        int modelRow = Integer.valueOf(e.getActionCommand());

					        long orderId = (Long) table.getValueAt(modelRow, 0);
					        OrderPageType type = (OrderPageType) table.getValueAt(modelRow, columns.length - 2);

					        OrderPage.start(type, orderId);
					        dispose();
					    }
					};
					
					ButtonColumn buttonColumn = new ButtonColumn(table, open, columns.length - 1);
					buttonColumn.setMnemonic(KeyEvent.VK_D);
					
					GridBagConstraints gbc_table = new GridBagConstraints();
					gbc_table.gridwidth = 5;
					gbc_table.insets = new Insets(0, 0, 0, 5);
					gbc_table.fill = GridBagConstraints.BOTH;
					gbc_table.gridx = 0;
					gbc_table.gridy = 4;
					
					sp = new JScrollPane(table);					

					contentPanel.add(sp, gbc_table);
					contentPanel.revalidate();
					contentPanel.repaint();
				}
			});
		}
		{
			JPanel buttonPane = new JPanel();
			GridBagConstraints gbc_buttonPane = new GridBagConstraints();
			gbc_buttonPane.fill = GridBagConstraints.BOTH;
			gbc_buttonPane.gridx = 0;
			gbc_buttonPane.gridy = 1;
			getContentPane().add(buttonPane, gbc_buttonPane);
			buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener () {
					public void actionPerformed(ActionEvent e) {
						HomePage.start();
						dispose();
					}
				});
			}
		}
	}
	
	private String getTypeSelection() {
		   ButtonModel typeGroupModel = typeGroup.getSelection();
		   
		   if (typeGroupModel != null) {
			   return typeGroupModel.getActionCommand();
		   }
		   
		   return null;
		}
}
