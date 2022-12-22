package com.group_buy_item.controller;

import java.io.IOException;
import java.util.LinkedList;
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
@WebServlet("/Group_Buy_Item/groupBuyItem.do")
public class Group_Buy_ItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Group_Buy_ItemServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("gbitem_id");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入團購商品編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/group_Buy_Item/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer gbitem_id = null;
			try {
				gbitem_id = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("團購商品編號格式不正確");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/group_Buy_Item/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始查詢資料 *****************************************/
			Group_Buy_ItemService group_Buy_ItemService = new Group_Buy_ItemService();
			Group_Buy_ItemVO group_Buy_ItemVO = group_Buy_ItemService.getOneGbi(gbitem_id);
			if (group_Buy_ItemVO == null) {
				errorMsgs.add("查無資料");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/group_Buy_Item/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("Group_Buy_ItemVO", group_Buy_ItemVO);
			String url = "/backend/group_Buy_Item/listOneGroupBuyItem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if ("getOne_For_Update".equals(action)) { // 來自listAllGroupBuyItem.jsp的請求

			List<String> errorMsgs1 = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs1);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer gbitem_id = Integer.valueOf(req.getParameter("gbitem_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				Group_Buy_ItemService group_Buy_ItemService = new Group_Buy_ItemService();
				Group_Buy_ItemVO group_Buy_ItemVO = group_Buy_ItemService.getOneGbi(gbitem_id);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("Group_Buy_ItemVO", group_Buy_ItemVO);

				// 從資料庫讀取 groupBuyItemPictureVO 存入 list 中

				groupBuyItemPictureService gbipSvc = new groupBuyItemPictureService();
				List<groupBuyItemPictureVO> list2 = gbipSvc.getAllGroupBuyItemPictureByGbitemID(gbitem_id);

				//以下兩種移除null的方式
//				list2.removeAll(Collections.singleton(null));


//				List<groupBuyItemPictureVO> listWithoutNulls = list2.stream()
//						.filter(Objects::nonNull)
//						.collect(Collectors.toList());

				req.setAttribute("list2", list2);
//				System.out.println(list2);

				// 成功轉交 update_groupBuyItem_input.jsp
				String url = "/backend/group_Buy_Item/update_groupBuyItem_input.jsp";
				RequestDispatcher failView = req.getRequestDispatcher(url);
				failView.forward(req, res);
				// 其他可能的錯誤處理
			}  catch (IOException e) {
//				e.printStackTrace();
				String url = "/backend/group_Buy_Item/listAllGroupBuyItem.jsp";
				errorMsgs1.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}
		}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if ("update".equals(action)) { // 來自update_groupBuyItem_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//			Integer gbitem_id = Integer.valueOf(req.getParameter("gbitem_id").trim());
			Integer gbitem_id = null;
			try {
				gbitem_id = Integer.valueOf(req.getParameter("gbitem_id").trim());
				if (gbitem_id == null) {
					errorMsgs.add("團購商品編號': 請勿空白");
				}
			} catch (NumberFormatException e1) {
				errorMsgs.add("團購商品編號請填數字.");
				e1.printStackTrace();
			}


			String gbitem_name = req.getParameter("gbitem_name");

//			String gbitemNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]$";
			if (gbitem_name == null || gbitem_name.trim().length() == 0) {
				errorMsgs.add("團購商品名稱: 請勿空白");
			}
//			else if (!gbitem_name.trim().matches(gbitemNameReg)) {
//				errorMsgs.add("團購商品名稱: 只能是中、英文字母、數字和_ ");
//			}

			String gbitem_content = req.getParameter("gbitem_content");
			if (gbitem_content == null || gbitem_content.trim().length() == 0) {
				errorMsgs.add("團購商品內容請勿空白");
			}

			Integer gbitem_price = null;

			try {
				gbitem_price = Integer.valueOf(req.getParameter("gbitem_price").trim());
				if (gbitem_price == null) {
					errorMsgs.add("團購商品價格: 請勿空白");
				}
			} catch (NumberFormatException e) {
				errorMsgs.add("團購商品價格請填數字");
				e.printStackTrace();
			}

			Integer gbitem_status = null;

			try {
				gbitem_status = Integer.valueOf(req.getParameter("gbitem_status"));
				if (gbitem_status == null) {
					errorMsgs.add("團購商品狀態: 請勿空白");
				}
			} catch (NumberFormatException e) {
				errorMsgs.add("團購商品狀態請填數字");
				e.printStackTrace();
			}

			java.sql.Date gbitem_startdate = null;

			try {
				gbitem_startdate = java.sql.Date.valueOf(req.getParameter("gbitem_startdate").trim());
			} catch (IllegalArgumentException e) {
				gbitem_startdate = new java.sql.Date(System.currentTimeMillis());
				e.printStackTrace();
				errorMsgs.add("請輸入日期!");
			}

			java.sql.Date gbitem_enddate = null;

			try {
				gbitem_enddate = java.sql.Date.valueOf(req.getParameter("gbitem_enddate").trim());
			} catch (IllegalArgumentException e) {
				gbitem_enddate = new java.sql.Date(System.currentTimeMillis());
				e.printStackTrace();
				errorMsgs.add("請輸入日期!");
			}

			Integer gbitem_type = null;

			try {
				gbitem_type = Integer.valueOf(req.getParameter("gbitem_type"));
				if (gbitem_type == null) {
					errorMsgs.add("團購商品類別: 請勿空白");
				}
			} catch (NumberFormatException e) {
				errorMsgs.add("團購商品類別請填數字");
				e.printStackTrace();
			}

			Group_Buy_ItemVO group_Buy_ItemVO = new Group_Buy_ItemVO();
			group_Buy_ItemVO.setGbitem_id(gbitem_id);
			group_Buy_ItemVO.setGbitem_name(gbitem_name);
			group_Buy_ItemVO.setGbitem_content(gbitem_content);
			group_Buy_ItemVO.setGbitem_price(gbitem_price);
			group_Buy_ItemVO.setGbitem_status(gbitem_status);
			group_Buy_ItemVO.setGbitem_startdate(gbitem_startdate);
			group_Buy_ItemVO.setGbitem_enddate(gbitem_enddate);
			group_Buy_ItemVO.setGbitem_type(gbitem_type);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("Group_Buy_ItemVO", group_Buy_ItemVO);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/group_Buy_Item/update_groupBuyItem_input.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始修改資料 *****************************************/
			Group_Buy_ItemService group_Buy_ItemService = new Group_Buy_ItemService();
			group_Buy_ItemVO = group_Buy_ItemService.updateGroup_Buy_ItemVO(gbitem_id, gbitem_name, gbitem_content, gbitem_price,
					gbitem_status, gbitem_startdate, gbitem_enddate, gbitem_type);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("Group_Buy_ItemVO", group_Buy_ItemVO);
			String url = "/backend/group_Buy_Item/listOneGroupBuyItem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if ("insert".equals(action)) { // 來自addGroupBuyItem.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			String gbitem_name = req.getParameter("gbitem_name");
			if (gbitem_name == null || gbitem_name.trim().length() == 0) {
				errorMsgs.add("團購商品名稱: 請勿空白");
			}

			String gbitem_content = req.getParameter("gbitem_content");
			if (gbitem_content == null || gbitem_content.trim().length() == 0) {
				errorMsgs.add("團購商品內容請勿空白");
			}

			Integer gbitem_price = null;

			try {
				gbitem_price = Integer.valueOf(req.getParameter("gbitem_price").trim());
				if (gbitem_price == null) {
					errorMsgs.add("團購商品價格: 請勿空白");
				}
			} catch (NumberFormatException e) {
				errorMsgs.add("團購商品價格請填數字");
				e.printStackTrace();
			}

			Integer gbitem_status = null;

			try {
				gbitem_status = Integer.valueOf(req.getParameter("gbitem_status"));
				if (gbitem_status == null) {
					errorMsgs.add("團購商品狀態: 請勿空白");
				}
			} catch (NumberFormatException e) {
				errorMsgs.add("團購商品狀態請填數字");
				e.printStackTrace();
			}

			java.sql.Date gbitem_startdate = null;

			try {
				gbitem_startdate = java.sql.Date.valueOf(req.getParameter("gbitem_startdate").trim());
			} catch (IllegalArgumentException e) {
				gbitem_startdate = new java.sql.Date(System.currentTimeMillis());
				e.printStackTrace();
				errorMsgs.add("請輸入日期!");
			}

			java.sql.Date gbitem_enddate = null;

			try {
				gbitem_enddate = java.sql.Date.valueOf(req.getParameter("gbitem_enddate").trim());
			} catch (IllegalArgumentException e) {
				gbitem_enddate = new java.sql.Date(System.currentTimeMillis());
				e.printStackTrace();
				errorMsgs.add("請輸入日期!");
			}

			Integer gbitem_type = null;

			try {
				gbitem_type = Integer.valueOf(req.getParameter("gbitem_type"));
				if (gbitem_type == null) {
					errorMsgs.add("團購商品類別: 請勿空白");
				}
			} catch (NumberFormatException e) {
				errorMsgs.add("團購商品類別請填數字");
				e.printStackTrace();
			}

			Group_Buy_ItemVO gbiVO = new Group_Buy_ItemVO();
			gbiVO.setGbitem_name(gbitem_name);
			gbiVO.setGbitem_content(gbitem_content);
			gbiVO.setGbitem_price(gbitem_price);
			gbiVO.setGbitem_status(gbitem_status);
			gbiVO.setGbitem_startdate(gbitem_startdate);
			gbiVO.setGbitem_enddate(gbitem_enddate);
			gbiVO.setGbitem_type(gbitem_type);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("gbiVO", gbiVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/group_Buy_Item/addGroupBuyItem.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始修改資料 *****************************************/
			Group_Buy_ItemService group_Buy_ItemService = new Group_Buy_ItemService();
			gbiVO = group_Buy_ItemService.addGroup_Buy_ItemVO(gbitem_name, gbitem_content, gbitem_price,
					gbitem_status, gbitem_startdate, gbitem_enddate, gbitem_type);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("gbiVO", gbiVO);
			String url = "/backend/group_Buy_Item/listAllGroupBuyItem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數***************************************/
				Integer gbitem_id = Integer.valueOf(req.getParameter("gbitem_id"));

				/***************************2.開始刪除資料***************************************/
				Group_Buy_ItemService group_Buy_ItemService = new Group_Buy_ItemService();
				group_Buy_ItemService.deleteGbi(gbitem_id);

				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				String url = "/backend/group_Buy_Item/listAllGroupBuyItem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

		}


	}

}
