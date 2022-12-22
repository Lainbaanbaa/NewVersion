package com.group_buy.model;

import java.util.List;

public interface Group_BuyDAO_interface {

	public void insert(Group_BuyVO Group_BuyVO);

	public void update(Group_BuyVO Group_BuyVO);

	public void delete(Integer gb_id);

	public Group_BuyVO findByPrimaryKey(Integer gb_id);

	public List<Group_BuyVO> getAll();

	public List<Group_BuyVO> findByMemID(Integer mem_id);
	
	public void updateGbprice(Group_BuyVO Group_BuyVO);

	public Group_BuyVO findLast();

	public void updateGbAmount(Group_BuyVO Group_BuyVO);

	public void updateGbStatus(Group_BuyVO Group_BuyVO);

	public List<Group_BuyVO> joinGBIGetAll();

	public List<Group_BuyVO> getAll2InProgress();

	public List<Group_BuyVO> getAll2End();

	public List<Group_BuyVO> joinGBIGetAllWhereMemID(Integer mem_id);

	public Group_BuyVO CheckByMemID(Integer mem_id);

}
