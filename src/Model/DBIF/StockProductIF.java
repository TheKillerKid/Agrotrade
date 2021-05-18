package Model.DBIF;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Model.StockProduct;

public interface StockProductIF {
	
	ArrayList<StockProduct> getStockProducts(long warehouseId) throws SQLException;

}
