package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** 
 * DB와의 접속을 위한 클래스.
 * 본인이 사용할 DB와 멤버변수 내용을 연동시켜야 한다.
 */
public class OjdbcConnection {
	
	private static String driverName = "oracle.jdbc.driver.OracleDriver";
	private static String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static String user = "javatest";
	private static String password = "1234"; 
	
	static {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/** Connection을 반환해주는 메서드 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
	
}
