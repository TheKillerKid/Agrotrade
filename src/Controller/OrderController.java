package Controller;

import java.sql.SQLException;

import Model.Model.Order;
import Model.Model.Sale;

public class OrderController {
	private SaleController saleCtrl = new SaleController();
	
	public long createOrder(Order order) {
		if(order instanceof Sale) {
			try {
				return this.saleCtrl.createSale((Sale)order);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
}
