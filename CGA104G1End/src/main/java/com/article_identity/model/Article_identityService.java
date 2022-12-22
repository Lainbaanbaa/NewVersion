package com.article_identity.model;

public class Article_identityService {
private Article_identityDAO_interface dao;
	
	public Article_identityService() {
		dao = new Article_identityJDBCDAO();
	}
	
	public Article_identityVO addArticle_picture(Integer mem_id, String article_pic) {
		Article_identityVO article_pictureVO = new Article_identityVO();
		article_pictureVO.setMem_id(mem_id);
		article_pictureVO.setArticle_pic(article_pic);
		System.out.println(1);
		dao.insert(article_pictureVO);
		
		return article_pictureVO;
	}

	public Article_identityVO getOneArticle_picture(Integer mem_id) {
		return dao.findByPrimaryKey(mem_id);
	}
	

}
