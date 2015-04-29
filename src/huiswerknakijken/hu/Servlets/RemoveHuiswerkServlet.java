package huiswerknakijken.hu.Servlets;

import huiswerknakijken.hu.Domain.Homework;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RemoveHuiswerkServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		RequestDispatcher rd = null;
		HttpSession session = req.getSession();
		String hwID = req.getParameter("hwID");
		ArrayList<Homework> hwList = (ArrayList<Homework>) session.getAttribute("Huiswerk");
		System.out.println("TEST "+ hwID+" "+hwList.size());
		if(hwList !=null && !hwList.isEmpty()){
			hwList.remove(hwID);
		}
		session.setAttribute("Huiswerk", hwList);
		rd = req.getRequestDispatcher("LeraarOverzicht.jsp");
		if(rd!=null){
		rd.forward(req,resp);
		}
	}
}
