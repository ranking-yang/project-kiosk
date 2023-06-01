package project;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import phone_button.PhoneNumberButton;

/**
 * 포인트 적립여부를 물어보는 화면을 정의한 클래스.
 * @author YANG
 */
public class PointSaveDialog extends JDialog {
	/** JDialog for PointSave */
	private static final long serialVersionUID = 7606585153328101865L;
	private static Font font = PosFrameProperties.HEAD_FONT;
	static PosFrame frame = PosFrameProperties.frame; 
	
	public PointSaveDialog(int paymentType) {
		super(frame, "포인트적립");
		
		JPanel noticePanel = new JPanel();
		noticePanel.setBounds(0, 0, 500, 35);
		noticePanel.setBackground(PosFrameProperties.CLICK_COLOR);
		
		JTextField pointSave = new JTextField("포인트를 적립하시겠습니까?");
		pointSave.setFont(PosFrameProperties.HEAD_FONT);
		pointSave.setBackground(Color.WHITE);
		pointSave.setHorizontalAlignment(JTextField.CENTER);
		pointSave.setBorder(null);
		pointSave.setEditable(false);
		pointSave.setBounds(90, 80, 320, 50);
		
		JButton noBtn = new JButton("아니오");
		noBtn.setFont(font);
		noBtn.setBounds(115, 160, 130, 70);
		noBtn.setBackground(PosFrameProperties.CLICK_COLOR);
		noBtn.setFocusable(false);
		noBtn.addActionListener((e) -> {
			dispose();
			new CreditCardScreen(paymentType, null).setVisible(true);
		});

		JButton yesBtn = new PhoneNumberButton(PhoneNumberButton.SAVINGPOINT, paymentType);
		yesBtn.setFont(font);
		yesBtn.setBounds(255, 160, 130, 70);
		yesBtn.setBackground(PosFrameProperties.RED_COLOR);
		yesBtn.setFocusable(false);
		yesBtn.addActionListener((e) -> dispose()); 
		
		add(noticePanel);
		add(pointSave);
		add(noBtn);
		add(yesBtn);
		
		setUndecorated(true);
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		setBounds(710, 355, 500, 285);
		setModalityType(DEFAULT_MODALITY_TYPE);
	}
	
}
