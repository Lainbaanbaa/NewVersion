package com.article_report.model;

import java.sql.Date;

public class Article_reportVO implements java.io.Serializable{
	private Integer afrep_id;
	private Integer article_id;
	private Integer mem_id;
	private String afrep_content;
	private Integer afrep_status;
	private Integer afrep_result;
	private Integer emp_id;
	private Date afrep_date;
	
	public Integer getAfrep_id() {
		return afrep_id;
	}
	public void setAfrep_id(Integer afrep_id) {
		this.afrep_id = afrep_id;
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
	public String getAfrep_content() {
		return afrep_content;
	}
	public void setAfrep_content(String afrep_content) {
		this.afrep_content = afrep_content;
	}
	public Integer getAfrep_status() {
		return afrep_status;
	}
	public void setAfrep_status(Integer afrep_status) {
		this.afrep_status = afrep_status;
	}
	public Integer getAfrep_result() {
		return afrep_result;
	}
	public void setAfrep_result(Integer afrep_result) {
		this.afrep_result = afrep_result;
	}
	public Integer getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}
	public Date getAfrep_date() {
		return afrep_date;
	}
	public void setAfrep_date(Date afrep_date) {
		this.afrep_date = afrep_date;
	}
	
	
	public com.article.model.ArticleVO getArticleVO() {
		com.article.model.ArticleService articleSvc = new com.article.model.ArticleService();
		com.article.model.ArticleVO articlVO = articleSvc.getOneArticle(article_id);
	    return articlVO;
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

	public com.emp.model.EmpVO getEmpVO() {
		com.emp.model.EmpService empSvc = new com.emp.model.EmpService();
		com.emp.model.EmpVO empVO = empSvc.getOneEmp(emp_id);
	    return empVO;
    }
}
