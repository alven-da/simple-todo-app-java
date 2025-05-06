package com.mytodo.repository.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDataSource {
	private static MySQLDataSource _instance;

	private Connection connection;
	
	private MySQLDataSource() {
		try {
			this.connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/todo_db",
					"hexagonal_user",
					"password");
			System.out.println("MySQL connected successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static MySQLDataSource getInstance() {
		if (_instance == null) {
			_instance = new MySQLDataSource();
		}
		
		return _instance;
	}
	
	public Connection getConnection() {
		return this.connection;
	}
}
