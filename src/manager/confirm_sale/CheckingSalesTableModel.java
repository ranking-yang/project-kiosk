package manager.confirm_sale;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.table.AbstractTableModel;

import database.dto.CheckingSalesDTO;

/** ShowSalesScreen의 JTable에 사용하기 위한 TableModel
 *  @author HONG */
public class CheckingSalesTableModel extends AbstractTableModel {
	/** AbstractTableModel for SSTableModel */
	private static final long serialVersionUID = 4447400537080255988L;
	private ArrayList<CheckingSalesDTO> data = new ArrayList<>();
	private NumberFormat numberFormat = NumberFormat.getInstance(Locale.KOREA);
	
	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

    public void addData(CheckingSalesDTO peice){
    	if(peice == null) {
    		return;
    	}
    	
        int idx = data.size();
        data.add(peice);
        fireTableRowsInserted(idx, idx); // 반드시 호출해야한다.
    }
	
    /** List의 모든 데이터 삭제 */
    public void removeAllData() {
    	if(data == null) {
    		return;
    	}
    	
    	int idx = data.size();
    	data.removeAll(data);
    	fireTableRowsDeleted(idx, idx);
    }
    
    public int getTotalPrice() {
    	int totalPrice = 0;
    	
    	for(int i = 0; i < data.size(); ++i) {
    		totalPrice += data.get(i).getPrice();
    	}
    	
    	return totalPrice;
    }
    
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		CheckingSalesDTO DTO = data.get(rowIndex);
        switch (columnIndex) {
        case 0 :    return DTO.getId();
        case 1 :    return DTO.getTime();
        case 2 :    return DTO.getPay_id();
        case 3 :    return numberFormat.format(DTO.getPrice());
        case 4 :	return DTO.getPick_up() == 0 ? "매장" : "포장";
       	default :   return "invalid";
        }
	}
	
}
