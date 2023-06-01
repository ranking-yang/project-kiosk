package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.OjdbcConnection;

/**
 * 관리자 id 및 pw를 조회해주는 DAO 클래스. 
 * @author BAE
 */
public class LoginDAO {

	Connection conn;
	
	public LoginDAO() {
		try {
			conn = OjdbcConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/** 연결 닫는 메서드 */
	public void closeConn() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/** DB에서 PW를 가져오는 메서드 
	    DB에서 가져오는 PW라 dbPw로 표기함**/
	public String getpw() {
		String queryPw =" SELECT login_pw FROM login";	
		String dbPw = "";
		
		try (
			PreparedStatement pstmtPw = conn.prepareStatement(queryPw);
			ResultSet rsPw = pstmtPw.executeQuery();
		){
				
			while (rsPw.next()) {
				 dbPw = rsPw.getString("login_pw");					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return dbPw;		

	}
	
	/** DB에서 ID를 가져오는 메서드 
    DB에서 가져오는 ID라 dbId로 표기함**/
	public String getId() {
		String queryId =" SELECT login_id FROM login";
		String dbId = "";
		
		try (
			PreparedStatement pstmtId = conn.prepareStatement(queryId);
			ResultSet rsId = pstmtId.executeQuery();
		){
			while (rsId.next()) {
				dbId = rsId.getString("login_Id");					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return dbId;
	}	
	
}
