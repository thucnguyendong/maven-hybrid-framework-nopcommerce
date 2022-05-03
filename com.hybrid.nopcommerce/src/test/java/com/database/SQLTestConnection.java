package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utilities.JDBCHelper;

public class SQLTestConnection {
	
	public static void main(String[] arg) {
		Connection conn;
		conn = JDBCHelper.getLocalSQLConnection();
		System.out.println(conn);
		String sql = "select EMP_ID,FIRST_NAME,LAST_NAME,TITLE from automationfc.dbo.EMPLOYEE";
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				int empId = result.getInt(1);
				String empFirstName = result.getString(2);
				String empLastName = result.getString(3);
				String empTitle = result.getString("title");
				
				System.out.println("-------------");
				System.out.println(empId);
				System.out.println(empFirstName+ " "+ empLastName);
				System.out.println(empTitle);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
