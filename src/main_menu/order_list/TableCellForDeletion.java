package main_menu.order_list;
import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import project.ImageMaker;

/** 오더리스트에 들어가는 삭제 버튼 생성클래스 */
public class TableCellForDeletion extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {

	/** AbstractCellEditor for TableCellForDeletion */
	private static final long serialVersionUID = 1667039469359232146L;
	
	OrderList orderList;
	JButton btn;
	
	public TableCellForDeletion(OrderList orderList) {
		this.orderList = orderList;
		btn = new JButton();
		ImageMaker.setImage(btn, "images/x.jpg", 35, 35);
		btn.setBorderPainted(false);
		
		btn.addActionListener((e) -> orderList.removeSelectedRow()); 
	}
	
	@Override
	public Object getCellEditorValue() {
		return null;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		return btn;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		return btn;
	}

}








