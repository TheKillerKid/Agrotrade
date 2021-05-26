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
import Model.Model.OrderLine;
import Model.Model.OrderPageType;
import Model.Model.OrderView;
import Model.DB.OrderDB;


public class OrderController {
	private SaleController saleCtrl = new SaleController();
	private LeaseController leaseCtrl = new LeaseController();
	private PurchaseController purchaseCtrl = new PurchaseController();
	private StockProductContoller stockProrductCtrl = new StockProductContoller();
	private CustomerController customerCtrl = new CustomerController();
	private OrderDB orderDb = new OrderDB();
	
	
	public Order createOrder(Order order) throws Exception{
		
		if(order instanceof Sale) {
			Sale sale = (Sale)order;
			order.setInvoice(new Invoice(0, LocalDate.now().plusDays(30), sale.getTotalPrice() - (sale.getTotalPrice() * sale.getCustomer().getStaticDiscount())));
		}
		
		if(order instanceof Lease) {
			Lease lease = (Lease)order;
			order.setInvoice(new Invoice(0, LocalDate.now().plusDays(30), lease.getTotalPrice() - (lease.getTotalPrice() * lease.getCustomer().getStaticDiscount())));
		}
		if(order instanceof Purchase) {
			Purchase purchase = (Purchase)order;
			order.setInvoice(new Invoice(0, LocalDate.now().plusDays(30), purchase.getTotalPrice()));
		}
		
		try {
			if(order instanceof Sale) {
				return this.saleCtrl.createSale((Sale)order);
			}
			if(order instanceof Lease) {
				return this.leaseCtrl.createLease((Lease)order);
			}
			if(order instanceof Purchase) {
				return this.purchaseCtrl.createPurchase((Purchase)order);
			}
		} catch (Exception e) {
			throw e;
		}
		
		return null;
	}
	
	public Order getOrder(OrderPageType type, long id) throws SQLException {
		try {
			if(type == OrderPageType.SALE) {
				return this.saleCtrl.getSale(id);
			}
			if(type == OrderPageType.LEASE) {
				return this.leaseCtrl.getLease(id);
			}
			if(type == OrderPageType.PURCHASE) {
				return this.purchaseCtrl.getPurchase(id);
			}
			return null;
		} catch(SQLException e) {
			throw e;
		}
	}
	
	public ArrayList<OrderView> getOrders(OrderPageType type) throws SQLException {
		ArrayList<OrderView> orders = new ArrayList<OrderView>(); 
		try {
			orders = orderDb.getOrderList(type);
		} catch(SQLException e) {
			throw e;
		}
		
		return orders; 
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
	
	public void sendSale(long id) throws Exception {
		try {
			this.saleCtrl.sendSale(id);
		} catch (Exception e) {
			throw e;
		}
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
