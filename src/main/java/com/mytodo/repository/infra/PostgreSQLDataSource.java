package com.mytodo.repository.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLDataSource {
	
	private static PostgreSQLDataSource _instance;
	
	private Connection connection;
	
	private PostgreSQLDataSource() {
		try {
			this.connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5433/todo_app",
					"postgres",
					"mypassword"
				);
			System.out.println("PostgreSQL connected successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static PostgreSQLDataSource getInstance() {
		if (_instance == null) {
			_instance = new PostgreSQLDataSource();
		}
		
		return _instance;
	}
	
	public Connection getConnection() {
		return this.connection;
	}
}
