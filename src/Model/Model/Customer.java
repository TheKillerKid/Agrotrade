package Model.Model;

public class Customer extends Person {
	private String cvrNo;
	private double staticDiscount;
	
	public Customer(long id, String firstName, String lastName, Address address, String phone, String email, String cvrNo,
			double staticDiscount) {
		super(id, firstName, lastName, address, phone, email);
		this.cvrNo = cvrNo;
		this.staticDiscount = staticDiscount;
	}

	public String getCvrNo() {
		return cvrNo;
	}

	public void setCvrNo(String cvrNo) {
		this.cvrNo = cvrNo;
	}

	public double getStaticDiscount() {
		return staticDiscount;
	}

	public void setStaticDiscount(double staticDiscount) {
		this.staticDiscount = staticDiscount;
	}
}
