package Model.Model;

import java.time.LocalDate;

public class Invoice {
	private long id;
	private LocalDate paymentDate;
	private double amount;
	
	public Invoice(long id, LocalDate paymentDate, double amount) {
		this.id = id;
		this.paymentDate = paymentDate;
		this.amount = amount;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
