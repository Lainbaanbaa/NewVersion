package com.article_identity.model;

public interface Article_identityDAO_interface {
	public void insert(Article_identityVO article_identityVO);
    public Article_identityVO findByPrimaryKey(Integer mem_id);

}
