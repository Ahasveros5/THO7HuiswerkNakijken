package huiswerknakijken.hu.Domain;

public class Student extends Person {

	private Klass mainClass;


	public Klass getMainClass() {
		return mainClass;
	}

	public void setMainClass(Klass mainClass) {
		this.mainClass = mainClass;
	}
	
}
