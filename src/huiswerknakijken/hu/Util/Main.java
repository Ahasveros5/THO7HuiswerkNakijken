package huiswerknakijken.hu.Util;

import huiswerknakijken.hu.DAO.ClassDAO;
import huiswerknakijken.hu.DAO.PersonDAO;
import huiswerknakijken.hu.Domain.Person;
import huiswerknakijken.hu.Domain.Person.UserRole;
import huiswerknakijken.hu.Domain.Class;

public class Main {

	
	public static void main(String [ ] args)
	{
		System.out.println("starting creation of samples");
		//CreateTestData();
		System.out.println("finished creating samples");
		
	}
	
	private static void CreateTestData(){
		ClassDAO cdao = new ClassDAO();
		Class c = new Class();
		c.setClassID(0);
		c.setName("Geen klas");
		cdao.add(c);
		PersonDAO pDAO = new PersonDAO();
		Person p = new Person();
		p.setID(3);
		p.setEmail("test@test.nl");
		p.setFirstName("Guido");
		p.setLastName("Kerkhove");
		p.setPassword("test");
		p.setRole(UserRole.Student);
		pDAO.add(p);
	}
}
