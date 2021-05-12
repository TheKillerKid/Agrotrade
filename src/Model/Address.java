package Model;

public class Address {

	private long id;
	private String street;
	private int streetNo;
	private String city;
	private String country;
	
	public Address(long id, String street, int streetNo, String city, String country) {
		this.id = id;
		this.street = street;
		this.streetNo = streetNo;
		this.city = city;
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
	public int getStreetNo() {
		return streetNo;
	}
	public void setStreetNo(int streetNo) {
		this.streetNo = streetNo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
