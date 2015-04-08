package huiswerknakijken.hu.Servlets;

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
		
		RequestDispatcher rd = null;
		//HttpSession session = null;
		if(userEmail.isEmpty() || password.isEmpty()) {
			req.setAttribute("msgs", "Vul alle velden in.");
			rd = req.getRequestDispatcher("loginpage.jsp");
				} else {				
					//session.setAttribute("userEmail",userEmail );
					rd = req.getRequestDispatcher("succes.jsp");
					}				
		
		if(rd != null) {
			rd.forward(req, resp);
		}
	}
	}
