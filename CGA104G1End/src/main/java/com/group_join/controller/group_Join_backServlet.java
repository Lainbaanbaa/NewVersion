package com.group_join.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.emp_effect.model.Emp_effectVO;
import com.group_buy.model.Group_BuyService;
import com.group_buy.model.Group_BuyVO;
import com.group_join.model.Group_JoinService;
import com.group_join.model.Group_JoinVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;

@WebServlet("/Group_Join_backServlet")
public class group_Join_backServlet extends HttpServlet {
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
	

		if ("list_group_join".equals(action)) {
			System.out.println("進入JOIN查詢");
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
			if (req.getParameter("whichPage") == null) {
				Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
				session.setAttribute("map", map1);
				map = map1;
			}
			Group_JoinService group_joinService = new Group_JoinService();
			List<Group_JoinVO> list = group_joinService.getAll(map);
			
			if (list.isEmpty()) {
				errorMsgs.put("gb_id", "查無資料");
				RequestDispatcher fail = req.getRequestDispatcher("/backend/group_join/select_page.jsp");
				fail.forward(req, res);
				return;		
			}
			req.setAttribute("list_group_join", list);
			RequestDispatcher successView = req.getRequestDispatcher("/backend/group_join/list_All.jsp");
			successView.forward(req, res);

		}
//後台
		if ("getOne_For_Update".equals(action)) {
			System.out.println("進入");
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();

			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer gb_id = Integer.valueOf(req.getParameter("gb_id"));
			Integer mem_id = Integer.valueOf(req.getParameter("mem_id"));
			/*************************** 2.開始查詢資料 ****************************************/
			Group_JoinService group_joinService = new Group_JoinService();
			Group_JoinVO group_joinVO = group_joinService.getOneEmp(gb_id, mem_id);
			
			Integer gb_amount = 0;
			Integer nowamount = 0;
			Group_JoinService group_joinSvc = new Group_JoinService();
			List<Group_JoinVO> group_joinVOgb = group_joinSvc.getOneGb(gb_id);
			for(Group_JoinVO amount :group_joinVOgb ) {
			gb_amount+=amount.getGbbuy_amount();
			}
			
			System.out.println("參團總數");
			Group_JoinVO group_joinVO1 = group_joinSvc.getOneEmp(gb_id, mem_id);
			group_joinVO1.getGbbuy_amount();
			nowamount=gb_amount-group_joinVO1.getGbbuy_amount();
			
			System.out.println(nowamount);
			
			req.setAttribute("nowamount", nowamount);	
			
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			session.setAttribute("group_joinVO", group_joinVO);
			
			String url = "/backend/group_join/update.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		//後台價錢計算
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
			if (gbbuy_amount == 0||gbbuy_amount==null) {
				errorMsgs.put("gbbuy_amount", "購買數量不能為0");
			}	


			Group_JoinVO group_joinVO = new Group_JoinVO();
			group_joinVO.setGb_id(gb_id);
			group_joinVO.setMem_id(mem_id);
			group_joinVO.setGbpay_status(gbpay_status);
			group_joinVO.setPickup_status(pickup_status);
			group_joinVO.setDeliver_status(deliver_status);
			group_joinVO.setGbbuy_amount(gbbuy_amount);
			
	
			
			/****************** 算價格 *******************/
			Integer gbbuy_price = gbbuy_amount * group_joinVO.getGroup_BuyVO().getGb_price();
			group_joinVO.setGbbuy_price(gbbuy_price);
			System.out.println("價格:" + gbbuy_price);
			
			/*********************數量複查*********************/
			Integer gb_amount = 0;
			Integer nowamount = 0;
			Group_JoinService group_joinSvc = new Group_JoinService();
			List<Group_JoinVO> group_joinVOgb = group_joinSvc.getOneGb(gb_id);
			for(Group_JoinVO amount :group_joinVOgb ) {
			gb_amount+=amount.getGbbuy_amount();
			}

			Group_JoinVO group_joinVO1 = group_joinSvc.getOneEmp(gb_id, mem_id);
			group_joinVO1.getGbbuy_amount();
			nowamount=gb_amount-group_joinVO1.getGbbuy_amount();
			req.setAttribute("nowamount", nowamount);	
			
			req.setAttribute("group_joinVO", group_joinVO);		
			String url = "/backend/group_join/update.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if ("UpdateByEmp".equals(action)) {
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
			if (gbbuy_amount == 0||gbbuy_amount==null) {
				errorMsgs.put("gbbuy_amount", "購買數量不能為0");
			}	
			try {
				gbbuy_price = Integer.valueOf(req.getParameter("gbbuy_price").trim());
			} catch (Exception e) {
				errorMsgs.put("gbbuy_price", "購買金額不能為0");
				System.out.println(gbbuy_price);
			}
			if (gbbuy_price == 0 || gbbuy_price==null ) {
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
				RequestDispatcher fail = req.getRequestDispatcher("/backend/group_join/update.jsp");
				fail.forward(req, res);
				return; 
			}
			//更新資料
			Group_JoinService group_joinSvc = new Group_JoinService();
			group_joinVO = group_joinSvc.updateGroup_Join(gb_id, mem_id, gbpay_status, pickup_status,
					deliver_status, gbbuy_amount, gbbuy_price);
			
			Integer gb_amount = 0;
			List<Group_JoinVO> group_joinVOgb = group_joinSvc.getOneGb(gb_id);
			for(Group_JoinVO amount :group_joinVOgb ) {
			gb_amount+=amount.getGbbuy_amount();			
			}		
			req.setAttribute("gb_id", gb_id);
			req.setAttribute("gb_amount", gb_amount);
			//上傳數量到團購團
			Group_BuyService group_buySvc = new Group_BuyService();
			Group_BuyVO group_buyVO = group_buySvc.updateGroup_Buy_GBAmount(gb_id, gb_amount);
			
			Group_JoinService group_joinService = new Group_JoinService();
			List<Group_JoinVO> list = group_joinService.getAll();

			req.setAttribute("list_group_join", list);
			String url = "/backend/group_join/list_All.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("delete".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();

			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer gb_id = Integer.valueOf(req.getParameter("gb_id").trim());
			Integer mem_id = Integer.valueOf(req.getParameter("mem_id").trim());

			/*************************** 2.開始刪除資料 ***************************************/
			Group_JoinService group_joinSvc = new Group_JoinService();
			group_joinSvc.deleteGj(gb_id, mem_id);
			//查詢全部
			Integer gb_amount = 0;
			List<Group_JoinVO> group_joinVOgb = group_joinSvc.getOneGb(gb_id);
			for(Group_JoinVO amount :group_joinVOgb ) {
			gb_amount+=amount.getGbbuy_amount();			
			}
			System.out.println(gb_amount);
			//上傳到團購團
			Group_BuyService group_buySvc = new Group_BuyService();
			Group_BuyVO group_buyVO = group_buySvc.updateGroup_Buy_GBAmount(gb_id, gb_amount);
			req.setAttribute("group_buyVO", group_buyVO);
 
			List<Group_JoinVO> list = group_joinSvc.getAll();
			req.setAttribute("list_group_join", list);
			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/backend/group_join/list_All.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if ("insert".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			Integer gb_id = null;
			Group_BuyService group_BuyService = new Group_BuyService();
			Group_BuyVO group_BuyVO = new Group_BuyVO();
			try {
				gb_id = Integer.valueOf(req.getParameter("gb_id").trim());
				group_BuyVO = group_BuyService.getOneGroup_Buy(gb_id);
				if (group_BuyVO == null) {
					errorMsgs.put("gb_id", "沒有這個團購團,請確認資料後再新增");
				}
				System.out.println(errorMsgs);
			} catch (Exception e) {
			}
			Integer mem_id = null;
			try {
				mem_id = Integer.valueOf(req.getParameter("mem_id").trim());

			} catch (Exception e) {
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(mem_id);

				if (memVO == null) {
					errorMsgs.put("mem_id", "沒有這個會員,請確認資料後再新增");
				}
			}

			Integer gbpay_status = Integer.valueOf(req.getParameter("gbpay_status").trim());
			Integer pickup_status = Integer.valueOf(req.getParameter("pickup_status").trim());
			Integer deliver_status = Integer.valueOf(req.getParameter("deliver_status").trim());
			Integer gbbuy_amount = null;
			try {
				gbbuy_amount = Integer.valueOf(req.getParameter("gbbuy_amount").trim());

			} catch (Exception e) {
				if (gbbuy_amount == 0) {
					errorMsgs.put("gbbuy_amount", "至少購賣1個產品");
				}
			}
			Integer gbbuy_price = null;
			try {
				gbbuy_price = Integer.valueOf(req.getParameter("gbbuy_price").trim());

			} catch (Exception e) {
				if (gbbuy_amount == null) {
					errorMsgs.put("gbbuy_amount", "至少購賣1個產品");
				}
			}
			 group_BuyService = new Group_BuyService();
			 group_BuyVO = group_BuyService.getOneGroup_Buy(gb_id);
			
			int amount = group_BuyVO.getGb_min()-group_BuyVO.getGb_amount();
			//數量沒了 跳回首頁抱錯
			if(amount <= 0) {
				errorMsgs.put("fail", "數量已滿,不可增加數量");
				
				String url = "/backend/group_join/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
				}
			
			
			
			/************************查詢是否參團******************/
			Group_JoinService group_joinSvc = new Group_JoinService();
			Group_JoinVO group_joinVO = group_joinSvc.getOneEmp(gb_id, mem_id);
			if (group_joinVO != null) {

				errorMsgs.put("gb_id", "已經購買,請更改參團資料");
				req.setAttribute("group_joinVO", group_joinVO);

				String url = "/backend/group_join/update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			}
			
			
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
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/group_join/addNewGj_Last.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			Group_JoinService group_joinService = new Group_JoinService();
			group_joinService.addGroup_Join(gb_id, mem_id, gbpay_status, pickup_status, deliver_status, gbbuy_amount,
					gbbuy_price);
			//新增後全部購買數量
			Integer gb_amount = 0;
			List<Group_JoinVO> group_joinVOgb = group_joinSvc.getOneGb(gb_id);
			for(Group_JoinVO amount2 :group_joinVOgb ) {
			gb_amount+=amount2.getGbbuy_amount();			
			}
			System.out.println(gb_amount);
			//上傳到團購團
			Group_BuyService group_buySvc = new Group_BuyService();
			Group_BuyVO group_buyVO = group_buySvc.updateGroup_Buy_GBAmount(gb_id, gb_amount);
			req.setAttribute("group_buyVO", group_buyVO);

			//回查詢
			List<Group_JoinVO> list = group_joinService.getAll();
			req.setAttribute("list_group_join", list);
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/backend/group_join/list_All.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Display".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer gb_id = Integer.valueOf(req.getParameter("gb_id").trim());
			/*************************** 2.開始查詢資料 *****************************************/
			Group_BuyService group_BuyService = new Group_BuyService();
			Group_BuyVO group_BuyVO = group_BuyService.getOneGroup_Buy(gb_id);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			int amount = group_BuyVO.getGb_min()-group_BuyVO.getGb_amount();
			//數量沒了 跳回首頁抱錯
			if(amount <= 0) {
				errorMsgs.put("fail", "數量已滿,不可增加");
				
				String url = "/backend/group_join/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			}
			int price = group_BuyVO.getGb_price();
			
			
			
			req.setAttribute("price", price);
			req.setAttribute("amount", amount);

			session.setAttribute("group_BuyVO", group_BuyVO);	
			String url = "/backend/group_join/addNewGj_Price.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if ("getOne_Display_ByEmp".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Group_JoinService group_joinSvc = new Group_JoinService();
			List<Group_JoinVO> list = group_joinSvc.getAll();
			req.setAttribute("list_group_join", list);

			String url = "/backend/group_join/list_All.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		

		if ("gb_totle_price".equals(action)) { 

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			Integer gb_id = Integer.valueOf(req.getParameter("gb_id").trim());
			Integer mem_id = Integer.valueOf(req.getParameter("mem_id").trim());
			Integer gbpay_status = Integer.valueOf(req.getParameter("gbpay_status").trim());
			Integer pickup_status = Integer.valueOf(req.getParameter("pickup_status").trim());
			Integer deliver_status = Integer.valueOf(req.getParameter("deliver_status").trim());
			Integer gbbuy_amount = Integer.valueOf(req.getParameter("gbbuy_amount").trim());
			Integer gb_price = Integer.valueOf(req.getParameter("gb_price").trim());
			Group_BuyService group_BuyService = new Group_BuyService();
			Group_BuyVO group_BuyVO = group_BuyService.getOneGroup_Buy(gb_id);
			
			int amount = group_BuyVO.getGb_min()-group_BuyVO.getGb_amount();
			//數量沒了 跳回首頁抱錯
			if(amount <= 0) {
				errorMsgs.put("fail", "數量已滿,不可增加數量");
				
				String url = "/backend/group_join/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
				}
			Integer gbbuy_price = (gb_price) * (gbbuy_amount);
			//deliver_status 狀態
			String status =null;
			
			switch(deliver_status) { 
	        case 0:
	        	status = "未出貨"; 
	            break; 
	        case 1: 
	        	status = "已出貨";
	        	
	            break; 
	        case 2: 
	        	status = "配送中"; 
	            break; 
	        case 3: 
	        	status = "已送達";
	            break; 
	    } 
			req.setAttribute("mem_id", mem_id);
			req.setAttribute("gb_id", gb_id);
			req.setAttribute("gbpay_status", gbpay_status);
			req.setAttribute("pickup_status", pickup_status);
			req.setAttribute("deliver_status", deliver_status);
			req.setAttribute("gbbuy_amount", gbbuy_amount);
			req.setAttribute("gbbuy_price", gbbuy_price);
			req.setAttribute("status", status);
			String url = "/backend/group_join/addNewGj_Last.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
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
			List<Group_JoinVO> group_joinVO1 = group_joinSvc.getAll();
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/	
			req.setAttribute("gb_id", gb_id);
			req.setAttribute("list_group_join", group_joinVO1);
			RequestDispatcher successView = req.getRequestDispatcher("/backend/group_join/list_All.jsp");
			successView.forward(req, res);
		}
	
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
			/*************************** 2.開始修改資料 *****************************************/
			Group_JoinService group_joinSvc = new Group_JoinService();
			group_joinVO = group_joinSvc.updatePickup(gb_id, mem_id, pickup_status);

			List<Group_JoinVO> group_joinVO1 = group_joinSvc.getAll();
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/	
			req.setAttribute("gb_id", gb_id);
			req.setAttribute("list_group_join", group_joinVO1);
			RequestDispatcher successView = req.getRequestDispatcher("/backend/group_join/list_All.jsp");
			successView.forward(req, res);
		}
	
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
			List<Group_JoinVO> group_joinVO1 = group_joinSvc.getAll();
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/	
			req.setAttribute("gb_id", gb_id);
			req.setAttribute("list_group_join", group_joinVO1);
			RequestDispatcher successView = req.getRequestDispatcher("/backend/group_join/list_All.jsp");
			successView.forward(req, res);
		}
	}
}
