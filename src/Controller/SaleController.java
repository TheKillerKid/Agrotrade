package Controller;

import java.sql.SQLException;

import Model.DB.SaleDB;
import Model.Model.Order;
import Model.Model.Sale;

public class SaleController {
	private SaleDB saleDB = new SaleDB();
	
	public long createSale(Sale sale) throws SQLException {
		try {
			return saleDB.createSale(sale);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
