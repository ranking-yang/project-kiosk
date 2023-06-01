package manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import database.dao.LoginDAO;
import project.Encryption;
import project.PosFrame;
import project.PosFrameProperties;

/** 로그인창을 정의한 클래스 */
public class LoginScreen extends JDialog{
	/** JDialog for LoginScreen */
	private static final long serialVersionUID = -2614439598311439463L;
	
	Font font = PosFrameProperties.HEAD_FONT;
	Font font2 = PosFrameProperties.HEAD_FONT2;
	
	LoginDAO dao;

	private boolean loginFailed; 
	
	static PosFrame frame = PosFrameProperties.frame;
	
	public LoginScreen() {
		super(frame, "관리자모드");

		dao = new LoginDAO();
		
		JLabel id = new JLabel("ID    :");
		id.setFont(font);
		
		JLabel pw = new JLabel("PW  :");
		pw.setFont(font);
		
		JTextField idField = new JTextField("admin");
		idField.setFont(font);
		
		JPasswordField pwField = new JPasswordField();
		pwField.setFont(font);
		
		JButton login = new JButton("로그인");
		login.setFont(font);
		login.setBackground(PosFrameProperties.CLICK_COLOR);
		login.setFocusable(false);
		
		id.setBounds(150, 30, 60, 40);
		pw.setBounds(150, 90, 60, 40);
		idField.setBounds(210, 30, 140, 40 );
		pwField.setBounds(210, 90, 140, 40 );
		login.setBounds(173, 160, 140, 60);
	
		add(id);
		add(pw);
		add(idField);
		add(pwField);
		add(login);
		
		setLayout(null);
		
		getContentPane().setBackground(Color.WHITE);
		
		setBounds(710, 355, 500, 285);
		
		login.addActionListener((e) -> {
				String idStr = idField.getText();

				String pwStr = Encryption.encryption(String.valueOf(pwField.getPassword()));

				if ((dao.getpw()).equals(pwStr) && 
						(dao.getId()).equals(idStr)) {
					dao.closeConn();
					
					dispose();
					
					frame.getMenu().getOrderList().deleteAll(); 
					
					PosFrameProperties.card.show(frame.getContentPane(), "p4");
				} else {
					loginFailed = true;
					JOptionPane.showMessageDialog(this, "로그인 실패");
				}
		});
		
		pwField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				loginFailed = false;
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(loginFailed) return; 
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					login.doClick();
				}
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.getMenu().setShowingNow(true);
				dao.closeConn();
				dispose();
			}
			
			@Override
			public void windowOpened(WindowEvent e) {
				pwField.requestFocus();
			}
		});

		setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
	}
}

