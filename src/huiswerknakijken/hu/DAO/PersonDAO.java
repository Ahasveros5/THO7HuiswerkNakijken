package huiswerknakijken.hu.DAO;

import huiswerknakijken.hu.Domain.Homework;
import huiswerknakijken.hu.Domain.Person;
import huiswerknakijken.hu.Domain.Person.UserRole;
import huiswerknakijken.hu.Domain.Student;
import huiswerknakijken.hu.Domain.Teacher;
import huiswerknakijken.hu.Util.OracleConnectionPool;
import huiswerknakijken.hu.Util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
	
	public List<Person> retrieveAllMatching(String keyword, int layerLevel){
		Connection con = OracleConnectionPool.getConnection();
		List<Person> ps = retrieveAllMatching(keyword, layerLevel, con);
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ps;
	}
	
	public List<Person> retrieveAllMatching(String keyword, int layerLevel, Connection con) {
		ResultSet rs = null;
		ArrayList<Person> users = null;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			PreparedStatement statement = null;
			
			String[] keywords = keyword.split(" ");
			if (keywords.length > 1){
				users = new ArrayList<Person>();
				for(String s : keywords)
					users.addAll(retrieveAllMatching(s,layerLevel,con));
				Set<Integer> ids = new HashSet<Integer>();
				for (Iterator<Person> it = users.iterator(); it.hasNext(); ) {
				    if (!ids.add(it.next().getID())) {
				        it.remove();
				    }
				}
			} else{
				if(!Util.isInteger(keyword)){
					keyword = "%" + keyword + "%";
					statement = connection.prepareStatement("SELECT * FROM person, C_class WHERE (lower(email) LIKE lower(?) OR lower(class_name) LIKE lower(?) OR lower(first_name) LIKE lower(?) OR lower(last_name) LIKE lower(?)) AND role=1 AND PERSON.class_id = C_Class.class_id");
					
					statement.setString(1, keyword);
					statement.setString(2, keyword);
					statement.setString(3, keyword);
					statement.setString(4, keyword);
				} else{
					statement = connection.prepareStatement("SELECT * FROM Person WHERE id=? AND role=1 ");
					statement.setInt(1, Integer.parseInt(keyword));
				}
			
			
				rs = statement.executeQuery();
				users = resultSetExtractor(rs, layerLevel, connection);
				statement.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
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
			sql = "INSERT INTO Person(first_name, last_name, email, password, role, id) VALUES (?,?,?,?,?,?)";
			if (s.getID() == -1){
				String generatedColumns[] = { "id" };
				statement = connection.prepareStatement(sql,generatedColumns);
			} else
				statement = connection.prepareStatement(sql);
			statement.setInt(6, s.getID());
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
					statement.setInt(6, s.getID());

				if(s.getMainClass() != null){
					statement.setInt(7,s.getMainClass().getClassID());}
				else{
					statement.setInt(7,0);
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
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Person, Person_Homework WHERE PERSON_HOMEWORK.homework_id = ? AND PERSON.role = 2 AND PERSON.id = PERSON_HOMEWORK.student_id");
			statement.setInt(1, h.getID());
			ResultSet rs = statement.executeQuery();
			ArrayList<Person> Person = resultSetExtractor(rs, layerLevel, connection);
			if(Person.size() > 0)
				retrievedStudent = Person.get(0);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retrievedStudent;

	}
	
	public ArrayList<Person> retrieveStudentsByHomework(int hid, int layerLevel) {
		ArrayList<Person> Persons = null;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Person, Person_Homework WHERE PERSON_HOMEWORK.homework_id = ? AND PERSON.role = 1 AND PERSON.id = PERSON_HOMEWORK.student_id");
			statement.setInt(1, hid);
			ResultSet rs = statement.executeQuery();
			Persons = resultSetExtractor(rs, layerLevel, connection);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Persons;

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
	
	public ArrayList<Person> retrieveAllByClass(int classID, int layerLevel, Connection con) {
		Connection connection = con;
		ArrayList<Person> eU = null;
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM PERSON WHERE class_id=?");
			statement.setInt(1, classID);
			ResultSet rs = statement.executeQuery();
			eU = resultSetExtractor(rs, layerLevel, connection);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return eU;
	}
	
	public ArrayList<Student> retrieveStudentsByCourse(int courseID, int layerLevel, Connection con) {
		Connection connection = con;
		ArrayList<Person> eU = null;
		ArrayList<Student> sts = null;
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM PERSON, PERSON_COURSE WHERE course_id=? AND role=1 AND PERSON.ID = PERSON_COURSE.PERSON_ID	");
			statement.setInt(1, courseID);
			ResultSet rs = statement.executeQuery();
			eU = resultSetExtractor(rs, layerLevel, connection);
			sts = new ArrayList<Student>();
			for (Person p : eU){
				sts.add(p.toStudent());
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sts;
	}
	
	public ArrayList<Person> retrieveTeachersByCourse(int classID, int layerLevel, Connection con) {
		Connection connection = con;
		ArrayList<Person> eU = null;
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM PERSON, PERSON_COURSE WHERE course_id=? AND role=2 AND PERSON.ID = PERSON_COURSE.PERSON_ID	");
			statement.setInt(1, classID);
			ResultSet rs = statement.executeQuery();
			eU = resultSetExtractor(rs, layerLevel, connection);
			statement.close();
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
					int role = rs.getInt("role");
					if(role == 1){//student
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
						p = new Teacher();
						p.setID(ID);
						p.setFirstName(rs.getString("first_name"));
						p.setLastName(rs.getString("last_name"));
						p.setEmail(rs.getString("email"));
						p.setPassword(rs.getString("password"));
						p.setRole(UserRole.Teacher);
					} else{ //no teacher or student, so it's someone not belonging to HU
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
