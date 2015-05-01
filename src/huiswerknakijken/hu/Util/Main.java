package huiswerknakijken.hu.Util;

import huiswerknakijken.hu.DAO.AnswerDAO;
import huiswerknakijken.hu.DAO.ClassDAO;
import huiswerknakijken.hu.DAO.HomeworkDAO;
import huiswerknakijken.hu.DAO.PersonDAO;
import huiswerknakijken.hu.DAO.QuestionDAO;
import huiswerknakijken.hu.Domain.Answer;
import huiswerknakijken.hu.Domain.Answer.Correct;
import huiswerknakijken.hu.Domain.Class;
import huiswerknakijken.hu.Domain.Homework;
import huiswerknakijken.hu.Domain.Person;
import huiswerknakijken.hu.Domain.Person.UserRole;
import huiswerknakijken.hu.Domain.Question;
import huiswerknakijken.hu.Domain.Student;
import huiswerknakijken.hu.Domain.Teacher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	
	public static void main(String [ ] args) throws FileNotFoundException
	{
		ExcelImport.readFile(new File("C:\\xampp\\tomcat\\temp.xlsx"));
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
		Person p = new Student();
		p.setID(12312313);
		p.setEmail("test@test.nl");
		p.setFirstName("Guido");
		p.setLastName("Kerkhove");
		p.setPassword("test");
		p.setRole(UserRole.Student);
		Person p2 = new Teacher();
		p2.setEmail("test2@test.nl");
		p2.setFirstName("Henk");
		p2.setLastName("Willem");
		p2.setPassword("test");
		p2.setRole(UserRole.Teacher);
		pDAO.add(p);
		pDAO.add(p2);
		HomeworkDAO hdao = new HomeworkDAO();
		Homework h = new Homework();
		h.setName("Engels Week 1");
		h.addStudent((Student) p);
		h.setTeacher((Teacher) p2);
		QuestionDAO qdao = new QuestionDAO();
		Question q = new Question();
		q.setName("Vraag 1");
		q.setDescription("In the sentence \" It is ... much.\" what needs to be filled in at the dots?");
		q.setHomework(h);
		hdao.add(h);
		
		qdao.add(q);
		System.out.println("QID: " + q.getID());
		AnswerDAO adao = new AnswerDAO();
		Answer a = new Answer();
		a.setAnswer("to");
		a.setCorrect(Correct.False);
		a.setQuestion(q);
		Answer a2 = new Answer();
		a2.setAnswer("too");
		a2.setCorrect(Correct.True);
		a2.setQuestion(q);
		Answer a3 = new Answer();
		a3.setAnswer("both are correct");
		a3.setCorrect(Correct.False);
		a3.setQuestion(q);
		Answer a4 = new Answer();
		a4.setAnswer("both are false");
		a4.setCorrect(Correct.False);
		a4.setQuestion(q);
		adao.add(a);
		adao.add(a2);
		adao.add(a3);
		adao.add(a4);
		
	}
}
