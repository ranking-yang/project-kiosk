package project;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import database.dto.OrderListDTO;
import database.dto.InputDataSet;
import main_menu.MenuScreen;
import payment_ways.PaymentsButton;

/** 주문 세부내역 화면을 정의한 클래스. */
public class OrderDetailScreen extends JDialog {
	/** JDialog for OrderDetail */
	private static final long serialVersionUID = 6025640523550216647L;
	
	private int countForCoupon = 0;
	
	private Integer sumBvgResult = 0;
	private Integer sumPriceResult = 0;
	MenuScreen parent;
	
	JButton takeIn;
	JButton takeOut;
	
	private Font gothicExtraBold30 = new Font("견고딕", Font.BOLD, 30);
	private Color ambiguousGray = new Color(230, 230, 230);
	@SuppressWarnings("rawtypes")
	Vector<Vector> productList;
	
	@SuppressWarnings("unchecked")
	public OrderDetailScreen(PosFrame frame, MenuScreen parent, String name) {
		super(frame, name);
		
		PosFrameProperties.orderDetailNow = this;

		this.parent = parent;
		this.productList = parent.getOrderList().getOrderListData();
		String [] col = {"No.", "제품명", "옵션", "개수", "금액"};
		String [][] row = new String[productList.size()][5];
		
		JPanel noticePanel = new JPanel();
		noticePanel.setBounds(0, 0, 760, 35);
		noticePanel.setBackground(PosFrameProperties.CLICK_COLOR);	
		
		JButton backBtn = new JButton("돌아가기");
		backBtn.setBackground(new Color(228, 161, 26));
		backBtn.setFont(PosFrameProperties.HEAD_FONT2);     
		backBtn.setFocusable(false);
        takeIn = new PaymentsButton("<html>먹고가기<br>다회용컵", this, PaymentsButton.TAKE_IN);
        takeIn.setBackground(PosFrameProperties.MAIN_COLOR);
        takeIn.setFocusable(false);
        takeOut = new PaymentsButton("<html>포장하기<br>일회용컵", this, PaymentsButton.TAKE_OUT);
        takeOut.setBackground(new Color(235, 65, 19));
        takeOut.setFocusable(false);

        for (int i = 0; i < productList.size(); ++i) {

        	OrderListDTO orderListDTO = new OrderListDTO();
        	//{"삭제", "음료이름", "옵션", "+", "개수", "-", "가격", "제품번호/옵션번호"}
        	Vector<String> productInfo = productList.get(i);
        	
            row[i][0] = i + 1 +".";
        	row[i][1] = productInfo.get(1);
        	row[i][2] = productInfo.get(2);
        	row[i][3] = productInfo.get(4);
        	row[i][4] = PosFrameProperties.numberFormatter(Integer.parseInt(productInfo.get(6)));

        	String[] pdOp = productInfo.get(7).split("/");
        	
        	Integer pd_id = Integer.parseInt(pdOp[0]);
        	String op_id = pdOp[1];
        	Integer count = Integer.parseInt(productInfo.get(4));
        	Integer price = Integer.parseInt(productInfo.get(6));
        	String pd_name = productInfo.get(1);
        	
        	orderListDTO.setPd_id(pd_id);
        	orderListDTO.setOp_id(op_id);
        	orderListDTO.setAlist_count(count);
        	orderListDTO.setAl_price(price);
        	orderListDTO.setPd_name(pd_name);
        	
        	InputDataSet.addAvailableListDTO(orderListDTO);        	
        }
        
        DefaultTableModel model = new DefaultTableModel(row,col) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();		
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		JTable orderList = new JTable(model);
		orderList.getColumnModel().getColumn(0).setPreferredWidth(30);
		orderList.getColumnModel().getColumn(1).setPreferredWidth(220);
		orderList.getColumnModel().getColumn(2).setPreferredWidth(220);
		orderList.getColumnModel().getColumn(3).setPreferredWidth(50);
		orderList.getColumnModel().getColumn(4).setPreferredWidth(100);
		
		orderList.setRowHeight(50);
		
		orderList.getColumnModel().getColumn(0).setCellRenderer(dtcr);
		orderList.getColumnModel().getColumn(2).setCellRenderer(dtcr);
		orderList.getColumnModel().getColumn(3).setCellRenderer(dtcr);
		orderList.getColumnModel().getColumn(4).setCellRenderer(dtcr);
		
		orderList.getTableHeader().setReorderingAllowed(false);
		orderList.getTableHeader().setResizingAllowed(false);
		
		orderList.getTableHeader().setFont(PosFrameProperties.BASIC2);
		orderList.getTableHeader().setBackground(PosFrameProperties.MAIN_COLOR);
		orderList.setShowGrid(false);
		
		orderList.setFont(PosFrameProperties.BASIC2);
				
        JScrollPane scrollForList = new JScrollPane(orderList);   
        scrollForList.setBounds(30, 60, 685, 400);
        scrollForList.getVerticalScrollBar().setValue(40);
        scrollForList.setSize(685, 400);        
        scrollForList.getViewport().setBackground(Color.WHITE);
        
        scrollForList.getVerticalScrollBar().setPreferredSize(new Dimension(0,10));
             	
        
        for (int i = 0; i < productList.size(); ++i) {
        	//{"삭제", "음료이름", "옵션", "+", "개수", "-", "가격", "제품번호/옵션번호"}
        	Vector<String> productInfo = productList.get(i);
        	Integer eachPrice = Integer.parseInt(productInfo.get(6));
        	Integer eachBvg = Integer.parseInt(productInfo.get(4));
        	
        	sumPriceResult += eachPrice;
        	sumBvgResult += eachBvg;
        	
        	Integer eachCount = Integer.parseInt(productInfo.get(4));
        	countForCoupon += eachCount;
        }
        
        JTextField sumBvg = new JTextField("   총 품목 수 :");
        JTextField sumBvgField = new JTextField();
        JTextField sumPrice = new JTextField("   합산금액   :");
        JTextField sumPriceField = new JTextField();
        
        sumBvg.setEditable(false);
        sumBvg.setFont(gothicExtraBold30);
        sumBvg.setBorder(null);
        
        sumBvgField.setFont(gothicExtraBold30);
        sumBvgField.setForeground(Color.RED);
        sumBvgField.setBorder(null);
        sumBvgField.setText(sumBvgResult.toString() + " ");
        sumBvgField.setHorizontalAlignment(SwingConstants.RIGHT);
        
        sumPrice.setEditable(false);
        sumPrice.setFont(gothicExtraBold30);
        sumPrice.setBorder(null);
        
        sumPriceField.setFont(gothicExtraBold30);
        sumPriceField.setForeground(Color.RED);
        sumPriceField.setBorder(null);
        sumPriceField.setText(PosFrameProperties.numberFormatter(sumPriceResult) + " ");
        sumPriceField.setHorizontalAlignment(SwingConstants.RIGHT);
        
        sumBvg.setBackground(ambiguousGray);
        sumBvgField.setBackground(ambiguousGray);
        sumPrice.setBackground(ambiguousGray);
        sumPriceField.setBackground(ambiguousGray);
        
        sumBvg.setBounds(390, 515, 180, 40);
        sumBvgField.setBounds(570, 515, 144, 40);
        sumPrice.setBounds(390, 555, 180, 40);
        sumPriceField.setBounds(570, 555, 144, 40);
        
        JPanel topSpace = new JPanel();
       
        topSpace.setBackground(Color.WHITE);
        topSpace.setBounds(0, 0, 750, 30);
        
        backBtn.setBounds(44, 650, 200, 150);
        takeIn.setBounds(279, 650, 200, 150);
        takeOut.setBounds(514, 650, 200, 150);
		
        add(noticePanel);        
        add(scrollForList);
        add(sumBvg);
        add(sumBvgField);
		add(sumPrice);
		add(sumPriceField);
		add(topSpace);
		add(backBtn);
		add(takeIn);
		add(takeOut);
		
		backBtn.addActionListener((e) -> {
			dispose();
			parent.setShowingNow(true);
			InputDataSet.getAvailList().removeAll(InputDataSet.getAvailList());
		});
		
		setUndecorated(true);
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		setBounds(580, 75, 760, 900);
		setBackground(Color.WHITE);
		setModalityType(DEFAULT_MODALITY_TYPE);
	}
	
	@SuppressWarnings("rawtypes")
	public Vector<Vector> getProductList() {
		return productList;
	}
	
	public int getCountForCoupon() {
		return countForCoupon;
	}

	public Integer getSum() {
		return this.sumPriceResult;
	}
	
}
