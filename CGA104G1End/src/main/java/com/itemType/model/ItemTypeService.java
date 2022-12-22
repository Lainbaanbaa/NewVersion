package com.itemType.model;

import com.itemType.model.ItemTypeVO;
import com.util.ServiceCommon;
import org.json.JSONArray;

import java.util.List;



public class ItemTypeService implements ServiceCommon {
	private ItemTypeDAOInterface itemTypeDao;

	public ItemTypeService() {
		itemTypeDao = new ItemTypeDAO();
	}

	public List<ItemTypeVO> getAll() {
		try {
			beginTranscation();
			List<ItemTypeVO> list = itemTypeDao.getAll();
			commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			return null;
		}
	}
	public JSONArray getAllJS(){
		try {
			beginTranscation();
			JSONArray list = itemTypeDao.getAllJS();
			commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			return null;
		}
	}
}
