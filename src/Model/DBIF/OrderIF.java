package Model.DBIF;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Model.Order;

public interface OrderIF {
	public Order createOrder(Order order) throws SQLException;
	public ArrayList<Order> getOrderList () throws SQLException;
}
