package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import Model.DBIF.SaleIF;
import Model.Model.Sale;

public class SaleDB implements SaleIF {

	@Override
	public long createSale(Sale sale) throws SQLException {
		String sqlCreate = "INSERT INTO Sale (shipping_date, delivery_date, customer_id) VALUES (?,?,?)";
		
		long id = 0;
		LocalDate shippingDate = null;
		LocalDate deliveryDate = null;
		long customerId = sale.getCustomer().getId();
		
		try (Connection con = DBConnection.getInstance().getConnection()) {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCreate);
			preparedStmt.setObject(1, shippingDate);
			preparedStmt.setObject(2, deliveryDate);
			preparedStmt.setLong(3, customerId);
			int saleId= preparedStmt.executeUpdate();
			sale.setId(saleId); 
			
			
		} catch (SQLException e) {
			throw e;
		}
		return id;
	}
}
