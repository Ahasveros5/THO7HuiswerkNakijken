package huiswerknakijken.hu.Util;

import java.sql.Connection;

public class OracleConnectionPool {
	private static OracleConnectionPool instance = new OracleConnectionPool();

	private OracleConnectionPool() {

	}

	private Connection createConnection() {
		Connection connection = null;
		
		try {
			
			connection = OracleConnection.getConnection(); 
		} catch (Exception e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		}
		return connection;
	}

	public static Connection getConnection() {
		//Thread.dumpStack();
		return instance.createConnection();
		
	}
}