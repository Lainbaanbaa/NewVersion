package com.group_buy_order.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
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
import com.group_buy_order.model.Group_Buy_OrderService;
import com.group_buy_order.model.Group_Buy_OrderVO;

import core.util.UUIDGenerator;
import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;

import static java.lang.System.out;

@WebServlet("/Group_Buy_OrderServlet")
public class Group_Buy_OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Group_Buy_OrderServlet() {
		super();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();

		if ("update_pt".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			Integer gborder_id = Integer.valueOf(req.getParameter("gborder_id").trim());

			Group_Buy_OrderVO group_buy_orderVO = new Group_Buy_OrderVO();

			group_buy_orderVO.setGborder_id(gborder_id);

			/*************************** 2.開始修改資料 *****************************************/
			Group_Buy_OrderService group_buy_OrderSvc = new Group_Buy_OrderService();
			group_buy_orderVO = group_buy_OrderSvc.update_pt(gborder_id);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			group_buy_orderVO = group_buy_OrderSvc.getOneEmp(gborder_id);

			req.setAttribute("group_buy_orderVO", group_buy_orderVO);

			String url = "/frontend/group_buy_order/listOneGroup_Buy_Order_byMaster.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		if ("update_paying".equals(action)) {

		
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer gborder_id = Integer.valueOf(req.getParameter("gborder_id").trim());
			
			Group_Buy_OrderService group_buy_OrderSvc = new Group_Buy_OrderService();

			Group_Buy_OrderVO group_buy_orderVO = group_buy_OrderSvc.getOneEmp(gborder_id);
				
			 StringBuffer sb = new StringBuffer();
		        String url = sb.append(req.getScheme()).append("://")
		                .append(req.getServerName()).append(":")
		                .append(req.getServerPort())
		                .append(req.getContextPath()).toString();
			
		 	   Date date = new Date();
		        SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		        String orderDate = sd.format(date);
		        String indexUrl = url + "/frontend/index.html";
		        String returnUrl = url + "/EcpayReturn";

		        AllInOne allInOne = new AllInOne("");
		        AioCheckOutALL obj = new AioCheckOutALL();
		        obj.setMerchantTradeNo(String.valueOf(gborder_id) + "br" + UUIDGenerator.getUUID()); // 訂單id+br+亂碼16位
		        obj.setMerchantTradeDate(orderDate); // 交易時間
		        obj.setTotalAmount(String.valueOf(group_buy_orderVO.getGb_endprice())); // 訂單總金額
		        obj.setTradeDesc("A test order."); // 訂單描述
		        obj.setItemName(group_buy_orderVO.getReceiver_name() + " 的 Barei 團購訂單，訂單編號: " + gborder_id); // 商品項目
		        obj.setReturnURL(indexUrl);
		        obj.setClientBackURL(indexUrl);
		        obj.setOrderResultURL(returnUrl);
		        obj.setNeedExtraPaidInfo("N");

		       String form = allInOne.aioCheckOut(obj, null);
		       out.print(form);
			
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			group_buy_orderVO = group_buy_OrderSvc.getOneEmp(gborder_id);

			

			
			req.setAttribute("group_buy_orderVO", group_buy_orderVO);

//			String url = "/frontend/group_buy_order/listOneGroup_Buy_Order_byMaster.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("getOne_For_Backend_Display".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("gborder_id");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("gborder_id", "請輸入團購訂單編號");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher fail = req.getRequestDispatcher("/backend/group_buy_order/listGroup_Buy_Order.jsp");
				fail.forward(req, res);
				return;
			}

			Integer gborder_id = null;
			try {
				gborder_id = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("gborder_id", "團購訂單編號格式不正確");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher fail = req.getRequestDispatcher("/backend/group_buy_order/listGroup_Buy_Order.jsp");
				fail.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/

			Group_Buy_OrderService group_buy_OrdeSvc = new Group_Buy_OrderService();
			Group_Buy_OrderVO group_buy_orderVO = group_buy_OrdeSvc.getOneEmp(gborder_id);
			if (group_buy_orderVO == null) {
				errorMsgs.put("gborder_id", "查無資料");

			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher fail = req.getRequestDispatcher("/backend/group_buy_order/listGroup_Buy_Order.jsp");
				fail.forward(req, res);
				return;
			}
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

			req.setAttribute("group_buy_orderVO", group_buy_orderVO);

			String url = "/backend/group_buy_order/updateGroup_Buy_Order.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("getOne_For_Display".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("gborder_id");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("gborder_id", "請輸入團購訂單編號");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher fail = req.getRequestDispatcher("/frontend/group_buy_order/select_page.jsp");
				fail.forward(req, res);
				return;
			}
			Integer gborder_id = null;
			try {
				gborder_id = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("gborder_id", "團購訂單編號格式不正確");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher fail = req.getRequestDispatcher("/frontend/group_buy_order/select_page.jsp");
				fail.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/

			Group_Buy_OrderService group_buy_OrdeSvc = new Group_Buy_OrderService();
			Group_Buy_OrderVO group_buy_orderVO = group_buy_OrdeSvc.getOneEmp(gborder_id);
			if (group_buy_orderVO == null) {
				errorMsgs.put("gborder_id", "查無資料");

			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher fail = req.getRequestDispatcher("/frontend/group_buy_order/select_page.jsp");
				fail.forward(req, res);
				return;
			}
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

			req.setAttribute("group_buy_orderVO", group_buy_orderVO);

			Integer gb_id = group_buy_orderVO.getGb_id();

			Group_BuyService group_buySvc = new Group_BuyService();

			Group_BuyVO group_buyVO = group_buySvc.getOneGroup_Buy(gb_id);
			System.out.println("編號006" + group_buyVO.getMem_id());
			Integer master = group_buyVO.getMem_id();
			System.out.println("編號007" + master);

			Integer mem = (Integer) session.getAttribute("mem_id");
			int master_id = master;
			int mem_id = mem;
			if (master_id == mem_id) {
				String url = "/frontend/group_buy_order/listOneGroup_Buy_Order_byMaster.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			}
			String url = "/frontend/group_buy_order/listOneGroup_Buy_Order.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if ("update".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			Integer gbitem_id = Integer.valueOf(req.getParameter("gbitem_id").trim());
			System.out.println(gbitem_id);
			// 團購團編號
			Integer gb_id = Integer.valueOf(req.getParameter("gb_id").trim());
			// 抓折扣數量
			Integer gbitem_amount = Integer.valueOf(req.getParameter("gbitem_amount").trim());
			// 抓運算後原始價格
			Integer gboriginal_price = Integer.valueOf(req.getParameter("gboriginal_price").trim());
			// 抓折扣後運算價格
			Integer gb_endprice = Integer.valueOf(req.getParameter("gb_endprice").trim());
			// 資料庫自主生成時間
			java.sql.Timestamp gborder_date = java.sql.Timestamp.valueOf(req.getParameter("gborder_date").trim());

			Integer gborder_paying = Integer.valueOf(req.getParameter("gborder_paying").trim());

			try {
				gborder_paying = Integer.valueOf(req.getParameter("gborder_paying").trim());

			} catch (Exception e) {

				errorMsgs.put("gborder_paying", "請選擇付款方式");
			}

			Integer gborder_send = null;
			try {
				gborder_send = Integer.valueOf(req.getParameter("gborder_send").trim());
			} catch (Exception e) {

				errorMsgs.put("gborder_send", "請輸入貨運方式");
			}

			Integer gborder_status = Integer.valueOf(req.getParameter("gborder_status").trim());

			String tracking_num = req.getParameter("tracking_num");
			java.sql.Timestamp pickup_time = java.sql.Timestamp.valueOf(req.getParameter("pickup_time").trim());

			Integer gborder_id = Integer.valueOf(req.getParameter("gborder_id").trim());

			String gborder_other = req.getParameter("gborder_other");

			String receiver_name = req.getParameter("receiver_name");
			if (receiver_name == null || receiver_name.trim().length() == 0) {

				errorMsgs.put("receiver_name", "收件人請勿留白");
			}

			String receiver_address = req.getParameter("receiver_address");
			if (receiver_address == null || receiver_address.trim().length() == 0) {
				errorMsgs.put("receiver_address", "地址請勿留白");
			}

			String receiver_phone = req.getParameter("receiver_phone");
			if (receiver_phone == null || receiver_phone.trim().length() == 0) {

				errorMsgs.put("receiver_phone", "電話請勿留白");
			}

			Group_Buy_OrderVO group_buy_orderVO = new Group_Buy_OrderVO();

			group_buy_orderVO.setGbitem_id(gbitem_id);
			group_buy_orderVO.setGb_id(gb_id);
			group_buy_orderVO.setGbitem_amount(gbitem_amount);
			group_buy_orderVO.setGboriginal_price(gboriginal_price);
			group_buy_orderVO.setGb_endprice(gb_endprice);
			group_buy_orderVO.setGborder_paying(gborder_paying);
			group_buy_orderVO.setGborder_send(gborder_send);
			group_buy_orderVO.setGborder_status(gborder_status);
			group_buy_orderVO.setGborder_other(gborder_other);
			group_buy_orderVO.setTracking_num(tracking_num);
			group_buy_orderVO.setReceiver_name(receiver_name);
			group_buy_orderVO.setReceiver_address(receiver_address);
			group_buy_orderVO.setReceiver_phone(receiver_phone);
			group_buy_orderVO.setPickup_time(pickup_time);
			group_buy_orderVO.setGborder_date(gborder_date);
			group_buy_orderVO.setGborder_id(gborder_id);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("group_buy_orderVO", group_buy_orderVO); // 含有輸入格式錯誤的VO物件,也存入req
				RequestDispatcher fail = req.getRequestDispatcher("/backend/group_buy_order/updateGroup_Buy_Order.jsp");
				fail.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			Group_Buy_OrderService group_buy_OrderSvc = new Group_Buy_OrderService();
			group_buy_orderVO = group_buy_OrderSvc.updateGroup_Buy_Order(gborder_id, gbitem_id, gb_id, gbitem_amount,
					gboriginal_price, gb_endprice, gborder_paying, gborder_send, gborder_status, gborder_other,
					tracking_num, receiver_name, receiver_address, receiver_phone, gborder_date, pickup_time);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/

			req.setAttribute("group_buy_orderVO", group_buy_orderVO);

			String url = "/backend/group_buy_order/after_updateGroup_Buy_Order.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		if ("insert_NewOrder".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			
			String address = req.getParameter("address");
			String zipcode = req.getParameter("zipcode");
			String city = req.getParameter("city");
			String dist = req.getParameter("dist");
			
			System.out.println(zipcode+city+dist+address);
			
			
			Integer gborder_id = 0;

			Integer gbitem_id = Integer.valueOf(req.getParameter("gbitem_id").trim());

			// 團購團編號
			Integer gb_id = Integer.valueOf(req.getParameter("gb_id").trim());

			// 抓折扣數量
			Integer gbitem_amount = Integer.valueOf(req.getParameter("gbitem_amount").trim());
			// 抓運算後原始價格
			Integer gboriginal_price = Integer.valueOf(req.getParameter("gboriginal_price").trim());
			// 抓折扣後運算價格
			Integer gb_endprice = Integer.valueOf(req.getParameter("gb_endprice").trim());
			// 資料庫自主生成時間
			java.sql.Timestamp gborder_date = null;

//			Integer gborder_paying = Integer.valueOf(req.getParameter("gborder_paying").trim());
			Integer gborder_paying = null;
			try {
				gborder_paying = Integer.valueOf(req.getParameter("gborder_paying").trim());
				;

			} catch (Exception e) {

				errorMsgs.put("gborder_paying", "請選擇付款方式");
			}

			Integer gborder_send = null;
			try {
				gborder_send = Integer.valueOf(req.getParameter("gborder_send").trim());
			} catch (Exception e) {

				errorMsgs.put("gborder_send", "請輸入貨運方式");
			}

			Integer gborder_status = Integer.valueOf(req.getParameter("gborder_status").trim());
			java.sql.Timestamp pickup_time = null;
			String gborder_other = req.getParameter("gborder_other");

			String receiver_name = req.getParameter("receiver_name");
			if (receiver_name == null || receiver_name.trim().length() == 0) {

				errorMsgs.put("receiver_name", "收件人請勿留白");
			}


			
			String receiver_address = req.getParameter("receiver_address");
			if (receiver_address == null || receiver_address.trim().length() == 0) {

				errorMsgs.put("receiver_phone", "電話請勿留白");
			}

		
			String receiver_phone = req.getParameter("receiver_phone");
			if (receiver_phone == null || receiver_phone.trim().length() == 0) {

				errorMsgs.put("receiver_phone", "電話請勿留白");
			}

			Group_Buy_OrderVO group_buy_orderVO = new Group_Buy_OrderVO();

			group_buy_orderVO.setGbitem_id(gbitem_id);
			group_buy_orderVO.setGb_id(gb_id);
			group_buy_orderVO.setGbitem_amount(gbitem_amount);
			group_buy_orderVO.setGboriginal_price(gboriginal_price);
			group_buy_orderVO.setGb_endprice(gb_endprice);
			group_buy_orderVO.setGborder_paying(gborder_paying);
			group_buy_orderVO.setGborder_send(gborder_send);
			group_buy_orderVO.setGborder_status(gborder_status);
			group_buy_orderVO.setGborder_other(gborder_other);
//			group_buy_orderVO.setTracking_num(tracking_num);
			group_buy_orderVO.setReceiver_name(receiver_name);
			group_buy_orderVO.setReceiver_address(receiver_address);
			group_buy_orderVO.setReceiver_phone(receiver_phone);
			group_buy_orderVO.setPickup_time(pickup_time);
			group_buy_orderVO.setGborder_date(gborder_date);
			group_buy_orderVO.setGborder_id(gborder_id);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("group_buy_orderVO", group_buy_orderVO); // 含有輸入格式錯誤req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/group_buy_order/addGroup_Buy_Order.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始新增資料 ***************************************/

			Group_Buy_OrderService group_buy_OrderSvc = new Group_Buy_OrderService();
			group_buy_orderVO = group_buy_OrderSvc.addGroup_Buy_Order_User(gbitem_id, gb_id, gbitem_amount,
					gboriginal_price, gb_endprice, gborder_paying, gborder_send, gborder_status, gborder_other,
					receiver_name, receiver_address, receiver_phone, gborder_date, gborder_id);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			Integer gb_status = 3;
			/*************************** 2.開始修改資料 *****************************************/

			Group_BuyService group_buySvc = new Group_BuyService();
			Group_BuyVO group_buyVO = group_buySvc.updateGroup_Buy_GBStatus(gb_id, gb_status);

			req.setAttribute("group_buy_orderVO", group_buy_orderVO);

			String url = "/frontend/group_buy_order/listOneGroup_Buy_Order_byMaster.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		// =============================來自團購團結帳====================================//
		if ("group_buy_goOrder".equals(action)) {

			System.out.println("進入團購訂單");

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//		
			Integer gbitem_id = Integer.valueOf(req.getParameter("gbitem_id"));
			Integer gb_id = Integer.valueOf(req.getParameter("gb_id"));
//		


			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

			Group_Buy_OrderVO group_buy_orderVO = new Group_Buy_OrderVO();

			
			
			Group_Buy_OrderService Group_Buy_OrderService = new Group_Buy_OrderService();
			group_buy_orderVO = Group_Buy_OrderService.getOneEmp(gb_id);
			
			Group_BuyService group_buySvc = new Group_BuyService();
			Group_BuyVO group_buyVO = group_buySvc.getOneGroup_Buy(gb_id);
			System.out.print(group_buyVO);
			
			if(group_buy_orderVO!=null) {
				
				Group_Buy_OrderService group_buy_OrderSvc = new Group_Buy_OrderService();
				group_buy_orderVO = group_buy_OrderSvc.getOneEmp(gb_id);
				
				
				req.setAttribute("group_buy_orderVO", group_buy_orderVO);
				String url = "/frontend/group_buy_order/listOneGroup_Buy_Order_byMaster.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				return;
			}
			group_buy_orderVO = new Group_Buy_OrderVO();
			group_buy_orderVO.setGb_id(gb_id);
			group_buy_orderVO.setGbitem_id(gbitem_id);
			
			req.setAttribute("group_buy_orderVO", group_buy_orderVO);
			req.setAttribute("group_buyVO", group_buyVO);
			String url = "/frontend/group_buy_order/addGroup_Buy_Order.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);

		}

//

	}

}
