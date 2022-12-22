package com.group_buy_item_picture.model;

import java.util.List;

import com.group_buy_item.model.Group_Buy_ItemService;
import com.group_buy_item.model.Group_Buy_ItemVO;

public class groupBuyItemPictureService {

	private groupBuyItemPictureDAO_interface dao;

	public groupBuyItemPictureService() {
		dao = new groupBuyItemPictureJDBCDAO();
	}

	public groupBuyItemPictureVO addGroupBuyItemPicture(Integer gbitem_id, byte[] gbip_content) {

		groupBuyItemPictureVO groupBuyItemPictureVO = new groupBuyItemPictureVO();

		groupBuyItemPictureVO.setGbitem_id(gbitem_id);
		groupBuyItemPictureVO.setGbip_content(gbip_content);

		dao.insert(groupBuyItemPictureVO);

		return groupBuyItemPictureVO;

	}

	public groupBuyItemPictureVO updateGroupBuyItemPicture(Integer gbip_id, Integer gbitem_id, byte[] gbip_content) {

		groupBuyItemPictureVO groupBuyItemPictureVO = new groupBuyItemPictureVO();

		groupBuyItemPictureVO.setGbip_id(gbip_id);
		groupBuyItemPictureVO.setGbitem_id(gbitem_id);
		groupBuyItemPictureVO.setGbip_content(gbip_content);

		dao.insert(groupBuyItemPictureVO);

		return groupBuyItemPictureVO;

	}

	public void deleteGroupBuyItemPicture(Integer gbip_id) {
		dao.delete(gbip_id);
	}

	public groupBuyItemPictureVO getOneGroupBuyItemPicture(Integer gbip_id) {
		return dao.findByPrimaryKey(gbip_id);
	}

	public List<groupBuyItemPictureVO> getAll() {
		return dao.getAll();
	}

	// 使用 GBITEM_ID 取得所有 GBITEM_ID 的照片
	public List<groupBuyItemPictureVO> getAllGroupBuyItemPictureByGbitemID(Integer gbitem_id){
		return dao.findByGbitemID(gbitem_id);
	}
		
	// 使用 GBITEM_ID 取得封面(第一張) GBITEM_ID 的照片
	public groupBuyItemPictureVO getMainGroupBuyItemPictureByGbitemID(Integer gbitem_id) {
		return dao.findFirstPICByGbitemID(gbitem_id);
	}
	// 使用 GBITEM_ID 取得封面(第二張) GBITEM_ID 的照片
	public groupBuyItemPictureVO getSecondGroupBuyItemPictureByGbitemID(Integer gbitem_id) {
		return dao.findFirstPICByGbitemID(gbitem_id);
	}

}
