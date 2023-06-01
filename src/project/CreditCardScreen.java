package project;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import database.dao.CouponDAO;
import database.dao.InputDataDAO;
import database.dto.InputDataSet;
import receipt.ReceiptChooseDialog;
import timer.CountTimer;
import timer.CreditCardTimerTask;

/** 최종 결제화면 */
public class CreditCardScreen extends JDialog {
	/** JDialog for PaymentScreen */
	private static final long serialVersionUID = -3644345961135899185L;
	
	private JLabel image, total;
	private JButton cancel, request;
	private int sum;
	private static Integer orderNumber = 0;
	
	public static final int END = 0;
	public static final int CARD = 1;
	public static final int QR = 2;
	
	static PosFrame frame = PosFrameProperties.frame;
	
	public CreditCardScreen(int paymentType, CouponDAO dao) {
		super(frame, "결제창");
		
		this.sum = PosFrameProperties.orderDetailNow.getSum();
		
		CountTimer timer = new CountTimer(30);
		
		int imageWidth = 240, imageHeight = 330;
		
		image = new JLabel();
		image.setBounds(130, 80, imageWidth, imageHeight);
		
		JPanel noticePanel = new JPanel();
		noticePanel.setBounds(0, 0, 500, 35);
		noticePanel.setBackground(PosFrameProperties.CLICK_COLOR);
		
		if(paymentType == CARD) {
			ImageMaker.setImage(image, "images/card.jpg", imageWidth, imageHeight); 
		} else if(paymentType == QR) {
			ImageMaker.setImage(image, "images/QRcode.png", imageWidth, imageHeight); 
		}
		
		total = new JLabel("합산금액:   " + PosFrameProperties.numberFormatter(sum));
		
		cancel = new JButton("취소");
		request = new JButton("승인요청");
		
		total.setBounds(120, 440, 270, 50);
		total.setOpaque(true);
		total.setBackground(Color.WHITE);
		total.setFont(PosFrameProperties.HEAD_FONT2);
		total.setVisible(true);
		
		cancel.setBounds(115, 500, 130, 70);
		cancel.setBackground(PosFrameProperties.CLICK_COLOR);
		cancel.setFont(PosFrameProperties.HEAD_FONT);
		cancel.setFocusable(false);
		
		request.setBounds(255, 500, 130, 70);
		request.setBackground(PosFrameProperties.RED_COLOR);
		request.setFont(PosFrameProperties.HEAD_FONT);
		request.setFocusable(false);
		
		cancel.addActionListener((e) -> {
			if(dao != null) dao.cancelAllResult();
			
			timer.setForceStop();
			dispose();
			
			InputDataSet.getAvailList().removeAll(InputDataSet.getAvailList());
			frame.getMenu().setShowingNow(true); 
		});
		
		request.addActionListener((e) -> {
			if(dao != null) dao.reflectAllResult();
			
			plusOrderNumber();
			
			timer.setForceStop();
			dispose();
			
			new InputDataDAO();
			new ReceiptChooseDialog().setVisible(true);
		});
		
		CreditCardTimerTask timerTask = new CreditCardTimerTask(timer, cancel);
		timer.schedule(timerTask, 0, 1000);
		
		add(noticePanel);
		add(image);
		add(total);
		add(cancel);
		add(request);
		
		setUndecorated(true);
		setResizable(false);
		setLayout(null);
		getContentPane().setBackground(Color.WHITE);
        setBounds(frame.getX() + 200,frame.getY() + 200,
        		frame.getWidth() - 400 ,frame.getHeight() - 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setModalityType(DEFAULT_MODALITY_TYPE);
	}
	
	/** 주문번호를 더해주는 메서드. 999가 되면 자동으로 0으로 초기화함. */
	public static void plusOrderNumber() {
		if (orderNumber >= 999) orderNumber = 0;
		
		++orderNumber;
	}
	
	public static Integer getOrderNumber() {
		return orderNumber;
	}

}
