package huiswerknakijken.hu.Domain;

public class Student extends Person {

	private int studentID;
	private Class mainClass;

	
	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public Class getMainClass() {
		return mainClass;
	}

	public void setMainClass(Class mainClass) {
		this.mainClass = mainClass;
	}
	
}
