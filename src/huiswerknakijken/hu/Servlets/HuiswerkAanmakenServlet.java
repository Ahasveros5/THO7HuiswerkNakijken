package huiswerknakijken.hu.Servlets;

import huiswerknakijken.hu.DAO.HomeworkDAO;
import huiswerknakijken.hu.Domain.Homework;
import huiswerknakijken.hu.Domain.Person;

import java.io.IOException;
import java.util.ArrayList;

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
		HomeworkDAO dao = new HomeworkDAO();
		String[]datevals;
		String[]timevals;
		String hwnaam = req.getParameter("HWName");
		String deadline = req.getParameter("deadline");
		String DLtime = req.getParameter("DLTime");		
		datevals = deadline.split("-");
		timevals = DLtime.split(":");
		ArrayList<Homework> hwList = new ArrayList<Homework>();
		int day = Integer.parseInt(datevals[2]);
		int month = Integer.parseInt(datevals[1]);
		int year = Integer.parseInt(datevals[0]);
		int uur  = Integer.parseInt(timevals[0]);
		int minuut = Integer.parseInt(timevals[1]);
		Person teacher = (Person)session.getAttribute("user");
		int teacherID = teacher.getID();
		String Dline = day+" - "+ month+" - "+year+" Om "+uur+ ":"+minuut;
		
		System.out.println("Selected date: "+day +" - "+month+" - "+year+" Om: "+uur+":"+minuut);
		
		if(!(hwnaam==null)&& !hwnaam.equals("")&& !(deadline==null)&&!deadline.equals("")){
		Homework hw = new Homework();
		hw.setName(hwnaam);
		hw.setDeadline(Dline);

		dao.add(hw);
		
		hwList.addAll(dao.retrieveAllByTeacher(teacherID, 1));
		req.setAttribute("Huiswerk", hwList);
		rd = req.getRequestDispatcher("LeraarOverzicht.jsp");
		}
		rd.forward(req,resp);
	}
	
}
