package com.emp.controller;

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

import com.emp.model.*;
import com.emp_effect.model.*;
import com.group_buy_order.model.Group_Buy_OrderVO;

@WebServlet("/backend/emp/EmpServlet")
public class EmpServlet extends HttpServlet {

	private static final long serialVersionUID = 432439384262214523L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();

		if ("getOne_For_Display".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("emp_id");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("emp_id", "請輸入員工編號");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher fail = req.getRequestDispatcher("/backend/emp/select_page.jsp");
				fail.forward(req, res);
				return;
			}

			Integer emp_id = null;
			try {
				emp_id = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("emp_id", "請輸入員工編號");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher fail = req.getRequestDispatcher("/backend/emp/select_page.jsp");
				fail.forward(req, res);
				return;// 程式中斷
			}
			System.out.println(req.getRequestURI());
			/*************************** 2.開始查詢資料 *****************************************/
			EmpService empSvc = new EmpService();
			EmpVO empVO = empSvc.getOneEmp(emp_id);
			if (empVO == null) {
				errorMsgs.put("emp_id", "查無員工");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher fail = req.getRequestDispatcher("/backend/emp/select_page.jsp");
				fail.forward(req, res);
				return;
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("empVO", empVO);
			String url = "/backend/emp/listOneEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}
		if ("getOne_For_Update".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();

			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer emp_id = Integer.valueOf(req.getParameter("emp_id"));

			/*************************** 2.開始查詢資料 ****************************************/
			EmpService empSvc = new EmpService();
			EmpVO empVO = empSvc.getOneEmp(emp_id);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("empVO", empVO);
			String url = "/backend/emp/update_emp_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if ("update".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			EmpService empSvc = new EmpService();
			EmpVO empVO = new EmpVO();

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			String emp_name = req.getParameter("emp_name");
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (emp_name == null || emp_name.trim().length() == 0) {
				errorMsgs.put("emp_name", "請勿空白");
			} else if (!emp_name.trim().matches(enameReg)) {
				errorMsgs.put("emp_name", "只能是中、英文字母、數字和_ 長度在2到10之間");
			}

			String account = req.getParameter("account").trim();
			String accountReg = "^[(a-zA-Z0-9_)]{5,10}$";
			try {
				account = req.getParameter("account").trim();
				if (account == null || account.trim().length() == 0) {
					errorMsgs.put("account", " 請勿空白");
				} else if (!account.trim().matches(accountReg)) {
					errorMsgs.put("account", "英文字母、數字和_ , 且長度必需在5到10之間");
					empVO = empSvc.getOneAc(account);
				} 
				empVO = empSvc.getOneAc(account);
				if(empVO.getEmp_id()!= null) {
					errorMsgs.put("account", "帳號不可以重複");
					}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			String password = req.getParameter("password").trim();
			String passwordReg = "^[(a-zA-Z0-9_)]{5,10}$";
			if (password == null || password.trim().length() == 0) {
				errorMsgs.put("password", "請勿空白");
			} else if (!password.trim().matches(passwordReg)) {
				errorMsgs.put("password", "只能是英文字母、數字和_長度在5到10之間");
			}

			java.sql.Date onjob_date = null;
			try {
				onjob_date = java.sql.Date.valueOf(req.getParameter("onjob_date").trim());
			} catch (IllegalArgumentException e) {
				onjob_date = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.put("onjob_date", "請輸入日期!");
			}

			Integer emp_status = Integer.valueOf(req.getParameter("emp_status").trim());
			if (emp_status == null) {
				errorMsgs.put("emp_status", "請勿空白");
			} else if (emp_status > 1) {
				errorMsgs.put("emp_status", "只能是0或1");
			}
			Integer emp_id = Integer.valueOf(req.getParameter("emp_id").trim());

			empVO = new EmpVO();

			empVO.setEmp_name(emp_name);
			empVO.setAccount(account);
			empVO.setPassword(password);
			empVO.setOnjob_date(onjob_date);
			empVO.setEmp_status(emp_status);
			empVO.setEmp_id(emp_id);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("empVO", empVO);
				RequestDispatcher fail = req.getRequestDispatcher("/backend/emp/update_emp_input.jsp");
				fail.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			empSvc = new EmpService();
			empVO = empSvc.updateEmp(emp_id, emp_name, account, password, onjob_date, emp_status);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("empVO", empVO);
			String url = "/backend/emp/listOneEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			EmpService empSvc = new EmpService();
			EmpVO empVO = new EmpVO();
			String emp_name = req.getParameter("emp_name");
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (emp_name == null || emp_name.trim().length() == 0) {
				errorMsgs.put("emp_name", " 請勿空白");
			} else if (!emp_name.trim().matches(enameReg)) {
				errorMsgs.put("emp_name", "只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			String account = null;
			String accountReg = "^[(a-zA-Z0-9_)]{5,10}$";

			try {
				account = req.getParameter("account").trim();
				if (account == null || account.trim().length() == 0) {
					errorMsgs.put("account", " 請勿空白");
				}  else if(!account.trim().matches(accountReg)) {
					errorMsgs.put("account", "英文字母、數字和_ , 且長度必需在5到10之間");
					
				} 
				empVO = empSvc.getOneAc(account);
				if(empVO.getEmp_id()!= null) {
					errorMsgs.put("account", "帳號不可以重複");
					}
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			String password = req.getParameter("password").trim();
			String passwordReg = "^[(a-zA-Z0-9_)]{5,10}$";
			if (password == null || password.trim().length() == 0) {
				errorMsgs.put("password", "請勿空白");
			} else if (!password.trim().matches(passwordReg)) {
				errorMsgs.put("password", "英文字母、數字和_ , 且長度必需在5到10之間");
			}

			java.sql.Date onjob_date = null;
			try {
				onjob_date = java.sql.Date.valueOf(req.getParameter("onjob_date").trim());
			} catch (IllegalArgumentException e) {
				onjob_date = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.put("onjob_date", "請輸入日期!");
			}
			Integer emp_status = null;
			try {
				emp_status = Integer.valueOf(req.getParameter("emp_status").trim());

			} catch (Exception e) {
				if (emp_status == null) {
					errorMsgs.put("emp_status", "員工狀態: 請勿空白");
				} else if (emp_status > 1) {
					errorMsgs.put("emp_status", "員工狀態: 只能是0或1");
				}
			}
			Integer effect_id = Integer.valueOf(req.getParameter("effect_id").trim());
			System.out.println(effect_id);

			empVO = new EmpVO();
			empVO.setEmp_name(emp_name);
			empVO.setAccount(account);
			empVO.setPassword(password);
			empVO.setOnjob_date(onjob_date);
			empVO.setEmp_status(emp_status);

			Emp_effectVO emp_effectVO = new Emp_effectVO();
			emp_effectVO.setEffect_id(effect_id);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/emp/addEmp.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			empSvc = new EmpService();
			empVO = empSvc.addEmp(emp_name, account, password, onjob_date, emp_status, effect_id);
			
	
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/backend/emp/listAllEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer empno = Integer.valueOf(req.getParameter("emp_id"));

			/*************************** 2.開始刪除資料 ***************************************/
			EmpService empSvc = new EmpService();
			empSvc.deleteEmp(empno);
			
		

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/backend/emp/listAllEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}

		if ("listemp_and_effect".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
			if (req.getParameter("whichPage") == null) {
				Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
				session.setAttribute("map", map1);
				map = map1;
			}
			EmpService empSvc = new EmpService();
			List<EmpVO> list = empSvc.getAll(map);

			if (list.isEmpty()) {
				errorMsgs.put("fail", "查無資料");
				RequestDispatcher successView = req.getRequestDispatcher("/backend/emp/select_page.jsp");
				successView.forward(req, res);
				return;
			}

			req.setAttribute("listemp_and_effect", list);
			RequestDispatcher successView = req.getRequestDispatcher("/backend/emp/listAllEmp.jsp");
			successView.forward(req, res);

		}
	}
}
