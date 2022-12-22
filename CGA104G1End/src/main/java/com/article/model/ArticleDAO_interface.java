package com.article.model;

import java.util.List;
import java.util.Map;

public interface ArticleDAO_interface {
	public void insert(ArticleVO articleVO);
    public void update(ArticleVO articleVO);
    public void delete(Integer article_id);
    public ArticleVO findByPrimaryKey(Integer article_id);
    public List<ArticleVO> getAll();
	public void hideArticle(ArticleVO articleVO);
	public List<ArticleVO> getAllArt(Map<String, String[]> map);
}
