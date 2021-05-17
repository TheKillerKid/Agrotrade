package Model.Model;

public class OrderLine {

	private long id;
	private int requestedAmount;
	private int amount;
	private StockProduct stockProduct;
	private Order order;
	
	public OrderLine(long id, int requestedAmount, int amount, StockProduct stockProduct, Order order) {	
		this.id = id;
		this.requestedAmount = requestedAmount;
		this.amount = amount;
		this.stockProduct = stockProduct;
		this.order = order;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getRequestedAmount() {
		return requestedAmount;
	}
	public void setRequestedAmount(int requestedAmount) {
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
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
}
