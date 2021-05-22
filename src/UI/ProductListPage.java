package UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.DB.ProductDB;
import Model.DB.StockProductDB;
import Model.Model.Product;
import Model.Model.StockProduct;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProductListPage extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static StockProductDB stockProductDB = new StockProductDB();
	private static ArrayList<StockProduct> stockProducts = new ArrayList<StockProduct>();


	public static void start() {
		try {
			ProductListPage dialog = new ProductListPage();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			loadData();

			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void loadData () throws SQLException {
		try {
			stockProducts = stockProductDB.getStockProducts(1);
		} catch (SQLException e) {
			throw e;
		}
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

		for (StockProduct stockProduct : stockProducts) {
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
						dispose();
					}
				});
			}
		}
	}

}
