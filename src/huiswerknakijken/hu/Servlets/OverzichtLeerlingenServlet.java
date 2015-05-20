package huiswerknakijken.hu.Servlets;

import huiswerknakijken.hu.DAO.ClassDAO;
import huiswerknakijken.hu.DAO.PersonDAO;
import huiswerknakijken.hu.Domain.Person;
import huiswerknakijken.hu.Domain.Person.UserRole;
import huiswerknakijken.hu.Domain.Student;
import huiswerknakijken.hu.Domain.Klass;

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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		ArrayList<Student>students = new ArrayList<Student>();
		ArrayList<Klass>klassen = new ArrayList<Klass>();		
		PersonDAO dao = new PersonDAO();
		ClassDAO cdao = new ClassDAO();
		HttpSession session = req.getSession();
		RequestDispatcher rd = null;
				
		klassen.addAll(cdao.retrieveAll());
		
		List<Person> list = dao.retrieveAllByRole(UserRole.Student.getIndex(), 1);
		for(int i = 0; i<list.size(); i++){
			students.add(list.get(i).toStudent());
		}
		
		
		session.setAttribute("Leerlingen", students);
		session.setAttribute("klassen", klassen);
		rd = req.getRequestDispatcher("OverzichtLeerlingen.jsp");
		
		if(rd!=null){
			rd.forward(req, resp);
		}
	}
}
