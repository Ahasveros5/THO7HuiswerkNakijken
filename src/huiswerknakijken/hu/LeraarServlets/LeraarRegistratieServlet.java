package huiswerknakijken.hu.LeraarServlets;

import huiswerknakijken.hu.DAO.PersonDAO;
import huiswerknakijken.hu.Domain.Person;
import huiswerknakijken.hu.Domain.Person.UserRole;
import huiswerknakijken.hu.Domain.Teacher;
import huiswerknakijken.hu.Util.PasswordHash;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LeraarRegistratieServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");

		String naam = req.getParameter("invoer_naam");
		String achternaam = req.getParameter("invoer_achternaam");		
		String email1 = req.getParameter("invoer_email");
		String email2 = req.getParameter("invoer_emailb");
		String ww1 = req.getParameter("invoer_ww");
		String ww2 = req.getParameter("invoer_wwb");

		RequestDispatcher rd = null;
		
		PersonDAO dao = new PersonDAO();

		if(
			naam.isEmpty() ||
			achternaam.isEmpty() ||
			email1.isEmpty()||
			email2.isEmpty()||
			ww1.isEmpty()||
			ww2.isEmpty()) {
			
			req.setAttribute("msgs", "Vul alle velden in.");
			rd = req.getRequestDispatcher("LeraarRegistreren.jsp");
		} else {
			if(!ww1.equals(ww2)) {
				req.setAttribute("msgs", "Wachtwoorden komen niet overeen");
				rd = req.getRequestDispatcher("LeraarRegistreren.jsp");
			} else if(!email1.equals(email2)){
				req.setAttribute("msgs", "Emailadressen komen niet overeen");
				rd = req.getRequestDispatcher("LeraarRegistreren.jsp");		
			} else if(dao.retrieveByEmail(email1, 0) != null){
				req.setAttribute("msgs", "Emailadres staat al geregistreerd");
				rd = req.getRequestDispatcher("LeraarRegistreren.jsp");
			}else{
				System.out.println("ADDING TEACHER");
				Person p = new Teacher();
				p.setFirstName(naam);
				p.setLastName(achternaam);
				p.setEmail(email1);
				try {
					p.setPassword(PasswordHash.createHash(ww1));
				} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				p.setRole(UserRole.Teacher);
				
				dao.add(p);			
				rd = req.getRequestDispatcher("loginpage.jsp");
			}
		}
		
		if(rd != null) {
			rd.forward(req, resp);
		}
	}

}
