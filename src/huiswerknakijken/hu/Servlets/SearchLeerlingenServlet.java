package huiswerknakijken.hu.Servlets;

import huiswerknakijken.hu.DAO.PersonDAO;
import huiswerknakijken.hu.Domain.Person;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class SearchLeerlingenServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");	
		PersonDAO dao = new PersonDAO();
		HttpSession session = req.getSession();
		RequestDispatcher rd = null;
		String searchvalues = req.getParameter("searchfield");
		System.out.println("searching on: "+ searchvalues);
		List<Person> list = dao.retrieveAllMatching(searchvalues, 1);
		session.setAttribute("GevondenLeerlingen", list);
		//session.setAttribute("klassen", klassen);
		rd = req.getRequestDispatcher("OverzichtLeerlingen.jsp");
		
		if(rd!=null){
			rd.forward(req, resp);
		}
	}
}