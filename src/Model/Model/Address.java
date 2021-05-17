package Model.Model;

public class Address {

	private long id;
	private String street;
	private String streetNo;
	private String city;
	private String postalCode;
	private String country;
	
	public Address(long id, String street, String streetNo, String city, String postalCode, String country) {
		this.id = id;
		this.street = street;
		this.streetNo = streetNo;
		this.city = city;
		this.postalCode = postalCode;
		this.country = country;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getStreetNo() {
		return streetNo;
	}
	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
