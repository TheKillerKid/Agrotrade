package Controller;

import java.sql.SQLException;

import Model.DB.PurchaseDB;
import Model.Model.Purchase;
import Model.Model.Sale;

public class PurchaseController {
private PurchaseDB purchaseDB = new PurchaseDB();
	
	public Purchase createPurchase(Purchase purchase) throws Exception {
		try {
			return purchaseDB.createPurchase(purchase);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Purchase getPurchase(long id) throws SQLException {
		try {
			return purchaseDB.getPurchase(id);
		} catch (SQLException e) {
			throw e;
		}
	}
}
