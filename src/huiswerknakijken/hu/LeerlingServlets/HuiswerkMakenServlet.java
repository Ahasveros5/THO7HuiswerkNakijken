package huiswerknakijken.hu.LeerlingServlets;

import huiswerknakijken.hu.DAO.HomeworkDAO;
import huiswerknakijken.hu.Domain.Homework;
import huiswerknakijken.hu.Domain.Person;
import huiswerknakijken.hu.Domain.Question;
import huiswerknakijken.hu.Util.OracleConnectionPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HuiswerkMakenServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		RequestDispatcher rd = null;
		HttpSession session = req.getSession();
		HomeworkDAO dao = new HomeworkDAO();
		
		String id = req.getParameter("id");
		System.out.println("retrieving!");
		Connection con = OracleConnectionPool.getConnection();
		Homework hw = dao.retrieveHomeworkByStudent(Integer.parseInt(id), ((Person)session.getAttribute("user")).getID(), 2, con);
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("HwObj", hw);
		ArrayList<Question> questions = hw.getQuestions();
		session.setAttribute("QuestObj", questions);
		Date date = new Date();
			try {
	            String target = hw.getDeadline();
	            target = target.replace(" Om ", " ");
	            DateFormat df = new SimpleDateFormat("dd - mm - yyyy hh:mm");
	            date =  df.parse(target);
	            System.out.println(date); 
	        } catch (ParseException pe) {
	            pe.printStackTrace();
	        }
			Date temp = new Date();
			System.out.println("hwdate: " + date.toString() + "||| cur: " + temp);
		if(date.after(temp))
			rd = req.getRequestDispatcher("HuiswerkLeerlingOverzicht.jsp");
		else
			rd = req.getRequestDispatcher("HuiswerkMaken.jsp");
		
		if(rd!= null){
			rd.forward(req, resp);
		}
	}
}
