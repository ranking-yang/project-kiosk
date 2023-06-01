package payment_ways;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import database.dto.InputDataSet;
import project.CreditCardScreen;
import project.PointSaveDialog;

/** PaymentsScreen과 같은 패키지에 존재해야합니다
 *  @author HONG */ 
public class ButtonActionListener implements ActionListener {

	PaymentsScreen parent;
	
	private final static int KAKAO_PAY = 37730;
	private final static int NAVER_PAY = 13561;
	private final static int LOCAL_PAY = 12481;
	private final static int CREDIT_CARD = 12345;
	private final static int COUPON = 11111;
	
	public ButtonActionListener(PaymentsScreen parent) {
		this.parent = parent;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == parent.couponBtn) {
			parent.dispose();
			InputDataSet.setSellingDataArray(0, COUPON);
			return;
		}
		
		if(e.getSource() == parent.cardBtn
				|| e.getSource() == parent.kkd_cash) {
			
			parent.dispose();
			
			if(e.getSource() == parent.cardBtn) InputDataSet.setSellingDataArray(0, CREDIT_CARD);
			if(e.getSource() == parent.kkd_cash) InputDataSet.setSellingDataArray(0, LOCAL_PAY);
			
			new PointSaveDialog(CreditCardScreen.CARD).setVisible(true);
			return;
		} 
		
		if(e.getSource() == parent.kakaoBtn 
				|| e.getSource() == parent.naverBtn) {
			
			parent.dispose();
			
			if (e.getSource() == parent.kakaoBtn) InputDataSet.setSellingDataArray(0, KAKAO_PAY);
			if (e.getSource() == parent.naverBtn) InputDataSet.setSellingDataArray(0, NAVER_PAY);
			
			new PointSaveDialog(CreditCardScreen.QR).setVisible(true);
		}
	}

}
