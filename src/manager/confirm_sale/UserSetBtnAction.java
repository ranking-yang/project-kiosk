package manager.confirm_sale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** 날짜선택창 불러오는 버튼 기능을 정의한 클래스 */
public class UserSetBtnAction implements ActionListener {

	CheckingSalesDataScreen parent;
	
	public UserSetBtnAction(CheckingSalesDataScreen parent) {
		this.parent = parent;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(parent.getRange().getSelectedIndex() == 0)
			return;
		
		Date_Picker date = new Date_Picker(parent);
		
		parent.setPreDate(date.getPreDate());
		parent.setPostDate(date.getPostDate());
		
		String pre = parent.getPreDate();
		String post = parent.getPostDate();
		String combine = "";
		
		if(!pre.equals("")) 
			combine += pre + "부터 ";
		
		if(!post.equals(""))
			combine += post + "까지";
		
		if(pre.equals("") && post.equals(""))
			combine = "기간 미지정";
		
		parent.setSecondWord(combine);
		
		parent.setLabelText();
		
		parent.getRefresh().doClick();
		
	}

}
