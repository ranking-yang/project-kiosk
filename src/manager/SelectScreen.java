package manager;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import manager.menu_management.CloseCheckDialog;
import project.ImageMaker;
import project.PosFrame;
import project.PosFrameProperties;

/** 관리자 로그인 성공시 나오는 화면 
 *  @author HONG */
public class SelectScreen extends JPanel {
	/** JPanel for Screen */
	private static final long serialVersionUID = 8927571322992279393L;
	
	private PosFrame frame = PosFrameProperties.frame;
	private Font font = PosFrameProperties.HEAD_FONT;
	
	private static final int start_X = 225; // 전체 컴퍼넌트의 x축 기준점
	private static final int start_Y = 340; // 전체 컴퍼넌트의 y축 기준점
	
	public SelectScreen() {
		setLayout(null);
        setBounds(frame.getX(), frame.getY(),
        		frame.getWidth(), frame.getHeight()); // 900, 1040
        
        JLabel head = new JLabel("관리자 모드");
        head.setBounds(start_X, start_Y - 110, 420, 100);
        head.setBackground(PosFrameProperties.RED_COLOR);
        head.setFont(PosFrameProperties.HEAD_FONT3);
        head.setHorizontalAlignment(JLabel.CENTER);
        head.setVerticalAlignment(JLabel.CENTER);
        
		JButton menu = new JButton("메뉴설정");
		menu.setBounds(start_X, start_Y, 200, 200); 
		menu.setBackground(new Color(235, 255, 242));
		setBtn(menu, "images/Manager_ModifyMenu.png");
		
		menu.addActionListener((e) -> {
			frame.getProductData().getDao().showMenu();
			PosFrameProperties.card.show(frame.getContentPane(), "p2");
		});
		
		JButton sales = new JButton("매출확인");
		sales.setBounds(menu.getX() + menu.getWidth() + 20, start_Y, 200, 200);
		sales.setBackground(new Color(206, 241, 254));
		setBtn(sales, "images/Manager_ShowSalesScore.png");
		
		sales.addActionListener((e) -> {
			frame.getSalesData().getRefresh().doClick();
			PosFrameProperties.card.show(frame.getContentPane(), "p3");
		});
		
		JButton out = new JButton("나가기");
		out.setBounds(menu.getX() + menu.getWidth()/2 + 43, start_Y + sales.getHeight() + 20,
				140, 60);
		out.setBackground(PosFrameProperties.CLICK_COLOR);
		out.setFocusable(false);
		out.setFont(font);

		out.addActionListener((e) -> new CloseCheckDialog());
		
		add(head);
		add(menu);
		add(sales);
		add(out);
		
		setOpaque(true);
		setBackground(new Color(239, 251, 255));
	}
	
	/** JButton의 공통부분 설정 */
	private void setBtn(JButton btn, String fileDirectory) {
		ImageMaker.setImage(btn, fileDirectory, 200, 150);
		btn.setFocusable(false);
		btn.setFont(font);
		btn.setVerticalTextPosition(JButton.BOTTOM);
		btn.setHorizontalTextPosition(JButton.CENTER);
	}
}
