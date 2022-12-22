package com.group_buy_report.model;

import java.sql.Timestamp;

public class Group_Buy_ReportVO implements java.io.Serializable{
	

	private static final long serialVersionUID = 1L;
	
	private Integer gbfrep_id;
	private Integer gborder_id;
	private Integer mem_id;
	private String frep_content;
	private Timestamp frep_time;
	private Integer frep_status;
	private Integer frep_result;
	private Integer emp_id;
	
	
	public Integer getGbfrep_id() {
		return gbfrep_id;
	}
	public void setGbfrep_id(Integer gbfrep_id) {
		this.gbfrep_id = gbfrep_id;
	}
	public Integer getGborder_id() {
		return gborder_id;
	}
	public void setGborder_id(Integer gborder_id) {
		this.gborder_id = gborder_id;
	}
	public Integer getMem_id() {
		return mem_id;
	}
	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}
	public String getFrep_content() {
		return frep_content;
	}
	public void setFrep_content(String frep_content) {
		this.frep_content = frep_content;
	}
	public Timestamp getFrep_time() {
		return frep_time;
	}
	public void setFrep_time(Timestamp frep_time) {
		this.frep_time = frep_time;
	}
	public Integer getFrep_status() {
		return frep_status;
	}
	public void setFrep_status(Integer frep_status) {
		this.frep_status = frep_status;
	}
	public Integer getFrep_result() {
		return frep_result;
	}
	public void setFrep_result(Integer frep_result) {
		this.frep_result = frep_result;
	}
	public Integer getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public com.mem.model.MemVO getmemVO() {
		com.mem.model.MemService memSvc = new com.mem.model.MemService();
	    com.mem.model.MemVO memVO = memSvc.getOneMem(mem_id);
	    return memVO;
    }
//	public com.mem.model.MemVO getmemVO() {
//		com.mem.model.MemService memSvc = new com.mem.model.MemService();
//		com.mem.model.MemVO memVO = memSvc.getOneMem(mem_id);
//		return memVO;
//	}
	
	
}
