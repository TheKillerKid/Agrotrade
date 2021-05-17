package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Model.DBIF.OrderLineIF;
import Model.Model.OrderLine;

public class OrderLineDB implements OrderLineIF {

	public long createOrderLine(OrderLine orderLine) throws SQLException {
		String sqlCreate = "INSERT INTO OrderLine (requested_amount, amount, stock_product_id, order_id) VALUES (?,?,?,?)";
		
		long id = 0;
		int requestedAmount = orderLine.getRequestedAmount();
		int amount = orderLine.getAmount();
		long stockProductId = orderLine.getStockProduct().getId();
		long orderId = orderLine.getOrder().getOrderId();
		
		try(Connection con = DBConnection.getInstance().getConnection()) {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCreate);
			preparedStmt.setInt(1,requestedAmount);
			preparedStmt.setInt(2, amount);
			preparedStmt.setLong(3, stockProductId);
			preparedStmt.setLong(4, orderId);
			
			id = preparedStmt.executeUpdate();
		} catch(SQLException e) {
			throw e;
		}
		return id;
	}
}
