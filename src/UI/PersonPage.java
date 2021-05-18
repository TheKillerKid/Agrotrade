package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import java.awt.Choice;

public class PersonPage extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Panel buttonsPanel;
	private JButton backBtn;
	private JButton saveBtn;

	private HomePage homePage = new HomePage();

	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonPage dialog = new PersonPage();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public PersonPage() {
		setBounds(100, 100, 745, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("Create Person");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 2;
			gbc_lblNewLabel.gridy = 1;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{;
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
						homePage.start();
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
					@Override
					public void mouseClicked(MouseEvent e) {
						homePage.start();
						dispose();
					}
				});
				GridBagConstraints gbc_saveBtn = new GridBagConstraints();
				gbc_saveBtn.insets = new Insets(0, 0, 0, 5);
				gbc_saveBtn.anchor = GridBagConstraints.NORTH;
				gbc_saveBtn.gridx = 3;
				gbc_saveBtn.gridy = 0;
				buttonsPanel.add(saveBtn, gbc_saveBtn);
			}
		}
	}
}
