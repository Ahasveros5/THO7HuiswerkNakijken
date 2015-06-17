package huiswerknakijken.hu.Servlets;

import huiswerknakijken.hu.DAO.ClassDAO;
import huiswerknakijken.hu.DAO.CourseDAO;
import huiswerknakijken.hu.Domain.Course;
import huiswerknakijken.hu.Domain.Klass;
import huiswerknakijken.hu.Domain.Person;
import huiswerknakijken.hu.Util.OracleConnectionPool;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VakSpecifiek extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		HttpSession session = req.getSession();
		CourseDAO cdao = new CourseDAO();
		ClassDAO kdao = new ClassDAO();
		RequestDispatcher rd = null;
		String vakId = req.getParameter("id");
		Course c = cdao.retrieveByID(Integer.parseInt(vakId), 2, OracleConnectionPool.getConnection());
		ArrayList<Klass>klassen = (ArrayList<Klass>) kdao.retrieveAll();
		ArrayList<Person> students = c.getStudents();
		session.setAttribute("Vak", c);
		session.setAttribute("studenten", students);
		session.setAttribute("Klassen", klassen);
		
		rd = req.getRequestDispatcher("VakSpecifiek.jsp?id="+vakId);
		
		if(rd!= null){
			rd.forward(req, resp);
		}
}
}
