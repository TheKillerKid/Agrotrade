package Model.Model;

public class Customer extends Person {
	private long cvrNo;
	private int staticDiscount;
	
	public Customer(long id, String firstName, String lastName, Address address, String phone, String email, long cvrNo,
			int staticDiscount) {
		super(id, firstName, lastName, address, phone, email);
		this.cvrNo = cvrNo;
		this.staticDiscount = staticDiscount;
	}

	public long getCvrNo() {
		return cvrNo;
	}

	public void setCvrNo(long cvrNo) {
		this.cvrNo = cvrNo;
	}

	public int getStaticDiscount() {
		return staticDiscount;
	}

	public void setStaticDiscount(int staticDiscount) {
		this.staticDiscount = staticDiscount;
	}
}
