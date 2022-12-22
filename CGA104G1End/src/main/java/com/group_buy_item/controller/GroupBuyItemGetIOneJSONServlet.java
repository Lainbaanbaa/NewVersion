package com.group_buy_item.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.group_buy_item.model.Group_Buy_ItemService;
import com.group_buy_item.model.Group_Buy_ItemVO;
import com.news.controller.newsServlet;

@WebServlet("/GroupBuyItemGetIOneJSONServlet")
public class GroupBuyItemGetIOneJSONServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GroupBuyItemGetIOneJSONServlet() {
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");

		Integer gbitem_id = Integer.valueOf(req.getParameter("gbitem_id"));
//		System.out.print(gbitem_id);
		/*************************** 2.開始查詢資料 *****************************************/
		Group_Buy_ItemService group_Buy_ItemService = new Group_Buy_ItemService();
		Group_Buy_ItemVO group_Buy_ItemVO = group_Buy_ItemService.getOneGbi(gbitem_id);
		
		Writer out = res.getWriter();
		Gson gson = new Gson();
		String json = gson.toJson(group_Buy_ItemVO);
		out.write(json);
//		System.out.print(json);
	}

}
