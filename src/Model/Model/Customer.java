package Model.Model;

public class Customer extends Person {
	private String cvrNo;
	private int staticDiscount;
	
	public Customer(long id, String firstName, String lastName, Address address, String phone, String email, String cvrNo,
			int staticDiscount) {
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

	public int getStaticDiscount() {
		return staticDiscount;
	}

	public void setStaticDiscount(int staticDiscount) {
		this.staticDiscount = staticDiscount;
	}
}
