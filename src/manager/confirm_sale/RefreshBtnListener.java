package manager.confirm_sale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import database.dao.CheckingSalesDAO;

/** 매출조회창에서 새로고침 버튼에 대한 기능을 정의한 클래스 */
public class RefreshBtnListener implements ActionListener {

	CheckingSalesDataScreen parent;
	
	CheckingSalesTableModel model;
	
	CheckingSalesDAO dao;
	
	public RefreshBtnListener(CheckingSalesDataScreen parent) {
		this.parent = parent;
		this.model = parent.getModel();
		this.dao = parent.getDao();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		model.removeAllData();
		
		int select1 = parent.getGroup().getSelectedIndex();
		int select2 = parent.getRange().getSelectedIndex(); 
		
		switch(select1) {
		case 0:
			parent.setColumnWitdh(0);
			if(select2 == 0) dao.totalSelect(); 
			if(select2 == 1) dao.userTotalSelect();
			break;
		case 1:	case 2: case 3:
			parent.setColumnWitdh(1);
			if(select2 == 0) dao.select(select1);
			if(select2 == 1) dao.userSelect(select1);
			break;
		default:
			System.out.println("유효하지 않은 접근");
		}
		
		parent.setTotalSalesSum();
	}
	
}
