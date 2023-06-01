package database.dto;

/** 주문목록 DTO */
public class OrderListDTO {
	
	private Integer pd_id;
	private Integer alist_count;
	private Integer al_price;
	private Integer sd_id; 
	private String op_id;
	private String pd_name;
	
	public Integer getPd_id() {
		return pd_id;
	}
	public String getPd_name() {
		return pd_name;
	}
	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}
	public void setPd_id(Integer pd_id) {
		this.pd_id = pd_id;
	}
	public String getOp_id() {
		return op_id;
	}
	public void setOp_id(String op_id) {
		this.op_id = op_id;
	}
	public Integer getAlist_count() {
		return alist_count;
	}
	public void setAlist_count(Integer alist_count) {
		this.alist_count = alist_count;
	}
	public Integer getAl_price() {
		return al_price;
	}
	public void setAl_price(Integer al_price) {
		this.al_price = al_price;
	}
	public Integer getSd_id() {
		return sd_id;
	}
	public void setSd_id(Integer sd_id) {
		this.sd_id = sd_id;
	}
	
}
