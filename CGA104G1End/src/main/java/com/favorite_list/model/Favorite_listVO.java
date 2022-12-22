package com.favorite_list.model;
import java.sql.Timestamp;

public class Favorite_listVO implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private Integer mem_id;
	private Integer item_id;
	private Timestamp fav_time;
	
	public Integer getMem_id() {
		return mem_id;
	}
	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}
	public Integer getItem_id() {
		return item_id;
	}
	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}
	public Timestamp getFav_time() {
		return fav_time;
	}
	public void setFav_time(Timestamp fav_time) {
		this.fav_time = fav_time;
	}
	

}