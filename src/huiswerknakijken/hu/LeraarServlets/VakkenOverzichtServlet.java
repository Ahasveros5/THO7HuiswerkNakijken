package huiswerknakijken.hu.LeraarServlets;

import huiswerknakijken.hu.DAO.CourseDAO;
import huiswerknakijken.hu.DAO.PersonDAO;
import huiswerknakijken.hu.Domain.Course;
import huiswerknakijken.hu.Domain.Person;
import huiswerknakijken.hu.Domain.Person.UserRole;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VakkenOverzichtServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		RequestDispatcher rd = null;
		HttpSession session = req.getSession();
		CourseDAO dao = new CourseDAO();
		PersonDAO pdao = new PersonDAO();
		int userID = ((Person) session.getAttribute("user")).getID();
		ArrayList<Course> vakken  = dao.retrieveAllByPerson(userID, 2);
		ArrayList<Person> teachers = (ArrayList<Person>) pdao.retrieveAllByRole(UserRole.Teacher.getIndex(), 1);
		System.out.println("Sizetest"+ vakken.size());
	
		session.setAttribute("leraren", teachers);
		session.setAttribute("Vakken", vakken);
		
		rd = req.getRequestDispatcher("VakkenOverzicht.jsp");
		if(rd!= null){
			rd.forward(req, resp);
		}
		
		
	}

}
