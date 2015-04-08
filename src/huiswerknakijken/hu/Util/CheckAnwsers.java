package huiswerknakijken.hu.Util;

import huiswerknakijken.hu.Domain.Answer;
import huiswerknakijken.hu.Domain.Answer.Correct;
import huiswerknakijken.hu.Domain.Question;

import java.util.ArrayList;

public class CheckAnwsers {
	
	private ArrayList<Answer> getAnswers(Question q){
		//AnwserDAO aDAO = new AnwserDAO();
		//return aDAO.retrieveByQuestion(q);
		return null;
	}
	
	public Answer getCorrectAnswer(Question q){
		//AnwserDAO aDAO = new AnwserDAO();
		//return aDAO.retrieveCorrectByQuestion(q);
		return null;
	}
	
	public boolean HasGivenCorrectAnswer(Question q, Answer a){
		if(a.getCorrect() == Correct.True){ //meerkeuze check
			return true;
		}
//		ArrayList<Answer> answers = getAnswers(q);
//		if (answers.contains(a))
		return false;
	}

}
