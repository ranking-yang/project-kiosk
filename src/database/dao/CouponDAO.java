package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.OjdbcConnection;
import database.dto.CouponDTO;
import project.Encryption;

/**
 * coupon 적용을 위한 클래스
 * 생성 시 Connection 생성.
 * AutoCommit = false. 최종 결제 여부에 따라 commit할지 rollback할지 정한다.
 */ 
public class CouponDAO {

	Connection couponDB;

	public CouponDAO() {
		try {
			couponDB = OjdbcConnection.getConnection();
			couponDB.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/** 결제 승인시 결과를 commit하고 연결 닫음. */ 
	public boolean reflectAllResult() {
		try {
			couponDB.commit();
			couponDB.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/** 결제 취소시 결과를 rollback하고 연결 닫음. */ 
	public boolean cancelAllResult() {
		try {
			couponDB.rollback();
			couponDB.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/** 성공 시 1 반환, 실패시 0 반환 */
	public int updateCouponInfo(String phone_number, int totalBuyCount) {
		
		String cipher_number = Encryption.encryption(phone_number);
		
		int result;
		if(!isValidNumber(cipher_number)) {
			result = signUp(cipher_number, totalBuyCount);
		} else {
			result = addStamp(cipher_number, totalBuyCount);
		}
		
		if(result == 0) {
			System.out.println("DB와 통신 중 오류가 발생");
			return 0;
		}
		
		CouponDTO target = new CouponDTO();
		
		target.setPhone_number(cipher_number);
		
		String query = "SELECT coupon_count, stamp_count"
				+ " FROM coupon WHERE phone_number = ?";
		
		try(
			PreparedStatement pstmt = couponDB.prepareStatement(query);
		) {
			pstmt.setString(1, cipher_number);
			
			try(
				ResultSet rs = pstmt.executeQuery();	
			) {
				rs.next();
				target.setCoupon_count(rs.getInt("coupon_count"));
				target.setStamp_count(rs.getInt("stamp_count"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
		updateStamp(target);

		return updateCoupon(target);
	}

	/** 쿠폰 잔여량이 있는지 조회하고 있을 경우 쿠폰 잔여량에서 -1함
	 *  rollback과 commit은 별도로 수행해야함 
	 *  성공적으로 이루어졌을 시 = 1,
	 *  없는 번호 = 0,
	 *  DB통신오류 = -1,
	 *  쿠폰 잔여량 없음 = 2 */
	public int useCoupon(String phone_number) {
		
		String cipher_number = Encryption.encryption(phone_number);
		
		if(!isValidNumber(cipher_number)) {
			return 0;
		}
		
		int checkCoupon = checkCoupon(cipher_number); 
		
		if(checkCoupon == -1) {
			return -1;
		}
		
		if(checkCoupon == 0) {
			return 2;
		}
		
		String query = "UPDATE coupon SET coupon_count = coupon_count - 1 WHERE phone_number = ?";
		
		try(
			PreparedStatement pstmt = couponDB.prepareStatement(query);	
		) {
			pstmt.setString(1, cipher_number);
			
			return pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	/** 해당 전화번호의 잔여 쿠폰량을 반환
	 *  -1 반환시 DB와의 통신오류
	 *  잔여 쿠폰량이 없는 경우에도 0을 반환하지만
	 *  없는 전화번호가 들어왔을 때도 0을 반환함 */
	private int checkCoupon(String phone_number) {
		
		String query = "SELECT coupon_count FROM coupon WHERE phone_number = ?";
		
		try(
			PreparedStatement pstmt = couponDB.prepareStatement(query);	
		) {
			pstmt.setString(1, phone_number);
			
			try(
				ResultSet rs = pstmt.executeQuery();	
			) {
				if(rs.next()) {
					return rs.getInt("coupon_count");
				}
				
				return 0;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}
		
	}
	
	/** 들어온 번호가 DB에 등록된 번호인지 유무 확인 */
	private boolean isValidNumber(String phone_number) {
		String query = "SELECT phone_number FROM coupon"
				+ " WHERE phone_number = ?";
		
		try(
			PreparedStatement pstmt = couponDB.prepareStatement(query);
		) {
			pstmt.setString(1, phone_number);
			try(
				ResultSet rs = pstmt.executeQuery();
			) {
				String temp = null; 
				while(rs.next()) {
					temp = rs.getString(1);
				}
				
				if(temp != null) {
					return true;
				}
				
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/** DB에 없는 번호일 경우 DB에 번호 등록 작업 수행. 추가된 stamp도 반영 */
	private int signUp(String phone_number, int totalBuyCount) {
		String query = "INSERT INTO coupon VALUES(?, 0, ?)";
		
		try(
			PreparedStatement pstmt = couponDB.prepareStatement(query);
		) {
			pstmt.setString(1, phone_number);
			pstmt.setInt(2, totalBuyCount);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/** DB에 있는 번호일 경우 stamp 추가 */
	private int addStamp(String phone_number, int totalBuyCount) {
		String query = "UPDATE coupon SET stamp_count = stamp_count + ?"
				+ " WHERE phone_number = ?";
		
		try(
			PreparedStatement pstmt = couponDB.prepareStatement(query);
		) {
			pstmt.setInt(1, totalBuyCount);
			pstmt.setString(2, phone_number);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/** 10스탬프 = 1쿠폰 전환 작업. 기존 쿠폰 숫자를 불러와서 전환된 쿠폰을 더해준다 */
	private void updateStamp(CouponDTO target) {
		int limitStamp = 10;
		int nowCoupon = target.getCoupon_count();
		int nowStamp = target.getStamp_count();
		
		while(nowStamp >= limitStamp) {
			nowStamp -= limitStamp;
			++nowCoupon;
		}
		
		target.setCoupon_count(nowCoupon);
		target.setStamp_count(nowStamp);
	}
	
	/** 10스탬프 = 1쿠폰 전환을 반영하여 업데이트. 성공시 return 1, error = 0 */
	private int updateCoupon(CouponDTO target) {
		String query = "UPDATE coupon SET coupon_count = ?, stamp_count = ?"
				+ " WHERE phone_number = ?";
		try(
			PreparedStatement pstmt = couponDB.prepareStatement(query);
		) {
			pstmt.setInt(1, target.getCoupon_count());
			pstmt.setInt(2, target.getStamp_count());
			pstmt.setString(3, target.getPhone_number());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
}
