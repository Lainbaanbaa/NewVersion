package com.artLikeHate.model;

public class ArtLikeHateService {
	
	private ArtLikeHateInterface dao;
	
	public ArtLikeHateService() {
		dao = new ArtLikeHateJDBCDAO();
	}
	
	public ArtLikeHateVO insertUpdate(Integer mem_id, Integer article_id, Integer like_status) {
		ArtLikeHateVO artLikeHateVO = new ArtLikeHateVO();
		artLikeHateVO.setMem_id(mem_id);
		artLikeHateVO.setArticle_id(article_id);
		artLikeHateVO.setLike_status(like_status);
		dao.insertUpdate(artLikeHateVO);
		return artLikeHateVO;
	}
	
	public Integer getLike(Integer article_id) {
		return dao.countLike(article_id);
	}
	
	public Integer getHate(Integer article_id) {
		return dao.countHate(article_id);
	}

}
