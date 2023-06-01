package payment_ways;

import javax.swing.JButton;

import database.dto.InputDataSet;
import project.OrderDetailScreen;
import project.PosFrameProperties;

/** PaymentsScreen을 호출하는 버튼 */
public class PaymentsButton extends JButton {
	/** JButton for Payments */
	private static final long serialVersionUID = 8792473318387612110L;
	
	final public static int TAKE_IN = 0;
	final public static int TAKE_OUT = 1;
	
	/** PaymentsScreen을 호출하는 버튼. bounds는 직접 설정하셔야 합니다.
	 *  @param parent	메인프레임을 넣어줍니다.
	 *  @param text 	버튼의 이름을 설정합니다.  
	 *  @param dialog	버튼이 붙어있는 dialog을 입력합니다.
	 */
	public PaymentsButton(String text, OrderDetailScreen dialog, int whereToDrink) {
		setText(text);
		setFont(PosFrameProperties.HEAD_FONT2);
		addActionListener((e) -> {
			dialog.dispose();
			
			if (whereToDrink == TAKE_IN) {
				InputDataSet.setSellingDataArray(1, TAKE_IN);
			} else {
				InputDataSet.setSellingDataArray(1, TAKE_OUT);
			}
			
			new PaymentsScreen().setVisible(true);
		});
	}
	
}
