package huiswerknakijken.hu.LeraarServlets;

import huiswerknakijken.hu.DAO.ClassDAO;
import huiswerknakijken.hu.DAO.PersonDAO;
import huiswerknakijken.hu.Domain.Klass;
import huiswerknakijken.hu.Domain.Person;
import huiswerknakijken.hu.Domain.Student;
import huiswerknakijken.hu.Util.PasswordHash;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GegevensWijzigenServlet extends HttpServlet{
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
		Student p = ((Person) session.getAttribute("selectedStudent")).toStudent();
		p.setFirstName(req.getParameter("voornaam"));
		p.setLastName(req.getParameter("achternaam"));
		p.setEmail(req.getParameter("email"));
		try {
			p.setPassword(PasswordHash.createHash(req.getParameter("password")));
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Klass k = new ClassDAO().retrieveByName(req.getParameter("KlasSelect"), 1);
		p.setMainClass(k);
		new PersonDAO().update(p);
		rd = req.getRequestDispatcher("SpecifiekeLeerling.jsp");
		
		if(rd!=null){
			rd.forward(req, resp);
		}
}
}
