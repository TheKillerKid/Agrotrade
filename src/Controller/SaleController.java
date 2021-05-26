package Controller;

import java.sql.SQLException;

import Model.DB.SaleDB;
import Model.IF.SaleIF;
import Model.Model.Sale;

public class SaleController {
	private SaleIF saleDb = new SaleDB();
	
	public Sale createSale(Sale sale) throws Exception {
		try {
			return saleDb.createSale(sale);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Sale getSale(long id) throws SQLException {
		try {
			return saleDb.getSale(id);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public void sendSale(long id) throws Exception {
		try {
			saleDb.sendSale(id);
		} catch (Exception e) {
			throw e;
		} 
	}
	
	public void saleDelivered(long id) throws Exception {
		try {
			saleDb.saleDelivered(id);
		} catch (Exception e) {
			throw e;
		} 
	}
}
