package com.group_buy_item_picture.model;

public class groupBuyItemPictureVO implements java.io.Serializable {
	
	private Integer gbip_id;
	private Integer gbitem_id;
	private byte[]  gbip_content;
	
	
	public Integer getGbip_id() {
		return gbip_id;
	}
	public void setGbip_id(Integer gbip_id) {
		this.gbip_id = gbip_id;
	}
	public Integer getGbitem_id() {
		return gbitem_id;
	}
	public void setGbitem_id(Integer gbitem_id) {
		this.gbitem_id = gbitem_id;
	}
	public byte[] getGbip_content() {
		return gbip_content;
	}
	public void setGbip_content(byte[] gbip_content) {
		this.gbip_content = gbip_content;
	}
	
	

	
	
}
