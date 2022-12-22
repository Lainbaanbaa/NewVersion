package com.group_buy_item.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group_buy_item.model.Group_Buy_ItemService;
import com.group_buy_item.model.Group_Buy_ItemVO;
import com.group_buy_item_picture.model.groupBuyItemPictureService;
import com.group_buy_item_picture.model.groupBuyItemPictureVO;

/**
 * Servlet implementation class GroupBuyItemGetIOneServlet
 */
@WebServlet("/GroupBuyItemGetIOneServlet")
public class GroupBuyItemGetIOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GroupBuyItemGetIOneServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		Integer gbitem_id = Integer.valueOf(req.getParameter("gbitem_id"));

		/*************************** 2.開始查詢資料 ****************************************/
		Group_Buy_ItemService group_Buy_ItemService = new Group_Buy_ItemService();
		Group_Buy_ItemVO group_Buy_ItemVO = group_Buy_ItemService.getOneGbi(gbitem_id);
		/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
		req.setAttribute("Group_Buy_ItemVO", group_Buy_ItemVO);
		
		// 從資料庫讀取 groupBuyItemPictureVO 存入 list 中
		
		groupBuyItemPictureService gbipSvc = new groupBuyItemPictureService();
		List<groupBuyItemPictureVO> list3 = gbipSvc.getAllGroupBuyItemPictureByGbitemID(gbitem_id);
		
		//以下兩種移除null的方式
//		list2.removeAll(Collections.singleton(null));
		
		
//		List<groupBuyItemPictureVO> listWithoutNulls = list2.stream()
//				.filter(Objects::nonNull)
//				.collect(Collectors.toList());
		
		req.setAttribute("list3", list3);
//		System.out.println(list2);
		
		// 成功轉交 update_groupBuyItem_input.jsp
		String url = "/frontend/groupBuy/listallgroupbuuy.jsp";
		RequestDispatcher failView = req.getRequestDispatcher(url);
		failView.forward(req, res);
	
	}

}
