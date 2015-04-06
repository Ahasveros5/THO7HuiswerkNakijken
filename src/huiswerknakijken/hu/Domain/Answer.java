package huiswerknakijken.hu.Domain;

public class Answer 
{
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
	public enum Correct
	{
		True,
		False,
		None
	}
}


