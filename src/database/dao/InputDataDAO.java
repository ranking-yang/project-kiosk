package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.OjdbcConnection;
import database.dto.InputDataSet;

/**
 * 판매 데이터 및 관련 제품을 데이터베이스에 저장시키는 클래스.
 * @author YANG
 */
public class InputDataDAO {
	
	public InputDataDAO() {
		
		String query1 = "INSERT INTO sellingdata VALUES(sd_id_seq.nextval, sysdate, ?, 1, ?)";
		String query1_1 = "SELECT MAX(sd_id) FROM sellingdata";
		String query2 = "INSERT INTO order_list VALUES(alist_id_seq.nextval, ?, ?, ?, ?, ?, ?)";
		
		Connection conn;
		try {
			conn = OjdbcConnection.getConnection();
			conn.setAutoCommit(false);
			
			try(
				PreparedStatement pstmt = conn.prepareStatement(query1);
			) {
				pstmt.setInt(1, InputDataSet.getSellingDataArray()[0]);
				pstmt.setInt(2, InputDataSet.getSellingDataArray()[1]);
				
				pstmt.executeUpdate();
			}
			
			try(
				PreparedStatement pstmt = conn.prepareStatement(query1_1);
				ResultSet rs = pstmt.executeQuery();
			) {
				if (rs.next()) {
					for(int i = 0; i < InputDataSet.getAvailList().size(); ++i) {
						InputDataSet.getAvailList().get(i).setSd_id(rs.getInt(1));
					}
				}
			}
			
			try(
				PreparedStatement pstmt = conn.prepareStatement(query2);
			) {
				for (int i = 0; i < InputDataSet.getAvailList().size(); ++i) {
					pstmt.setInt(1, InputDataSet.getAvailList().get(i).getPd_id());
					pstmt.setInt(2, InputDataSet.getAvailList().get(i).getAlist_count());
					pstmt.setInt(3, InputDataSet.getAvailList().get(i).getAl_price());
					pstmt.setInt(4, InputDataSet.getAvailList().get(i).getSd_id());
					pstmt.setString(5, InputDataSet.getAvailList().get(i).getOp_id());
					pstmt.setString(6, InputDataSet.getAvailList().get(i).getPd_name());
					pstmt.executeUpdate();
				}
			}
			
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
