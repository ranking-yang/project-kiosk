package receipt;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Vector;

import database.dto.OrderListDTO;
import database.dto.InputDataSet;
import project.CreditCardScreen;
import project.PosFrameProperties;

/**영수증에 들어갈 정보들을 담는 클래스*/
public class ReceiptInfo {
	
	private Integer orderNumber;
	private String branchName;
	private Integer branchNumber;
	private String phoneNumber;
	private String address;
	private String managerName;
	private String sellingTime;
	private String orderDetail;
	private Integer paymentWay;
	private int totalBeverage;
	private int count;
	
	private List<OrderListDTO> availDto = InputDataSet.getAvailList();
	
	@SuppressWarnings("rawtypes")
	private Vector<Vector> productVec; 
	
	@SuppressWarnings("rawtypes")
	public ReceiptInfo(Vector<Vector> productVec) {
		this.productVec = productVec;
		
		this.orderNumber = CreditCardScreen.getOrderNumber();
		this.branchName = "Ezen Coffe 구리지점";
		this.branchNumber = 1;
		this.phoneNumber = "031-555-4449";
		this.address = "경기도구리시인창동(670-1태영빌딩)";
		this.managerName = "Mr_Yang";
		this.sellingTime = LocalDateTime.now().toString().substring(0, 19);
		this.orderDetail = setOrderDetail();
		this.paymentWay = InputDataSet.getSellingDataArray()[0];
	}
	
	/** 영수증 번호를 구하는 메서드 */
	public String getReceiptNumber() {
		Integer sd_id = availDto.get(0).getSd_id();
		
		if (sd_id == null) sd_id = 0;
		
		return String.format("%08d", sd_id) + String.format("%03d", CreditCardScreen.getOrderNumber());
	}
	
	/** 제품 세부 내역을 구하는 메서드 */ 
	@SuppressWarnings("unchecked")
	public String setOrderDetail() {
		count = 0;
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < productVec.size(); ++i) {
			//{"삭제", "음료이름", "옵션", "+", "개수", "-", "가격", "제품번호/옵션번호"}
			Vector<String> productInfo = productVec.get(i);
			
			String name = productInfo.get(1);
			String optionName = productInfo.get(2);
			String qty = productInfo.get(4);
			String price = PosFrameProperties.numberFormatter(Integer.parseInt(productInfo.get(6)));
			sb.append(++count +")제품명:" + name + "\n옵션:" + optionName + "\n" + qty + "개 " + price + "원\n\n");
			totalBeverage += Integer.parseInt(qty);
		}
		
		return sb.toString();
	}
	
	public Integer getOrderNumber() {
		return orderNumber;
	}

	public String getBranchName() {
		return branchName;
	}

	public Integer getBranchNumber() {
		return branchNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public String getManagerName() {
		return managerName;
	}

	public String getSellingTime() {
		return sellingTime;
	}

	public Integer getPriceWithoutSurTax() {
		return getTotalPrice() - (getTotalPrice() / 10);
	}

	public Integer getSurTax() {
		return getTotalPrice() / 10;
	}

	public Integer getTotalPrice() {
		int sum = 0;
		for (int i = 0; i < availDto.size(); ++i) {
			int price = availDto.get(i).getAl_price();
			
			sum += price;
		}
		return sum;
	}

	public Integer getPaymentWay() {
		return paymentWay;
	}

	public int getTotalBeverage() {
		return totalBeverage;
	}
	
	public String getOrderDetail() {
		return orderDetail;
	}
	
	public int getCount() {
		return count;
	}

}
