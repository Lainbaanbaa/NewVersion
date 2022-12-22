package com.artLikeHate.model;

public interface ArtLikeHateInterface {
	public void insertUpdate(ArtLikeHateVO artLikeHateVO);

	public Integer countLike(Integer article_id);

	public Integer countHate(Integer article_id);

}
