package Model.DBIF;

import java.sql.SQLException;

import Model.Model.OrderLine;

public interface OrderLineIF {
	public long createOrderLine(OrderLine orderLine) throws SQLException;
}
