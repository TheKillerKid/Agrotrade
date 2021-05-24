package Model.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Order {
	protected long orderId;
	protected double totalPrice;
	protected String note;
	protected LocalDate creationDate;
	protected Warehouse warehouse;
	protected ArrayList<OrderLine> orderLines;
	protected Invoice invoice;
	
	
	public Order(long orderId, double totalPrice, String note, LocalDate creationDate,
			Warehouse warehouse, ArrayList<OrderLine> orderLines, Invoice invoice) {
		this.orderId = orderId;
		this.totalPrice = totalPrice;
		this.note = note;
		this.creationDate = creationDate;
		this.warehouse = warehouse;
		this.orderLines = orderLines;
		this.invoice = invoice;
	}
	
	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public ArrayList<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(ArrayList<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	
}
