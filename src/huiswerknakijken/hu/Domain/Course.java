package huiswerknakijken.hu.Domain;

import java.util.ArrayList;

public class Course 
{
	private int ID;
	private String name;
	private Homework homework;
	private ArrayList<Person> teachers = new ArrayList<Person>();
	private ArrayList<Person> students = new ArrayList<Person>();
	private ArrayList<Goal> goals = new ArrayList<Goal>();
	
	public String getName() {
		return name;
	}
	public void setName(String naam) {
		this.name = naam;
	}
	public Homework getHomework() {
		return homework;
	}
	public void setHomework(Homework homework) {
		this.homework = homework;
	}
	public ArrayList<Person> getStudents() {
		return students;
	}
	public void setStudents(ArrayList<Person> arrayList) {
		this.students = arrayList;
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
	public ArrayList<Person> getTeachers() {
		return teachers;
	}
	public void setTeachers(ArrayList<Person> teachers) {
		this.teachers = teachers;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
}
