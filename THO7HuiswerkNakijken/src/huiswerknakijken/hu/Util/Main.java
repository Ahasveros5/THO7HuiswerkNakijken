package huiswerknakijken.hu.Util;
import huiswerknakijken.hu.DAO.PersonDAO;
import huiswerknakijken.hu.Domain.Person;

public class Main {

	
	public static void main(String [ ] args)
	{
		PersonDAO pDAO = new PersonDAO();
		Person p = pDAO.retrieveByEmail("Test@test.nl", 0);
		System.out.println(p.getFirstName());
		
	}
}
