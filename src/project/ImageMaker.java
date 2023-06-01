package project;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/** 각 클래스에 흩어져있던 Image생성용 method 통합 클래스.
 *  static 메서드들이므로 인스턴스 생성 없이 사용하면 된다. */
public class ImageMaker {

	/** JButton에 지정한 경로의 이미지를 넣어주는 메서드
	 *  @param btn 이미지를 넣을 대상
	 *  @param fileDirectory 파일경로
	 *  @param width  가로길이
	 *  @param height 세로길이 */
	public static void setImage(JButton btn, String fileDirectory, int width, int height) {
		try {
			BufferedImage bufferedImage = 
					ImageIO.read(new File(fileDirectory));
			
			Image scaledImage = bufferedImage.getScaledInstance(
					width, height, Image.SCALE_AREA_AVERAGING);
			
			btn.setIcon(new ImageIcon(scaledImage));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 버튼 위에 커서 위치할 때 블러처리 된 사진 구현 메서드
	 *  @param btn 이미지를 넣을 대상
	 *  @param fileDirectory 파일경로
	 *  @param width  가로길이
	 *  @param height 세로길이 */
	public static void setBlurImage(JButton button, String fileDirectory, int width, int height) {
		try {
			BufferedImage bufferedImage = 
					ImageIO.read(new File(fileDirectory));
			
			Image scaledImage = bufferedImage.getScaledInstance(
					width + 5, height + 5, Image.SCALE_AREA_AVERAGING);
			
			button.setRolloverIcon(new ImageIcon(scaledImage));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** JLabel에 지정한 경로의 이미지를 넣어주는 메서드
	 *  @param label 이미지를 넣을 대상
	 *  @param fileDirectory 파일경로
	 *  @param width  가로길이
	 *  @param height 세로길이 */
	public static void setImage(JLabel label, String fileDirectory, int width, int height) {
		try {
			BufferedImage bufferedImage = 
					ImageIO.read(new File(fileDirectory));
			
			Image scaledImage = bufferedImage.getScaledInstance(
					width, height, Image.SCALE_AREA_AVERAGING);
			
			label.setIcon(new ImageIcon(scaledImage));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
