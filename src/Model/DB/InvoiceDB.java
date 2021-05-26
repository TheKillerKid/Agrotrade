package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import Model.IF.InvoiceIF;
import Model.Model.Invoice;
import Model.Model.MessagesEnum;

public class InvoiceDB implements InvoiceIF {
	
	@Override
	public Invoice createInvoice(Invoice invoice, long orderId) throws SQLException {
				
		String sqlCreate = "INSERT INTO Invoice (payment_date, amount, order_id) VALUES (?,?,?)";
		
		LocalDate paymentDate = invoice.getPaymentDate();
		double amount = invoice.getAmount();
		
		Connection con = DBConnection.getInstance().getConnection();

	     try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCreate, Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setObject(1, java.sql.Date.valueOf(paymentDate));
			preparedStmt.setDouble(2, amount);
			preparedStmt.setLong(3, orderId);
			
			preparedStmt.executeUpdate();
			
			ResultSet rs = preparedStmt.getGeneratedKeys();
            if (rs.next()) {
            	invoice.setId(rs.getLong(1));
            }
            else {
                throw new SQLException(MessagesEnum.DBSAVEERROR.text);
            }
		} catch(SQLException e) {
			throw e;
		}
				
		return invoice;
	}
	
	public Invoice getInvoice(long orderId) throws SQLException {
		Invoice res = null;
		String sqlInvoice = "SELECT * FROM Invoice WHERE order_id = ?";
		
		Connection con = DBConnection.getInstance().getConnection();
		try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlInvoice);
			
			preparedStmt.setLong(1, orderId);
			
			ResultSet rs = preparedStmt.executeQuery();
			
			if (rs.next()) {
				res = buildInvoice(rs);
			}
			
		} catch(SQLException e) {
			throw e;
		}
		
		return res;
	}
	
	private Invoice buildInvoice(ResultSet rs) throws SQLException {
		return new Invoice(rs.getLong("id"), rs.getDate("payment_date").toLocalDate(), rs.getDouble("amount"));
	}
}
