package manager.confirm_sale;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;

/** 매출조회에서 콤보박스가 조작되었을 때 기능을 정의한 클래스. */
public class ComboBoxListener implements ItemListener {
	
	CheckingSalesDataScreen parent;
	
	JComboBox<String> group;
	JComboBox<String> range;
	
	JLabel combine;
	
	public ComboBoxListener(CheckingSalesDataScreen parent) {
		this.parent = parent;
		this.group = parent.getGroup();
		this.range = parent.getRange();
		this.combine = parent.getCombine();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.DESELECTED) 
			return;
		
		if(e.getSource() == group) {
			parent.setFirstWord(String.valueOf(e.getItem()));
			parent.setLabelText();
			
			parent.getRefresh().doClick();
		}
		
		if(e.getSource() == range) {
			parent.setSecondWord(String.valueOf(e.getItem()));

			if(range.getSelectedIndex() == 1) {
				parent.getUserSetBtn().doClick();
			} else {
				parent.getRefresh().doClick();
				parent.setLabelText();
			}
		}
	}
	
}
