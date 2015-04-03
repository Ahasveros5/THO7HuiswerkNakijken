package huiswerknakijken.hu.Domain;

public class Person {

	protected String firstName;
	protected String lastName;
	protected String birthdate;
	protected Address address;
	protected String phoneNumber;
	protected String eMail;
	protected boolean male;
	protected String password;
	protected int ID;
	
	public  Person(){
		
	}
	
	public int getID(){
		return ID;
	}
	public void setID(int id){
		ID = id;
	}
	
	
	public String getBirthDate() {
		return birthdate;
	}
	public void setAge(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return eMail;
	}
	public void setEmail(String eMail) {
		this.eMail = eMail;
	}
	public boolean isMale() {
		return male;
	}
	public void setMale(boolean male) {
		this.male = male;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
