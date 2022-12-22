package com.group_buy_item.model;

import java.sql.Date;
import java.util.List;


public class Group_Buy_ItemService {

	private Group_Buy_ItemDAO_interface dao;
	
	public Group_Buy_ItemService() {
		dao = new Group_Buy_ItemJDBCDAO();
	}
	
	public Group_Buy_ItemVO addGroup_Buy_ItemVO(String gbitem_name, String gbitem_content, 
			Integer gbitem_price, Integer gbitem_status, Date gbitem_startdate, Date gbitem_enddate, Integer gbitem_type) {
		
		Group_Buy_ItemVO gbiVO = new Group_Buy_ItemVO();
		
		gbiVO.setGbitem_name(gbitem_name);
		gbiVO.setGbitem_content(gbitem_content);
		gbiVO.setGbitem_price(gbitem_price);
		gbiVO.setGbitem_status(gbitem_status);
		gbiVO.setGbitem_startdate(gbitem_startdate);
		gbiVO.setGbitem_enddate(gbitem_enddate);
		gbiVO.setGbitem_type(gbitem_type);
		dao.insert(gbiVO);
		
		return gbiVO;
	}
	
	public Group_Buy_ItemVO updateGroup_Buy_ItemVO(Integer gbitem_id, String gbitem_name, String gbitem_content, 
			Integer gbitem_price, Integer gbitem_status, Date gbitem_startdate, Date gbitem_enddate, Integer gbitem_type) {
		
		Group_Buy_ItemVO gbiVO = new Group_Buy_ItemVO();
		
		gbiVO.setGbitem_id(gbitem_id);
		gbiVO.setGbitem_name(gbitem_name);
		gbiVO.setGbitem_content(gbitem_content);
		gbiVO.setGbitem_price(gbitem_price);
		gbiVO.setGbitem_status(gbitem_status);
		gbiVO.setGbitem_startdate(gbitem_startdate);
		gbiVO.setGbitem_enddate(gbitem_enddate);
		gbiVO.setGbitem_type(gbitem_type);
		dao.update(gbiVO);
		
		return gbiVO;
	}
	
	public void deleteGbi(Integer gbitem_id) {
		dao.delete(gbitem_id);
	}

	public Group_Buy_ItemVO getOneGbi(Integer gbitem_id) {
		return dao.findByPrimaryKey(gbitem_id);
	}

	public List<Group_Buy_ItemVO> getAll() {
		return dao.getAll();
	}
}
