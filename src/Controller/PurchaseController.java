package Controller;

import java.sql.SQLException;

import Model.DB.PurchaseDB;
import Model.IF.PurchaseIF;
import Model.Model.Purchase;
import Model.Model.Sale;

public class PurchaseController {
private PurchaseIF purchaseDb = new PurchaseDB();
	
	public Purchase createPurchase(Purchase purchase) throws Exception {
		try {
			return purchaseDb.createPurchase(purchase);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Purchase getPurchase(long id) throws SQLException {
		try {
			return purchaseDb.getPurchase(id);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public void setAsReceived(Purchase purchase) throws Exception {
		try {
			this.purchaseDb.setAsReceived(purchase);
		} catch (Exception e) {
			throw e;
		}
	}
}
