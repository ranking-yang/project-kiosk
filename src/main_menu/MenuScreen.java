package main_menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import database.dto.InputDataSet;
import main_menu.order_list.OrderList;
import manager.LoginScreen;
import project.ImageMaker;
import project.PosFrame;
import project.PosFrameProperties;

/** 메뉴선택창.
 *  다른 모든 작업들은 이 Panel위에서 이루어진다.
 *  @author YANG */
public class MenuScreen extends JPanel {
	/** JPanel for MenuScreen */
	private static final long serialVersionUID = 5189910367038791579L;

	static PosFrame parent = PosFrameProperties.frame;
	
	Font font = PosFrameProperties.HEAD_FONT;
	
	Color mainColor = PosFrameProperties.MAIN_COLOR;
	Color clickColor = PosFrameProperties.CLICK_COLOR;
	Color redColor = PosFrameProperties.RED_COLOR;
	
	private boolean showingNow;
	
	private JButton homeBtn;
	
	private JLabel timerLabel;
	
	private OrderList orderList;
	
	private PaymentButton payBtn;
	
	private MenuLabelButtons menuLabelBtn;
	
	private JLayeredPane layeredPane;
	
	private CategoryLabel categoryLabel;
	
	public MenuScreen() {
		int parentWitdh = parent.getWidth();
		int parentHeight = parent.getHeight();
		
		JLabel imageLabel = new JLabel();
		imageLabel.setSize(parentWitdh, parentHeight);
		ImageMaker.setImage(imageLabel, "images/mainColor.jpg", parentWitdh, parentHeight);
		
		JLabel homeImage = new JLabel();
		homeImage.setBounds(261, 5, 360, 50);
		ImageMaker.setImage(homeImage, "images/mega_logo2.jpg", 350, 50);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(20, 195, 845, 490);
		
		menuLabelBtn = new MenuLabelButtons(layeredPane);
		
		// 홈버튼. 초기화버튼.
		homeBtn = new JButton();
		homeBtn.setFont(font);
		homeBtn.setBounds(815, 5, 50, 50);
		homeBtn.addActionListener((e) -> {
			menuLabelBtn.resetAllPages();
			orderList.deleteAll();
			InputDataSet.getAvailList().clear();
			PosFrameProperties.card.first(parent.getContentPane());
			setShowingNow(false);
			parent.getWaiting().waitingScreenThreadRun();
		});
		homeBtn.setBorderPainted(false);
		homeBtn.setFocusable(false);
		ImageMaker.setImage(homeBtn, "images/home.jpg", 50, 50);
		
		// 관리자 진입 버튼 		
		JButton managerBtn = new JButton();
		managerBtn.setFont(font);
		managerBtn.setBounds(20, 10, 45, 45);
		managerBtn.addActionListener((e) -> {
				setShowingNow(false);
				new LoginScreen().setVisible(true);
		});
		managerBtn.setBorderPainted(false);
		managerBtn.setFocusable(false);
		ImageMaker.setImage(managerBtn, "images/manager.jpg", 45, 45);
		
		// 현재 페이지를 표시해주는 라벨
		JLabel page = new JLabel();
		page.setBounds(372, 685, 140, 50);
		page.setHorizontalAlignment(SwingConstants.CENTER);
		add(page);
		
		// 카테고리 버튼들을 생성해주는 라벨클래스
		categoryLabel = new CategoryLabel(layeredPane, menuLabelBtn, page);
		
		// 메뉴카테고리 이전/다음 버튼 클래스
		new BeforeAfterButton(this, categoryLabel);
		
		// 오더리스트 생성(orderPanel위에 부착)
		JPanel orderPanel = new JPanel();
		orderPanel.setBounds(20, 735, 555, 245);
		orderPanel.setLayout(new BorderLayout());
		this.orderList = new OrderList(555, 245);
		orderPanel.add(orderList);
		
		// 남은 주문 가능 시간나타내주는 부분
		timerLabel = new JLabel();
		timerLabel.setFont(PosFrameProperties.PHONE_NUMBER);
		timerLabel.setBounds(590, 740, 160, 130);
		timerLabel.setBackground(mainColor);
		timerLabel.setHorizontalAlignment(JLabel.CENTER);
		timerLabel.setVerticalAlignment(JLabel.CENTER);
		
		// 오더리스트를 초기화시켜주는 버튼
		JButton allRemoveBtn = new JButton("<html>전체<br>삭제");
		allRemoveBtn.setFont(font);
		allRemoveBtn.setBounds(760, 740, 105, 130);
		allRemoveBtn.setBackground(mainColor);
		allRemoveBtn.addActionListener((e) -> orderList.deleteAll()); 
		allRemoveBtn.setFocusable(false);
		
		// 결제버튼
		payBtn = new PaymentButton(590, 890, 275, 90, this);
		payBtn.setFocusable(false);
		
		add(layeredPane);
		add(homeImage);
		add(managerBtn);
		add(homeBtn);
		add(categoryLabel);
		add(orderPanel);
		add(timerLabel);
		add(allRemoveBtn);
		add(payBtn);
		add(imageLabel);
		
		setOpaque(true);
		setBackground(Color.ORANGE);
		setLayout(null);
		setBounds(510, 5, 900, 1040);
		setVisible(true);
	}

	/** 메인메뉴의 타이머가 동작해야하는지 여부를 정함 */
	public void setShowingNow(boolean showingNow) {
		this.showingNow = showingNow;
	}

	public boolean isShowingNow() {
		return showingNow;
	}

	public JButton getHomeBtn() {
		return homeBtn;
	}
	
	public OrderList getOrderList() {
		return orderList;
	}
	
	public PaymentButton getPaymentButton() {
		return this.payBtn;
	}

	public MenuLabelButtons getMenuLabelBtn() {
		return menuLabelBtn;
	}

	public JLabel getTimerLabel() {
		return timerLabel;
	}
	
	public JLayeredPane getMenuLayeredPane() {
		return layeredPane;
	}
	
	public CategoryLabel getCategoryLabel() {
		return categoryLabel;
	}
}


