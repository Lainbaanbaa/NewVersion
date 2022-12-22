package com.group_buy_order.model;

import java.util.*;



public interface Group_Buy_OrderDAO_interface {

	public Group_Buy_OrderVO insert_first(Group_Buy_OrderVO Group_Buy_OrderVO);
	
	public void insert(Group_Buy_OrderVO Group_Buy_OrderVO);

//	public void update(Group_Buy_OrderVO Group_Buy_OrderVO);
	
	public void update_pt(Group_Buy_OrderVO Group_Buy_OrderVO);
	
	public void update_paying(Group_Buy_OrderVO Group_Buy_OrderVO);
	
	public Group_Buy_OrderVO updateone(Group_Buy_OrderVO Group_Buy_OrderVO);

	public void delete(Integer gborder_id);

	public Group_Buy_OrderVO findByPrimaryKey(Integer gborder_id);

	public List<Group_Buy_OrderVO> getAll();
	
	public List<Group_Buy_OrderVO> getAll(Map<String, String[]> map);

}
