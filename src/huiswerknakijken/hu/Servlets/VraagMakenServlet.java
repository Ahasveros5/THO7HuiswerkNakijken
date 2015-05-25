package huiswerknakijken.hu.Servlets;

import huiswerknakijken.hu.DAO.AnswerDAO;
import huiswerknakijken.hu.Domain.Person;

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
		int a = Integer.parseInt(req.getParameter("answer"));
		int q = Integer.parseInt(req.getParameter("qid"));
		int p = ((Person) session.getAttribute("user")).getID();
		AnswerDAO adao = new AnswerDAO();
		adao.addGivenAnswer(a, q, p);
		System.out.println("answer: " + a);
		rd = req.getRequestDispatcher("VraagMaken.jsp?id=" + qnumber);
		
		if(rd!= null){
			rd.forward(req, resp);
		}
	}
}
