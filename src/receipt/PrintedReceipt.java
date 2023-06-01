package receipt;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import project.CreditCardScreen;
import project.ImageMaker;
import project.PosFrame;
import project.PosFrameProperties;

/** 
 * 영수증 출력 화면을 정의하는 클래스. 
 */
public class PrintedReceipt extends JDialog {
	/** JDialog for PrintedReceipt */
	private static final long serialVersionUID = -5585400398093211978L;
	private JLabel defaultPrice;
	private JLabel surtax;
	private JLabel cost;
	private JLabel receivecost;
	private JLabel willReceiveCost;
	private JLabel paymentCost;
	
	Font gulimPlain13 = new Font("굴림", Font.PLAIN, 13);
	Font gothicExtraPlain13 = new Font("견고딕", Font.PLAIN, 13);
	Font gothicExtraPlain18 = new Font("견고딕", Font.PLAIN, 18);
	private JTextArea orderTable;
	
	@SuppressWarnings("rawtypes")
	Vector<Vector> productVec;
	
	static PosFrame frame = PosFrameProperties.frame;
	
	public PrintedReceipt() {
		super(frame, "영수증");
		
		productVec = frame.getMenu().getOrderList().getOrderListData();
		
		ReceiptInfo info = new ReceiptInfo(productVec);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		panel.setBackground(Color.WHITE);
		
		getContentPane().add(panel);
		
		JLabel orderWaitNum = new JLabel("[주문(대기)번호]");
		orderWaitNum.setFont(gothicExtraPlain13);
		orderWaitNum.setBounds(12, 10, 129, 26);
		panel.add(orderWaitNum);
		
		JPanel opderNum = new JPanel();
		opderNum.setBackground(Color.DARK_GRAY);
		opderNum.setBounds(141, 28, 47, 34);
		panel.add(opderNum);
		
		JLabel branchInfo = new JLabel("<html>영수증번호:" + info.getReceiptNumber() + "<br>"
				+ "지점:" + info.getBranchName() + "<br>"
				+ "지점번호:" + info.getBranchNumber() + " 전화번호:" + info.getPhoneNumber() + "<br>"
				+ "주소:" + info.getAddress() + "</html>");
		branchInfo.setFont(gothicExtraPlain13);
		branchInfo.setBounds(12, 72, 241, 70);
		panel.add(branchInfo);
		
		JLabel date = new JLabel("날짜:" + info.getSellingTime());
		date.setFont(gothicExtraPlain13);
		date.setBounds(12, 156, 180, 15);
		panel.add(date);
		
		JLabel managerName = new JLabel(info.getManagerName());
		managerName.setFont(gothicExtraPlain13);
		managerName.setBounds(265, 72, 57, 15);
		panel.add(managerName);
		
		String line =
				"-----------------------------------------------------------------------------";
		
		JLabel line1 = new JLabel(line);
		line1.setBounds(12, 167, 310, 15);
		panel.add(line1);
		
		int plusHeight = 0;
		int orderCount = 1;
		
		// 영수증에 8품목까지 표시가능.
		int count = info.getCount();
		while(orderCount < 8) {
			if(count == orderCount) {
				break;
			}
			
			if(count > orderCount) {
				plusHeight += 64;
				++orderCount;
			}
		}
		
		orderTable = new JTextArea(info.getOrderDetail());
		orderTable.setFont(gulimPlain13);
		orderTable.setBounds(12, 181, 310, 51 + plusHeight);
		orderTable.setEditable(false);
		
		panel.add(orderTable);
		
		defaultPrice = new JLabel(PosFrameProperties.numberFormatter(info.getPriceWithoutSurTax()));
		defaultPrice.setFont(gulimPlain13);
		defaultPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		defaultPrice.setBounds(243, 258 + plusHeight, 79, 15);
		panel.add(defaultPrice);
		
		surtax = new JLabel(PosFrameProperties.numberFormatter(info.getSurTax()));
		surtax.setFont(gulimPlain13);
		surtax.setHorizontalAlignment(SwingConstants.RIGHT);
		surtax.setBounds(243, 277 + plusHeight, 79, 15);
		panel.add(surtax);
		
		cost = new JLabel(PosFrameProperties.numberFormatter(info.getTotalPrice()));
		cost.setFont(gothicExtraPlain18);
		cost.setHorizontalAlignment(SwingConstants.RIGHT);
		cost.setBounds(233, 301 + plusHeight, 89, 24);
		panel.add(cost);
		
		receivecost = new JLabel(PosFrameProperties.numberFormatter(info.getTotalPrice()));
		receivecost.setHorizontalAlignment(SwingConstants.RIGHT);
		receivecost.setFont(gothicExtraPlain18);
		receivecost.setBounds(233, 346 + plusHeight, 89, 24);
		panel.add(receivecost);
		
		willReceiveCost = new JLabel("0");
		willReceiveCost.setHorizontalAlignment(SwingConstants.RIGHT);
		willReceiveCost.setFont(gothicExtraPlain18);
		willReceiveCost.setBounds(243, 324 + plusHeight, 79, 24);
		panel.add(willReceiveCost);
		
		paymentCost = new JLabel(PosFrameProperties.numberFormatter(info.getTotalPrice()));
		paymentCost.setFont(gulimPlain13);
		paymentCost.setHorizontalAlignment(SwingConstants.RIGHT);
		paymentCost.setBounds(243, 404 + plusHeight, 79, 21);
		panel.add(paymentCost);
		
		JLabel orderNum = new JLabel(CreditCardScreen.getOrderNumber().toString());
		orderNum.setFont(new Font("굴림", Font.PLAIN, 20));
		orderNum.setForeground(Color.WHITE);
		opderNum.add(orderNum);
		
		JLabel receiptInfo = new JLabel("(영수증에는 8잔까지만 표시됩니다)");
		receiptInfo.setFont(new Font("굴림", Font.PLAIN, 11));
		receiptInfo.setBounds(12, 235 + plusHeight, 224, 20);
		panel.add(receiptInfo);
		
		JLabel totalBeverage = new JLabel("총 품목 수:  " + info.getTotalBeverage());
		totalBeverage.setFont(new Font("굴림", Font.PLAIN, 12));
		totalBeverage.setHorizontalAlignment(SwingConstants.RIGHT);
		totalBeverage.setBounds(218, 234 + plusHeight, 104, 20);
		panel.add(totalBeverage);
		
		JLabel line2 = new JLabel(line);
		line2.setBounds(12, 245 + plusHeight, 310, 15);
		panel.add(line2);
		
		JLabel line3 = new JLabel(line);
		line3.setBounds(12, 393 + plusHeight, 310, 15);
		panel.add(line3);
		
		JLabel line4 = new JLabel(line);
		line4.setBounds(12, 435 + plusHeight, 310, 15);
		panel.add(line4);
		
		JLabel costLabel = new JLabel("<html>합계<br>받을금액<br>받은금액");
		costLabel.setFont(new Font("굴림", Font.PLAIN, 18));
		costLabel.setBounds(12, 295 + plusHeight, 129, 75);
		panel.add(costLabel);
		
		JLabel surtaxLabel = new JLabel("<html>부가세 과세 물품가액<br>부가세");
		surtaxLabel.setFont(gulimPlain13);
		surtaxLabel.setBounds(12, 257 + plusHeight, 129, 31);
		panel.add(surtaxLabel);
		
		JLabel paymentRecords = new JLabel("결제수단별 결제내역");
		paymentRecords.setFont(gulimPlain13);
		paymentRecords.setBounds(12, 384 + plusHeight, 129, 14);
		panel.add(paymentRecords);
		
		JLabel payWith = new JLabel("<html>결제수단:" + info.getPaymentWay() + "</html>");
		payWith.setFont(gulimPlain13);
		payWith.setBounds(12, 400 + plusHeight, 116, 28);
		panel.add(payWith);
		
		JLabel cardInfo = new JLabel("카드 정보");
		cardInfo.setFont(gulimPlain13);
		cardInfo.setBounds(12, 441 + plusHeight, 116, 26);
		panel.add(cardInfo);
		
		JLabel barcodeImage = new JLabel();
		ImageMaker.setImage(barcodeImage, "images/barcode.jpg", 320, 50);
		barcodeImage.setBounds(27, 487 + plusHeight, 277, 49);
		panel.add(barcodeImage);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(1450, 230 - plusHeight / 2, 350, 590 + plusHeight);
		setResizable(false);
		setVisible(true);
	}
	
}
