package Model.Model;

import java.time.LocalDate;

public class Employee extends Person {
	
	private String password;
	private String cprNo;
	private DepartmentType department;
	private PositionType position;
	private Warehouse warehouse;
	private LocalDate dateOfBirth;
	

	public Employee(long id, String firstName, String lastName, LocalDate dateOfBirth, Address address, String phone, String email, String password, String cprNo, DepartmentType department, PositionType position, Warehouse warehouse) {
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


	public String getCprNo() {
		return cprNo;
	}


	public void setCprNo(String cprNo) {
		this.cprNo = cprNo;
	}


	public DepartmentType getDepartment() {
		return department;
	}


	public void setDepartment(DepartmentType department) {
		this.department = department;
	}


	public PositionType getPosition() {
		return position;
	}


	public void setPosition(PositionType position) {
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
