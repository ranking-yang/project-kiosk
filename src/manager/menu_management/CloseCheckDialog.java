package manager.menu_management;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import database.dto.InputDataSet;
import project.PosFrame;
import project.PosFrameProperties;

/** 관리자 모드를 종료할 것인지 물어보는 Dialog를 정의한 클래스 */
public class CloseCheckDialog extends JDialog{
	/** JDialog for CloseCheck */
	private static final long serialVersionUID = 5888249757948058595L;
	
	public CloseCheckDialog() {
		PosFrame frame = PosFrameProperties.frame;
			
		JLabel closingMent = new JLabel("<html><body><center>대기 화면으로 돌아갑니다.</center></body></html>");
		closingMent.setFont(new Font("견고딕", Font.BOLD, 20));
		
		JButton noButton = new JButton("취소");
		noButton.setFont(PosFrameProperties.HEAD_FONT);
		noButton.setBackground(PosFrameProperties.CLICK_COLOR);
		noButton.setFocusable(false);
		
		JButton okButton = new JButton("확인");
		okButton.setFont(PosFrameProperties.HEAD_FONT);
		okButton.setBackground(PosFrameProperties.RED_COLOR);
		okButton.setFocusable(false);
		
		add(closingMent);
		add(noButton);
		add(okButton);
		
		closingMent.setBounds(110, 30, 280, 90);
		noButton.setBounds(255, 150, 130, 70);
		okButton.setBounds(115, 150, 130, 70);
		
		setLayout(null);
		setBounds(frame.getX() + 200, frame.getY() + 350, 500, 285);
		getContentPane().setBackground(Color.WHITE);
		
		okButton.addActionListener((e) -> {
			frame.getMenu().getMenuLabelBtn().resetAllPages();

			frame.getMenu().getOrderList().deleteAll();
			InputDataSet.getAvailList().clear();

			frame.getDao().updateInfo();
			frame.getMenu().getMenuLabelBtn().updateMenuLabel();
			
			dispose();
			
			frame.getWaiting().waitingScreenThreadRun();
			PosFrameProperties.card.first(frame.getContentPane());
		});

		noButton.addActionListener((e) -> dispose());
		
		setModalityType(DEFAULT_MODALITY_TYPE);
		setVisible(true);
	}	
}
