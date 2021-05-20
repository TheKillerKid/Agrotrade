package Controller;

import java.rmi.dgc.Lease;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import Model.Model.Invoice;
import Model.Model.Order;
import Model.Model.Sale;
import Model.Model.StockProduct;
import Model.Model.OrderLine;
import Model.Model.Product;
import Controller.StockProductContoller;

public class OrderController {
	private SaleController saleCtrl = new SaleController();
	private StockProductContoller stockProrductCtrl = new StockProductContoller();
	
	
	public Order createOrder(Order order) throws SQLException{
		
		order.setTotalPrice(calculateTotalPrice(order));
		
		order.setInvoice(new Invoice(0, LocalDate.now().plusDays(30), calculateTotalPrice(order)));
		
		try {
			if(order instanceof Sale) {
				return this.saleCtrl.createSale((Sale)order);
			}
		} catch (SQLException e) {
			throw e;
		}
		
		return null;
	}
	
		//calculates total price for invoice
	private double calculateTotalPrice(Order order) {
		
		double totalPrice = 0;
		for (OrderLine orderLine : order.getOrderLines()) {
			if(order instanceof Sale) {
				totalPrice += orderLine.getAmount() * orderLine.getStockProduct().getProduct().getSalePrice().getAmount();
			}
			
			/*
			 	COMPILATON ERROR WITH THIS ONE, maybe ECLIPSE BUG
				else if(order instanceof Lease) {
				totalPrice += orderLine.getAmount() * orderLine.getStockProduct().getProduct().getLeasePrice().getAmount();
			 	}
			 */
			
		}	
		
		return totalPrice;
		
	}
	
	public ArrayList<StockProduct> getStockProducts(long warehouseId) throws SQLException {
		try {
			return this.stockProrductCtrl.getStockProducts(warehouseId);
		
		} catch (SQLException e) {
			throw e;
		}

	}
		
}
