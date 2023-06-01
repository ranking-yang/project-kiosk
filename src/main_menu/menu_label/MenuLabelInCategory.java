package main_menu.menu_label;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;

import database.dao.DataCollection;
import project.ImageMaker;

/** 
 *  제품버튼들을 생성해주는 클래스
 *  @author YANG */
public class MenuLabelInCategory extends JLabel {
	/** JLabel for MenuLabelInCategory */ 
	private static final long serialVersionUID = -7567240135343181278L;
	
	private List<MenuButton> menuListBvg = new ArrayList<>();
	
	private int tm_id;
	
	private int currIndex;
	
	private int len;
	
	private int currPage;
	
	public MenuLabelInCategory(int tm_id, int x, int y, int width, int height) {
		this.tm_id = tm_id;
		currPage = 1;
		setLayout(new GridLayout(2, 5, 5, 5));
		setBounds(x, y, width, height);
		setMenuButtons();
	}
	
	/**
	 * 메뉴라벨위에 메뉴버튼들을 생성해주는 함수
	 */
	public void setMenuButtons() {
		
		int indexForDTO = 0;
		int indexForMenuBtn = 0;
		
		menuListBvg.clear();
		
		while (indexForDTO < DataCollection.getNumberOfProducts()) {
			Integer tm_id = DataCollection.getProductList().get(indexForDTO).getTm_id();
			
			if (tm_id == this.tm_id) {
				
				menuListBvg.add(new MenuButton(DataCollection.getProductList().get(indexForDTO)));
				
				String pd_thumbnail = menuListBvg.get(indexForMenuBtn).getPd_thumbnail();
				ImageMaker.setImage(menuListBvg.get(indexForMenuBtn), pd_thumbnail, 162, 246);
				
				if (menuListBvg.size() <= 10) {
					this.add(menuListBvg.get(indexForMenuBtn));
					currIndex = indexForMenuBtn;
				}
				
				DataCollection.getProductList().remove(indexForDTO--);
				indexForMenuBtn++;
			}
			indexForDTO++;
		}
		
		len = menuListBvg.size();
		while (len < 10) {
			JButton btn = new JButton();
			ImageMaker.setImage(btn, "images/white.jpg", 162, 246);
			btn.setBorderPainted(false);
			
			btn.setLayout(null);
			this.add(btn);
			len++;
		}
	}
	
	/** 다음 페이지를 보여주기 위한 메서드 */
	public void next() {
		
		int lastProduct = len - 1;
		if (len <= 10 || currIndex == lastProduct) return;
		
		currPage++;
		
		this.removeAll();
		if (currIndex == -1) currIndex = 9;
		
		int lastInNextPage = currIndex + 10;
		
		if (lastInNextPage < lastProduct) {
			
			while (++currIndex <= lastInNextPage) {
				this.add(menuListBvg.get(currIndex));
			}
			
		} else {
			
			while (++currIndex <= lastProduct) {
				this.add(menuListBvg.get(currIndex));
			}
			
			int blankBtnQty = len % 10 == 0 ? 10 : len % 10;
			
			while (blankBtnQty < 10) {
				JButton btn = new JButton();
				ImageMaker.setImage(btn, "images/white.jpg", 162, 246);
				btn.setBorderPainted(false);
				
				btn.setLayout(null);
				this.add(btn);
				blankBtnQty++;
			}
			
		}
		currIndex--;
		this.revalidate();
		this.repaint();
		
	}
	
	/** 이전 페이지를 보여주기 위한 메서드 */
	public void previous() {
		
		if (len <= 10 || currIndex == -1 || currPage == 1) return;
		
		currPage--;
		
		this.removeAll();
		
		int lastProduct = len - 1;
		
		if (currIndex == lastProduct) {
			int lastInPrePage = 
					len % 10 == 0 ? (currIndex -= 10) : (currIndex -= (len % 10));
			int firstInPrePage = lastInPrePage - 9;
			
			while (currIndex >= firstInPrePage) {
				this.add(menuListBvg.get(lastInPrePage - (currIndex - firstInPrePage)));
				currIndex--;
			}
			
		} else if (currIndex == 9) {
			
			for (int i = 0; i <= currIndex; ++i) {
				this.add(menuListBvg.get(i));
			}
			currIndex = -1;
			
		}else {
		
			int lastInPrePage = (currIndex -= 10);
			int firstInPrePage = lastInPrePage - 9;

			while (currIndex >= firstInPrePage) { 
				this.add(menuListBvg.get(lastInPrePage - (currIndex - firstInPrePage)));
				currIndex--;
			}
			
		}
		
		this.revalidate();
		this.repaint();
	}
	
	
	/** 모든 페이지를 첫번째 페이지로 돌려놓는 메서드 */
	public void resetPage() {
		while(currPage > 1) previous();
	}
	
	/** 현재 페이지를 알려주는 메서드 */
	public String showCurrPage() {
		int spotQty = len % 10 == 0 ? len / 10 : len / 10 + 1;
		
		String text = " ";
		for (int i = 1; i <= spotQty; ++i) {
			
			if (i == currPage) {
				text += "● ";
				continue;
			}
			
			text += "○ "; 
		}
		
		return text;
	}
	
}
