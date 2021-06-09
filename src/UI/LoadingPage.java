package UI;

import javax.swing.JDialog;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;

import Model.Model.LoginContainer;

import javax.swing.ImageIcon;

public class LoadingPage extends JDialog implements Runnable {
	private static LoadingPage dialog = null;
	private JLabel loadingLbl;
	private boolean running;
	
	public void start() {
		running = true;
		
		if(LoginContainer.getInstance().betaEnabled) {
			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			dialog.setVisible(true);
		}
		
		boolean oneDot = true;
		boolean twoDots = false;
		boolean threeDots = false;
		while(running) {
			if(oneDot) {
				loadingLbl.setText("Processing.");
				System.out.println("Processing.");
				oneDot = false;
				twoDots = true;
			} else if(twoDots) {
				loadingLbl.setText("Processing..");
				System.out.println("Processing..");
				twoDots = false;
				threeDots = true;
			} else if(threeDots) {
				loadingLbl.setText("Processing...");
				System.out.println("Processing...");
				threeDots = false;
				oneDot = true;
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			getContentPane().revalidate();
			getContentPane().repaint();
		}

		dialog.setVisible(false);
	}
	
	private LoadingPage() {
		setBounds(580, 380, 400, 400);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		{
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(LoadingPage.class.getResource("/Resources/tracktor-loading.gif")));
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 1;
			getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		}
		
		loadingLbl = new JLabel("Loading...");
		GridBagConstraints gbc_loadingLbl = new GridBagConstraints();
		gbc_loadingLbl.insets = new Insets(0, 0, 5, 5);
		gbc_loadingLbl.gridx = 1;
		gbc_loadingLbl.gridy = 2;
		getContentPane().add(loadingLbl, gbc_loadingLbl);
		
	}
	
	@Override
	public void run() {
		start();
	}
	
	public static LoadingPage getInstance() {
		if(dialog == null) {
			dialog = new LoadingPage();
		}
		return dialog;
	}
	
	public void destroy() {
		running = false;
	}

}
