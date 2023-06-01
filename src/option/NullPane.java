package option;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import project.ImageMaker;

/**옵션값이 없는 제품에 사용될 JLayeredPane*/
public class NullPane extends JLayeredPane {
	/** JLayeredPane for NullPane */
	private static final long serialVersionUID = -8393683106426103184L;

	JLabel label;
	
	JButton btn1;
	JButton btn2;
	JButton btn3;
	
	public NullPane() {
		
		JLabel noOptionImage = new JLabel();
		int width = 400;
		int height = 220;
		noOptionImage.setBounds(0, 0, width, height);
		ImageMaker.setImage(noOptionImage, "images/noOption.jpg", width, height);
		add(noOptionImage);
		
		JLabel noOptionImage2 = new JLabel();
		noOptionImage2.setBounds(250, 200, 300, 300);
		ImageMaker.setImage(noOptionImage2, "images/sonKick.jpg", 300, 300);
		add(noOptionImage2);
		
	}
	
}
