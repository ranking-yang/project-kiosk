package manager.confirm_sale;

import java.awt.Color;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import database.dao.CheckingSalesDAO;
import database.dto.SoldProductDTO;
import project.PosFrameProperties;

/**
 * 관리자 매출조회화면에서 sd_id를 더블클릭하면 관련 order_list 테이블 정보를 조회해주는 클래스 
 * @author HONG
 */
public class SoldProductDialog extends JDialog {
	/**
	 * JDialog for OrderlistDialog
	 */
	private static final long serialVersionUID = -5120201118195420463L;
	
	private static final String[] HEADER = {"주문ID", "개수", "제품번호", "제품이름", "가격", "옵션번호"};
	private static final int[] colWidth = {50, 70, 60, 240, 100, 80};
	
	public SoldProductDialog(CheckingSalesDAO dao, int sd_id) {
		List<SoldProductDTO> dto = dao.selectSoldProduct(sd_id);
		
		DefaultTableModel model = new DefaultTableModel() {
			/**
			 * model for DefaultTableModel
			 */
			private static final long serialVersionUID = 9188418449911232678L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		for(int i = 0; i < HEADER.length; ++i) {
			model.addColumn(HEADER[i]);
		}
		
		String[] newRow = new String[HEADER.length];
		for(int i = 0; i < dto.size(); ++i) {
			newRow[0] = String.valueOf(dto.get(i).getAlist_id());
			newRow[1] = String.valueOf(dto.get(i).getAlist_count());
			newRow[2] = dto.get(i).getPd_id();
			newRow[3] = dto.get(i).getPd_name();
			newRow[4] = PosFrameProperties.numberFormatter(dto.get(i).getAl_price());
			newRow[5] = dto.get(i).getOp_id();
			
			model.addRow(newRow);
		}
		
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(JLabel.CENTER);
		
		JTable table = new JTable(model);
		table.setBackground(Color.white);
		table.setRowHeight(25);
		table.setFont(PosFrameProperties.BASIC);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		for(int i = 0; i < HEADER.length; ++i) {
			table.getColumnModel().getColumn(i).setPreferredWidth(colWidth[i]);
			table.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}
		
		int columnTotalSize = 0;
		for(int i = 0; i < colWidth.length; ++i)
			columnTotalSize += colWidth[i];
		
		table.setSize(columnTotalSize, 100);
		JScrollPane scroll = new JScrollPane(table);
		scroll.getViewport().setBackground(Color.WHITE);
		
		add(scroll);
		
		setSize(600, 350);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("주문정보 조회");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModalityType(DEFAULT_MODALITY_TYPE);
	}
	
}
