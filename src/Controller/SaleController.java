package Controller;

import java.sql.SQLException;

import Model.DB.SaleDB;
import Model.Model.Sale;

public class SaleController {
	private SaleDB saleDB = new SaleDB();
	
	public Sale createSale(Sale sale) throws Exception {
		try {
			return saleDB.createSale(sale);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Sale getSale(long id) throws SQLException {
		try {
			return saleDB.getSale(id);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public void sendSale(long id) throws Exception {
		try {
			saleDB.sendSale(id);
		} catch (Exception e) {
			throw e;
		} 
	}
}
