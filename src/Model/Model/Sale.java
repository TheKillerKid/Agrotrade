package Model.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Sale extends Order {
	private long id;
	private LocalDate shippingDate;
	private LocalDate deliveryDate;
	private Customer customer;

	public Sale(long orderId, double totalPrice, String note, LocalDate creationDate, Warehouse warehouse,
			ArrayList<OrderLine> orderLines, Invoice invoice, long id, LocalDate shippingDate, LocalDate deliveryDate,
			Customer customer) {
		super(orderId, totalPrice, note, creationDate, warehouse, orderLines, invoice);
		this.id = id;
		this.shippingDate = shippingDate;
		this.deliveryDate = deliveryDate;
		this.customer = customer;
	}

	public void setOrder(Order order) {
		this.setOrderId(order.getOrderId());
		this.setTotalPrice(order.getTotalPrice());
		this.setNote(order.getNote());
		this.setCreationDate(order.getCreationDate());
		this.setWarehouse(order.getWarehouse());
		this.setOrderLines(order.getOrderLines());
		this.setInvoice(order.getInvoice());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(LocalDate shippingDate) {
		this.shippingDate = shippingDate;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
