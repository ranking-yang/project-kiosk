package project;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.text.NumberFormat;
import java.util.Locale;

/** 포스프레임의 속성들을 정의한 클래스 */
public class PosFrameProperties {

	public static PosFrame frame;
	public static CardLayout card;
	
	public PosFrameProperties(PosFrame frame, CardLayout card) {
		PosFrameProperties.frame = frame;
		PosFrameProperties.card = card;
	}

	// OrderDetail에 기록된 정보가 필요한 클래스에서 사용. 
	// 새로운 OrderDetailScreen 클래스가 생성될 때마다 변경됨
	public static OrderDetailScreen orderDetailNow;
	
	// product images 파일 폴더 경로
	final public static String PRODUCT_IMAGE_DIR = "images/products/";
	
	// 공용 컬러 모음
	final public static Color MAIN_COLOR = new Color(253, 209, 0);
	final public static Color CLICK_COLOR = new Color(228, 161, 26);
	final public static Color RED_COLOR = new Color(235, 65, 19);
	
	// 공용 폰트 모음
	final public static Font HEAD_FONT = new Font("휴먼모음", Font.BOLD, 20);
	final public static Font HEAD_FONT2 = new Font("휴먼모음", Font.BOLD, 25);
	final public static Font HEAD_FONT3 = new Font("휴먼모음", Font.BOLD, 35);
	
	final public static Font BASIC = new Font("견고딕", Font.PLAIN, 16);
	final public static Font BASIC2 = new Font("견고딕", Font.BOLD, 20);
	final public static Font SMALL_BASIC = new Font("견고딕", Font.PLAIN, 14);
	final public static Font SMALL_BASIC2 = new Font("견고딕", Font.BOLD, 17);
	
	final public static Font PHONE_NUMBER = new Font("휴먼모음", Font.BOLD, 50);
	
	/** int에 세자리수마다 ,를 찍어주는 메서드 */
	public static String numberFormatter(int price) {
		NumberFormat numberFormat = NumberFormat.getInstance(Locale.KOREA);
		
		return numberFormat.format(price);
	}
	
}
