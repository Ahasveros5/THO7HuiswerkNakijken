package hu.huiswerknakijken.Domain;

import java.util.ArrayList;

public class Homework {

	private Grade grade;
	private ArrayList<Question> question;
	private Course course;
	private Teacher teacher;
	private ArrayList<Student> students;
	
	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public ArrayList<Question> getQuestion() {
		return question;
	}

	public void setQuestion(ArrayList<Question> question) {
		this.question = question;
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

	
}
