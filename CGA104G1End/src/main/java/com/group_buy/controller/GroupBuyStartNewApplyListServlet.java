package com.group_buy.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.group_buy.model.Group_BuyVO;
import com.mem.model.MemVO;

@WebServlet("/GroupBuyStartNewApplyListServlet")
public class GroupBuyStartNewApplyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GroupBuyStartNewApplyListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
	
		
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		req.setAttribute("errorMsgs", errorMsgs);
		HttpSession session = req.getSession();
		// 取得會員編號
//					MemVO memVO = (MemVO)req.getSession().getAttribute("memVO");
//					Integer mem_id = memVO.getMem_id();
//					
//					System.out.println(mem_id);
					
					Integer mem_id = null;
					try {
						mem_id = (Integer) session.getAttribute("mem_id");
//						System.out.println(mem_id);
						if (mem_id == null) {
							errorMsgs.put("gbitem_id","團購主會員編號': 請勿空白");
						}
					} catch (NumberFormatException e1) {
						errorMsgs.put("mem_id","團購主會員編號請填數字.");
						e1.printStackTrace();
					}
					
//					Integer gbitem_id = null;
//					try {
//						gbitem_id = Integer.valueOf(req.getParameter("gbitem_id").trim());
////						System.out.println(gbitem_id);
//						if (gbitem_id == null) {
//							errorMsgs.put("gbitem_id","團購商品編號': 請勿空白");
//						}
//					} catch (NumberFormatException e1) {
//						errorMsgs.put("gbitem_id","團購商品編號請填數字.");
//						e1.printStackTrace();
//					}
					
					
					Group_BuyVO group_BuyVO = new Group_BuyVO();
					group_BuyVO.setMem_id(mem_id);
//					group_BuyVO.setGbitem_id(gbitem_id);

					if (!errorMsgs.isEmpty()) {
						req.setAttribute("Group_BuyVO", group_BuyVO);
						RequestDispatcher failureView = req.getRequestDispatcher("/frontend/groupBuy/listallgroupbuy.jsp");
						failureView.forward(req, res);
						return;
					}
					/*************************** 3.修改完成,準備轉交(Send the Success view) *************/	
					
					
					req.setAttribute("Group_BuyVO", group_BuyVO);
//					
					RequestDispatcher successView = req.getRequestDispatcher("/frontend/groupBuy/addgroupbuyapplylist.jsp");
					successView.forward(req, res);
	}

}
