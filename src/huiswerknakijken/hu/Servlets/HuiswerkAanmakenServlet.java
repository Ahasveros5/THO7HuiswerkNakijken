package huiswerknakijken.hu.Servlets;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HuiswerkAanmakenServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		RequestDispatcher rd = null;
		HttpSession session = req.getSession();
		String[]datevals;
		String[]timevals;
		Calendar c = Calendar.getInstance();
		String hwnaam = req.getParameter("HWName");
		String deadline = req.getParameter("deadline");
		String DLtime = req.getParameter("DLTime");
		
		datevals = deadline.split("-");
		timevals = DLtime.split(":");
		
		int day = Integer.parseInt(datevals[2]);
		int month = Integer.parseInt(datevals[1]);
		int year = Integer.parseInt(datevals[0]);
		int uur  = Integer.parseInt(timevals[0]);
		int minuut = Integer.parseInt(timevals[1]);
		
		System.out.println("Selected date: "+day +" - "+month+" - "+year+" Om: "+uur+":"+minuut);
	}
}
