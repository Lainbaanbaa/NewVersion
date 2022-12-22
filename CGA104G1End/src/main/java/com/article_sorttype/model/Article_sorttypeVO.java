package com.article_sorttype.model;

public class Article_sorttypeVO implements java.io.Serializable{

	private Integer sort_id;
	private String sort_content;
	
	public Integer getSort_id() {
		return sort_id;
	}
	public void setSort_id(Integer sort_id) {
		this.sort_id = sort_id;
	}
	public String getSort_content() {
		return sort_content;
	}
	public void setSort_content(String sort_content) {
		this.sort_content = sort_content;
	}
	
	public String toString() {
		return "Article_sorttypeVO [sort_id=" + sort_id + ", sort_content=" + sort_content + "]";
	}
	
}
