package receipt;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import project.OrderNumberDialog;
import project.PosFrameProperties;
import timer.CountTimer;
import timer.ReceiptTimerTask;

/**영수증을 출력할지 말지 결정하는 화면*/
public class ReceiptChooseDialog extends JDialog {
	/** JDialog for Receipt */
	private static final long serialVersionUID = 9120411566941202965L;
	static Font font = PosFrameProperties.HEAD_FONT;
	static Font font2 = PosFrameProperties.HEAD_FONT2;

	public ReceiptChooseDialog() {
		super(PosFrameProperties.frame);
		
		CountTimer timer = new CountTimer(10);
		
		JPanel noticePanel = new JPanel();
		noticePanel.setBounds(0, 0, 500, 35);
		noticePanel.setBackground(PosFrameProperties.CLICK_COLOR);
		
		JTextField receiptPrint = new JTextField("영수증 출력");
		receiptPrint.setFont(font2);
		receiptPrint.setBounds(155, 50, 180, 50);
		receiptPrint.setEditable(false);
		receiptPrint.setBackground(Color.WHITE);
		receiptPrint.setBorder(null);
		receiptPrint.setHorizontalAlignment(JTextField.CENTER);
		
		JTextField countSecond = new JTextField();
		countSecond.setFont(font2);
		countSecond.setBounds(155, 100, 180, 50);
		countSecond.setEditable(false);
		countSecond.setBackground(Color.WHITE);
		countSecond.setBorder(null);
		countSecond.setHorizontalAlignment(JTextField.CENTER);
		
		JButton notPrintBtn = new JButton("미출력");
		notPrintBtn.setFont(font);
		notPrintBtn.setBounds(110, 170, 130, 70);
		notPrintBtn.setBackground(PosFrameProperties.CLICK_COLOR);
		notPrintBtn.setFocusable(false);
		notPrintBtn.addActionListener((e) -> {
			timer.setForceStop();
			dispose();
			
			new OrderNumberDialog().setVisible(true);
		});
		
		JButton printBtn = new JButton("출력");
		printBtn.setFont(font);
		printBtn.setBounds(250, 170, 130, 70);
		printBtn.setBackground(PosFrameProperties.RED_COLOR);
		printBtn.setFocusable(false);
		printBtn.addActionListener((e) -> {
			timer.setForceStop();
			dispose();
			
			new PrintedReceipt();
			new OrderNumberDialog().setVisible(true);
		});
		
		add(noticePanel);
		add(receiptPrint);
		add(countSecond);
		add(notPrintBtn);
		add(printBtn);
		
		timer.schedule(new ReceiptTimerTask(timer, countSecond, notPrintBtn), 0, 1000);
		
		setUndecorated(true);
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(710, 355, 500, 285);
		setModalityType(DEFAULT_MODALITY_TYPE);
	}
	
}
