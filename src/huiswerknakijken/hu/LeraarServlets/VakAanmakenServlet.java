package huiswerknakijken.hu.LeraarServlets;

import huiswerknakijken.hu.DAO.CourseDAO;
import huiswerknakijken.hu.DAO.PersonDAO;
import huiswerknakijken.hu.Domain.Course;
import huiswerknakijken.hu.Domain.Person;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VakAanmakenServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		RequestDispatcher rd = null;
		HttpSession session = req.getSession();
		CourseDAO dao = new CourseDAO();
		PersonDAO pdao = new PersonDAO();
		
		String naam = req.getParameter("Vaknaam");
		String[] teacherIDs = req.getParameterValues("teacherSelect");
		ArrayList<Person>teachers = new ArrayList<Person>();
		for(int i = 0; i<teacherIDs.length; i++){
			teachers.add(pdao.retrieve(Integer.parseInt(teacherIDs[i]), 1));
		}
		
		if(!naam.equals("")&&!(naam==null)){
		Course c = new Course();
		c.setTeachers(teachers);
		c.setName(naam);
		dao.add(c);
		}
		session.setAttribute("Vakken", dao.retrieveAllByPerson(((Person)session.getAttribute("user")).getID(), 1));
		rd = req.getRequestDispatcher("VakkenOverzicht.jsp");
		if(rd!= null){
			rd.forward(req, resp);
		}
	}
}
