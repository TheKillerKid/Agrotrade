package Model.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Lease extends Order {
	private long id;
	private LocalDate shippingDate;
	private LocalDate deliveryDate;
	private Customer customer;
	
	public Lease(long orderId, double totalPrice, String note, LocalDate creationDate, Warehouse warehouse,
			ArrayList<OrderLine> orderLines, Invoice invoice) {
		super(orderId, totalPrice, note, creationDate, warehouse, orderLines, invoice);
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
