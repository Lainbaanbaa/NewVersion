package com.group_join.model;

import java.util.*;


public interface Group_JoinDAO_interface {

	public void insert(Group_JoinVO Group_JoinVO);

	public void update(Group_JoinVO Group_JoinVO);
	
	public void updatePay(Group_JoinVO Group_JoinVO);
	
	public void updatePickup(Group_JoinVO Group_JoinVO);
	
	public void updateDeliver(Group_JoinVO Group_JoinVO);

	public void deleteGj(Integer gb_id, Integer mem_id);
	
	public Group_JoinVO findByPrimaryKey(Integer gb_id,Integer mem_id);
	
	public List<Group_JoinVO> findBygbid(Integer gb_id);
	
	public List<Group_JoinVO> findByMem(Integer mem_id);
	
	public List<Group_JoinVO> getAll();
	
	public List<Group_JoinVO> getAll(Map<String, String[]> map);
}
