package huiswerknakijken.hu.Servlets;

import huiswerknakijken.hu.DAO.ClassDAO;
import huiswerknakijken.hu.DAO.PersonDAO;
import huiswerknakijken.hu.Domain.Klass;
import huiswerknakijken.hu.Domain.Person;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SpecifiekeLeerlingServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		RequestDispatcher rd = null;
		HttpSession session = req.getSession();
		PersonDAO dao = new PersonDAO();
		ClassDAO kdao = new ClassDAO();
		String id = req.getParameter("id");
		System.out.println("retrieving!");
		List<Klass> klassen = kdao.retrieveAll(1);
		Person p = dao.retrieve(Integer.parseInt(id), 1);
		session.setAttribute("selectedStudent", p);
		session.setAttribute("klassen", klassen);
		rd = req.getRequestDispatcher("SpecifiekeLeerling.jsp");
		
		if(rd!= null){
			rd.forward(req, resp);
		}
	}
}
