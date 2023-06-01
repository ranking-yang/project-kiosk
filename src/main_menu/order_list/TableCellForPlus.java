package main_menu.order_list;
import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import project.ImageMaker;

/** 오더리스트에 들어가는 플러스 버튼 생성클래스 */
public class TableCellForPlus extends AbstractCellEditor implements TableCellEditor, TableCellRenderer  {
	/** AbstractCellEditor for TableCellForPlus */
	private static final long serialVersionUID = 3074901502186503454L;
	
	JButton btn;
	OrderList orderList;
	
	public TableCellForPlus(OrderList orderList) {
		this.btn = new JButton();
		ImageMaker.setImage(btn, "images/+.jpg", 35, 35);
		this.orderList = orderList;
		btn.setBorderPainted(false);
		
		btn.addActionListener((e) -> orderList.plusCount());
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
