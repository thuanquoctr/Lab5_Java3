package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class JDBCUtil {
	public static Connection getConnection() {
		Connection c = null;
		try {
			DriverManager.registerDriver(new Driver());
			String url = "jdbc:mySQL://localhost:3306/QLSV";
			String us = "root";
			String pass = "tqthuan1734";
			c = DriverManager.getConnection(url, us, pass);
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return c;
	}

	public static void close(Connection c) {
		try {
			if (c != null) {
				c.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
