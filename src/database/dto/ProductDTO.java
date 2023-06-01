package database.dto;

/** 제품정보를 받아오기 위한 DTO */
public class ProductDTO {
	
	private Integer pd_id;
	private String pd_name;
	private Integer pd_price;
	private String pd_thumbnail;
	private Integer tm_id;
	private boolean pd_shot;
	private boolean pd_milk;
	private boolean pd_cream;
	private boolean pd_ice;
	private boolean pd_stevia;
	
	public Integer getPd_id() {
		return pd_id;
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
	public Integer getPd_price() {
		return pd_price;
	}
	public void setPd_price(Integer pd_price) {
		this.pd_price = pd_price;
	}
	public String getPd_thumbnail() {
		return pd_thumbnail;
	}
	public void setPd_thumbnail(String pd_thumbnail) {
		this.pd_thumbnail = pd_thumbnail;
	}
	public Integer getTm_id() {
		return tm_id;
	}
	public void setTm_id(Integer tm_id) {
		this.tm_id = tm_id;
	}
	public boolean isPd_shot() {
		return pd_shot;
	}
	public void setPd_shot(boolean pd_shot) {
		this.pd_shot = pd_shot;
	}
	public boolean isPd_milk() {
		return pd_milk;
	}
	public void setPd_milk(boolean pd_milk) {
		this.pd_milk = pd_milk;
	}
	public boolean isPd_cream() {
		return pd_cream;
	}
	public void setPd_cream(boolean pd_cream) {
		this.pd_cream = pd_cream;
	}
	public boolean isPd_ice() {
		return pd_ice;
	}
	public void setPd_ice(boolean pd_ice) {
		this.pd_ice = pd_ice;
	}
	public boolean isPd_stevia() {
		return pd_stevia;
	}
	public void setPd_stevia(boolean pd_stevia) {
		this.pd_stevia = pd_stevia;
	}
	
}
