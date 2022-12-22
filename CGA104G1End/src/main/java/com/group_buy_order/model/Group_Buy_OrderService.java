package com.group_buy_order.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class Group_Buy_OrderService {

	private Group_Buy_OrderDAO_interface dao;

	public Group_Buy_OrderService() {
		dao = new Group_Buy_OrderDAO();
	}

	public Group_Buy_OrderVO addGroup_Buy_Order_User(Integer gbitem_id, Integer gb_id,Integer gbitem_amount, Integer gboriginal_price,
			Integer gb_endprice, Integer gborder_paying, Integer gborder_send, Integer gborder_status,
			String gborder_other, String receiver_name, String receiver_address, String receiver_phone,
			Timestamp gborder_date, Integer gborder_id) {

		Group_Buy_OrderVO gboVO = new Group_Buy_OrderVO();

		gboVO.setGbitem_id(gbitem_id);
		gboVO.setGb_id(gb_id);
		gboVO.setGbitem_amount(gbitem_amount);
		gboVO.setGboriginal_price(gboriginal_price);
		gboVO.setGb_endprice(gb_endprice);
		gboVO.setGborder_paying(gborder_paying);
		gboVO.setGborder_send(gborder_send);
		gboVO.setGborder_status(gborder_status);
		gboVO.setGborder_other(gborder_other);
		gboVO.setReceiver_name(receiver_name);
		gboVO.setReceiver_address(receiver_address);
		gboVO.setReceiver_phone(receiver_phone);

		gboVO.setGborder_date(gborder_date);
		gboVO.setGborder_id(gborder_id);

		return dao.insert_first(gboVO);
	}

	public Group_Buy_OrderVO addGroup_Buy_Order(Integer gbitem_id, Integer gb_id, Integer gbitem_amount,
			Integer gboriginal_price, Integer gb_endprice, Timestamp gborder_date, Integer gborder_paying,
			Integer gborder_send, Integer gborder_status, String gborder_other, String tracking_num,
			String receiver_name, String receiver_address, String receiver_phone, Timestamp pickup_time) {

		Group_Buy_OrderVO gboVO = new Group_Buy_OrderVO();

		gboVO.setGbitem_id(gbitem_id);
		gboVO.setGb_id(gb_id);
		gboVO.setGbitem_amount(gbitem_amount);
		gboVO.setGboriginal_price(gboriginal_price);
		gboVO.setGb_endprice(gb_endprice);
		gboVO.setGborder_date(gborder_date);
		gboVO.setGborder_paying(gborder_paying);
		gboVO.setGborder_send(gborder_send);
		gboVO.setGborder_status(gborder_status);
		gboVO.setGborder_other(gborder_other);
		gboVO.setTracking_num(tracking_num);
		gboVO.setReceiver_name(receiver_name);
		gboVO.setReceiver_address(receiver_address);
		gboVO.setReceiver_phone(receiver_phone);
		gboVO.setPickup_time(pickup_time);
		dao.insert(gboVO);

		return gboVO;
	}

	public Group_Buy_OrderVO updateGroup_Buy_Order(Integer gborder_id, Integer gbitem_id, Integer gb_id,
			Integer gbitem_amount, Integer gboriginal_price, Integer gb_endprice, Integer gborder_paying,
			Integer gborder_send, Integer gborder_status, String gborder_other, String tracking_num,
			String receiver_name, String receiver_address, String receiver_phone, Timestamp gborder_date,
			Timestamp pickup_time) {

		Group_Buy_OrderVO gboVO = new Group_Buy_OrderVO();

		gboVO.setGborder_id(gborder_id);
		gboVO.setGbitem_id(gbitem_id);
		gboVO.setGb_id(gb_id);
		gboVO.setGbitem_amount(gbitem_amount);
		gboVO.setGboriginal_price(gboriginal_price);
		gboVO.setGb_endprice(gb_endprice);
		gboVO.setGborder_date(gborder_date);
		gboVO.setGborder_paying(gborder_paying);
		gboVO.setGborder_send(gborder_send);
		gboVO.setGborder_status(gborder_status);
		gboVO.setGborder_other(gborder_other);
		gboVO.setTracking_num(tracking_num);
		gboVO.setReceiver_name(receiver_name);
		gboVO.setReceiver_address(receiver_address);
		gboVO.setReceiver_phone(receiver_phone);
		gboVO.setPickup_time(pickup_time);
		return dao.updateone(gboVO);

	}

	public Group_Buy_OrderVO update_pt(Integer gborder_id) {

		Group_Buy_OrderVO gboVO = new Group_Buy_OrderVO();

		gboVO.setGborder_id(gborder_id);
		dao.update_pt(gboVO);

		return gboVO;

	}

	public Group_Buy_OrderVO update_paying(Integer gborder_id) {

		Group_Buy_OrderVO gboVO = new Group_Buy_OrderVO();

		gboVO.setGborder_id(gborder_id);
		dao.update_paying(gboVO);

		return gboVO;
	}

	public void deleteEmp(Integer gborder_id) {
		dao.delete(gborder_id);
	}

	public Group_Buy_OrderVO getOneEmp(Integer gborder_id) {
		return dao.findByPrimaryKey(gborder_id);
	}

	public List<Group_Buy_OrderVO> getAll() {
		return dao.getAll();
	}

	public List<Group_Buy_OrderVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}

}