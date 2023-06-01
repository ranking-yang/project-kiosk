package manager.menu_management;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.dao.ProductDAO;
import manager.ImageFile.ImageFileUpdate;
import project.PosFrame;
import project.PosFrameProperties;

/** 메뉴수정 Dialog를 정의한 클래스 */
public class EditMenuDialog extends JDialog{
	/** JDialog for EditMenuDialog */
	private static final long serialVersionUID = 6878856706124218842L;
	
	PosFrame frame;
	ProductDAO dao;
	ManagerModeScreen parent;
	
	ImageFileUpdate imageUpdate;
	
	JTextField productThumbnail;
	
	private boolean check1;
	private boolean check2;

	/** 메뉴수정 메서드*/
	public EditMenuDialog() {	
		this.frame = PosFrameProperties.frame;	
		this.parent = frame.getProductData(); 
		int row = parent.menuTable.getSelectedRow();			
		if (row <0){
			JOptionPane.showMessageDialog(parent, "선택된 메뉴가 없습니다 메뉴를 선택해주세요", "입력값 없음", JOptionPane.WARNING_MESSAGE);
			return;
		}
		this.dao = parent.getDao();
		
		JPanel productPanel = new JPanel();
		productPanel.setBackground(new Color(171, 255, 199));
		JLabel productLabel = new JLabel("제품 설정");
		JLabel nameLabel = new JLabel("제품명");
		JLabel nameLabelEx = new JLabel("ex) (ICE)아메리카노, (HOT)카페라떼");
		JLabel priceLabel = new JLabel("가격");
		JLabel thumbnailLabel = new JLabel("이미지");
		JLabel tm_idLabel = new JLabel("제품 번호");
		JLabel tm_idLabelEx= new JLabel("ex) 601, 1011");
		JLabel tm_idLabelInfo1 = new JLabel("<html>100. NEW<br>200. 커피(ICE)<br>300. 커피(HOT)");
		JLabel tm_idLabelInfo2 = new JLabel("<html>400. 디카페인<br>500. 티<br>600. 스무디&프라페");
		JLabel tm_idLabelInfo3 = new JLabel("<html>700. 에이드&주스<br>800. 음료<br>900. 디저트");
		JLabel tm_idLabelInfo4 = new JLabel("<html>1000. DM상품");
		JLabel shot = new JLabel("SHOT");
		JLabel milk = new JLabel("MILK");
		JLabel ice = new JLabel("ICE");
		JLabel stevia = new JLabel("STEVIA");
		JLabel cream = new JLabel("CREAM");
		
		
		String name = (String)ManagerModeScreen.model.getValueAt(row,1);
		String price = ManagerModeScreen.model.getValueAt(row, 2).toString();
		String thumbnail = (String)ManagerModeScreen.model.getValueAt(row, 3);
		String tm =  ManagerModeScreen.model.getValueAt(row, 0).toString();
		
		JTextField productName = new JTextField(name);
		JTextField productPrice = new JTextField(price);
		productThumbnail = new JTextField(thumbnail);
		JTextField tm_id = new JTextField(tm);			
	
		CheckboxGroup shotgroup = new CheckboxGroup();
		check(5);
		Checkbox shotY = new Checkbox("Y", shotgroup, check1);
		Checkbox shotN = new Checkbox("N", shotgroup, check2);
		
		CheckboxGroup milkgroup = new CheckboxGroup();
		check(6);
		Checkbox milkY = new Checkbox("Y", milkgroup, check1);
		Checkbox milkN = new Checkbox("N", milkgroup, check2);
		
		CheckboxGroup icegroup = new CheckboxGroup();
		check(7);
		Checkbox iceY = new Checkbox("Y", icegroup, check1);
		Checkbox iceN = new Checkbox("N", icegroup, check2);
		
		CheckboxGroup steviagroup = new CheckboxGroup();
		check(8);
		Checkbox steviaY = new Checkbox("Y", steviagroup, check1);
		Checkbox steviaN = new Checkbox("N", steviagroup, check2);
		
		CheckboxGroup creamgroup = new CheckboxGroup();
		check(9);
		Checkbox creamY = new Checkbox("Y", creamgroup, check1);
		Checkbox creamN = new Checkbox("N", creamgroup, check2);
	
		JButton editBtn = new JButton("메뉴 수정");		
		JButton close = new JButton("종료");
		JButton imageBtn = new JButton("교체");

		ImageFileUpdate imageUpdate = new ImageFileUpdate(this);
		
		imageBtn.addActionListener((e) -> imageUpdate.showFileDialog()); 
		
		add(productPanel);
		productPanel.add(productLabel);
		add(nameLabel);
		add(nameLabelEx);
		add(priceLabel);
		add(thumbnailLabel);
		add(tm_idLabel);
		add(tm_idLabelEx);add(tm_idLabelInfo1);add(tm_idLabelInfo2);add(tm_idLabelInfo3);add(tm_idLabelInfo4);
		add(shot);add(milk);add(ice);add(stevia);add(cream);		
		add(productName);
		add(productPrice);
		add(productThumbnail);
		add(tm_id);
		add(shotY);add(shotN);
		add(milkY);add(milkN);
		add(iceY);add(iceN);
		add(steviaY);add(steviaN);
	    add(creamY);add(creamN);
		
		add(editBtn);		
		add(close);
		add(imageBtn);
	
		productPanel.setBounds(40, 20, 110, 40);
		nameLabel.setBounds(50, 70, 130, 35);
		nameLabelEx.setBounds(130, 70, 200, 35);
		priceLabel.setBounds(50, 145, 130, 35);
		thumbnailLabel.setBounds(50, 220, 130, 35);
		tm_idLabel.setBounds(50, 295, 130, 35);
		tm_idLabelEx.setBounds(150, 295, 130, 35);
		tm_idLabelInfo1.setBounds(50, 345, 110, 75);
		tm_idLabelInfo2.setBounds(170, 345, 110, 75);
		tm_idLabelInfo3.setBounds(290, 345, 110, 75);
		tm_idLabelInfo4.setBounds(410, 348, 110, 35);
		
		shot.setBounds(380, 90, 70, 50);
		milk.setBounds(380, 145, 70, 50);
		ice.setBounds(380, 200, 70, 50);
		stevia.setBounds(380, 255, 70, 50);
		cream.setBounds(380, 310, 70, 50);
		
		productName.setBounds(50, 100, 300, 30);
		productPrice.setBounds(50, 175, 300, 30);
		productThumbnail.setBounds(50, 250, 220, 30);
		productThumbnail.setEditable(false);
		imageBtn.setBounds(280, 250, 70, 30);
		tm_id.setBounds(50, 325, 300, 30);
		
		productLabel.setFont(PosFrameProperties.BASIC2);
		nameLabel.setFont(PosFrameProperties.BASIC2);
		priceLabel.setFont(PosFrameProperties.BASIC2);
		thumbnailLabel.setFont(PosFrameProperties.BASIC2);
		tm_idLabel.setFont(PosFrameProperties.BASIC2);
		
		productName.setFont(PosFrameProperties.BASIC);
		productPrice.setFont(PosFrameProperties.BASIC);
		productThumbnail.setFont(PosFrameProperties.BASIC);
		tm_id.setFont(PosFrameProperties.BASIC);
		
		shotY.setBounds(480, 90, 30, 50);
		shotN.setBounds(520, 90, 30, 50);
		
		milkY.setBounds(480, 145, 30, 50);
		milkN.setBounds(520, 145, 30, 50);
		
		iceY.setBounds(480, 200, 30, 50);
		iceN.setBounds(520, 200, 30, 50);
		
		steviaY.setBounds(480, 255, 30, 50);
		steviaN.setBounds(520, 255, 30, 50);
		
		creamY.setBounds(480, 310, 30, 50);
		creamN.setBounds(520, 310, 30, 50);	
		
		editBtn.setBounds(135, 420, 140, 60);	
		editBtn.setFocusable(false);
		close.setBounds(305, 420, 140, 60);		
		close.setFocusable(false);
		imageBtn.setBounds(280, 250, 70, 30);
		imageBtn.setFocusable(false);
		
		
		shot.setFont(PosFrameProperties.SMALL_BASIC2);
		milk.setFont(PosFrameProperties.SMALL_BASIC2);
		ice.setFont(PosFrameProperties.SMALL_BASIC2);
		stevia.setFont(PosFrameProperties.SMALL_BASIC2);
		cream.setFont(PosFrameProperties.SMALL_BASIC2);
		
		shotY.setFont(PosFrameProperties.SMALL_BASIC);
		shotN.setFont(PosFrameProperties.SMALL_BASIC);
		milkY.setFont(PosFrameProperties.SMALL_BASIC);
		milkN.setFont(PosFrameProperties.SMALL_BASIC);
		iceY.setFont(PosFrameProperties.SMALL_BASIC);
		iceN.setFont(PosFrameProperties.SMALL_BASIC);
		steviaY.setFont(PosFrameProperties.SMALL_BASIC);
		steviaN.setFont(PosFrameProperties.SMALL_BASIC);
		creamY.setFont(PosFrameProperties.SMALL_BASIC);
		creamN.setFont(PosFrameProperties.SMALL_BASIC);
		
		editBtn.setFont(PosFrameProperties.HEAD_FONT);
		editBtn.setBackground(PosFrameProperties.CLICK_COLOR);
		close.setFont(PosFrameProperties.HEAD_FONT);
		close.setBackground(PosFrameProperties.RED_COLOR);
		imageBtn.setFont(PosFrameProperties.BASIC);
		imageBtn.setBackground(Color.LIGHT_GRAY);
		
		editBtn.addActionListener((e) -> {
			
			int pdId =(Integer)ManagerModeScreen.model.getValueAt(row,0);
			
			int newPd_id = Integer.parseInt(tm_id.getText());
			String txtName = productName.getText();
			int txtPrice = Integer.parseInt(productPrice.getText());
			String txtThumbnail = productThumbnail.getText();
			int theme_id = newPd_id/100; // 새로 입력받은 제품번호기준으로 카테고리를 지정해야한다
			boolean addShot = shotY.getState();
			boolean addMilk = milkY.getState();
			boolean addIce = iceY.getState();
			boolean addStevia = steviaY.getState();
			boolean addCream = creamY.getState();
			dao.edit(newPd_id, txtName, txtPrice,
					txtThumbnail, theme_id, addShot,
					addMilk, addIce, addStevia, addCream, pdId, imageUpdate);
						
			dispose();
			ManagerModeScreen.model.setRowCount(0);
			dao.showMenu();
		});
		
		close.addActionListener((e) -> {
			dispose();

			ManagerModeScreen.model.setRowCount(0);
			dao.showMenu();
		});
		
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(229, 255, 238));
		setBounds(frame.getX() + 150,frame.getY() + 230,
        		frame.getWidth() - 300,frame.getHeight() - 500);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setVisible(true);
	}
	
	/** 체크박스의 상태변경해주는 메서드*/
	private void check(int colNum) {
		int row = parent.menuTable.getSelectedRow();
		if(ManagerModeScreen.model.getValueAt(row, colNum).equals("Y")) {
			check1 = true; check2 = false;			
		} else {
			check1 = false; check2 = true;
		}
	}
	
	public JTextField getProductThumbnail() {
		return productThumbnail;
	}
	
}
