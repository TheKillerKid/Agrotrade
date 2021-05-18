package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import Model.DBIF.InvoiceIF;

import Model.Model.Invoice;

import Model.Model.Order;

public class InvoiceDB implements InvoiceIF {

	private OrderDB orderDB = new OrderDB();
	
	@Override
	public long createInvoice(Invoice invoice) throws SQLException {
				
		String sqlCreate = "INSERT INTO Invoice (payment_date, amount, order_id) VALUES (?,?,?)";
		
		long id = 0;
		LocalDate paymentDate = invoice.getPaymentDate();
		int amount = (Integer) 0;
		long orderId = 0;
		
		try (Connection con = DBConnection.getInstance().getConnection()) {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCreate);
			preparedStmt.setObject(1, paymentDate);
			preparedStmt.setInt(2, amount);
			preparedStmt.setLong(3, orderId);
			
			id = preparedStmt.executeUpdate();
		} catch(SQLException e) {
			throw e;
		}
				
		return id;
	}
	
	
}
