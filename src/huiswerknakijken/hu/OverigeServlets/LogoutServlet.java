package huiswerknakijken.hu.OverigeServlets;

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
		if(req != null && req.getSession() != null){
			HttpSession session = req.getSession();
			session.invalidate();
		}

		rd = req.getRequestDispatcher("loginpage.jsp");
		rd.forward(req, resp);
	}
}
