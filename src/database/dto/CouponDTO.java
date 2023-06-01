package database.dto;

/** 쿠폰 정보를 받아오기 위한 DTO */
public class CouponDTO {
	String phone_number;
	Integer coupon_count;
	Integer stamp_count;
	
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public Integer getCoupon_count() {
		return coupon_count;
	}
	public void setCoupon_count(Integer coupon_count) {
		this.coupon_count = coupon_count;
	}
	public Integer getStamp_count() {
		return stamp_count;
	}
	public void setStamp_count(Integer stamp_count) {
		this.stamp_count = stamp_count;
	}
}
