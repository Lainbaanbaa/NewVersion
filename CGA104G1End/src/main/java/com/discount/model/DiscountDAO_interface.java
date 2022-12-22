package com.discount.model;

import java.util.*;

public interface DiscountDAO_interface {

	public void insert(DiscountVO DiscountVO);

	public void update(DiscountVO DiscountVO);

	public void delete(Integer discount_id);

	public DiscountVO findByPrimaryKey(Integer discount_id);
	
	public List<DiscountVO> findDiscountByPrimaryKey(Integer gbitem_id);

	public List<DiscountVO> getAll();

	public DiscountVO CheckByGBitemID(Integer gbitem_id);

}
