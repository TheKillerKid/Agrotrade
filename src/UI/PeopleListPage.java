package UI;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.PersonController;
import Model.Model.Customer;
import Model.Model.Employee;
import Model.Model.Person;
import Model.Model.PersonFilter;
import Model.Model.PersonPageType;
import Model.Model.Supplier;

import java.awt.GridBagLayout;
import javax.swing.JCheckBox;

public class PeopleListPage extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JScrollPane sp;
	private JCheckBox customerCheckbox = new JCheckBox("Customers");
	private JCheckBox employeesCheckbox = new JCheckBox("Employees");
	private JCheckBox suppliersCheckbox = new JCheckBox("Suppliers");
	
	private PersonFilter filter = new PersonFilter();

	/**
	 * Launch the application.
	 */
	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PeopleListPage dialog = new PeopleListPage();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
	
				} catch (Exception e) {
					throw e;
				}
			}
		});
	}

	private ArrayList<Person> loadData() {
		PersonController peopleController = new PersonController();

		filter.setEmployee(employeesCheckbox.isSelected());		
		filter.setCustomer(customerCheckbox.isSelected());
		filter.setSupplier(suppliersCheckbox.isSelected());

		ArrayList<Person> res = new ArrayList<Person>();

		try {
			res = peopleController.getPeople(filter);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public PersonPageType getType(Person person) {
		if (person instanceof Customer) {
			return PersonPageType.CUSTOMER;
		}
		if (person instanceof Employee) {
			return PersonPageType.EMPLOYEE;
		}
		if (person instanceof Supplier) {
			return PersonPageType.SUPPLIER;
		}

		return null;
	}

	public PeopleListPage() {
		setBounds(150, 150, 1280, 800);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{109, 100, 0, 0, 599, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 22, 733, 39, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_contentPanel = new GridBagConstraints();
		gbc_contentPanel.fill = GridBagConstraints.BOTH;
		gbc_contentPanel.insets = new Insets(0, 0, 5, 0);
		gbc_contentPanel.gridx = 0;
		gbc_contentPanel.gridy = 0;
		
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.anchor = GridBagConstraints.WEST;
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox.gridx = 0;
		gbc_chckbxNewCheckBox.gridy = 1;
		getContentPane().add(employeesCheckbox, gbc_chckbxNewCheckBox);
		{
			GridBagConstraints gbc_chckbxNewCheckBox_1 = new GridBagConstraints();
			gbc_chckbxNewCheckBox_1.insets = new Insets(0, 0, 5, 5);
			gbc_chckbxNewCheckBox_1.gridx = 1;
			gbc_chckbxNewCheckBox_1.gridy = 1;
			getContentPane().add(customerCheckbox, gbc_chckbxNewCheckBox_1);
		}
		{
			GridBagConstraints gbc_chckbxNewCheckBox_2 = new GridBagConstraints();
			gbc_chckbxNewCheckBox_2.insets = new Insets(0, 0, 5, 5);
			gbc_chckbxNewCheckBox_2.gridx = 2;
			gbc_chckbxNewCheckBox_2.gridy = 1;
			getContentPane().add(suppliersCheckbox, gbc_chckbxNewCheckBox_2);
		}
		{
			JButton searchButton = new JButton("Search");
			GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
			gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton.gridx = 3;
			gbc_btnNewButton.gridy = 1;
			getContentPane().add(searchButton, gbc_btnNewButton);
			searchButton.addActionListener(new ActionListener () {
				public void actionPerformed(ActionEvent e) {
					if (sp != null) {
						getContentPane().remove(sp);
					}

					ArrayList<Person> people = loadData();
					
					Object[][] data = new Object[people.size()][];

					
					for (int i = 0; i < people.size(); i++) {
						Person person = people.get(i);
						
						Object [] newData = {
							person.getId(),
							person.getFirstName(),
							person.getLastName(),
							getType(person),
							"Open",
						};

						data[i] = newData;

					}
					String column[] = {"Id", "First name", "Last name", "Type", ""};

					DefaultTableModel model = new DefaultTableModel(data, column);
					JTable table = new JTable(model);
					
					AbstractAction open = new AbstractAction() {
						public void actionPerformed(ActionEvent e) {
					        JTable table = (JTable)e.getSource();
					        int modelRow = Integer.valueOf(e.getActionCommand());

					        long personId = (Long) table.getValueAt(modelRow, 0);
					        PersonPageType type = (PersonPageType) table.getValueAt(modelRow, 3);
					        
					        PersonPage.start(type, personId);
					        dispose();
					    }
					};
					
					ButtonColumn buttonColumn = new ButtonColumn(table, open, 4);
					buttonColumn.setMnemonic(KeyEvent.VK_D);
					
					GridBagConstraints gbc_table = new GridBagConstraints();
					gbc_table.gridwidth = 5;
					gbc_table.insets = new Insets(0, 0, 5, 5);
					gbc_table.fill = GridBagConstraints.BOTH;
					gbc_table.gridx = 0;
					gbc_table.gridy = 3;
					
					sp = new JScrollPane(table);					

					getContentPane().add(sp, gbc_table);
					getContentPane().revalidate();
					getContentPane().repaint();
				}
			});
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			GridBagConstraints gbc_buttonPane = new GridBagConstraints();
			gbc_buttonPane.insets = new Insets(0, 0, 0, 5);
			gbc_buttonPane.gridwidth = 5;
			gbc_buttonPane.anchor = GridBagConstraints.NORTH;
			gbc_buttonPane.fill = GridBagConstraints.HORIZONTAL;
			gbc_buttonPane.gridx = 0;
			gbc_buttonPane.gridy = 4;
			getContentPane().add(buttonPane, gbc_buttonPane);

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
