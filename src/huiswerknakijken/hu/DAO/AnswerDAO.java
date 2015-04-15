package huiswerknakijken.hu.DAO;

import huiswerknakijken.hu.Domain.Answer;
import huiswerknakijken.hu.Domain.Answer.Correct;
import huiswerknakijken.hu.Util.OracleConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class AnswerDAO implements DAOInterface<Answer> {
	public List<Answer> retrieveAll(int layerLevel) {
		ResultSet rs = null;
		ArrayList<Answer> classes = null;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Answer");
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
	
	public Answer retrieveByQuestion(int id, int layerLevel) {
		Answer retrievedClass = null;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Answer WHERE question_id=?");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			ArrayList<Answer> Answer = resultSetExtractor(rs, layerLevel, connection);
			retrievedClass = Answer.get(0);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retrievedClass;

	}
	
	public Answer retrieveCorrectByQuestion(int id, int layerLevel) {
		Answer retrievedAnswer = null;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Answer WHERE question_id=? AND correct = 1");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			ArrayList<Answer> Answer = resultSetExtractor(rs, layerLevel, connection);
			retrievedAnswer = Answer.get(0);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retrievedAnswer;

	}
	
	public ArrayList<Answer> retrieveAllByQuestion(int id, int layerLevel) {
		ArrayList<Answer> Answer = null;
		Connection connection = OracleConnectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Answer WHERE question_id=?");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			Answer = resultSetExtractor(rs, layerLevel, connection);
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Answer;

	}
	
	

	@Override
	public boolean delete(Answer s) {
		System.out.println("Deleting NYI");
		return false;
	}

	@Override
	public boolean add(Answer s) {
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
				String generatedColumns[] = { "Answer_id" };
				sql = "INSERT INTO Answer(answer, correct, question_id) VALUES (?,?,?)";
				statement = connection.prepareStatement(sql, generatedColumns);
				statement.setString(1, s.getAnswer());
				if (s.getCorrect() == null)
					s.setCorrect(Correct.False);
				statement.setInt(2, s.getCorrect().getIndex());
				statement.setInt(3,s.getQuestion().getID());
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
	public boolean update(Answer s) {
		boolean b = false;
		return b;
	}


	private ArrayList<Answer> resultSetExtractor(ResultSet rs, int layerLevel, Connection connection) {
		ArrayList<Answer> extractedStudents = new ArrayList<Answer>();
		
		try {
			while (rs.next()) {
					Answer c;
					c = new Answer();
					c.setID(rs.getInt("Answer_id"));
					c.setAnswer(rs.getString("answer"));
					c.setCorrect(Correct.getValue(rs.getInt("correct")));
					//u.setLayerLevel(layerLevel);

					if (layerLevel > 1) { 
						
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
	public List<Answer> retrieveAll() {
		return retrieveAll(4);
	}

	@Override
	public Answer retrieve(String s) {
		System.out.println("ERROR NOT WORKING METHOD RETRIEVE(string)");
		return null;//retrieve(s, 10);
	}
}
