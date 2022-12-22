package com.article_comment.model;

import java.util.List;

public interface Article_commentDAO_interface {

	public void insert(Article_commentVO article_commentVO);
    public void update(Article_commentVO article_commentVO);
    public void delete(Integer com_id);
    public Article_commentVO findByPrimaryKey(Integer com_id);
    public List<Article_commentVO> getAll(Integer article_id);
}
