package huiswerknakijken.hu.Servlets;

import huiswerknakijken.hu.DAO.HomeworkDAO;
import huiswerknakijken.hu.Domain.Homework;
import huiswerknakijken.hu.Domain.Homework.Status;
import huiswerknakijken.hu.Domain.Person;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LeerlingOverzichtServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		HomeworkDAO dao = new HomeworkDAO();
		HttpSession session = req.getSession();
		Person student = (Person) session.getAttribute("user");
		int studentID = student.getID();
		ArrayList<Homework> hwList = new ArrayList<Homework>();
		RequestDispatcher rd = null;
		hwList = dao.retrieveAllByPerson(studentID,1);
		hwList = dao.retrieveAllByPersonNotStatus(studentID, Status.Af, 1); //retrieves all homework that is NOT finished
		if(hwList.size() < 1)
			System.out.println("empty list");
		
		session.setAttribute("Huiswerk", hwList);
		rd = req.getRequestDispatcher("LeerlingOverzicht.jsp");
		
		if(rd!=null){
			rd.forward(req, resp);
		}
}
}
