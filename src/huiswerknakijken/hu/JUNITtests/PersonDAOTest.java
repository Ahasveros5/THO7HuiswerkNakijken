package huiswerknakijken.hu.JUNITtests;

import huiswerknakijken.hu.DAO.ClassDAO;
import huiswerknakijken.hu.DAO.PersonDAO;
import huiswerknakijken.hu.Domain.Klass;
import huiswerknakijken.hu.Domain.Person;
import huiswerknakijken.hu.Domain.Person.UserRole;
import huiswerknakijken.hu.Domain.Student;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PersonDAOTest {

	private String firstName;	
	private String email;
	private String lastName;		
	private String password;
	private UserRole role;
	private int personid;
	private PersonDAO pdao;
	private Student user;
	private Klass klass;
	private ClassDAO cdao;


	@Before
	public void setUp() throws Exception {
		pdao = new PersonDAO();
		cdao = new ClassDAO();
		firstName = "Henk";
		lastName = "Paladijn";
		email = "henk-paladijn@hotmail.com";
		password = "LarenVrede";
		role = UserRole.Student;
		personid = 9234512;
		klass = new Klass("Test Klas");
		user = new Student(firstName,lastName,email, personid,password,role);
		klass.addStudent(user);
		user.setMainClass(klass);
		cdao.add(klass);
		pdao.addStudent(user);		
		
	}

	@Test
	public void testRetrievePersonCorrect() {
		Person test = pdao.retrieve(personid,1);
		Assert.assertNotNull(test);
	}
	
	@Test
	public void testRetrievePersonWrong() {
		Person test = pdao.retrieve(-1,1);
		Assert.assertNull(test);
	}
	
	@Test
	public void testRetrievePersonByClassCorrect() {
		ArrayList<Person> test = pdao.retrieveAllByClass(klass.getClassID(), 1);
		Assert.assertNotEquals(test, new ArrayList<Person>());
	}
	
	@Test
	public void testRetrievePersonByClassWrong() {
		ArrayList<Person> test = pdao.retrieveAllByClass(-1, 1);
		Assert.assertEquals(test, new ArrayList<Person>());
	}
	
	@Test
	public void testRetrievePersonByEmailCorrect() {
		Person test = pdao.retrieveByEmail(email, 1);
		Assert.assertNotNull(test);
	}
	
	@Test
	public void testRetrievePersonByEmailWrong() {
		Person test = pdao.retrieveByEmail("-1@-1.nl", 1);
		Assert.assertNull(test);
	}

	
	@After
	public void after() throws Exception {
		pdao.delete(user);
		cdao.delete(klass);
	}

}
