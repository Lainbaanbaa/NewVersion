package com.article_report.model;

import java.util.List;

public class Article_reportService {
private Article_reportDAO_interface dao;
	
	public Article_reportService() {
		dao = new Article_reportJDBCDAO();
	}
	
	public Article_reportVO addArticle_report(Integer article_id, Integer mem_id, String afrep_content, Integer afrep_status) {
		Article_reportVO article_reportVO = new Article_reportVO();
		article_reportVO.setArticle_id(article_id);
		article_reportVO.setMem_id(mem_id);
		article_reportVO.setAfrep_content(afrep_content);
		article_reportVO.setAfrep_status(afrep_status);
		dao.insert(article_reportVO);
		
		return article_reportVO;
	}
	
	public Article_reportVO updateArticle_report(Integer afrep_status, Integer afrep_result, Integer emp_id, Integer afrep_id) {
		Article_reportVO article_reportVO = new Article_reportVO();
		article_reportVO.setAfrep_status(afrep_status);
		article_reportVO.setAfrep_result(afrep_result);
		article_reportVO.setEmp_id(emp_id);
		article_reportVO.setAfrep_id(afrep_id);
		dao.update(article_reportVO);
		return article_reportVO;
	}
	
	public void deleteArticle_report(Integer afrep_id) {
		dao.delete(afrep_id);
	}

	public Article_reportVO getOneArticle_report(Integer afrep_id) {
		return dao.findByPrimaryKey(afrep_id);
	}
	
	public List<Article_reportVO> getAll() {
		return dao.getAll();
	}
	
	public List<Article_reportVO> getAllSolved() {
		return dao.getAllSolved();
	}

}
