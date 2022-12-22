package com.article_comment.model;

import java.sql.Date;

public class Article_commentVO implements java.io.Serializable{
	private Integer com_id;
	private Integer article_id;
	private Integer mem_id;
	private String com_content;
	private Date com_publish;
	public Integer getCom_id() {
		return com_id;
	}
	public void setCom_id(Integer com_id) {
		this.com_id = com_id;
	}
	public Integer getArticle_id() {
		return article_id;
	}
	public void setArticle_id(Integer article_id) {
		this.article_id = article_id;
	}
	public Integer getMem_id() {
		return mem_id;
	}
	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}
	public String getCom_content() {
		return com_content;
	}
	public void setCom_content(String com_content) {
		this.com_content = com_content;
	}
	public Date getCom_publish() {
		return com_publish;
	}
	public void setCom_publish(Date com_publish) {
		this.com_publish = com_publish;
	}
	
	public com.article_identity.model.Article_identityVO getArticle_identityVO() {
		com.article_identity.model.Article_identityService article_identitySvc = new com.article_identity.model.Article_identityService();
		com.article_identity.model.Article_identityVO article_identityVO = article_identitySvc.getOneArticle_picture(mem_id);
	    return article_identityVO;
	}
	
	public com.mem.model.MemVO getMemVO() {
		com.mem.model.MemService memSvc = new com.mem.model.MemService();
		com.mem.model.MemVO memVO = memSvc.getOneMem(mem_id);
	    return memVO;
    }

}
