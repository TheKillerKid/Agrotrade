package Model.DBIF;

import java.sql.SQLException;

import Model.Model.Order;

public interface OrderIF {
	public Order createOrder(Order order) throws SQLException;
	
}
