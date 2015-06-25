package huiswerknakijken.hu.Domain;

import java.util.ArrayList;


public class Question 
{
	protected int ID;
	protected String name;
	protected String description;
	private ArrayList<Answer> answers = new ArrayList<Answer>();
	protected Homework homework;
	protected int number;
	private Type type;

	
	public enum Type{
		Meerkeuze(1),
		Openvraag(2),
		Openwoord(3),
		Unknown(4);
		
		private final int index;   
		 
		Type(int index) {
			this.index = index;
		}

		public int getIndex() { 
			return index; 
		}
		
		public static Type getValue(int i){
			Type ur = Type.Unknown;
			for (Type us : Type.values()) {
				if (us.getIndex() == i) {
					ur = us;
				}
			}
			return ur;
		}
	}
	
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
	
	public int getNumber(){
		return number;
	}
	
	public void setNumber(int i){
		number = i;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
}
