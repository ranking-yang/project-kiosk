package timer;

import java.util.TimerTask;

/** 
 * TimerTask를 상속한 클래스. CountTimer의 count이 0이 되면 적절한 동작을
 * 하도록 되어있다.
 */
public class CountTimerTask extends TimerTask {

	CountTimer countTimer;
	
	/** 
	 * CountTimer 클래스 생성자 외부에서 별개로 생성하는 것은 추천하지 않습니다.
	 * @param countTimer 이 클래스를 호출한 CountTimer를 넣습니다. this로 해결합니다.
	 */
	public CountTimerTask(CountTimer countTimer) {
		this.countTimer = countTimer;
	}
	
	@Override
	public void run() {
		if(countTimer.getCount() <= 0 ) {
			countTimer.cancel(); 
		} else {
			countTimer.setCount();
		}
	}
	
}
