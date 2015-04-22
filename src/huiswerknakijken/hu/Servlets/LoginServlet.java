package huiswerknakijken.hu.Servlets;

import huiswerknakijken.hu.DAO.PersonDAO;
import huiswerknakijken.hu.Domain.Person;
import huiswerknakijken.hu.Domain.Person.UserRole;

import java.io.IOException;

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
		if(userEmail.isEmpty() || password.isEmpty()) {
			req.setAttribute("msgs", "Vul alle velden in.");
			rd = req.getRequestDispatcher("loginpage.jsp");
		} else {			
			if (p != null && password.equals(p.getPassword())){
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
					rd = req.getRequestDispatcher("LeerlingOverzicht.jsp");
				}
			} else {
				req.setAttribute("msgs", "Wachtwoord of gebruikersnaam incorrect.");
				rd = req.getRequestDispatcher("loginpage.jsp");
				System.out.println("testor");
			}
		}	
		
		
		if(rd != null) {
			rd.forward(req, resp);
		}
	}
	}
