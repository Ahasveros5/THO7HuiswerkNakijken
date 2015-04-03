package hu.huiswerknakijken.Domain;

import java.util.ArrayList;

public class Course 
{
	private Homework homework;
	private ArrayList<Student> students = new ArrayList<Student>();
	private ArrayList<Goal> goals = new ArrayList<Goal>();
	
	public Homework getHomework() {
		return homework;
	}
	public void setHomework(Homework homework) {
		this.homework = homework;
	}
	public ArrayList<Student> getStudents() {
		return students;
	}
	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}
	public void addStudent(Student s){
		students.add(s);
	}
	public ArrayList<Goal> getGoals() {
		return goals;
	}
	public void setGoals(ArrayList<Goal> goals) {
		this.goals = goals;
	}
	
}
