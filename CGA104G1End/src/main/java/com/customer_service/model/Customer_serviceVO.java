package com.customer_service.model;

import java.sql.Timestamp;

public class Customer_serviceVO implements java.io.Serializable{
	
	private Integer cs_id;
	private Integer mem_id;
	private Integer emp_id;
	private Timestamp cs_time;
	private String cs_context;
	private Integer cs_pointer;
	
	
	public Integer getCs_id() {
		return cs_id;
	}
	public void setCs_id(Integer cs_id) {
		this.cs_id = cs_id;
	}
	public Integer getMem_id() {
		return mem_id;
	}
	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}
	public Integer getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}
	public Timestamp getCs_time() {
		return cs_time;
	}
	public void setCs_time(Timestamp cs_time) {
		this.cs_time = cs_time;
	}
	public String getCs_context() {
		return cs_context;
	}
	public void setCs_context(String cs_context) {
		this.cs_context = cs_context;
	}
	public Integer getCs_pointer() {
		return cs_pointer;
	}
	public void setCs_pointer(Integer cs_pointer) {
		this.cs_pointer = cs_pointer;
	}


}
