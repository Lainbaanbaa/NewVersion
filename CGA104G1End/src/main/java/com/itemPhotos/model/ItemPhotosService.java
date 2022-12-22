package com.itemPhotos.model;

import com.item.model.ItemVO;
import com.util.ServiceCommon;
import org.json.JSONArray;

import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;

public class ItemPhotosService implements ServiceCommon {
	private ItemPhotosInterface dao;

	public ItemPhotosService() {
		dao = new ItemPhotosDAO();
	}

	public byte[] getPhoto(Integer itemId) {

		try {
			beginTranscation();
			List<ItemPhotosVO> list = dao.getPhoto(itemId);
			commit();
			return list == null || list.size() == 0 ? null : list.get(0).getIpPhoto();


		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			return null;
		}



	}

	//測試
	public List<ItemPhotosVO> getPhotos(Integer itemId) {

		try {
			beginTranscation();
			List<ItemPhotosVO> list = dao.getPhoto(itemId);
			commit();

			return list == null || list.size() == 0 ? null : list;

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			return null;
		}


	}




	public JSONArray getAllPhoto(Integer itemId){

		try {
			beginTranscation();
			JSONArray jsonArray = dao.getAllPhoto(itemId);

			commit();
			return jsonArray;


		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			return null;
		}



	}

	public String getPhotoJson(Integer itemId) {
		try {
			beginTranscation();
			List<ItemPhotosVO> list = dao.getPhoto(itemId);
			Encoder encoder=Base64.getEncoder();
			commit();
			return list == null || list.size() == 0 ? null : encoder.encodeToString(list.get(0).getIpPhoto());


		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			return null;
		}



	}
	public boolean deletePhoto(Integer ipId) {

		try {
			beginTranscation();
			Integer number=	dao.deletePhoto(ipId);
			commit();
			return number>0;

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			return false;
		}


	}
	public void insert(ItemPhotosVO itemPhotosVO){
			try{
				beginTranscation();
				dao.insert(itemPhotosVO);
				commit();
			}catch (Exception e){
				e.printStackTrace();
				rollback();

			}
	}



}
