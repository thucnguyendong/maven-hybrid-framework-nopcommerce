package com.database;

import java.sql.Connection;
import java.util.ArrayList;

import commons.BasePage;
import commons.SQLQueryConstants;
import utilities.DatabaseHelper;

public class SQLTestConnection {
	
	public static void main(String[] arg) {
		Connection conn;
		conn = DatabaseHelper.getLocalSQLConnection();
		System.out.println(conn);
		ArrayList<String> list= BasePage.getBasePage().getColumnDataFromDB(conn,SQLQueryConstants.GET_EMPLOYEE_INFORMATION,"FIRST_NAME");
		ArrayList<Integer> numberList= BasePage.getBasePage().getColumnNumberFromDB(conn, SQLQueryConstants.GET_EMPLOYEE_INFORMATION, "EMP_ID");
		for (int i=0; i<list.size();i++) {
			System.out.println(numberList.get(i));
			System.out.println(list.get(i));
		}
		DatabaseHelper.closeConnection(conn);
	}
}
