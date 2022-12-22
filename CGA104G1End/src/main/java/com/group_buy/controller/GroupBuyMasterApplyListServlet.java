package com.group_buy.controller;

import java.io.IOException;
import java.util.Iterator;
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

import com.discount.model.DiscountService;
import com.group_buy.model.Group_BuyService;
import com.group_buy.model.Group_BuyVO;
import com.group_buy_item.model.Group_Buy_ItemService;
import com.group_buy_item.model.Group_Buy_ItemVO;
import com.mem.model.MemVO;
import com.news.controller.newsServlet;

@WebServlet("/GroupBuyMasterApplyListServlet")
public class GroupBuyMasterApplyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GroupBuyMasterApplyListServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("confirmGroupBuy".equals(action)) { // 來自chosegroupbuydiscount.jsp "確認開團"
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			// 取得會員編號
//		MemVO memVO = (MemVO) req.getSession().getAttribute("memVO");
//		Integer mem_id = memVO.getMem_id();

			
			Integer discount_price = null;
			try {
				discount_price = Integer.valueOf(req.getParameter("discount_price").trim());
			} catch (Exception e) {
				errorMsgs.put("discount_price", "團購折扣格式不正確");
			}
			
////			Integer gb_id = null;
////			try {
////				gb_id = Integer.valueOf(req.getParameter("gb_id").trim());
////				if (gb_id == null) {
////					errorMsgs.put("gb_id", "團購團編號': 請勿空白");
////				}
////			} catch (NumberFormatException e1) {
////				errorMsgs.put("gb_id", "團購團號請填數字.");
////				e1.printStackTrace();
//			}
			Integer mem_id = null;
			try {
				mem_id = Integer.valueOf(req.getParameter("mem_id").trim());
				if (mem_id == null) {
					errorMsgs.put("gbitem_id", "團購主會員編號': 請勿空白");
				}
			} catch (NumberFormatException e1) {
				errorMsgs.put("mem_id", "團購主會員編號請填數字.");
				e1.printStackTrace();
			}
			
			Integer gbitem_id = null;
			try {
				gbitem_id = Integer.valueOf(req.getParameter("gbitem_id").trim());
				if (gbitem_id == null) {
					errorMsgs.put("團購商品編號:"," 請勿空白");
				}
			} catch (NumberFormatException e1) {
				errorMsgs.put("團購商品編號","請填數字.");
				e1.printStackTrace();
			}
			
			Integer gb_min = null;

			try {
				gb_min = Integer.valueOf(req.getParameter("gb_min").trim());
				if (gb_min == null) {
					errorMsgs.put("團購人數低標: ","請勿空白");
				}
			} catch (NumberFormatException e) {
				errorMsgs.put("團購人數低標","請填數字");
				e.printStackTrace();
			}

			Integer gb_amount = null;

			try {
				gb_amount = Integer.valueOf(req.getParameter("gb_amount"));
				if (gb_amount == null) {
					errorMsgs.put("目前參團人數: ","請勿空白");
				}
			} catch (NumberFormatException e) {
				errorMsgs.put("目前參團人數","請填數字");
				e.printStackTrace();
			}
			
			java.sql.Timestamp gbstart_date = null;

			try {
				gbstart_date = java.sql.Timestamp.valueOf(req.getParameter("gbstart_date").trim());
			} catch (IllegalArgumentException e) {
				gbstart_date = new java.sql.Timestamp(System.currentTimeMillis());
				e.printStackTrace();
				errorMsgs.put("團購開始","請輸入日期!");
			}
			java.sql.Timestamp gbend_date = null;
			
			try {
				gbend_date = java.sql.Timestamp.valueOf(req.getParameter("gbend_date").trim());
			} catch (IllegalArgumentException e) {
				gbend_date = new java.sql.Timestamp(System.currentTimeMillis());
				e.printStackTrace();
				errorMsgs.put("團購結束","請輸入日期!");
			}

			Integer gb_status = null;

			try {
				gb_status = Integer.valueOf(req.getParameter("gb_status"));
				if (gb_status == null) {
					errorMsgs.put("團購狀態: ","請勿空白");
				}
			} catch (NumberFormatException e) {
				errorMsgs.put("團購狀態","請填數字");
				e.printStackTrace();
			}
			
			

			Integer gbitem_price = null;
			try {
				gbitem_price = Integer.valueOf(req.getParameter("gbitem_price").trim());
				System.out.println(gbitem_price);
				if (gbitem_price == null) {
					errorMsgs.put("團購商品價格:", " 請勿空白");
				}
			} catch (NumberFormatException e1) {
				errorMsgs.put("團購商品價格", "請填數字.");
				e1.printStackTrace();
			}
			

			Integer gb_price = null;

			try {
				gb_price = Integer.valueOf(req.getParameter("gb_price"));
				if (gb_price == null) {
					errorMsgs.put("gb_price", "團購價格: 請勿空白");
				}
			} catch (NumberFormatException e) {
				errorMsgs.put("gb_price", "團購價格請填數字");
				e.printStackTrace();
			}
			
			
			String gb_name = req.getParameter("gb_name");
			
//			String gbitemNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]$";
			if (gb_name == null || gb_name.trim().length() == 0) {
				errorMsgs.put("團購名稱: ","請勿空白");
			} 
			Group_BuyVO group_BuyVO = new Group_BuyVO();
			group_BuyVO.setMem_id(mem_id);
			group_BuyVO.setGbitem_id(gbitem_id);
			group_BuyVO.setGb_min(gb_min);
			group_BuyVO.setGb_amount(gb_amount);
			group_BuyVO.setGbstart_date(gbstart_date);
			group_BuyVO.setGbend_date(gbend_date);
			group_BuyVO.setGb_status(gb_status);
			group_BuyVO.setGb_price(gb_price);
			group_BuyVO.setGb_name(gb_name);
			/******************************** 先查詢是否參團過 **************************/
			Group_BuyService group_BuyService = new Group_BuyService();
			Group_BuyVO group_BuyVO2 = group_BuyService.CheckByMemID(mem_id);
//			/****************************** 如果有資料跳回新增團購團 ***********************************/
			if (group_BuyVO2 != null) {
//				errorMsgs.put("請勿重新整理", "一個會員只能開一個團購團");
				List<Group_BuyVO> list = group_BuyService.joinGBIGetAllWhereMemID(mem_id);
				req.setAttribute("list", list);
				String url = "/frontend/groupBuy/mygroupbuyapplylist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			}
			
			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/frontend/groupBuy/chosegroupbuydiscount.jsp");
//				failureView.forward(req, res);
//				return;// 程式中斷
//			}
			/*************************** 2.開始修改資料 *****************************************/
//			Group_BuyVO group_BuyVO = new Group_BuyVO();
//			Group_BuyService group_BuyService = new Group_BuyService();
			
			
			group_BuyVO = group_BuyService.addGroup_Buy(mem_id, gbitem_id, gb_min, gb_amount, gbstart_date, gbend_date, gb_status, gb_price, gb_name);
			group_BuyVO = group_BuyService.getLastGroup_Buy();
			
			Integer gb_id = group_BuyVO.getGb_id();
			
			gb_price = (int) (Math.round(discount_price * gbitem_price * 0.01));
			group_BuyVO = group_BuyService.updateGroup_Buy_GBPrice(gb_id, gb_price);
			List<Group_BuyVO> list = group_BuyService.joinGBIGetAllWhereMemID(mem_id);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("list", list);
			RequestDispatcher successView = req.getRequestDispatcher("/frontend/groupBuy/mygroupbuyapplylist.jsp");
			successView.forward(req, res);
		}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		if ("listAllMyGroupBuy".equals(action)) { // 來自需要查看自己團購團狀態的需求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			HttpSession session = req.getSession();
			// 取得會員編號
//			MemVO memVO = (MemVO) req.getSession().getAttribute("memVO");
//			Integer mem_id = memVO.getMem_id();
//				Integer mem_id = null;
//				try {
//					mem_id = Integer.valueOf(req.getParameter("mem_id").trim());
//					if (mem_id == null) {
//						errorMsgs.put("gbitem_id", "團購主會員編號': 請勿空白");
//					}
//				} catch (NumberFormatException e1) {
//					errorMsgs.put("mem_id", "團購主會員編號請填數字.");
//					e1.printStackTrace();
//				}
				
				
				Integer mem_id = (Integer) session.getAttribute("mem_id");
			System.out.println(mem_id);
				//尋找mem_id為團主或是團員
				Group_BuyService group_buySvc = new Group_BuyService();
				Group_BuyVO group_buyVO = new Group_BuyVO();
				
//				Group_BuyVO group_BuyVO2 = group_buySvc.CheckByMemID(mem_id);
//				if (group_BuyVO2 != null) {
//					System.out.println("已經開團過了");
//					req.setAttribute("Group_BuyVO", group_BuyVO);
					List<Group_BuyVO> list = group_buySvc.joinGBIGetAllWhereMemID(mem_id);
					req.setAttribute("list", list);
					String url = "/frontend/groupBuy/mygroupbuyapplylist.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					return;
//				}else {
//					req.setAttribute("mem_id", mem_id);                             
//					String url = req.getContextPath()+"/Group_JoinServlet?action=getOne_Display_ByMem";
//					RequestDispatcher successView = req.getRequestDispatcher(url);
//					successView.forward(req, res);
//				}
				
				
				
				
				
				
				
				
					
								
						
//						if (!errorMsgs.isEmpty()) {
//							req.setAttribute("Group_BuyVO", group_buyVO);
//							RequestDispatcher failureView = req
//									.getRequestDispatcher("/backend/group_buy/listallgroupbuy.html");
//							failureView.forward(req, res);
//							return;
//						}
				
				
				
				
				
				
				
				
				
//			/*************************** 2.開始修改資料 *****************************************/
//			Group_BuyVO group_BuyVO = new Group_BuyVO();
//			Group_BuyService group_BuyService = new Group_BuyService();
//			List<Group_BuyVO> list = group_BuyService.joinGBIGetAllWhereMemID(mem_id);
//		
//			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
//			req.setAttribute("list", list);
//			RequestDispatcher successView = req.getRequestDispatcher("/frontend/groupBuy/mygroupbuyapplylist.jsp");
//			successView.forward(req, res);
			
		}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		if ("transferT2GroupBuyOrder".equals(action)) { 
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// 取得會員編號
//			MemVO memVO = (MemVO) req.getSession().getAttribute("memVO");
//			Integer mem_id = memVO.getMem_id();

				Integer mem_id = null;
				try {
					mem_id = Integer.valueOf(req.getParameter("mem_id").trim());
					if (mem_id == null) {
						errorMsgs.put("gbitem_id", "團購主會員編號': 請勿空白");
					}
				} catch (NumberFormatException e1) {
					errorMsgs.put("mem_id", "團購主會員編號請填數字.");
					e1.printStackTrace();
				}
				
				Integer gbitem_id = null;
				try {
					gbitem_id = Integer.valueOf(req.getParameter("gbitem_id").trim());
					if (gbitem_id == null) {
						errorMsgs.put("gbitem_id", "團購商品編號': 請勿空白");
					}
				} catch (NumberFormatException e1) {
					errorMsgs.put("gbitem_id", "團購商品編號請填數字.");
					e1.printStackTrace();
				}
			
			/*************************** 2.開始修改資料 *****************************************/
				//查詢新增團購訂單所需要的欄位
			Group_BuyVO group_BuyVO = new Group_BuyVO();
			Group_BuyService group_BuyService = new Group_BuyService();
			List<Group_BuyVO> list = group_BuyService.getAllGroupBuyApplyListByMemID(mem_id);
			
			Group_Buy_ItemService group_Buy_ItemService = new Group_Buy_ItemService();
			Group_Buy_ItemVO group_Buy_ItemVO = group_Buy_ItemService.getOneGbi(gbitem_id);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("group_BuyVO", list);
			req.setAttribute("Group_Buy_ItemVO", group_Buy_ItemVO);
			req.setAttribute("action", "getOne_Discount_For_Display");
			
			//轉交團購訂單的Servlet
			RequestDispatcher successView = req.getRequestDispatcher("/backend/discount/DiscountServlet");
			successView.forward(req, res);
		}
		
	}

}
