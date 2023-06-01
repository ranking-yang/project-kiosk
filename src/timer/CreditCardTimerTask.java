package timer;

import javax.swing.JButton;

/**
 * CreditCardScreen 시간 경과시 강제종료 시키기 위한 클래스
 * @author HONG
 */
public class CreditCardTimerTask extends OrderNumberTask {
	
	/**
	 * @param countTimer 시간을 재줄 Timer를 넣어준다
	 * @param btn		 시간 경과시 작동할 버튼을 넣어준다
	 */
	public CreditCardTimerTask(CountTimer countTimer, JButton btn) {
		super(countTimer, btn);
	}

	@Override
	public void run() {
		if(countTimer.getCount() == -1) return;
		
		if(countTimer.getCount() == 0) {
			countTimer.cancel();
			btn.doClick();
		} else {
			countTimer.setCount();
		}
	}
	
}
