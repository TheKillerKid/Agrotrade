package UI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;

import Model.DB.ProductDB;
import Model.DB.StockProductDB;
import Model.Model.LoginContainer;
import Model.Model.Product;
import Model.Model.StockProduct;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import java.awt.GridBagConstraints;
import java.awt.Insets;


public class ProductListPage extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	String[] columnNames = {
		"First Name",
	    "Last Name",
    };

	private static class JTableButtonRenderer implements TableCellRenderer {        
        @Override public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JButton button = (JButton)value;
            return button;  
        }
    }

	public static void start() {
		try {
			ProductListPage dialog = new ProductListPage();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private ArrayList<StockProduct> loadData () {
		StockProductDB stockProductDB = new StockProductDB();
		ArrayList<StockProduct> stockProducts = new ArrayList<StockProduct>();

		try {
			stockProducts = stockProductDB.getStockProducts(LoginContainer.getInstance().getCurrentUser().getWarehouse().getId());
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
		gbl_contentPanel.columnWidths = new int[]{38, 205, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 117, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			ArrayList<StockProduct> stockProducts = loadData(); 
			String[][] data = new String[stockProducts.size()][];

				
			for (int i = 0; i < stockProducts.size(); i++) {
				StockProduct stockProduct = stockProducts.get(i);
				String [] newData = {
					stockProduct.getProduct().getBarcode(),
					stockProduct.getProduct().getName(),
					stockProduct.getProduct().getCategory().getName(),
					Integer.toString(stockProduct.getAmount()),
					String.format("%s/%s", stockProduct.getMinStock(), stockProduct.getMaxStock()),
					stockProduct.getProduct().getSupplier().getSupplierName(),
					""
				};

				data[i] = newData;

			}
			
			String column[]={"Barcode", "Name", "Category", "Ammount", "Min/Max stock", "Supplier", "Go to"};

			JTable table = new JTable(data, column);

			TableCellRenderer buttonRenderer = new JTableButtonRenderer();
			table.getColumn("Go to").setCellRenderer(buttonRenderer);

			GridBagConstraints gbc_table = new GridBagConstraints();
			gbc_table.gridheight = 3;
			gbc_table.gridwidth = 3;
			gbc_table.insets = new Insets(0, 0, 5, 5);
			gbc_table.fill = GridBagConstraints.BOTH;
			gbc_table.gridx = 0;
			gbc_table.gridy = 0;
		    JScrollPane sp=new JScrollPane(table);

			contentPanel.add(sp, gbc_table);
		}

		for (StockProduct stockProduct : loadData()) {
			System.out.println(stockProduct.getMaxStock());
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
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

}
