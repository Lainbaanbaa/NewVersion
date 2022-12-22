package com.group_buy.controller;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.group_buy.model.Group_BuyService;
import com.group_buy.model.Group_BuyVO;
import com.group_buy_item.model.Group_Buy_ItemService;
import com.group_buy_item.model.Group_Buy_ItemVO;

@WebServlet("/GroupBuyIGetIOneJSONServlet")
public class GroupBuyIGetIOneJSONServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GroupBuyIGetIOneJSONServlet() {
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");

		Integer gb_id = Integer.valueOf(req.getParameter("gb_id"));
		/*************************** 2.開始查詢資料 *****************************************/
		Group_BuyService group_BuyService = new Group_BuyService();
		Group_BuyVO group_BuyVO = group_BuyService.getOneGroup_Buy(gb_id);
		
		Writer out = res.getWriter();
		Gson gson = new Gson();
		String json = gson.toJson(group_BuyVO);
		out.write(json);
//		System.out.println(json);
	
	}

}
