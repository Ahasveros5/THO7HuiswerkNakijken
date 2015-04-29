package huiswerknakijken.hu.Servlets;

import huiswerknakijken.hu.DAO.HomeworkDAO;
import huiswerknakijken.hu.Domain.Homework;
import huiswerknakijken.hu.Domain.Person;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LeraarOverzichtServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		HomeworkDAO dao = new HomeworkDAO();
		HttpSession session = req.getSession();
		Person teacher = (Person) session.getAttribute("user");
		int teacherID = teacher.getID();
		ArrayList<Homework> hwList = new ArrayList<Homework>();
		RequestDispatcher rd = null;
		if(dao.retrieveAllByTeacher(teacherID, 1).size() < 1)
			System.out.println("empty list");
		hwList.addAll(dao.retrieveAllByTeacher(teacherID, 1));
		session.setAttribute("Huiswerk", hwList);
		rd = req.getRequestDispatcher("LeraarOverzicht.jsp");
		
		if(rd!=null){
			rd.forward(req, resp);
		}
}
}