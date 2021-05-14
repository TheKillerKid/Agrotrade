package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JDesktopPane;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import java.awt.Color;
import javax.swing.JMenu;

public class HomePage extends JDialog {

	
	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage dialog = new HomePage();
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
	public HomePage() {
		setBounds(100, 100, 740, 480);
		getContentPane().setLayout(new BorderLayout());
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.BLACK);
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Order");
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("Product");
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("Person");
		menuBar.add(mnNewMenu_2);
	}
}
