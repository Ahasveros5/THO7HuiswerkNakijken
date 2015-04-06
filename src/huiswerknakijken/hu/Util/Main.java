package huiswerknakijken.hu.Util;

import huiswerknakijken.hu.DAO.PersonDAO;

public class Main {

	
	public static void main(String [ ] args)
	{
		PersonDAO pDAO = new PersonDAO();
		pDAO.retrieveByEmail("Test@test.nl", 0);
		
	}
}
