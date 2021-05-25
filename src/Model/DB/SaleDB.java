package Model.DB;

import java.sql.Connection;
import java.sql.Date;
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
	private CustomerDB customerDb = new CustomerDB();
	
	@Override
	public Sale createSale(Sale sale) throws SQLException {
		String sqlCreate = "INSERT INTO Sale (shipping_date, delivery_date, customer_id) VALUES (?,?,?)";

		LocalDate shippingDate = sale.getShippingDate();
		LocalDate deliveryDate = sale.getDeliveryDate();
		long customerId = sale.getCustomer().getId();
		
	     Connection con = DBConnection.getInstance().getConnection();
	
	     try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCreate, Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setObject(1, shippingDate != null ? java.sql.Date.valueOf(shippingDate) : null);
			preparedStmt.setObject(2, deliveryDate != null ? java.sql.Date.valueOf(deliveryDate) : null);
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
	
	public Sale getSale(long saleId) throws SQLException {
		Sale res = null;
		
		String sqlSale = "SELECT  * FROM Sale WHERE id = ?";
		
	    Connection con = DBConnection.getInstance().getConnection();
	    
	    try {	    	
	    	PreparedStatement preparedStmt = con.prepareStatement(sqlSale);	    	
	    	
	    	ResultSet rs = preparedStmt.executeQuery();
	    	
	    	if (rs.next()) {
	    		Sale sale = buildSale(rs);
	    		
	    		sale.setCustomer(customerDb.getCustomerById(rs.getLong("customer_id")));
	    		
	    		res = sale;
	    	}
	    } catch (SQLException e) {
	    	throw e;
	    }

		
		return res;
	}
	
	private Sale buildSale(ResultSet rs) throws SQLException {
		return new Sale(
				-1,
				0,
				null,
				null,
				null,
				null,
				null,
				rs.getLong("id"),
				rs.getDate("shipping_date").toLocalDate(),
				rs.getDate("delivery_date").toLocalDate(),
				null
		);
	}
}
