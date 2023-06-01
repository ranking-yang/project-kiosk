package main_menu.order_list;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;

import project.PosFrameProperties;

/** 메인메뉴에서 주문목록을 표현해주는 클래스
 *  @author YANG */
public class OrderList extends JScrollPane {
	/** JScrollPane for OrderList */
	private static final long serialVersionUID = 4489877632083565671L;
	
	private DefaultTableModel dtm;
	private JTable jtable;
	private List<Integer> basicPriceList = new ArrayList<>();
	
	private String[] header;
	
	public OrderList(int width, int height) {
		setSize(width, height);
		
		header = new String[] {"X", "음료이름", "옵션", "+", "개", "-", "가격", "옵션번호"};
		
		dtm = new DefaultTableModel(null, header) {

			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int row, int column) {
				if(column == 1 || column == 2 || column == 4 || column == 6) return false;
				
				return true;
			}
			
		};
		jtable = new JTable(dtm);
		
		this.getVerticalScrollBar().setPreferredSize(new Dimension(10, 10));
		this.getViewport().setBackground(Color.WHITE);
		this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JLabel upperCorner = new JLabel();
		upperCorner.setOpaque(true);
		upperCorner.setBackground(PosFrameProperties.MAIN_COLOR);
		
		this.setCorner(UPPER_RIGHT_CORNER, upperCorner);
		
		// 스크롤바 및 ArrowButton설정
		this.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = PosFrameProperties.CLICK_COLOR;
			}
			
			@Override
			protected JButton createDecreaseButton(int orientation) {
				
				JButton btn = super.createDecreaseButton(orientation);
				btn.setBackground(PosFrameProperties.MAIN_COLOR);
				btn.setFocusable(false);
				
				return btn;
			}
			
			@Override
			protected JButton createIncreaseButton(int orientation) {
				
				JButton btn = super.createIncreaseButton(orientation);
				btn.setBackground(PosFrameProperties.MAIN_COLOR);
				btn.setFocusable(false);
				
				return btn;
			}
		});
		
		this.getVerticalScrollBar().setBackground(PosFrameProperties.MAIN_COLOR);
		
		jtable.setRowHeight(35);
		new SetTableProperties(jtable);
		setViewportView(jtable);
		
		jtable.getColumnModel().getColumn(0).setCellRenderer(new TableCellForDeletion(this));// 버튼모양생성
		jtable.getColumnModel().getColumn(0).setCellEditor(new TableCellForDeletion(this)); // 작동
		
		jtable.getColumnModel().getColumn(3).setCellRenderer(new TableCellForPlus(this));
		jtable.getColumnModel().getColumn(3).setCellEditor(new TableCellForPlus(this));
		
		jtable.getColumnModel().getColumn(5).setCellRenderer(new TableCellForMinus(this));
		jtable.getColumnModel().getColumn(5).setCellEditor(new TableCellForMinus(this));
		
	}
	
	public JTable getTable() {
		return jtable;
	}
	
	public void addBasicPriceList(Integer priceOfSelectedMenu) {
		basicPriceList.add(priceOfSelectedMenu);
	}
	
	public List<Integer> getBasicPriceList() {
		return basicPriceList;
	}
	
	/**테이블에서 선택한 행을 삭제하는 메서드*/
	public void removeSelectedRow() {
		
		int row = jtable.getSelectedRow();
		
		if (row == -1) return;
		
		DefaultTableModel model = (DefaultTableModel) jtable.getModel();
		model.removeRow(row);
		
		this.basicPriceList.remove(row);
		
		int rowCnt = jtable.getRowCount();
		
		if (rowCnt > 0) {
			@SuppressWarnings("rawtypes")
			Vector<Vector> vector = model.getDataVector();
			
			String[][] strData = new String[vector.size()][(vector.get(0)).size()];
			for (int i = 0; i < vector.size(); ++i) {
				@SuppressWarnings("unchecked")
				Vector<String> vec = vector.get(i);
				for (int j = 0; j < vec.size(); ++j) {
					strData[i][j] = vec.get(j);
				}
			}
			
			DefaultTableModel cloneModel = new DefaultTableModel(strData, header) {

				private static final long serialVersionUID = 1L;
				
				public boolean isCellEditable(int row, int column) {
					if(column == 1 || column == 2 || column == 4 || column == 6) return false;
					
					return true;
				}
				
			};
			JTable cloneTable = new JTable(cloneModel);
			
			cloneTable(cloneTable);
		
		} else {
			DefaultTableModel cloneModel = new DefaultTableModel(null, header);
			JTable cloneTable = new JTable(cloneModel);
			
			cloneTable(cloneTable);
		}
		
	}
	
	/**주문버튼 눌렀을 때 실행될 메서드
	 * 하나의 행을 추가한다*/
	public void addSelectedMenu(String[] selectedProduct) {
		
		DefaultTableModel model = (DefaultTableModel) jtable.getModel();
		
		int rowCnt = jtable.getRowCount();
		
		if (rowCnt > 0) {
			@SuppressWarnings("rawtypes")
			Vector<Vector> vector = model.getDataVector();
			
			String[][] strData = new String[vector.size()][(vector.get(0)).size()];
			
			boolean hasSame = false;
			for (int i = 0; i < vector.size(); ++i) {
				int cnt = 0;
				
				@SuppressWarnings("unchecked")
				Vector<String> vec = vector.get(i);
				
				if (selectedProduct[1].equals(vec.get(1))) ++cnt;
				if (selectedProduct[7].equals(vec.get(7))) ++cnt;
				
				if (cnt == 2) {
					Integer qty = Integer.parseInt(vec.get(4)) + 1;
					Integer price = Integer.parseInt(vec.get(6)) + this.basicPriceList.get(i);
					vec.remove(4);
					vec.add(4, qty.toString());
					vec.remove(6);
					vec.add(6, price.toString());
					
					for (int j = 0; j < vec.size(); ++j) {
						strData[i][j] = vec.get(j);
					}
					hasSame = true;
					
				} else {
				
					for (int j = 0; j < vec.size(); ++j) {
						strData[i][j] = vec.get(j);
					}
				}
			}
			DefaultTableModel cloneModel = new DefaultTableModel(strData, header) {

				private static final long serialVersionUID = 1L;
				
				public boolean isCellEditable(int row, int column) {
					if(column == 1 || column == 2 || column == 4 || column == 6) return false;
					
					return true;
				}
				
			};
			if (!hasSame) cloneModel.addRow(selectedProduct);
			JTable cloneTable = new JTable(cloneModel);

			cloneTable(cloneTable);

		} else {
			DefaultTableModel cloneModel = new DefaultTableModel(null, header) {

				private static final long serialVersionUID = 1L;
				
				public boolean isCellEditable(int row, int column) {
					if(column == 1 || column == 2 || column == 4 || column == 6) return false;
					
					return true;
				}
				
			};
			cloneModel.addRow(selectedProduct);
			JTable cloneTable = new JTable(cloneModel);

			cloneTable(cloneTable);
		}
		
	}
	
	/**해당 행의 개수를 증가시키는 메서드
	 * 가격도 개수에 맞게 변화된다*/
	public void plusCount() {
		
		DefaultTableModel model = (DefaultTableModel) jtable.getModel();
		int row = jtable.getSelectedRow();
		Integer qty = Integer.parseInt(model.getValueAt(row, 4).toString());
		Integer price = this.basicPriceList.get(row);
		
		Integer qtyAfter = ++qty;
		Integer priceAfter = price * qty;
		
		model.setValueAt(qtyAfter.toString(), row, 4);
		model.setValueAt(priceAfter.toString(), row, 6);
		
		@SuppressWarnings("rawtypes")
		Vector<Vector> vector = model.getDataVector();
		
		String[][] strData = new String[vector.size()][(vector.get(0)).size()];
		for (int i = 0; i < vector.size(); ++i) {
			@SuppressWarnings("unchecked")
			Vector<String> vec = vector.get(i);
			for (int j = 0; j < vec.size(); ++j) {
				strData[i][j] = vec.get(j);
			}
		}
		DefaultTableModel cloneModel = new DefaultTableModel(strData, header) {

			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int row, int column) {
				if(column == 1 || column == 2 || column == 4 || column == 6) return false;
				
				return true;
			}
			
		};
		JTable cloneTable = new JTable(cloneModel);

		cloneTable(cloneTable);
		
	}
	
	/**해당 행의 개수를 감소시키는 메서드
	 * 가격도 개수에 맞게 변화된다*/
	public void minusCount() {
		
		DefaultTableModel model = (DefaultTableModel) jtable.getModel();
		
		int row = jtable.getSelectedRow();
		Integer qty = Integer.parseInt(model.getValueAt(row, 4).toString());
		Integer currPrice = Integer.parseInt(model.getValueAt(row, 6).toString());
		Integer price = this.basicPriceList.get(row);
		
		Integer qtyAfter = --qty;
		if (qty == 0) {
			removeSelectedRow();
			return;
		}
		Integer priceAfter = currPrice - price;
		
		model.setValueAt(qtyAfter.toString(), row, 4);
		model.setValueAt(priceAfter.toString(), row, 6);
		
		
		@SuppressWarnings("rawtypes")
		Vector<Vector> vector = model.getDataVector();

		String[][] strData = new String[vector.size()][(vector.get(0)).size()];
		for (int i = 0; i < vector.size(); ++i) {
			@SuppressWarnings("unchecked")
			Vector<String> vec = vector.get(i);
			for (int j = 0; j < vec.size(); ++j) {
				strData[i][j] = vec.get(j);
			}
		}
		DefaultTableModel cloneModel = new DefaultTableModel(strData, header) {

			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int row, int column) {
				if(column == 1 || column == 2 || column == 4 || column == 6) return false;
				
				return true;
			}
			
		};
		JTable cloneTable = new JTable(cloneModel);

		cloneTable(cloneTable);
		
	}
	
	/**삭제버튼을 눌렀을 때 실행되는 메서드
	 * 오더리스트가 초기화된다*/
	public void deleteAll() {
		this.basicPriceList.clear();		
		DefaultTableModel clonModel = new DefaultTableModel(null, header);
		JTable clonTable = new JTable(clonModel);

		cloneTable(clonTable);
	}
	
	/**테이블 생성시 중복되는 부분*/
	private void cloneTable(JTable clonTable) {
		jtable = clonTable;
		jtable.setRowHeight(35);
		new SetTableProperties(jtable);
		setViewportView(jtable);

		jtable.getColumnModel().getColumn(0).setCellRenderer(new TableCellForDeletion(this));
		jtable.getColumnModel().getColumn(0).setCellEditor(new TableCellForDeletion(this));

		jtable.getColumnModel().getColumn(3).setCellRenderer(new TableCellForPlus(this));
		jtable.getColumnModel().getColumn(3).setCellEditor(new TableCellForPlus(this));

		jtable.getColumnModel().getColumn(5).setCellRenderer(new TableCellForMinus(this));
		jtable.getColumnModel().getColumn(5).setCellEditor(new TableCellForMinus(this));

		revalidate();
		repaint();
	}
	
	/**오더리스트에 있는 제품들을 Vector로 반환*/
	@SuppressWarnings("rawtypes")
	public Vector<Vector> getOrderListData() {
		DefaultTableModel model = (DefaultTableModel) jtable.getModel();
		
		return model.getDataVector();
	}

}
