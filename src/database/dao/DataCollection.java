package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.OjdbcConnection;
import database.dto.ProductDTO;


/**
 * 대기화면에서 메뉴선택화면으로 넘어가기 전에 메뉴에 표시할 전제품에 대한 정보를 List에 담는 클래스.
 */
public class DataCollection {
	
	private static List<ProductDTO> productList = new ArrayList<>();
	private static Map<Integer, String> themeMap = new HashMap<>();
	private static Map<String, Integer> optionMap = new HashMap<>();
	private ProductDTO pDto;
	Connection conn;
	
	public DataCollection() {
		try {
			conn = OjdbcConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * DB에 있는 모든 제품과 옵션에 대한 데이터를 담는 메서드. 
	 */
	public void updateInfo() {
		productList.clear();
		themeMap.clear();
		
		String query = "SELECT * FROM product INNER JOIN theme USING(tm_id) ORDER BY pd_id";
		String query1 = "SELECT op_id, add_price FROM personal_option";
		try(
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
		) {
			while (rs.next()) {
				pDto = new ProductDTO();

				themeMap.put(rs.getInt("tm_id"), rs.getString("tm_name"));
				
				pDto.setTm_id(rs.getInt("tm_id"));
				pDto.setPd_id(rs.getInt("pd_id"));
				pDto.setPd_name(rs.getString("pd_name"));
				pDto.setPd_price(rs.getInt("pd_price"));
				pDto.setPd_thumbnail(rs.getString("pd_thumbnail"));
				pDto.setPd_shot(rs.getString("pd_shot").equals("Y") ? true : false);
				pDto.setPd_milk(rs.getString("pd_milk").equals("Y") ? true : false);
				pDto.setPd_ice(rs.getString("pd_ice").equals("Y") ? true : false);
				pDto.setPd_stevia(rs.getString("pd_stevia").equals("Y") ? true : false);
				pDto.setPd_cream(rs.getString("pd_cream").equals("Y") ? true : false);

				productList.add(pDto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		try (
			PreparedStatement pstmt = conn.prepareStatement(query1);
			ResultSet rs = pstmt.executeQuery();
		) {
			while(rs.next()) {
				optionMap.put(rs.getString(1), rs.getInt(2));
			}
		}  catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**데이터베이스 접속을 통해 얻어온 제품에 대한 정보들을 담는 리스트를 반환한다*/
	public static List<ProductDTO> getProductList() {
		return productList;
	}
	/**데이터베이스 접속을 통해 얻어온 카테고리에 대한 정보들을 담는 맵을 반환한다*/
	public static Map<Integer, String> getThemeMap() {
		return themeMap;
	}
	/**데이터베이스 접속을 통해 얻어온 옵션일련번호(String)
	 * 추가금액(Integer)을 담은 맵을 반환한다*/
	public static Map<String, Integer> getOptionMap() {
		return optionMap;
	}
	
	/**제품의 총 개수를 반환해주는 메서드*/
	public static int getNumberOfProducts() {
		return productList.size();
	}
	/**카테고리의 총 개수를 반환해주는 메서드*/
	public static int getNumberOfThemes() {
		return themeMap.size();
	}

}
