package huiswerknakijken.hu.Domain;

import java.util.ArrayList;


public class Question 
{
	protected int ID;
	protected String name;
	protected String description;
	private ArrayList<Answer> answers = new ArrayList<Answer>();
	protected Homework homework;

	public Question(){
		
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ArrayList<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(ArrayList<Answer> answers) {
		this.answers = answers;
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

	public Homework getHomework() {
		return homework;
	}

	public void setHomework(Homework homework) {
		this.homework = homework;
	}
	
}
