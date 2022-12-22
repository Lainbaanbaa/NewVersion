package com.emp_effect.controller;

import java.io.IOException;
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

import com.emp_effect.model.Emp_effectService;
import com.emp_effect.model.Emp_effectVO;

@WebServlet("/backend/emp_effect/Emp_effectServlet")
public class Emp_effectSevlet extends HttpServlet {

	private static final long serialVersionUID = -7396232889022699746L;

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
				RequestDispatcher fail = req.getRequestDispatcher("/backend/emp/listAllEmpNoEffect.jsp");
				fail.forward(req, res);
				return;
			}

			Integer emp_id = null;
			try {
				emp_id = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("emp_id", "員工編號格式不正確");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher fail = req.getRequestDispatcher("/backend/emp/listAllEmpNoEffect.jsp");
				fail.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			Emp_effectService emp_effectSvc = new Emp_effectService();
			List<Emp_effectVO> emp_effectVO = emp_effectSvc.getOneEmp(emp_id);
			if (emp_id == null) {
				errorMsgs.put("fail", "查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher fail = req.getRequestDispatcher("/backend/emp_effect/select_page.jsp");
				fail.forward(req, res);
				return;
			}
			req.setAttribute("emp_id", emp_id);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("emp_effectVO", emp_effectVO);
			String url = "/backend/emp_effect/listOneEmp_Effect.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}
		if ("getOne_For_DisplayEffect".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("effect_id");
			Integer effect_id = null;
			try {
				effect_id = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("effect_id", "權限編號格式不正確");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher fail = req.getRequestDispatcher("/backend/emp_effect/select_page.jsp");
				fail.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			Emp_effectService emp_effectSvc = new Emp_effectService();
			List<Emp_effectVO> emp_effectVO = emp_effectSvc.getOneEffect(effect_id);
			if (effect_id == null) {
				errorMsgs.put("fail", "查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher fail = req.getRequestDispatcher("/backend/emp_effect/select_page.jsp");
				fail.forward(req, res);
				return;
			}
			req.setAttribute("effect_id", effect_id);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("emp_effectVO", emp_effectVO);
			String url = "/backend/emp_effect/listOneEmp_Effect.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}
		if ("getOne_For_Update".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();

			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer emp_id = Integer.valueOf(req.getParameter("emp_id"));

			/*************************** 2.開始查詢資料 ****************************************/
			Emp_effectService emp_effectSvc = new Emp_effectService();
			List<Emp_effectVO> emp_effectVO = emp_effectSvc.getOneEmp(emp_id);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("emp_effectVO", emp_effectVO);
			String url = "/backend/emp_effect/update_Emp_Effect_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if ("insert".equals(action)) {

			   Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			   req.setAttribute("errorMsgs", errorMsgs);

			   /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			   Integer emp_id = Integer.valueOf(req.getParameter("emp_id").trim());
			   if (emp_id == null) {
			    errorMsgs.put("emp_id", "請勿空白");
			   }

			   Integer effect_id = Integer.valueOf(req.getParameter("effect_id").trim());
			   if (effect_id == null) {
			    errorMsgs.put("effect_id", "請勿空白");
			   }
			   Emp_effectService emp_effectSvc = new Emp_effectService();
			   Emp_effectVO emp_effectVO = new Emp_effectVO();
			   
			   if(emp_effectSvc.getEffect(emp_id, effect_id)!=null) {
			    errorMsgs.put("effect_id", "權限已有,請勿重複加入");
			   }
			   
			   emp_effectVO.setEmp_id(emp_id);
			   emp_effectVO.setEffect_id(effect_id);

			   if (!errorMsgs.isEmpty()) {
			    req.setAttribute("emp_effectVO", emp_effectVO);
			    RequestDispatcher fail = req.getRequestDispatcher("/backend/emp_effect/addEmp_Effect.jsp");
			    fail.forward(req, res);
			    return; // 程式中斷
			   }

			   /*************************** 2.開始修改資料 *****************************************/
			   emp_effectSvc = new Emp_effectService();
			   emp_effectVO = emp_effectSvc.addEmp(emp_id, effect_id);
			    emp_effectSvc = new Emp_effectService();
			   List<Emp_effectVO> emp_effectVO1 = emp_effectSvc.getOneEmp(emp_id);
			   /*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			   req.setAttribute("emp_effectVO", emp_effectVO1);
			   req.setAttribute("emp_id", emp_id);// 資料庫update成功後,正確的的empVO物件,存入req
			   String url = "/backend/emp_effect/listOneEmp_Effect.jsp";
			   RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			   successView.forward(req, res);
			  }
		if ("go_Insert".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();

			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer emp_id = Integer.valueOf(req.getParameter("emp_id"));

			Emp_effectVO emp_effectVO = new Emp_effectVO();
			emp_effectVO.setEmp_id(emp_id);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			session.setAttribute("emp_id", emp_id);
			session.setAttribute("emp_effectVO", emp_effectVO);

			String url = "/backend/emp_effect/addEmp_Effect.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if ("delete".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();

			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer emp_id = (Integer) session.getAttribute("emp_id");
			Integer effect_id = Integer.valueOf(req.getParameter("effect_id"));

			/*************************** 2.開始刪除資料 ***************************************/
			Emp_effectService emp_effectSvc = new Emp_effectService();
			emp_effectSvc.deleteEmp(emp_id, effect_id);

			List<Emp_effectVO> emp_effectVO = emp_effectSvc.getOneEmp(emp_id);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			req.setAttribute("emp_id", emp_id);
			session.setAttribute("emp_effectVO", emp_effectVO);
			System.out.println(emp_id);
			String url = "/backend/emp_effect/listOneEmp_Effect.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
}
