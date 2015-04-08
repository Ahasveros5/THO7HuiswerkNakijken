package huiswerknakijken.hu.DAO;

import huiswerknakijken.hu.Domain.Person;
import huiswerknakijken.hu.Domain.Person.UserRole;
import huiswerknakijken.hu.Domain.Student;
import huiswerknakijken.hu.Domain.Teacher;
import huiswerknakijken.hu.Util.OracleConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;

public class PersonDAO implements DAOInterface<Person> {
	public List<Person> retrieveAll(int layerLevel) {
		ResultSet rs = null;
		ArrayList<Person> Students = null;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Person");
			rs = statement.executeQuery();
			Students = resultSetExtractor(rs, layerLevel, connection);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Students;
	}
	
	public List<Person> retrieveAllMatching(String email, String firstName, String lastName, int layerLevel) {
		ResultSet rs = null;
		ArrayList<Person> Students = null;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			PreparedStatement statement = null;
			statement = connection.prepareStatement("SELECT * FROM Person WHERE lower(email) LIKE lower(?) AND lower(first_name) LIKE lower(?) AND lower(last_name) LIKE lower(?)");

			statement.setString(2, "%"+email+"%");
			statement.setString(3, "%"+firstName+"%");
			statement.setString(4, "%"+lastName+"%");
			
			rs = statement.executeQuery();
			Students = resultSetExtractor(rs, layerLevel, connection);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Students;
	}

	@Override
	public boolean delete(Person s) {
		System.out.println("Deleting NYI");
		return false;
	}

	@Override
	public boolean add(Person s) {
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
			if(s.getID() == -1){
				String generatedColumns[] = { "Person_ID" };
				if(s.getRole() == UserRole.Student){
					sql = "INSERT INTO Person(first_name, last_name, email, password, role, class_id) VALUES (?,?,?,?,?,?)";
					statement = connection.prepareStatement(sql, generatedColumns);
					Student st = (Student)s;
					statement.setInt(6,st.getMainClass().getClassID());
				} else{
					sql = "INSERT INTO Person(first_name, last_name, email, password, role) VALUES (?,?,?,?,?)";
					statement = connection.prepareStatement(sql, generatedColumns);
				}
				System.out.println("ATTENTION:: While adding Person generating new ID.");
			}
			else{
				if(s.getRole() == UserRole.Student){
					sql = "INSERT INTO Person(first_name, last_name, email, password, role, id,class_id) VALUES (?,?,?,?,?,?,?)";
					statement = connection.prepareStatement(sql);
					statement.setInt(6, s.getID());
					Student st = s.toStudent();
					if(st.getMainClass() != null)
						statement.setInt(7,st.getMainClass().getClassID());
					else
						statement.setInt(7,0);
				} else{
					sql = "INSERT INTO Person(first_name, last_name, email, password, role, id) VALUES (?,?,?,?,?,?)";
					statement = connection.prepareStatement(sql);
					statement.setInt(6, s.getID());
				}
			}
			statement.setString(1, s.getFirstName());
			statement.setString(2, s.getLastName());
			statement.setString(3, s.getEmail());
			statement.setString(4, s.getPassword());
			if (s.getRole() != null)
				statement.setInt(5, s.getRole().getIndex());
			else{
				statement.setInt(5, UserRole.Unknown.getIndex());
			}
			statement.executeUpdate();
			if(s.getID() == -1){
				int ID = -1;
	
				ResultSet rsid = statement.getGeneratedKeys();
				if (rsid != null && rsid.next()) {
					ID = rsid.getInt(1);
					s.setID(ID);
				}
			}
			
			statement.close();


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
	public boolean update(Person s) {
		boolean b = false;
		Connection connection = OracleConnectionPool.getConnection();
		//Service.getService().getStudents().put(u.getStudentid(), u);
		try {
			connection.setAutoCommit(false);
			PreparedStatement statement = connection.prepareStatement("UPDATE PERSON SET first_name=?, last_name=?, email=?, password=? WHERE id=?");
			statement.setString(1, s.getFirstName());
			statement.setString(2, s.getLastName());
			statement.setString(3, s.getEmail());
			statement.setString(4, s.getPassword());
			statement.setInt(5, s.getID());
			statement.executeUpdate();
			statement.close();


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


	public Person retrieve(int id, int layerLevel) {
		Person retrievedStudent = null;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Person WHERE id = ?");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			ArrayList<Person> Person = resultSetExtractor(rs, layerLevel, connection);
			retrievedStudent = Person.get(0);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retrievedStudent;

	}
	
	public Person retrieve(int id, int layerLevel, Connection connection) {
		Person retrievedStudent = null;
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Person WHERE id=?");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			ArrayList<Person> Person = resultSetExtractor(rs, layerLevel, connection);
			retrievedStudent = Person.get(0);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retrievedStudent;

	}

	public Person retrieveByEmail(String s, int layerLevel) {
		Person retrievedStudent = null;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM PERSON WHERE email=?");
			statement.setString(1, s);
			ResultSet rs = statement.executeQuery();
			ArrayList<Person> eU = resultSetExtractor(rs, layerLevel, connection);
			if (eU.size() > 0)
				retrievedStudent = eU.get(0);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retrievedStudent;
	}
<<<<<<< HEAD:src/huiswerknakijken/hu/DAO/PersonDAO.java
=======
	
	public ArrayList<Person> retrieveAllByClass(int classID, int layerLevel) {
		Connection connection = OracleConnectionPool.getConnection();
		ArrayList<Person> eU = null;
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM PERSON WHERE class_id=?");
			statement.setInt(1, classID);
			ResultSet rs = statement.executeQuery();
			eU = resultSetExtractor(rs, layerLevel, connection);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return eU;
	}
>>>>>>> origin/master:src/huiswerknakijken/hu/DAO/PersonDAO.java

	/*public Person retrieveByStudentname(String s, int layerLevel) {
		Person retrievedStudent = null;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Students WHERE lower(Studentname)=?");
			statement.setString(1, s.toLowerCase());
			ResultSet rs = statement.executeQuery();
			ArrayList<Person> eU = resultSetExtractor(rs, layerLevel, connection);
			if (eU.size() > 0)
				retrievedStudent = eU.get(0);
			statement.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retrievedStudent;
	}*/

	private ArrayList<Person> resultSetExtractor(ResultSet rs, int layerLevel, Connection connection) {
		ArrayList<Person> extractedStudents = new ArrayList<Person>();
		
		try {
			while (rs.next()) {
				boolean notInCache = true;
				int ID = rs.getInt("id");
				/*if (cacheStudents.containsKey(StudentID)) {
					Person cacheStudent = cacheStudents.get(StudentID);
					if (cacheStudent.getLayerLevel() >= layerLevel) {
						extractedStudents.add(cacheStudents.get(StudentID));
						notInCache = false;
					}
				}*/
				if (notInCache) {
					Person p;
					int role = rs.getInt("role");
					if(role == 1){//student
						p = new Student();
						p.setID(ID);
						p.setFirstName(rs.getString("first_name"));
						p.setLastName(rs.getString("last_name"));
						p.setEmail(rs.getString("email"));
						p.setPassword(rs.getString("password"));
					} else if (role == 2){//teacher
						p = new Teacher();
						p.setID(ID);
						p.setFirstName(rs.getString("first_name"));
						p.setLastName(rs.getString("last_name"));
						p.setEmail(rs.getString("email"));
						p.setPassword(rs.getString("password"));
					} else{ //no teacher or student, so it's someone not belonging to HU
						p = new Person();
						p.setID(ID);
						p.setFirstName(rs.getString("first_name"));
						p.setLastName(rs.getString("last_name"));
						p.setEmail(rs.getString("email"));
						p.setPassword(rs.getString("password"));
					}
					//u.setLayerLevel(layerLevel);

					if (layerLevel > 1) {
						
					}

					if (layerLevel > 2) {
						
					}

					//cacheStudents.put(u.getStudentid(), u);
					extractedStudents.add(p);
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
	public List<Person> retrieveAll() {
		return retrieveAll(4);
	}

	@Override
	public Person retrieve(String s) {
		System.out.println("ERROR NOT WORKING METHOD RETRIEVE(string)");
		return null;//retrieve(s, 10);
	}
}
