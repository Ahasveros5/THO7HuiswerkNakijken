package huiswerknakijken.hu.Util;

import huiswerknakijken.hu.DAO.AnswerDAO;
import huiswerknakijken.hu.Domain.Answer;
import huiswerknakijken.hu.Domain.Answer.Correct;
import huiswerknakijken.hu.Domain.Question;

import java.util.ArrayList;

public final class CheckAnswers {
	
	private static ArrayList<Answer> getAnswers(Question q){
		AnswerDAO aDAO = new AnswerDAO();
		return aDAO.retrieveAllByQuestion(q.getID(),1);
	}
	
	public static Answer getCorrectAnswer(Question q){
		AnswerDAO aDAO = new AnswerDAO();
		return aDAO.retrieveCorrectByQuestion(q.getID(),1);
	}
	
	public static boolean HasGivenCorrectAnswer(Question q, Answer a){
		if(a.getCorrect() == Correct.True){ //meerkeuze check
			return true;
		}
		ArrayList<Answer> answers = getAnswers(q);
		if (answers.contains(a))
		{
			//do other stuff here
		}
		return false;
	}

}
