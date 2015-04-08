package huiswerknakijken.hu.Domain;

import java.util.ArrayList;

//hier moeten waarschijnlijk ook nog Courses aan gekoppeld worden wat dus de vakken zijn.
//zodat je per vak huiswerk kan hebben enzo...

public class Class {

	private ArrayList<Student> students = new ArrayList<Student>();
	private ArrayList<Teacher> teachers = new ArrayList<Teacher>();
	private Course course;
	private int classID = -1;
	private String name;
	
	public Class(){
		
	}
	
	public Class(String name){
		this.name = name;
	}
	
	public ArrayList<Student> getStudents() {
		return students;
	}
	public void setStudents(ArrayList<? extends Person> students) {
		this.students = (ArrayList<Student>) students;
	}
	public ArrayList<Teacher> getTeacher() {
		return teachers;
	}
	public void setTeacher(ArrayList<Teacher> teachers) {
		this.teachers = teachers;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public int getClassID() {
		return classID;
	}
	public void setClassID(int classID) {
		this.classID = classID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
