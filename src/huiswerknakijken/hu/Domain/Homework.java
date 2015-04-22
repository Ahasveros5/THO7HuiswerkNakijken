package huiswerknakijken.hu.Domain;

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

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
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
		questions.add(q);
	}

	public String toString(){
		return name+" "+deadline;
	}
	
}
