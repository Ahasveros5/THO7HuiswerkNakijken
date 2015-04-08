package huiswerknakijken.hu.Domain;

public class Person {
	
	public enum UserRole{
		Student(1),
		Teacher(2),
		Unknown(3);
		
		private final int index;   
		 
		UserRole(int index) {
			this.index = index;
		}

		public int getIndex() { 
			return index; 
		}
	}

	protected String firstName;
	protected String lastName;
	protected String eMail;
	protected String password;
	protected int ID; 
	protected UserRole role; //1 = student // 2 = teacher
	
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public  Person(){
		
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
