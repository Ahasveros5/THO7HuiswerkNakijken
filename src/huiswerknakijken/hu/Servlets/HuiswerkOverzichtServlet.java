package huiswerknakijken.hu.Servlets;

import huiswerknakijken.hu.DAO.HomeworkDAO;
import huiswerknakijken.hu.DAO.PersonDAO;
import huiswerknakijken.hu.Domain.Homework;
import huiswerknakijken.hu.Domain.Person;
import huiswerknakijken.hu.Domain.Person.UserRole;
import huiswerknakijken.hu.Domain.Question;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HuiswerkOverzichtServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		RequestDispatcher rd = null;
		HttpSession session = req.getSession();
		HomeworkDAO dao = new HomeworkDAO();
		PersonDAO pdao = new PersonDAO();
		
		String id = req.getParameter("id");
		Homework hw = dao.retrieveByID(Integer.parseInt(id), 2);
		session.setAttribute("HwObj", hw);
		ArrayList<Question> questions = hw.getQuestions();
		session.setAttribute("QuestObj", questions);
		ArrayList<Person>studenten = (ArrayList<Person>) pdao.retrieveAllByRole(UserRole.Student.getIndex(), 1);
		session.setAttribute("leerlingen", studenten);
		rd = req.getRequestDispatcher("HuiswerkOverzicht.jsp?id="+id);
		
		if(rd!= null){
			rd.forward(req, resp);
		}
	}
}
