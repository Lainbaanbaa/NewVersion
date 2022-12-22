package com.group_buy.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;

@WebServlet("/GroupBuyFilterOneServlet")
public class GroupBuyFilterOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FilterConfig config;

	public void destroy() {
		config = null;
	}

//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		HttpServletRequest req = (HttpServletRequest) request;
//		HttpServletResponse res = (HttpServletResponse) response;
//		// 【取得 session】
//
//		HttpSession session = req.getSession();
//
//		// 【從 session 判斷此user是否登入過】
//
////		EmpService empSvc = new EmpService();
////		List<EmpVO> list = empSvc.login((String) session.getAttribute("account"),
////				(String) session.getAttribute("password"));
//		
//		
//		
//		
//		
//		session.setAttribute("list", list);
//		
//		
//		Integer effectid = null;
//		for (EmpVO a : list) {
//			effectid = a.getEffect_id();
//
//			if (effectid == 1) {
//				System.out.println("進入emp");
//				chain.doFilter(request, response);
//				System.out.println("離開emp");
//				return;
//			}
//		}
//		session.setAttribute("location", req.getRequestURI());
//		res.sendRedirect(req.getContextPath() + "/backend/login/backLogin.jsp");
//	}
//
//	public void init(FilterConfig fConfig) throws ServletException {
//		this.config = config;
//	}

}
