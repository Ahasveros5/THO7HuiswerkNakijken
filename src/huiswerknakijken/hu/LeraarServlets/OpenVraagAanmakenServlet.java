package huiswerknakijken.hu.LeraarServlets;

import huiswerknakijken.hu.DAO.HomeworkDAO;
import huiswerknakijken.hu.DAO.QuestionDAO;
import huiswerknakijken.hu.Domain.Answer;
import huiswerknakijken.hu.Domain.Homework;
import huiswerknakijken.hu.Domain.Question;
import huiswerknakijken.hu.Domain.Answer.Correct;
import huiswerknakijken.hu.Domain.Question.Type;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OpenVraagAanmakenServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		ArrayList<Answer>antwoorden = new ArrayList<Answer>();
		HttpSession session = req.getSession();
		String naam = req.getParameter("Naam");
		String vraag = req.getParameter("OpenVraag");
		String keywords = req.getParameter("Keywords");
		ArrayList<Question> questions = new ArrayList<Question>();
		
		Question q = null;

		RequestDispatcher rd = null;
		QuestionDAO dao = new QuestionDAO();

		if( 
			vraag.isEmpty()||
			keywords.isEmpty()){
			
			req.setAttribute("msgs", "Vul alle velden in.");
			rd = req.getRequestDispatcher("LeraarVraagAanmaken.jsp");
		}else{
			q = new Question();
			q.setType(Type.Openvraag);
			q.setName(naam);
			q.setDescription(vraag);
			
			Answer a = new Answer();
			a.setAnswer(keywords);
			a.setQuestion(q);
			a.setCorrect(Correct.True);
			antwoorden.add(a);
			
			q.setAnswers(antwoorden);
			
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
