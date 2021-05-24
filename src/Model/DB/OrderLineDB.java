package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.DBIF.OrderLineIF;
import Model.Model.MessagesEnum;
import Model.Model.OrderLine;

public class OrderLineDB implements OrderLineIF {
	private StockProductDB stockProductDb = new StockProductDB();
	
	public long createOrderLine(OrderLine orderLine, long orderId) throws SQLException {
		String sqlCreate = "INSERT INTO OrderLine (requested_amount, amount, stock_product_id, order_id) VALUES (?,?,?,?)";
		
		long id = 0;
		int requestedAmount = orderLine.getRequestedAmount();
		int amount = orderLine.getAmount();
		long stockProductId = orderLine.getStockProduct().getId();
		
		Connection con = DBConnection.getInstance().getConnection();

    try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCreate, Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setInt(1,requestedAmount);
			preparedStmt.setInt(2, amount);
			preparedStmt.setLong(3, stockProductId);
			preparedStmt.setLong(4, orderId);
			
			preparedStmt.executeUpdate();
			
			ResultSet rs = preparedStmt.getGeneratedKeys();
            if (rs.next()) {
            	id = rs.getLong(1);
            	stockProductDb.sellStockProduct(orderLine.getStockProduct().getId(), orderLine.getAmount() ,orderLine.getStockProduct().getWarehouseId());
            }
            else {
                throw new SQLException(MessagesEnum.DBSAVEERROR.text);
            }
		} catch(SQLException e) {
			throw e;
		}
		return id;
	}
}
