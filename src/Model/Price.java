package Model;

import java.time.LocalDate;

public class Price {

	private long id;
	private double amount;
	private LocalDate startDate;
	private String priceType;
	
	public Price(long id, double amount, LocalDate startDate, String priceType) {
		this.id = id;
		this.amount = amount;
		this.startDate = startDate;
		this.priceType = priceType;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public String getPriceType() {
		return priceType;
	}
	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}
}
