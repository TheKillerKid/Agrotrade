package Model.Model;

public class ProductView {
	private long stockProductId;
	private long barcode;
	private String name;
	private String category;
	private int amount;
	private int minStock;
	private int maxStock;
	private String supplier;
	private String location;
	
	public ProductView(long stockProductId, long barcode, String name, String category, int amount, int minStock,
			int maxStock, String supplier, String location) {
		this.stockProductId = stockProductId;
		this.barcode = barcode;
		this.name = name;
		this.category = category;
		this.amount = amount;
		this.minStock = minStock;
		this.maxStock = maxStock;
		this.supplier = supplier;
		this.location = location;
	}
	
	public long getStockProductId() {
		return stockProductId;
	}
	public void setStockProductId(long stockProductId) {
		this.stockProductId = stockProductId;
	}
	public long getBarcode() {
		return barcode;
	}
	public void setBarcode(long barcode) {
		this.barcode = barcode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
