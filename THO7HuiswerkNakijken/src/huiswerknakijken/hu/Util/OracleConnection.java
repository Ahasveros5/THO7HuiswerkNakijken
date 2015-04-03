package huiswerknakijken.hu.Util;

import java.sql.Connection;
import java.sql.DriverManager;
 
public class OracleConnection {
	private static OracleConnection instance = new OracleConnection();
	private static final String url = "jdbc:oracle:thin:@ondora01.hu.nl:8521:cursus01";
	private static final String username = "THO_SOA_2015_TEAM1";
	private static final String password = "THO_SOA_2015_TEAM1";
	
	private static final String DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";
	
	private OracleConnection(){
		try{
			Class.forName(DRIVER_CLASS);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private Connection createConnection(){
		Connection connection = null;;
		try{
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Succesful database connection!");
		}
		catch(Exception e){
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		}
		return connection;
	}
	

	public static Connection getConnection(){
		return instance.createConnection();
	}
	
}