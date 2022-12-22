package com.qualified_doctor.model;

public class Qualified_doctorVO implements java.io.Serializable {
	
	private Integer doc_id;
	private Integer mem_id;
	private Integer doc_status;
	
	
	public Integer getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(Integer doc_id) {
		this.doc_id = doc_id;
	}
	public Integer getMem_id() {
		return mem_id;
	}
	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}
	public Integer getDoc_status() {
		return doc_status;
	}
	public void setDoc_status(Integer doc_status) {
		this.doc_status = doc_status;
	}

}
