package com.article.model;

import java.sql.Date;

public class ArticleVO implements java.io.Serializable{
	
	private Integer article_id;
	private Integer mem_id;
	private Integer sort_id;
	private String article_title;
	private String article_content;
	private Integer article_status;
	private Integer article_like;
	private Integer article_dislike;
	private Date article_publish;
	private Date article_update;
	
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
	public Integer getSort_id() {
		return sort_id;
	}
	public void setSort_id(Integer sort_id) {
		this.sort_id = sort_id;
	}
	public String getArticle_title() {
		return article_title;
	}
	public void setArticle_title(String article_title) {
		this.article_title = article_title;
	}
	public String getArticle_content() {
		return article_content;
	}
	public void setArticle_content(String article_content) {
		this.article_content = article_content;
	}
	public Integer getArticle_status() {
		return article_status;
	}
	public void setArticle_status(Integer article_status) {
		this.article_status = article_status;
	}
	public Integer getArticle_like() {
		return article_like;
	}
	public void setArticle_like(Integer article_like) {
		this.article_like = article_like;
	}
	public Integer getArticle_dislike() {
		return article_dislike;
	}
	public void setArticle_dislike(Integer article_dislike) {
		this.article_dislike = article_dislike;
	}
	public Date getArticle_publish() {
		return article_publish;
	}
	public void setArticle_publish(Date article_publish) {
		this.article_publish = article_publish;
	}
	public Date getArticle_update() {
		return article_update;
	}
	public void setArticle_update(Date article_update) {
		this.article_update = article_update;
	}
	
	
	public com.article_sorttype.model.Article_sorttypeVO getArticle_sorttypeVO() {
		com.article_sorttype.model.Article_sorttypeService article_sorttypeSvc = new com.article_sorttype.model.Article_sorttypeService();
		com.article_sorttype.model.Article_sorttypeVO article_sorttypeVO = article_sorttypeSvc.getOneSort(sort_id);
	    return article_sorttypeVO;
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
	
	public Integer getArtLike() {
		com.artLikeHate.model.ArtLikeHateService artLikeHateSvc = new com.artLikeHate.model.ArtLikeHateService();
		Integer like = artLikeHateSvc.getLike(article_id);
	    return like;
	}
	
	public Integer getArtHate() {
		com.artLikeHate.model.ArtLikeHateService artLikeHateSvc = new com.artLikeHate.model.ArtLikeHateService();
		Integer hate = artLikeHateSvc.getHate(article_id);
	    return hate;
	}
}
