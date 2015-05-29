package huiswerknakijken.hu.Servlets;

import huiswerknakijken.hu.DAO.PersonDAO;
import huiswerknakijken.hu.Domain.Person;
import huiswerknakijken.hu.Domain.Person.UserRole;
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

public class LeerlingRegistratieServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PersonDAO dao = new PersonDAO();
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
			rd = req.getRequestDispatcher("LeerlingRegistreren.jsp");
		} else {
			if(!ww1.equals(ww2)) {
				req.setAttribute("msgs", "Wachtwoorden komen niet overeen");
				rd = req.getRequestDispatcher("LeerlingRegistreren.jsp");
			} else if(!email1.equals(email2)){
				req.setAttribute("msgs", "Emailadressen komen niet overeen");
				rd = req.getRequestDispatcher("LeerlingRegistreren.jsp");			
			}
			else if(dao.retrieveByEmail(email1, 0) != null){
				req.setAttribute("msgs", "Emailadres staat al geregistreerd");
				rd = req.getRequestDispatcher("LeerlingRegistreren.jsp");
			}
			else{
				Person p = new Student();
				p.setEmail(email1);
				try {
					p.setPassword(PasswordHash.createHash(ww1));
				} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				p.setFirstName(naam);
				p.setLastName(achternaam);
				p.setID(studentnr);
				p.setRole(UserRole.Student);
				dao.add(p); 
				
				rd = req.getRequestDispatcher("loginpage.jsp");
				req.setAttribute("msgs", "Registreren succesvol");				
			}
		}		
		
		

		if(rd != null) {
			rd.forward(req, resp);
		}
	}

}
