package com.itemPhotos.model;


import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;

import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.util.HibernateUtil;

public class ItemPhotosDAO implements ItemPhotosInterface {

	@Override
	public void insert(ItemPhotosVO itemPhotosVO) {
		getSession().persist(itemPhotosVO);
	}

	@Override
	public void update(ItemPhotosVO itemPhotosVO) {
		getSession().merge(itemPhotosVO);

	}

	@Override
	public void delete(Integer itemId) {
		ItemPhotosVO itemPhotosVO = new ItemPhotosVO();
		itemPhotosVO.setItemId(itemId);
		getSession().remove(itemPhotosVO);
	}

	@Override
	public ItemPhotosVO findByPrimaryKey(Integer ipId) {

		return getSession().get(ItemPhotosVO.class, ipId);
	}

	@Override
	public JSONArray getAllPhoto(Integer itemId) {
		JSONArray jsonArray = new JSONArray();
		List<ItemPhotosVO> list = getSession().createQuery("FROM ItemPhotosVO  where itemId= :id", ItemPhotosVO.class).setParameter("id",itemId).list();
		 Encoder encoder= Base64.getEncoder() ;
		for (ItemPhotosVO itemPhotosVO : list) {
			byte[] photo=itemPhotosVO.getIpPhoto();
			String photo64 = encoder.encodeToString(photo);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("ipId", itemPhotosVO.getIpId());
			jsonObject.put("itemId", itemPhotosVO.getItemId());
			jsonObject.put("photo", photo64);
			jsonArray.put(jsonObject);
		}
		return jsonArray;
	}

	@Override
	public List<ItemPhotosVO> getPhoto(Integer itemId) {
		return getSession().createQuery("From ItemPhotosVO where itemId in (:itemId)", ItemPhotosVO.class).setParameter("itemId", itemId).list();
	}


	@Override
	public int deletePhoto(Integer ipId) {
		try {
			ItemPhotosVO itemPhotosVO=new ItemPhotosVO();
			itemPhotosVO.setIpId(ipId);

			getSession().remove(itemPhotosVO);

			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	
		
	}



	
//	public static void main(String[] args) {
//	Session session = HibernateUtil.getSessionFactory().openSession();
//	List<ItemPhotosVO> list = session.createQuery("From ItemPhotosVO where itemId in (:itemId)", ItemPhotosVO.class).setParameter("itemId", 1).list();
//	for(ItemPhotosVO vo : list ) {
//		System.out.println(vo.getIpPhoto());
//	}
//	session.close();
//	}
	
	
	
}
