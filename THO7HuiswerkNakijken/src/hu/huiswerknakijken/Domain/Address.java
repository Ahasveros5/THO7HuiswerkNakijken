package hu.huiswerknakijken.Domain;

public class Address {
	private String Zip;
	private String Street;
	private String City;
	private int Number;
	
	public String getZip() {
		return Zip;
	}
	
	public void setZip(String zip) {
		Zip = zip;
	}
	
	public String getStreet() {
		return Street;
	}
	
	public void setStreet(String street) {
		Street = street;
	}
	
	public String getCity() {
		return City;
	}
	
	public void setCity(String city) {
		City = city;
	}
	
	public int getNumber() {
		return Number;
	}
	
	public void setNumber(int number) {
		Number = number;
	}
}
