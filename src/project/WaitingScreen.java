package project;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;

/** 
 * 대기화면을 정의한 클래스.
 * 이미지를 지정할 수 있으며, 카드레이웃을 통해 다음 화면으로 넘어간다.
 */
public class WaitingScreen extends JButton {
	/** JButton for WaitingScreen */
	private static final long serialVersionUID = 68559311452205612L;
	
	static PosFrame parent = PosFrameProperties.frame;
	
	// 대기화면 이미지 파일 폴더 경로
	private String path = "images/waitingScreen/";
	
	// 대기화면용 이미지 파일 이름(확장자까지)
	private String[] imagesArr = {
			"Bee my Honey.jpg", "MEGA MGC STICK.jpg", "Son_Heung_min2.jpg",
			"TRAVEL THE Summer.jpg", "TRAVEL THE Summer2.jpg", "Son_Heung_min.jpg"
	};	
	
	private int imageIndex = 1;
	
	private boolean screenShowing;
	
	private TimerTask imageTask;
	
	public WaitingScreen() {
		screenShowing = true;
		
		int imageWidth = 890;
		int imageHeight = 1000;
		
		ImageMaker.setImage(this, path + imagesArr[0], imageWidth, imageHeight); 
		
		imageTask = new TimerTask() {
			@Override
			public void run() {
				if(!screenShowing) return;
				
				if(imageIndex == imagesArr.length) imageIndex = 0;
				
				ImageMaker.setImage(PosFrameProperties.frame.getWaiting(), path + imagesArr[imageIndex++], imageWidth, imageHeight);
			}
		}; 

		new Timer(true).schedule(imageTask, 5000, 5000);
		
		addActionListener((e) -> {
			screenShowing = false; 
			parent.getMenu().getTimerLabel().setText(String.valueOf(parent.getTimer().getFixTime()));
			parent.getTimer().resetCount();
			parent.getMenu().setShowingNow(true);
			parent.getMenu().getCategoryLabel().getButton(1).doClick();
			PosFrameProperties.card.show(parent.getContentPane(), "p1");
		}); 
		
		setFocusable(false);
		setVisible(true);
	}

	/** 
	 *  대기화면 쓰레드를 실행시켜주는 메서드.
	 *  대기화면 사진 전환 기능을 다시 수행시키려면 대기화면으로 돌아오는 기능을 가진 곳에 반드시 넣어줘야 한다. 
	 */
	public void waitingScreenThreadRun() {
		screenShowing = true;
		imageTask.run();
	}
	
}