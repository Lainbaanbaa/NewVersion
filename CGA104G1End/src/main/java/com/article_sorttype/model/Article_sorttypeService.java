package com.article_sorttype.model;

import java.util.List;

public class Article_sorttypeService {
	
private Article_sorttypeDAO_interface dao;
	
	public Article_sorttypeService() {
		dao = new Article_sorttypeJDBCDAO();
	}
	
	public Article_sorttypeVO addArticle_sorttype(String sort_content) {
		Article_sorttypeVO article_sorttypeVO = new Article_sorttypeVO();
		article_sorttypeVO.setSort_content(sort_content);;
		dao.insert(article_sorttypeVO);
		return article_sorttypeVO;
	}
	
	public Article_sorttypeVO updateArticle(Integer sort_id, String sort_content) {
		Article_sorttypeVO article_sorttypeVO = new Article_sorttypeVO();
		article_sorttypeVO.setSort_id(sort_id);
		article_sorttypeVO.setSort_content(sort_content);
		dao.update(article_sorttypeVO);
		return article_sorttypeVO;
	}
	
	public void deleteSort(Integer sort_id) {
		dao.delete(sort_id);
	}

	public Article_sorttypeVO getOneSort(Integer sort_id) {
		return dao.findByPrimaryKey(sort_id);
	}
	
	public List<Article_sorttypeVO> getAll() {
		return dao.getAll();
	}

}
