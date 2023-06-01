package database.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import database.OjdbcConnection;
import database.dto.CheckingSalesDTO;
import database.dto.SoldProductDTO;
import manager.confirm_sale.CheckingSalesDataScreen;
import manager.confirm_sale.CheckingSalesTableModel;

/** 매출정보를 받아오기 위해 DB와의 통신을 하는 DAO 클래스
 *  @author HONG */
public class CheckingSalesDAO {

	Connection sellingDB;
	
	CheckingSalesDataScreen parent;
	
	CheckingSalesTableModel model;

	Date preDate;
	Date postDate;
	
	public CheckingSalesDAO(CheckingSalesDataScreen parent) {
		try {
			sellingDB = OjdbcConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.parent = parent;
		this.model = parent.getModel();
	}
	
	/** 전체 매출 정보 호출 */
	public void totalSelect() {
		String query = "SELECT sd_id, sd_time, pay_id, total.price, pick_up"
				+ " FROM sellingdata"
				+ " INNER JOIN (SELECT sd_id, sum(al_price) AS price FROM order_list GROUP BY sd_id) total USING (sd_id)"
				+ " ORDER BY sd_id";
		
		try(
			PreparedStatement pstmt = sellingDB.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
		) {
			CheckingSalesDTO dto = null;
			while(rs.next()) {
				dto = new CheckingSalesDTO(
						rs.getInt("sd_id"),
						rs.getDate("sd_time").toString(),
						rs.getInt("pay_id"),
						rs.getInt("price"),
						rs.getInt("pick_up"));
				
				model.addData(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/** sd_id에 관련된 OrderList 정보를 받아오는 메서드 */
	public List<SoldProductDTO> selectSoldProduct(int sd_id) {
		String query = "SELECT alist_id, alist_count, pd_id, pd_name, al_price, op_id"
				+ " FROM order_list"
				+ " WHERE sd_id = ?";
		try(
			PreparedStatement pstmt = sellingDB.prepareStatement(query);
		) {
			pstmt.setInt(1, sd_id);
			
			try(
				ResultSet rs = pstmt.executeQuery();
			) {
				List<SoldProductDTO> list = new ArrayList<>();
				
				while(rs.next()) {
					SoldProductDTO dto = new SoldProductDTO();
					dto.setAlist_id(rs.getInt("alist_id"));
					dto.setAlist_count(rs.getInt("alist_count"));
					dto.setPd_id(rs.getInt("pd_id"));
					dto.setPd_name(rs.getString("pd_name"));
					dto.setAl_price(rs.getInt("al_price"));
					dto.setOp_id(rs.getString("op_id"));
					
					list.add(dto);
				}
				
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static final String yearQuery =
			"SELECT TO_CHAR(sd_time, 'YYYY\"년\"') AS time, SUM(al_price)"
			+ " FROM sellingdata sd INNER JOIN order_list USING (sd_id)"
			+ " GROUP BY TO_CHAR(sd_time, 'YYYY\"년\"')"
			+ " ORDER BY time";
	private static final String monthQuery = 
			"SELECT TO_CHAR(sd_time, 'YYYY\"년\" mm\"월\"') AS time, SUM(al_price)"
			+ " FROM sellingdata sd INNER JOIN order_list USING (sd_id)"
			+ " GROUP BY TO_CHAR(sd_time, 'YYYY\"년\" mm\"월\"')"
			+ " ORDER BY time";
	private static final String dayQuery = 
			"SELECT TO_CHAR(sd_time, 'YYYY\"년\" mm\"월\" DD\"일\"') AS time, SUM(al_price)"
			+ " FROM sellingdata sd INNER JOIN order_list USING (sd_id)"
			+ " GROUP BY TO_CHAR(sd_time, 'YYYY\"년\" mm\"월\" DD\"일\"')"
			+ " ORDER BY time";
	
	/** range ComboBox 인덱스가 0이고 Group ComboBox 인덱스가 0이외의 값일 때
	 *  @param groupType 첫번째 ComboBox에서 선택된 index값을 넣습니다.
	 *  	   1 : 연도별, 2 : 월별, 3 : 일별 */
	public void select(int groupType) {
		String query;
		if(groupType == 1) {
			query = yearQuery;
		} else if(groupType == 2) {
			query = monthQuery;
		} else {
			query = dayQuery;
		} 
		
		try(
			PreparedStatement pstmt = sellingDB.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
		) {
			CheckingSalesDTO dto = null;
			while(rs.next()) {
				dto = new CheckingSalesDTO(
						null,
						rs.getString(1),								 
						null,
						rs.getInt(2),
						null);
				
				model.addData(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 사용자지정 설정시 아래의 메서드 호출
	
	/** Date 멤버변수 재설정해주는 메서드.
	 *  시작날짜 미지정시 현재일로부터 20년 전으로 지정됨.
	 *  종료날짜 미지정시 현재일까지로 지정됨.
	 *  @param preStr	시작날짜
	 *  @param postStr	종료날짜 */ 
	private void dateSetting(String preStr, String postStr) {
		if(preStr.equals("") && postStr.equals(""))
			return;
		
		if(!preStr.equals("") && !postStr.equals("")) {
			preDate = Date.valueOf(preStr);	
			postDate = Date.valueOf(Date.valueOf(postStr).toLocalDate().plusDays(1));
		} 
		
		if(preStr.equals("")) {
			preDate = Date.valueOf(LocalDate.now().minusYears(20));
			postDate = Date.valueOf(postStr);
		}
		
		if(postStr.equals("")) {
			postDate = Date.valueOf(LocalDate.now().plusDays(1));
			preDate = Date.valueOf(preStr);
		}
		
	}
	
	/** Group ComboBox 인덱스가 0이고 range를 사용자지정으로 했을 때 */
	public void userTotalSelect() {
		String query = "SELECT sd_id, sd_time, pay_id, total.price, pick_up"
				+ " FROM sellingdata"
				+ " INNER JOIN (SELECT sd_id, sum(al_price) AS price FROM order_list GROUP BY sd_id) total USING (sd_id)"
				+ " WHERE sd_time BETWEEN ? AND ?"
				+ " ORDER BY sd_id";
		
		dateSetting(parent.getPreDate(), parent.getPostDate());

		try(
			PreparedStatement pstmt = sellingDB.prepareStatement(query);
		) {
			pstmt.setDate(1, preDate);
			pstmt.setDate(2, postDate);
			
			try(
				ResultSet rs = pstmt.executeQuery();
			) {
				CheckingSalesDTO dto = null;
				while(rs.next()) {
					dto = new CheckingSalesDTO(
							rs.getInt("sd_id"),
							rs.getDate("sd_time").toString(),
							rs.getInt("pay_id"),
							rs.getInt("price"),
							rs.getInt("pick_up"));
					
					model.addData(dto);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static final String userYearQuery =  
			"SELECT TO_CHAR(sd_time, 'YYYY\"년\"') AS time, SUM(al_price)"
			+ " FROM sellingdata INNER JOIN order_list USING (sd_id)"
			+ " WHERE sd_time BETWEEN ? AND ?"
			+ " GROUP BY TO_CHAR(sd_time, 'YYYY\"년\"')"
			+ " ORDER BY time";
	
	private static final String userMonthQuery =
			"SELECT TO_CHAR(sd_time, 'YYYY\"년\" mm\"월\"') AS time, SUM(al_price)"
			+ " FROM sellingdata INNER JOIN order_list USING (sd_id)"
			+ " WHERE sd_time BETWEEN ? AND ?"
			+ " GROUP BY TO_CHAR(sd_time, 'YYYY\"년\" mm\"월\"')"
			+ " ORDER BY time";
	
	private static final String userDayQuery = 
			"SELECT TO_CHAR(sd_time, 'YYYY\"년\" mm\"월\" DD\"일\"') AS time, SUM(al_price)"
			+ " FROM sellingdata INNER JOIN order_list USING (sd_id)"
			+ " WHERE sd_time BETWEEN ? AND ?"
			+ " GROUP BY TO_CHAR(sd_time, 'YYYY\"년\" mm\"월\" DD\"일\"')"
			+ " ORDER BY time";
	
	/** Group ComboBox 인덱스가 0 이외의 값이고 range를 사용자지정으로 했을 때
	 *  @param groupType 첫번째 ComboBox에서 선택된 index값을 넣습니다.
	 *  	   1 : 연도별, 2 : 월별, 3 : 일별 */
	public void userSelect(int groupType) {
		String query;
		if(groupType == 1) {
			query = userYearQuery;
		} else if(groupType == 2) {
			query = userMonthQuery;
		} else {
			query = userDayQuery;
		} 
		
		dateSetting(parent.getPreDate(), parent.getPostDate());
		
		try(
			PreparedStatement pstmt = sellingDB.prepareStatement(query);
		) {
			pstmt.setDate(1, preDate);
			pstmt.setDate(2, postDate);
			
			try(
				ResultSet rs = pstmt.executeQuery();
			) {
				CheckingSalesDTO dto = null;
				while(rs.next()) {
					dto = new CheckingSalesDTO(
							null,
							rs.getString(1),								 
							null,
							rs.getInt(2),
							null);
					
					model.addData(dto);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
