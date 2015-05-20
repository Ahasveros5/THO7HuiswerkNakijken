package huiswerknakijken.hu.Servlets;

import huiswerknakijken.hu.DAO.ClassDAO;
import huiswerknakijken.hu.Domain.Klass;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class KlasMakenServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		HttpSession session = req.getSession();
		RequestDispatcher rd = null;
		ClassDAO dao = new ClassDAO();
		ArrayList<Klass> klassen = new ArrayList<Klass>();
		String naam = req.getParameter("Klasnaam");
		Klass c = new Klass(naam);
		dao.add(c);
		klassen.addAll(dao.retrieveAll());
		session.setAttribute("klassen", klassen);
		
		rd = req.getRequestDispatcher("OverzichtLeerlingen.jsp");
		
		if(rd!=null){
			rd.forward(req, resp);
		}
	}
}
