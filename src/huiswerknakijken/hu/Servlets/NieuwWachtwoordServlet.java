package huiswerknakijken.hu.Servlets;

import huiswerknakijken.hu.DAO.PersonDAO;
import huiswerknakijken.hu.Domain.Person;
import huiswerknakijken.hu.Domain.Person.UserRole;
import huiswerknakijken.hu.Util.PasswordHash;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NieuwWachtwoordServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PersonDAO dao = new PersonDAO();
		String ww1 = req.getParameter("invoer_ww");
		String ww2 = req.getParameter("invoer_wwb");
		Person p = (Person) req.getSession().getAttribute("user");

		RequestDispatcher rd = null;

		if(
			ww1.isEmpty()||
			ww2.isEmpty()) {
			
			req.setAttribute("msgs", "Vul alle velden in.");
			rd = req.getRequestDispatcher("NieuwWachtwoord.jsp");
		} else {
			if(!ww1.equals(ww2)) {
				req.setAttribute("msgs", "Wachtwoorden komen niet overeen");
				rd = req.getRequestDispatcher("NieuwWachtwoord.jsp");
			}
			else{
				try {
					p.setPassword(PasswordHash.createHash(ww1));
				} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("hashed pass: " + p.getPassword());
				dao.update(p); 
				System.out.println("Updated");
				
				if(p.getRole()==UserRole.Teacher){
					rd= req.getRequestDispatcher("LeraarOverzichtServlet.do");
				}
				if(p.getRole()==UserRole.Student){
					rd = req.getRequestDispatcher("LeerlingOverzichtServlet.do");
				}				
			}
		}		
		
		

		if(rd != null) {
			rd.forward(req, resp);
		}
	}

}
