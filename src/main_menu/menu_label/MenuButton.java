package main_menu.menu_label;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;

import database.dto.ProductDTO;
import option.OptionScreen;
import project.PosFrameProperties;

/** 메뉴에 대한 정보를 담고 있는 제품버튼 */ 
public class MenuButton extends JButton {
	/** JButton for MenuButton */ 
	private static final long serialVersionUID = 3461714384980901231L;

	ProductDTO pDto;
	
	Integer pd_id;
	String pd_name;
	Integer pd_price;
	String pd_thumbnail;
	boolean pd_shot;
	boolean pd_milk;
	boolean pd_ice;
	boolean pd_stevia;
	boolean pd_cream;
	
	public MenuButton(ProductDTO pDto) {
		this.pDto = pDto;
		this.pd_id = pDto.getPd_id();
		this.pd_name = pDto.getPd_name();
		this.pd_price = pDto.getPd_price();
		this.pd_thumbnail = pDto.getPd_thumbnail();
		this.pd_shot = pDto.isPd_shot();
		this.pd_milk = pDto.isPd_milk();
		this.pd_ice = pDto.isPd_ice();
		this.pd_stevia = pDto.isPd_stevia();
		this.pd_cream = pDto.isPd_cream();
		
		setLayout(null);
		
		JLabel menuName = new JLabel(this.pd_name);
		menuName.setHorizontalAlignment(CENTER);
		menuName.setFont(new Font("휴먼모음T", Font.PLAIN, 18));
		menuName.setBounds(3, 180, 159, 30);
		add(menuName);
		
		JLabel menuPrice = new JLabel(String.valueOf(this.pd_price));
		menuPrice.setHorizontalAlignment(RIGHT);
		menuPrice.setFont(new Font("휴먼모음T", Font.PLAIN, 18));
		menuPrice.setForeground(PosFrameProperties.RED_COLOR);
		menuPrice.setBounds(30, 205, 70, 30);
		add(menuPrice);
		
		JLabel won = new JLabel("원");
		won.setHorizontalAlignment(CENTER);
		won.setFont(new Font("휴먼모음T", Font.PLAIN, 15));
		won.setForeground(PosFrameProperties.RED_COLOR);
		won.setBounds(97, 213, 20, 20);
		add(won);
		
		addActionListener((e) -> {
			PosFrameProperties.frame.getMenu().setShowingNow(false); // 메인메뉴 시간초 정지
			new OptionScreen(this);
		});
	}
	
	public Integer getPd_id() {
		return pd_id;
	}


	public String getPd_name() {
		return pd_name;
	}


	public Integer getPd_price() {
		return pd_price;
	}


	public String getPd_thumbnail() {
		return pd_thumbnail;
	}


	public boolean isPd_shot() {
		return pd_shot;
	}


	public boolean isPd_milk() {
		return pd_milk;
	}


	public boolean isPd_ice() {
		return pd_ice;
	}


	public boolean isPd_stevia() {
		return pd_stevia;
	}


	public boolean isPd_cream() {
		return pd_cream;
	}

}
