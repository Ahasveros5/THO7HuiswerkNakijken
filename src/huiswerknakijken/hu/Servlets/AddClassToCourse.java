package huiswerknakijken.hu.Servlets;

import huiswerknakijken.hu.DAO.ClassDAO;
import huiswerknakijken.hu.Domain.Course;
import huiswerknakijken.hu.Domain.Klass;
import huiswerknakijken.hu.Domain.Person;
import huiswerknakijken.hu.Domain.Student;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddClassToCourse extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		HttpSession session = req.getSession();
		RequestDispatcher rd = null;
		ClassDAO cdao = new ClassDAO();
		Course c = (Course) session.getAttribute("Vak");
		int vakId = c.getID();
		String klas = req.getParameter("VakSelect");
		Klass k = cdao.retrieveByName(klas, 2);
		ArrayList<Person> students = (ArrayList<Person>) session.getAttribute("studenten");
		if(students == null){
			System.out.println("Geen studenten!!");
		}
		for(int i= 0; i<k.getStudents().size(); i++){
		students.add(k.getStudents().get(i));
		}
		session.setAttribute("studenten",students);
		rd = req.getRequestDispatcher("VakSpecifiek.jsp?id="+vakId);
		
		if(rd!= null){
			rd.forward(req, resp);
		}
	}
}