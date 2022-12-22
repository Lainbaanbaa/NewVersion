package com.article_report.model;

import java.util.List;

public interface Article_reportDAO_interface {
	public void insert(Article_reportVO article_reportVO);
    public void update(Article_reportVO article_reportVO);
    public void delete(Integer afrep_id);
    public Article_reportVO findByPrimaryKey(Integer afrep_id);
    public List<Article_reportVO> getAll();
    public List<Article_reportVO> getAllSolved();

}
