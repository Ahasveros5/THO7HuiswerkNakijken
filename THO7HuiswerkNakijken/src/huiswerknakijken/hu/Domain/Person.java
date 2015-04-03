package huiswerknakijken.hu.Domain;

public class Person {

	protected String firstName;
	protected String lastName;
	protected String eMail;
	protected String password;
	protected int ID = -1; //null value
	
	public  Person(){
		
	}
	
	public int getID(){
		return ID;
	}
	public void setID(int id){
		ID = id;
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
	public String getEmail() {
		return eMail;
	}
	public void setEmail(String eMail) {
		this.eMail = eMail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
