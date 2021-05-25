package Model.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Purchase extends Order {
	private long id;
	private LocalDate deliveryDate;
	
	public Purchase(long orderId, double totalPrice, String note, LocalDate creationDate, Warehouse warehouse,
			ArrayList<OrderLine> orderLines, Invoice invoice, long id, LocalDate deliveryDate) {
		super(orderId, totalPrice, note, creationDate, warehouse, orderLines, invoice);
		this.id = id;
		this.deliveryDate = deliveryDate;
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
	
	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

}
