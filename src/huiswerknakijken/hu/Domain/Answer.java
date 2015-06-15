package huiswerknakijken.hu.Domain;


public class Answer 
{
	private int ID;
	private String answer;
	private Correct correct;
	private Question question;
	
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Correct getCorrect() { // 0 = correct, 1 = not correct
		return correct;
	}
	public void setCorrect(Correct correct) {
		this.correct = correct;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public enum Correct
	{
		True(1),
		False(2),
		None(3);
		
		private final int index;   
		 
		Correct(int index) {
			this.index = index;
		}

		public int getIndex() { 
			return index; 
		}
		
		public static Correct getValue(int i){
			Correct c = Correct.None;
			for (Correct us : Correct.values()) {
				if (us.getIndex() == i) {
					c = us;
				}
			}
			return c;
		}
	}
}


