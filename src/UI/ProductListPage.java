package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.StockProductContoller;
import Model.Model.LoginContainer;
import Model.Model.StockProduct;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.JTable;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JLabel;


public class ProductListPage extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nameFiltereField;
	private JCheckBox isUnderMinAmountCheckBox;
	
	private ArrayList<StockProduct> stockProducts = new ArrayList<StockProduct>();
	private Object[][] data;

	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductListPage dialog = new ProductListPage();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					LoadingPage loadingPage = LoadingPage.getInstance();
					loadingPage.destroy();
				}
			}
		});

	}
	
	private ArrayList<StockProduct> loadData () {
		StockProductContoller stockProductController = new StockProductContoller();
		ArrayList<StockProduct> stockProducts = new ArrayList<StockProduct>();

		try {
			stockProducts = stockProductController.getStockProducts(LoginContainer.getInstance().getCurrentUser().getWarehouse().getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return stockProducts;
	}

	public ProductListPage() throws SQLException {
		setBounds(150, 150, 1280, 800);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{411, 0};
		gbl_contentPanel.rowHeights = new int[]{66, 117, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		String column[]={"Id", "Barcode", "Name", "Category", "Current stock", "Min/Max stock", "Supplier", "Location", ""};
		
		DefaultTableModel model = new DefaultTableModel(data, column);
		JTable table = new JTable( model );
		
		AbstractAction open = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
		        JTable table = (JTable)e.getSource();
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		       
		        long stockProductId = Long.parseLong((String) table.getValueAt(modelRow, 0)); 
		        
		        ProductPage.start(stockProductId);
		        dispose();
		    }
		};

		{
			JPanel filterPanal = new JPanel();
			GridBagConstraints gbc_filterPanal = new GridBagConstraints();
			gbc_filterPanal.insets = new Insets(0, 0, 5, 0);
			gbc_filterPanal.fill = GridBagConstraints.HORIZONTAL;
			gbc_filterPanal.gridx = 0;
			gbc_filterPanal.gridy = 0;
			contentPanel.add(filterPanal, gbc_filterPanal);
			GridBagLayout gbl_filterPanal = new GridBagLayout();
			gbl_filterPanal.columnWidths = new int[]{50, 0, 200, 0, 99, 0, 50, 0};
			gbl_filterPanal.rowHeights = new int[]{40, 0};
			gbl_filterPanal.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_filterPanal.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			filterPanal.setLayout(gbl_filterPanal);
			
			{
				JLabel nameFilterLbl = new JLabel("Name");
				GridBagConstraints gbc_nameFilterLbl = new GridBagConstraints();
				gbc_nameFilterLbl.insets = new Insets(0, 0, 0, 5);
				gbc_nameFilterLbl.gridx = 1;
				gbc_nameFilterLbl.gridy = 0;
				filterPanal.add(nameFilterLbl, gbc_nameFilterLbl);
			}
			{
				nameFiltereField = new JTextField();
				GridBagConstraints gbc_nameFiltereField = new GridBagConstraints();
				gbc_nameFiltereField.insets = new Insets(0, 0, 0, 5);
				gbc_nameFiltereField.fill = GridBagConstraints.HORIZONTAL;
				gbc_nameFiltereField.gridx = 2;
				gbc_nameFiltereField.gridy = 0;
				filterPanal.add(nameFiltereField, gbc_nameFiltereField);
				nameFiltereField.setColumns(10);
			}
			{
				if(nameFiltereField.getText().isEmpty()){
					this.stockProducts = loadData(); 
					this.data = new Object[stockProducts.size()][];
					
						
					for (int i = 0; i < stockProducts.size(); i++) {
						StockProduct stockProduct = stockProducts.get(i);
						Object [] newData = {
							Long.toString(stockProduct.getId()),
							stockProduct.getProduct().getBarcode(),
							stockProduct.getProduct().getName(),
							stockProduct.getProduct().getCategory().getName(),
							Integer.toString(stockProduct.getAmount()),
							String.format("%s/%s", stockProduct.getMinStock(), stockProduct.getMaxStock()),
							stockProduct.getProduct().getSupplier().getSupplierName(),
							stockProduct.getProductLocation(),
							"Open",
						};
		
						this.data[i] = newData;

					}
				}
			}
			{
				isUnderMinAmountCheckBox = new JCheckBox("Under minimum stock");
				GridBagConstraints gbc_isUnderMinAmountCheckBox = new GridBagConstraints();
				gbc_isUnderMinAmountCheckBox.insets = new Insets(0, 0, 0, 5);
				gbc_isUnderMinAmountCheckBox.gridx = 3;
				gbc_isUnderMinAmountCheckBox.gridy = 0;
				filterPanal.add(isUnderMinAmountCheckBox, gbc_isUnderMinAmountCheckBox);
			}
			{
				JButton btnSearch = new JButton("Search");
				GridBagConstraints gbc_btnSearch = new GridBagConstraints();
				gbc_btnSearch.anchor = GridBagConstraints.EAST;
				gbc_btnSearch.insets = new Insets(0, 0, 0, 5);
				gbc_btnSearch.gridx = 5;
				gbc_btnSearch.gridy = 0;
				filterPanal.add(btnSearch, gbc_btnSearch);
				
				btnSearch.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ArrayList<StockProduct> filteredList = stockProducts.stream()
								.filter((stockProduct) -> {
									System.out.println(stockProduct.getProduct().getName().contains(nameFiltereField.getText()) && 
									isUnderMinAmountCheckBox.isSelected() ? stockProduct.getAmount() < stockProduct.getMinStock() : true);

									return stockProduct.getProduct().getName().contains(nameFiltereField.getText()) && 
									isUnderMinAmountCheckBox.isSelected() ? stockProduct.getAmount() < stockProduct.getMinStock() : true;
								})
								.collect(Collectors.toCollection(ArrayList::new));
						setData(new Object[filteredList.size()][]);
						Object[][] data = new Object[filteredList.size()][];
						
						for (int i = 0; i < filteredList.size(); i++) {
							StockProduct stockProduct = filteredList.get(i);
							Object [] newData = {
								Long.toString(stockProduct.getId()),
								stockProduct.getProduct().getBarcode(),
								stockProduct.getProduct().getName(),
								stockProduct.getProduct().getCategory().getName(),
								Integer.toString(stockProduct.getAmount()),
								String.format("%s/%s", stockProduct.getMinStock(), stockProduct.getMaxStock()),
								stockProduct.getProduct().getSupplier().getSupplierName(),
								stockProduct.getProductLocation(),
								"Open",
							};

							data[i] = newData;
						}
						setData(data);
						DefaultTableModel model = new DefaultTableModel(data, column);
						table.setModel(model);
						ButtonColumn buttonColumn = new ButtonColumn(table, open, 8);
						buttonColumn.setMnemonic(KeyEvent.VK_D);
					}
				});
			}
			
			ButtonColumn buttonColumn = new ButtonColumn(table, open, 8);
			buttonColumn.setMnemonic(KeyEvent.VK_D);

			GridBagConstraints gbc_table = new GridBagConstraints();
			gbc_table.gridheight = 2;
			gbc_table.fill = GridBagConstraints.BOTH;
			gbc_table.gridx = 0;
			gbc_table.gridy = 1;
		    JScrollPane sp = new JScrollPane(table);

			contentPanel.add(sp, gbc_table);
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Back");
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
	
	public void setData(Object[][] data) {
		this.data = data;
	}
	
	public Object[][] getData() {
		return this.data;
	}
}
