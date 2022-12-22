package com.article_sorttype.model;

import java.util.List;

public interface Article_sorttypeDAO_interface {
	public void insert(Article_sorttypeVO article_sorttypeVO);
    public void update(Article_sorttypeVO article_sorttypeVO);
    public void delete(Integer sort_id);
    public Article_sorttypeVO findByPrimaryKey(Integer sort_id);
    public List<Article_sorttypeVO> getAll();

}
