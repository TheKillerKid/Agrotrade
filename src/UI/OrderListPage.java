package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;

public class OrderListPage extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

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
	
	public static void main(String[] args) {
		try {
			OrderListPage dialog = new OrderListPage();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Create the dialog.
	 */
	public OrderListPage() {
		setBounds(300, 300, 1280, 800);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{210, 210, 210, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{36, 0, 0, 125, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		ButtonGroup typeGroup = new ButtonGroup();
		{
			JRadioButton saleRadio = new JRadioButton("Sale");
			GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
			gbc_rdbtnNewRadioButton.anchor = GridBagConstraints.WEST;
			gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 5);
			gbc_rdbtnNewRadioButton.gridx = 0;
			gbc_rdbtnNewRadioButton.gridy = 0;
			contentPanel.add(saleRadio, gbc_rdbtnNewRadioButton);
			typeGroup.add(saleRadio);
		}
		{
			JRadioButton purchaseRadio = new JRadioButton("Purchase");
			GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
			gbc_rdbtnNewRadioButton_1.anchor = GridBagConstraints.WEST;
			gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 5);
			gbc_rdbtnNewRadioButton_1.gridx = 0;
			gbc_rdbtnNewRadioButton_1.gridy = 1;
			contentPanel.add(purchaseRadio, gbc_rdbtnNewRadioButton_1);
			typeGroup.add(purchaseRadio);
		}
		{
			JRadioButton leaseRadio = new JRadioButton("Lease");
			GridBagConstraints gbc_rdbtnNewRadioButton_2 = new GridBagConstraints();
			gbc_rdbtnNewRadioButton_2.anchor = GridBagConstraints.WEST;
			gbc_rdbtnNewRadioButton_2.insets = new Insets(0, 0, 5, 5);
			gbc_rdbtnNewRadioButton_2.gridx = 0;
			gbc_rdbtnNewRadioButton_2.gridy = 2;
			contentPanel.add(leaseRadio, gbc_rdbtnNewRadioButton_2);
			typeGroup.add(leaseRadio);
		}
		{
			table = new JTable();
			GridBagConstraints gbc_table = new GridBagConstraints();
			gbc_table.gridwidth = 4;
			gbc_table.insets = new Insets(0, 0, 0, 5);
			gbc_table.fill = GridBagConstraints.BOTH;
			gbc_table.gridx = 0;
			gbc_table.gridy = 3;
			contentPanel.add(table, gbc_table);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
