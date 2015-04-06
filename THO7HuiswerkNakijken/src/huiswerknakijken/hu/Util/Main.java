package huiswerknakijken.hu.Util;
import huiswerknakijken.hu.DAO.ClassDAO;
import huiswerknakijken.hu.DAO.PersonDAO;
import huiswerknakijken.hu.Domain.Class;
import huiswerknakijken.hu.Domain.Person.UserRole;
import huiswerknakijken.hu.Domain.Student;

public class Main {

	
	public static void main(String [ ] args)
	{
		ClassDAO cDAO = new ClassDAO();
		Class s = cDAO.retrieveByName("IV2a", 2);
		for (Student st : s.getStudents())
			System.out.println(st.getEmail());
		
		
	}
}
