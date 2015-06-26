package huiswerknakijken.hu.JUNITtests;

import huiswerknakijken.hu.DAO.CourseDAO;
import huiswerknakijken.hu.DAO.HomeworkDAO;
import huiswerknakijken.hu.DAO.PersonDAO;
import huiswerknakijken.hu.Domain.Course;
import huiswerknakijken.hu.Domain.Homework;
import huiswerknakijken.hu.Domain.Person;
import huiswerknakijken.hu.Domain.Person.UserRole;
import huiswerknakijken.hu.Domain.Student;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HomeworkDAOTest {

	private String name;
	private String deadline;
	private String description;
	private Homework homework;
	private HomeworkDAO hdao;
	private Person user;
	private PersonDAO pdao;
	private CourseDAO cdao;
	private Course course;


	@Before
	public void setUp() throws Exception {
		hdao = new HomeworkDAO();	
		pdao = new PersonDAO();
		cdao = new CourseDAO();
		name = "Week 1";
		deadline = "24-7-2015";
		description = "De opdrachten van Java week 1";
		homework = new Homework(name,deadline,description);
		course = new Course();
		homework.setCourse(course);
		cdao.add(course);
		System.out.println("TRALALA: " + course.getID());
		user = new Person("test","test","test", 51,"test",UserRole.Teacher);
		pdao.add(user);
		homework.setTeacher(user);
		homework.addStudent(user.toStudent());
		hdao.add(homework);
	}

	@Test
	public void testRetrieveHomeworkCorrect() {
		Homework test = hdao.retrieveByID(homework.getID(), 1);
		if(test == null) 
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAH: " + homework.getID());
		Assert.assertNotNull(test);
	}
	
	@Test
	public void testRetrieveHomeworkWrong() {
		Homework test = hdao.retrieveByID(-1,1);
		Assert.assertNull(test);
	}
	
	@Test
	public void testRetrieveHomeworkByNameCorrect() {
		Homework test = hdao.retrieveByName(name, 1);
		Assert.assertNotNull(test);
	}
	
	@Test
	public void testRetrieveHomeworkByNameWrong() {
		Homework test = hdao.retrieveByName("TestHomeworkDAOHomeworkObject", 1);
		Assert.assertNull(test);

	}
	
	@After
	public void after() throws Exception {
		hdao.delete(homework);
		cdao.delete(course);
		pdao.delete(user);
	}

}
