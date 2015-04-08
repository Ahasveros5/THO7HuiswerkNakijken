package huiswerknakijken.hu.DAO;

import huiswerknakijken.hu.Domain.Class;
import huiswerknakijken.hu.Util.OracleConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class ClassDAO implements DAOInterface<Class> {
	public List<Class> retrieveAll(int layerLevel) {
		ResultSet rs = null;
		ArrayList<Class> classes = null;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLASS");
			rs = statement.executeQuery();
			classes = resultSetExtractor(rs, layerLevel, connection);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return classes;
	}
	
	public Class retrieveByName(String name, int layerLevel) {
		Class retrievedClass = new Class();
		Connection connection = OracleConnectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLASS WHERE class_name=?");
			statement.setString(1, name);
			ResultSet rs = statement.executeQuery();
			ArrayList<Class> Class = resultSetExtractor(rs, layerLevel, connection);
			retrievedClass = Class.get(0);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retrievedClass;

	}

	@Override
	public boolean delete(Class s) {
		System.out.println("Deleting NYI");
		return false;
	}

	@Override
	public boolean add(Class s) {
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
				String generatedColumns[] = { "class_id" };
				sql = "INSERT INTO Class(class_name) VALUES (?)";
				statement = connection.prepareStatement(sql, generatedColumns);
				statement.setString(1, s.getName());
				statement.executeUpdate();
				int ID = -1;
				ResultSet rsid = statement.getGeneratedKeys();
				if (rsid != null && rsid.next()) {
					ID = rsid.getInt(1);
					s.setClassID(ID);
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
	public boolean update(Class s) {
		boolean b = false;
		return b;
	}


	private ArrayList<Class> resultSetExtractor(ResultSet rs, int layerLevel, Connection connection) {
		ArrayList<Class> extractedStudents = new ArrayList<Class>();
		
		try {
			while (rs.next()) {
				boolean notInCache = true;
				/*if (cacheStudents.containsKey(StudentID)) {
					Class cacheStudent = cacheStudents.get(StudentID);
					if (cacheStudent.getLayerLevel() >= layerLevel) {
						extractedStudents.add(cacheStudents.get(StudentID));
						notInCache = false;
					}
				}*/
				if (notInCache) {
					Class c;
					c = new Class();
					c.setClassID(rs.getInt("class_id"));
					c.setName(rs.getString("class_name"));
					//u.setLayerLevel(layerLevel);

					if (layerLevel > 1) { //students to class
						PersonDAO pDAO = new PersonDAO();
						c.setStudents(pDAO.retrieveAllByClass(c.getClassID(), 0));
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
	public List<Class> retrieveAll() {
		return retrieveAll(4);
	}

	@Override
	public Class retrieve(String s) {
		System.out.println("ERROR NOT WORKING METHOD RETRIEVE(string)");
		return null;//retrieve(s, 10);
	}
}
