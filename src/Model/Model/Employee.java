package Model.Model;

import java.time.LocalDate;

public class Employee extends Person {
	
	private String password;
	private long cprNo;
	private String department;
	private String position;
	private Warehouse warehouse;
	private LocalDate dateOfBirth;
	

	public Employee(long id, String firstName, String lastName, LocalDate dateOfBirth, Address address, String phone, String email, String password, long cprNo, String department, String position, Warehouse warehouse) {
		super(id, firstName, lastName, address, phone, email);
		this.dateOfBirth = dateOfBirth;
		this.password = password;
		this.cprNo = cprNo;
		this.department = department;
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


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;	
	}
	
	public Warehouse getWarehouse() {
		return warehouse;
	}
	
	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}
	
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}
