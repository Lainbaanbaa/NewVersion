package com.verify_code.model;

import java.sql.Timestamp;

public class Verify_codeVO implements java.io.Serializable{
	

	private static final long serialVersionUID = 1L;
	private Integer vc_id;
	private Integer mem_id;
	private String vc_code;
	
	
	public void setVc_time(Timestamp vc_time) {
		this.vc_time = vc_time;
	}
	private Timestamp vc_time;
	
	
	public Integer getVc_id() {
		return vc_id;
	}
	public void setVc_id(Integer vc_id) {
		this.vc_id = vc_id;
	}
	public Integer getMem_id() {
		return mem_id;
	}
	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}
	public String getVc_code() {
		return vc_code;
	}
	public void setVc_code(String vc_code) {
		this.vc_code = vc_code;
	}
	public Timestamp getVc_time() {
		return vc_time;
	}


	

}
