package Model.DBIF;

import java.sql.SQLException;

import Model.Model.Order;

public interface OrderIF {
	public long createOrder(Order order) throws SQLException;
	
}
