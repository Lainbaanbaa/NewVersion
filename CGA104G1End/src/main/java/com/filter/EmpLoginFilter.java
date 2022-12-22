package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter(urlPatterns =  { "/backend/emp/*", 
							"/backend/effect/*", 
							"/backend/commodityDetails/*", 
							"/backend/coupon/*", 
							"/backend/discount/*", 
							"/backend/emp_effect/*", 
							"/backend/favorite_list/*", 
							"/backend/group_buy/*", 
							"/backend/group_Buy_Item/*", 
							"/backend/group_buy_order/*", 
							"/backend/group_buy_report/*", 
							"/backend/group_join/*", 
							"/backend/mem/*", 
							"/backend/qualified_doctor/*", 
							"/backend/article/*", 
							"/backend/news/*", 
							"/NameServlet" ,
							"/backend/item/*"
							} )

public class EmpLoginFilter implements Filter {
   
	private FilterConfig config;

	public void init(FilterConfig config) {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}


	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】
		
	
		HttpSession session = req.getSession();

		// 【從 session 判斷此user是否登入過】

		 Object account = session.getAttribute("account");
		 System.out.println(account);
		 if(account == null){
			 System.out.println(req.getRequestURI());
		 	session.setAttribute("location", req.getRequestURI());
		 	res.sendRedirect(req.getContextPath()+"/backend/login/backLogin.jsp");		 	
		 	return; 
		   }else {
				chain.doFilter(request, response);
			}
		System.out.println("123");
	}
}
