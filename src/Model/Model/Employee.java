package Model.Model;

public class Employee extends Person {
	
	private String password;
	private long cprNo;
	private String deparment;
	private String position;
	private Warehouse warehouse;
	

	public Employee(long id, String firstName, String lastName, Address address, String phone, String email, String password, long cprNo, String department, String position, Warehouse warehouse) {
		super(id, firstName, lastName, address, phone, email);
		this.password = password;
		this.cprNo = cprNo;
		this.deparment = department;
		this.position = position;
		this.warehouse = warehouse;
	}
		
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public long getCprNo() {
		return cprNo;
	}


	public void setCprNo(long cprNo) {
		this.cprNo = cprNo;
	}


	public String getDeparment() {
		return deparment;
	}


	public void setDeparment(String deparment) {
		this.deparment = deparment;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;	
	}
	
	public void getWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}
	
	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}
}
