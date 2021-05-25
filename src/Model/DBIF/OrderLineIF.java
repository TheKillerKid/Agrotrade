package Model.DBIF;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Model.OrderLine;

public interface OrderLineIF {
	public long createOrderLine(OrderLine orderLine, long orderId) throws SQLException;
	public ArrayList<OrderLine> getOrderLineList(long orderId, long warehouseId) throws SQLException;
}
