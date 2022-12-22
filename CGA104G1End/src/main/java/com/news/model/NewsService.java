package com.news.model;

import java.util.List;

import org.json.JSONArray;

public class NewsService {
	private NewsDAO_interface dao;
	public NewsService() {
		dao = new NewsDAO();
	}

	public NewsVO addNews(NewsVO newsVO) {
		dao.insert(newsVO);
		return newsVO;
	}
	
	public NewsVO updateNews(NewsVO newsVO) {
		dao.update(newsVO);
		return newsVO;
	}
	
	public void deleteNews(Integer newsId) {
		dao.delete(newsId);
	}
	
	public List<NewsVO> searchNews(String newsTitle) {
		return dao.findByPrimaryKey(newsTitle);
	}
	
	public List<NewsVO> getAll() {
		return dao.getAll();
	}
	
}
