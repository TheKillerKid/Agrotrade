package Model.Model;

public class StockProduct {
	
	private long id;
	private int amount;
	private int minStock;
	private int maxStock;
	private Product product;
	private long warehouseID;
	
	public StockProduct(long id, int amount, int minStock, int maxStock, Product product, long warehouseID) {
		this.id = id;
		this.amount = amount;
		this.minStock = minStock;
		this.maxStock = maxStock;
		this.product = product;
		this.warehouseID = warehouseID;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getMinStock() {
		return minStock;
	}

	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}

	public int getMaxStock() {
		return maxStock;
	}

	public void setMaxStock(int maxStock) {
		this.maxStock = maxStock;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public long getWarehouseID() {
		return warehouseID;
	}
	
	public void setWarehouseID(long warehouseID) {
		this.warehouseID = warehouseID;
	}
}