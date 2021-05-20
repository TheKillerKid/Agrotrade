package Model.DB;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import Model.DBIF.OrderIF;
import Model.Model.Order;
import Model.Model.Sale;

public class OrderDB implements OrderIF {
	
	public InvoiceDB invoiceDb = new InvoiceDB();
	public long createOrder(Order order) throws SQLException {
		String sqlCreate = "INSERT INTO Order (total_price, note, creation_date, warehouse_id, sale_id, lease_id, purchase_id) VALUES (?,?,?,?,?,?,?)";
		
		long id = 0;
		double totalPrice = order.getTotalPrice();
		String note = order.getNote();
		LocalDate creationDate = order.getCreationDate();
		long warehouseId = order.getWarehouse().getId();
		
		Long saleId = null;
		if(order instanceof Sale) {
			Sale sale = (Sale)order;
			saleId = sale.getId();
		}	
		Long leaseId = null;
		Long purchaseId = null;
		
		try(Connection con = DBConnection.getInstance().getConnection()) {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCreate);
			preparedStmt.setDouble(1, totalPrice);
			preparedStmt.setString(2, note);
			preparedStmt.setObject(3, java.sql.Date.valueOf(creationDate));
			preparedStmt.setLong(4, warehouseId);
			preparedStmt.setLong(5, saleId);
			preparedStmt.setLong(6, leaseId);
			preparedStmt.setLong(7, purchaseId);
			
			id = preparedStmt.executeUpdate();
			long invoiceId = invoiceDb.createInvoice(order.getInvoice(), id);
			order.getInvoice().setId(invoiceId);
			
		} catch(SQLException e) {
			throw e;
		}
		return id;
	}
}
