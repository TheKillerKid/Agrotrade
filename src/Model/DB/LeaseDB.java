package Model.DB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import Model.DBIF.LeaseIF;
import Model.Model.Lease;
import Model.Model.MessagesEnum;
import Model.Model.OrderLine;
import Model.Model.OrderPageType;

public class LeaseDB implements LeaseIF{

	private OrderDB orderDb = new OrderDB();
	private StockProductDB stockProductDb = new StockProductDB();
	
	private CustomerDB customerDb = new CustomerDB();
	
	@Override
	public Lease createLease(Lease lease) throws Exception {
		String sqlCreate = "INSERT INTO Lease (borrow_date, expected_return_date, real_return_date, customer_id) VALUES (?,?,?,?)";
		
		LocalDate borrowDate = lease.getBorrowDate();
		LocalDate realReturnDate = lease.getRealReturnDate();
		LocalDate expectedReturnDate = lease.getExpectedReturnDate();
		long customerId = lease.getCustomer().getId();
			
	     Connection con = DBConnection.getInstance().getConnection();
	
	     try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCreate, Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setObject(1, borrowDate != null ? java.sql.Date.valueOf(borrowDate) : null);
			preparedStmt.setObject(2, expectedReturnDate != null ? java.sql.Date.valueOf(expectedReturnDate) : null);
			preparedStmt.setObject(3, realReturnDate != null ? java.sql.Date.valueOf(realReturnDate) : null);
			preparedStmt.setLong(4, customerId);
			
			preparedStmt.executeUpdate();
			
			ResultSet rs = preparedStmt.getGeneratedKeys();
            if (rs.next()) {
                lease.setId(rs.getLong(1)); 
    			lease.setOrder(orderDb.createOrder(lease));
            }
            else {
                throw new SQLException(MessagesEnum.DBSAVEERROR.text);
            }
		} catch (SQLException e) {
			throw e;
		}
		return lease;
	}
	
	@Override
	public Lease getLease(long id) throws SQLException {
		Lease res = null;
		
		String sqlSale = "SELECT * FROM Lease WHERE id = ?";
		
	    Connection con = DBConnection.getInstance().getConnection();
	    
	    try {	    	
	    	PreparedStatement preparedStmt = con.prepareStatement(sqlSale);	  
	    	
	    	preparedStmt.setLong(1, id);
	    	
	    	ResultSet rs = preparedStmt.executeQuery();
	    	
	    	if (rs.next()) {
	    		Lease lease = buildLease(rs);
	    		
	    		lease.setOrder(orderDb.getOrder(lease.getId(), OrderPageType.LEASE));
	    		lease.setCustomer(customerDb.getCustomerById(rs.getLong("customer_id")));
	    		
	    		res = lease;
	    	}
	    } catch (SQLException e) {
	    	throw e;
	    }
	
		return res;
	}
	
	@Override
	public void returnLease(Lease lease) throws Exception {
		String sql = "UPDATE Lease SET real_return_date = ? WHERE id = ?";

		LocalDate realReturnDate = LocalDate.now();
		
		
	     Connection con = DBConnection.getInstance().getConnection();
	
	     try {
			PreparedStatement preparedStmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setObject(1, realReturnDate != null ? java.sql.Date.valueOf(realReturnDate) : null);
			preparedStmt.setLong(2, lease.getId());
			
			preparedStmt.executeUpdate();
			
			ResultSet rs = preparedStmt.getGeneratedKeys();
            if (!rs.next()) {
            	throw new Exception(MessagesEnum.DBUPDATEERROR.text);
            }
            else {
            	for(OrderLine ol : lease.getOrderLines()) {
            		stockProductDb.returnLeaseOrPurchaseStockProduct(ol.getStockProduct().getId(), ol.getAmount(), ol.getStockProduct().getWarehouseId());
            	}
            }
		} catch (Exception e) {
			throw e;
		}
	}
	
	private Lease buildLease(ResultSet rs) throws SQLException {
		Date sqlBorrowDate = rs.getDate("borrow_date");
		Date sqlExpectedReturnDate = rs.getDate("expected_return_date");
		Date sqlRealReturnDate = rs.getDate("real_return_date");
		return new Lease(
					-1,
					-1,
					null,
					null,
					null,
					null,
					null,
					rs.getLong("id"),
					sqlBorrowDate != null ? sqlBorrowDate.toLocalDate() : null,
					sqlExpectedReturnDate != null ? sqlExpectedReturnDate.toLocalDate() : null,
					sqlRealReturnDate != null ? sqlRealReturnDate.toLocalDate() : null,
					null
		);
	}
}
