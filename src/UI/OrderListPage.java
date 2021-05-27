package UI;

import java.awt.EventQueue;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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

import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

@SuppressWarnings("serial")
public class OrderListPage extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JScrollPane sp;
	
	private OrderController orderController = new OrderController();

	private ButtonGroup typeGroup = new ButtonGroup();
	
	private JCheckBox saleShipped = new JCheckBox("Shipped");
	private JCheckBox saleNotShipped = new JCheckBox("Not shipped");
	private JCheckBox saleDelivered = new JCheckBox("Delivered");
	private JCheckBox saleNotDelivered = new JCheckBox("Not delivered");
	private JCheckBox purchaseReceived = new JCheckBox("Recieved");
	private JCheckBox purchaseNotReceived = new JCheckBox("Not received");
	
	private JCheckBox leaseReturnDateExceded = new JCheckBox("Return date exceded");
	private JCheckBox leaseReturned = new JCheckBox("Returned");
	
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

						if (saleShipped.isSelected()) {
							return isSale && order.getSaleShippingDate() != null;
						}
						if (saleNotShipped.isSelected()) {
							return isSale && order.getSaleShippingDate() == null;
						}
						
						if (saleDelivered.isSelected() ) {
							return isSale && order.getSaleDeliveryDate() != null;
						}
						if (saleNotDelivered.isSelected()) {
							return isSale && order.getSaleDeliveryDate() == null;
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
						shippingDate != null ? shippingDate.format(DateTimeFormatter.ofPattern("dd MM yyyy")) : "–",
						deliveryDate != null ? deliveryDate.format(DateTimeFormatter.ofPattern("dd MM yyyy")) : "–",
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
				
				if (purchaseReceived.isSelected()) {
					return isPurchase && order.getPurchaseDeliveryDate() != null;
				}

				if (purchaseNotReceived.isSelected()) {
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
						
						if (leaseReturnDateExceded.isSelected()) {
							return isLease && isExceded;
						}
						
						if (leaseReturned.isSelected()) {
							return isLease && order.getLeaseRealReturnDate() != null;
						}
						
						if (leaseReturnDateExceded.isSelected() && leaseReturned.isSelected()) {
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
			String column[] = {"Id", "Created at", "CVR", "Total price", "Shipping date", "Delivery date", "Type", ""};
			
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
		gbl_contentPanel.columnWidths = new int[]{179, 156, 90, 0, 210, 0};
		gbl_contentPanel.rowHeights = new int[]{35, 34, 35, 35, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JRadioButton saleRadio = new JRadioButton("Sale");
			saleRadio.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					if(saleRadio.isSelected()) {
						saleShipped.setEnabled(true);
						saleNotShipped.setEnabled(true);
						saleDelivered.setEnabled(true);
						saleNotDelivered.setEnabled(true);
					}
					else {
						saleShipped.setEnabled(false);
						saleNotShipped.setEnabled(false);
						saleDelivered.setEnabled(false);
						saleNotDelivered.setEnabled(false);
						
						saleShipped.setSelected(false);
						saleNotShipped.setSelected(false);
						saleDelivered.setSelected(false);
						saleNotDelivered.setSelected(false);
					}
				}
			});
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
			saleShipped.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					if(saleShipped.isSelected()) {
						saleNotShipped.setSelected(false);
						saleDelivered.setSelected(false);
						saleNotDelivered.setSelected(false);
					}
				}
			});
			GridBagConstraints gbc_saleShipped = new GridBagConstraints();
			gbc_saleShipped.anchor = GridBagConstraints.WEST;
			gbc_saleShipped.insets = new Insets(0, 0, 5, 5);
			gbc_saleShipped.gridx = 2;
			gbc_saleShipped.gridy = 0;
			contentPanel.add(saleShipped, gbc_saleShipped);
		}
		{
			saleNotShipped.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					if(saleNotShipped.isSelected()) {
						saleShipped.setSelected(false);
						saleDelivered.setSelected(false);
						saleNotDelivered.setSelected(false);
					}
				}
			});
			GridBagConstraints gbc_saleNotShipped = new GridBagConstraints();
			gbc_saleNotShipped.anchor = GridBagConstraints.WEST;
			gbc_saleNotShipped.insets = new Insets(0, 0, 5, 5);
			gbc_saleNotShipped.gridx = 3;
			gbc_saleNotShipped.gridy = 0;
			contentPanel.add(saleNotShipped, gbc_saleNotShipped);
		}
		{
			saleDelivered.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					if(saleDelivered.isSelected()) {
						saleShipped.setSelected(false);
						saleNotShipped.setSelected(false);
						saleNotDelivered.setSelected(false);
					}
				}
			});
			GridBagConstraints gbc_saleDelivered = new GridBagConstraints();
			gbc_saleDelivered.anchor = GridBagConstraints.WEST;
			gbc_saleDelivered.insets = new Insets(0, 0, 5, 5);
			gbc_saleDelivered.gridx = 2;
			gbc_saleDelivered.gridy = 1;
			contentPanel.add(saleDelivered, gbc_saleDelivered);
		}
		{
			saleNotDelivered.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					if(saleNotDelivered.isSelected()) {
						saleShipped.setSelected(false);
						saleNotShipped.setSelected(false);
						saleDelivered.setSelected(false);
					}
				}
			});
			GridBagConstraints gbc_saleNotDelivered = new GridBagConstraints();
			gbc_saleNotDelivered.anchor = GridBagConstraints.WEST;
			gbc_saleNotDelivered.insets = new Insets(0, 0, 5, 5);
			gbc_saleNotDelivered.gridx = 3;
			gbc_saleNotDelivered.gridy = 1;
			contentPanel.add(saleNotDelivered, gbc_saleNotDelivered);
		}
		{
			leaseReturned.setEnabled(false);
			leaseReturned.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					if(leaseReturned.isSelected()) {
						leaseReturnDateExceded.setSelected(false);
					}
				}
			});
			GridBagConstraints gbc_leaseReturned = new GridBagConstraints();
			gbc_leaseReturned.anchor = GridBagConstraints.WEST;
			gbc_leaseReturned.insets = new Insets(0, 0, 0, 5);
			gbc_leaseReturned.gridx = 2;
			gbc_leaseReturned.gridy = 3;
			contentPanel.add(leaseReturned, gbc_leaseReturned);
		}
		{
			leaseReturnDateExceded.setEnabled(false);
			leaseReturnDateExceded.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					if(leaseReturnDateExceded.isSelected()) {
						leaseReturned.setSelected(false);
					}
				}
			});
			GridBagConstraints gbc_leaseReturnDateExceded = new GridBagConstraints();
			gbc_leaseReturnDateExceded.anchor = GridBagConstraints.WEST;
			gbc_leaseReturnDateExceded.insets = new Insets(0, 0, 0, 5);
			gbc_leaseReturnDateExceded.gridx = 3;
			gbc_leaseReturnDateExceded.gridy = 3;
			contentPanel.add(leaseReturnDateExceded, gbc_leaseReturnDateExceded);
		}
		{
			JButton searchButton = new JButton("Search");
			GridBagConstraints gbc_searchButton = new GridBagConstraints();
			gbc_searchButton.anchor = GridBagConstraints.EAST;
			gbc_searchButton.gridx = 4;
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
			JRadioButton purchaseRadio = new JRadioButton("Purchase");
			purchaseRadio.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					if(purchaseRadio.isSelected()) {
						purchaseReceived.setEnabled(true);
						purchaseNotReceived.setEnabled(true);
					}
					else {
						purchaseReceived.setEnabled(false);
						purchaseNotReceived.setEnabled(false);
						
						purchaseReceived.setSelected(false);
						purchaseNotReceived.setSelected(false);
					}
				}
			});
			purchaseRadio.setActionCommand(OrderPageType.PURCHASE.toString());
			typeGroup.add(purchaseRadio);
			GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
			gbc_rdbtnNewRadioButton_1.anchor = GridBagConstraints.WEST;
			gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 5);
			gbc_rdbtnNewRadioButton_1.gridx = 0;
			gbc_rdbtnNewRadioButton_1.gridy = 2;
			contentPanel.add(purchaseRadio, gbc_rdbtnNewRadioButton_1);
		}
		{
			purchaseReceived.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					if(purchaseReceived.isSelected()) {
						purchaseNotReceived.setSelected(false);
					}
				}
			});
			GridBagConstraints gbc_purchaseReceived = new GridBagConstraints();
			gbc_purchaseReceived.anchor = GridBagConstraints.WEST;
			gbc_purchaseReceived.insets = new Insets(0, 0, 5, 5);
			gbc_purchaseReceived.gridx = 2;
			gbc_purchaseReceived.gridy = 2;
			contentPanel.add(purchaseReceived, gbc_purchaseReceived);
			purchaseReceived.setEnabled(false);
		}
		{
			purchaseNotReceived.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					if(purchaseNotReceived.isSelected()) {
						purchaseReceived.setSelected(false);
					}
				}
			});
			GridBagConstraints gbc_purchaseNotRecieved = new GridBagConstraints();
			gbc_purchaseNotRecieved.anchor = GridBagConstraints.WEST;
			gbc_purchaseNotRecieved.insets = new Insets(0, 0, 5, 5);
			gbc_purchaseNotRecieved.gridx = 3;
			gbc_purchaseNotRecieved.gridy = 2;
			contentPanel.add(purchaseNotReceived, gbc_purchaseNotRecieved);
			purchaseNotReceived.setEnabled(false);
		}
		{
			JRadioButton leaseRadio = new JRadioButton("Lease");
			leaseRadio.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					if(leaseRadio.isSelected()) {
						leaseReturned.setEnabled(true);
						leaseReturnDateExceded.setEnabled(true);
					}
					else {
						leaseReturned.setEnabled(false);
						leaseReturnDateExceded.setEnabled(false);
						
						leaseReturned.setSelected(false);
						leaseReturnDateExceded.setSelected(false);
					}
				}
			});
			leaseRadio.setActionCommand(OrderPageType.LEASE.toString());
			typeGroup.add(leaseRadio);
			GridBagConstraints gbc_rdbtnNewRadioButton_2 = new GridBagConstraints();
			gbc_rdbtnNewRadioButton_2.anchor = GridBagConstraints.WEST;
			gbc_rdbtnNewRadioButton_2.insets = new Insets(0, 0, 0, 5);
			gbc_rdbtnNewRadioButton_2.gridx = 0;
			gbc_rdbtnNewRadioButton_2.gridy = 3;
			contentPanel.add(leaseRadio, gbc_rdbtnNewRadioButton_2);
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
