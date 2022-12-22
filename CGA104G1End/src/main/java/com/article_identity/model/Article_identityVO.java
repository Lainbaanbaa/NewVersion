package com.article_identity.model;

import java.sql.Timestamp;

public class Article_identityVO implements java.io.Serializable{
	private Integer article_picno;
	private String article_pic;
	private Integer mem_id;
	private Timestamp upload_time;
	public Integer getArticle_picno() {
		return article_picno;
	}
	public void setArticle_picno(Integer article_picno) {
		this.article_picno = article_picno;
	}
	public String getArticle_pic() {
		return article_pic;
	}
	public void setArticle_pic(String article_pic) {
		this.article_pic = article_pic;
	}
	public Integer getMem_id() {
		return mem_id;
	}
	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}
	public Timestamp getUpload_time() {
		return upload_time;
	}
	public void setUpload_time(Timestamp upload_time) {
		this.upload_time = upload_time;
	}
	
	public String toString() {
		return "Article_identityVO [article_picno=" + article_picno + ", article_pic=" + article_pic + 
				", mem_id=" + mem_id + ", upload_time=" + upload_time +"]";
	}
}
