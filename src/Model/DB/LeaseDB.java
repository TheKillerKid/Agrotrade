package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import Model.DBIF.LeaseIF;
import Model.Model.Lease;
import Model.Model.MessagesEnum;

public class LeaseDB implements LeaseIF{

	private OrderDB orderDb = new OrderDB();
	
	@Override
	public Lease createLease(Lease lease) throws SQLException {
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
}
