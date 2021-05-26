package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.DB.StockProductDB;
import Model.IF.StockProductIF;
import Model.Model.StockProduct;

public class StockProductContoller {
	private StockProductIF stockProductDb = new StockProductDB();
	
	public ArrayList<StockProduct> getStockProducts(long warehouseId) throws SQLException {
		try {
			return stockProductDb.getStockProducts(warehouseId);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public StockProduct getStockProduct(long id, long warehouseId) throws SQLException {
		try {
			return stockProductDb.getStockProduct(id, warehouseId);
		} catch (SQLException e) {
			throw e;
		}
	}
}