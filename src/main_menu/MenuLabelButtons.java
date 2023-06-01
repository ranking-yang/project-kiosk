package main_menu;

import javax.swing.JLayeredPane;

import database.dao.DataCollection;
import main_menu.menu_label.MenuLabelInCategory;

/**
 * 메뉴 라벨에 버튼을 부착해주는 클래스.
 * @author YANG
 */
public class MenuLabelButtons {
	MenuLabelInCategory[] menuLabel;
	
	public MenuLabelButtons(JLayeredPane layeredPane) {
		
		menuLabel = new MenuLabelInCategory[DataCollection.getNumberOfThemes()];
		
		for (int i = 0; i < menuLabel.length; ++i) {
			int tm_id = i + 1;
			menuLabel[i] = new MenuLabelInCategory(tm_id, 0, 0, 845, 490);
		}

		layeredPane.add(menuLabel[0]);
	}
	
	/** 동기화시 버튼을 새로 그려주는 메서드 */
	public void updateMenuLabel() {
		for(int i = 0; i < menuLabel.length; ++i) {
			menuLabel[i].removeAll();
			menuLabel[i].setMenuButtons();
		}
	}
	
	/** 이전 다음으로 변경된 모든 메뉴 페이지를 초기화 해주는 메서드 */
	public void resetAllPages() {
		for (int i = 0; i < menuLabel.length; ++i) {
			menuLabel[i].resetPage();
		}
	}

	public MenuLabelInCategory[] getMenuLabel() {
		return menuLabel;
	}
	
}
