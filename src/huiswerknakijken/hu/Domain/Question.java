package huiswerknakijken.hu.Domain;

import java.util.ArrayList;


public class Question 
{
	
	protected String description;
	private ArrayList<Answer> answers = new ArrayList<Answer>();

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
	
}
