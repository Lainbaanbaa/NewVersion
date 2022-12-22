package com.notification.mode;

import java.sql.Timestamp;

public class NotificationVO implements java.io.Serializable{
	
	private Integer ntf_id;
	private Integer mem_id;
	private String ntf_context;
	private Timestamp ntf_time;
	private Integer ntf_read;
	public Integer getNtf_id() {
		return ntf_id;
	}
	public void setNtf_id(Integer ntf_id) {
		this.ntf_id = ntf_id;
	}
	public Integer getMem_id() {
		return mem_id;
	}
	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}
	public String getNtf_context() {
		return ntf_context;
	}
	public void setNtf_context(String ntf_context) {
		this.ntf_context = ntf_context;
	}
	public Timestamp getNtf_time() {
		return ntf_time;
	}
	public void setNtf_time(Timestamp ntf_time) {
		this.ntf_time = ntf_time;
	}
	public Integer getNtf_read() {
		return ntf_read;
	}
	public void setNtf_read(Integer ntf_read) {
		this.ntf_read = ntf_read;
	}
	

	

}
