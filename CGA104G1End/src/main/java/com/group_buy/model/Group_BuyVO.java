package com.group_buy.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Group_BuyVO implements java.io.Serializable{
	private Integer gb_id;
	private Integer mem_id;
	private Integer gbitem_id;
	private Integer gb_min;
	private Integer gb_amount;
	private Timestamp gbstart_date;
	private Timestamp gbend_date;
	private Integer gb_status;
	private Integer gb_price;
	private String  gb_name;
	
	private String gbitem_name;
	private String gbitem_content;
	private Integer gbitem_price;
	private Integer gbitem_status;
	private Date gbitem_startdate;
	private Date gbitem_enddate;
	private Integer gbitem_type;
	
	 public com.mem.model.MemVO getMemVO() {
		 com.mem.model.MemService memService = new com.mem.model.MemService();
		 com.mem.model.MemVO memVO = memService.getOneMem(mem_id);
		    return memVO;
	  }
	
	
	public Integer getGb_id() {
		return gb_id;
	}
	public void setGb_id(Integer gb_id) {
		this.gb_id = gb_id;
	}
	public Integer getMem_id() {
		return mem_id;
	}
	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}
	public Integer getGbitem_id() {
		return gbitem_id;
	}
	public void setGbitem_id(Integer gbitem_id) {
		this.gbitem_id = gbitem_id;
	}
	public Integer getGb_min() {
		return gb_min;
	}
	public void setGb_min(Integer gb_min) {
		this.gb_min = gb_min;
	}
	public Integer getGb_amount() {
		return gb_amount;
	}
	public void setGb_amount(Integer gb_amount) {
		this.gb_amount = gb_amount;
	}
	public Timestamp getGbstart_date() {
		return gbstart_date;
	}
	public void setGbstart_date(Timestamp gbstart_date) {
		this.gbstart_date = gbstart_date;
	}
	public Timestamp getGbend_date() {
		return gbend_date;
	}
	public void setGbend_date(Timestamp gbend_date) {
		this.gbend_date = gbend_date;
	}
	public Integer getGb_status() {
		return gb_status;
	}
	public void setGb_status(Integer gb_status) {
		this.gb_status = gb_status;
	}
	public Integer getGb_price() {
		return gb_price;
	}
	public void setGb_price(Integer gb_price) {
		this.gb_price = gb_price;
	}
	public String getGb_name() {
		return gb_name;
	}
	public void setGb_name(String gb_name) {
		this.gb_name = gb_name;
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
	
	
	
	
	
	
	
	
	


}
