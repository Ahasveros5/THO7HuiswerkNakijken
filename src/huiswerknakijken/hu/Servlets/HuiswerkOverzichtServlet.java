package huiswerknakijken.hu.Servlets;

import huiswerknakijken.hu.DAO.HomeworkDAO;
import huiswerknakijken.hu.DAO.PersonDAO;
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

public class HuiswerkOverzichtServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		RequestDispatcher rd = null;
		HttpSession session = req.getSession();
		HomeworkDAO dao = new HomeworkDAO();
		PersonDAO pdao = new PersonDAO();
		
		int id = Integer.parseInt(req.getParameter("id"));

		ArrayList<Person> studenten = (ArrayList<Person>) pdao.retrieveStudentsByHomework(id, 1);
		ArrayList<Homework> hwlist = new ArrayList<Homework>();
		Connection con = OracleConnectionPool.getConnection();
		for(int i = 0; i<studenten.size(); i++){
			Homework hw = dao.retrieveHomeworkByStudent(dao.retrieveByID(id, 1).getID(), studenten.get(i).getID(), 2, con);
			hwlist.add(hw);
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("HwObj", hwlist);
		ArrayList<Question> questions = null;
		if(hwlist.get(0) != null)
			questions = hwlist.get(0).getQuestions();
		session.setAttribute("QuestObj", questions);

		rd = req.getRequestDispatcher("HuiswerkOverzicht.jsp?id="+id);
		
		if(rd!= null){
			rd.forward(req, resp);
		}
	}
}
