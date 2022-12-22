package com.mem.model;

import java.sql.Date;

public class MemVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer mem_id;
	private String mem_account;
	private String mem_password;
	private String mem_name;
	private String mem_address;
	private String mem_phone;
	private String mem_uid;
	private String mem_email;
	private String mem_sex;
	private Date mem_dob;
	private Integer mem_status;
//	private byte[] mem_up;
	
	
//	public byte[] getMem_up() {
//		return mem_up;
//	}
//	public void setMem_up(byte[] mem_up) {
//		this.mem_up = mem_up;
//	}
	public Integer getMem_id() {
		return mem_id;
	}
	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_account() {
		return mem_account;
	}
	public void setMem_account(String mem_account) {
		this.mem_account = mem_account;
	}
	public String getMem_password() {
		return mem_password;
	}
	public void setMem_password(String mem_password) {
		this.mem_password = mem_password;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_address() {
		return mem_address;
	}
	public void setMem_address(String mem_address) {
		this.mem_address = mem_address;
	}
	public String getMem_phone() {
		return mem_phone;
	}
	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}
	public String getMem_uid() {
		return mem_uid;
	}
	public void setMem_uid(String mem_uid) {
		this.mem_uid = mem_uid;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public String getMem_sex() {
		return mem_sex;
	}
	public void setMem_sex(String mem_sex) {
		this.mem_sex = mem_sex;
	}
	public Date getMem_dob() {
		return mem_dob;
	}
	public void setMem_dob(Date mem_dob) {
		this.mem_dob = mem_dob;
	}
	public Integer getMem_status() {
		return mem_status;
	}
	public void setMem_status(Integer mem_status) {
		this.mem_status = mem_status;
	}
	
	public com.article_identity.model.Article_identityVO getArticle_identityVO() {
		com.article_identity.model.Article_identityService article_identitySvc = new com.article_identity.model.Article_identityService();
		com.article_identity.model.Article_identityVO article_identityVO = article_identitySvc.getOneArticle_picture(mem_id);
	    return article_identityVO;
	}
}
