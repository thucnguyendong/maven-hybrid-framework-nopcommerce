package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseHelper {
	
	public static DatabaseHelper initDatabase(){
		return new DatabaseHelper();
	}
	
	public Connection getLocalMySQLConnection() {
		String hostName = "localhost";
		String dbName = "automationfc";
		String username = "root";
		String password = "Tien@2112";
		return getMySQLConnection(hostName, dbName, username, password);
	}
	
	public Connection getMySQLConnection(String hostName, String dbName, String username, String password) {
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
	public Connection getLocalSQLConnection() {
		String hostName = "localhost";
		String dbName = "automationfc";
		String username = "tester";
		String password = "123456";
		return getJTDSConnection(hostName, dbName,username,password);
	}
	
	public Connection getSQLConnection(String hostName, String dbName, String username, String password) {
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
	
	public Connection getJTDSConnection(String hostName, String dbName, String username, String password) {
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
	
	public ArrayList<String> getColumnDataFromDB(Connection conn, String sql, String columnName){
		ArrayList<String> arrayList = new ArrayList<>();
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				arrayList.add(result.getString(columnName));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayList;
	}
	
	public ArrayList<Integer> getColumnNumberFromDB(Connection conn, String sql, String columnName){
		ArrayList<Integer> arrayList = new ArrayList<>();
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				arrayList.add(result.getInt(columnName));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayList;
	}
	
	public void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
