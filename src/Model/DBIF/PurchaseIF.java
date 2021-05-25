package Model.DBIF;

import java.sql.SQLException;

import Model.Model.Purchase;

public interface PurchaseIF {
	Purchase createPurchase(Purchase purchase) throws SQLException;
}
