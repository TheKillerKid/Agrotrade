package Controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import Model.Model.Customer;
import Model.Model.Invoice;
import Model.Model.Lease;
import Model.Model.Purchase;
import Model.Model.Order;
import Model.Model.Sale;
import Model.Model.StockProduct;
import Model.Model.Supplier;
import Model.Model.OrderLine;
import Model.Model.OrderPageType;

public class OrderController {
	private SaleController saleCtrl = new SaleController();
	private LeaseController leaseCtrl = new LeaseController();
	private StockProductContoller stockProrductCtrl = new StockProductContoller();
	private CustomerController customerCtrl = new CustomerController();
	
	
	public Order createOrder(Order order) throws SQLException{
		
		if(order instanceof Sale) {
			Sale sale = (Sale)order;
			order.setInvoice(new Invoice(0, LocalDate.now().plusDays(30), sale.getTotalPrice() - (sale.getTotalPrice() * sale.getCustomer().getStaticDiscount())));
		}
		
		if(order instanceof Lease) {
			Lease lease = (Lease)order;
			order.setInvoice(new Invoice(0, LocalDate.now().plusDays(30), lease.getTotalPrice() - (lease.getTotalPrice() * lease.getCustomer().getStaticDiscount())));
		}
		/*if(order instanceof Purchase) {Sale sale = (Sale)order;
			order.setInvoice(new Invoice(0, LocalDate.now().plusDays(30), sale.getTotalPrice() * sale.getCustomer().getStaticDiscount()));
		}*/
		
		try {
			if(order instanceof Sale) {
				return this.saleCtrl.createSale((Sale)order);
			}
			if(order instanceof Lease) {
				return this.leaseCtrl.createLease((Lease)order);
			}
		} catch (SQLException e) {
			throw e;
		}
		
		return null;
	}
	
	public double calculateTotalPrice(ArrayList<OrderLine> ols, OrderPageType type) {
		double totalPrice = 0;
		for (OrderLine orderLine : ols) {
			if(type == OrderPageType.SALE) {
				totalPrice += orderLine.getAmount() * orderLine.getStockProduct().getProduct().getSalePrice().getAmount();
			}
			if(type == OrderPageType.LEASE) {
				totalPrice += orderLine.getAmount() * orderLine.getStockProduct().getProduct().getLeasePrice().getAmount();
			}
			if(type == OrderPageType.PURCHASE) {
				totalPrice += orderLine.getAmount() * orderLine.getStockProduct().getProduct().getSalePrice().getAmount();
			}
		}	
		
		return totalPrice;
		
	}
	
	public ArrayList<Customer> getCustomers() throws SQLException{
		try {
			return this.customerCtrl.getCustomers();
		} catch (SQLException e) {
			throw e;
		}
	} 
	
	public ArrayList<StockProduct> getStockProducts(long warehouseId) throws SQLException {
		try {
			return this.stockProrductCtrl.getStockProducts(warehouseId);
		} catch (SQLException e) {
			throw e;
		}

	}
	
	public Customer getCustomer(String cvrNo) throws SQLException {
		try {
			return customerCtrl.getCustomer(cvrNo);
		} catch (SQLException e) {
			throw e;
		}
	}
		
}
