package com.group_buy_item.model;

import java.util.*;

public interface Group_Buy_ItemDAO_interface {
	
	public void insert(Group_Buy_ItemVO Group_Buy_ItemVO);

	public void update(Group_Buy_ItemVO Group_Buy_ItemVO);

	public void delete(Integer gbitem_id);

	public Group_Buy_ItemVO findByPrimaryKey(Integer gbitem_id);

	public List<Group_Buy_ItemVO> getAll();
}
