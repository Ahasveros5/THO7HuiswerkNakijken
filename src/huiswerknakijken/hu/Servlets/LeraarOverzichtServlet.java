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

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		HomeworkDAO dao = new HomeworkDAO();
		HttpSession session = req.getSession();
		Person teacher = (Person) session.getAttribute("user");
		int teacherID = teacher.getID();
		ArrayList<Homework> hwList = dao.retrieveAllByTeacher(teacherID, 1);
		RequestDispatcher rd = null;
		session.setAttribute("Huiswerk", hwList);
		rd = req.getRequestDispatcher("LeraarOverzicht.jsp");
		
		if(rd!=null){
			rd.forward(req, resp);
		}
}
}
