package huiswerknakijken.hu.DAO;

import huiswerknakijken.hu.Domain.Course;
import huiswerknakijken.hu.Domain.Person;
import huiswerknakijken.hu.Util.OracleConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CourseDAO implements DAOInterface<Course> {
	public List<Course> retrieveAll(int layerLevel) {
		ResultSet rs = null;
		ArrayList<Course> courses = null;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM COURSE");
			rs = statement.executeQuery();
			courses = resultSetExtractor(rs, layerLevel, connection);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courses;
	}
	
	public Course retrieveByName(String name, int layerLevel) {
		Course retrievedCourse = null;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Course WHERE course_name=?");
			statement.setString(1, name);
			ResultSet rs = statement.executeQuery();
			ArrayList<Course> Class = resultSetExtractor(rs, layerLevel, connection);
			if(Class.size() == 0){
				retrievedCourse = null;
			} else{
				retrievedCourse = Class.get(0);
			}
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retrievedCourse;

	}
	
	public Course retrieveByID(int id, int layerLevel, Connection con) {
		Course retrievedCourse = null;
		Connection connection = con;
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Course WHERE course_id=?");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			ArrayList<Course> Class = resultSetExtractor(rs, layerLevel, connection);
			if(Class.size() == 0){
				retrievedCourse = null;
			} else{
				retrievedCourse = Class.get(0);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retrievedCourse;

	}
	
	public ArrayList<Course> retrieveAllByPerson(int pid, int layerLevel) {
		ArrayList<Course> retrievedCourse = null;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Course, Person_Course WHERE Person_Course.person_id=? AND Person_Course.course_id = Course.course_id");
			statement.setInt(1, pid);
			ResultSet rs = statement.executeQuery();
			retrievedCourse = resultSetExtractor(rs, layerLevel, connection);
			
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retrievedCourse;

	}
	
	public boolean removePerson(Person p, Course s, Connection connection){
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {

			String sql;
			PreparedStatement statement = null;
				sql = "DELETE FROM PERSON_COURSE WHERE course_id=? AND person_id=?";
				statement = connection.prepareStatement(sql);
				statement.setInt(1, s.getID());
				statement.setInt(2, p.getID());
				statement.executeUpdate();
			statement.close();
			connection.commit();
		
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
		
		return true;
	}
	
	public boolean removePerson(Person p, Course s){
		Connection connection = OracleConnectionPool.getConnection();
		boolean b = removePerson(p,s,connection);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public boolean delete(Course s) {
		System.out.println("Deleting NYI");
		return false;
	}

	@Override
	public boolean add(Course s) {
		boolean b = false;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {

			String sql;
			PreparedStatement statement = null;
			System.out.println("adding course");
				String generatedColumns[] = { "course_id" };
				sql = "INSERT INTO COURSE(course_name) VALUES (?)";
				statement = connection.prepareStatement(sql, generatedColumns);
				statement.setString(1, s.getName());
				statement.executeUpdate();
				System.out.println("temp");
					int ID = -1;
					ResultSet rsid = statement.getGeneratedKeys();
					if (rsid != null && rsid.next()) {
						ID = rsid.getInt(1);
						s.setID(ID);
					}
			statement.close();
			
			System.out.println("COURSEID: " + s.getID());
			for (Person p : s.getStudents()){
					addPerson(p, s, connection);
			}
			for (Person p : s.getTeachers()){
					addPerson(p,s,connection);
			}
			
			connection.commit();
			connection.close();


			b = true;
			System.out.println("added");
			
			
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
	
	public void addPerson(Person p, Course c, Connection con) throws SQLException{
		PreparedStatement statementKoppel = null;
		String sqlKoppel = "INSERT INTO PERSON_COURSE(person_id,course_id) VALUES (?,?)";
		statementKoppel = con.prepareStatement(sqlKoppel);
				statementKoppel.setInt(1, p.getID());
				statementKoppel.setInt(2, c.getID());
		statementKoppel.executeUpdate();
		statementKoppel.close();
	}

	@Override
	public boolean update(Course s) {
		boolean b = false;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			connection.setAutoCommit(false);
			PersonDAO dao = new PersonDAO();
/*			System.out.println("all before: \n"+s.getStudents().toString());
			
			System.out.println("temp: \n"+ temp.toString());
			s.getStudents().removeAll(temp);
			System.out.println("all after: \n"+s.getStudents().toString());
			*/
			List<Person> temp = dao.retrieveStudentsByCourse(s.getID(), 1,connection);
			for (Iterator<Person> it=s.getStudents().iterator(); it.hasNext();) {
				for(Person p : temp)
					if (it.next().getID() == p.getID())
						it.remove(); // NOTE: Iterator's remove method, not ArrayList's, is used.
			}
			for(Person p : s.getStudents()){
				System.out.println("Person: " + p.getID() + "|||| COURSE: " + s.getID());
				addPerson(p,s,connection);
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


	private ArrayList<Course> resultSetExtractor(ResultSet rs, int layerLevel, Connection connection) {
		ArrayList<Course> extractedStudents = new ArrayList<Course>();
		
		try {
			while (rs.next()) {
				boolean notInCache = true;
				if (notInCache) {
					Course c;
					c = new Course();
					c.setID(rs.getInt("course_id"));
					c.setName(rs.getString("course_name"));
					//u.setLayerLevel(layerLevel);

					if (layerLevel > 1) { //students to course
						PersonDAO pDAO = new PersonDAO();
						c.setStudents(pDAO.retrieveStudentsByCourse(c.getID(), 0, connection));
						c.setTeachers(pDAO.retrieveTeachersByCourse(c.getID(), 0, connection));
					}

					if (layerLevel > 2) {
						
					}

					//cacheStudents.put(u.getStudentid(), u);
					extractedStudents.add(c);
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return extractedStudents;
	}

	@Override
	public List<Course> retrieveAll() {
		return retrieveAll(4);
	}

	@Override
	public Course retrieve(String s) {
		System.out.println("ERROR NOT WORKING METHOD RETRIEVE(string)");
		return null;//retrieve(s, 10);
	}
}
