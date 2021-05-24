package Model.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Lease extends Order {
	private long id;
	private LocalDate borrowDate;
	private LocalDate expectedReturnDate;
	private LocalDate realReturnDate;
	private Customer customer;
	
	public Lease(long orderId, double totalPrice, String note, LocalDate creationDate, Warehouse warehouse,
			ArrayList<OrderLine> orderLines, Invoice invoice, long id, LocalDate borrowDate, LocalDate expectedReturnDate, LocalDate realReturnDate, Customer customer) {
		super(orderId, totalPrice, note, creationDate, warehouse, orderLines, invoice);
		this.id = id;
		this.borrowDate = borrowDate;
		this.expectedReturnDate = expectedReturnDate;
		this.realReturnDate = realReturnDate;
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
	
	public LocalDate getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(LocalDate borrowDate) {
		this.borrowDate = borrowDate;
	}
	
	public LocalDate getExpectedReturnDate() {
		return expectedReturnDate;
	}

	public void setExpectedReturnDate(LocalDate expectedReturnDate) {
		this.expectedReturnDate = expectedReturnDate;
	}
	
	public LocalDate getRealReturnDate() {
		return realReturnDate;
	}

	public void setRealReturnDate(LocalDate realReturnDate) {
		this.realReturnDate = realReturnDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
