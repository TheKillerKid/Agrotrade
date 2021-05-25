package Controller;

import java.sql.SQLException;

import Model.DB.PurchaseDB;
import Model.Model.Purchase;

public class PurchaseController {
private PurchaseDB purchaseDB = new PurchaseDB();
	
	public Purchase createPurchase(Purchase purchase) throws SQLException {
		try {
			return purchaseDB.createPurchase(purchase);
		} catch (SQLException e) {
			throw e;
		}
	}
}
