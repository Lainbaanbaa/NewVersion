package com.group_buy.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group_buy.model.Group_BuyService;
import com.group_buy.model.Group_BuyVO;

@WebServlet("/immediatelyRefresh")
public class immediatelyRefresh extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public immediatelyRefresh() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Group_BuyVO> list = new ArrayList<>();
		Group_BuyService group_BuyService = new Group_BuyService();
		list = group_BuyService.getAll2InProgress();
//		System.out.println("我是list"+list);
		for (Group_BuyVO Group_BuyVO : list) {
//			System.out.println("我是Group_BuyVO.getGb_id()"+Group_BuyVO.getGb_id());
			group_BuyService.updateGroup_Buy_GBStatus(Group_BuyVO.getGb_id(), 1);
//			System.out.println(1);
		}
			
			
			List<Group_BuyVO> list2 = new ArrayList<>();
			Group_BuyService group_BuyService2 = new Group_BuyService();
				list2 = group_BuyService2.getAll2End();

				for (Group_BuyVO Group_BuyVO2 : list2) {
					// 判斷狀態
					 if (Group_BuyVO2.getGb_min() > Group_BuyVO2.getGb_amount()) {
						group_BuyService.updateGroup_Buy_GBStatus(Group_BuyVO2.getGb_id(), 8);
	//					System.out.print("送出狀態"+8);
					}else {
						group_BuyService.updateGroup_Buy_GBStatus(Group_BuyVO2.getGb_id(), 3);
					};
				}
				
				
		/*************************** 準備轉交(Send the Success view) *************/
//				req.setAttribute("Group_BuyVO", group_BuyVO);
//				String url = "/backend/group_buy/listOneGroupBuy.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
				
				
				
				
	}

}
