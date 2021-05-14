package Model.Model;

import java.time.LocalDate;

public class Invoice {
	private long id;
	private LocalDate paymentDate;
	private double totalPrice;
	
	public Invoice(long id, LocalDate paymentDate, double totalPrice) {
		this.id = id;
		this.paymentDate = paymentDate;
		this.totalPrice = totalPrice;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
