package com.group_buy_report.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp_effect.model.Emp_effectService;
import com.emp_effect.model.Emp_effectVO;
import com.group_buy_report.model.Group_Buy_ReportService;
import com.group_buy_report.model.Group_Buy_ReportVO;


@WebServlet("/Group_Buy_ReportServlet")
public class Group_Buy_ReportServlet extends HttpServlet{

	private static final long serialVersionUID = 7992456591865216670L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String str = req.getParameter("gbfrep_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.put("gbfrep_id","請輸入員工編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher fail= req
							.getRequestDispatcher("/backend/group_buy_report/select_page.jsp");
					fail.forward(req, res);
					return;
				}

				Integer gbfrep_id = null;
				try {
					gbfrep_id = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.put("gbfrep_id","員工編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher fail = req
							.getRequestDispatcher("/backend/group_buy_report/select_page.jsp");
					fail.forward(req, res);
					return;//程式中斷
				}

				/***************************2.開始查詢資料*****************************************/
				Group_Buy_ReportService GBRSvc = new Group_Buy_ReportService();
				Group_Buy_ReportVO Group_Buy_ReportVO = GBRSvc. getOneGroup_Buy_Report(gbfrep_id);
				if (gbfrep_id == null) {
					errorMsgs.put("gbfrep_id","查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher fail = req
							.getRequestDispatcher("/backend/group_buy_report/select_page.jsp");
					fail.forward(req, res);
					return;
				}
				req.setAttribute("gbfrep_id", gbfrep_id);
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("Group_Buy_ReportVO", Group_Buy_ReportVO);
				String url = "/backend/group_buy_report/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		if ("getOne_For_Update".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();

			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數****************************************/
			Integer gbfrep_id = Integer.valueOf(req.getParameter("gbfrep_id"));

				/***************************2.開始查詢資料****************************************/
				Group_Buy_ReportService GBRSvc = new Group_Buy_ReportService();
				Group_Buy_ReportVO Group_Buy_ReportVO = GBRSvc.getOneGroup_Buy_Report(gbfrep_id);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("Group_Buy_ReportVO", Group_Buy_ReportVO);
				String url = "/backend/group_buy_report/updateGroup_order_Report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
		}
		if ("update".equals(action)) {

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/


				Integer gborder_id = Integer.valueOf(req.getParameter("gborder_id").trim());
				if (gborder_id == null ) {
					errorMsgs.put("emp_id","員工編號: 請勿空白");
				}
				Integer mem_id = Integer.valueOf(req.getParameter("mem_id").trim());
				if (mem_id == null ) {
					errorMsgs.put("gbfrep_id","團購檢舉編號: 請勿空白");
				}
				String frep_content = req.getParameter("frep_content").trim();
				if (frep_content == null || frep_content.trim().length() == 0) {
					errorMsgs.put("frep_content", "團購檢舉內容 請勿空白");
				}
//				Integer frep_status = Integer.valueOf(req.getParameter("frep_status").trim());
//				if (frep_status == null ) {
//					errorMsgs.put("gbfrep_id","團購檢舉編號: 請勿空白");
//				}
				Integer frep_result = Integer.valueOf(req.getParameter("frep_result").trim());
				if (frep_result == null ) {
					errorMsgs.put("gbfrep_id","團購檢舉編號: 請勿空白");
				}
				Integer emp_id = Integer.valueOf(req.getParameter("emp_id").trim());
				if (emp_id == null ) {
					errorMsgs.put("emp_id","團購檢舉編號: 請勿空白");
				}
				Integer gbfrep_id = Integer.valueOf(req.getParameter("gbfrep_id").trim());
				if (gbfrep_id == null ) {
					errorMsgs.put("gbfrep_id","團購檢舉編號: 請勿空白");
				}

				Group_Buy_ReportVO Group_Buy_ReportVO = new Group_Buy_ReportVO();
				Group_Buy_ReportService GBRSvc = new Group_Buy_ReportService();

				Group_Buy_ReportVO.setGborder_id(gborder_id);
				Group_Buy_ReportVO.setMem_id(mem_id);
				Group_Buy_ReportVO.setFrep_content(frep_content);
//				Group_Buy_ReportVO.setFrep_status(frep_status);
				Group_Buy_ReportVO.setFrep_result(frep_result);
				Group_Buy_ReportVO.setEmp_id(emp_id);
				Group_Buy_ReportVO.setGbfrep_id(gbfrep_id);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("emp_effectVO", Group_Buy_ReportVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher fail = req
							.getRequestDispatcher("/backend/group_buy_report/listAllGroup_buy_Report.jsp");
					fail.forward(req, res);
					return; //程式中斷
				}


				/***************************2.開始修改資料*****************************************/

				Group_Buy_ReportVO = GBRSvc.updateGroup_Buy_Report(gborder_id,mem_id,frep_content,frep_result,emp_id,gbfrep_id);
				if((Group_Buy_ReportVO.getFrep_result())==0) {
					System.out.println("審核結果=0");
					GBRSvc.updatestatus0(frep_result, gborder_id, mem_id);
				}
				else if((Group_Buy_ReportVO.getFrep_result())==1) {
					System.out.println("審核結果=1");
					GBRSvc.updatestatus1(frep_result, gborder_id, mem_id);
				}else if((Group_Buy_ReportVO.getFrep_result())==2){
					System.out.println("審核結果=2");
					GBRSvc.updatestatus1(frep_result, gborder_id, mem_id);
				}

				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("Group_Buy_ReportVO", Group_Buy_ReportVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/backend/group_buy_report/listAllGroup_buy_Report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}

		if ("getOne_For_Insert".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();

			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數****************************************/
			Integer mem_id = Integer.valueOf(req.getParameter("mem_id"));

				/***************************2.開始查詢資料****************************************/
				Group_Buy_ReportService GBRSvc = new Group_Buy_ReportService();
				Group_Buy_ReportVO Group_Buy_ReportVO = GBRSvc.getOneGroup_Buy_Report(mem_id);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("Group_Buy_ReportVO", Group_Buy_ReportVO);
				String url = "/backend/group_buy_report/updateGroup_order_Report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
		}
		if ("insert".equals(action)) { // 來自addGroupBuyItem.jsp的請求


			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			Integer gborder_id = null;

			try {
				gborder_id = Integer.valueOf(req.getParameter("gborder_id").trim());
				if (gborder_id == null) {
					errorMsgs.add("團購訂單編號: 請勿空白");
				}
			} catch (NumberFormatException e) {
				errorMsgs.add("團購訂單編號 請填數字");
				e.printStackTrace();
			}

			Integer mem_id = null;

			try {
				mem_id = Integer.valueOf(req.getParameter("mem_id").trim());
				if (mem_id == null) {
					errorMsgs.add("會員編號: 請勿空白");
				}
			} catch (NumberFormatException e) {
				errorMsgs.add("會員編號 請填數字");
				e.printStackTrace();
			}


			String frep_content = req.getParameter("frep_content").trim();
			if (frep_content == null || frep_content.trim().length() == 0) {
				errorMsgs.add("團購檢舉內容 請勿空白");
			}


			Integer emp_id = null;

			try {
				emp_id = Integer.valueOf(req.getParameter("emp_id"));
				if (emp_id == null) {
					errorMsgs.add("員工編號: 請勿空白");
				}
			} catch (NumberFormatException e) {
				errorMsgs.add("emp_id 請填數字");
				e.printStackTrace();
			}


			Group_Buy_ReportVO Group_Buy_ReportVO = new Group_Buy_ReportVO();
			Group_Buy_ReportVO.setGborder_id(gborder_id);
			Group_Buy_ReportVO.setMem_id(mem_id);
			Group_Buy_ReportVO.setFrep_content(frep_content);
			Group_Buy_ReportVO.setEmp_id(emp_id);


			if (!errorMsgs.isEmpty()) {
				req.setAttribute("Group_Buy_ReportVO", Group_Buy_ReportVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/group_buy_report/addGroup_Buy_Report.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始修改資料 *****************************************/
			Group_Buy_ReportService GBRSvc = new Group_Buy_ReportService();
			Group_Buy_ReportVO = GBRSvc.addGroup_Buy_Report(gborder_id, mem_id, frep_content,
					emp_id);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
//			req.setAttribute("Group_Buy_ReportVO", Group_Buy_ReportVO);
			String url = "/frontend/mem/mem_index.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數***************************************/
				Integer gbfrep_id = Integer.valueOf(req.getParameter("gbfrep_id"));

				/***************************2.開始刪除資料***************************************/
				Group_Buy_ReportService GBRSvc = new Group_Buy_ReportService();
				GBRSvc.deleteGroup_Buy_Report(gbfrep_id);

				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				String url = "/backend/group_buy_report/listAllGroup_buy_Report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}

	}

}
