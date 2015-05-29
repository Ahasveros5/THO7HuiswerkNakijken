package huiswerknakijken.hu.DAO;

import huiswerknakijken.hu.Domain.Homework;
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
	
	public List<Person> retrieveAllByRole(int role, int layerLevel) {
		ResultSet rs = null;
		ArrayList<Person> Students = null;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Person WHERE Role=?");
			statement.setInt(1, role);
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
			/*if(s.getID() == -1){
				
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
			else{*/
				/*if(s.getRole() == UserRole.Student){
						sql = "INSERT INTO Person(first_name, last_name, email, password, role, id,class_id) VALUES (?,?,?,?,?,?,?)";
						if (s.getID() == -1){
							String generatedColumns[] = { "id" };
							statement = connection.prepareStatement(sql,generatedColumns);
						} else
							statement = connection.prepareStatement(sql);
						System.out.println("testo");
						statement.setInt(6, s.getID());

					Student st = s.toStudent();
					if(st.getMainClass() != null){
						System.out.println("Adding class id: " + st.getMainClass().getClassID());
						statement.setInt(7,st.getMainClass().getClassID());}
					else{
						statement.setInt(7,0);
						System.out.println("Testo2");
					}
				} else{
					*/
			sql = "INSERT INTO Person(first_name, last_name, email, password, role, id) VALUES (?,?,?,?,?,?)";
			if (s.getID() == -1){
				String generatedColumns[] = { "id" };
				statement = connection.prepareStatement(sql,generatedColumns);
			} else
				statement = connection.prepareStatement(sql);
			statement.setInt(6, s.getID());
			System.out.println("Teacher id is wel bestaand");
				//}
			//}
			statement.setString(1, s.getFirstName());
			statement.setString(2, s.getLastName());
			statement.setString(3, s.getEmail());
			statement.setString(4, s.getPassword());
			//if (s.getRole() != null)
				statement.setInt(5, s.getRole().getIndex());
			//else{
			//	statement.setInt(5, UserRole.Unknown.getIndex());
			//}
				s.print();
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
	
	public boolean addStudent(Student s) {
		boolean b = false;
		Connection connection = OracleConnectionPool.getConnection();
		if(s.getMainClass() == null)
			System.out.println("GEEN MAIN CLASS BITCHEZZZ");
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement statement = null;
		try {

			String sql;
			
			if(s.getRole() == UserRole.Student){
					sql = "INSERT INTO Person(first_name, last_name, email, password, role, id,class_id) VALUES (?,?,?,?,?,?,?)";
					if (s.getID() == -1){
						String generatedColumns[] = { "id" };
						statement = connection.prepareStatement(sql,generatedColumns);
					} else
						statement = connection.prepareStatement(sql);
					System.out.println("testo");
					statement.setInt(6, s.getID());

				if(s.getMainClass() != null){
					System.out.println("Adding class id: " + s.getMainClass().getClassID());
					statement.setInt(7,s.getMainClass().getClassID());}
				else{
					statement.setInt(7,0);
					System.out.println("Testo2");
				}
			}
			statement.setString(1, s.getFirstName());
			statement.setString(2, s.getLastName());
			statement.setString(3, s.getEmail());
			statement.setString(4, s.getPassword());
			//if (s.getRole() != null)
				statement.setInt(5, s.getRole().getIndex());
			//else{
			//	statement.setInt(5, UserRole.Unknown.getIndex());
			//}
				s.print();
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
			System.out.println("In update, id: " + s.getID());
			System.out.println("In update, pass: " + s.getPassword());
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
	
	public Person retrieveTeacherByHomework(Homework h, int layerLevel) {
		Person retrievedStudent = null;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			System.out.println("ID: " +  h.getID());
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Person, Person_Homework WHERE PERSON_HOMEWORK.homework_id = ? AND PERSON.role = 2 AND PERSON.id = PERSON_HOMEWORK.student_id");
			statement.setInt(1, h.getID());
			ResultSet rs = statement.executeQuery();
			System.out.println("adding teacher");
			ArrayList<Person> Person = resultSetExtractor(rs, layerLevel, connection);
			if(Person.size() > 0)
				retrievedStudent = Person.get(0);
			else
				System.out.println("nulllaksdjfkjas");
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
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM PERSON WHERE LOWER(email)=LOWER(?)");
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
					System.out.println("creating person");
					int role = rs.getInt("role");
					if(role == 1){//student
						System.out.println("STUDENT CREATED");
						p = new Student();
						p.setID(ID);
						p.setFirstName(rs.getString("first_name"));
						p.setLastName(rs.getString("last_name"));
						p.setEmail(rs.getString("email"));
						String s = rs.getString("password");
						if (s == null)
							s = "";
						p.setPassword(s);
						p.setRole(UserRole.Student);
					} else if (role == 2){//teacher
						System.out.println("TEACHER CREATED");
						p = new Teacher();
						p.setID(ID);
						p.setFirstName(rs.getString("first_name"));
						p.setLastName(rs.getString("last_name"));
						p.setEmail(rs.getString("email"));
						p.setPassword(rs.getString("password"));
						p.setRole(UserRole.Teacher);
					} else{ //no teacher or student, so it's someone not belonging to HU
						System.out.println("PERSON CREATED");
						p = new Person();
						p.setID(ID);
						p.setFirstName(rs.getString("first_name"));
						p.setLastName(rs.getString("last_name"));
						p.setEmail(rs.getString("email"));
						p.setPassword(rs.getString("password"));
						p.setRole(UserRole.Unknown);
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
