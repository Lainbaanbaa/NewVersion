package com.news.model;

import java.sql.Timestamp;
import javax.persistence.*;

@Entity
@Table(name = "news", schema = "ba_rei")
public class NewsVO implements java.io.Serializable{
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "news_id", insertable = false)
	private Integer newsId;
	@Column(name = "emp_id")
	private Integer empId;
	@Column(name = "news_title")
	private String newsTitle;
	@Column(name = "news_content")
	private String newsContent;
	@Column(name = "create_time", insertable = false)
	private Timestamp createTime;
	
	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	public com.emp.model.EmpVO getEmpVO() {
		com.emp.model.EmpService empSvc = new com.emp.model.EmpService();
		com.emp.model.EmpVO empVO = empSvc.getOneEmp(empId);
	    return empVO;
    }


}
