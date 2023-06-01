package timer;

import java.util.TimerTask;

import javax.swing.JButton;

/** 
 * OrderNumberDialog 클래스 버튼의 남은 시간을 변화시키기 위한 클래스
 */
public class OrderNumberTask extends TimerTask {

	CountTimer countTimer;
	JButton btn;
	
	/** 
	 * @param countTimer 이 클래스를 호출한 CountTimer를 넣습니다. 
	 * @param btn 내용을 변화시킬 JButton 인스턴스를 넣습니다.
	 */
	public OrderNumberTask(CountTimer countTimer, JButton btn) {
		this.countTimer = countTimer;
		this.btn = btn;
	}
	
	@Override
	public void run() {
		if(countTimer.getCount() == -1) return;
		
		btn.setText(String.format("남은시간 (%d)", countTimer.getCount()));
		
		if(countTimer.getCount() == 0) {
			countTimer.cancel();
			btn.doClick();
		} else {
			countTimer.setCount();
		}
	}
	
}
