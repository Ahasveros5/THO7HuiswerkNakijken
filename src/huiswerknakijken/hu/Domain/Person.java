package huiswerknakijken.hu.Domain;

import huiswerknakijken.hu.Domain.Answer.Correct;

public class Person implements Comparable {
	
	public void print(){
		System.out.println("Printing out Person:\n" + 
		firstName + " " + lastName + "\n" + 
		eMail + "\n" +
		password + "\n" +
		ID + "\n" +
		role.toString());
	}
	
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
		
		public static UserRole getValue(int i){
			UserRole ur = UserRole.Unknown;
			for (UserRole us : UserRole.values()) {
				if (us.getIndex() == i) {
					ur = us;
				}
			}
			return ur;
		}
	}

	protected String firstName;
	protected String lastName;
	protected String eMail;
	protected String password;
	protected int ID = -1; 
	protected UserRole role; //1 = student // 2 = teacher
	
	
	public Student toStudent(){
		Student s = new Student();
		s.eMail = eMail;
		s.firstName = firstName;
		s.lastName = lastName;
		s.password = password;
		s.ID = ID;
		s.role = role;
		return s;
	}
	
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
	
	public Person(String firstName, String lastName, String email, int id, String password, UserRole role){
		this.firstName = firstName;
		this.lastName = lastName;
		this.eMail = email;
		this.ID = id;
		this.password = password;
		this.role = role;
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
	
	public String toString(){
		return firstName +" "+lastName;
	}

	@Override
	public int compareTo(Object compTo) {
		int id = ((Person)compTo).getID();
		return ID-id;
	}
	
	
}
