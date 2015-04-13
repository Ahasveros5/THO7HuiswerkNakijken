package huiswerknakijken.hu.Servlets;

import huiswerknakijken.hu.Domain.Person;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		RequestDispatcher rd = null;
		HttpSession session = req.getSession();
		session.setAttribute("user", null);
		
/*		for (Cookie cookie : req.getCookies()) {
			if (cookie.getName().equals("userName")) { 
				userName = cookie.getValue();
				cookie.setMaxAge(0);
				break;
			}
		}*/

		rd = req.getRequestDispatcher("index.jsp");
		rd.forward(req, resp);
	}
}
