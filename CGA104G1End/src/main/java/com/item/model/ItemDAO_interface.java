package com.item.model;

import java.util.List;

import org.json.JSONArray;

import com.util.ItemCommon;
import org.json.JSONObject;


public interface ItemDAO_interface extends ItemCommon{
	 public Integer insert(ItemVO itemVO);

     public void update(ItemVO itemVO);

     public void updateJS(ItemVO itemVO);
     public void delete(Integer itemId);
     public ItemVO findByPrimaryKey(Integer itemId);
     public List<ItemVO> getAll();
     public JSONArray getAllJS(int pageCount);



     JSONArray search(String keyWords, Integer type);

     public JSONArray frontEndSearch(String keyWords, Integer type);
     //listAllItems.html
     public JSONArray getAllList();
     public JSONObject getCount();


    public void insertFavList(String item, String memId);

     public String getFavList(String memId);

}
