package com.effect.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.effect.model.EffectService;
import com.effect.model.EffectVO;

@WebServlet("/backend/effect/EffectServlet")
public class EffectServlet  extends HttpServlet{

	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("getOne_For_Display".equals(action)) {
//			 Select.jsp請求
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// 請求參數,錯誤處理
			String eff = req.getParameter("effect_id");
			// 去除空白後長度為0
			if (eff == null || (eff.trim()).length() == 0) {
				errorMsgs.put("effect_id","請輸入權限編號");
			}
			// 是否為空字串(非空白)
			if (!errorMsgs.isEmpty()) {
				// 轉發到
				RequestDispatcher fail = req.getRequestDispatcher("/backend/effect/select_page.jsp");
				fail.forward(req, res);
				return;
			}

			Integer effect_id = null;
			try {
				effect_id = Integer.valueOf(eff);
			} catch (NumberFormatException e) {
				errorMsgs.put("effect_id","權限編號不正確");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher fail = req.getRequestDispatcher("/backend/effect/select_page.jsp");
				fail.forward(req, res);
				return;
			}

			// 驗證過,開始查詢資料
			EffectService effectSvc = new EffectService();
			EffectVO effectVO = effectSvc.getOnEffect(effect_id);
			if (effectVO == null) {
				errorMsgs.put("effect_id","查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher fail = req.getRequestDispatcher("/backend/effect/select_page.jsp");
				fail.forward(req, res);
				return;
			}

			// 回傳資料
			req.setAttribute("effectVO", effectVO);
			String url = "/backend/effect/listOneEffect.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Integer effect_id = Integer.valueOf(req.getParameter("effect_id"));

			EffectService effectSvc  = new EffectService();
			EffectVO effectVO = effectSvc.getOnEffect(effect_id);

			req.setAttribute("effectVO", effectVO);
			String url = "/backend//effect/update_effect_input.jsp";

			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("update".equals(action)) {
			
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			Integer effect_id = Integer.valueOf(req.getParameter("effect_id").trim());
			String effect_name = req.getParameter("effect_name");
		if(effect_name == null || effect_name.trim().length() == 0 ) {
			errorMsgs.put("effect_name","請輸入權限名稱");
		}
		String effect_info = req.getParameter("effect_info");
		if(effect_info == null || effect_info.trim().length() == 0 ) {
			errorMsgs.put("effect_info","請輸入權限說明");
		}
			EffectVO effectVO = new EffectVO();
			effectVO.setEffect_id(effect_id);
			effectVO.setEffect_name(effect_name);
			effectVO.setEffect_info(effect_info);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("effectVO", effectVO);
				RequestDispatcher fail = req.getRequestDispatcher("/backend/effect/update_effect_input.jsp");
				fail.forward(req, res);
				return;
			}
			// 修改
			EffectService effectSvc = new EffectService();
			// 修改完成
//			
			effectVO = effectSvc.updateEffect(effect_id, effect_name, effect_info);

			req.setAttribute("effectVO", effectVO);
			String url = "/backend/effect/listOneEffect.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if ("insert".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String effect_name = req.getParameter("effect_name").trim();
			if (effect_name == null || effect_name.trim().length() == 0) {
				errorMsgs.put("effect_name","權限名稱請勿留白");
			}
			
			String effect_info = req.getParameter("effect_info").trim();
			if (effect_info == null || effect_info.trim().length() == 0) {
				errorMsgs.put("effect_info","權限說明請勿留白");
			}
			
			EffectVO effectVO =new EffectVO();
			effectVO.setEffect_name(effect_name);
			effectVO.setEffect_info(effect_info);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("effectVO", effectVO);
				RequestDispatcher fail = req.getRequestDispatcher("/backend/effect/addEffect.jsp");
				fail.forward(req, res);
				return;
			}

			// 新增資料
			EffectService effectSvc = new EffectService();
			effectVO = effectSvc.addEffect(effect_name, effect_info);
//			
			// 完成,轉交
			req.setAttribute("effectVO", effectVO);
			String url = "/backend/effect/listAllEffect.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if("delete".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			//接收請求
			Integer effect_id = Integer.valueOf(req.getParameter("effect_id"));
			//刪除
			EffectService effectSvc = new EffectService();
			effectSvc.deleteEffect(effect_id);
			//完成,轉交
			String url = "/backend/effect/listAllEffect.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}	
}
