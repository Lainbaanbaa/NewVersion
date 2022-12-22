package com.artLikeHate.model;

import java.sql.Timestamp;

public class ArtLikeHateVO implements java.io.Serializable{
	private Integer mem_id;
	private Integer article_id;
	private Integer like_status;
	private Timestamp like_time;
	public Integer getMem_id() {
		return mem_id;
	}
	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}
	public Integer getArticle_id() {
		return article_id;
	}
	public void setArticle_id(Integer article_id) {
		this.article_id = article_id;
	}
	public Integer getLike_status() {
		return like_status;
	}
	public void setLike_status(Integer like_status) {
		this.like_status = like_status;
	}
	public Timestamp getLike_time() {
		return like_time;
	}
	public void setLike_time(Timestamp like_time) {
		this.like_time = like_time;
	}
	
	public Integer getLikeCount() {
		ArtLikeHateService artLikeHateSvc = new ArtLikeHateService();
		Integer like = artLikeHateSvc.getLike(article_id);
		return like;
	}
}
