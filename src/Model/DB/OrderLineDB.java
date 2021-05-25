package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.DBIF.OrderLineIF;
import Model.Model.MessagesEnum;
import Model.Model.OrderLine;

public class OrderLineDB implements OrderLineIF {
	private StockProductDB stockProductDb = new StockProductDB();
	
	public long createOrderLine(OrderLine orderLine, long orderId, boolean isPurchase) throws SQLException {
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
            	if(!isPurchase) {
            		stockProductDb.sellOrLeaseStockProduct(orderLine.getStockProduct().getId(), orderLine.getAmount() ,orderLine.getStockProduct().getWarehouseId());
            	}
            }
            else {
                throw new SQLException(MessagesEnum.DBSAVEERROR.text);
            }
		} catch(SQLException e) {
			throw e;
		}
		return id;
	}
	
	public ArrayList<OrderLine> getOrderLineList (long orderId, long warehouseId) throws SQLException {
		ArrayList<OrderLine> res = new ArrayList<OrderLine>();  
		String sqlGetOrder = "SELECT * FROM OrderView WHERE order_id = ?";
		
		Connection con = DBConnection.getInstance().getConnection();
		
		try {
			PreparedStatement preparedStmt = con.prepareStatement(sqlGetOrder);
			
			preparedStmt.setLong(0, orderId);
			
			ResultSet rs = preparedStmt.executeQuery();
			
			while(rs.next()) {
				OrderLine orderLine = buildOrderLine(rs);
				
				orderLine.setStockProduct(stockProductDb.getStockProduct(rs.getLong("stock_product_id"), warehouseId));
				
				res.add(orderLine);
			}
			
		} catch (SQLException e) {
			throw e;
		}
		
		
		
		return res;
	}
	
	public OrderLine buildOrderLine(ResultSet rs) throws SQLException {
		return new OrderLine(rs.getLong("id"), rs.getInt("requested_amount"), rs.getInt("amount"), null);
	}
}
