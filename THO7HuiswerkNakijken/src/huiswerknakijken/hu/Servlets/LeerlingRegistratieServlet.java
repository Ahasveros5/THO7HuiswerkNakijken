package huiswerknakijken.hu.Servlets;

import huiswerknakijken.hu.Domain.Person;
import huiswerknakijken.hu.Domain.Student;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LeerlingRegistratieServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");

		String naam = req.getParameter("invoer_naam");
		String achternaam = req.getParameter("invoer_achternaam");
		int studentnr = Integer.parseInt(req.getParameter("invoer_studentnr"));

		String email1 = req.getParameter("invoer_email");
		String email2 = req.getParameter("invoer_emailb");
		String ww1 = req.getParameter("invoer_ww");
		String ww2 = req.getParameter("invoer_wwb");

		RequestDispatcher rd = null;

		if(
			naam.isEmpty() ||
			achternaam.isEmpty() ||
			email1.isEmpty()||
			email2.isEmpty()||
			ww1.isEmpty()||
			ww2.isEmpty()) {
			
			req.setAttribute("msgs", "Vul alle velden in.");
			rd = req.getRequestDispatcher("register.jsp");
		} else {
			if(!ww1.equals(ww2)) {
				req.setAttribute("msgs", "Wachtwoorden komen niet overeen");
				rd = req.getRequestDispatcher("register.jsp");
			} else if(!email1.equals(email2)){
				req.setAttribute("msgs", "Emailadressen komen niet overeen");
				rd = req.getRequestDispatcher("register.jsp");			
			}
		}
		Person p = new Student();
		p.setEmail(email1);
		p.setPassword(ww1);
		p.setFirstName(naam);
		p.setLastName(achternaam);

		
	}

}
