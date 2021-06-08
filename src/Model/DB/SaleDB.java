package Model.DB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import Model.IF.SaleIF;
import Model.Model.MessagesEnum;
import Model.Model.OrderPageType;
import Model.Model.Sale;

public class SaleDB implements SaleIF {

	private OrderDB orderDb = new OrderDB();
	private CustomerDB customerDb = new CustomerDB();
	
	@Override
	public Sale createSale(Sale sale) throws Exception {
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
                throw new Exception(MessagesEnum.DBSAVEERROR.text);
            }
            con.commit();
		} catch (SQLException e) {
			con.rollback();
			throw e;
		}
		return sale;
	}
	
	public Sale getSale(long id) throws SQLException {
		Sale res = null;
		
		String sqlSale = "SELECT * FROM Sale WHERE id = ?";
		
	    Connection con = DBConnection.getInstance().getConnection();
	    
	    try {	    	
	    	PreparedStatement preparedStmt = con.prepareStatement(sqlSale);	  
	    	
	    	preparedStmt.setLong(1, id);
	    	
	    	ResultSet rs = preparedStmt.executeQuery();
	    	
	    	if (rs.next()) {
	    		Sale sale = buildSale(rs);
	    		
	    		sale.setOrder(orderDb.getOrder(sale.getId(), OrderPageType.SALE));
	    		sale.setCustomer(customerDb.getCustomerById(rs.getLong("customer_id")));
	    		
	    		res = sale;
	    	}
	    	
	    	con.commit();
	    } catch (SQLException e) {
	    	con.rollback();
	    	throw e;
	    }
	
		return res;
	}

	@Override
	public void sendSale(long id) throws Exception {
		String sql = "UPDATE Sale SET shipping_date = ? WHERE id = ?";

		LocalDate shippingDate = LocalDate.now();
		
	     Connection con = DBConnection.getInstance().getConnection();
	
	     try {
			PreparedStatement preparedStmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setObject(1, shippingDate != null ? java.sql.Date.valueOf(shippingDate) : null);
			preparedStmt.setLong(2, id);
			
			preparedStmt.executeUpdate();
			
			ResultSet rs = preparedStmt.getGeneratedKeys();
            if (!rs.next()) {
            	throw new Exception(MessagesEnum.DBUPDATEERROR.text);
            }
            con.commit();
		} catch (Exception e) {
			con.rollback();
			throw e;
		}
	}
	
	@Override
	public void saleDelivered(long id) throws Exception {
		String sql = "UPDATE Sale SET delivery_date = ? WHERE id = ?";

		LocalDate deliveryDate = LocalDate.now();
		
	     Connection con = DBConnection.getInstance().getConnection();
	
	     try {
			PreparedStatement preparedStmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setObject(1, deliveryDate != null ? java.sql.Date.valueOf(deliveryDate) : null);
			preparedStmt.setLong(2, id);
			
			preparedStmt.executeUpdate();
			
			ResultSet rs = preparedStmt.getGeneratedKeys();
            if (!rs.next()) {
            	throw new Exception(MessagesEnum.DBUPDATEERROR.text);
            }
            con.commit();
		} catch (Exception e) {
			con.commit();
			throw e;
		}
	}
	
	
	private Sale buildSale(ResultSet rs) throws SQLException {
		Date sqlShippingDate = rs.getDate("shipping_date");
		Date sqlDeliveryDate = rs.getDate("delivery_date");
		return new Sale(
				-1,
				-1,
				null,
				null,
				null,
				null,
				null,
				rs.getLong("id"),
				sqlShippingDate != null ? sqlShippingDate.toLocalDate() : null,
				sqlDeliveryDate != null ? sqlDeliveryDate.toLocalDate() : null,
				null
		);
	}
}
