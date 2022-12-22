package com.group_buy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.group_buy.model.Group_BuyService;
import com.group_buy.model.Group_BuyVO;
import com.group_buy_item.model.Group_Buy_ItemService;
import com.group_buy_item.model.Group_Buy_ItemVO;
import com.itemType.model.ItemTypeDAO;
import com.itemType.model.ItemTypeService;
import com.itemType.model.ItemTypeVO;


@WebServlet("/GroupBuyGetAllJSONServlet")
public class GroupBuyGetAllJSONServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GroupBuyGetAllJSONServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		Writer out = res.getWriter();
		/*************************** 2.開始查詢資料 *****************************************/
		// 取得所有團購團
		Group_BuyService group_BuyService = new Group_BuyService();
//		List<Group_BuyVO> list = group_BuyService.getAll();
		List<Group_BuyVO> list = group_BuyService.joinGBIGetAll();
		JSONArray jsonObject1 = new JSONArray(list);
		
		
		// 取得所有團購商品
//		Group_Buy_ItemService group_Buy_ItemService = new Group_Buy_ItemService();
//		List<Group_Buy_ItemVO> list2 = group_Buy_ItemService.getAll();
//		JSONArray jsonObject2 = new JSONArray(list2);
		// 取得所有團購類別
//		ItemTypeService itemTypeService = new ItemTypeService();
//		List<ItemTypeVO> list3 = itemTypeService.getAll();
//		JSONArray jsonObject3 = new JSONArray(list3);
		// 創建JSONObject物件 裝入上述物件
				JSONObject groupBuyAll = new JSONObject();
				groupBuyAll.put("GroupBuyVOs", jsonObject1);
//				groupBuyAll.put("GroupBuyItemVOs", jsonObject2);
//				groupBuyAll.put("ItemTypeVOs", jsonObject3);
				out.write(groupBuyAll.toString());
		
	}

}
