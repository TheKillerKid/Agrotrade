package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.DB.StockProductDB;

import Model.Model.StockProduct;

public class StockProductContoller {
	private StockProductDB stockProductDb = new StockProductDB();
	
	public ArrayList<StockProduct> getStockProducts(long warehouseId) throws SQLException {
		return stockProductDb.getStockProducts(warehouseId);
	}
	
}
