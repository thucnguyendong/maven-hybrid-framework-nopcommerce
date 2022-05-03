package utilities;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCHelper {
	
	public static Connection getLocalMySQLConnection() {
		String hostName = "localhost";
		String dbName = "automationfc";
		String username = "root";
		String password = "Tien@2112";
		return getMySQLConnection(hostName, dbName, username, password);
	}
	
	public static Connection getMySQLConnection(String hostName, String dbName, String username, String password) {
		Connection conn = null;
		try {
			String connectionURL = "jdbc:mysql://"+ hostName + ":3306/"+ dbName;
			conn = DriverManager.getConnection(connectionURL, username, password);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static Connection getLocalSQLConnection() {
		String hostName = "localhost";
		String dbName = "automationfc";
		String username = "tester";
		String password = "123456";
		return getJTDSConnection(hostName, dbName,username,password);
	}
	
	public static Connection getSQLConnection(String hostName, String dbName, String username, String password) {
		Connection conn = null;
		try {
			String connectionURL = "jdbc:sqlserver://"+ hostName + ":1433"+ ";instance=SQLEXPRESS;databaseName="+dbName+";encrypt=true;trustServerCertificate=true;";
			conn = DriverManager.getConnection(connectionURL, username, password);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static Connection getJTDSConnection(String hostName, String dbName, String username, String password) {
		Connection conn = null;
		try {
			String connectionURL = "jdbc:jtds:sqlserver://"+ hostName + ":1433/"+dbName+";instance=SQLEXPRESS;encrypt=true;trustServerCertificate=true;";
			conn = DriverManager.getConnection(connectionURL,username,password);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
