package huiswerknakijken.hu.Servlets;

import huiswerknakijken.hu.DAO.HomeworkDAO;
import huiswerknakijken.hu.Domain.Homework;
import huiswerknakijken.hu.Domain.Person;
import huiswerknakijken.hu.Domain.Question;
import huiswerknakijken.hu.Util.OracleConnectionPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

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
		for(Question q : questions)
			System.out.println("tra:::: " + q.getAnswers().size());
		rd = req.getRequestDispatcher("HuiswerkMaken.jsp");
		
		if(rd!= null){
			rd.forward(req, resp);
		}
	}
}
