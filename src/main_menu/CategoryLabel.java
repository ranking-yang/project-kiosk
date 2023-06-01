package main_menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import database.dao.DataCollection;
import main_menu.menu_label.MenuLabelInCategory;
import project.PosFrameProperties;

/** 
 * 카테고리 버튼들을 담고있는 라벨생성
 * @author YANG
 */
public class CategoryLabel extends JLabel {
	/** JLabel for CategoryLabel */
	private static final long serialVersionUID = 2339776603084894867L;

	private List<JButton> categories = new ArrayList<>();
	private JLayeredPane layeredPane;
	private JLabel page;
	
	private MenuLabelInCategory[] menuLabel;
	
	private int currIndex;
	
	
	/** 카테고리 버튼들을 생성하는 클래스 */
	public CategoryLabel(JLayeredPane layeredPane, MenuLabelButtons menuLabelBtn, JLabel page) {
		this.layeredPane = layeredPane;
		this.menuLabel = menuLabelBtn.getMenuLabel();
		this.page = page;
		
		setLayout(new GridLayout(2, 5, 5, 5));
		setBounds(20, 60, 845, 130);
		setBackground(new Color(253, 209, 0));
		
		setButtons();
	}
	
	
	/** 라벨에 버튼들을 부착해주는 메서드 */
	public void setButtons() {
		int len = DataCollection.getNumberOfThemes();
		Map<Integer, String> themeMap = DataCollection.getThemeMap();
		
		for (int i = 0; i < len; ++i) {
			
			categories.add(new JButton(themeMap.get(i + 1)));
			categories.get(i).setBorderPainted(false);
			categories.get(i).setFont(new Font("HY헤드라인M", Font.PLAIN, 16));
			categories.get(i).setFocusable(false);
			
			int index = i;
			categories.get(index).addActionListener((e) -> {
				for (int k = 0; k < categories.size(); ++k) {
					categories.get(k).setBackground(null);
					categories.get(k).setForeground(null);
					layeredPane.remove(menuLabel[k]);
				}
				
				categories.get(index).setBackground(PosFrameProperties.CLICK_COLOR);
				categories.get(index).setForeground(Color.WHITE);
				layeredPane.add(menuLabel[index]);
				page.setText(menuLabel[index].showCurrPage());
				currIndex = index;
			});
			
			this.add(categories.get(i));
		}
		
	}
	
	/** 버튼을 불러와주는 getter
	 *  @param index : 리스트의 인덱스값 */
	public JButton getButton(int index) {
		return categories.get(index);
	}
	
	/** 카테고리버튼들을 가지고있는 리스트를 반환한다 */
	public List<JButton> getCategories() {
		return categories;
	}
	
	public MenuLabelInCategory getMenuLabel() {
		return this.menuLabel[currIndex];
	}
	
	public JLabel getLabelPage() {
		return page;
	}
}
