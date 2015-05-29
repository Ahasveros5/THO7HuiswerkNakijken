package huiswerknakijken.hu.Domain;

import huiswerknakijken.hu.Domain.Person.UserRole;

import java.util.ArrayList;

public class Homework {

	private String name;
	private Grade grade;
	private ArrayList<Question> questions = new ArrayList<Question>();
	private Course course;
	private Teacher teacher;
	private ArrayList<Student> students = new ArrayList<Student>();
	private int ID;
	private String deadline;
	private String description;
	private int numberQuestions = 0;
	private Status status = Status.Nieuw;
	private int currentQuestion = 1;
	
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

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Person teacher2) {
		this.teacher = (Teacher) teacher2;
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
	
}
