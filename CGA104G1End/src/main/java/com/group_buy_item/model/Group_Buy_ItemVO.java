package com.group_buy_item.model;

import java.sql.Date;

public class Group_Buy_ItemVO implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer gbitem_id;
	private String gbitem_name;
	private String gbitem_content;
	private Integer gbitem_price;
	private Integer gbitem_status;
	private Date gbitem_startdate;
	private Date gbitem_enddate;
	private Integer gbitem_type;
	
	public Integer getGbitem_id() {
		return gbitem_id;
	}
	public void setGbitem_id(Integer gbitem_id) {
		this.gbitem_id = gbitem_id;
	}
	public String getGbitem_name() {
		return gbitem_name;
	}
	public void setGbitem_name(String gbitem_name) {
		this.gbitem_name = gbitem_name;
	}
	public String getGbitem_content() {
		return gbitem_content;
	}
	public void setGbitem_content(String gbitem_content) {
		this.gbitem_content = gbitem_content;
	}
	public Integer getGbitem_price() {
		return gbitem_price;
	}
	public void setGbitem_price(Integer gbitem_price) {
		this.gbitem_price = gbitem_price;
	}
	public Integer getGbitem_status() {
		return gbitem_status;
	}
	public void setGbitem_status(Integer gbitem_status) {
		this.gbitem_status = gbitem_status;
	}
	public Date getGbitem_startdate() {
		return gbitem_startdate;
	}
	public void setGbitem_startdate(Date gbitem_startdate) {
		this.gbitem_startdate = gbitem_startdate;
	}
	public Date getGbitem_enddate() {
		return gbitem_enddate;
	}
	public void setGbitem_enddate(Date gbitem_enddate) {
		this.gbitem_enddate = gbitem_enddate;
	}
	public Integer getGbitem_type() {
		return gbitem_type;
	}
	public void setGbitem_type(Integer gbitem_type) {
		this.gbitem_type = gbitem_type;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
}
