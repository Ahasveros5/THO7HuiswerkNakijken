package huiswerknakijken.hu.Domain;

import java.util.ArrayList;


/*Het Homework Object is een beetje apart.
 * Het heeft 2 verschillende soorten varianten.
 * 1. Het is een globaal huiswerk object, wat betekent dat de vragen, de naam, de leraar, de deadline, beschrijving en een lijst met alle studenten erin staan.
 * 2. Het is een Student specifiek object, wat betekent dat er niet alleen de zelfde waarde als bij het globale huiswerk object in zitten, maar ook
 * dat er een cijfer, de specifieke student van het huiswerk is, de status van het huiswerk en welke vraag die is er in staat.
 */
public class Homework {

	private String name;
	private ArrayList<Question> questions = new ArrayList<Question>();
	private Course course;
	private Person teacher;
	private Student student; //specific student
	private ArrayList<Student> students = new ArrayList<Student>(); //all students having this homework type
	private int ID;
	private String deadline;
	private String description;
	private int numberQuestions = 0;
	private Status status = Status.Nieuw;
	private int currentQuestion = 1;
	private double cijfer = -1;
	
	public enum Status{
		Nieuw(1),
		Begonnen(2),
		Af(3);
		
		private final int index;   
		 
		Status(int index) {
			this.index = index;
		}

		public int getIndex() { 
			return index; 
		}
		
		public static Status getValue(int i){
			Status ur = Status.Nieuw;
			for (Status us : Status.values()) {
				if (us.getIndex() == i) {
					ur = us;
				}
			}
			return ur;
		}
	}
	
	public Homework(String naam, String deadline, String description){
		name = naam;
		this.deadline = deadline;
		this.description = description;
		this.status = Status.Nieuw;
	}
	
	public Homework() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Person getTeacher() {
		return teacher;
	}

	public void setTeacher(Person teacher2) {
		this.teacher =  teacher2;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	public void addStudent(Student s){
		students.add(s);
	}
	
	public void addQuestion(Question q){
		numberQuestions++;
		q.setNumber(numberQuestions);
		questions.add(q);
		
	}

	public String toString(){
		return name+" "+deadline;
	}

	public int getNumberQuestions() {
		return numberQuestions;
	}

	public void setNumberQuestions(int numberQuestions) {
		this.numberQuestions = numberQuestions;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getCurrentQuestion() {
		return currentQuestion;
	}

	public void setCurrentQuestion(int currentQuestion) {
		this.currentQuestion = currentQuestion;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Person student) {
		this.student = (Student)student;
	}

	public double getCijfer() {
		return cijfer;
	}

	public void setCijfer(double cijfer) {
		this.cijfer = cijfer;
	}
	
}
