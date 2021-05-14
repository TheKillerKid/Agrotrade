package Model.DB;

import java.sql.SQLException;

import Model.DBIF.OrderIF;
import Model.Model.Order;

public class OrderDB implements OrderIF {
	
	public long createOrder(Order order) throws SQLException {
		String sqlCreate = "INSERT INTO Order (total_price, note, creation_date, warehouse_id, sale_id, lease_id, purchase_id) VALUES (?,?,?,?,?,?,?)";
		
		long id = 0;
		
		double totalPrice = order.getTotalPrice();
		String note = order.getNote();
		LocalDate creationDate = order.getCreationDate();
		long warehouseId = order.getWarehouse().getId();
		long saleId = order.
	}
	
}
