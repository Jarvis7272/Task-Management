package com.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionprovider {

	static Connection con;
	private final static String url = "jdbc:mysql://localhost:3306/taskplanner";
	private final static String uname = "root";
	private final static String upass = "tera1234";

	public static final Connection connectionp() throws SQLException {
		try {
			// Load the MySQL driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establ1ish the connection
			con = DriverManager.getConnection(url, uname, upass);

		} catch (ClassNotFoundException e) {
			System.err.println("MySQL Driver not found.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Failed to create a connection.");
			e.printStackTrace();
			throw e;  // Propagating the SQLException for better error handling
		}
		return con;
	}
}
