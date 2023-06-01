package timer;

import java.util.Timer;

/** 메인화면에서 120초 미응답 시 대기화면으로 이동하는 카운트
 *  getCount() 메서드를 통해 남은 시간을 확인할 수 있다 
 *  대기화면으로 이동하는 코드를 별도로 구현해야 한다
 *  메인화면에서 사용자의 동작을 감지하는 코드를 구현해서 
 *  setCount() 메서드를 통해 120초를 초기화시켜야 한다 */
public class MainTimer extends Timer {

	private static final int fixTime = 120;
	private static int count = fixTime;
	
	/** 120초의 카운트다운을 시작합니다.
	 *  MenuScreen 클래스의 showingNow 멤버변수가 true인 경우에만 작동합니다. */
	public MainTimer() {
		super(true);
	}
	
	/** 현재 남은 시간을 보여주는 메서드 */
	public int getCount() {
		return count;
	}
	
	public int getFixTime() {
		return fixTime;
	}
	
	/** 120초로 잔여시간을 초기화해주는 메서드 */
	public void resetCount() {
		MainTimer.count = fixTime;
	}
	
	public void setCount() {
		--MainTimer.count;
	}
	
}
