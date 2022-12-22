package com.itemType.model;

import java.util.List;

import com.util.ItemCommon;
import org.json.JSONArray;


public interface ItemTypeDAOInterface extends ItemCommon{
	public void insert(ItemTypeVO itemTypeVO);
	public void update(ItemTypeVO itemTypeVO);
	public void delete(Integer itemtId);
	public ItemTypeVO findByPrimaryKey(Integer itemtId);
	public List<ItemTypeVO> getAll();

	public JSONArray getAllJS();
}


