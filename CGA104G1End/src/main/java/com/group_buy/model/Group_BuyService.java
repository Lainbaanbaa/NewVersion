package com.group_buy.model;

import java.sql.Timestamp;
import java.util.List;

public class Group_BuyService {
	
	private Group_BuyDAO_interface dao;
	
	public Group_BuyService() {
		dao = new Group_BuyJDBCDAO();
	}
	
	public Group_BuyVO addGroup_Buy(Integer mem_id, Integer gbitem_id, Integer gb_min,
			Integer gb_amount, Timestamp gbstart_date, Timestamp gbend_date, Integer gb_status,Integer gb_price,String gb_name) {
		
		Group_BuyVO gbVO = new Group_BuyVO();
		gbVO.setMem_id(mem_id);
		gbVO.setGbitem_id(gbitem_id);
		gbVO.setGb_min(gb_min);
		gbVO.setGb_amount(gb_amount);
		gbVO.setGbstart_date(gbstart_date);
		gbVO.setGbend_date(gbend_date);
		gbVO.setGb_status(gb_status);
		gbVO.setGb_price(gb_price);
		gbVO.setGb_name(gb_name);
		dao.insert(gbVO);
		
		return gbVO;
	}
	
	public Group_BuyVO updateGroup_Buy(Integer gb_id, Integer mem_id, Integer gbitem_id, Integer gb_min,
			Integer gb_amount, Timestamp gbstart_date, Timestamp gbend_date, Integer gb_status,Integer gb_price,String gb_name ) {
		
		Group_BuyVO gbVO = new Group_BuyVO();
		gbVO.setGb_id(gb_id);
		gbVO.setMem_id(mem_id);
		gbVO.setGbitem_id(gbitem_id);
		gbVO.setGb_min(gb_min);
		gbVO.setGb_amount(gb_amount);
		gbVO.setGbstart_date(gbstart_date);
		gbVO.setGbend_date(gbend_date);
		gbVO.setGb_status(gb_status);
		gbVO.setGb_price(gb_price);
		gbVO.setGb_name(gb_name);
		dao.update(gbVO);
		
		return gbVO;
	}
	
	public void deleteGroup_Buy(Integer gb_id) {
		dao.delete(gb_id);
	}

	public Group_BuyVO getOneGroup_Buy(Integer gb_id) {
		return dao.findByPrimaryKey(gb_id);
	}
	public Group_BuyVO CheckByMemID(Integer mem_id) {
		return dao.CheckByMemID(mem_id);
	}

	public List<Group_BuyVO> getAll() {
		return dao.getAll();
	}
	public List<Group_BuyVO> joinGBIGetAll() {
		return dao.joinGBIGetAll();
	}
	
	public List<Group_BuyVO> joinGBIGetAllWhereMemID(Integer mem_id) {
		return dao.joinGBIGetAllWhereMemID(mem_id);
	}
	//查詢團購狀態等於0的(團購尚未開始)，而且團購開始時間大於現在時間(為了要更改狀態為團購進行中)
	public List<Group_BuyVO> getAll2InProgress(){
		return dao.getAll2InProgress();
	}
	
	//查詢團購狀態等於1的(團購進行中)，而且現在時間>=團購結束時間(為了要更改狀態為團購關閉OR結束)
	public List<Group_BuyVO> getAll2End(){
		return dao.getAll2End();
	}
	
	
	// 用 mem_id 取得所有資料
	public List<Group_BuyVO> getAllGroupBuyApplyListByMemID(Integer mem_id) {
		return dao.findByMemID(mem_id);
	}
	
	public Group_BuyVO updateGroup_Buy_GBPrice(Integer gb_id,Integer gb_price ) {
		Group_BuyVO gbVO = new Group_BuyVO();
		gbVO.setGb_id(gb_id);
		gbVO.setGb_price(gb_price);
		dao.updateGbprice(gbVO);
		
		return gbVO;
	}
	
	public Group_BuyVO updateGroup_Buy_GBAmount(Integer gb_id,Integer gb_amount ) {
		Group_BuyVO gbVO = new Group_BuyVO();
		gbVO.setGb_id(gb_id);
		gbVO.setGb_amount(gb_amount);
		dao.updateGbAmount(gbVO);
		
		return gbVO;
	}
	
	public Group_BuyVO updateGroup_Buy_GBStatus(Integer gb_id,Integer gb_status ) {
		Group_BuyVO gbVO = new Group_BuyVO();
		gbVO.setGb_id(gb_id);
		gbVO.setGb_status(gb_status);
		dao.updateGbStatus(gbVO);
		
		return gbVO;
	}
	
	public Group_BuyVO getLastGroup_Buy() {
		return dao.findLast();
	}
	
	
}
