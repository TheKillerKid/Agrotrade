package Model.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import Model.DBIF.SaleIF;
import Model.Model.OrderLine;
import Model.Model.Sale;

public class SaleDB implements SaleIF {

	private OrderDB orderDb = new OrderDB();
	private OrderLineDB ordeLineDb = new OrderLineDB();
	
	@Override
	public Sale createSale(Sale sale) throws SQLException {
		String sqlCreate = "INSERT INTO Sale (shipping_date, delivery_date, customer_id) VALUES (?,?,?)";
		
		long id = 0;
		LocalDate shippingDate = null;
		LocalDate deliveryDate = null;
		long customerId = sale.getCustomer().getId();
		
		try (Connection con = DBConnection.getInstance().getConnection()) {
			PreparedStatement preparedStmt = con.prepareStatement(sqlCreate);
			preparedStmt.setObject(1, java.sql.Date.valueOf(shippingDate));
			preparedStmt.setObject(2, java.sql.Date.valueOf(deliveryDate));
			preparedStmt.setLong(3, customerId);
			long saleId= preparedStmt.executeUpdate();
			sale.setId(saleId); 
			sale.setOrderId(orderDb.createOrder(sale));
			
			ArrayList<OrderLine> ols = new ArrayList<OrderLine>(); 
			
			for (OrderLine ol : sale.getOrderLines()) {
				ol.setId(ordeLineDb.createOrderLine(ol));
				ols.add(ol);
			}
			sale.setOrderLines(ols);
		} catch (SQLException e) {
			throw e;
		}
		return sale;
	}
}
