package manager.menu_management;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.dao.ProductDAO;
import manager.ImageFile.ImageFileInsert;
import project.PosFrame;
import project.PosFrameProperties;

/** 관리자 메뉴 추가 Dialog
 *  @author BAE */
public class NewMenu extends JDialog {
	/** JDialog for NewMenu */
	private static final long serialVersionUID = 6258367863400229718L;
	
	private ProductDAO dao;
	
	ImageFileInsert fileChooser;
	
	JTextField productThumbnail;
	JButton refresh;

	public NewMenu() {
		PosFrame frame = PosFrameProperties.frame;
		dao = frame.getProductData().getDao();
			
		JPanel productPanel = new JPanel();
		productPanel.setBackground(new Color(171, 255, 199));
		JLabel productLabel = new JLabel("제품 설정");
		productPanel.add(productLabel);
		
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
		
		JTextField productName = new JTextField();
		JTextField productPrice = new JTextField();
		productThumbnail = new JTextField();
		JTextField tm_id = new JTextField();			
	
		CheckboxGroup shotgroup = new CheckboxGroup();
		Checkbox shotY = new Checkbox("Y", shotgroup, false);
		Checkbox shotN = new Checkbox("N", shotgroup, false);
		
		CheckboxGroup milkgroup = new CheckboxGroup();
		Checkbox milkY = new Checkbox("Y", milkgroup, false);
		Checkbox milkN = new Checkbox("N", milkgroup, false);
		
		CheckboxGroup icegroup = new CheckboxGroup();
		Checkbox iceY = new Checkbox("Y", icegroup, false);
		Checkbox iceN = new Checkbox("N", icegroup, false);
		
		CheckboxGroup steviagroup = new CheckboxGroup();
		Checkbox steviaY = new Checkbox("Y", steviagroup, false);
		Checkbox steviaN = new Checkbox("N", steviagroup, false);
		CheckboxGroup creamgroup = new CheckboxGroup();
		Checkbox creamY = new Checkbox("Y", creamgroup, false);
		Checkbox creamN = new Checkbox("N", creamgroup, false);
	
		JButton addBtn = new JButton("메뉴 추가");	
		addBtn.setFocusable(false);
		JButton close = new JButton("종료");
		close.setFocusable(false);
		JButton imageBtn = new JButton("첨부");
		imageBtn.setFocusable(false);
		
		productThumbnail.setEditable(false);
		
		fileChooser = new ImageFileInsert(this);
		
		imageBtn.addActionListener((e) -> fileChooser.showChooseFileDialog());
		
		add(productPanel);
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
		
		add(addBtn);		
		add(close);
		add(imageBtn);
	
		productPanel.setBounds(35, 20, 110, 40);
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
		
		addBtn.setBounds(135, 420, 140, 60);	
		close.setBounds(305, 420, 140, 60);
		
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
		
		addBtn.setFont(PosFrameProperties.HEAD_FONT);
		addBtn.setBackground(PosFrameProperties.CLICK_COLOR);
		close.setFont(PosFrameProperties.HEAD_FONT);
		close.setBackground(PosFrameProperties.RED_COLOR);
		imageBtn.setFont(PosFrameProperties.BASIC);
		imageBtn.setBackground(Color.LIGHT_GRAY);
		
		setLayout(null);
		getContentPane().setBackground(new Color(229, 255, 238));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(frame.getX() + 150,frame.getY() + 230,
        		frame.getWidth() - 300,frame.getHeight() - 500);		
		
		addBtn.addActionListener((e) -> {
			int pd_id = Integer.parseInt(tm_id.getText());
			String name = productName.getText();
			int price = Integer.parseInt(productPrice.getText());
			String thumbnail = productThumbnail.getText();
			int theme_id = pd_id/100;
			boolean addShot = shotY.getState();
			boolean addMilk = milkY.getState();
			boolean addIce = iceY.getState();
			boolean addStevia = steviaY.getState();
			boolean addCream = creamY.getState();
						
			dao.addProduct(pd_id, name, price,
					thumbnail, theme_id, addShot,
					addMilk, addIce, addStevia, addCream, fileChooser);
			
			ManagerModeScreen.model.setRowCount(0);
			dao.showMenu();
			dispose();
		});	
	
		close.addActionListener((e) -> {
			ManagerModeScreen.model.setRowCount(0);
			dao.showMenu();
			dispose();
		});
		
		setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
		setVisible(true);
	}
	
	public JTextField getProductThumbnail() {
		return productThumbnail;
	}
	
}
