package huiswerknakijken.hu.LeraarServlets;

import huiswerknakijken.hu.DAO.ClassDAO;
import huiswerknakijken.hu.DAO.CourseDAO;
import huiswerknakijken.hu.Domain.Course;
import huiswerknakijken.hu.Domain.Klass;
import huiswerknakijken.hu.Domain.Person;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HuiswerkAanmaken extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		HttpSession session = req.getSession();
		RequestDispatcher rd = null;
		ClassDAO kdao = new ClassDAO();
		CourseDAO cdao = new CourseDAO();
		ArrayList<Klass> klassen = (ArrayList<Klass>) kdao.retrieveAll(2);
		ArrayList<Course>vakken = cdao.retrieveAllByPerson(((Person) session.getAttribute("user")).getID(), 1);
		session.setAttribute("klassen", klassen);
		session.setAttribute("Vakken",vakken);
		rd = req.getRequestDispatcher("HuiswerkAanmaken.jsp");
		
		if(rd!=null){
			rd.forward(req, resp);
		}
}
}
