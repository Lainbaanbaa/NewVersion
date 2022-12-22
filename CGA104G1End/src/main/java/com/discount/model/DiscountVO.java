package com.discount.model;

	public class DiscountVO implements java.io.Serializable{
	private Integer discount_id;
	private Integer gbitem_id;
	private Integer discount_minamount;
	private Integer discount_maxamount;
	private Integer discount_price;
	private String discount_nar;
	
	public Integer getDiscount_id() {
		return discount_id;
	}
	public void setDiscount_id(Integer discount_id) {
		this.discount_id = discount_id;
	}
	public Integer getGbitem_id() {
		return gbitem_id;
	}
	public void setGbitem_id(Integer gbitem_id) {
		this.gbitem_id = gbitem_id;
	}
	public Integer getDiscount_minamount() {
		return discount_minamount;
	}
	public void setDiscount_minamount(Integer discount_minamount) {
		this.discount_minamount = discount_minamount;
	}
	public Integer getDiscount_maxamount() {
		return discount_maxamount;
	}
	public void setDiscount_maxamount(Integer discount_maxamount) {
		this.discount_maxamount = discount_maxamount;
	}
	public Integer getDiscount_price() {
		return discount_price;
	}
	public void setDiscount_price(Integer discount_price) {
		this.discount_price = discount_price;
	}
	public String getDiscount_nar() {
		return discount_nar;
	}
	public void setDiscount_nar(String discount_nar) {
		this.discount_nar = discount_nar;
	}

//TESTgALLON
}
