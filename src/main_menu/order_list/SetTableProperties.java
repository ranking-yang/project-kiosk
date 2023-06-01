package main_menu.order_list;
import java.awt.Color;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import project.PosFrameProperties;

/** 오더리스트 테이블에 대한 설정을 해주는 클래스 */ 
public class SetTableProperties {
	
	public SetTableProperties(JTable jtable) {
		
		DefaultTableCellRenderer dtcrCenter = new DefaultTableCellRenderer();
		dtcrCenter.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmCenter = jtable.getColumnModel();
		
		DefaultTableCellRenderer dtcrRight = new DefaultTableCellRenderer();
		dtcrRight.setHorizontalAlignment(SwingConstants.RIGHT);
		TableColumnModel tcmRight = jtable.getColumnModel();
		
		// {"삭제", "음료이름", "옵션", "+", "개수", "-", "가격", "일련번호"};
		jtable.getColumnModel().getColumn(0).setPreferredWidth(30);
		jtable.getColumnModel().getColumn(1).setPreferredWidth(160);
		jtable.getColumnModel().getColumn(2).setPreferredWidth(180);
		jtable.getColumnModel().getColumn(3).setPreferredWidth(30);
		jtable.getColumnModel().getColumn(4).setPreferredWidth(30);
		jtable.getColumnModel().getColumn(5).setPreferredWidth(30);
		jtable.getColumnModel().getColumn(6).setPreferredWidth(70);
		
		jtable.setFont(PosFrameProperties.SMALL_BASIC);
		
		tcmCenter.getColumn(4).setCellRenderer(dtcrCenter);  
		tcmRight.getColumn(6).setCellRenderer(dtcrRight);  
		
		jtable.getColumnModel().getColumn(7).setWidth(0);
		jtable.getColumnModel().getColumn(7).setMinWidth(0);
		jtable.getColumnModel().getColumn(7).setMaxWidth(0);
		
		jtable.setBackground(Color.WHITE);
		jtable.setShowVerticalLines(false);
		jtable.setShowHorizontalLines(false);
		jtable.getTableHeader().setFont(PosFrameProperties.SMALL_BASIC2);
		jtable.getTableHeader().setReorderingAllowed(false);
		jtable.getTableHeader().setResizingAllowed(false);
		jtable.setRowSelectionAllowed(false);
		jtable.getTableHeader().setBackground(PosFrameProperties.MAIN_COLOR);
		
	}

}
