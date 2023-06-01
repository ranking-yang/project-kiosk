package payment_ways;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import database.dto.InputDataSet;
import phone_button.PhoneNumberButton;
import project.CreditCardScreen;
import project.ImageMaker;
import project.PosFrame;
import project.PosFrameProperties;

/** 결제방식 선택창 표시를 위한 JDialog 상속 클래스
 *  JFrame 선언 후 listener와 함께 사용해야 합니다
 *  이 클래스만 별도로 호출하는 방식에는 응답하지 않습니다.
 *  되도록 PaymentsButton 클래스를 이용하여 호출하세요 */
public class PaymentsScreen extends JDialog { 
	private static final long serialVersionUID = 319683941534950954L;

	static final int defalutX = 50;  
	static final int defalutX2 = 220;  
	
	static final int width = 150;
	static final int height = 130;
		
	JButton cardBtn;
	JButton naverBtn;
	JButton kakaoBtn;
	JButton kkd_cash;
	
	JButton couponBtn;
	
	PosFrame frame;
	
	ButtonActionListener btnAction;
	
	/** 결제방식 선택창. 기본사이즈(700, 800).
	 *  bounds를 별도로 설정할 필요가 없습니다. 
	 *  메인프레임 사이즈(900, 1040)에 최적화 되어있습니다
	 *  이외의 사이즈에서는 의도하지 않은 오류가 있을 수 있습니다. */
	public PaymentsScreen() {
		this.frame = PosFrameProperties.frame;
        setTitle("결제방식 선택창");
		
        JPanel noticePanel = new JPanel();
		noticePanel.setBounds(0, 0, 760, 35);
		noticePanel.setBackground(PosFrameProperties.CLICK_COLOR);
        
        btnAction = new ButtonActionListener(this);
        
        setBounds(frame.getX() + 100,frame.getY() + 120,
        		frame.getWidth() - 200 ,frame.getHeight() - 240);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
        JLabel head1 = createHeadLabel("결제방식");
        JLabel head2 = createHeadLabel("쿠폰결제");
        
        head1.setLocation(defalutX - 10, 70);
        head2.setLocation(defalutX - 10, 440);

        cardBtn = paymentButton();
        naverBtn = paymentButton();
        kakaoBtn = paymentButton();
        kkd_cash = paymentButton();
        
        cardBtn.setLocation(defalutX, head1.getY() + 50);
        ImageMaker.setImage(cardBtn, "images/card2.jpg", width, height);
        ImageMaker.setBlurImage(cardBtn, "images/blur_card2.jpg", width, height);
        cardBtn.setBorderPainted(false);
        
        kakaoBtn.setLocation(defalutX, head1.getY() + 200);
        ImageMaker.setImage(kakaoBtn, "images/cacaopay.jpg", width, height);
        ImageMaker.setBlurImage(kakaoBtn, "images/blur_cacaopay.jpg", width, height);
        kakaoBtn.setBorderPainted(false);
        
        naverBtn.setLocation(defalutX2, head1.getY() + 200);
        ImageMaker.setImage(naverBtn, "images/naverpay.jpg", width, height);
        ImageMaker.setBlurImage(naverBtn, "images/blur_naverpay.jpg", width, height);
        naverBtn.setBorderPainted(false);
        
        kkd_cash.setLocation(defalutX2 + 170, head1.getY() + 200);
        ImageMaker.setImage(kkd_cash, "images/kkd_cash.jpg", width, height);
        ImageMaker.setBlurImage(kkd_cash, "images/blur_kkd_cash.jpg", width, height);
        kkd_cash.setBorderPainted(false);
        
        couponBtn = new PhoneNumberButton(PhoneNumberButton.CHECKNUM, CreditCardScreen.END);
       
        couponBtn.setLocation(defalutX, head2.getY() + 50);
        couponBtn.setSize(width, height);
        couponBtn.setFont(PosFrameProperties.HEAD_FONT);
        ImageMaker.setImage(couponBtn, "images/mega_coupon.jpg", width, height);
        ImageMaker.setBlurImage(couponBtn, "images/blur_mega_coupon.jpg", width, height);
        couponBtn.setBorderPainted(false);
        couponBtn.addActionListener(btnAction);
        
        JButton cancel = new JButton("취소");
        cancel.setBackground(PosFrameProperties.CLICK_COLOR);
        cancel.setFont(PosFrameProperties.HEAD_FONT2);
        cancel.setBounds(280, 680, 130, 70);
        cancel.setFocusable(false);
        
        cancel.addActionListener((e) -> {
        	dispose();
			InputDataSet.getAvailList().clear();
			frame.getMenu().setShowingNow(true); // 메인화면 시간초 작동
		});
        
        this.add(noticePanel);
        
        this.add(head1);
        this.add(cardBtn);
        this.add(naverBtn);
        this.add(kakaoBtn);
        this.add(kkd_cash);
        
        this.add(head2);
        this.add(couponBtn);
        
        this.add(cancel);
        
        setUndecorated(true);
        setModalityType(DEFAULT_MODALITY_TYPE);
	}
	
	/** 제목라벨을 생성해주는 메서드 */
	protected JLabel createHeadLabel(String text) {
		JLabel headLabel = new JLabel(text);
		
		headLabel.setSize(300,30);
		headLabel.setOpaque(true);
		headLabel.setBackground(PosFrameProperties.MAIN_COLOR);
		headLabel.setFont(PosFrameProperties.HEAD_FONT2);
		
		return headLabel;
	}
	
	/** 결제방식 버튼 생성해주는 메서드(포인트 적립 여부창)*/
	protected JButton paymentButton() {
		JButton btn = new JButton();
		
		btn.setSize(width, height);
		btn.setFont(PosFrameProperties.HEAD_FONT);
		
		btn.addActionListener(btnAction);
		
		return btn;
	}
	
}
