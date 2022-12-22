package com.itemType.model;

import java.util.List;

import com.item.model.ItemVO;
import org.json.JSONArray;
import org.json.JSONObject;

public class ItemTypeDAO implements ItemTypeDAOInterface {

	// 好像沒用到
	@Override
	public void insert(ItemTypeVO itemTypeVO) {
		getSession().persist(itemTypeVO);
	}

	// 好像沒用到
	@Override
	public void update(ItemTypeVO itemTypeVO) {
		getSession().merge(itemTypeVO);
	}

	// 好像沒用到
	@Override
	public void delete(Integer itemtId) {
		ItemTypeVO itemTypeVO = new ItemTypeVO();
		itemTypeVO.setItemtId(itemtId);
		getSession().remove(itemTypeVO);
	}

	@Override
	public ItemTypeVO findByPrimaryKey(Integer itemtId) {
		return getSession().get(ItemTypeVO.class, itemtId);
	}


	@Override
	public List<ItemTypeVO> getAll() {
		List<ItemTypeVO> list = getSession().createQuery("From ItemTypeVO", ItemTypeVO.class).list();
		return list;
	}

	@Override
	public JSONArray getAllJS() {
		List<ItemTypeVO> list = getSession().createQuery("From ItemTypeVO order by itemtId", ItemTypeVO.class).list();
		JSONArray jsonArray=new JSONArray();
		for(ItemTypeVO itemTypeVO:list){
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("itemtId",itemTypeVO.getItemtId());
			jsonObject.put("itemtName",itemTypeVO.getItemtName());
			jsonArray.put(jsonObject);
		}
		return jsonArray;
	}

}
