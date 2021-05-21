package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import Model.DBIF.InvoiceIF;

import Model.Model.Invoice;

public class InvoiceDB implements InvoiceIF {
	
	@Override
	public long createInvoice(Invoice invoice, long orderId) throws SQLException {
				
		String sqlCreate = "INSERT INTO Invoice (payment_date, amount, order_id) VALUES (?,?,?)";
		
		long id = 0;
		LocalDate paymentDate = invoice.getPaymentDate();
		double amount = invoice.getAmount();
		
     Connection con = DBConnection.getInstance().getConnection();

     try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCreate);
			preparedStmt.setObject(1, java.sql.Date.valueOf(paymentDate));
			preparedStmt.setDouble(2, amount);
			preparedStmt.setLong(3, orderId);
			
			id = preparedStmt.executeUpdate();
		} catch(SQLException e) {
			throw e;
		}
				
		return id;
	}
	
	
}
