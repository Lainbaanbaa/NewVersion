package com.group_buy_item_picture.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.group_buy_item_picture.model.groupBuyItemPictureService;
import com.group_buy_item_picture.model.groupBuyItemPictureVO;

@WebServlet("/groupBuyItemPicture/groupBuyItemPictureGetAll.do")
public class GroupBuyItemPictureGetAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GroupBuyItemPictureGetAllServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		Integer gbitem_id = Integer.valueOf(req.getParameter("gbitem_id"));
		
		groupBuyItemPictureService gbipSvc = new groupBuyItemPictureService();
		List<groupBuyItemPictureVO> list = gbipSvc.getAllGroupBuyItemPictureByGbitemID(gbitem_id);
		List<Integer> resul  = new ArrayList<Integer>();
		for(groupBuyItemPictureVO groupBuyItemPictureVO: list) {
			resul.add(groupBuyItemPictureVO.getGbip_id());
		}
		Writer out = res.getWriter();
		Gson gson = new Gson();
		String json = gson.toJson(resul);
		out.write(json);
//		System.out.println(json);
	}
}
