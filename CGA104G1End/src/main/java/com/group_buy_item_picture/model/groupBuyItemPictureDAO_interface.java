package com.group_buy_item_picture.model;

import java.util.List;


public interface groupBuyItemPictureDAO_interface {
	public void insert(groupBuyItemPictureVO groupBuyItemPictureVO);

	public void update(groupBuyItemPictureVO groupBuyItemPictureVO);

	public void delete(Integer gbip_id);

	public groupBuyItemPictureVO findByPrimaryKey(Integer gbip_id);

	public List<groupBuyItemPictureVO> getAll();
	// 使用 GBITEM_ID 取得所有 GBITEM_ID 的照片
	public List<groupBuyItemPictureVO> findByGbitemID(Integer gbitem_id);
	// 使用 GBITEM_ID 取得封面(第一張) GBITEM_ID 的照片
	public groupBuyItemPictureVO findFirstPICByGbitemID(Integer gbitem_id);
}
