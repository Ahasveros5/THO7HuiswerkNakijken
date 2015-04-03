package huiswerknakijken.hu.DAO;

import huiswerknakijken.hu.Domain.Student;
import huiswerknakijken.hu.util.OracleConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements DAOInterface<Student> {
	public List<Student> retrieveAll(int layerLevel) {
		ResultSet rs = null;
		ArrayList<Student> Students = null;
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
	
	public List<Student> retrieveAllMatching(String email, String firstName, String lastName, int layerLevel) {
		ResultSet rs = null;
		ArrayList<Student> Students = null;
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
	public boolean delete(Student s) {
		System.out.println("Deleting NYI");
		return false;
	}

	@Override
	public boolean add(Student s) {
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
				sql = "INSERT INTO Person(first_name, last_name, email, password) VALUES (?,?,?,?)";
				statement = connection.prepareStatement(sql, generatedColumns);
				System.out.println("ATTENTION:: While adding student generating new ID.");
			}
			else{
				sql = "INSERT INTO Person(first_name, last_name, email, password,id) VALUES (?,?,?,?,?)";
				statement = connection.prepareStatement(sql);
				statement.setInt(5, s.getID());
			}
			statement.setString(1, s.getFirstName());
			statement.setString(2, s.getLastName());
			statement.setString(3, s.getEmail());
			statement.setString(4, s.getPassword());
			statement.executeUpdate();
			int ID = -1;

			ResultSet rsid = statement.getGeneratedKeys();
			if (rsid != null && rsid.next()) {
				ID = rsid.getInt(1);
				s.setID(ID);
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
	public boolean update(Student s) {
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


	public Student retrieve(int id, int layerLevel) {
		Student retrievedStudent = new Student();
		Connection connection = OracleConnectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Person WHERE id = ?");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			ArrayList<Student> Student = resultSetExtractor(rs, layerLevel, connection);
			retrievedStudent = Student.get(0);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retrievedStudent;

	}
	
	public Student retrieve(int id, int layerLevel, Connection connection) {
		Student retrievedStudent = new Student();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Person WHERE id=?");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			ArrayList<Student> Student = resultSetExtractor(rs, layerLevel, connection);
			retrievedStudent = Student.get(0);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retrievedStudent;

	}

	public Student retrieveByEmail(String s, int layerLevel) {
		Student retrievedStudent = null;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM PERSON WHERE email=?");
			statement.setString(1, s);
			ResultSet rs = statement.executeQuery();
			ArrayList<Student> eU = resultSetExtractor(rs, layerLevel, connection);
			if (eU.size() > 0)
				retrievedStudent = eU.get(0);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retrievedStudent;
	}

	/*public Student retrieveByStudentname(String s, int layerLevel) {
		Student retrievedStudent = null;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Students WHERE lower(Studentname)=?");
			statement.setString(1, s.toLowerCase());
			ResultSet rs = statement.executeQuery();
			ArrayList<Student> eU = resultSetExtractor(rs, layerLevel, connection);
			if (eU.size() > 0)
				retrievedStudent = eU.get(0);
			statement.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retrievedStudent;
	}*/

	private ArrayList<Student> resultSetExtractor(ResultSet rs, int layerLevel, Connection connection) {
		ArrayList<Student> extractedStudents = new ArrayList<Student>();
		
		try {
			while (rs.next()) {
				boolean notInCache = true;
				int ID = rs.getInt("id");
				/*if (cacheStudents.containsKey(StudentID)) {
					Student cacheStudent = cacheStudents.get(StudentID);
					if (cacheStudent.getLayerLevel() >= layerLevel) {
						extractedStudents.add(cacheStudents.get(StudentID));
						notInCache = false;
					}
				}*/
				if (notInCache) {
					Student u = new Student();
					u.setID(ID);
					u.setFirstName(rs.getString("first_name"));
					u.setLastName(rs.getString("last_name"));
					u.setEmail(rs.getString("email"));
					u.setPassword(rs.getString("password"));
					//u.setLayerLevel(layerLevel);

					if (layerLevel > 1) {
						/*AddressDAO adao = new AddressDAO();
						String sqlKoppel;
						PreparedStatement statementKoppel;
						sqlKoppel = "SELECT * FROM Student_ADDRESSES WHERE Student_id = ? AND date_to IS NULL";
						statementKoppel = connection.prepareStatement(sqlKoppel);
						statementKoppel.setInt(1, u.getStudentid());
						ResultSet addressStudent = statementKoppel.executeQuery();
						while (addressStudent.next()) {
							int addressInt = addressStudent.getInt("address_id");
							int addressType = addressStudent.getInt("address_type_code");
							Address a = adao.retrieveByID(addressInt);
							for (AddressType at : AddressType.values()) {
								if (at.index() == addressType) {
									a.setAddressType(at);
									break;
								}
							}
							u.getAddresses().add(a);
						}
						statementKoppel.close();*/
					}

					if (layerLevel > 2) {
						
					}

					//cacheStudents.put(u.getStudentid(), u);
					extractedStudents.add(u);
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
	public List<Student> retrieveAll() {
		return retrieveAll(4);
	}

	@Override
	public Student retrieve(String s) {
		System.out.println("ERROR NOT WORKING METHOD RETRIEVE(string)");
		return null;//retrieve(s, 10);
	}
}
