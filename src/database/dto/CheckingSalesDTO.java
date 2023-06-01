package database.dto;

/** DB에서 SellingData를 받아오기 위한 DTO
 *  @author HONG */
public class CheckingSalesDTO {

	private Integer id;
	private String time;
	private Integer pay_id;
	private Integer price;
	private Integer pick_up;
	
	public CheckingSalesDTO(Integer id, String time, Integer pay_id, Integer price, Integer pick_up) {
		this.id = id;
		this.time = time;
		this.pay_id = pay_id;
		this.price = price;
		this.pick_up = pick_up;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getPay_id() {
		return pay_id;
	}

	public void setPay_id(Integer pay_id) {
		this.pay_id = pay_id;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getPick_up() {
		return pick_up;
	}

	public void setPick_up(Integer pick_up) {
		this.pick_up = pick_up;
	}
	
	@Override
	public String toString() {
		return String.format("%d (%d)", id, price);
	}
	
}
