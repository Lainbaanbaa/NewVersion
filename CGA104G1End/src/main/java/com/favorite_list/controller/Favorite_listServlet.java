package com.favorite_list.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.favorite_list.model.Favorite_listService;
import com.favorite_list.model.Favorite_listVO;

public class Favorite_listServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		

		if ("getOne_For_Display".equals(action)) {
//			 Select.jsp請求

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
			// 請求參數,錯誤處理
			String mem = req.getParameter("mem_id");
			// 去除空白後長度為0
			if (mem == null || (mem.trim()).length() == 0) {
				errorMsgs.add("請輸入會員編號");
			}
			// 是否為空字串(非空白)
			if (!errorMsgs.isEmpty()) {
				// 轉發到
				RequestDispatcher fail = req.getRequestDispatcher("/favorite_list/select_page.jsp");
				fail.forward(req, res);
				return;
			}

			Integer mem_id = null;
			try {
				mem_id = Integer.valueOf(mem);
			} catch (NumberFormatException e) {
				errorMsgs.add("會員編號不正確");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher fail = req.getRequestDispatcher("/favorite_list/select_page.jsp");
				fail.forward(req, res);
				return;
			}

			// 驗證過,開始查詢資料
			Favorite_listService favorite_listSvc = new Favorite_listService();
			Favorite_listVO favorite_listVO = favorite_listSvc.getOneFavorite_list(mem_id);
			if (favorite_listVO == null) {
				errorMsgs.add("查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher fail = req.getRequestDispatcher("/favorite_list/select_page.jsp");
				fail.forward(req, res);
				return;
			}

			// 回傳資料
			req.setAttribute("favorite_listVO", favorite_listVO);
			String url = "/favorite_list/listOneMem_id.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			Integer mem_id = Integer.valueOf(req.getParameter("mem_id"));

			Favorite_listService favorite_listSvc = new Favorite_listService();
			Favorite_listVO favorite_listVO = favorite_listSvc.getOneFavorite_list(mem_id);

			req.setAttribute("favorite_listVO", favorite_listVO);
			String url = "/favorite_list/update_favorite_input.jsp";

			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			Integer mem_id = Integer.valueOf(req.getParameter("mem_id").trim());
			Integer item_id = null;
			try {
				item_id = Integer.valueOf(req.getParameter("item_id").trim());
			} catch (NumberFormatException e) {
				item_id = 0;
				errorMsgs.add("請輸入商品編號");
			}
			java.sql.Timestamp fav_time = null;
			fav_time = java.sql.Timestamp.valueOf(req.getParameter("fav_time").trim());

			Favorite_listVO favorite_listVO = new Favorite_listVO();
			favorite_listVO.setMem_id(mem_id);
			favorite_listVO.setItem_id(mem_id);
			favorite_listVO.setFav_time(Timestamp.valueOf(LocalDateTime.now()));

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("favorite_listVO", favorite_listVO);
				RequestDispatcher fail = req.getRequestDispatcher("/favorite_list/update_favorite_input.jsp");
				fail.forward(req, res);
				return;
			}
			// 修改
			Favorite_listService favorite_listSvc = new Favorite_listService();
			// 修改完成
//			favorite_listVO = favorite_listSvc.updateFavorite_list(mem_id, item_id);
			favorite_listVO = favorite_listSvc.updateFavorite_list(mem_id, item_id, fav_time);

			req.setAttribute("favorite_listVO", favorite_listVO);
			String url = "/favorite_list/listOneMem_id.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Integer mem_id = null;
			try {
				mem_id = Integer.valueOf(req.getParameter("mem_id").trim());
			} catch (NumberFormatException e) {
				mem_id = 0;
				errorMsgs.add("會員編號請勿空白");
			}

			Integer item_id = null;
			try {
				item_id = Integer.valueOf(req.getParameter("item_id").trim());
			} catch (NumberFormatException e) {
				item_id = 0;
				errorMsgs.add("商品編號請勿空白");
			}
			java.sql.Timestamp fav_time = java.sql.Timestamp.valueOf(req.getParameter("fav_time").trim());
			Favorite_listVO favorite_listVO = new Favorite_listVO();
			favorite_listVO.setMem_id(mem_id);
			favorite_listVO.setItem_id(item_id);
			favorite_listVO.setFav_time(fav_time);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("favorite_listVO", favorite_listVO);
				RequestDispatcher fail = req.getRequestDispatcher("/favorite_list/addFavorite_list.jsp");
				fail.forward(req, res);
				return;
			}

			// 新增資料
			Favorite_listService favorite_listSvc = new Favorite_listService();
			favorite_listVO = favorite_listSvc.addFavorite_list(mem_id, item_id, fav_time);
//			favorite_listVO = favorite_listSvc.addFavorite_list(mem_id, item_id);
			// 完成,轉交
			req.setAttribute("favorite_listVO", favorite_listVO);
			String url = "/favorite_list/listAllFavorite_list.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			//接收請求
			Integer mem_id = Integer.valueOf(req.getParameter("mem_id"));
			//刪除
			Favorite_listService favorite_listSvc = new Favorite_listService();
			favorite_listSvc.deleteFavorite_list(mem_id);
			//完成,轉交
			String url = "/favorite_list/listAllFavorite_list.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}

}
