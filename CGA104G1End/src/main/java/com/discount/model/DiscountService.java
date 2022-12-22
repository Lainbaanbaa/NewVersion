package com.discount.model;

import java.util.List;

public class DiscountService {

	private DiscountDAO_interface dao;
	
	public DiscountService() {
		dao = new DiscountJDBCDAO();
	}
	
	public DiscountVO addDiscount(Integer gbitem_id, Integer discount_minamount,
			Integer discount_maxamount, Integer discount_price, String discount_nar) {
		
		DiscountVO DiscountVO = new DiscountVO();
		DiscountVO.setGbitem_id(gbitem_id);
		DiscountVO.setDiscount_minamount(discount_minamount);
		DiscountVO.setDiscount_maxamount(discount_maxamount);
		DiscountVO.setDiscount_price(discount_price);
		DiscountVO.setDiscount_nar(discount_nar);
		dao.insert(DiscountVO);

		return DiscountVO;
		
	}
	
	public DiscountVO updateDiscount(Integer discount_id, Integer gbitem_id, Integer discount_minamount,
			Integer discount_maxamount, Integer discount_price, String discount_nar) {
		
		DiscountVO DiscountVO = new DiscountVO();
		DiscountVO.setDiscount_id(discount_id);
		DiscountVO.setGbitem_id(gbitem_id);
		DiscountVO.setDiscount_minamount(discount_minamount);
		DiscountVO.setDiscount_maxamount(discount_maxamount);
		DiscountVO.setDiscount_price(discount_price);
		DiscountVO.setDiscount_nar(discount_nar);
		dao.update(DiscountVO);

		return DiscountVO;
	}
	
	public void deleteDiscount(Integer discount_id) {
		dao.delete(discount_id);
	}
	
	public DiscountVO getoneDiscount(Integer discount_id) {
		return dao.findByPrimaryKey(discount_id);
	}
	
	public DiscountVO CheckByGBitemID(Integer gbitem_id) {
		return dao.CheckByGBitemID(gbitem_id);
	}
	
	public List<DiscountVO> getoneGbitem_id(Integer gbitem_id) {
		return dao.findDiscountByPrimaryKey(gbitem_id);
	}
	public List<DiscountVO> getAll(){
		return dao.getAll();
	}
	
	
}
