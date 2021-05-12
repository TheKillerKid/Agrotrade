package Model;

public class Person {
	
	private long id;
	private String firstName;
	private String lastName;
	private Address address;
	private String phone;
	private String email;

	public Person(long id, String firstName, String lastName, Address address, String phone, String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFristName() {
		return firstName;
	}

	public void setFristName(String fristName) {
		this.firstName = fristName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAdress() {
		return address;
	}

	public void setAdress(Address adress) {
		this.address = adress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
