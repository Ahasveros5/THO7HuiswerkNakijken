package huiswerknakijken.hu.Servlets;

import huiswerknakijken.hu.DAO.ClassDAO;
import huiswerknakijken.hu.DAO.PersonDAO;
import huiswerknakijken.hu.Domain.Klass;
import huiswerknakijken.hu.Domain.Person;
import huiswerknakijken.hu.Domain.Person.UserRole;
import huiswerknakijken.hu.Util.PasswordHash;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");

		String userEmail = req.getParameter("email_login");
		String password = req.getParameter("ww_login");
		PersonDAO pdao = new PersonDAO();
		Person p = pdao.retrieveByEmail(userEmail, 1);
		
		RequestDispatcher rd = null;
		HttpSession session = req.getSession();
		if(userEmail.isEmpty()) {
			req.setAttribute("msgs", "Vul een gebruikersnaam in.");
			rd = req.getRequestDispatcher("loginpage.jsp");
		} else {			
			try {
				if (p != null && (p.getPassword() == null || PasswordHash.validatePassword(password, p.getPassword()))){ //nog de p.getpassword null check apart maken
						System.out.println("test: " + p.getFirstName() + " " + p.getLastName());
						session.setAttribute("user",p );
						rd = req.getRequestDispatcher("index.jsp");
						System.out.println("testi");
						if(p.getRole() == null)
							System.out.println("ROLE IS NULL");
						if(p.getRole()==UserRole.Teacher){
							rd= req.getRequestDispatcher("LeraarOverzichtServlet.do");
						}
						if(p.getRole()==UserRole.Student){
							rd = req.getRequestDispatcher("LeerlingOverzichtServlet.do");
						}
						System.out.println("ppass: " + p.getPassword());
						if (p.getPassword() == null || p.getPassword().equals("")){
							System.out.println("nulll ww door gestuurd naar...");
							rd = req.getRequestDispatcher("NieuwWachtwoord.jsp");
						}
				} else {
					//System.out.println("pass: '" + p.getPassword() + "'");
					req.setAttribute("msgs", "Wachtwoord of gebruikersnaam incorrect.");
					rd = req.getRequestDispatcher("loginpage.jsp");
				}
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		
		
		if(rd != null) {
			rd.forward(req, resp);
		}
	}
	}
