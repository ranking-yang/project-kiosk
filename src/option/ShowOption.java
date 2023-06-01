package option;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLayeredPane;

import main_menu.menu_label.MenuButton;
import option.option_name.factory.CreamFactory;
import option.option_name.factory.IceFactory;
import option.option_name.factory.MilkFactory;
import option.option_name.factory.ShotFactory;
import option.option_name.factory.SteviaFactory;
import option.option_name.upper_option.Cream;
import option.option_name.upper_option.Ice;
import option.option_name.upper_option.Milk;
import option.option_name.upper_option.Shot;
import option.option_name.upper_option.Stevia;

/**해당 제품버튼이 어떤 옵션값을 가지고 있는지 체크하고
 * 해당하는 OptionPane만을 OptionScreen에 전달하는 클래스*/
public class ShowOption {
	MenuButton menuBtn;
	
	String name;
	int price;
	
	boolean isShot;
	boolean isMilk;
	boolean isCream;
	boolean isIce;
	boolean isStevia;
	
	public ShowOption(MenuButton menuBtn) {
		
		this.menuBtn = menuBtn;
		
		this.isShot = menuBtn.isPd_shot();
		this.isMilk = menuBtn.isPd_milk();
		this.isCream = menuBtn.isPd_cream();
		this.isIce = menuBtn.isPd_ice();
		this.isStevia = menuBtn.isPd_stevia();
		
	}
	
	/**제품에 해당하는 옵션값만을 List에 담아 반환하는 메서드*/
	public List<JLayeredPane> availablePaneList() {
		List<JLayeredPane> paneList = new ArrayList<>();
		
		if (isShot) {
			paneList.add(new OptionPane(new ShotFactory(new Shot())));
		} else {
			SerialNumber.options[0] = null;
		}
		if (isMilk) {
			paneList.add(new OptionPane(new MilkFactory(new Milk())));
		} else {
			SerialNumber.options[1] = null;
		}
		if (isIce) {
			paneList.add(new OptionPane(new IceFactory(new Ice())));
		} else {
			SerialNumber.options[2] = null;
		}
		if (isStevia) {
			paneList.add(new OptionPane(new SteviaFactory(new Stevia())));
		} else {
			SerialNumber.options[3] = null;
		}
		if (isCream) {
			paneList.add(new OptionPane(new CreamFactory(new Cream())));
		} else {
			SerialNumber.options[4] = null;
		}
		
		if (paneList.size() == 0) {
			paneList.add(new NullPane());
		}
		
		return paneList;
	}

	
}
