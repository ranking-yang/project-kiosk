package option;

import java.awt.Color;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import database.dao.DataCollection;
import main_menu.menu_label.MenuButton;
import main_menu.order_list.OrderList;
import project.PosFrame;
import project.PosFrameProperties;

/**옵션창을 보여주는 JDialog*/
public class OptionScreen extends JDialog {
	/** JDialog for OptionScreen */ 
	private static final long serialVersionUID = -6761149390981004038L;
	
	private OrderList orderList;
	private MenuButton selected;
	private Integer price;
	private String className;
	
	public static int paneX;
	public static int paneY;
	public static int paneWidth;
	public static int paneHeight;
	
	static PosFrame frame = PosFrameProperties.frame;
	
	public OptionScreen(MenuButton selected) {
		super(frame, "옵션창");
		
		this.orderList = frame.getMenu().getOrderList();
		this.selected = selected;
		
		ShowOption options = new ShowOption(selected);
		
		JPanel noticePanel = new JPanel();
		noticePanel.setBounds(0, 0, 760, 35);
		noticePanel.setBackground(PosFrameProperties.CLICK_COLOR);
		
		JLabel notice = new JLabel();
		notice.setText("선택하신 상품의 옵션을 선택해주세요.");
		notice.setFont(PosFrameProperties.HEAD_FONT);
		noticePanel.add(notice);
		
		JLabel notice2 = new JLabel();
		notice2.setFont(PosFrameProperties.BASIC);
		notice2.setBounds(400, 45, 500, 25);
		
		JLabel productName = new JLabel();
		productName.setText(selected.getPd_name());
		productName.setBounds(30, 45, 350, 25);
		productName.setFont(PosFrameProperties.HEAD_FONT);
		
		int x = 30;
		int y = 80;
		
		List<JLayeredPane> paneList = options.availablePaneList();
		
		for (int i = 0; i < paneList.size(); ++i) {
			JLayeredPane pane = paneList.get(i);
			className = pane.getClass().getName();
			
			if (className.equals("option.NullPane")) {
				pane.setBounds(120, 270, 700, 800);
			} else {
				pane.setBounds(x, y, 700, 160);
				notice2.setText("변경을 원하지 않으시면 주문버튼을 눌러주세요.");
			}
			
			getContentPane().add(pane);
			y += 170;
		}
		
		JButton cancel = new JButton("취소");
		cancel.setBackground(PosFrameProperties.CLICK_COLOR);
		cancel.setFont(PosFrameProperties.HEAD_FONT2);
		cancel.setFocusable(false);
		
		cancel.addActionListener((e) -> {
			SerialNumber.serialNumber = new Integer[] {1, 1, 1, 0, 1};
			SerialNumber.options = new String[] {"기본", "기본", "기본", "기본", "기본"};
			
			dispose();

			frame.getMenu().setShowingNow(true);
		});
		
		JButton order = new JButton("주문");
		order.setBackground(new Color(235, 65, 19));
		order.setFont(PosFrameProperties.HEAD_FONT2);
		order.setFocusable(false);
		
		order.addActionListener((e) -> {
			orderList.addSelectedMenu(selectedMenu());
			SerialNumber.serialNumber = new Integer[] {1, 1, 1, 0, 1};
			SerialNumber.options = new String[] {"기본", "기본", "기본", "기본", "기본"};
			
			dispose();
			
			frame.getMenu().setShowingNow(true);
		});
		
		cancel.setBounds(225, 770, 130, 70);
		order.setBounds(380, 770, 130, 70);
		
		add(notice2);
		add(noticePanel);
		add(productName);
		add(cancel);
		add(order);
		
		setUndecorated(true);
		setResizable(true);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(580, 75, 760, 900);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setVisible(true);
	}

	/** 옵션창에서 선택된 제품을 오더리스트로 전달해주는 메서드 */
	private String[] selectedMenu() {
		String name = selected.getPd_name();
		String serialNumber = "";
		String options = "";
		Integer pd_id = selected.getPd_id();
		price = selected.getPd_price();
		
		if (this.className.equals("option.NullPane")) {
			orderList.addBasicPriceList(price);
			return new String[] {"", name, "", "", "1", "", price.toString(), pd_id.toString() + "/00000"};
		}
		
		for (int i = 0; i < SerialNumber.serialNumber.length; ++i) {
			serialNumber += SerialNumber.serialNumber[i];
			
			if (SerialNumber.options[i] != null) {
				options += SerialNumber.options[i] + "/";
			}
		}
		
		Integer optionPrice = DataCollection.getOptionMap().putIfAbsent(serialNumber, 5000);
		
		price += optionPrice;
		
		orderList.addBasicPriceList(price);
		//{"삭제", "음료이름", "옵션", "+", "개수", "-", "가격", "일련번호"};
		String[] rowForOrderList = {"", name, options, "", "1", "", price.toString(),
				pd_id.toString() + "/" + serialNumber};
		
		return rowForOrderList;
	}

}
