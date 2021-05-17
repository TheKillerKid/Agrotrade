package Model.DBIF;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Model.StockProduct;

public interface StockProductIF {
	
	ArrayList<StockProduct> getStockProducts(long warehouseId) throws SQLException;
	
	ArrayList<StockProduct> createStockProducts(long productId, int minStock, int maxStock) throws SQLException;

}
