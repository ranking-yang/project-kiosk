package database.dto;

/** 판매정보를 담기 위한 DTO */
public class SellingDataDTO {
	
	private Integer sd_id;
	private Integer pay_id;
	private Integer bc_id;
	private Integer pick_up;
	
	
	public Integer getSd_id() {
		return sd_id;
	}
	public void setSd_id(Integer sd_id) {
		this.sd_id = sd_id;
	}
	public Integer getPay_id() {
		return pay_id;
	}
	public void setPay_id(Integer pay_id) {
		this.pay_id = pay_id;
	}
	public Integer getBc_id() {
		return bc_id;
	}
	public void setBc_id(Integer bc_id) {
		this.bc_id = bc_id;
	}
	public Integer getPick_up() {
		return pick_up;
	}
	public void setPick_up(Integer pick_up) {
		this.pick_up = pick_up;
	}
	
}
