package phone_button;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import database.dto.InputDataSet;
import main_menu.MenuScreen;
import project.PosFrame;
import project.PosFrameProperties;

/** PhoneNumberScreen을 호출하는 버튼 */
public class PhoneNumberButton extends JButton {
	// JButton for PhoneNumberScreen
	private static final long serialVersionUID = -7655290562912919795L;

	public final static int SAVINGPOINT = 0;
	public final static int CHECKNUM = 1;
	
	@SuppressWarnings("rawtypes")
	private Vector<Vector> productsInOrderList;
	private JTable orderTable;
	
	/** PhoneNumberScreen을 호출하는 버튼. bounds는 직접 설정하셔야 합니다.
	 *  @param type 		버튼의 이름을 설정합니다.
	 *  @param paymentType 	버튼의 타입을 설정합니다. 어떤 기능을 위해 사용될 버튼인지 정합니다.
	 *  					쿠폰조회는 0, 포인트적립은 1  
	 */
	public PhoneNumberButton(int type, int paymentType) {
		PosFrame frame = PosFrameProperties.frame;
		MenuScreen menu = frame.getMenu();
		
		this.productsInOrderList = menu.getOrderList().getOrderListData();
		this.orderTable = menu.getOrderList().getTable();
		
		@SuppressWarnings("unchecked")
		Vector<String> productInfo = this.productsInOrderList.get(0);
		String[] serialNumber = productInfo.get(7).split("/");
		
		int pd_id = Integer.parseInt(serialNumber[0]);// 201, 301
		int qty = Integer.parseInt(productInfo.get(4));
		int rowCnt = this.orderTable.getRowCount();
		
		setFont(PosFrameProperties.HEAD_FONT2);
		
		if(type == SAVINGPOINT) {
			setText("예");
			addActionListener(e -> {
				new PhoneNumberScreen("전화번호 입력", SAVINGPOINT, paymentType).setVisible(true);
			});
		}
		
		if(type == CHECKNUM)  
			addActionListener(e -> {
				if(qty != 1 || rowCnt != 1) {
					JOptionPane.showMessageDialog(null, "쿠폰결제는 한번에 한잔씩 가능합니다.");
					InputDataSet.getAvailList().clear();
					menu.setShowingNow(true);
					
					return;
				}
				
				if(!(pd_id == 201 || pd_id == 301)) {
					JOptionPane.showMessageDialog(null, "쿠폰결제는 (HOT/ICE)아메리카노만 가능합니다.");
					InputDataSet.getAvailList().clear();
					menu.setShowingNow(true);
					
					return;
				}
				
				new PhoneNumberScreen("쿠폰 조회", CHECKNUM, paymentType).setVisible(true);
			});
	}
	
}
