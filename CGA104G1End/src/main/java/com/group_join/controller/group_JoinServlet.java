package com.group_join.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.group_buy.model.Group_BuyService;
import com.group_buy.model.Group_BuyVO;
import com.group_join.model.Group_JoinService;
import com.group_join.model.Group_JoinVO;

@WebServlet("/Group_JoinServlet")
public class group_JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();

		// 參團
		if ("go_join".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			String gbitem_name = req.getParameter("gbitem_name");
			String gb_name = req.getParameter("gb_name");

			/*********************** 抓登入ID ,未測試 ************************/
			Integer mem_id = (Integer) session.getAttribute("mem_id");
			/*********************** 抓登入ID *************************/
			Integer nowamount = 0;
			Integer gb_id = Integer.valueOf(req.getParameter("gb_id").trim());
			Group_JoinService group_joinSvc = new Group_JoinService();
			Group_JoinVO group_joinVO = group_joinSvc.getOneEmp(gb_id, mem_id);
			if (group_joinVO != null) {
				errorMsgs.put("gb_id", "已經購買,請更改參團資料");
				Integer gb_amount = 0;

				group_joinSvc = new Group_JoinService();
				List<Group_JoinVO> group_joinVOgb = group_joinSvc.getOneGb(gb_id);
				for (Group_JoinVO amount : group_joinVOgb) {
					gb_amount += amount.getGbbuy_amount();
				}
				group_joinVO.getGbbuy_amount();
				nowamount = gb_amount - group_joinVO.getGbbuy_amount();
				req.setAttribute("nowamount", nowamount);
				req.setAttribute("group_joinVO", group_joinVO);
				String url = "/frontend/group_join/update_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			}

			Integer gbitem_id = Integer.valueOf(req.getParameter("gbitem_id").trim());
			Integer gb_min = Integer.valueOf(req.getParameter("gb_min").trim());
			Integer gb_amount = Integer.valueOf(req.getParameter("gb_amount").trim());
			Integer gb_price = Integer.valueOf(req.getParameter("gb_price").trim());

			group_joinVO = new Group_JoinVO();
			group_joinVO.setGb_id(gb_id);
			group_joinVO.setMem_id(mem_id);
			group_joinVO.setGbitem_id(gbitem_id);
			session.setAttribute("group_joinVO", group_joinVO);
			session.setAttribute("gb_price", gb_price);
			req.setAttribute("gb_name", gb_name);
			session.setAttribute("gbitem_name", gbitem_name);
			req.setAttribute("gb_min", gb_min);
			session.setAttribute("gb_amount", gb_amount);
			Group_BuyService group_BuyService = new Group_BuyService();
			Group_BuyVO group_BuyVO = group_BuyService.getOneGroup_Buy(gb_id);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			int amount = group_BuyVO.getGb_min() - group_BuyVO.getGb_amount();
			// 數量沒了 跳錯誤
			if (amount <= 0) {
				errorMsgs.put("fail", "數量已滿,不可增加");
			}
			nowamount= group_BuyVO.getGb_amount();
			req.setAttribute("nowamount", nowamount);
			String url = "/frontend/group_join/addgroupjoin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		// 算錢
		if ("inser_Newprice".equals(action)) {
			System.out.println("進入算錢");
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer gb_id = Integer.valueOf(req.getParameter("gb_id").trim());
			Integer mem_id = Integer.valueOf(req.getParameter("mem_id").trim());
			Integer gbpay_status = Integer.valueOf(req.getParameter("gbpay_status").trim());
			Integer pickup_status = Integer.valueOf(req.getParameter("pickup_status").trim());
			Integer deliver_status = Integer.valueOf(req.getParameter("deliver_status").trim());
			Integer gbbuy_amount = 0;
			try {
				gbbuy_amount = Integer.valueOf(req.getParameter("gbbuy_amount").trim());
			} catch (Exception e) {

				errorMsgs.put("gbbuy_amount", "購買數量不能為0");
			}
			if (gbbuy_amount == 0) {
				errorMsgs.put("gbbuy_amount", "購買數量不能為0");
			}
			Group_JoinVO group_joinVO = new Group_JoinVO();
			group_joinVO.setGb_id(gb_id);
			group_joinVO.setMem_id(mem_id);
			group_joinVO.setGbpay_status(gbpay_status);
			group_joinVO.setPickup_status(pickup_status);
			group_joinVO.setDeliver_status(deliver_status);
			group_joinVO.setGbbuy_amount(gbbuy_amount);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("group_joinVO", group_joinVO);
				RequestDispatcher fail = req.getRequestDispatcher("/frontend/group_join/addgroupjoin.jsp");
				fail.forward(req, res);
				return;
			}

			/****************** 算價格 *******************/
			Integer gbbuy_price = gbbuy_amount * group_joinVO.getGroup_BuyVO().getGb_price();

			group_joinVO.setGbbuy_price(gbbuy_price);
			/********************* 數量複查 *********************/
			Integer gb_amount = 0;
			Integer nowamount = 0;
			Group_JoinService group_joinSvc = new Group_JoinService();
			List<Group_JoinVO> group_joinVOgb = group_joinSvc.getOneGb(gb_id);
			for (Group_JoinVO amount : group_joinVOgb) {
				gb_amount += amount.getGbbuy_amount();
			}

			nowamount = gb_amount;
			req.setAttribute("nowamount", nowamount);

			req.setAttribute("group_joinVO", group_joinVO);

			String url = "/frontend/group_join/addgroupjoin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if ("gb_goprice".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String gbitem_name = (String) session.getAttribute("gbitem_name");

			/*********************** 抓登入ID ,未測試 ************************/
//			Integer mem_id = Integer.valueOf(req.getParameter("mem_id").trim());

			System.out.println("進入價格計算");
			/*********************** 抓登入ID *************************/
			Integer mem_id = (Integer) session.getAttribute("mem_id");
			Integer gb_id = Integer.valueOf(req.getParameter("gb_id").trim());
//			System.out.println(gb_id+"團購編號");

			Integer gbpay_status = Integer.valueOf(req.getParameter("gbpay_status").trim());
			Integer pickup_status = Integer.valueOf(req.getParameter("pickup_status").trim());
			Integer deliver_status = Integer.valueOf(req.getParameter("deliver_status").trim());
			Integer gbbuy_amount = Integer.valueOf(req.getParameter("gbbuy_amount").trim());
			Integer gb_price = (Integer) session.getAttribute("gb_price");
			Integer gbbuy_price = (gb_price) * (gbbuy_amount);
//			Integer gbitem_id = Integer.valueOf(req.getParameter("gbitem_id").trim());
//			Integer gb_min = Integer.valueOf(req.getParameter("gb_min").trim());
			Integer gb_amount = (Integer) session.getAttribute("gb_amount");
//			Integer gb_status = Integer.valueOf(req.getParameter("gb_status").trim());

			Group_JoinVO group_joinVO = new Group_JoinVO();
			group_joinVO.setGb_id(gb_id);
//			group_joinVO.setGbitem_id(gbitem_id);
			group_joinVO.setMem_id(mem_id);
			group_joinVO.setGbpay_status(gbpay_status);
			group_joinVO.setPickup_status(pickup_status);
			group_joinVO.setDeliver_status(deliver_status);
			group_joinVO.setGbbuy_amount(gbbuy_amount);
			group_joinVO.setGbbuy_price(gbbuy_price);

			session.setAttribute("group_joinVO", group_joinVO);
//			session.setAttribute("mem_id", mem_id);
//			req.setAttribute("gbitem_name", gbitem_name);
//			session.setAttribute("gb_id", gb_id);
//			req.setAttribute("gbitem_id", gbitem_id);
//			req.setAttribute("gb_min", gb_min);
			req.setAttribute("gb_amount", gb_amount);
//			req.setAttribute("gbstart_date", gbstart_date);
//			req.setAttribute("gbend_date", gbend_date);
//			req.setAttribute("gb_status", gb_status);
			session.setAttribute("gbpay_status", gbpay_status);
			session.setAttribute("pickup_status", pickup_status);
			session.setAttribute("deliver_status", deliver_status);
			session.setAttribute("gbbuy_amount", gbbuy_amount);
			session.setAttribute("gbbuy_price", gbbuy_price);

			String url = "/frontend/group_join/addgroupjoinprice.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("insert".equals(action)) {
			/************************************* 等待處理PK問題 ***********************/

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			Integer gb_id = Integer.valueOf(req.getParameter("gb_id").trim());
			Integer mem_id = Integer.valueOf(req.getParameter("mem_id").trim());
			/******************************** 先查詢是否參團過 **************************/
			Group_JoinService group_joinSvc = new Group_JoinService();
			Group_JoinVO group_joinVO = group_joinSvc.getOneEmp(gb_id, mem_id);
			/****************************** 如果有資料跳到更新訂單 ***********************************/
			if (group_joinVO != null) {
				errorMsgs.put("gb_id", "已經購買,請更改參團資料");
				req.setAttribute("group_joinVO", group_joinVO);
				System.out.println(
						group_joinVO.getGroup_BuyVO().getGb_min() - group_joinVO.getGroup_BuyVO().getGb_amount());
				String url = "/frontend/group_join/update_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			}

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("group_joinVO", group_joinVO);
				RequestDispatcher fail = req.getRequestDispatcher("/frontend/group_join/addgroupjoin.jsp");
				fail.forward(req, res);
				return;
			}
			Integer gbbuy_amount = 0;
			Integer gbbuy_price = 0;

			try {
				gbbuy_amount = Integer.valueOf(req.getParameter("gbbuy_amount").trim());
			} catch (Exception e) {
				errorMsgs.put("gbbuy_amount", "購買數量不能為0");
			}
			if (gbbuy_amount == 0 || gbbuy_amount == null) {
				errorMsgs.put("gbbuy_amount", "購買數量不能為0");
			}
			try {
				gbbuy_price = Integer.valueOf(req.getParameter("gbbuy_price").trim());
			} catch (Exception e) {
				errorMsgs.put("gbbuy_price", "購買金額不能為0");
				System.out.println(gbbuy_price);
			}
			if (gbbuy_price == 0 || gbbuy_price == null) {
				errorMsgs.put("gbbuy_price", "購買金額不能為0");
			}
			Integer gbpay_status = Integer.valueOf(req.getParameter("gbpay_status").trim());
			Integer pickup_status = Integer.valueOf(req.getParameter("pickup_status").trim());
			Integer deliver_status = Integer.valueOf(req.getParameter("deliver_status").trim());
			group_joinVO = new Group_JoinVO();
			group_joinVO.setGb_id(gb_id);
			group_joinVO.setMem_id(mem_id);
			group_joinVO.setGbpay_status(gbpay_status);
			group_joinVO.setPickup_status(pickup_status);
			group_joinVO.setDeliver_status(deliver_status);
			group_joinVO.setGbbuy_amount(gbbuy_amount);
			group_joinVO.setGbbuy_price(gbbuy_price);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("group_joinVO", group_joinVO);
				RequestDispatcher fail = req.getRequestDispatcher("/frontend/group_join/addgroupjoin.jsp");
				fail.forward(req, res);
				return;
			}
			// 送出資料 新增
			group_joinVO = group_joinSvc.addGroup_Join(gb_id, mem_id, gbpay_status, pickup_status, deliver_status,
					gbbuy_amount, gbbuy_price);
			// 查詢團購商品總數量
			Integer gb_amount = 0;
			List<Group_JoinVO> group_joinVOgb = group_joinSvc.getOneGb(gb_id);
			for (Group_JoinVO amount : group_joinVOgb) {
				gb_amount += amount.getGbbuy_amount();
			}
			req.setAttribute("gb_id", gb_id);
			req.setAttribute("gb_amount", gb_amount);
			// 上傳數量到團購團
			Group_BuyService group_buySvc = new Group_BuyService();
			Group_BuyVO group_buyVO = group_buySvc.updateGroup_Buy_GBAmount(gb_id, gb_amount);
			req.setAttribute("group_buyVO", group_buyVO);
			group_buyVO=group_buySvc.getOneGroup_Buy(gb_id);
			Integer gbitem_id=group_buyVO.getGbitem_id();

			req.setAttribute("gbitem_id", gbitem_id);

			String url = "/CGA104G1/frontend/groupBuy/listallgroupbuy.html";
			res.sendRedirect(url);
		}

		if ("getOne_For_Display".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			Integer gb_id = (Integer) session.getAttribute("gb_id");
			Integer mem_id = (Integer) session.getAttribute("mem_id");

			Group_JoinVO group_joinVO = new Group_JoinVO();
			group_joinVO.setGb_id(gb_id);
			group_joinVO.setGb_id(mem_id);

			/*************************** 2.開始查詢資料 *****************************************/

			Group_JoinService group_joinSvc = new Group_JoinService();
			group_joinVO = group_joinSvc.getOneEmp(gb_id, mem_id);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

			session.setAttribute("group_joinVO", group_joinVO);

			String url = "/frontend/group_join/listOneGroupJoin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		// 團購商品來的團員進入查詢
		if ("getOne_Display_ByMem".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			Integer mem_id = (Integer) session.getAttribute("mem_id");
			// 團主判斷
			Group_BuyService group_buySvc = new Group_BuyService();
			List<Group_BuyVO> group_buyVO = group_buySvc.joinGBIGetAllWhereMemID(mem_id);
			Boolean Verify = false;
			for (Group_BuyVO gb : group_buyVO) {
				System.out.println(gb.getGb_id() + "我團主啦");

				if (gb.getGb_id() != null) {
					Verify = true;
					System.out.println("團主" + Verify);
				}
			}

			/*************************** 2.開始查詢資料 *****************************************/

			Group_JoinService group_joinSvc = new Group_JoinService();
			List<Group_JoinVO> group_joinVO = group_joinSvc.getOneByMem(mem_id);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			session.setAttribute("group_joinVO", group_joinVO);
			session.setAttribute("Verify", Verify);

			String url = "/frontend/group_join/listAllMemGroupJoin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("getOneGB_For_Display".equals(action)) {

			   Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			   req.setAttribute("errorMsgs", errorMsgs);

			   /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			   Integer gb_id = null;
			   Integer mem_id = (Integer) session.getAttribute("mem_id");
			   Group_BuyService group_buySvc = new Group_BuyService();
			   List<Group_BuyVO> group_buyVO = group_buySvc.joinGBIGetAllWhereMemID(mem_id);
			   for (Group_BuyVO gb : group_buyVO) {
			    gb_id = gb.getGb_id();

			    System.out.println(mem_id + "喔喔");
			   }
			   Group_BuyVO group_buyVO1 = group_buySvc.getOneGroup_Buy(gb_id);
			   Integer  amount = group_buyVO1.getGb_min()-group_buyVO1.getGb_amount();
			   
			   
			   
			   
			   Group_JoinService group_joinSvc = new Group_JoinService();
			   List<Group_JoinVO> group_joinVO = group_joinSvc.getOneGb(gb_id);
			   /*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

			   boolean verify = true;
			   req.setAttribute("gb_id", gb_id);
			   req.setAttribute("amount", amount);
			   session.setAttribute("group_joinVO", group_joinVO);
			   for (Group_JoinVO a : group_joinVO) {
			    if (a.getGbpay_status() != 1 || a.getPickup_status() != 1 || a.getDeliver_status() != 3) {
			     verify = false;
			    }

			   }
			   session.setAttribute("verify", verify);

			   System.out.println(verify);

			   String url = "/frontend/group_join/listAllGroupJoin.jsp";
			   RequestDispatcher successView = req.getRequestDispatcher(url);
			   successView.forward(req, res);
			  }
		// 團員進入更改
		if ("goUpdateByMem".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer gb_id = Integer.valueOf(req.getParameter("gb_id").trim());
			Integer mem_id = (Integer) session.getAttribute("mem_id");

			Group_JoinVO group_joinVO = new Group_JoinVO();

			group_joinVO.setGb_id(gb_id);
			group_joinVO.setGb_id(mem_id);

			/*************************** 2.開始修改資料 *****************************************/
			Group_JoinService group_joinSvc = new Group_JoinService();
			group_joinVO = group_joinSvc.getOneEmp(gb_id, mem_id);

			/********************* 數量複查 *********************/
			Integer gb_amount = 0;
			Integer nowamount = 0;
			 group_joinSvc = new Group_JoinService();
			List<Group_JoinVO> group_joinVOgb = group_joinSvc.getOneGb(gb_id);
			for (Group_JoinVO amount : group_joinVOgb) {
				gb_amount += amount.getGbbuy_amount();
			}

			nowamount = gb_amount;
			req.setAttribute("nowamount", nowamount);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("group_joinVO", group_joinVO);
			String url = "/frontend/group_join/update_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("update".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer gb_id = Integer.valueOf(req.getParameter("gb_id").trim());
			Integer mem_id = Integer.valueOf(req.getParameter("mem_id").trim());
//			Integer gbitem_id = (Integer)session.getAttribute("gbitem_id");
			Integer gbpay_status = Integer.valueOf(req.getParameter("gbpay_status").trim());
			Integer pickup_status = Integer.valueOf(req.getParameter("pickup_status").trim());
			Integer deliver_status = Integer.valueOf(req.getParameter("deliver_status").trim());
			Integer gbbuy_amount = Integer.valueOf(req.getParameter("gbbuy_amount").trim());
			Integer gbbuy_price = (Integer) session.getAttribute("gbbuy_price");
			Group_JoinVO group_joinVO = new Group_JoinVO();

			group_joinVO.setGb_id(gb_id);
			group_joinVO.setGb_id(mem_id);
//			group_joinVO.setGbitem_id(gbitem_id);
			group_joinVO.setGbpay_status(gbpay_status);
			group_joinVO.setPickup_status(pickup_status);
			group_joinVO.setDeliver_status(deliver_status);
			group_joinVO.setGbbuy_amount(gbbuy_amount);
			group_joinVO.setGbbuy_price(gbbuy_price);

			/*************************** 2.開始修改資料 *****************************************/
			Group_JoinService group_joinSvc = new Group_JoinService();
			group_joinVO = group_joinSvc.updateGroup_Join(gb_id, mem_id, gbpay_status, pickup_status, deliver_status,
					gbbuy_amount, gbbuy_price);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("group_joinVO", group_joinVO);
			String url = "/frontend/group_join/listAllGroupJoin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		// 團主更改
		if ("updatePay".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer gb_id = Integer.valueOf(req.getParameter("gb_id").trim());
			Integer mem_id = Integer.valueOf(req.getParameter("mem_id").trim());
			Integer gbpay_status = Integer.valueOf(req.getParameter("gbpay_status").trim());
			Group_JoinVO group_joinVO = new Group_JoinVO();

			group_joinVO.setGb_id(gb_id);
			group_joinVO.setGb_id(mem_id);
			group_joinVO.setGbpay_status(gbpay_status);

			System.out.println("付款狀態");
			/*************************** 2.開始修改資料 *****************************************/
			Group_JoinService group_joinSvc = new Group_JoinService();
			group_joinVO = group_joinSvc.updatePay(gb_id, mem_id, gbpay_status);
			List<Group_JoinVO> group_joinVO1 = group_joinSvc.getOneGb(gb_id);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("gb_id", gb_id);
			req.setAttribute("group_joinVO", group_joinVO1); // 資料庫update成功後,正確的的empVO物件,存入req

			boolean verify = true;
			for (Group_JoinVO a : group_joinVO1) {
				if (a.getGbpay_status() != 1 || a.getPickup_status() != 1 || a.getDeliver_status() != 3) {
					verify = false;
				}

			}
			session.setAttribute("verify", verify);
			String url = "/frontend/group_join/listAllGroupJoin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}
		// 團主更改
		if ("updatePickup".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer gb_id = Integer.valueOf(req.getParameter("gb_id").trim());
			Integer mem_id = Integer.valueOf(req.getParameter("mem_id").trim());
			Integer pickup_status = Integer.valueOf(req.getParameter("pickup_status").trim());
			Group_JoinVO group_joinVO = new Group_JoinVO();

			group_joinVO.setGb_id(gb_id);
			group_joinVO.setGb_id(mem_id);
			group_joinVO.setPickup_status(pickup_status);

			System.out.println("取貨狀態");
			/*************************** 2.開始修改資料 *****************************************/
			Group_JoinService group_joinSvc = new Group_JoinService();
			group_joinVO = group_joinSvc.updatePickup(gb_id, mem_id, pickup_status);

			List<Group_JoinVO> group_joinVO1 = group_joinSvc.getOneGb(gb_id);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("gb_id", gb_id);
			req.setAttribute("group_joinVO", group_joinVO1); // 資料庫update成功後,正確的的empVO物件,存入req

			boolean verify = true;
			for (Group_JoinVO a : group_joinVO1) {
				if (a.getGbpay_status() != 1 || a.getPickup_status() != 1 || a.getDeliver_status() != 3) {
					verify = false;
				}

			}
			session.setAttribute("verify", verify);
			String url = "/frontend/group_join/listAllGroupJoin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}
		// 團主更改
		if ("updateDeliver".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer gb_id = Integer.valueOf(req.getParameter("gb_id").trim());
			Integer mem_id = Integer.valueOf(req.getParameter("mem_id").trim());
			Integer deliver_status = Integer.valueOf(req.getParameter("deliver_status").trim());

			Group_JoinVO group_joinVO = new Group_JoinVO();

			group_joinVO.setGb_id(gb_id);
			group_joinVO.setGb_id(mem_id);
			group_joinVO.setDeliver_status(deliver_status);

			/*************************** 2.開始修改資料 *****************************************/
			Group_JoinService group_joinSvc = new Group_JoinService();
			group_joinVO = group_joinSvc.updateDeliver(gb_id, mem_id, deliver_status);
			/*************************** 團購數量複查 *************/
			List<Group_JoinVO> group_joinVO1 = group_joinSvc.getOneGb(gb_id);
			Integer gb_amount = 0;
			for (Group_JoinVO amount : group_joinVO1) {
				gb_amount += amount.getGbbuy_amount();
			}

			Group_BuyService group_BuyService = new Group_BuyService();
			Group_BuyVO group_BuyVO = group_BuyService.getOneGroup_Buy(gb_id);

			System.out.println(group_BuyVO.getGb_min());

			req.setAttribute("gb_id", gb_id);
			req.setAttribute("group_joinVO", group_joinVO1);
			boolean verify = true;
			for (Group_JoinVO a : group_joinVO1) {
				if (a.getGbpay_status() != 1 || a.getPickup_status() != 1 || a.getDeliver_status() != 3
						|| gb_amount == group_BuyVO.getGb_min()) {
					verify = false;
				}
			}
			session.setAttribute("verify", verify);
			String url = "/frontend/group_join/listAllGroupJoin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		// 團主更改 團購團完成

		if ("update_gb_status".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer gb_id = Integer.valueOf(req.getParameter("gb_id").trim());
			Integer gb_status = 9;
			Integer mem_id = (Integer) session.getAttribute("mem_id");
//			Group_JoinVO group_joinVO = new Group_JoinVO();
//
//			group_joinVO.setGb_id(gb_id);
//			group_joinVO.setGb_id(gb_status);
//

			/*************************** 2.開始修改資料 *****************************************/
			Group_BuyVO group_buyVO = new Group_BuyVO();
			Group_BuyService group_buySvc = new Group_BuyService();
			group_buyVO = group_buySvc.updateGroup_Buy_GBStatus(gb_id, gb_status);

//			group_buyVO = group_buySvc.getOneGroup_Buy(gb_id);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("group_buyVO", group_buyVO);

			Group_BuyService group_BuyService = new Group_BuyService();
			List<Group_BuyVO> list = group_BuyService.joinGBIGetAllWhereMemID(mem_id);
			req.setAttribute("list", list);

			String url = "./frontend/groupBuy/mygroupbuyapplylist.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			System.out.println(req.getRequestDispatcher(url));
			successView.forward(req, res);
		}

		// 價錢計算
		if ("update_Newprice".equals(action)) {
			System.out.println("進入算錢");
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer gb_id = Integer.valueOf(req.getParameter("gb_id").trim());
			Integer mem_id = Integer.valueOf(req.getParameter("mem_id").trim());
			Integer gbpay_status = Integer.valueOf(req.getParameter("gbpay_status").trim());
			Integer pickup_status = Integer.valueOf(req.getParameter("pickup_status").trim());
			Integer deliver_status = Integer.valueOf(req.getParameter("deliver_status").trim());
			Integer gbbuy_amount = 0;
			try {
				gbbuy_amount = Integer.valueOf(req.getParameter("gbbuy_amount").trim());
			} catch (Exception e) {

				errorMsgs.put("gbbuy_amount", "購買數量不能為0");
			}
			if (gbbuy_amount == 0 || gbbuy_amount == null) {
				errorMsgs.put("gbbuy_amount", "購買數量不能為0");
			}
			Group_JoinVO group_joinVO = new Group_JoinVO();
			group_joinVO.setGb_id(gb_id);
			group_joinVO.setMem_id(mem_id);
			group_joinVO.setGbpay_status(gbpay_status);
			group_joinVO.setPickup_status(pickup_status);
			group_joinVO.setDeliver_status(deliver_status);
			group_joinVO.setGbbuy_amount(gbbuy_amount);
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("group_joinVO", group_joinVO);
				RequestDispatcher fail = req.getRequestDispatcher("/frontend/group_join/update_input.jsp");
				fail.forward(req, res);
				return;
			}
			/****************** 算價格 *******************/
			Integer gbbuy_price = gbbuy_amount * group_joinVO.getGroup_BuyVO().getGb_price();
			group_joinVO.setGbbuy_price(gbbuy_price);

			Integer gb_amount = 0;
			Integer nowamount = 0;
			Group_JoinService group_joinSvc = new Group_JoinService();
			List<Group_JoinVO> group_joinVOgb = group_joinSvc.getOneGb(gb_id);
			for (Group_JoinVO amount : group_joinVOgb) {
				gb_amount += amount.getGbbuy_amount();
			}

			Group_JoinVO group_joinVO1 = group_joinSvc.getOneEmp(gb_id, mem_id);
			group_joinVO1.getGbbuy_amount();
			nowamount = gb_amount - group_joinVO1.getGbbuy_amount();
			req.setAttribute("nowamount", nowamount);
			System.out.println("價格:" + gbbuy_price);

			req.setAttribute("group_joinVO", group_joinVO);

			String url = "/frontend/group_join/update_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		// 團員更改確認後跳到全查
		if ("UpdateByMem".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("進入更新確認");
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer gb_id = Integer.valueOf(req.getParameter("gb_id").trim());
			Integer mem_id = Integer.valueOf(req.getParameter("mem_id").trim());
			Integer gbpay_status = Integer.valueOf(req.getParameter("gbpay_status").trim());
			Integer pickup_status = Integer.valueOf(req.getParameter("pickup_status").trim());
			Integer deliver_status = Integer.valueOf(req.getParameter("deliver_status").trim());
			Integer gbbuy_amount = 0;
			Integer gbbuy_price = 0;

			try {
				gbbuy_amount = Integer.valueOf(req.getParameter("gbbuy_amount").trim());
			} catch (Exception e) {

				errorMsgs.put("gbbuy_amount", "購買數量不能為0");

			}
			if (gbbuy_amount == 0 || gbbuy_amount == null) {
				errorMsgs.put("gbbuy_amount", "購買數量不能為0");
			}

			try {
				gbbuy_price = Integer.valueOf(req.getParameter("gbbuy_price").trim());
			} catch (Exception e) {

				errorMsgs.put("gbbuy_price", "購買金額不能為0");
				System.out.println(gbbuy_price);
			}
			if (gbbuy_price == 0 || gbbuy_price == null) {
				errorMsgs.put("gbbuy_price", "購買金額不能為0");
			}

			Group_JoinVO group_joinVO = new Group_JoinVO();
			group_joinVO.setGb_id(gb_id);
			group_joinVO.setMem_id(mem_id);
			group_joinVO.setGbpay_status(gbpay_status);
			group_joinVO.setPickup_status(pickup_status);
			group_joinVO.setDeliver_status(deliver_status);
			group_joinVO.setGbbuy_amount(gbbuy_amount);
			group_joinVO.setGbbuy_price(gbbuy_price);
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("group_joinVO", group_joinVO);
				RequestDispatcher fail = req.getRequestDispatcher("/frontend/group_join/update_input.jsp");
				fail.forward(req, res);
				return;
			}
			// 更新資料
			Group_JoinService group_joinSvc = new Group_JoinService();
			group_joinVO = group_joinSvc.updateGroup_Join(gb_id, mem_id, gbpay_status, pickup_status, deliver_status,
					gbbuy_amount, gbbuy_price);
			// 查詢團購總數量
			Integer gb_amount = 0;
			List<Group_JoinVO> group_joinVOgb = group_joinSvc.getOneGb(gb_id);
			for (Group_JoinVO amount : group_joinVOgb) {
				gb_amount += amount.getGbbuy_amount();
			}

			// 帶入gb_id和gb_amount新增購買數量
			Group_BuyService group_buySvc = new Group_BuyService();
			Group_BuyVO group_buyVO = group_buySvc.updateGroup_Buy_GBAmount(gb_id, gb_amount);
			req.setAttribute("group_buyVO", group_buyVO);

			mem_id = (Integer) session.getAttribute("mem_id");
			Map<Integer, String[]> map = new HashMap<Integer, String[]>();
			// 團主判斷
			group_buySvc = new Group_BuyService();
			List<Group_BuyVO> group_buyVO1 = group_buySvc.joinGBIGetAllWhereMemID(mem_id);
			Boolean Verify = false;
			for (Group_BuyVO gb : group_buyVO1) {
				if (gb.getGb_id() != null) {
					Verify = true;
				}
			}

			req.setAttribute("group_buyVO", group_buyVO1);
			// 跳轉全查
			List<Group_JoinVO> group_joinVO2 = group_joinSvc.getOneByMem(mem_id);
			req.setAttribute("group_joinVO", group_joinVO2);
			session.setAttribute("Verify", Verify);

			String url = "/frontend/group_join/listAllMemGroupJoin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		// 團員取消參團

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer mem_id = (Integer) session.getAttribute("mem_id");
			Integer gb_id = Integer.valueOf(req.getParameter("gb_id"));

			/*************************** 2.開始刪除資料 ***************************************/
			Group_JoinService group_joinSvc = new Group_JoinService();
			group_joinSvc.deleteGj(gb_id, mem_id);
			// 查詢參團總數量
			Integer gb_amount = 0;
			List<Group_JoinVO> group_joinVOgb = group_joinSvc.getOneGb(gb_id);
			for (Group_JoinVO amount : group_joinVOgb) {
				gb_amount += amount.getGbbuy_amount();
			}
			// 帶入gb_id和gb_amount新增購買數量
			Group_BuyService group_buySvc = new Group_BuyService();
			Group_BuyVO group_buyVO = group_buySvc.updateGroup_Buy_GBAmount(gb_id, gb_amount);
			req.setAttribute("group_buyVO", group_buyVO);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			// 團主判斷
			group_buySvc = new Group_BuyService();
			List<Group_BuyVO> group_buyVO1 = group_buySvc.joinGBIGetAllWhereMemID(mem_id);
			Boolean Verify = false;
			for (Group_BuyVO gb : group_buyVO1) {
				if (gb.getGb_id() != null) {
					Verify = true;
				}
			}

			// 跳轉全查
			List<Group_JoinVO> group_joinVO2 = group_joinSvc.getOneByMem(mem_id);
			req.setAttribute("group_joinVO", group_joinVO2);
			session.setAttribute("Verify", Verify);

			String url = "/frontend/group_join/listAllMemGroupJoin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}
}
