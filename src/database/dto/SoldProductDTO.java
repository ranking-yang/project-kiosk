package database.dto;

/** 판매데이터에 띄워줄 제품에 대한 DTO */
public class SoldProductDTO {
	
	Integer alist_id;
	Integer alist_count;
	Integer pd_id;
	String pd_name;
	Integer al_price;
	String op_id;
	
	public Integer getAlist_id() {
		return alist_id;
	}
	public void setAlist_id(Integer alist_id) {
		this.alist_id = alist_id;
	}
	public Integer getAlist_count() {
		return alist_count;
	}
	public void setAlist_count(Integer alist_count) {
		this.alist_count = alist_count;
	}
	public String getPd_id() {
		if(pd_id == 0) 
			return "단종";
		return String.valueOf(pd_id);
	}
	public void setPd_id(Integer pd_id) {
		this.pd_id = pd_id;
	}
	public String getPd_name() {
		return pd_name;
	}
	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}
	public Integer getAl_price() {
		return al_price;
	}
	public void setAl_price(Integer al_price) {
		this.al_price = al_price;
	}
	public String getOp_id() {
		return op_id;
	}
	public void setOp_id(String op_id) {
		this.op_id = op_id;
	}
	
}
