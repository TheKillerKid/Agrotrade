package Model.Model;

public class Product {
	
	private long id;
	private long barcode;
	private String name;
	private String category;
	//private Price purchasePrice;
	//private Price salePrice;
	//private Price leasePrice;
	private String unit;
	private int discount;
	private Supplier supplier;
	
	public Product(long id, long barcode, String name, String category, String unit, int discount, Supplier supplier) {				//need of adding prices
		this.id = id;
		this.barcode = barcode;
		this.name = name;
		this.category = category;
		this.unit = unit;
		this.discount = discount;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public Supplier getSupplier() {
		return supplier;
	}
}
