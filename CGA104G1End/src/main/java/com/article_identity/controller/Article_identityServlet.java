package com.article_identity.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.article_identity.model.Article_identityService;
import com.article_identity.model.Article_identityVO;

@WebServlet("/Article_identityServlet")
public class Article_identityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("insert".equals(action)) {
			Integer mem_id = Integer.valueOf(req.getParameter("mem_id").trim());
			String article_pic = req.getParameter("article_pic").trim();
			
			Article_identityService article_identitySvc =new Article_identityService();
			Article_identityVO article_identityVO = article_identitySvc.addArticle_picture(mem_id, article_pic);
			req.setAttribute("Article_identityVO", article_identityVO);
			String url = "/frontend/article/select_page.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}

}
