package com.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;


import com.emp.model.EmpService;
import com.emp.model.EmpVO;

//@WebFilter(urlPatterns = { "/backend/item/*",
//						   "/backend/commodityDetails/*",
//						   "/backend/favorite_list/*",
//						   "/backend/coupon/*"
//						})
public class StoreFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;
	private FilterConfig config;

	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession();

		EmpService empSvc = new EmpService();
		List<EmpVO> list = empSvc.login((String) session.getAttribute("account"),
				(String) session.getAttribute("password"));
		session.setAttribute("list", list);
		Integer effectid=null;
		for (EmpVO a : list) {
			effectid= a.getEffect_id();
			
			if (effectid == 2) {
			chain.doFilter(request, response);	
			return;
			} 
		}
		session.setAttribute("location", req.getRequestURI());
		res.sendRedirect(req.getContextPath() + "/backend/accessReject.html");
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.config = config;
	}

}