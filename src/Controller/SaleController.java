package Controller;

import java.sql.SQLException;

import Model.DB.SaleDB;
import Model.Model.Sale;

public class SaleController {
	private SaleDB saleDB = new SaleDB();
	
	public Sale createSale(Sale sale) throws SQLException {
		try {
			return saleDB.createSale(sale);
		} catch (SQLException e) {
			throw e;
		}
	}
}
