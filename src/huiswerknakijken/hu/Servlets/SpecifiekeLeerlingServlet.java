package huiswerknakijken.hu.Servlets;

import huiswerknakijken.hu.DAO.PersonDAO;
import huiswerknakijken.hu.Domain.Homework;
import huiswerknakijken.hu.Domain.Person;
import huiswerknakijken.hu.Domain.Question;

import java.io.IOException;
import java.util.ArrayList;

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
		
		String id = req.getParameter("id");
		System.out.println("retrieving!");
		Person p = dao.retrieve(Integer.parseInt(id), 1);
		session.setAttribute("selectedStudent", p);
		rd = req.getRequestDispatcher("SpecifiekeLeerling.jsp");
		
		if(rd!= null){
			rd.forward(req, resp);
		}
	}
}
