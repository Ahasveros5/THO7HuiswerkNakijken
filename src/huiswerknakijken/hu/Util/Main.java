package huiswerknakijken.hu.Util;

import huiswerknakijken.hu.DAO.PersonDAO;
import huiswerknakijken.hu.Domain.Person;

public class Main {

	
	public static void main(String [ ] args)
	{
		System.out.println("a");
		PersonDAO pDAO = new PersonDAO();
		Person p = new Person();
		p.setID(3);
		pDAO.add(p);
		//System.out.println("test: " + p.getFirstName());
		
	}
}
