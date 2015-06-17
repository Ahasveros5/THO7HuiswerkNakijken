package huiswerknakijken.hu.Util;

import huiswerknakijken.hu.DAO.AnswerDAO;
import huiswerknakijken.hu.Domain.Answer;
import huiswerknakijken.hu.Domain.Answer.Correct;
import huiswerknakijken.hu.Domain.Person;
import huiswerknakijken.hu.Domain.Question;
import huiswerknakijken.hu.Domain.Question.Type;

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
	//returning points granted, fully correct = 1
	//0.5 = 50% correct
	//0.1 = 10% correct
	//0 = 0% correct
	public static double getGivenAnswerPoints(Question q, Person p){
		AnswerDAO adao = new AnswerDAO();
		if(q.getType() == Type.Meerkeuze){
			if((adao.retrieveGivenAnswer(q, p, 1)).getCorrect() == Correct.True){
				return 1;
			} else return 0;
		} else if(q.getType() == Type.Openvraag){
			String[] keywords = adao.retrieveCorrectByQuestion(q.getID(), 1).getAnswer().split(",");
			String answer = adao.retrieveGivenAnswer(q, p, 1).getAnswer();
			double pointsGiven = 1/keywords.length;
			double points = 0;
			for(String s : keywords){
				if (answer.toLowerCase().contains(s.toLowerCase()))
					points += pointsGiven;
			}
			return points;
		}
		
		
		
		return 0;
	}

}
