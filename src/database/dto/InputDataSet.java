package database.dto;

import java.util.ArrayList;
import java.util.List;

/** DB에 넣어줘야하는 정보를 모아두는 클래스 */
public class InputDataSet {
	private static List<OrderListDTO> orderList = new ArrayList<>();
	
	/**OrderListDTO를 담은 리스트를 반환한다*/
	public static List<OrderListDTO> getAvailList() {
		return orderList;
	}
	
	/**OrderListDTO를 리스트에 담아주는 메서드*/
	public static void addAvailableListDTO(OrderListDTO orderListDTO) {
		orderList.add(orderListDTO);
	}
	
	/**0번: pay_id
	 * 1번: pick_up*/
	private static Integer[] sellingDataArray = {null, null};
	
	/**pay_id와 pick_up 을 담고있는 배열을 반환한다*/
	public static Integer[] getSellingDataArray() {
		return sellingDataArray;
	}
	
	/**0번: pay_id
	 * 1번: pick_up*/
	public static void setSellingDataArray(int index, Integer value) {
		sellingDataArray[index] = value;
	}
	
}
