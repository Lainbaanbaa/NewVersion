package com.article_comment.model;

import java.util.List;

public class Article_commentService {
	
	private Article_commentDAO_interface dao;
	public  Article_commentService() {
		dao = new Article_commentJDBCDAO();
	}

	public Article_commentVO addArticle_comment(Integer article_id, Integer mem_id, String com_content) {
		Article_commentVO article_commentVO = new Article_commentVO();
		article_commentVO.setArticle_id(article_id);
		article_commentVO.setMem_id(mem_id);
		article_commentVO.setCom_content(com_content);
		dao.insert(article_commentVO);
		return article_commentVO;
	}
	
	public Article_commentVO updateArticle_comment(String com_content, Integer com_id) {
		Article_commentVO article_commentVO = new Article_commentVO();
		article_commentVO.setCom_content(com_content);
		article_commentVO.setCom_id(com_id);
		dao.update(article_commentVO);
		return article_commentVO;
	}
	
	public void deleteArticle_comment(Integer com_id) {
		dao.delete(com_id);
	}
	
	//查詢單一留言應該不需要
	public Article_commentVO getOneArticle_comment(Integer com_id) {
		return dao.findByPrimaryKey(com_id);
	}
	
	public List<Article_commentVO> getAll(Integer article_id) {
		return dao.getAll(article_id);
	}

}
