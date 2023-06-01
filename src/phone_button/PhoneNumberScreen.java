package phone_button;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import database.dao.CouponDAO;
import database.dto.InputDataSet;
import project.CreditCardScreen;
import project.PosFrame;
import project.PosFrameProperties;

/** 휴대폰 번호 입력 팝업창
 *  JFrame 선언 후 listener와 함께 사용해야 합니다
 *  이 클래스만 별도로 호출하는 방식에는 응답하지 않습니다.
 *  되도록 PhoneNumberButton 클래스를 이용하여 호출하세요 */
public class PhoneNumberScreen extends JDialog {
	/** JDialog for Teamproject */
	private static final long serialVersionUID = -8829278244201899669L;
	
	private final static int defaultX = 20;
	private String TotalNumber = "010-"; 

	public final static int SAVINGPOINT = 0;
	public final static int CHECKNUM = 1;
	
	int type;
	
	int paymentType;
	
	PosFrame frame;
	
    JButton
    num1, num2, num3,
    num4, num5, num6,
    num7, num8, num9,
    num010, num0, numDelete;
    
    JButton[] btns = {num1, num2, num3, num4, num5, num6, num7, num8, num9, num010, num0, numDelete};
    String[] btnText = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "010", "0", "←"};
    
    JButton lastBtn;
	
    JLabel phoneNum;

    NumberActionListener numBtnAction;
    
	/** 결제방식 선택창. 기본사이즈(500, 640)
	 *  bounds를 별도로 설정할 필요가 없습니다. 
	 *  메인프레임 사이즈(900, 1040)에 최적화 되어있습니다
	 *  이외의 사이즈에서는 의도하지 않은 오류가 있을 수 있습니다. */
	public PhoneNumberScreen(String name, int type, int paymentType) {
        this.frame = PosFrameProperties.frame;
        this.type = type;
        this.paymentType = paymentType;
        
        setTitle(name);
        
		CouponDAO dao = new CouponDAO();
		
        JPanel noticePanel = new JPanel();
		noticePanel.setBounds(0, 0, 500, 35);
		noticePanel.setBackground(PosFrameProperties.CLICK_COLOR);
		noticePanel.setLayout(new BorderLayout());
		
		JButton outBtn = new JButton("X");
		outBtn.setBounds(62, 0, 35, 32);
		outBtn.setFont(PosFrameProperties.HEAD_FONT);
		outBtn.setBackground(PosFrameProperties.CLICK_COLOR);
		outBtn.setBorderPainted(false);
		outBtn.setFocusable(false);
		outBtn.addActionListener((e) -> {
			dao.cancelAllResult(); 
			
			dispose();
			
			if(paymentType == CreditCardScreen.CARD || paymentType == CreditCardScreen.QR) {
				new CreditCardScreen(paymentType, null).setVisible(true);
			}
				
			if(paymentType == CreditCardScreen.END) {
				InputDataSet.getAvailList().removeAll(InputDataSet.getAvailList()); 
				frame.getMenu().setShowingNow(true); 
			}
		});
		noticePanel.add(outBtn, BorderLayout.EAST);
        add(noticePanel);
        
        JLabel headLabel = new JLabel("휴대폰번호 입력");
    	headLabel.setBounds(defaultX, 35, 460, 70);
		headLabel.setOpaque(true);
		headLabel.setBackground(Color.WHITE);
		headLabel.setFont(PosFrameProperties.HEAD_FONT2);
		headLabel.setHorizontalAlignment(JLabel.CENTER);
        add(headLabel);

        phoneNum = new JLabel(TotalNumber); 
    	phoneNum.setBounds(defaultX + 75, headLabel.getY() + 80, 460, 70);
    	phoneNum.setFont(PosFrameProperties.PHONE_NUMBER);
    	phoneNum.setHorizontalAlignment(JLabel.LEFT);
        add(phoneNum);
        
        Color color = new Color(253, 246, 227);
        
        JPanel pad = new JPanel();
        pad.setBounds(defaultX, phoneNum.getY() + 80, 460, 340);
    	pad.setLayout(new GridLayout(4,3));
    	
    	numBtnAction = new NumberActionListener(this);
    	
        for(int i = 0; i < btns.length; ++i) {
        	btns[i] = createNumButton(btnText[i]);
        	btns[i].setBackground(color);
        	btns[i].addActionListener(numBtnAction);
        	pad.add(btns[i]);
        }
        add(pad);
    	
        lastBtn = new JButton();
        
    	if(type == CHECKNUM) {
    		lastBtn.setText("쿠폰조회");
    	} else {
    		lastBtn.setText("포인트적립");
    	}
    	
    	LastBtnActionListener lastBtnAction = new LastBtnActionListener(this, type, dao);
    	
    	lastBtn.setBounds(defaultX, pad.getY() + pad.getHeight() + 10, 460, 70);
    	lastBtn.setFont(PosFrameProperties.HEAD_FONT2);
        lastBtn.setFocusable(false);
        lastBtn.addActionListener(lastBtnAction);
        lastBtn.setBackground(PosFrameProperties.CLICK_COLOR);
        add(lastBtn);
        
        setUndecorated(true);
        getContentPane().setBackground(Color.WHITE);
        setResizable(false);
        setBounds(frame.getX() + 200,frame.getY() + 200,
        		frame.getWidth() - 400 ,frame.getHeight() - 400);
        setLayout(null);
        setModalityType(DEFAULT_MODALITY_TYPE);
	}
	
	/** 숫자 버튼 생성해주는 메서드. 숫자 버튼 설정을 일괄적으로 변환가능 */
	private JButton createNumButton(String num) {
		JButton btn = new JButton(num);
		
		btn.setSize(150, 50);
		btn.setFont(PosFrameProperties.HEAD_FONT2);
		btn.setFocusable(false);
		
		return btn;
	}

	/** 하이푼(-)을 제외한 순수 숫자로 11자리 추출 */
	public String getPhoneNumber() {
		StringBuilder sb = new StringBuilder(numBtnAction.getPhoneNumStr());
		
		if(sb.length() > 3) {
			sb.deleteCharAt(3);
			if(sb.length() > 7) {
				sb.deleteCharAt(7);
			}
		}
		
		return sb.toString();
	}
	
	public String getTotalNumber() {
		return TotalNumber;
	}

	public void setTotalNumber(String totalNumber) {
		TotalNumber = totalNumber;
	}
	
}
