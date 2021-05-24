package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import Model.DBIF.SaleIF;
import Model.Model.MessagesEnum;
import Model.Model.OrderLine;
import Model.Model.Sale;

public class SaleDB implements SaleIF {

	private OrderDB orderDb = new OrderDB();
	
	@Override
	public Sale createSale(Sale sale) throws SQLException {
		String sqlCreate = "INSERT INTO Sale (shipping_date, delivery_date, customer_id) VALUES (?,?,?)";

		LocalDate shippingDate = sale.getShippingDate();
		LocalDate deliveryDate = sale.getDeliveryDate();
		long customerId = sale.getCustomer().getId();
		
     Connection con = DBConnection.getInstance().getConnection();

     try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCreate, Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setObject(1, java.sql.Date.valueOf(shippingDate));
			preparedStmt.setObject(2, java.sql.Date.valueOf(deliveryDate));
			preparedStmt.setLong(3, customerId);
			
			preparedStmt.executeUpdate();
			
			ResultSet rs = preparedStmt.getGeneratedKeys();
            if (rs.next()) {
                sale.setId(rs.getLong(1)); 
    			sale.setOrder(orderDb.createOrder(sale));
            }
            else {
                throw new SQLException(MessagesEnum.DBSAVEERROR.text);
            }
		} catch (SQLException e) {
			throw e;
		}
		return sale;
	}
}
