package Model.DBIF;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Model.StockProduct;

public interface StockProductIF {
	
	ArrayList<StockProduct> getStockProducts(long warehouseId) throws SQLException;
	
	StockProduct getStockProduct(long id, long warehouseId) throws SQLException;
	
	void sellOrLeaseStockProduct(long stockProductId, int amount, long warehouseId) throws SQLException;
	
	void returnLeaseOrPurchaseStockProduct(long stockProductId, int amount, long warehouseId) throws SQLException;
}
