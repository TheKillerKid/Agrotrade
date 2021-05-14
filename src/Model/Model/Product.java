package Model.Model;

public class Product {
	
	private long id;
	private long barcode;
	private String name;
	private Category category;
	private Price purchasePrice;
	private Price salePrice;
	private Price leasePrice;
	private Unit unit;
	private int discount;
	private Supplier supplier;
	
	public Product(long id, long barcode, String name, Category category, Price purchasePrice, Price salePrice, Price leasePrice, Unit unit, int discount, Supplier supplier) {				//need of adding prices
		this.id = id;
		this.barcode = barcode;
		this.name = name;
		this.category = category;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.leasePrice = leasePrice;
		this.unit = unit;
		this.discount = discount;
	}
	
	public Price getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Price purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Price getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Price salePrice) {
		this.salePrice = salePrice;
	}

	public Price getLeasePrice() {
		return leasePrice;
	}

	public void setLeasePrice(Price leasePrice) {
		this.leasePrice = leasePrice;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
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
