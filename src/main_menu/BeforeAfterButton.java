package main_menu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import project.ImageMaker;
import project.PosFrame;
import project.PosFrameProperties;

/**
 * 이전 다음 버튼 클래스
 * @author LEE
 */
public class BeforeAfterButton {
	
	static PosFrame parent = PosFrameProperties.frame;
	
	public BeforeAfterButton(MenuScreen menuScreen, CategoryLabel categoryLabel) {
		int beforeMenuX = 240;
		int afterMenuX = 580;
		int menuY = 685;
		int width = 65;
		int height = 50;
		
		String beforeImage = "images/before.jpg";
		String afterImage = "images/after.jpg";
		
		JButton beforeMenu = new JButton();
		beforeMenu.setBounds(beforeMenuX, menuY, width, height);
		ImageMaker.setImage(beforeMenu, beforeImage, width, height);
		beforeMenu.setBorderPainted(false);
		menuScreen.add(beforeMenu);
		
		JButton afterMenu = new JButton();
		afterMenu.setBounds(afterMenuX, menuY, width, height);
		ImageMaker.setImage(afterMenu, afterImage, width, height);
		afterMenu.setBorderPainted(false);
		menuScreen.add(afterMenu);
		
		beforeMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				beforeMenu.setLocation(beforeMenuX - 10, menuY);
				ImageMaker.setImage(beforeMenu, beforeImage, width, height);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				beforeMenu.setLocation(beforeMenuX, menuY);
				ImageMaker.setImage(beforeMenu, beforeImage, width, height);
				categoryLabel.getMenuLabel().previous();
				categoryLabel.getLabelPage().setText(categoryLabel.getMenuLabel().showCurrPage());
			}
		});
		
		afterMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				afterMenu.setLocation(afterMenuX + 10, menuY);
				ImageMaker.setImage(afterMenu, afterImage, width, height);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				afterMenu.setLocation(afterMenuX, menuY);
				ImageMaker.setImage(afterMenu, afterImage, width, height);
				categoryLabel.getMenuLabel().next();
				categoryLabel.getLabelPage().setText(categoryLabel.getMenuLabel().showCurrPage());
			}
		});
	}
}
