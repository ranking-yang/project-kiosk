package phone_button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import database.dao.CouponDAO;
import project.CreditCardScreen;
import project.PosFrameProperties;
import receipt.ReceiptChooseDialog;

/** PhoneNumberScreen에서 lastBtn을 눌렀을 때 어떤 타입의 PhoneNumberScreen인지에
 *  따라 정해진 동작을 실행 */
public class LastBtnActionListener implements ActionListener {

	int type;
	PhoneNumberScreen parent;
	CouponDAO dao;
	
	/** @param parent	이 클래스를 호출하는 PhoneNumberScreen을 준다
	 *  @param type 	SAVINGPOINT = 0, CHECKNUM = 1 */
	public LastBtnActionListener(PhoneNumberScreen parent, int type, CouponDAO dao) {
		this.type = type;
		this.parent = parent;
		this.dao = dao;
	}

	@Override
	 /** 스탬프적립을 위해 호출된 스크린에서는 btn을 눌렀을 때 스탬프적립 클래스를 실행하고 
	 *   Screen을 닫습니다.
	 *   쿠폰결제를 위해 호출된 스크린에서는 쿠폰을 이용한 결제를 실행하는 클래스를 실행하고
	 *   Screen을 닫습니다. */
	public void actionPerformed(ActionEvent e) {
		
		String number = parent.getPhoneNumber();
		
		if(!(number.length() == 11)) {
			JOptionPane.showMessageDialog(null, "잘못된 형식입니다. 전화번호를 확인해주세요.", "전화번호 확인", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if(type == PhoneNumberButton.SAVINGPOINT) {
			
			int result = dao.updateCouponInfo(number, PosFrameProperties.orderDetailNow.getCountForCoupon());
			
			if(result == 1) {
				parent.dispose();
				new CreditCardScreen(parent.paymentType, dao).setVisible(true);
			} else {
				return; 
			}
		}
	
		if(type == PhoneNumberButton.CHECKNUM) {
			
			int result = dao.useCoupon(number);
			
			if(result == 1) {
				CreditCardScreen.plusOrderNumber();
				dao.reflectAllResult(); 
				parent.dispose();
				
				new ReceiptChooseDialog().setVisible(true); 
			} else if(result == 0) {
				JOptionPane.showMessageDialog(null, "없는 번호입니다. 전화번호를 확인해주세요.", "전화번호 확인", JOptionPane.WARNING_MESSAGE);
				return;
			} else if(result == 2) {
				JOptionPane.showMessageDialog(null, "잔여 쿠폰이 없습니다.", "쿠폰 확인", JOptionPane.WARNING_MESSAGE);
				return;
			} else {
				
				return; 
			}
		}
	}
	
}
