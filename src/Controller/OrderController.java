package Controller;

import Model.Model.Order;
import Model.Model.Sale;

public class OrderController {
	private SaleController saleCtrl = new SaleController();
	
	public long createOrder(Order order) {
		if(order instanceof Sale) {
			return this.saleCtrl.createSale((Sale)order);
		}
		return 0;
	}
		
}
