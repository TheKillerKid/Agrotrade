package Model;

import java.time.LocalDate;

public class OrderLine {

	private long id;
	private LocalDate requestedAmount;
	private int amount;
	private StockProduct stockProduct;
	
	public OrderLine(long id, LocalDate requestedAmount, int amount, StockProduct stockProduct) {	
		this.id = id;
		this.requestedAmount = requestedAmount;
		this.amount = amount;
		this.stockProduct = stockProduct;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LocalDate getRequestedAmount() {
		return requestedAmount;
	}
	public void setRequestedAmount(LocalDate requestedAmount) {
		this.requestedAmount = requestedAmount;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public StockProduct getStockProduct() {
		return stockProduct;
	}
	public void setStockProduct(StockProduct stockProduct) {
		this.stockProduct = stockProduct;
	}
}
