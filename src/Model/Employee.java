package Model;

public class Employee extends Person {
	private long id;
	private String firstName;
	private String lastName;
	private Address address;
	private String phone;
	private String email;

	public Employee(long id, String firstName, String lastName, Address address, String phone, String email) {
		super(id, firstName, lastName, address, phone, email);
	}

}
