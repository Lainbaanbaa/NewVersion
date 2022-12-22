package com.favorite_list.model;


import java.sql.Timestamp;
import java.util.List;

public class Favorite_listService {

	private Favorite_listDAO_interface dao;
	
	
//	想連接的目標
	public Favorite_listService() {
		dao = new Favorite_listJDBCDAO();
	}
	
	
	public Favorite_listVO addFavorite_list(Integer mem_id, Integer item_id, Timestamp fav_time) 
//	public Favorite_listVO addFavorite_list(Integer mem_id, Integer item_id)
	{
		
		Favorite_listVO favorite_listVO = new Favorite_listVO();
		
		
		favorite_listVO.setMem_id(mem_id);
		favorite_listVO.setItem_id(item_id);
//		favorite_listVO.setFav_time(fav_time);
		
		dao.insert(favorite_listVO);
		
		return favorite_listVO;
	}
	
	public Favorite_listVO updateFavorite_list(Integer mem_id, Integer item_id, Timestamp fav_time)
//	public Favorite_listVO updateFavorite_list(Integer mem_id, Integer item_id)
	{
		
		
		Favorite_listVO favorite_listVO = new Favorite_listVO();
		
		
		favorite_listVO.setMem_id(mem_id);
		favorite_listVO.setItem_id(item_id);
		favorite_listVO.setFav_time(fav_time);	
		dao.update(favorite_listVO);
		
		return favorite_listVO;
	}
	
	
	public void deleteFavorite_list(Integer mem_id) {
		dao.delete(mem_id);
	}
	
	public Favorite_listVO getOneFavorite_list(Integer mem_id) {
		return dao.findByPrimaryKey(mem_id);
	}
	
	public List<Favorite_listVO> getAll(){
		return dao.getAll();
	}
}

	
