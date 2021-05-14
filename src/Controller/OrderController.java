package Controller;

import java.sql.SQLException;

import Model.Model.Order;
import Model.Model.Sale;

public class OrderController {
	private SaleController saleCtrl = new SaleController();
	
	public Sale createOrder(Order order) throws SQLException{
		if(order instanceof Sale) {
			try {
				return this.saleCtrl.createSale((Sale)order);
			} catch (SQLException e) {
				throw e;
			}
		}
		return null;
	}
}
