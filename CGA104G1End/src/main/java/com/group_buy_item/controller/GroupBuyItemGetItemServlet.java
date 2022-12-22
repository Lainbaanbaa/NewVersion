package com.group_buy_item.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GroupBuyItemGetItemServlet")
public class GroupBuyItemGetItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GroupBuyItemGetItemServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 取得所有團購商品類別
	
	
	
	}

}
