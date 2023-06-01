package timer;

import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JTextField;

/** 
 * Receipt 클래스 남은 시간을 변화시키기 위한 클래스
 */
public class ReceiptTimerTask extends TimerTask {

	CountTimer countTimer;
	JTextField text;
	JButton btn;
	
	/** 
	 * @param countTimer 이 클래스를 호출한 CountTimer를 넣습니다.
	 * @param text 내용을 변화시킬 JTextField의 인스턴스.
	 * @param btn  남은 시간 경과 후 동작시킬 JButton의 인스턴스.
	 */
	public ReceiptTimerTask(CountTimer countTimer, JTextField text, JButton btn) {
		this.countTimer = countTimer;
		this.text = text;
		this.btn = btn;
	}
	
	@Override
	public void run() {
		if(countTimer.getCount() == -1) return;
		
		text.setText("" + countTimer.getCount());
			
		if(countTimer.getCount() == 0) {
			countTimer.cancel();
			btn.doClick();
		} else {
			countTimer.setCount();
		}
	}
	
}
