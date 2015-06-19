package huiswerknakijken.hu.DAO;

import huiswerknakijken.hu.Domain.Homework;
import huiswerknakijken.hu.Domain.Homework.Status;
import huiswerknakijken.hu.Domain.Person;
import huiswerknakijken.hu.Domain.Person.UserRole;
import huiswerknakijken.hu.Domain.Student;
import huiswerknakijken.hu.Domain.Teacher;
import huiswerknakijken.hu.Util.OracleConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class HomeworkDAO implements DAOInterface<Homework> {
	public List<Homework> retrieveAll(int layerLevel) {
		ResultSet rs = null;
		ArrayList<Homework> homework = null;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Homework, Person_Homework");
			rs = statement.executeQuery();
			homework = resultSetExtractor(rs, layerLevel, connection);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return homework;
	}
	
	public List<Homework> retrieveAllWithStatus(Status s, int layerLevel) {
		ResultSet rs = null;
		ArrayList<Homework> homework = null;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Homework, Person_Homework WHERE status=?");
			statement.setInt(1, s.getIndex());
			rs = statement.executeQuery();
			homework = resultSetExtractor(rs, layerLevel, connection);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return homework;
	}
	//retrieves the homework that does not have the given status
	public List<Homework> retrieveAllNotStatus(Status s, int layerLevel) {
		ResultSet rs = null;
		ArrayList<Homework> homework = null;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Homework, Person_Homework WHERE status!=?");
			statement.setInt(1, s.getIndex());
			rs = statement.executeQuery();
			homework = resultSetExtractor(rs, layerLevel, connection);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return homework;
	}

	
	public Homework retrieveByName(String name, int layerLevel) {
		Homework retrievedHomework = null;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Homework, Person_Homework WHERE homework_name=?");
			statement.setString(1, name);
			ResultSet rs = statement.executeQuery();
			ArrayList<Homework> Homework = resultSetExtractor(rs, layerLevel, connection);
			retrievedHomework = Homework.get(0);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retrievedHomework;
	}
	
	public Homework retrieveByID(int id, int layerLevel) {
		Homework retrievedHomework = null;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Homework, Person_Homework WHERE Homework.homework_id=? AND Homework.homework_id = Person_Homework.homework_id");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			System.out.println("ID: "+ id);
			ArrayList<Homework> Homework = resultSetExtractor(rs, layerLevel, connection);
			if(Homework != null && Homework.size() > 0)
				retrievedHomework = Homework.get(0);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retrievedHomework;
	}
	
	public Homework retrieveHomeworkByStudent(int Hid, int Sid, int layerLevel, Connection con) {
		Homework retrievedHomework = null;
		Connection connection = con;
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Homework, Person_Homework WHERE Homework.homework_id=? AND Person_Homework.student_id=? AND Homework.homework_id = Person_Homework.homework_id");
			statement.setInt(1, Hid);
			statement.setInt(2, Sid);
			ResultSet rs = statement.executeQuery();
			ArrayList<Homework> Homework = resultSetExtractor(rs, layerLevel, connection);
			retrievedHomework = Homework.get(0);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retrievedHomework;
	}
	
	public ArrayList<Homework> retrieveAllByName(String name, int layerLevel) {
		ArrayList<Homework> homework = null;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Homework, Person_Homework WHERE homework_name=?");
			statement.setString(1, name);
			ResultSet rs = statement.executeQuery();
			homework = resultSetExtractor(rs, layerLevel, connection);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return homework;
	}
	
	public ArrayList<Homework> retrieveAllByStudent(int studentID, int layerLevel) {
		ArrayList<Homework> homework = null;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Homework, Person_Homework WHERE Person_Homework.Student_id=? AND Homework.homework_id = Person_Homework.homework_id");
			statement.setInt(1, studentID);
			ResultSet rs = statement.executeQuery();
			homework = resultSetExtractor(rs, layerLevel, connection);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return homework;
	}
	
	public ArrayList<Homework> retrieveAllByTeacher(int teacherID, int layerLevel) {
		ArrayList<Homework> homework = null;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Homework WHERE Homework.teacher_id=?");
			statement.setInt(1, teacherID);
			ResultSet rs = statement.executeQuery();
			homework = resultSetExtractor(rs, layerLevel, connection);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return homework;
	}
	
	public ArrayList<Homework> retrieveAllByPersonStatus(int studentID, Status s, int layerLevel) {
		ArrayList<Homework> homework = null;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Homework, Person_Homework WHERE Person_Homework.Student_id=? AND Homework.homework_id = Person_Homework.homework_id AND Status=?");
			statement.setInt(1, studentID);
			statement.setInt(2, s.getIndex());
			ResultSet rs = statement.executeQuery();
			homework = resultSetExtractor(rs, layerLevel, connection);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return homework;
	}
	
	public ArrayList<Homework> retrieveAllByPersonNotStatus(int studentID, Status s, int layerLevel) {
		ArrayList<Homework> homework = null;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Homework, Person_Homework WHERE Person_Homework.Student_id=? AND Homework.homework_id = Person_Homework.homework_id AND Status!=?");
			statement.setInt(1, studentID);
			statement.setInt(2, s.getIndex());
			ResultSet rs = statement.executeQuery();
			homework = resultSetExtractor(rs, layerLevel, connection);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return homework;
	}

	@Override
	public boolean delete(Homework s) {
		System.out.println("Deleting NYI");
		return false;
	}
	
	private void addStudent(Connection connection, Homework h,Student s) throws SQLException {
		PreparedStatement statementKoppel = null;
		String sqlKoppel = "INSERT INTO PERSON_HOMEWORK(student_id,homework_id) VALUES (?,?)";
		statementKoppel = connection.prepareStatement(sqlKoppel);
				statementKoppel.setInt(1, s.getID());
				statementKoppel.setInt(2, h.getID());
		statementKoppel.executeUpdate();
		statementKoppel.close();
	}
	
	private void updateStudentHomework(Connection connection, Homework h,Student s) throws SQLException {
		PreparedStatement statementKoppel = null;
		String sqlKoppel = "UPDATE Person_HOMEWORK SET Status=?, currentQuestion=? WHERE Homework_id=? AND Student_id=?";
		statementKoppel = connection.prepareStatement(sqlKoppel);
				statementKoppel.setInt(1, h.getStatus().getIndex());
				statementKoppel.setInt(2, h.getCurrentQuestion());
				statementKoppel.setInt(3, h.getID());
				statementKoppel.setInt(4, s.getID());
		statementKoppel.executeUpdate();
		statementKoppel.close();
	}
	
	
	
	@Override
	public boolean add(Homework s) {
		boolean b = false;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {

			String sql;
			PreparedStatement statement;
				String generatedColumns[] = { "homework_id" };
				sql = "INSERT INTO Homework(homework_name, deadline,questions,course_id, teacher_id) VALUES (?,?,?,?,?)";
				statement = connection.prepareStatement(sql, generatedColumns);
				statement.setString(1, s.getName());
				statement.setString(2, s.getDeadline());
				statement.setInt(3, s.getNumberQuestions());
				statement.setInt(4, s.getCourse().getID());
				statement.setInt(5, s.getTeacher().getID());
				statement.executeUpdate();
				
				int ID = -1;
				ResultSet rsid = statement.getGeneratedKeys();
				if (rsid != null && rsid.next()) {
					ID = rsid.getInt(1);
					s.setID(ID);
				}
			statement.close();
			System.out.println("Student size: "+s.getStudents().size());
			if(s.getStudents() != null && s.getStudents().size() > 0){
				for(Student st : s.getStudents()){
					addStudent(connection, s, st);
					updateStudentHomework(connection, s, st);
				}
			}
			b = true;
			connection.commit();
			connection.close();
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println("Unique constraint error");
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public boolean update(Homework s) {
		boolean b = false;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			connection.setAutoCommit(false);
			PreparedStatement statement = connection.prepareStatement("UPDATE HOMEWORK SET Homework_Name=?, Deadline=?, Questions=?, course_id=? WHERE Homework_id=?");
			statement.setString(1, s.getName());
			statement.setString(2, s.getDeadline());
			statement.setInt(3, s.getNumberQuestions());
			statement.setInt(4, s.getCourse().getID());
			statement.setInt(5, s.getID());
			statement.executeUpdate();
			statement.close();
			updatePersonHomework(s, connection);
			if(s.getStudents().size() > 0){
				for(Student st : s.getStudents()){
					updateStudentHomework(connection, s, st);
				}
			}

			connection.commit();
			
			b = true;
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return b;
	}
	
	private boolean updatePersonHomework(Homework s, Connection c) {
		boolean b = false;
		Connection connection = c;
		try {
			connection.setAutoCommit(false);			
			PreparedStatement statement = connection.prepareStatement("UPDATE PERSON_HOMEWORK SET Status=?, CurrentQuestion=?, grade=? WHERE Homework_id=? AND Student_id=?");
			statement.setInt(1, s.getStatus().getIndex());
			statement.setInt(2, s.getCurrentQuestion());
			statement.setDouble(3, s.getCijfer());
			statement.setInt(4, s.getID());
			statement.setInt(5, s.getStudent().getID());
			statement.executeUpdate();
			statement.close();
			if(s.getStudents().size() > 0){
				for(Student st : s.getStudents()){
					updateStudentHomework(connection, s, st);
				}
			}

			connection.commit();
			connection.close();
			b = true;
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return b;
	}


	private ArrayList<Homework> resultSetExtractor(ResultSet rs, int layerLevel, Connection connection) {
		ArrayList<Homework> extractedStudents = new ArrayList<Homework>();
		
		try {
			while (rs.next()) {
					Homework c;
					c = new Homework();
					c.setID(rs.getInt("homework_id"));
					c.setName(rs.getString("homework_name"));
					c.setDeadline(rs.getString("deadline"));
					c.setNumberQuestions(rs.getInt("questions"));
					ResultSetMetaData md = rs.getMetaData();
					boolean b = false;
					for(int i = 1; i<md.getColumnCount(); i++){
						if (md.getColumnName(i).equals("student_id")){
							b = true;
							break;
						}
					}
					PersonDAO dao = new PersonDAO();
					if(b){
						c.setCurrentQuestion(rs.getInt("currentQuestion"));
						c.setStatus(Status.getValue(rs.getInt("status")));
						c.setCijfer(rs.getInt("Grade"));
						if(rs.getInt("student_id") > 0){
							Person p = dao.retrieve(rs.getInt("student_id"),1,connection);
							if (p.getRole() == UserRole.Student)
								c.setStudent(dao.retrieve(rs.getInt("student_id"), 1,connection));
							else
								System.out.println("ERROR_HOMEWORK-DAO::: Getting homework from someone who's not a student, ID: " + p.getID());
						}
					}
					c.setCourse((new CourseDAO()).retrieveByID(rs.getInt("course_id"), 2,connection));
					
					
					//c.setTeacher(dao.retrieveTeacherByHomework(c, 1));
					//Person p = dao.retrieve(rs.getInt("student_id"),1);
					Person t = dao.retrieve(rs.getInt("teacher_id"), 1,connection);
					c.setTeacher(t);
					
					/*if (p.getRole() == UserRole.Teacher){
						c.setTeacher(p);
					}
					else if (p.getRole() == UserRole.Student)
						c.setStudent(dao.retrieve(rs.getInt("student_id"), 1));
					else
						System.out.println("ERROR_HOMEWORK-DAO::: Getting homework from someone who's not a teacher nor a student, ID: " + p.getID());
					if(c.getTeacher() == null)
						c.setTeacher(dao.retrieveTeacherByHomework(c, 1));
					//u.setLayerLevel(layerLevel);*/

					if (layerLevel > 1) { //questions
						QuestionDAO qDAO = new QuestionDAO();
						c.setQuestions(qDAO.retrieveAllByHomework(c.getID(), 2));
					}

					if (layerLevel > 2) {
						
					}

					//cacheStudents.put(u.getStudentid(), u);
					extractedStudents.add(c);
				}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return extractedStudents;
	}

	@Override
	public List<Homework> retrieveAll() {
		return retrieveAll(4);
	}

	@Override
	public Homework retrieve(String s) {
		System.out.println("ERROR NOT WORKING METHOD RETRIEVE(string)");
		return null;//retrieve(s, 10);
	}
}
