package com.news.model;

import java.util.List;

import core.dao.CoreDao;

public interface NewsDAO_interface extends CoreDao{
	public void insert(NewsVO newsVO);
    public void update(NewsVO newsVO);
    public void delete(Integer newsId);
    public List<NewsVO> findByPrimaryKey(String newsTitle);
    public List<NewsVO> getAll();
}
