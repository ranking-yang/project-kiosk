
package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import database.OjdbcConnection;
import manager.ImageFile.ImageFileDelete;
import manager.ImageFile.ImageFileInsert;
import manager.ImageFile.ImageFileUpdate;
import manager.menu_management.ManagerModeScreen;

/** 
 * 메뉴 추가, 수정, 삭제에 대한 DAO 클래스
 * @author BAE 
 */
public class ProductDAO extends JDialog {
	/** JDialog for ProductDAO */
	private static final long serialVersionUID = -6476417753274639066L;
	
	Connection productDB;
	ManagerModeScreen parent;
	
	public ProductDAO(ManagerModeScreen parent) {
		try {
			productDB = OjdbcConnection.getConnection();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		this.parent = parent;
	}

	/** 옵션선택상태를 스트링으로 전환*/
	public static String stateStr(boolean state) {
		
		return state ? "Y" : "N";
	}
	
	/** 테이블 데이터를 갱신하는 메서드 */
	public void showMenu() {
		String query = "SELECT * FROM product ORDER BY pd_id";
		
		try (
			PreparedStatement pstmt = productDB.prepareStatement(query);		
			ResultSet rs = pstmt.executeQuery();
		) {	
			while(rs.next()) {
				ManagerModeScreen.model.addRow(new Object[]{rs.getInt("pd_id"), rs.getString("pd_name"),
						rs.getInt("pd_price"), rs.getString("pd_thumbnail"), 
						rs.getInt("tm_id")*100, rs.getString("pd_shot") ,rs.getString("pd_milk"),
						rs.getString("pd_ice"), rs.getString("pd_stevia"), rs.getString("pd_cream")});
			};
		}catch(Exception e) {
			e.printStackTrace();					
		}
	}
	
	/** 제품 추가 메서드 **/
	public void addProduct(int pd_id, String productName, int productPrice,
			String productThumbnail, int tm_id, boolean shot, boolean milk,
			boolean ice, boolean stevia, boolean cream, ImageFileInsert fileChooser) {
		
		String query ="INSERT INTO product"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try	(
			PreparedStatement pstmt =productDB.prepareStatement(query);				
		){
			pstmt.setInt(1, pd_id);
			pstmt.setString(2, productName);
			pstmt.setInt(3, productPrice);
			pstmt.setString(4, productThumbnail);
			pstmt.setInt(5, tm_id);
			pstmt.setString(6, stateStr(shot));
			pstmt.setString(7, stateStr(milk));
			pstmt.setString(8, stateStr(ice));
			pstmt.setString(9, stateStr(stevia));
			pstmt.setString(10, stateStr(cream));			
			
			pstmt.executeUpdate();
			
			fileChooser.fileCopyExecute();
			
		} catch(SQLIntegrityConstraintViolationException e) {
			JOptionPane.showMessageDialog(null, "중복된 제품번호가 있습니다.", "경고창", JOptionPane.ERROR_MESSAGE);
		} catch(SQLException e) {
	        e.printStackTrace();	
		} 
	}

	/** 제품 행 선택 후 삭제 버튼을 누르면 삭제 메세지를 출력.
	 *  선택 결과에 따라 삭제를 실행하는 메서드
	 *  해당 행 Thumnail 경로가 가리키는 이미지 파일도 함께 삭제함
	 *  @author BAE
	 *  @author HONG(imageDelete) */ 
	public void deleteProduct() {
		int row = parent.getMenuTable().getSelectedRow();
		
		if (row == -1) {
			JOptionPane.showMessageDialog(parent, "선택된 메뉴가 없습니다 삭제할 메뉴를 선택해주세요", "입력값 없음", JOptionPane.WARNING_MESSAGE);
			return;
		}
		String ment = (String)ManagerModeScreen.model.getValueAt(row, 1);
	
		int choice =  JOptionPane.showConfirmDialog(parent, "\""+ ment + "\" 제품의 모든 데이터가 삭제됩니다.", "삭제 확인", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
		if(choice != 0) return;
		
		ImageFileDelete imageDelete = parent.getImageDelete();
		
		String query = "DELETE FROM product WHERE pd_id = ?";
		
		try (
			PreparedStatement pstmt = productDB.prepareStatement(query);
		){
			pstmt.setInt(1, (Integer) ManagerModeScreen.model.getValueAt(row, 0));
			
			int result = pstmt.executeUpdate();
			
			if(result == 0) {
				JOptionPane.showMessageDialog(parent, "DB에 삭제할 데이터가 존재하지 않습니다", "삭제 오류", JOptionPane.WARNING_MESSAGE);
			} else {
				imageDelete.getFilePath((String) ManagerModeScreen.model.getValueAt(row, 3)); // 이미지 파일 컬럼은 3
				imageDelete.fileDeleteExecute(); // 이미지 파일 삭제 실행
			}
		
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		ManagerModeScreen.model.setRowCount(0);
		
		showMenu();
	}
	
	/** 메뉴 수정에 관한 메서드 */
	public void edit(int newPd_id, String productName, int productPrice,
			String productThumbnail, int tmId, boolean shot, boolean milk,
			boolean ice, boolean stevia, boolean cream, int pdId, ImageFileUpdate imageUpdate) {
		
		int choice = JOptionPane.showConfirmDialog(parent, "수정하시겠습니까?", "확인", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
		if(choice != 0)	return;
		
		String query ="Update product"
					+ " Set pd_id = ?, pd_name = ?, pd_price = ? , pd_thumbnail = ?,"
					+ " tm_id = ?, pd_shot = ?, pd_milk = ?, pd_ice = ?, pd_stevia = ?, pd_cream =?"
					+ " WHERE pd_id = ?";
		try (				
			PreparedStatement pstmt = productDB.prepareStatement(query);
		) {	
			pstmt.setInt(1, newPd_id);
			pstmt.setString(2, productName);
			pstmt.setInt(3, productPrice);
			pstmt.setString(4, productThumbnail);
			pstmt.setInt(5, tmId); // DB에는 1 ~ 10까지 저장되어있기에 예시와 같이 100 ~ 1000으로 변환할 필요가 없다
			pstmt.setString(6, stateStr(shot));
			pstmt.setString(7, stateStr(milk));
			pstmt.setString(8, stateStr(ice));
			pstmt.setString(9, stateStr(stevia));
			pstmt.setString(10, stateStr(cream));
			pstmt.setInt(11, pdId);		
			
			pstmt.executeUpdate();
			// DB에 반영 성공시 이미지 변환 이루어짐
			imageUpdate.fileUpdateExecute();
			
		} catch(SQLIntegrityConstraintViolationException e) {
			JOptionPane.showMessageDialog(null, "수정하려는 번호와 중복되는 제품번호가 있습니다.", "경고창", JOptionPane.ERROR_MESSAGE);
		} catch(SQLException e) {			
	        e.printStackTrace();		
		}
		ManagerModeScreen.model.setRowCount(0);
		
		showMenu();
	}
	
}
