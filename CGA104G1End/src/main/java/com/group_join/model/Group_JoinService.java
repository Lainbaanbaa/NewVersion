package com.group_join.model;

import java.util.List;
import java.util.Map;

import com.emp.model.EmpVO;


public class Group_JoinService {

	private Group_JoinDAO_interface dao;
	
	public Group_JoinService() {
		dao = new Group_JoinDAO();
	}
	
	
	public Group_JoinVO addGroup_Join(Integer gb_id, Integer mem_id, 
			Integer gbpay_status, Integer pickup_status, Integer deliver_status ,Integer gbbuy_amount, Integer gbbuy_price ) {
		
		Group_JoinVO gjVO = new Group_JoinVO();
		gjVO.setGb_id(gb_id);
		gjVO.setMem_id(mem_id);
		gjVO.setGbpay_status(gbpay_status);
		gjVO.setPickup_status(pickup_status);
		gjVO.setDeliver_status(deliver_status);
		gjVO.setGbbuy_amount(gbbuy_amount);
		gjVO.setGbbuy_price(gbbuy_price);
	
		
		dao.insert(gjVO);
		
		return gjVO;
	}
	
	public Group_JoinVO updateGroup_Join(Integer gb_id, Integer mem_id, 
			Integer gbpay_status, Integer pickup_status, Integer deliver_status , Integer gbbuy_amount ,Integer gbbuy_price ) {
		
		Group_JoinVO gjVO = new Group_JoinVO();
		gjVO.setGb_id(gb_id);
		gjVO.setMem_id(mem_id);
		gjVO.setGbpay_status(gbpay_status);
		gjVO.setPickup_status(pickup_status);
		gjVO.setDeliver_status(deliver_status);
		gjVO.setGbbuy_amount(gbbuy_amount);
		gjVO.setGbbuy_price(gbbuy_price);
		dao.update(gjVO);
		
		return gjVO;
	}
	
	public Group_JoinVO updatePay(Integer gb_id, Integer mem_id, Integer gbpay_status) {
		
		Group_JoinVO gjVO = new Group_JoinVO();
		gjVO.setGb_id(gb_id);
		gjVO.setMem_id(mem_id);
		gjVO.setGbpay_status(gbpay_status);
		dao.updatePay(gjVO);
		
		return gjVO;
	}
	public Group_JoinVO updatePickup(Integer gb_id, Integer mem_id, Integer pickup_status) {
		
		Group_JoinVO gjVO = new Group_JoinVO();
		gjVO.setGb_id(gb_id);
		gjVO.setMem_id(mem_id);
		gjVO.setPickup_status(pickup_status);
		dao.updatePickup(gjVO);
		
		return gjVO;
	}
	public Group_JoinVO updateDeliver(Integer gb_id, Integer mem_id, Integer deliver_status) {
		
		Group_JoinVO gjVO = new Group_JoinVO();
		gjVO.setGb_id(gb_id);
		gjVO.setMem_id(mem_id);
		gjVO.setDeliver_status(deliver_status);
		dao.updateDeliver(gjVO);
		
		return gjVO;
	}
	public void deleteGj(Integer gb_id , Integer mem_id) {
		dao.deleteGj(gb_id , mem_id);
	}

	public Group_JoinVO getOneEmp(Integer gb_id ,Integer mem_id) {
		return dao.findByPrimaryKey(gb_id,mem_id);
	}

	public List<Group_JoinVO> getOneGb(Integer gb_id) {
		return dao.findBygbid(gb_id);
	}
	
	public List<Group_JoinVO> getOneByMem(Integer mem_id) {
		return dao.findByMem(mem_id);
	}
	
	public List<Group_JoinVO> getAll() {
		return dao.getAll();
	}
	public List<Group_JoinVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	
}
