package huiswerknakijken.hu.DAO;

import huiswerknakijken.hu.Domain.Homework;
import huiswerknakijken.hu.Util.OracleConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Homework");
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
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Homework WHERE homework_name=?");
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
	
	public ArrayList<Homework> retrieveAllByName(String name, int layerLevel) {
		ArrayList<Homework> homework = null;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Homework WHERE homework_name=?");
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

	@Override
	public boolean delete(Homework s) {
		System.out.println("Deleting NYI");
		return false;
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
				sql = "INSERT INTO Homework(homework_name) VALUES (?)";
				statement = connection.prepareStatement(sql, generatedColumns);
				statement.setString(1, s.getName());
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
	public boolean update(Homework s) {
		boolean b = false;
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
					//u.setLayerLevel(layerLevel);

					if (layerLevel > 1) { //questions
						QuestionDAO qDAO = new QuestionDAO();
						c.setQuestion(qDAO.retrieveAllByHomework(c.getID(), 1));
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
