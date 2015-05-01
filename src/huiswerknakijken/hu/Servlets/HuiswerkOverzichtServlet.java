package huiswerknakijken.hu.Servlets;

import huiswerknakijken.hu.DAO.HomeworkDAO;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HuiswerkOverzichtServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		RequestDispatcher rd = null;
		HttpSession session = req.getSession();
		HomeworkDAO dao = new HomeworkDAO();
		
		String id = req.getParameter("id");
		
		req.setAttribute("hwID", id);
		
		rd = req.getRequestDispatcher("HuiswerkOverzicht.jsp?id="+id);
		
		if(rd!= null){
			rd.forward(req, resp);
		}
	}
}
