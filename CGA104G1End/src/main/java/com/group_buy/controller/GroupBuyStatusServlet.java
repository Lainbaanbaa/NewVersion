package com.group_buy.controller;

import com.group_buy.model.Group_BuyService;
import com.group_buy.model.Group_BuyVO;
import com.item.model.ItemService;
import com.item.model.ItemVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@WebServlet(name = "GroupBuyStatusServlet", urlPatterns = { "/GroupBuyStatusServlet" }, loadOnStartup = 2)
public class GroupBuyStatusServlet extends HttpServlet {

	Timer timer = new Timer();
	    Calendar cal = Calendar.getInstance();

	    @Override
	    public void init() throws ServletException {
	        timer.scheduleAtFixedRate(getAllInProgress, cal.getTime(), 10000);
	        timer.scheduleAtFixedRate(getAll2End, cal.getTime(), 10000);
//	        System.out.println("i am GroupBuyStatusServlet");
	    }
	    //將所有gb_status=0的團購團，在現在時間大於開團時間後，更新狀態為1
	    TimerTask getAllInProgress = new TimerTask() {
	    	
	        @Override
	        public void run() {
//	        	 System.out.println("我是run方法getAllInProgress");
	        	List<Group_BuyVO> list = new ArrayList<>();
				Group_BuyService group_BuyService = new Group_BuyService();
				list = group_BuyService.getAll2InProgress();
//				System.out.println("我是list"+list);
				for (Group_BuyVO Group_BuyVO : list) {
//					System.out.println("我是Group_BuyVO.getGb_id()"+Group_BuyVO.getGb_id());
					group_BuyService.updateGroup_Buy_GBStatus(Group_BuyVO.getGb_id(), 1);
//					System.out.println(1);
				}      
	        }
	    };
	    //將所有gb_status=1的團購團，在現在時間大於截止時間後，且訂購數量小於標準，更新狀態為8
	    TimerTask getAll2End = new TimerTask() {
	    	
	    	@Override
	    	public void run() {
//	    		System.out.println("我是run方法getAll2End");
	    		List<Group_BuyVO> list = new ArrayList<>();
				Group_BuyService group_BuyService = new Group_BuyService();

				// 拿到全部團購團
				list = group_BuyService.getAll2End();

				for (Group_BuyVO Group_BuyVO : list) {
					// 判斷狀態
					 if (Group_BuyVO.getGb_min() > Group_BuyVO.getGb_amount()) {
						group_BuyService.updateGroup_Buy_GBStatus(Group_BuyVO.getGb_id(), 8);
//						System.out.print("送出狀態"+8);
					}else {
						group_BuyService.updateGroup_Buy_GBStatus(Group_BuyVO.getGb_id(), 3);
					}
					;
				}
	    	}
	    };

	    @Override
	    public void destroy() {
	        timer.cancel();
	    }
}
