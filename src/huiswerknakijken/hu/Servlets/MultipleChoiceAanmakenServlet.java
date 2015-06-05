package huiswerknakijken.hu.Servlets;

import huiswerknakijken.hu.DAO.HomeworkDAO;
import huiswerknakijken.hu.DAO.QuestionDAO;
import huiswerknakijken.hu.Domain.Answer;
import huiswerknakijken.hu.Domain.Answer.Correct;
import huiswerknakijken.hu.Domain.Homework;
import huiswerknakijken.hu.Domain.Question;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MultipleChoiceAanmakenServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		ArrayList<Answer>antwoorden = new ArrayList<Answer>();
		HttpSession session = req.getSession();
		String naam = req.getParameter("naam");
		String vraag = req.getParameter("vraagMultipleChoice");
		String antwoord1 = req.getParameter("Antwoord1");
		String antwoord2 = req.getParameter("Antwoord2");
		String antwoord3 = req.getParameter("Antwoord3");
		String antwoord4 = req.getParameter("Antwoord4");
		int goedeAntwoord = Integer.parseInt(req.getParameter("GoedeAntwoord"));
		ArrayList<Question> questions = new ArrayList<Question>();
		
		Question q = null;
		Answer a = new Answer();
		a.setAnswer(antwoord1);
		
		antwoorden.add(a);
		
		Answer b = new Answer();
		b.setAnswer(antwoord2);
		antwoorden.add(b);
		
		Answer c = new Answer();
		c.setAnswer(antwoord3);
		antwoorden.add(c);
		
		Answer d = new Answer();
		d.setAnswer(antwoord4);
		antwoorden.add(d);

		if(goedeAntwoord == 1){
			a.setCorrect(Correct.True);
		}
		if(goedeAntwoord == 2){
			b.setCorrect(Correct.True);
		}
		if(goedeAntwoord == 3){
			c.setCorrect(Correct.True);
		}
		if(goedeAntwoord == 4){
			d.setCorrect(Correct.True);
		}
		RequestDispatcher rd = null;
		QuestionDAO dao = new QuestionDAO();

		if( 
			vraag.isEmpty()||
			antwoord1.isEmpty()||
			antwoord2.isEmpty()||
			antwoord3.isEmpty()||
			antwoord4.isEmpty()||
			goedeAntwoord==0){
			
			req.setAttribute("msgs", "Vul alle velden in.");
			rd = req.getRequestDispatcher("LeraarVraagAanmaken.jsp");
		}else{
			q = new Question();
			q.setName(naam);
			q.setDescription(vraag);
			q.setAnswers(antwoorden);
			a.setQuestion(q);
			b.setQuestion(q);
			c.setQuestion(q);
			d.setQuestion(q);
			ArrayList<Homework> hl = (ArrayList<Homework>)session.getAttribute("HwObj");
			Homework h = hl.get(0);
			q.setHomework(h);
			HomeworkDAO hdao = new HomeworkDAO();
			h.addQuestion(q);
			hdao.update(h);
			rd = req.getRequestDispatcher("HuiswerkOverzicht.jsp?id="+h.getID());
			questions.add(q);
			session.setAttribute("questObj", questions);
			dao.addComplete(q);
		}
		
		
		if(rd != null) {
			rd.forward(req, resp);
		}
}
}
