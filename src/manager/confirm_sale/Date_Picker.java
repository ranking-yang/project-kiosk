package manager.confirm_sale;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import project.PosFrameProperties;

/** 날짜 선택기를 만들어서 띄워주는 클래스 
 *  @author Sheeraz Gul 
 *  원본 : https://www.delftstack.com/ko/howto/java/java-swing-date/ */
class DatePick {
    int DATE_MONTH = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
    int DATE_YEAR = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);;
    JLabel J_Label = new JLabel("", JLabel.CENTER);
    String DATE_DAY = "";
    JDialog J_Dialog;
    JButton[] J_Button = new JButton[49];

    public DatePick(JDialog parent) {
        J_Dialog = new JDialog();
        J_Dialog.setModal(true);
        String[] Header = { "일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일" };
        JPanel J_Panel1 = new JPanel(new GridLayout(7, 7));
        J_Panel1.setPreferredSize(new Dimension(700, 360));

        for (int i = 0; i < J_Button.length; i++) {
            final int selection = i;
            J_Button[i] = new JButton();
            J_Button[i].setFocusPainted(false);
            J_Button[i].setBackground(Color.white);
            if (i > 6)
                J_Button[i].addActionListener((ae) -> {
                    DATE_DAY = J_Button[selection].getActionCommand();
                    J_Dialog.dispose();
                });
            if (i < 7) {
            	J_Button[i].setText(Header[i]);
            }
            
            int btnColor = i % 7;
            
            if(btnColor == 0)
            	J_Button[i].setForeground(Color.red);
            
            if(btnColor == 6)
            	J_Button[i].setForeground(Color.blue);
            
            if(btnColor > 0 && btnColor < 6)
            	J_Button[i].setForeground(Color.black);
            
            J_Button[i].setFont(PosFrameProperties.HEAD_FONT);
            J_Panel1.add(J_Button[i]);
        }
        
        JPanel J_Panel2 = new JPanel(new GridLayout(1, 3));
        JButton Previous_Button = new JButton("<< 이전");
        Previous_Button.addActionListener((e) -> {
            DATE_MONTH--;
            Display_Date();
        });
        Previous_Button.setFont(PosFrameProperties.HEAD_FONT);
        J_Label.setFont(PosFrameProperties.HEAD_FONT);
        
        J_Panel2.add(Previous_Button);
        J_Panel2.add(J_Label);
        
        JButton Next_Button = new JButton("다음 >>");
        Next_Button.addActionListener((e) -> {
            DATE_MONTH++;
            Display_Date();
        });
        Next_Button.setFont(PosFrameProperties.HEAD_FONT);
        
        J_Panel2.add(Next_Button);
        J_Dialog.add(J_Panel1, BorderLayout.CENTER);
        J_Dialog.add(J_Panel2, BorderLayout.SOUTH);
        J_Dialog.pack();
        J_Dialog.setLocationRelativeTo(parent);
        J_Dialog.setTitle("날짜선택기");
        Display_Date();
        J_Dialog.setVisible(true);
    }

    public void Display_Date() {
        for (int i = 7; i < J_Button.length; i++)
            J_Button[i].setText("");
        
        java.text.SimpleDateFormat Simple_Date_Format = new java.text.SimpleDateFormat(
                "yyyy년 MMMM");
        java.util.Calendar Calendar = java.util.Calendar.getInstance();
        
        Calendar.set(DATE_YEAR, DATE_MONTH, 1);
        
        int Day_Of_Week = Calendar.get(java.util.Calendar.DAY_OF_WEEK);
        int Days_In_Month = Calendar.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
        
        for (int i = 6 + Day_Of_Week, Day = 1; Day <= Days_In_Month; i++, Day++)
            J_Button[i].setText("" + Day);
        
        J_Label.setText(Simple_Date_Format.format(Calendar.getTime()));
    }

    public String Set_Picked_Date() {
        if (DATE_DAY.equals(""))
            return DATE_DAY;
        java.text.SimpleDateFormat Simple_Date_Format = new java.text.SimpleDateFormat(
                "yyyy-MM-dd");
        java.util.Calendar Calendar = java.util.Calendar.getInstance();
        Calendar.set(DATE_YEAR, DATE_MONTH, Integer.parseInt(DATE_DAY));
        return Simple_Date_Format.format(Calendar.getTime());
    }
}

/** 날짜 선택시 나타나는 Dialog를 정의한 클래스 */
public class Date_Picker extends JDialog {
	/** JDialog for Date Picker */
	private static final long serialVersionUID = -8069798708692387997L;

	final JTextField preDate;
	final JTextField postDate;
	
	public Date_Picker(CheckingSalesDataScreen parent) {
		
		JPanel prePanel = new JPanel();
		prePanel.setLayout(null);
        JLabel preLabel = new JLabel("언제부터 : ");
        JButton preButton = new JButton("날짜선택");
        preButton.setBackground(Color.LIGHT_GRAY);
        
        preLabel.setBounds(30, 25, 120, 40);
        preButton.setBounds(330, 25, 120, 40);
        
        preLabel.setFont(PosFrameProperties.BASIC2);
        preButton.setFont(PosFrameProperties.BASIC2);
        
        preDate = new JTextField(20);
        preDate.setBounds(140, 25, 150, 40);
        preDate.setFont(PosFrameProperties.BASIC);
        preDate.setEditable(false);
        
        prePanel.add(preLabel);
        prePanel.add(preButton);
        prePanel.add(preDate);
        
        getContentPane().add(prePanel);
        
        preButton.addActionListener((e) -> {
        	preDate.setText(new DatePick(this).Set_Picked_Date());
        });

        JPanel postPanel = new JPanel();
        postPanel.setLayout(null);
        JLabel postLabel = new JLabel("언제까지 : ");
        JButton postButton = new JButton("날짜선택");
        postButton.setBackground(Color.LIGHT_GRAY);
        
        postLabel.setBounds(30, 80, 120, 40);
        postButton.setBounds(330, 80, 120, 40);
        
        postLabel.setFont(PosFrameProperties.BASIC2);
        postButton.setFont(PosFrameProperties.BASIC2);
        
        postDate = new JTextField(20);
        postDate.setBounds(140, 80, 150, 40);
        postDate.setFont(PosFrameProperties.BASIC);
        postDate.setEditable(false);
        
        prePanel.add(postLabel);
        prePanel.add(postButton);
        prePanel.add(postDate);
        getContentPane().add(postPanel);
        
        postButton.addActionListener((e) -> postDate.setText(new DatePick(this).Set_Picked_Date()));
        
        JButton cancel = new JButton("취소");
        cancel.setBounds(110, 160, 130, 70);
        cancel.setBackground(PosFrameProperties.RED_COLOR);
        cancel.setFont(PosFrameProperties.HEAD_FONT);
        prePanel.add(cancel);
        
        cancel.addActionListener((e) -> dispose());
        
        JButton completeButton = new JButton("선택완료");
        completeButton.setBounds(250, 160, 130, 70);
        completeButton.setBackground(PosFrameProperties.CLICK_COLOR);
        completeButton.setFont(PosFrameProperties.HEAD_FONT);
        getContentPane().add(completeButton);
        
        completeButton.addActionListener((e) -> {
        	String preStr = preDate.getText();
        	String postStr = postDate.getText();
        	
        	if(preStr.equals("") && postStr.equals("")) {
        		JOptionPane.showMessageDialog(this, "날짜 미선택", "오류", JOptionPane.ERROR_MESSAGE);
        		return;
        	}
        	
        	if(preStr.equals("") || postStr.equals("")) {
        		dispose();
        		return;
        	}
        	
        	LocalDate pre = changeType(preStr); 
        	LocalDate post = changeType(postStr);
        	
        	if(pre.compareTo(post) <= 0) {
        		dispose();
        	} else {
        		JOptionPane.showMessageDialog(this, "유효하지 않은 날짜", "오류", JOptionPane.ERROR_MESSAGE);
        	}
        });
        
        add(prePanel);
        prePanel.setBackground(Color.WHITE);
        pack();
        setBounds(710, 355, 500, 285);
        
        setResizable(false);
        setModal(true);
        setVisible(true);
	}
	
	/** String으로 주어진 date값을 LocalDate타입으로 변환해서 넣어줌 */
	private LocalDate changeType(String date) {
		String[] set = date.split("-");
		
		int year = Integer.parseInt(set[0]);
		int month = Integer.parseInt(set[1]);
		int day = Integer.parseInt(set[2]);
		
		return LocalDate.of(year, month, day); 
	}
	
	public String getPreDate() {
		return preDate.getText();
	}

	public String getPostDate() {
		return postDate.getText();
	}

}