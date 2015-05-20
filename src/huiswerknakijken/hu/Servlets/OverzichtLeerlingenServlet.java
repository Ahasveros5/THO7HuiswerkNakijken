package huiswerknakijken.hu.Servlets;

import huiswerknakijken.hu.DAO.PersonDAO;
import huiswerknakijken.hu.Domain.Person;
import huiswerknakijken.hu.Domain.Person.UserRole;
import huiswerknakijken.hu.Domain.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class OverzichtLeerlingenServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		ArrayList<Student>students = new ArrayList<Student>();
		PersonDAO dao = new PersonDAO();
		HttpSession session = req.getSession();
		RequestDispatcher rd = null;
		
		List<Person> list = dao.retrieveAllByRole(UserRole.Student.getIndex(), 1);
		for(int i = 0; i<list.size(); i++){
			students.add(list.get(i).toStudent());
		}
		
		session.setAttribute("Leerlingen", students);
		rd = req.getRequestDispatcher("OverzichtLeerlingen.jsp");
		
		if(rd!=null){
			rd.forward(req, resp);
		}
	}
}
