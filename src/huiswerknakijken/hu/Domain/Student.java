package huiswerknakijken.hu.Domain;

import huiswerknakijken.hu.Domain.Person.UserRole;

public class Student extends Person {

	private Klass mainClass;


	public Student(String firstName, String lastName, String email,
			int personid, String password, UserRole role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.eMail = email;
		this.ID = personid;
		this.password = password;
		this.role = role;
	}

	public Student() {
		// TODO Auto-generated constructor stub
	}

	public Klass getMainClass() {
		return mainClass;
	}

	public void setMainClass(Klass mainClass) {
		this.mainClass = mainClass;
	}
	
}
