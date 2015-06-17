package huiswerknakijken.hu.Servlets;

import huiswerknakijken.hu.DAO.AnswerDAO;
import huiswerknakijken.hu.DAO.HomeworkDAO;
import huiswerknakijken.hu.DAO.QuestionDAO;
import huiswerknakijken.hu.Domain.Answer;
import huiswerknakijken.hu.Domain.Answer.Correct;
import huiswerknakijken.hu.Domain.Homework;
import huiswerknakijken.hu.Domain.Homework.Status;
import huiswerknakijken.hu.Domain.Person;
import huiswerknakijken.hu.Domain.Question;
import huiswerknakijken.hu.Domain.Question.Type;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VraagMakenServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		RequestDispatcher rd = null;
		HttpSession session = req.getSession();

		String qnumber = req.getParameter("id");
		String a = req.getParameter("answer");
		int q = Integer.parseInt(req.getParameter("qid"));
		int p = ((Person) session.getAttribute("user")).getID();
		AnswerDAO adao = new AnswerDAO();
		QuestionDAO qdao = new QuestionDAO();
		Question cur = qdao.retrieveById(q, 1);
		if (cur.getType() == Type.Meerkeuze)
			adao.addGivenAnswer(Integer.parseInt(a), q, p);
		else{
			Answer ans = new Answer();
			ans.setAnswer(a);
			ans.setCorrect(Correct.None);
			ans.setQuestion(cur);
			adao.add(ans);
			adao.addGivenAnswer(ans.getID(), q, p);
		}
		System.out.println("answer: " + a);
		Homework h = (Homework)session.getAttribute("HwObj");
		if(h.getStudent() == null){
			System.out.println("Creating student for homework in questionthingy");
			h.setStudent((Person)session.getAttribute("user"));
		}
		h.setCurrentQuestion(Integer.parseInt(qnumber));
		if (h.getStatus() == Status.Nieuw){
			h.setStatus(Status.Begonnen);
		}
		
		if (Integer.parseInt(qnumber) > h.getNumberQuestions()){
			h.setStatus(Status.Af);
			rd = req.getRequestDispatcher("HuiswerkAf.jsp");
		} else{
			rd = req.getRequestDispatcher("VraagMaken.jsp?id=" + qnumber);
		}
		HomeworkDAO dao = new HomeworkDAO();
		dao.update(h);
		if(rd!= null){
			rd.forward(req, resp);
		}
	}
}
