package project;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import timer.CountTimer;
import timer.OrderNumberTask;

/** 대기번호를 보여주는 화면을 정의한 클래스 */
public class OrderNumberDialog extends JDialog {
	/** JDialog for OrderNumber*/
	private static final long serialVersionUID = 5893136854342270773L;
	static PosFrame frame = PosFrameProperties.frame;
	
	static Font font = PosFrameProperties.HEAD_FONT;
	static Font font2 = PosFrameProperties.HEAD_FONT2;
	
	public OrderNumberDialog() {
		super(frame);
		
		CountTimer timer = new CountTimer(4);
		
		JPanel noticePanel = new JPanel();
		noticePanel.setSize(500, 35);
		noticePanel.setBackground(PosFrameProperties.CLICK_COLOR);
		
		JTextField myOrderNo = new JTextField("고객님의 주문번호는");
		myOrderNo.setFont(font2);
		myOrderNo.setEditable(false);
		myOrderNo.setBackground(Color.WHITE);
		myOrderNo.setBorder(null);
		myOrderNo.setBounds(120, 50, 240, 50);
		
		JTextField currOrderNo = new JTextField(CreditCardScreen.getOrderNumber().toString());
		currOrderNo.setHorizontalAlignment(JTextField.CENTER);
		currOrderNo.setFont(font2);
		currOrderNo.setEditable(false);
		currOrderNo.setBackground(Color.WHITE);
		currOrderNo.setBorder(null);
		currOrderNo.setBounds(120, 100, 240, 50);
		
		JButton timer4sc = new JButton();
		timer4sc.setFont(font);
		timer4sc.setBounds(165, 170, 170, 70);
		timer4sc.setBackground(PosFrameProperties.MAIN_COLOR);
		timer4sc.setFocusable(false);
		timer4sc.addActionListener((e) -> {
			timer.setForceStop();
			dispose();
			frame.getMenu().getHomeBtn().doClick(); 
		});
		
		timer.schedule(new OrderNumberTask(timer, timer4sc), 0, 1000);
		
		add(noticePanel);
		add(myOrderNo);
		add(currOrderNo);
		add(timer4sc);
		
		setUndecorated(true);
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(710, 355, 500, 285);
		setModalityType(DEFAULT_MODALITY_TYPE);
	}
	
}
