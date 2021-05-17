package Model.Model;

public class Supplier extends Person{
	
	private long cvrNo;
	private String supplierName;
	
	public Supplier(long id, String firstName, String lastName, Address address, String phone, String email, long cvrNo, String supplierName) {
		super(id, firstName, lastName, address, phone, email);
		this.cvrNo = cvrNo;
		this.supplierName = supplierName;
	}
	
	public Supplier(long id) {
		super(id, null, null, null, null, null);
	}
	
	public long getCvrNo() {
		return cvrNo;
	}

	public void setCvrNo(long cvrNo) {
		this.cvrNo = cvrNo;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
}
