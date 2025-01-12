package com.sanjeeban.db;

import java.sql.DriverManager;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	public static Connection getConnection() {
		
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_db","root","--password--");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
}
