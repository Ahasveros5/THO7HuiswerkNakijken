package huiswerknakijken.hu.Servlets;

import huiswerknakijken.hu.DAO.PersonDAO;
import huiswerknakijken.hu.Domain.Person;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");

		String userEmail = req.getParameter("email_login");
		String password = req.getParameter("ww_login");
		PersonDAO pdao = new PersonDAO();
		Person p = pdao.retrieveByEmail(userEmail, 1);
		
		RequestDispatcher rd = null;
		//HttpSession session = null;
		if(userEmail.isEmpty() || password.isEmpty()) {
			req.setAttribute("msgs", "Vul alle velden in.");
			rd = req.getRequestDispatcher("loginpage.jsp");
				} else {			
					if (p != null || password.equals(p.getPassword())){
					//session.setAttribute("userEmail",userEmail );
					rd = req.getRequestDispatcher("index.jsp");
					} else {
						req.setAttribute("msgs", "Wachtwoord of gebruikersnaam incorrect.");
						rd = req.getRequestDispatcher("loginpage.jsp");
					}
				}				
		
		if(rd != null) {
			rd.forward(req, resp);
		}
	}
	}
