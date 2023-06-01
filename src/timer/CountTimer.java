package timer;

import java.util.Timer;

/** 
 * [영수증출력], [주문번호] 등에서 쓰이는 카운트 기능.
 * count에 주어진 값을 -1씩 수정하면서 0까지 센다.
 * 0이 되어도 시스템을 종료하거나 하는 기능은 없다. 
 * getCount() 메서드를 통해 원하는 기능 설정이 필요하다.
 * 이 Count클래스에는 count 멤버변수를 초기화할 수 있는 메서드는 별도로 없다.
 */
public class CountTimer extends Timer {

	private int count;

	/**
	 * 매개변수 count에서 1초당 -1을 하는 Timer 클래스와 TimerTask 클래스가 함께 생성됩니다.  
	 * @param count 주어진 값부터 0까지 카운트를 진행한다. 음수값이 주어지면 예외가 발생한다.
	 * @throws IllegalArgumentException if {@code count} is negative.
	 */
	public CountTimer(int count) {
		super(true);
		
        if (count < 0) throw new IllegalArgumentException("음수값은 허용되지 않습니다.");
        
		this.count = count;
	}

	void setCount() {
		--count;
	}
	
	public int getCount() {
		return count;
	}
	
	/** Thread 종료시키기 위한 메서드 */
	public void setForceStop() {
		count = -1;
	}
}
