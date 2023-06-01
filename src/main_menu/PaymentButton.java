package main_menu;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import project.OrderDetailScreen;
import project.PosFrameProperties;

/**메인화면에 있는 결제버튼(주문세부내역으로 전환시켜주는 역할)*/
public class PaymentButton extends JButton {
	/** JButton for PaymentButton */
	private static final long serialVersionUID = 3430381166045898252L;
	private OrderDetailScreen orderDetail;
	
	public PaymentButton(int x, int y, int width, int height, MenuScreen parent) {
		setText("결제");
		setFont(PosFrameProperties.HEAD_FONT);
		setBounds(x, y, width, height);
		setBackground(new Color(228, 161, 26));
		
		addActionListener((e) -> {
			JTable jtable = parent.getOrderList().getTable();
			int rowCnt = jtable.getRowCount();
			
			if (rowCnt == 0) {
				JOptionPane.showMessageDialog(null, "선택된 제품이 없습니다.");
				return;
			} 
			
			parent.setShowingNow(false);
			this.orderDetail = new OrderDetailScreen(PosFrameProperties.frame, parent, "주문세부내역");
			orderDetail.setVisible(true);
		});
	}
	
	public OrderDetailScreen getOrderDetail() {
		return orderDetail;
	}
}
