/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.filter;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebFilter(urlPatterns = { "/backend/commodityDetails/*" })
public class CouponFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;
	private FilterConfig config;

	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】

		HttpSession session = req.getSession();

		// 【從 session 判斷此user是否登入過】

		EmpService empSvc = new EmpService();
		List<EmpVO> list = empSvc.login((String) session.getAttribute("account"),
				(String) session.getAttribute("password"));
	session.setAttribute("list", list);
		Integer effectid=null;
		for (EmpVO a : list) {
			effectid= a.getEffect_id();

			if (effectid == 1) {
				System.out.println("進入emp");
			chain.doFilter(request, response);
			System.out.println("離開emp");
			return;
			}
		}
		session.setAttribute("location", req.getRequestURI());
		res.sendRedirect(req.getContextPath() + "/backend/login/backLogin.jsp");
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.config = config;
	}

}
