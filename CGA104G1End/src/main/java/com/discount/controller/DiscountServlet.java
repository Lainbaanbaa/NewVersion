package com.discount.controller;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.discount.model.*;

@WebServlet("/backend/discount/DiscountServlet")
public class DiscountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DiscountServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		/****************************************
		 * 由團購商品選擇後送來
		 *****************************************/
		if ("getOne_Discount_For_Display".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer gbitem_id = Integer.valueOf(req.getParameter("gbitem_id"));
			/*
			 * =============================================================================
			 * ==================
			 */
			String gbitem_name = req.getParameter("gbitem_name");
			String gbitem_content = req.getParameter("gbitem_content");
			Integer gbitem_price = Integer.valueOf(req.getParameter("gbitem_price"));
			Integer gbitem_status = Integer.valueOf(req.getParameter("gbitem_status"));
			java.sql.Date gbitem_startdate = java.sql.Date.valueOf(req.getParameter("gbitem_startdate").trim());
			java.sql.Date gbitem_enddate = java.sql.Date.valueOf(req.getParameter("gbitem_enddate").trim());

			DiscountService discountSvc = new DiscountService();
			List<DiscountVO> discountVO = discountSvc.getoneGbitem_id(gbitem_id);
			System.out.println(req.getParameter("gbitem_id"));
			if (discountVO == null) {
				errorMsgs.put("gbitem_id", "沒有此商品的優惠資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/discount/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			session.setAttribute("DiscountVO", discountVO); // 資料庫取出的empVO物件,存入req
			session.setAttribute("gbitem_id", gbitem_id);
			//================================================團購商品假資料=========================
			session.setAttribute("gbitem_name", gbitem_name);
			session.setAttribute("gbitem_content", gbitem_content);
			session.setAttribute("gbitem_price", gbitem_price);
			session.setAttribute("gbitem_status", gbitem_status);
			session.setAttribute("gbitem_startdate", gbitem_startdate);
			session.setAttribute("gbitem_enddate", gbitem_enddate);

			String url = "/frontend/discount/choose.disvount.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);

		}

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("discount_id");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入團購折扣編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/discount/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer discount_id = null;
			try {
				discount_id = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("團購折扣格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/discount/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			DiscountService discountService = new DiscountService();
			DiscountVO discountVO = discountService.getoneDiscount(discount_id);
			if (discountVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/discount/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("DiscountVO", discountVO); // 資料庫取出的empVO物件,存入req
			String url = "/backend/discount/listOneDiscount.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);

		}
		/**************************************
		 * 從
		 ***************************************/
		if ("getOne_For_Display_ByUser".equals(action)) { // 來自select_page.jsp的請求



			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer discount_id = Integer.valueOf(req.getParameter("discount_id"));


			/*************************** 2.開始查詢資料 *****************************************/
			DiscountService discountService = new DiscountService();
			DiscountVO discountVO = discountService.getoneDiscount(discount_id);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			session.setAttribute("DiscountVO", discountVO); // 資料庫取出的VO物件,存入session
			String url = "/frontend/group_buy_order/addGroup_Buy_Order.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
			successView.forward(req, res);
		}
//////////////////////////////////////////////////////////////////////////////////////////////////////////
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs1 = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs1);

			/*************************** 1.接收請求參數 ****************************************/
			Integer discount_id = Integer.valueOf(req.getParameter("discount_id"));

			/*************************** 2.開始查詢資料 ****************************************/
			DiscountService disSvc = new DiscountService();
			DiscountVO DiscountVO = disSvc.getoneDiscount(discount_id);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("DiscountVO", DiscountVO);
			String url = "/backend/discount/update_discount_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer discount_id = Integer.valueOf(req.getParameter("discount_id").trim());

			Integer gbitem_id = null;
			try {
				gbitem_id = Integer.valueOf(req.getParameter("gbitem_id").trim());
				if (gbitem_id == null || !errorMsgs.isEmpty()) {
					errorMsgs.add("團購商品編號': 請勿空白");
				}
			} catch (NumberFormatException e1) {
				gbitem_id = 0;
				errorMsgs.add("團購商品編號請填數字.");
				e1.printStackTrace();
			}

			Integer discount_minamount = null;
			try {
				discount_minamount = Integer.valueOf(req.getParameter("discount_minamount").trim());
				if (discount_minamount == null || !errorMsgs.isEmpty()) {
					errorMsgs.add("商品數量下限: 請勿空白");
				}
			} catch (NumberFormatException e1) {
				discount_minamount = 0;
				errorMsgs.add("商品數量下限請填數字.");
				e1.printStackTrace();
			}

			Integer discount_maxamount = null;
			try {
				discount_maxamount = Integer.valueOf(req.getParameter("discount_maxamount").trim());
				if (discount_maxamount == null || !errorMsgs.isEmpty()) {
					errorMsgs.add("商品數量上限: 請勿空白");
				}
			} catch (NumberFormatException e1) {
				discount_maxamount = 0;
				errorMsgs.add("商品數量上限請填數字.");
				e1.printStackTrace();
			}

			Integer discount_price = null;
			try {
				discount_price = Integer.valueOf(req.getParameter("discount_price").trim());
				if (discount_price == null || !errorMsgs.isEmpty()) {
					errorMsgs.add("折扣價格: 請勿空白");
				}
			} catch (NumberFormatException e1) {
				discount_price = 0;
				errorMsgs.add("折扣價格請填數字.");
				e1.printStackTrace();
			}

			String discount_nar = req.getParameter("discount_nar");
			if (discount_nar == null || discount_nar.trim().length() == 0) {
				errorMsgs.add("折扣說明: 請勿空白");
			}

			DiscountVO DiscountVO = new DiscountVO();
			DiscountVO.setDiscount_id(discount_id);
			DiscountVO.setGbitem_id(gbitem_id);
			DiscountVO.setDiscount_minamount(discount_minamount);
			DiscountVO.setDiscount_maxamount(discount_maxamount);
			DiscountVO.setDiscount_price(discount_price);
			DiscountVO.setDiscount_nar(discount_nar);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("DiscountVO", DiscountVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/discount/update_discount_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			/*************************** 2.開始修改資料 *****************************************/
			DiscountService discountService = new DiscountService();
			DiscountVO = discountService.updateDiscount(discount_id, gbitem_id, discount_minamount, discount_maxamount,
					discount_price, discount_nar);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("DiscountVO", DiscountVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/backend/discount/listOneDiscount.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

		if ("insert".equals(action)) { // 來自addDiscount.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/


			Integer gbitem_id = null;
			String gbitemIdRange = "^[1-9]\\d*$";
			try {
				gbitem_id = Integer.valueOf(req.getParameter("gbitem_id").trim());
				if (gbitem_id == null || !errorMsgs.isEmpty()) {
					gbitem_id = 1;
					errorMsgs.add("團購商品編號': 請勿空白");
				}else if (!gbitem_id.toString().trim().matches(gbitemIdRange)) {
					errorMsgs.add("團購商品編號請填非0、小數、的數字.");
				}
			} catch (NumberFormatException e1) {
				errorMsgs.add("團購商品編號請填非小數數字.");
				e1.printStackTrace();
			}

			Integer discount_minamount = null;
			try {
				discount_minamount = Integer.valueOf(req.getParameter("discount_minamount").trim());
				if (discount_minamount == null || !errorMsgs.isEmpty()) {
					errorMsgs.add("商品數量下限: 請勿空白");
				}
			} catch (NumberFormatException e1) {
				discount_minamount = 0;
				errorMsgs.add("商品數量下限請填非小數數字.");
				e1.printStackTrace();
			}

			Integer discount_maxamount = null;
			try {
				discount_maxamount = Integer.valueOf(req.getParameter("discount_maxamount").trim());
				if (discount_maxamount == null || !errorMsgs.isEmpty()) {
					errorMsgs.add("商品數量上限: 請勿空白");
				}
			} catch (NumberFormatException e1) {
				discount_maxamount = 0;
				errorMsgs.add("商品數量上限請填非小數數字.");
				e1.printStackTrace();
			}

			Integer discount_price = null;
			try {
				discount_price = Integer.valueOf(req.getParameter("discount_price").trim());
				if (discount_price == null || !errorMsgs.isEmpty()) {
					errorMsgs.add("折扣價格上限: 請勿空白");
				}
			} catch (NumberFormatException e1) {
				discount_price = 0;
				errorMsgs.add("折扣價格請填非小數數字.");
				e1.printStackTrace();
			}

			String discount_nar = req.getParameter("discount_nar");
			if (discount_nar == null || discount_nar.trim().length() == 0) {
				errorMsgs.add("折扣說明: 請勿空白");
			}

			DiscountVO DiscountVO = new DiscountVO();
			DiscountVO.setGbitem_id(gbitem_id);
			DiscountVO.setDiscount_minamount(discount_minamount);
			DiscountVO.setDiscount_maxamount(discount_maxamount);
			DiscountVO.setDiscount_price(discount_price);
			DiscountVO.setDiscount_nar(discount_nar);



			if (!errorMsgs.isEmpty()) {
				req.setAttribute("DiscountVO", DiscountVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/discount/addDiscount.jsp");
				failureView.forward(req, res);
				return; //程式中斷
			}
			/***************************2.開始新增資料***************************************/

			DiscountService discountService = new DiscountService();
			DiscountVO = discountService.addDiscount(gbitem_id, discount_minamount, discount_maxamount,
					discount_price, discount_nar);

			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			String url = "/backend/discount/listAllDiscount.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs1 = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs1);

			/*************************** 1.接收請求參數 ***************************************/
			Integer discount_id = Integer.valueOf(req.getParameter("discount_id"));

			/*************************** 2.開始刪除資料 ***************************************/
			DiscountService disSvc = new DiscountService();
			disSvc.deleteDiscount(discount_id);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/backend/discount/listAllDiscount.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}

	}

}
