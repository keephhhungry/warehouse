package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

	public static Connection getConn() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/gallery?allowPublicKeyRetrieval=true&useSSL=false&user=root&password=root&serverTimezone=GMT");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

}
