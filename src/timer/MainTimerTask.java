package timer;

import java.awt.Color;
import java.util.TimerTask;

import javax.swing.JLabel;

import main_menu.MenuScreen;
import project.PosFrameProperties;

/** 메인 타이머의 동작을 정의하는 클래스 */
public class MainTimerTask extends TimerTask {
	
	MenuScreen parent;
	MainTimer timer;
	
	JLabel label;

	public MainTimerTask(MainTimer timer, JLabel label) {
		 this.parent = PosFrameProperties.frame.getMenu();
		 this.timer = timer;
		 this.label = label;
	}
	
	@Override
	public void run() {
		int count = timer.getCount();
		if(count < 0) return;
		
		if(parent.isShowingNow()) {
			timer.setCount();   
			
			label.setText("" + count); 
			Color nowColor = label.getForeground();
			
			if(count >= 11 && nowColor != Color.black)
				label.setForeground(Color.black);
			
			if(count < 11 && nowColor != Color.red)
				label.setForeground(Color.red);
			
			if(count <= 0) {
				parent.getHomeBtn().doClick();
			}
		}
	}
	
}
