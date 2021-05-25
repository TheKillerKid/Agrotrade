package Model.DBIF;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Model.OrderLine;

public interface OrderLineIF {
	public ArrayList<OrderLine> getOrderLineList(long orderId, long warehouseId) throws SQLException;
	public long createOrderLine(OrderLine orderLine, long orderId, boolean isPurchase) throws SQLException;
}
