package com.item.model;

import java.lang.reflect.Type;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;

import com.itemPhotos.model.ItemPhotosDAO;
import com.itemPhotos.model.ItemPhotosVO;
import com.util.ServiceCommon;
import com.itemPhotos.model.ItemPhotosInterface;
import org.json.JSONObject;

public class ItemService implements ServiceCommon {
	private ItemDAO_interface itemDao;
	private ItemPhotosInterface itemPhotoDao;

	public ItemService() {
		itemDao = new ItemDAO();
		itemPhotoDao = new ItemPhotosDAO();
	}

	public List<ItemVO> getAll() {
		try {
			beginTranscation();
			List<ItemVO> list = itemDao.getAll();
			commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			return null;
		}

	}

	public void expire(Integer itemId){
		try {
			beginTranscation();
			final String hql="Update ItemVO set itemStatus=0 where itemId= :itemId";
			getSession().createQuery(hql).setParameter("itemId",itemId).executeUpdate();
			commit();
		}catch (Exception e){
			e.printStackTrace();
			rollback();
		}
	}

	//可刪
	public ItemVO addItem(String itemName, String itemContent, Integer itemPrice, Integer itemAmount,

						  Date startDate, Date enddate, Integer itemStatus, Integer itemtId, List<byte[]> item_photo) {

		try {
			beginTranscation();
			ItemVO itemVO = new ItemVO();
			itemVO.setItemName(itemName);
			itemVO.setItemContent(itemContent);
			itemVO.setItemPrice(itemPrice);
			itemVO.setItemAmount(itemAmount);
			itemVO.setItemDate(startDate);
			itemVO.setItemEnddate(enddate);
			itemVO.setItemStatus(itemStatus);
			itemVO.setItemtId(itemtId);

			Integer item_id = itemDao.insert(itemVO);

			for (byte[] photo : item_photo) {

				ItemPhotosVO itemPhotosVO = new ItemPhotosVO();
				itemPhotosVO.setItemId(item_id);
				itemPhotosVO.setIpPhoto(photo);
				itemPhotoDao.insert(itemPhotosVO);
			}
			commit();
			return itemVO;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			return null;

		}

	}

	//可刪
	public void update(String itemName, String itemContent, Integer itemPrice, Integer itemAmount, Date startDate,
					   Date endDate, Integer itemStatus, Integer itemtType, List<byte[]> itemPhoto, Integer itemId) {
		try {
			beginTranscation();
			ItemVO itemVO = new ItemVO();
			itemVO.setItemName(itemName);
			itemVO.setItemContent(itemContent);
			itemVO.setItemPrice(itemPrice);
			itemVO.setItemAmount(itemAmount);
			itemVO.setItemDate(startDate);
			itemVO.setItemEnddate(endDate);
			itemVO.setItemStatus(itemStatus);
			itemVO.setItemtId(itemtType);
			itemVO.setItemId(itemId);

			itemDao.update(itemVO);

			if (itemPhoto.size() != 0) {
				for (byte[] photo : itemPhoto) {

					ItemPhotosVO itemPhotosVO = new ItemPhotosVO();
					itemPhotosVO.setItemId(itemId);
					itemPhotosVO.setIpPhoto(photo);
					itemPhotoDao.insert(itemPhotosVO);
				}
			}
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
	}


	public void updateJS(ItemVO itemVO){
		try {
			beginTranscation();
			itemDao.updateJS(itemVO);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();

		}
	}
	public Integer addItemJS(ItemVO itemVO){
		try {
			beginTranscation();
			Integer itemId=itemDao.insert(itemVO);
			commit();
			return itemId;

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			return -1;
		}
	}
	public ItemVO getItem(Integer itemId) {
		try {
			beginTranscation();
			ItemVO itemVO=itemDao.findByPrimaryKey(itemId);
			commit();
			return itemVO;

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			return null;
		}

	}

	public void deleteItem(Integer itemId) {
		try {
			beginTranscation();
			itemPhotoDao.delete(itemId);
			itemDao.delete(itemId);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}


	}

	//	shop1.html
	public JSONArray getAllJs(int pageNumber) {
		try {
			beginTranscation();
			JSONArray jsonArray=itemDao.getAllJS(pageNumber);
			commit();
			return jsonArray;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			return null;
		}



	}

	public void insertFavList(JsonObject obj, String memId){
		final JsonObject item = obj.get("item").getAsJsonObject();
		JsonArray jsonArray;
		String favListStr = itemDao.getFavList(memId);
		if(favListStr != null){

			Gson gson = new Gson();
			jsonArray = gson.fromJson(favListStr, JsonArray.class);

			Type listType = new TypeToken<List<ItemVO>>() {}.getType();
			ArrayList<ItemVO> list = new Gson().fromJson(jsonArray, listType);

			for(ItemVO itemVO:list){
				if(itemVO.getItemId() == item.get("itemId").getAsInt()) {
					return;
				}
			}
			jsonArray.add(item);
		} else {
			jsonArray = new JsonArray();
			jsonArray.add(item);
		}
		itemDao.insertFavList(jsonArray.toString(), memId);
	}

	public String getFavList(String memId){
		return  itemDao.getFavList(memId);

	}

	//listAllItems.html
	public JSONArray getAllList(){
		try {
			beginTranscation();
			JSONArray jsonArray=itemDao.getAllList();
			commit();
			return jsonArray;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			return null;
		}
	}

	public JSONObject getCount(){
		try {
			beginTranscation();
			JSONObject jsonObject=itemDao.getCount();
			commit();
			return jsonObject;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			return null;
		}
	}

	public JSONArray search(String keyWords, Integer typeId){
		try{
			beginTranscation();
			JSONArray jsonArray=itemDao.search(keyWords,typeId);
			commit();
			return jsonArray;
		}catch (Exception e){
			e.printStackTrace();
			rollback();
			return null;
		}
	}

	public JSONArray frontEndSearch(String keyWords, Integer typeId){
		try{
			beginTranscation();
			JSONArray jsonArray=itemDao.frontEndSearch(keyWords,typeId);
			commit();
			return jsonArray;
		}catch (Exception e){
			e.printStackTrace();
			rollback();
			return null;
		}
	}

	public String removeTraceList(String memId,String itemId){
		try {
			beginTranscation();
			String favListStr=itemDao.getFavList(memId);
			Gson gson = new Gson();
			JsonArray jsonArray = gson.fromJson(favListStr, JsonArray.class);
			Type listType = new TypeToken<List<ItemVO>>(){}.getType();
			ArrayList<ItemVO> list = new Gson().fromJson(jsonArray, listType);
			Integer id=Integer.valueOf(itemId);
			for(ItemVO itemVO:list){
				if(itemVO.getItemId() == id) {
					list.remove(itemVO);
					break;
				}
			}
			String resultJsonStr=gson.toJson(list);
			itemDao.insertFavList(resultJsonStr, memId);
			return resultJsonStr;
		}catch (Exception e){
			e.printStackTrace();
			rollback();
			return null;

		}
	}
}
